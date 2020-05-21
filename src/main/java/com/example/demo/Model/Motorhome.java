package com.example.demo.Model;

public class Motorhome {
    private int id;
    private int brandId;
    private int sizeId;
    private String brandName;
    private String sizeName;

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
}
