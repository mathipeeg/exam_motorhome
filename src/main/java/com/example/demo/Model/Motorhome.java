package com.example.demo.Model;

import java.awt.image.BufferedImage;

public class Motorhome {
    private int id;
    private int brandId;
    private int sizeId;
    private BufferedImage img;
    private String brandName;
    private String sizeName;
    private int sizePrice;

    public Motorhome(int id, int brandId, int sizeId) {
        this.id = id;
        this.brandId = brandId;
        this.sizeId = sizeId;
    }

    public Motorhome(int id, int brandId, int sizeId, String brandName, String sizeName) {
        this.id = id;
        this.brandId = brandId;
        this.sizeId = sizeId;
        this.brandName = brandName;
        this.sizeName = sizeName;
    }

    public Motorhome(int id, int brandId, int sizeId, String brandName, String sizeName, int sizePrice)
    {
        this.id = id;
        this.brandId = brandId;
        this.sizeId = sizeId;
        this.brandName = brandName;
        this.sizeName = sizeName;
        this.sizePrice = sizePrice;
    }

    public Motorhome(int id, int brandId, int sizeId, BufferedImage img, String brandName, String sizeName, int sizePrice) {
        this.id = id;
        this.brandId = brandId;
        this.sizeId = sizeId;
        this.img = img;
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

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
}
