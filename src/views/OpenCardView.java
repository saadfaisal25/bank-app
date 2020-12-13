package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.BankApp;

public class OpenCardView extends Pane {
    private static OpenCardView instance = new OpenCardView();
    private ToggleGroup group;
    private RadioButton travelB;
    private RadioButton rewardsB;
    private RadioButton cashBackB;
    private TextField creditField;
    private Button applyB;

    public OpenCardView() {
        setPrefSize(800, 600);

        // create back button
        Button backB = new Button("Back");
        backB.setPrefSize(100, 40);
        backB.relocate(0,0);

        // create open credit card page title label
        Label titleLabel = new Label("Select A Credit card");
        titleLabel.setFont(new Font("Trebuchet MS", 30));

        HBox topBox = new HBox(titleLabel);
        topBox.setPrefWidth(800);
        topBox.setLayoutY(50);
        topBox.setAlignment(Pos.CENTER);

        // set new toggle group
        group = new ToggleGroup();

        // create labels to show travel card details
        Label travelLabel = new Label("Travel Card");
        travelLabel.setFont(new Font("Trebuchet MS", 20));
        Label travelInfo1 = new Label("+ Get Airline Dollars on \nEvery Purchase");
        travelInfo1.setTextAlignment(TextAlignment.CENTER);
        travelInfo1.setFont(new Font("Trebuchet MS", 15));
        Label travelInfo2 = new Label("- $100 Annual Fee");
        travelInfo2.setFont(new Font("Trebuchet MS", 15));

        // create travel card radio button
        travelB = new RadioButton();
        travelB.setToggleGroup(group);

        VBox travelInfo = new VBox(travelLabel, travelInfo1, travelInfo2, travelB);
        travelInfo.setSpacing(20);
        travelInfo.setPrefWidth(260);
        travelInfo.relocate(0, 200);
        travelInfo.setAlignment(Pos.CENTER);

        // create labels to show rewards card details
        Label rewardsLabel = new Label("Rewards Card");
        rewardsLabel.setFont(new Font("Trebuchet MS", 20));
        Label rewardsInfo1 = new Label("+ Get Points on Every Purchase");
        rewardsInfo1.setFont(new Font("Trebuchet MS", 15));
        Label rewardsInfo2 = new Label("+ No Annual Fee");
        rewardsInfo2.setFont(new Font("Trebuchet MS", 15));

        // create rewards card radio button
        rewardsB = new RadioButton();
        rewardsB.setToggleGroup(group);

        VBox rewardsInfo = new VBox(rewardsLabel, rewardsInfo1, rewardsInfo2, rewardsB);
        rewardsInfo.setSpacing(25);
        rewardsInfo.setPrefWidth(280);
        rewardsInfo.relocate(260, 200);
        rewardsInfo.setAlignment(Pos.CENTER);

        // create labels to show cash back card details
        Label cashBackLabel = new Label("Cash Back Card");
        cashBackLabel.setFont(new Font("Trebuchet MS", 20));
        Label cashBackInfo1 = new Label("+ Get Cash Back on Every $100 Spent");
        cashBackInfo1.setFont(new Font("Trebuchet MS", 15));
        Label cashBackInfo2 = new Label("+ No Annual Fee");
        cashBackInfo2.setFont(new Font("Trebuchet MS", 15));

        // create cash back radio button
        cashBackB = new RadioButton();
        cashBackB.setToggleGroup(group);

        VBox cashBackInfo = new VBox(cashBackLabel, cashBackInfo1, cashBackInfo2, cashBackB);
        cashBackInfo.setSpacing(25);
        cashBackInfo.setPrefWidth(260);
        cashBackInfo.relocate(540, 200);
        cashBackInfo.setAlignment(Pos.CENTER);

        // create label and text field to enter credit score
        Label creditLabel = new Label("Credit Score:");
        creditLabel.setFont(new Font("Trebuchet MS", 15));

        creditField = new TextField();
        creditField.setPrefSize(100, 25);

        // create apply button
        applyB = new Button("Apply For Credit Card");
        applyB.setPrefSize(300, 60);
        applyB.setDisable(true);

        // add the bottom elements to an HBox and center all HBox elements
        HBox botBox = new HBox(creditLabel, creditField, applyB);
        botBox.setSpacing(30);
        botBox.setPrefWidth(800);
        botBox.relocate(0, 500);
        botBox.setAlignment(Pos.CENTER);

        getChildren().addAll(backB, topBox, travelInfo, rewardsInfo, cashBackInfo, botBox);

        backB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleBack();
            }
        });

        travelB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableApplyB();
            }
        });

        rewardsB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableApplyB();
            }
        });

        cashBackB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enableApplyB();
            }
        });

        applyB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleApply();
            }
        });
    }

    public static OpenCardView getInstance() {
        return instance;
    }

    // clear fields and selections and go back to OverviewView scene
    public void handleBack() {
        travelB.setSelected(false);
        rewardsB.setSelected(false);
        cashBackB.setSelected(false);
        creditField.clear();
        applyB.setDisable(true);
        this.getScene().setRoot(OverviewView.getInstance());
    }

    // enable the apply button when a radio button is selected
    public void enableApplyB() {
        applyB.setDisable(false);
    }

    // create a dialog window to show the application was approved
    public void createDialogApprove(String cardName) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Card Application Approved");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        dialog.setContentText("You have opened a new " + cardName);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

        OverviewView.getInstance().update();
        handleBack();
    }

    // create a dialog window to show the application was denied
    public void createDialogDeny() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Card Application Denied");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        dialog.setContentText("Your credit score does not meet the requirements");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    // check which radio button was selected and create the corresponding credit card for the user
    public void handleApply() {
        if (group.getSelectedToggle() == travelB) {
            int creditScore = Integer.parseInt(creditField.getText());
            if (BankApp.getModel().openTravelCard(creditScore)) {
                createDialogApprove("Travel Credit Card");
            } else {
                createDialogDeny();
            }
        } else if (group.getSelectedToggle() == rewardsB) {
            int creditScore = Integer.parseInt(creditField.getText());
            if (BankApp.getModel().openRewardsCard(creditScore)) {
                createDialogApprove("Rewards Credit Card");
            } else {
                createDialogDeny();
            }
        } else if (group.getSelectedToggle() == cashBackB) {
            int creditScore = Integer.parseInt(creditField.getText());
            if (BankApp.getModel().openCashBackCard(creditScore)) {
                createDialogApprove("Cash Back Credit Card");
            } else {
                createDialogDeny();
            }
        }
    }

}
