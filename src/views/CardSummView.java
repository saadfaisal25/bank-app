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
import model.CreditCard;
import model.Operation;
import model.RewardsCard;
import model.TravelCard;

public class CardSummView extends Pane {
    private CreditCard card;
    private Label balanceLabel;
    private Label airlineLabel;
    private Label pointsLabel;
    private ListView<Operation> operationView;
    private TextField withdrawF;
    private TextField depositF;
    private boolean isTravelCard;
    private boolean isRewardsCard;

    public CardSummView(CreditCard card) {
        this.card = card;

        // set boolean values depending on the card name
        if (card.getCardName().equals("Travel Card")) {
            isTravelCard = true;
            isRewardsCard = false;
        } else if (card.getCardName().equals("Rewards Card")) {
            isTravelCard = false;
            isRewardsCard = true;
        } else {
            isTravelCard = false;
            isRewardsCard = false;
        }

        setPrefSize(800, 600);

        // create back button
        Button backB = new Button("Back");
        backB.setPrefSize(100, 40);
        backB.relocate(0,0);

        // create card name label
        Label nameLabel = new Label(card.getCardName());
        nameLabel.setFont(new Font("Trebuchet MS", 20));
        nameLabel.relocate(20, 60);

        // create card number label
        Label numberLabel = new Label("Card Number: " + card.getCardNumber());
        numberLabel.setFont(new Font("Trebuchet MS", 20));
        numberLabel.relocate(20, 100);

        // create card balance label
        balanceLabel = new Label("Balance: $" + card.getBalance());
        balanceLabel.setFont(new Font("Trebuchet MS", 20));
        balanceLabel.relocate(20, 140);

        // initialize airline and points labels, and set them if the given card matches
        airlineLabel = new Label();
        pointsLabel = new Label();
        if (isTravelCard) {
            airlineLabel = new Label("Airline Dollars: " + ((TravelCard) card).getAirlineDollars());
            airlineLabel.setFont(new Font("Trebuchet MS", 20));
            airlineLabel.relocate(400, 60);
        } else if (isRewardsCard) {
            pointsLabel = new Label("Reward Points: " + ((RewardsCard) card).getPoints());
            pointsLabel.setFont(new Font("Trebuchet MS", 20));
            pointsLabel.relocate(400, 60);
        }

        // set operationView to show the past credit card operations
        ObservableList<Operation> operations= FXCollections.observableArrayList(card.getOperations());
        operationView = new ListView<>(operations);
        operationView.setPrefSize(760, 300);
        operationView.relocate(20, 200);

        // create withdraw button and field
        Button withdrawB = new Button("Withdraw:");
        withdrawB.setPrefSize(100, 30);
        withdrawF = new TextField();
        withdrawF.setText("0.00");
        withdrawF.setPrefHeight(30);

        // create deposit button and field
        Button depositB = new Button("Deposit:");
        depositB.setPrefSize(100, 30);
        depositF = new TextField();
        depositF.setText("0.00");
        depositF.setPrefHeight(30);

        // create close card button
        Button closeCardB = new Button("Close Card");
        closeCardB.setPrefSize(150, 40);

        HBox botBox = new HBox(depositB, depositF, withdrawB, withdrawF, closeCardB);
        botBox.setSpacing(20);
        botBox.setPrefWidth(800);
        botBox.relocate(0, 520);
        botBox.setAlignment(Pos.CENTER);

        getChildren().addAll(backB, nameLabel, numberLabel, balanceLabel, airlineLabel, pointsLabel, operationView, botBox);

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

        closeCardB.setOnAction(new EventHandler<ActionEvent>() {
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

    // update the balance label and operationView, and other labels if the card matches
    public void update() {
        OverviewView.getInstance().update();

        if (isTravelCard) {
            airlineLabel.setText("Airline Dollars: " + ((TravelCard) card).getAirlineDollars());
        } else if (isRewardsCard) {
            pointsLabel.setText("Reward Points: " + ((RewardsCard) card).getPoints());
        }

        balanceLabel.setText("Balance: $" + card.getBalance());
        ObservableList<Operation> operations = FXCollections.observableArrayList(card.getOperations());
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

    // create fail dialog and call update()
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
        double amount = Double.parseDouble(depositF.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to deposit $" + amount + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle("Confirm Action");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            if (card.deposit(amount)) {
                createDialogSuccess();
                depositF.setText("0.00");
            } else {
                depositF.setText("0.00");
                createDialogFail();
            }
        }
    }

    // confirm withdraw before withdrawing from account
    public void handleWithdraw() {
        double amount = Double.parseDouble(withdrawF.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to withdraw $" + amount + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle("Confirm Action");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            if (card.withdraw(amount)) {
                createDialogSuccess();
                withdrawF.setText("0.00");
            } else {
                withdrawF.setText("0.00");
                createDialogFail();
            }
        }
    }

    // confirm before closing the credit card
    public void handleClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close this credit card?", ButtonType.YES, ButtonType.CANCEL);
        alert.setTitle("Confirm Credit Card Closure");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            if (BankApp.getModel().closeCard(card)) {
                OverviewView.getInstance().update();
                handleBack();
            }
        }
    }

}
