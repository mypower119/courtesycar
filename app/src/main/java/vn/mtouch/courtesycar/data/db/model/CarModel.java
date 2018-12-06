package vn.mtouch.courtesycar.data.db.model;

import java.util.ArrayList;

import rx.Completable;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/1/18
 */

public class CarModel implements Comparable {
    private long id;
    private String carName;
    private String carCode;
    private String qrCode;

    public CarModel() {

    }

    public CarModel(CarModel carModel) {
        this.id = carModel.id;
        this.carName = carModel.getCarName();
        this.carCode = carModel.getCarCode();
        this.qrCode = carModel.getQrCode();
    }

    public CarModel(String carName, String carCode, String qrCode) {
        this.carName = carName;
        this.carCode = carCode;
        this.qrCode = qrCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    @Override
    public int compareTo(Object o) {
        CarModel carModel = (CarModel) o;
        return (carCode + "").equals(carModel.getCarCode()) && (carName + "").equals(carModel.getCarName()) && (qrCode + "").equals(carModel.getQrCode()) ? 0 : 1;
    }

    public static ArrayList<CarModel> getInitDataFirst() {
        ArrayList<CarModel> arrayList = new ArrayList<>();
        arrayList.add(new CarModel("Honda Accord", "PXB747", "1"));
        arrayList.add(new CarModel("Nissan Maxima", "YPS617", "2"));
        arrayList.add(new CarModel("Audi A4", "1HO4HG", "3"));
        arrayList.add(new CarModel("Honda Jazz", "1LB3NT", "4"));
        arrayList.add(new CarModel("Mitsubishi Pajero", "1KW6IT", "5"));
        arrayList.add(new CarModel("Ford Fiesta", "1NP4IH", "6"));
        arrayList.add(new CarModel("Subaru Liberty", "1KC2YS", "7"));
        return arrayList;
    }
}
