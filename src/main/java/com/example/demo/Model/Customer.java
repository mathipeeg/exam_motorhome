package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private int telephone;
    private String email;
    private String address;
    private String cardInfo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expDate;
    private int cvs;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, int telephone, String email, String address, String cardInfo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.cardInfo = cardInfo;
    }

    public Customer(int id, String firstName, String lastName, int telephone, String email, String address, String cardInfo, Date expDate, int cvs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.cardInfo = cardInfo;
        this.expDate = expDate;
        this.cvs = cvs;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getCvs() {
        return cvs;
    }

    public void setCvs(int cvs) {
        this.cvs = cvs;
    }
}
