package vn.mtouch.courtesycar.data.db.model.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/29/18
 */

@Entity(tableName = "car")
public class CarDBO {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Long id;

    @ColumnInfo(name = "name")
    public String carName;

    @ColumnInfo(name = "code")
    public String carCode;

    @ColumnInfo(name = "qr_code")
    public String qrCode;

    @ColumnInfo(name = "status")
    public Integer status;
}
