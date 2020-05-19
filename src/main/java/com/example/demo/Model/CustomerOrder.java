package com.example.demo.Model;

public class CustomerOrder {
    private String firstName;
    private String lastName;
    private int telephone;
    private String email;
    private String address;
    private int cardInfo;
    private int motorhomeId;
    private String pickup;
    private String dropoff;
    private String startDate;
    private String endDate;
    private String existingEmail;

    public CustomerOrder() {
    }

    public CustomerOrder(int motorhomeId, String pickup, String dropoff, String startDate, String endDate, String existingEmail) {
        this.motorhomeId = motorhomeId;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.startDate = startDate;
        this.endDate = endDate;
        this.existingEmail = existingEmail;
    }

    public CustomerOrder(String firstName, String lastName, int telephone, String email, String address, int cardInfo, int motorhomeId, String pickup, String dropoff, String startDate, String endDate, String existingEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.cardInfo = cardInfo;
        this.motorhomeId = motorhomeId;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.startDate = startDate;
        this.endDate = endDate;
        this.existingEmail = existingEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(int cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDropoff() {
        return dropoff;
    }

    public void setDropoff(String dropoff) {
        this.dropoff = dropoff;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMotorhomeId() {
        return motorhomeId;
    }

    public void setMotorhomeId(int motorhomeId) {
        this.motorhomeId = motorhomeId;
    }

    public String getExistingEmail() {
        return existingEmail;
    }

    public void setExistingEmail(String existingEmail) {
        this.existingEmail = existingEmail;
    }
}
