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

public class LoginView extends Pane {
    private static LoginView instance = new LoginView();
    public static String test = "sasdads";

    private LoginView() {
        setPrefSize(800, 600);

        Button backB = new Button("Back");
        backB.setPrefSize(100, 40);
        backB.relocate(0,0);

        // Create login page title label
        Label titleLabel = new Label("Banking Application");
        titleLabel.setFont(new Font("Trebuchet MS", 30));

        HBox topBox = new HBox(titleLabel);
        topBox.setPrefWidth(800);
        topBox.setLayoutY(50);
        topBox.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        Label userLabel = new Label("Username:");
        userLabel.setFont(new Font("Trebuchet MS", 20));

        TextField userField = new TextField();
        userField.setPrefSize(175, 50);

        HBox midBox1 = new HBox(userLabel, userField);
        midBox1.setPrefWidth(800);
        midBox1.setLayoutY(250);
        midBox1.setSpacing(50);
        midBox1.setAlignment(Pos.CENTER);

        // Create log in and sign up buttons
        Label passLabel = new Label("Password:");
        passLabel.setFont(new Font("Trebuchet MS", 20));

        TextField passField = new TextField();
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
    }

    public static LoginView getInstance() {
        return instance;
    }

    public void handleBack() {
        BankApp.window.getScene().setRoot(AuthenticateView.getInstance());
    }
}

