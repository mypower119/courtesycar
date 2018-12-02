package vn.mtouch.courtesycar.data.db.model;

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

    public CarModel(long id, String carName, String carCode) {
        this.id = id;
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
}
