package views;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.Address;

public class AddressPane extends Pane {
    TextField countryF;
    TextField cityF;
    TextField provinceF;
    TextField streetF;
    TextField postF;

    public Address getAddress() {
        String country = countryF.getText();
        String city = cityF.getText();
        String province = provinceF.getText();
        String street = streetF.getText();
        String post = postF.getText();

        return new Address(country, city, province, street, post);
    }

    public AddressPane() {
        Label countryL = new Label("Country:");
        Label cityL = new Label("City:");
        Label provinceL = new Label("Province:");
        Label streetL = new Label("Street:");
        Label postL = new Label("Postal Code:");

        countryL.setFont(new Font("Trebuchet MS", 20));
        cityL.setFont(new Font("Trebuchet MS", 20));
        provinceL.setFont(new Font("Trebuchet MS", 20));
        streetL.setFont(new Font("Trebuchet MS", 20));
        postL.setFont(new Font("Trebuchet MS", 20));

        countryL.relocate(0, 0);
        cityL.relocate(0, 30);
        provinceL.relocate(0, 60);
        streetL.relocate(0, 90);
        postL.relocate(0, 120);

        countryF = new TextField();
        cityF = new TextField();
        provinceF = new TextField();
        streetF = new TextField();
        postF = new TextField();

        countryF.relocate(200,0);
        countryF.setPrefHeight(25);

        cityF.relocate(200,30);
        cityF.setPrefHeight(25);

        provinceF.relocate(200,60);
        provinceF.setPrefHeight(25);

        streetF.relocate(200,90);
        streetF.setPrefHeight(25);

        postF.relocate(200,120);
        postF.setPrefHeight(25);

        getChildren().addAll(countryL, countryF, cityL, cityF, provinceL, provinceF, streetL, streetF, postL, postF);
    }
}
