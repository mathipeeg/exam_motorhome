package com.example.demo.Model;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class Motorhome {
    private int id;
    private int brandId;
    private int sizeId;
    private String imgPath;
    private int amount;
    private String brandName;
    private String sizeName;
    private int sizePrice;

    public Motorhome(int id, int brandId, int sizeId, String imgPath, int amount) {
        this.id = id;
        this.brandId = brandId;
        this.sizeId = sizeId;
        this.imgPath = imgPath;
        this.amount = amount;
    }

    public Motorhome(int id, int brandId, int sizeId, String imgPath, int amount, String brandName, String sizeName) {
        this.id = id;
        this.brandId = brandId;
        this.sizeId = sizeId;
        this.imgPath = imgPath;
        this.amount = amount;
        this.brandName = brandName;
        this.sizeName = sizeName;
    }

    public Motorhome(int id, int brandId, int sizeId, String imgPath, int amount, String brandName, String sizeName, int sizePrice) {
        this.id = id;
        this.brandId = brandId;
        this.sizeId = sizeId;
        this.imgPath = imgPath;
        this.amount = amount;
        this.brandName = brandName;
        this.sizeName = sizeName;
        this.sizePrice = sizePrice;
    }

    public Motorhome(int id, int brandId, int sizeId, String imgPath, String brandName, String sizeName, int sizePrice) {
        this.id = id;
        this.brandId = brandId;
        this.sizeId = sizeId;
        this.imgPath = imgPath;
        this.brandName = brandName;
        this.sizeName = sizeName;
        this.sizePrice = sizePrice;
    }

    public Motorhome() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getSizePrice()
    {
        return sizePrice;
    }

    public void setSizePrice(int sizePrice)
    {
        this.sizePrice = sizePrice;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
