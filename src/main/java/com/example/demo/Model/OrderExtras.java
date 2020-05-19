package com.example.demo.Model;

public class OrderExtras {

    private int id;
    private int extraId;
    private int orderId;

    public OrderExtras(int id, int extraId, int orderId) {
        this.id = id;
        this.extraId = extraId;
        this.orderId = orderId;
    }

    public OrderExtras() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExtraId() {
        return extraId;
    }

    public void setExtraId(int extraId) {
        this.extraId = extraId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
