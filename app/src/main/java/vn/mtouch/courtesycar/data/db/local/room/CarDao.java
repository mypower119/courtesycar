package vn.mtouch.courtesycar.data.db.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("Select * from car")
    List<CarDBO> getCars();

    @Query("Select * from car where qr_code = :qrcode")
    List<CarDBO> findCarByQrCode(String qrcode);

    @Insert
    long insertCar(CarDBO dbo);

    @Update
    void updateCar(CarDBO dbo);

    @Delete
    void deleteCar(CarDBO dbo);

    @Delete
    void deleteAll(List<CarDBO> dbos);

    @Query("SELECT * from car where id = :id")
    CarDBO findCarById(long id);

    @Query("SELECT * from car where code like :carCode and name like :carName")
    CarDBO findCarByCarCodeAndName(String carCode, String carName);
}
