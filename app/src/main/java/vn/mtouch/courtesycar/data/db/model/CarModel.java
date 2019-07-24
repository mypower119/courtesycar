package vn.mtouch.courtesycar.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import rx.Completable;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/1/18
 */

public class CarModel implements Comparable {
    public static final int STATUS_NOT_BORROW = 0;
    public static final int STATUS_BORROWING = STATUS_NOT_BORROW + 1;

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("carName")
    @Expose
    private String carName;
    @SerializedName("carCode")
    @Expose
    private String carCode;
    @SerializedName("qrCode")
    @Expose
    private String qrCode;
    @SerializedName("status")
    @Expose
    private int status = STATUS_NOT_BORROW;

    public CarModel() {

    }

    public CarModel(CarModel carModel) {
        this.id = carModel.id;
        this.carName = carModel.getCarName();
        this.carCode = carModel.getCarCode();
        this.qrCode = carModel.getQrCode();
        this.status = carModel.getStatus();
    }

    public CarModel(String carName, String carCode, String qrCode, int status) {
        this.carName = carName;
        this.carCode = carCode;
        this.qrCode = qrCode;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        arrayList.add(new CarModel("Honda Accord", "PXB747", "1", STATUS_NOT_BORROW));
        arrayList.add(new CarModel("Nissan Maxima", "YPS617", "2", STATUS_NOT_BORROW));
        arrayList.add(new CarModel("Audi A4", "1HO4HG", "3", STATUS_NOT_BORROW));
        arrayList.add(new CarModel("Honda Jazz", "1LB3NT", "4", STATUS_NOT_BORROW));
        arrayList.add(new CarModel("Mitsubishi Pajero", "1KW6IT", "5", STATUS_NOT_BORROW));
        arrayList.add(new CarModel("Ford Fiesta", "1NP4IH", "6", STATUS_NOT_BORROW));
        arrayList.add(new CarModel("Subaru Liberty", "1KC2YS", "7", STATUS_NOT_BORROW));
        return arrayList;
    }
}
