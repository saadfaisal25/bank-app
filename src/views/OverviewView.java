package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.BankApp;
import model.Account;
import model.CreditCard;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OverviewView extends Pane {
    private static OverviewView instance = new OverviewView();
    private ListView<Account> accountsView;
    private ListView<CreditCard> cardsView;

    public OverviewView() {
        setPrefSize(800, 600);

        // create new menu and menu item to log out of application
        Menu accountMenu = new Menu("Account");
        MenuItem logoutItem = new MenuItem("Log Out");
        accountMenu.getItems().addAll(logoutItem);

        // create new menu bar and add accountMenu to the menuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(accountMenu);
        HBox topBox = new HBox(menuBar);

        // create bank account label
        Label accountsLabel = new Label("Bank Accounts: ");
        accountsLabel.setFont(new Font("Trebuchet MS", 20));
        accountsLabel.relocate(20, 35);

        // set accounts ListView to the users accounts, using account toString
        ObservableList<Account> accounts = FXCollections.observableArrayList(BankApp.getModel().getUser().getAccounts());
        accountsView = new ListView<>(accounts);
        accountsView.setPrefSize(600, 200);
        accountsView.relocate(20, 80);

        // create credit card label
        Label cardsLabel = new Label("Credit Cards: ");
        cardsLabel.setFont(new Font("Trebuchet MS", 20));
        cardsLabel.relocate(20, 300);

        // set cards ListVIew to the users credit cards,  using credit card toString
        ObservableList<CreditCard> cards = FXCollections.observableArrayList(BankApp.getModel().getUser().getCreditCards());
        cardsView = new ListView<>(cards);
        cardsView.setPrefSize(600, 200);
        cardsView.relocate(20, 350);

        // create open account button
        Button openAccB = new Button("Open Bank Account");
        openAccB.setPrefSize(140, 90);
        openAccB.relocate(640, 80);

        // create account summary button
        Button accSummB = new Button("Check Account \nSummary");
        accSummB.textAlignmentProperty().set(TextAlignment.CENTER);
        accSummB.setPrefSize(140, 90);
        accSummB.relocate(640, 190);

        // create open credit card button
        Button openCardB = new Button("Apply for a Credit Card");
        openCardB.setPrefSize(140,90);
        openCardB.relocate(640, 350);

        // create credit card summary button
        Button cardSummB = new Button("Check Credit Card \nSummary");
        cardSummB.textAlignmentProperty().set(TextAlignment.CENTER);
        cardSummB.setPrefSize(140,90);
        cardSummB.relocate(640, 460);

        logoutItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleLogout();
            }
        });

        openAccB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleOpenAcc();
            }
        });

        openCardB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleOpenCard();
            }
        });

        accSummB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleAccSumm();
            }
        });

        cardSummB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleCardSumm();
            }
        });

        getChildren().addAll(topBox, accountsLabel, accountsView, cardsLabel, cardsView, openAccB, accSummB,
                openCardB, cardSummB);
    }

    public static OverviewView getInstance() {
        return instance;
    }

    // unset the model's user and set scene to LoginView
    public void handleLogout() {
        BankApp.getModel().unsetUser();
        this.getScene().setRoot(LoginView.getInstance());
    }

    // set scene to OpenAccountView
    public void handleOpenAcc() {
        this.getScene().setRoot(OpenAccountView.getInstance());
    }

    // set scene to OpenCardView
    public void handleOpenCard() {
        this.getScene().setRoot(OpenCardView.getInstance());
    }

    // if an account is selected, set scene to its AccSummaryView
    public void handleAccSumm() {
        int index = accountsView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Account account = BankApp.getModel().getUser().getAccounts().get(index);
            this.getScene().setRoot(new AccSummaryView(account));
        }
    }

    // if a credit card is selected, go to its CardSummView
    public void handleCardSumm() {
        int index = cardsView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            CreditCard card = BankApp.getModel().getUser().getCreditCards().get(index);
            this.getScene().setRoot(new CardSummView(card));
        }
    }

    // save the user data in the database and update the ListViews
    public void update() {
        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(
                BankApp.getDirectory()+ BankApp.getModel().getUser().getUsername()+".txt"))) {
            BankApp.getModel().saveUserToData(objOut);
        } catch (IOException e) {
            System.out.println("Error: IO Exception saving user");
        }

        ObservableList<Account> accounts = FXCollections.observableArrayList(BankApp.getModel().getUser().getAccounts());
        accountsView.setItems(accounts);
        ObservableList<CreditCard> cards = FXCollections.observableArrayList(BankApp.getModel().getUser().getCreditCards());
        cardsView.setItems(cards);
    }
}
