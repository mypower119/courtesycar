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

    public CarModel() {

    }

    public CarModel(CarModel carModel) {
        this.id = carModel.id;
        this.carName = carModel.getCarName();
        this.carCode = carModel.getCarCode();
    }

    public CarModel(String carName, String carCode) {
        this.carName = carName;
        this.carCode = carCode;
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
        return (carCode + "").equals(carModel.getCarCode()) && (carName + "").equals(carModel.getCarName()) ? 0 : 1;
    }

    public static ArrayList<CarModel> getInitDataFirst() {
        ArrayList<CarModel> arrayList = new ArrayList<>();
        arrayList.add(new CarModel("Honda Accord", "PXB747"));
        arrayList.add(new CarModel("Nissan Maxima", "YPS617"));
        arrayList.add(new CarModel("Audi A4", "1HO4HG"));
        arrayList.add(new CarModel("Honda Jazz", "1LB3NT"));
        arrayList.add(new CarModel("Mitsubishi Pajero", "1KW6IT"));
        arrayList.add(new CarModel("Ford Fiesta", "1NP4IH"));
        arrayList.add(new CarModel("Subaru Liberty", "1KC2YS"));
        return arrayList;
    }
}
