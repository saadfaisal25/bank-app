package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Bank;

public class BankApp extends Application {
    public static Stage window;
    private static Bank model;
    private String directory;

    public BankApp() {
        directory = "D:/Libraries/Documents/bank-app/src/db/";
        model = new Bank("NewBank", directory);
    }

    public void start(Stage primaryStage) {
        window = primaryStage;
        //Pane viewPane = new Pane();
        AuthenticateView view = AuthenticateView.getInstance();
        //LoginView view = LoginView.getInstance();
        //viewPane.getChildren().add(view);

        primaryStage.setTitle("Bank Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
