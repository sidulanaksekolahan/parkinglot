package com.oca.parkinglot.entity;

public class Spot {

    private String lot;
    private String availability;

    public Spot() {}

    public Spot(String lot, String availability) {
        this.lot = lot;
        this.availability = availability;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "lot='" + lot + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}
