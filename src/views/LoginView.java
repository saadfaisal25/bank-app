package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import main.BankApp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoginView extends Pane {
    private static LoginView instance = new LoginView();
    private TextField userField;
    private PasswordField passField;

    private LoginView() {
        setPrefSize(800, 600);

        // create back button
        Button backB = new Button("Back");
        backB.setPrefSize(100, 40);
        backB.relocate(0,0);

        // Create login page title label
        Label titleLabel = new Label("Log In Form");
        titleLabel.setFont(new Font("Trebuchet MS", 30));

        HBox topBox = new HBox(titleLabel);
        topBox.setPrefWidth(800);
        topBox.setLayoutY(50);
        topBox.setAlignment(Pos.CENTER);

        // Create username label and text field
        Label userLabel = new Label("Username:");
        userLabel.setFont(new Font("Trebuchet MS", 20));

        userField = new TextField();
        userField.setPrefSize(175, 50);

        HBox midBox1 = new HBox(userLabel, userField);
        midBox1.setPrefWidth(800);
        midBox1.setLayoutY(250);
        midBox1.setSpacing(50);
        midBox1.setAlignment(Pos.CENTER);

        // create password label and text field
        Label passLabel = new Label("Password:");
        passLabel.setFont(new Font("Trebuchet MS", 20));

        passField = new PasswordField();
        passField.setPrefSize(175, 50);

        HBox midBox2 = new HBox(passLabel, passField);
        midBox2.setPrefWidth(800);
        midBox2.setLayoutY(350);
        midBox2.setSpacing(50);
        midBox2.setAlignment(Pos.CENTER);

        // Create login button
        Button loginB = new Button("Log In");
        loginB.setPrefSize(250, 50);

        HBox botBox = new HBox(loginB);
        botBox.setPrefWidth(800);
        botBox.setAlignment(Pos.CENTER);
        botBox.setLayoutY(520);

        getChildren().addAll(backB, topBox, midBox1, midBox2, botBox);

        backB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleBack();
            }
        });

        loginB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleLogin();
            }
        });
    }

    public static LoginView getInstance() {
        return instance;
    }

    // set scene to go back to AuthenticateView
    public void handleBack() {
        this.getScene().setRoot(AuthenticateView.getInstance());
    }

    // show a new dialog window so user can acknowledge successful login
    public void createDialogSuccess() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Success");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        dialog.setContentText("Log In Successful");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

        userField.clear();
        passField.clear();
    }

    // dialog window to show login failure
    public void createDialogFail() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Failure");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        dialog.setContentText("Invalid username or password. Please try again.");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

        passField.clear();
    }

    // checks if username and password is valid
    public void handleLogin() {
        String username = userField.getText();
        String password = passField.getText();

        // if the username and password are valid, set the current application user from the database
        if (BankApp.getModel().authenticate(username, password)) {
            System.out.println("Log in authenticated");
            try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(BankApp.getDirectory()+username+".txt"))) {
                BankApp.getModel().setUserFromData(objIn);
                System.out.println("User data set");
            } catch (IOException e) {
                System.out.println("Error: IO Exception setting user");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            createDialogSuccess();
            // set the scene to Overview View
            this.getScene().setRoot(OverviewView.getInstance());
        } else {
            createDialogFail();
        }

    }
}

