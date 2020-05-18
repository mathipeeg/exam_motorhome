package com.example.demo.Model;

public class Order {
    private int id;
    private int customerId;
    private int extraId;
    private String pickup;
    private String dropoff;
    private String startDate;
    private String endDate;
    private int nights;
    private int deposit;

    public Order() {
    }

    public Order(int id, int customerId, int extraId, String pickup, String dropoff, String startDate, String endDate, int nights, int deposit) {
        this.id = id;
        this.customerId = customerId;
        this.extraId = extraId;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getExtraId() {
        return extraId;
    }

    public void setExtraId(int extraId) {
        this.extraId = extraId;
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

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}
