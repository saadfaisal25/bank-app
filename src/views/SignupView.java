package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.Address;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SignupView extends Pane {
    private static SignupView instance = new SignupView();
    private AddressPane addressPane;
    private TextField userField;
    private TextField passField;
    private TextField nameField;
    private TextField ageField;

    public SignupView() {
        setPrefSize(800, 600);

        Button backB = new Button("Back");
        backB.setPrefSize(100, 40);
        backB.relocate(0,0);

        // Create login page title label
        Label titleLabel = new Label("Sign Up Form");
        titleLabel.setFont(new Font("Trebuchet MS", 30));

        HBox topBox = new HBox(titleLabel);
        topBox.setPrefWidth(800);
        topBox.setLayoutY(50);
        topBox.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        Label userLabel = new Label("Username:");
        userLabel.setFont(new Font("Trebuchet MS", 20));

        userField = new TextField();
        userField.setPrefSize(175, 25);

        HBox midBox1 = new HBox(userLabel, userField);
        midBox1.setPrefWidth(800);
        midBox1.setLayoutY(100);
        midBox1.setSpacing(50);
        midBox1.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        Label passLabel = new Label("Password:");
        passLabel.setFont(new Font("Trebuchet MS", 20));

        passField = new TextField();
        passField.setPrefSize(175, 25);

        HBox midBox2 = new HBox(passLabel, passField);
        midBox2.setPrefWidth(800);
        midBox2.setLayoutY(150);
        midBox2.setSpacing(50);
        midBox2.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(new Font("Trebuchet MS", 20));

        nameField = new TextField();
        nameField.setPrefSize(175, 25);

        HBox midBox3 = new HBox(nameLabel, nameField);
        midBox3.setPrefWidth(800);
        midBox3.setLayoutY(200);
        midBox3.setSpacing(50);
        midBox3.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        Label ageLabel = new Label("Age:");
        ageLabel.setFont(new Font("Trebuchet MS", 20));

        ageField = new TextField();
        ageField.setPrefSize(175, 25);

        HBox midBox4 = new HBox(ageLabel, ageField);
        midBox4.setPrefWidth(800);
        midBox4.setLayoutY(250);
        midBox4.setSpacing(50);
        midBox4.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        addressPane = new AddressPane();

        HBox midBox5 = new HBox(addressPane);
        midBox5.setPrefWidth(800);
        midBox5.setLayoutY(300);
        midBox5.setSpacing(50);
        midBox5.setAlignment(Pos.CENTER);

        // Create quit button
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

    public void handleBack() {
        this.getScene().setRoot(AuthenticateView.getInstance());
    }

    public void handleSignup() {
        String username = userField.getText();
        String password = passField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        Address address = addressPane.getAddress();

        try (PrintWriter out = new PrintWriter(new FileWriter(BankApp.getDirectory()+"users.txt", true));) {
            BankApp.getModel().createNewUser(out, username, password, name, address, age);
            System.out.println("User Created");
        } catch (IOException e) {
            System.out.println("Error: IO Exception creating user");
        }
    }
}
