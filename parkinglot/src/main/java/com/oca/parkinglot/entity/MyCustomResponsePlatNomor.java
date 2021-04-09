package com.oca.parkinglot.entity;

import java.util.ArrayList;
import java.util.List;

public class MyCustomResponsePlatNomor {

    private List<String> platNomor;

    public MyCustomResponsePlatNomor() {}

    public MyCustomResponsePlatNomor(List<String> platNomor) {
        this.platNomor = platNomor;
    }

    public List<String> getPlatNomor() {
        return platNomor;
    }

    public void addPlatNomor(String platNomor) {
        if (this.platNomor == null) {
            this.platNomor = new ArrayList<>();
        }

        this.platNomor.add(platNomor);
    }

    @Override
    public String toString() {
        return "MyCustomResponsePlatNomor{" +
                "platNomor=" + platNomor +
                '}';
    }
}
