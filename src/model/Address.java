package model;

import java.io.Serializable;

public class Address implements Serializable {
    private String country;
    private String city;
    private String province;
    private String street;
    private String postalCode;

    public Address(String country, String city, String province, String street, String postalCode) {
        this.country = country;
        this.city = city;
        this.province = province;
        this.street = street;
        this.postalCode = postalCode;
    }

    public String toString() {
        return street + "\n" + city + ", " + province + " " + postalCode + "\n" + country;
    }
}
