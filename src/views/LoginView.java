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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoginView extends Pane {
    private static LoginView instance = new LoginView();
    private TextField userField;
    private TextField passField;

    private LoginView() {
        setPrefSize(800, 600);

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

        // Create log in and sign up buttons
        Label userLabel = new Label("Username:");
        userLabel.setFont(new Font("Trebuchet MS", 20));

        userField = new TextField();
        userField.setPrefSize(175, 50);

        HBox midBox1 = new HBox(userLabel, userField);
        midBox1.setPrefWidth(800);
        midBox1.setLayoutY(250);
        midBox1.setSpacing(50);
        midBox1.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        Label passLabel = new Label("Password:");
        passLabel.setFont(new Font("Trebuchet MS", 20));

        passField = new TextField();
        passField.setPrefSize(175, 50);

        HBox midBox2 = new HBox(passLabel, passField);
        midBox2.setPrefWidth(800);
        midBox2.setLayoutY(350);
        midBox2.setSpacing(50);
        midBox2.setAlignment(Pos.CENTER);

        // Create quit button
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

    public void handleBack() {
        this.getScene().setRoot(AuthenticateView.getInstance());
    }

    public void handleLogin() {
        String username = userField.getText();
        String password = passField.getText();

        if (BankApp.getModel().authenticate(username, password)) {
            System.out.println("Log in authenticated");
            try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(BankApp.getDirectory()+username+".txt"))) {
                BankApp.getModel().setUserFromData(objIn);
                System.out.println("User data set");
                System.out.println(BankApp.getModel().getUser().getName());
                System.out.println(BankApp.getModel().getUser().getAddress());
                System.out.println(BankApp.getModel().getUser().getAge());
            } catch (IOException e) {
                System.out.println("Error: IO Exception setting user");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid username or password");
        }

    }
}

