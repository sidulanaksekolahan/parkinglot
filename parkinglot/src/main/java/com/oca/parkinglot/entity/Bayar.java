package com.oca.parkinglot.entity;

import java.time.LocalDateTime;

public class Bayar {

    private String platNomor;
    private String tglMasuk;
    private String tglKeluar;
    private double jumlahBayar;

    public Bayar() {}

    public Bayar(String platNomor, String tglMasuk, String tglKeluar, double jumlahBayar) {
        this.platNomor = platNomor;
        this.tglMasuk = tglMasuk;
        this.tglKeluar = tglKeluar;
        this.jumlahBayar = jumlahBayar;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public String getTglKeluar() {
        return tglKeluar;
    }

    public void setTglKeluar(String tglKeluar) {
        this.tglKeluar = tglKeluar;
    }

    public double getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(double jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    @Override
    public String toString() {
        return "Bayar{" +
                "platNomor='" + platNomor + '\'' +
                ", tglMasuk=" + tglMasuk +
                ", tglKeluar=" + tglKeluar +
                ", jumlahBayar=" + jumlahBayar +
                '}';
    }
}
