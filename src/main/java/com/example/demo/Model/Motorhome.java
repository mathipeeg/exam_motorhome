package com.example.demo.Model;

public class Motorhome {
    private int id;
    private int brandId;
    private int sizeId;

    public Motorhome(int id, int brandId, int sizeId) {
        this.id = id;
        this.brandId = brandId;
        this.sizeId = sizeId;
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
}
