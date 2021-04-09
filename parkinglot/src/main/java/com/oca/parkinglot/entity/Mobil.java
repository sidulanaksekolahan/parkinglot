package com.oca.parkinglot.entity;

public class Mobil {

    private String platNomor;
    private String warna;
    private String tipe;

    public Mobil() {}

    public Mobil(String platNomor, String warna, String tipe) {
        this.platNomor = platNomor;
        this.warna = warna;
        this.tipe = tipe;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    @Override
    public String toString() {
        return "Mobil{" +
                "platNomor='" + platNomor + '\'' +
                ", warna='" + warna + '\'' +
                ", tipe='" + tipe + '\'' +
                '}';
    }
}
