package vn.mtouch.courtesycar.data.db.local.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/29/18
 */

@Dao
public interface CarDao {
    @Query("Select * from car")
    LiveData<List<CarDBO>> getAllCars();

    @Query("Select * from car where qr_code = :qrcode")
    List<CarDBO> findCarByQrCode(String qrcode);

    @Insert
    long insertCar(CarDBO dbo);

    @Update
    void updateCar(CarDBO dbo);

    @Delete
    void deleteCar(CarDBO dbo);

    @Query("SELECT * from car where id = :id")
    CarDBO findCarById(long id);

    @Query("SELECT * from car where code like :carCode and name like :carName")
    CarDBO findCarByCarCodeAndName(String carCode, String carName);
}
