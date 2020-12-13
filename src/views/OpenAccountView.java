package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.BankApp;

public class OpenAccountView extends Pane {
    private static OpenAccountView instance = new OpenAccountView();
    private ToggleGroup group;
    private RadioButton savingsB;
    private RadioButton chequingB;
    private Button openB;

    public OpenAccountView() {
        setPrefSize(800, 600);

        // create back button
        Button backB = new Button("Back");
        backB.setPrefSize(100, 40);
        backB.relocate(0,0);

        // create open account page title label
        Label titleLabel = new Label("Select An Account");
        titleLabel.setFont(new Font("Trebuchet MS", 30));

        HBox topBox = new HBox(titleLabel);
        topBox.setPrefWidth(800);
        topBox.setLayoutY(50);
        topBox.setAlignment(Pos.CENTER);

        // set new toggle group
        group = new ToggleGroup();

        // create labels to show savings account details
        Label savingsLabel = new Label("Savings Account");
        savingsLabel.setFont(new Font("Trebuchet MS", 20));
        Label savingsInfo1 = new Label("+ No Monthly Fee");
        savingsInfo1.setFont(new Font("Trebuchet MS", 15));
        Label savingsInfo2 = new Label("+ Gain 1% Monthly Interest");
        savingsInfo2.setFont(new Font("Trebuchet MS", 15));
        Label savingsInfo3 = new Label("- $3.00 Withdrawal Fee");
        savingsInfo3.setFont(new Font("Trebuchet MS", 15));

        // create a savings account radio button
        savingsB = new RadioButton();
        savingsB.setToggleGroup(group);

        VBox savingsInfo = new VBox(savingsLabel, savingsInfo1, savingsInfo2, savingsInfo3, savingsB);
        savingsInfo.setSpacing(20);
        savingsInfo.setPrefWidth(400);
        savingsInfo.relocate(0, 200);
        savingsInfo.setAlignment(Pos.CENTER);

        // create labels to show chequing account details
        Label chequingLabel = new Label("Chequing Account");
        chequingLabel.setFont(new Font("Trebuchet MS", 20));
        Label chequingInfo1 = new Label("+ No Withdrawal Fee");
        chequingInfo1.setFont(new Font("Trebuchet MS", 15));
        Label chequingInfo2 = new Label("- $10 Monthly Maintenance Fee");
        chequingInfo2.setFont(new Font("Trebuchet MS", 15));

        // create a chequing account radio button
        chequingB = new RadioButton();
        chequingB.setToggleGroup(group);

        VBox chequingInfo = new VBox(chequingLabel, chequingInfo1, chequingInfo2, chequingB);
        chequingInfo.setSpacing(25);
        chequingInfo.setPrefWidth(400);
        chequingInfo.relocate(400, 200);
        chequingInfo.setAlignment(Pos.CENTER);

        // create open account button
        openB = new Button("Open Account");
        openB.setPrefSize(300, 60);
        openB.relocate(250, 500);
        openB.setDisable(true);

        getChildren().addAll(backB, topBox, savingsInfo, chequingInfo, openB);

        backB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleBack();
            }
        });

        savingsB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableOpenB();
            }
        });

        chequingB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableOpenB();
            }
        });

        openB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleOpen();
            }
        });
    }

    public static OpenAccountView getInstance() {
        return instance;
    }

    public void handleBack() {
        savingsB.setSelected(false);
        chequingB.setSelected(false);
        openB.setDisable(true);
        this.getScene().setRoot(OverviewView.getInstance());
    }

    // enable open account button when a radio button is selected
    public void enableOpenB() {
        openB.setDisable(false);
    }

    // create a dialog window when an account is created
    public void createDialog(String accName) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Account Created");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        dialog.setContentText("You have opened a new " + accName);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

        OverviewView.getInstance().update();
        handleBack();
    }

    // if the selected account is opened successfully, show the dialog window and go back to the overview View
    public void handleOpen() {
        if (group.getSelectedToggle() == savingsB) {
            if (BankApp.getModel().openSavingsAccount()) {
                createDialog("Savings Account");
            }
        } else if (group.getSelectedToggle() == chequingB) {
            if (BankApp.getModel().openChequingAccount()) {
                createDialog("Chequing Account");
            }
        }
    }
}
