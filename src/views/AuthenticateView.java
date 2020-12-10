package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class AuthenticateView extends Pane {
    public static AuthenticateView instance = new AuthenticateView();

    private AuthenticateView() {
        setPrefSize(800, 600);

        // Create login page title label
        Label titleLabel = new Label("Banking Application");
        titleLabel.setFont(new Font("Trebuchet MS", 30));

        HBox topBox = new HBox(titleLabel);
        topBox.setPrefWidth(800);
        topBox.setLayoutY(50);
        topBox.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        Button loginB = new Button("Log In");
        loginB.setPrefSize(175, 50);

        Button signupB = new Button("Sign Up");
        signupB.setPrefSize(175, 50);

        HBox midBox = new HBox(loginB, signupB);
        midBox.setPrefWidth(800);
        midBox.setLayoutY(200);
        midBox.setSpacing(50);
        midBox.setAlignment(Pos.CENTER);

        // Create quit button
        Button quitB = new Button("Quit");
        quitB.setPrefSize(175, 50);

        HBox botBox = new HBox(quitB);
        botBox.setPrefWidth(800);
        botBox.setAlignment(Pos.CENTER);
        botBox.setLayoutY(400);

        getChildren().addAll(topBox, midBox, botBox);

        loginB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleLogin();
            }
        });

        signupB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleSignup();
            }
        });

        quitB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleQuit();
            }
        });
    }

    public static AuthenticateView getInstance() {
        return instance;
    }

    public void handleLogin() {
        BankApp.window.getScene().setRoot(LoginView.getInstance());
    }

    public void handleSignup() {

    }

    public void handleQuit() {

    }
}
