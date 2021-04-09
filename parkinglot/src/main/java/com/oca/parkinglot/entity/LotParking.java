package com.oca.parkinglot.entity;

import java.time.LocalDateTime;

public class LotParking {

    private String platNomor;
    private String parkingLot;
    private String tglMasuk;
    private boolean isParking;

    public LotParking() {}

    public LotParking(String platNomor, String parkingLot, String tglMasuk, boolean isParking) {
        this.platNomor = platNomor;
        this.parkingLot = parkingLot;
        this.tglMasuk = tglMasuk;
        this.isParking = isParking;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public boolean isParking() {
        return isParking;
    }

    public void setParking(boolean parking) {
        isParking = parking;
    }

    @Override
    public String toString() {
        return "LotParking{" +
                "platNomor='" + platNomor + '\'' +
                ", parkingLot='" + parkingLot + '\'' +
                ", tglMasuk='" + tglMasuk + '\'' +
                ", isParking=" + isParking +
                '}';
    }
}
