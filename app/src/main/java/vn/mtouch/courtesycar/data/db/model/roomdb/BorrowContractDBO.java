package vn.mtouch.courtesycar.data.db.model.roomdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

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

}
