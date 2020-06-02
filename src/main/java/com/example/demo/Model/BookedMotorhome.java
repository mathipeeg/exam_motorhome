package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BookedMotorhome {
    private int id;
    private int motorhomeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public BookedMotorhome() {
    }

    public BookedMotorhome(int id, int motorhomeId, Date startDate, Date endDate) {
        this.id = id;
        this.motorhomeId = motorhomeId;
        this.startDate = startDate;
        this.endDate = endDate;
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
}
