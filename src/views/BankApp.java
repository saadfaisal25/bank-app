package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bank;

public class BankApp extends Application {
    private static Bank model;
    private static String directory;

    public static Bank getModel() {return model;}
    public static String getDirectory() {return directory;}

    public BankApp() {
        directory = "D:/Libraries/Documents/bank-app/src/db/";
        model = new Bank("NewBank", directory);
    }

    public void start(Stage primaryStage) {
        AuthenticateView view = AuthenticateView.getInstance();

        primaryStage.setTitle("Bank Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
