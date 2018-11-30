package vn.mtouch.courtesycar.data.db.local.room;

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
    @Query("Select * from borrow_contract")
    List<CarDBO> getAllContract();

    @Insert
    long insertCar(CarDBO dbo);

    @Update
    void updateCar(CarDBO dbo);

    @Delete
    void deleteCar(CarDBO dbo);

    @Delete
    void deleteAllCar(CarDao... dbos);
}
