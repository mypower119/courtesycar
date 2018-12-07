package vn.mtouch.courtesycar.data.db.model;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/1/18
 */

public class BorrowContractModel {

    public static final int ALL_STATE = 0;
    public static final int STATE_NEW_BORROW = ALL_STATE + 1;
    public static final int STATE_RETURNED = ALL_STATE + 2;


    private long id;
    private String carName;
    private String carCode;
    private String qrCode;
    private Long timeIn;
    private Long timeOut;
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private Integer state = STATE_NEW_BORROW;
    private String licenseType;
    private String pathFrontLicense;
    private String pathBackLicense;

    public BorrowContractModel() {

    }

    public BorrowContractModel(long id, String carName, String carCode,
                               Long timeIn, Long timeOut, String fullName,
                               String dateOfBirth, String phoneNumber, Integer state,
                               String licenseType, String qrCode, String pathFront, String pathBack) {
        this.id = id;
        this.carName = carName;
        this.carCode = carCode;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.licenseType = licenseType;
        this.qrCode = qrCode;
        this.pathFrontLicense = pathFront;
        this.pathBackLicense = pathBack;
    }

    public String getPathFrontLicense() {
        return pathFrontLicense;
    }

    public void setPathFrontLicense(String pathFrontLicense) {
        this.pathFrontLicense = pathFrontLicense;
    }

    public String getPathBackLicense() {
        return pathBackLicense;
    }

    public void setPathBackLicense(String pathBackLicense) {
        this.pathBackLicense = pathBackLicense;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licensetype) {
        this.licenseType = licensetype;
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

    public Long getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Long timeIn) {
        this.timeIn = timeIn;
    }

    public Long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
