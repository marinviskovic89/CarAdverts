package com.caradverts.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class Car {
    private long id;
    private String title;
    private String fuelType;
    private int price;
    private boolean newer;
    private int mileage;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date firstRegistration;
    public Car(){}
    public Car( long id, String title, String fuelType, int price, boolean newer, int mileage, Date firstRegistration) {
        this.id = id;
        this.title = title;
        this.fuelType = fuelType;
        this.price = price;
        this.newer = newer;
        this.mileage = mileage;
        this.firstRegistration = firstRegistration;
    }

    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getPrice() {
        return price;
    }

    public boolean isNewer() {
        return newer;
    }

    public int getMileage() {
        return mileage;
    }

    public Date getFirstRegistration() {
        return firstRegistration;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNewer(boolean newer) {
        this.newer = newer;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setFirstRegistration(Date firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    @Override
    public String toString() {
        return " [id=" + id + ", title=" + title + ", fuelType=" + fuelType + ", price=" + price + ", newer=" + newer + ", mileage="+ mileage +", firstRegistration="+ firstRegistration + "]";
    }

}
