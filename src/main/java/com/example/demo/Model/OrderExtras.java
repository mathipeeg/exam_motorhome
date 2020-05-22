package com.example.demo.Model;

public class OrderExtras {

    private int id;
    private int extraId;
    private int orderId;
    private String description;
    private int price;

    public OrderExtras(int id, int extraId, int orderId) {
        this.id = id;
        this.extraId = extraId;
        this.orderId = orderId;
    }

    public OrderExtras() {
    }

    public OrderExtras(int extraId, String description, int price)
    {
        this.extraId = extraId;
        this.description = description;
        this.price = price;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
}
