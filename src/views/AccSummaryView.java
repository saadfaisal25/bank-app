package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import main.BankApp;
import model.Account;
import model.Operation;

public class AccSummaryView extends Pane {
    private Account account;
    private Label balanceLabel;
    private ListView<Operation> operationView;
    private TextField withdrawF;
    private TextField depositF;

    public AccSummaryView(Account account) {
        this.account = account;

        setPrefSize(800, 600);

        // create back button
        Button backB = new Button("Back");
        backB.setPrefSize(100, 40);
        backB.relocate(0,0);

        // create account name label
        Label nameLabel = new Label(account.getAccountName());
        nameLabel.setFont(new Font("Trebuchet MS", 20));
        nameLabel.relocate(20, 60);

        // create account number label
        Label numberLabel = new Label("Account Number: " + account.getAccNumber());
        numberLabel.setFont(new Font("Trebuchet MS", 20));
        numberLabel.relocate(20, 100);

        // create balance label
        balanceLabel = new Label("Balance: $" + account.getBalance());
        balanceLabel.setFont(new Font("Trebuchet MS", 20));
        balanceLabel.relocate(20, 140);

        // set operationView to show all account operations using Operation toString
        ObservableList<Operation> operations= FXCollections.observableArrayList(account.getOperations());
        operationView = new ListView<>(operations);
        operationView.setPrefSize(760, 300);
        operationView.relocate(20, 200);

        // create withdraw button and text field
        Button withdrawB = new Button("Withdraw:");
        withdrawB.setPrefSize(100, 30);
        withdrawF = new TextField();
        withdrawF.setText("0.00");
        withdrawF.setPrefHeight(30);

        // create deposit button and text field
        Button depositB = new Button("Deposit:");
        depositB.setPrefSize(100, 30);
        depositF = new TextField();
        depositF.setText("0.00");
        depositF.setPrefHeight(30);

        // create close account button
        Button closeAccB = new Button("Close Account");
        closeAccB.setPrefSize(150, 40);

        HBox botBox = new HBox(depositB, depositF, withdrawB, withdrawF, closeAccB);
        botBox.setSpacing(20);
        botBox.setPrefWidth(800);
        botBox.relocate(0, 520);
        botBox.setAlignment(Pos.CENTER);

        getChildren().addAll(backB, nameLabel, numberLabel, balanceLabel, operationView, botBox);

        backB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleBack();
            }
        });

        depositB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleDeposit();
            }
        });

        withdrawB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleWithdraw();
            }
        });

        closeAccB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleClose();
            }
        });
    }

    // set scene to go back to OverviewView
    public void handleBack() {
        this.getScene().setRoot(OverviewView.getInstance());
    }

    // update the balance label and operationView
    public void update() {
        OverviewView.getInstance().update();

        balanceLabel.setText("Balance: $" + account.getBalance());
        ObservableList<Operation> operations = FXCollections.observableArrayList(account.getOperations());
        operationView.setItems(operations);
    }

    // create success dialog and call update()
    public void createDialogSuccess() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Transaction Successful");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        dialog.setContentText("The transaction was completed successfully");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

        update();
    }

    // create fail dialog
    public void createDialogFail() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Transaction Failed");

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        dialog.setContentText("The transaction failed. Make sure the amount entered is valid.");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    // confirm deposit before depositing into account
    public void handleDeposit() {
        // create an alert dialog
        double amount = Double.parseDouble(depositF.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to deposit $" + amount + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle("Confirm Action");
        alert.showAndWait();

        // if the deposit is confirmed, deposit into account
        if (alert.getResult() == ButtonType.YES) {
            if (account.deposit(amount)) {
                createDialogSuccess();
                depositF.setText("0.00");
            } else {
                depositF.setText("0.00");
                createDialogFail();
            }
        }
    }

    // confirm withdraw before doing it
    public void handleWithdraw() {
        // create alert to confirm
        double amount = Double.parseDouble(withdrawF.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to withdraw $" + amount + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle("Confirm Action");
        alert.showAndWait();

        // if confirmed, withdraw the amount from account
        if (alert.getResult() == ButtonType.YES) {
            if (account.withdraw(amount)) {
                createDialogSuccess();
                withdrawF.setText("0.00");
            } else {
                withdrawF.setText("0.00");
                createDialogFail();
            }
        }
    }

    // confirm before closing the account
    public void handleClose() {
        // create new alert dialog to confirm
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close this account?", ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle("Confirm Account Closure");
        alert.showAndWait();

        // if the user says yes, close the account
        if (alert.getResult() == ButtonType.YES) {
            if (BankApp.getModel().closeAccount(account)) {
                OverviewView.getInstance().update();
                handleBack();
            }
        }
    }

}
