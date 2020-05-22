package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Order {
    private int id;
    private int motorhomeId;
    private int customerId;
    private String pickup;
    private String dropoff;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private int nights;
    private double deposit;


    public Order() {
    }

    public Order(int id, int motorhomeId, int customerId, String pickup, String dropoff, Date startDate, Date endDate, int nights, double deposit) {
        this.id = id;
        this.motorhomeId = motorhomeId;
        this.customerId = customerId;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nights = nights;
        this.deposit = deposit;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMotorhomeId() {
        return motorhomeId;
    }

    public void setMotorhomeId(int motorhomeId) {
        this.motorhomeId = motorhomeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }


}
