package vn.mtouch.courtesycar.data.db.model.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/29/18
 */

@Entity(tableName = "borrow_contract",
        indices = {@Index(name = "index_car_code", value = "car_code"),
                @Index(name = "index_phone_number", value = "phone_number"),
                @Index(name = "index_state", value = "state"),
                @Index(name = "index_time_out", value = "time_out"),
                @Index(name = "index_full_name", value = "full_name"),
})
public class BorrowContractDBO {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Long id;

    @ColumnInfo(name = "car_name")
    public String carName;

    @ColumnInfo(name = "car_code")
    public String carCode;

    @ColumnInfo(name = "qr_code")
    public String qrCode;

    @ColumnInfo(name = "time_in")
    public Long timeIn;

    @ColumnInfo(name = "time_out")
    public Long timeOut;

    @ColumnInfo(name = "full_name")
    public String fullName;

    @ColumnInfo(name = "dob")
    public String dateOfBirth;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "state")
    public Integer state;

    @ColumnInfo(name = "license_type")
    public String licenseType;

    @ColumnInfo(name = "path_front_license")
    public String pathFrontLicense;

    @ColumnInfo(name = "path_back_license")
    public String pathBackLicense;

    @ColumnInfo(name = "path_signature")
    public String pathSignature;

    @ColumnInfo(name = "address")
    public String address;

}
