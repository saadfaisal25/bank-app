package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import main.BankApp;
import model.Address;

public class SignupView extends Pane {
    private static SignupView instance = new SignupView();
    private AddressPane addressPane;
    private TextField userField;
    private PasswordField passField;
    private TextField nameField;
    private TextField ageField;

    public SignupView() {
        setPrefSize(800, 600);

        // create back button
        Button backB = new Button("Back");
        backB.setPrefSize(100, 40);
        backB.relocate(0,0);

        // Create signup page title label
        Label titleLabel = new Label("Sign Up Form");
        titleLabel.setFont(new Font("Trebuchet MS", 30));

        HBox topBox = new HBox(titleLabel);
        topBox.setPrefWidth(800);
        topBox.setLayoutY(50);
        topBox.setAlignment(Pos.CENTER);

        // Create username label and field
        Label userLabel = new Label("Username:");
        userLabel.setFont(new Font("Trebuchet MS", 20));

        userField = new TextField();
        userField.setPrefSize(175, 25);

        HBox midBox1 = new HBox(userLabel, userField);
        midBox1.setPrefWidth(800);
        midBox1.setLayoutY(100);
        midBox1.setSpacing(50);
        midBox1.setAlignment(Pos.CENTER);

        // Create password label and field
        Label passLabel = new Label("Password:");
        passLabel.setFont(new Font("Trebuchet MS", 20));

        passField = new PasswordField();
        passField.setPrefSize(175, 25);

        HBox midBox2 = new HBox(passLabel, passField);
        midBox2.setPrefWidth(800);
        midBox2.setLayoutY(150);
        midBox2.setSpacing(50);
        midBox2.setAlignment(Pos.CENTER);

        // Create name label and field
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(new Font("Trebuchet MS", 20));

        nameField = new TextField();
        nameField.setPrefSize(175, 25);

        HBox midBox3 = new HBox(nameLabel, nameField);
        midBox3.setPrefWidth(800);
        midBox3.setLayoutY(200);
        midBox3.setSpacing(50);
        midBox3.setAlignment(Pos.CENTER);

        // Create age label and field
        Label ageLabel = new Label("Age:");
        ageLabel.setFont(new Font("Trebuchet MS", 20));

        ageField = new TextField();
        ageField.setPrefSize(175, 25);

        HBox midBox4 = new HBox(ageLabel, ageField);
        midBox4.setPrefWidth(800);
        midBox4.setLayoutY(250);
        midBox4.setSpacing(50);
        midBox4.setAlignment(Pos.CENTER);

        // Create address pane
        addressPane = new AddressPane();

        HBox midBox5 = new HBox(addressPane);
        midBox5.setPrefWidth(800);
        midBox5.setLayoutY(300);
        midBox5.setSpacing(50);
        midBox5.setAlignment(Pos.CENTER);

        // Create sign up button
        Button signupB = new Button("Sign Up");
        signupB.setPrefSize(250, 50);

        HBox botBox = new HBox(signupB);
        botBox.setPrefWidth(800);
        botBox.setAlignment(Pos.CENTER);
        botBox.setLayoutY(520);

        getChildren().addAll(backB, topBox, midBox1, midBox2, midBox3, midBox4, midBox5, botBox);

        backB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleBack();
            }
        });

        signupB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleSignup();
            }
        });
    }

    public static SignupView getInstance() {
        return instance;
    }

    // clear all text fields
    public void clearFields() {
        userField.clear();
        passField.clear();
        nameField.clear();
        ageField.clear();
        addressPane.clear();
    }

    // set scene to go back to AuthenticateView
    public void handleBack() {
        this.getScene().setRoot(AuthenticateView.getInstance());
    }

    // create dialog window for successful sign up
    public void createDialogSuccess() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Success");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        dialog.setContentText("Sign Up Successful. You can now log in.");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

        clearFields();
    }

    // create dialog window for failed sign up
    public void createDialogFail() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Failure");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        dialog.setContentText("There was an error creating the user. Make sure the fields are valid.");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    // get all textfield values and create a new user in the model
    public void handleSignup() {
        String username = userField.getText();
        String password = passField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        Address address = addressPane.getAddress();

        // create new bank user using text field input
        try (PrintWriter out = new PrintWriter(new FileWriter(BankApp.getDirectory()+"users.txt", true));) {
            BankApp.getModel().createNewUser(out, username, password, name, address, age);
            createDialogSuccess();
            // set scene to AuthenticateView so user can now log in
            this.getScene().setRoot(AuthenticateView.getInstance());
        } catch (IOException e) {
            createDialogFail();
            System.out.println("Error: IO Exception creating user");
        }
    }
}
