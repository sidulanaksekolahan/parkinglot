package com.oca.parkinglot.controller;

import com.oca.parkinglot.entity.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class MobilController {

    List<Mobil> mobils;
    List<LotParking> lotParkings;
    List<Spot> spots;

    @PostConstruct
    public void init() {
        mobils = new ArrayList<>();
        lotParkings = new ArrayList<>();
        spots = new ArrayList<>();

        // create dummy data for Spot parking
        spots.add(new Spot("A1", "available"));
        spots.add(new Spot("A2", "available"));
        spots.add(new Spot("A3", "available"));
        spots.add(new Spot("A4", "available"));
        spots.add(new Spot("A5", "available"));
    }

    @GetMapping("mobils")
    public List<Mobil> getMobils() {
        return mobils;
    }

    // registration vehicle
    @PostMapping("/mobilsRegister")
    public LotParking regKendaraan(@RequestBody Mobil mobil) {

        // get current date
        LocalDateTime localDateTimeIn = LocalDateTime.now();

        // format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = localDateTimeIn.format(formatter);

        // find spot availability
        Optional<Spot> spot = spots.stream()
                .filter(s -> s.getAvailability() == "available")
                .findFirst();

        LotParking lotParking = null;
        if (spot.isPresent()) {

            // add new car
            mobils.add(mobil);

            lotParking = new LotParking(
                    mobil.getPlatNomor(),
                    spot.get().getLot(),
                    formattedDate,
                    true
            );

            // add new lot
            lotParkings.add(lotParking);

            // find index and change availability
            int index = spots.indexOf(spot.get());
            spots.set(index, new Spot(spot.get().getLot(), "not available"));
        } else {
            throw new DataNotFoundException("No more slot!");
        }

        return lotParking;
    }

    @GetMapping("/lotParkings")
    public List<LotParking> getLotParkings() {
        return lotParkings;
    }

    @PostMapping("/mobilsOut")
    public Bayar kendaraanKeluar(@RequestBody Mobil platNomor) throws ParseException {

        // date-out
        LocalDateTime localDateTimeOut = LocalDateTime.now();

        // format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = localDateTimeOut.format(formatter);

        // find date-in based on platNomor
        Optional<LotParking> parking = lotParkings.stream()
                .filter(l -> {
                    return l.getPlatNomor().equals(platNomor.getPlatNomor()) &&
                            l.isParking() == true;
                })
                .findFirst();

        Bayar bayar = null;
        if (parking.isPresent()) {

            String tempTipe = null;

            // reset isParking
            parking.get().setParking(false);

            // find difference between date time
            String dateIn = parking.get().getTglMasuk();
            String dateOut = formattedDate;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            Date date1 = format.parse("2021-04-08 10:19");
            Date date1 = format.parse(dateIn);
            Date date2 = format.parse(dateOut);
            long inMilliseconds = date2.getTime() - date1.getTime();
            long inHours = (inMilliseconds / (1000 * 60 * 60)) % 24;
            System.out.println("difference:" + inHours);

            if (inHours > 0) {
                // find type of car
                Optional<String> tipe = mobils.stream()
                        .filter(m -> m.getPlatNomor().equals(platNomor.getPlatNomor()))
                        .map(m -> m.getTipe())
                        .findFirst();

                if (tipe.isPresent()) {
                    tempTipe = tipe.get();
                    System.out.println(tempTipe);

                    if (tempTipe.equals("SUV")) {
                        int tarifDasar = 25000;

                        double tempPercent;
                        tempPercent = 1 * tarifDasar + (inHours - 1) * (20/100.0 * tarifDasar);
                        System.out.println(tempPercent);

                        bayar = new Bayar(
                                platNomor.getPlatNomor(),
                                parking.get().getTglMasuk(),
                                formattedDate,
                                tempPercent
                        );
                    } else if (tempTipe.equals("MPV")){
                        int tarifDasar = 35000;

                        double tempPercent;
                        tempPercent = 1 * tarifDasar + (inHours - 1) * (20/100.0 * tarifDasar);
                        System.out.println(tempPercent);

                        bayar = new Bayar(
                                platNomor.getPlatNomor(),
                                parking.get().getTglMasuk(),
                                formattedDate,
                                tempPercent
                        );
                    }
                }
            } else {
                // find type of car
                Optional<String> tipe = mobils.stream()
                        .filter(m -> m.getPlatNomor().equals(platNomor.getPlatNomor()))
                        .map(m -> m.getTipe())
                        .findFirst();

                if (tipe.isPresent()) {
                    tempTipe = tipe.get();
                    System.out.println(tempTipe);

                    if (tempTipe.equals("SUV")) {
                        int tarifDasar = 25000;

                        bayar = new Bayar(
                                platNomor.getPlatNomor(),
                                parking.get().getTglMasuk(),
                                formattedDate,
                                tarifDasar
                        );
                    } else if (tempTipe.equals("MPV")){
                        int tarifDasar = 35000;

                        bayar = new Bayar(
                                platNomor.getPlatNomor(),
                                parking.get().getTglMasuk(),
                                formattedDate,
                                tarifDasar
                        );
                    }
                }
            }

            // get parkingLot
            String currentParkingLot = parking.get().getParkingLot();

            // reset parkingLot
            Optional<Spot> spot = spots.stream()
                    .filter(s -> s.getLot() == currentParkingLot)
                    .findFirst();

            if (spot.isPresent()) {
                // find index and change availability
                int index = spots.indexOf(spot.get());
                spots.set(index, new Spot(spot.get().getLot(), "available"));
            }

        } else {
            throw new DataNotFoundException("Data not found!");
        }

        return bayar;
    }

    @GetMapping("/spots")
    public List<Spot> getSpots() {
        return spots;
    }

    // number of car per type
    @GetMapping("/numberOfTypeCar")
    public CustomResponseJumlahKendaraan numberOfTypeCar(@RequestParam String type) {

        // counting the number of car based on type
        long count = mobils.stream()
                .filter(mobil -> mobil.getTipe().equals(type))
                .count();



        return new CustomResponseJumlahKendaraan(Integer.parseInt(String.valueOf(count)));
    }

    @GetMapping("/listPlat")
    public MyCustomResponsePlatNomor getListPlat(@RequestParam String color) {

        MyCustomResponsePlatNomor myCustomResponsePlatNomor =
                new MyCustomResponsePlatNomor();

        mobils.stream()
                .filter(mobil -> {
                    System.out.println(mobil.getWarna().equals(color));
                    return mobil.getWarna().equals(color);
                })
                .map(mobil -> mobil.getPlatNomor())
                .forEach(plat -> {
                    System.out.println(plat);
                    myCustomResponsePlatNomor.addPlatNomor(plat);
                });

        return myCustomResponsePlatNomor;
    }

}













