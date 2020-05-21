package com.example.demo.Model;

public class OrderReceipt
{
    private int id;
    private int receiptId;
    private int orderReceipt;

    public OrderReceipt(int id, int receiptId, int orderReceipt)
    {
        this.id = id;
        this.receiptId = receiptId;
        this.orderReceipt = orderReceipt;
    }

    public OrderReceipt()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getReceiptId()
    {
        return receiptId;
    }

    public void setReceiptId(int receiptId)
    {
        this.receiptId = receiptId;
    }

    public int getOrderReceipt()
    {
        return orderReceipt;
    }

    public void setOrderReceipt(int orderReceipt)
    {
        this.orderReceipt = orderReceipt;
    }
}

