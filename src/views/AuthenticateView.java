package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AuthenticateView extends Pane {
    // create a single instance of this class
    public static AuthenticateView instance = new AuthenticateView();

    private AuthenticateView() {
        setPrefSize(800, 600);

        // Create authentication page title label
        Label titleLabel = new Label("Banking Application");
        titleLabel.setFont(new Font("Trebuchet MS", 30));

        // HBox to store and center the label horizontally
        HBox topBox = new HBox(titleLabel);
        topBox.setPrefWidth(800);
        topBox.setLayoutY(50);
        topBox.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        Button loginB = new Button("Log In");
        loginB.setPrefSize(175, 50);

        Button signupB = new Button("Sign Up");
        signupB.setPrefSize(175, 50);

        // store and center the buttons in an HBox
        HBox midBox = new HBox(loginB, signupB);
        midBox.setPrefWidth(800);
        midBox.setLayoutY(200);
        midBox.setSpacing(50);
        midBox.setAlignment(Pos.CENTER);

        // Create quit button
        Button quitB = new Button("Quit");
        quitB.setPrefSize(175, 50);

        // store and center quit button in an HBox
        HBox botBox = new HBox(quitB);
        botBox.setPrefWidth(800);
        botBox.setAlignment(Pos.CENTER);
        botBox.setLayoutY(400);

        // add all HBoxes
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

    // set scene to LoginView
    public void handleLogin() {
        this.getScene().setRoot(LoginView.getInstance());
    }

    // set scene to SignupView
    public void handleSignup() {
        this.getScene().setRoot(SignupView.getInstance());
    }

    // close the stage and exit application
    public void handleQuit() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }
}
