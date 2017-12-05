package com.workshop.mongodb.lamaoffice.mongo.morphia;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
class Address {

    private String streetName;
    private int number;
    private String zipCode;
    private String place;

    public Address() {
    }

    public Address(String streetName, int number, String zipCode, String place) {
        this.streetName = streetName;
        this.number = number;
        this.zipCode = zipCode;
        this.place = place;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
