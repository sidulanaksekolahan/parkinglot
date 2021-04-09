package com.oca.parkinglot.entity;

public class CustomResponseJumlahKendaraan {

    private int jumlahKendaraan;

    public CustomResponseJumlahKendaraan() {}

    public CustomResponseJumlahKendaraan(int jumlahKendaraan) {
        this.jumlahKendaraan = jumlahKendaraan;
    }

    public int getJumlahKendaraan() {
        return jumlahKendaraan;
    }

    public void setJumlahKendaraan(int jumlahKendaraan) {
        this.jumlahKendaraan = jumlahKendaraan;
    }

    @Override
    public String toString() {
        return "CustomResponseJumlahKendaraan{" +
                "jumlahKendaraan=" + jumlahKendaraan +
                '}';
    }
}
