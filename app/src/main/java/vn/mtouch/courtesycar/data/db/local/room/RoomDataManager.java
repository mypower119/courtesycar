package vn.mtouch.courtesycar.data.db.local.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

import rx.Completable;
import rx.functions.Action0;
import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/29/18
 */

public class RoomDataManager {

    private CourtesyCarDatabase mCourtesyCarDatabase;

    private BorrowContractDao mBorrowContractDao;

    private CarDao mCarDao;

    private RoomDataManager() {

    }

    volatile static RoomDataManager sInstance = null;

    public static RoomDataManager getInstance() {
        RoomDataManager instance = sInstance;
        if(instance == null) {
            synchronized (RoomDataManager.class) {
                instance = sInstance;
                if(instance == null) {
                    instance = sInstance = new RoomDataManager();
                }
            }
        }
        return instance;
    }

    public synchronized void initForUser(Context context) {
        if (mCourtesyCarDatabase == null) {
            mCourtesyCarDatabase = Room.databaseBuilder(context, CourtesyCarDatabase.class, "courtesy_car_db")
//                    .addMigrations(mUserDBMigration_1_2)
//                    .addCallback(mUserDBCallback)
                    .build();

            mBorrowContractDao = mCourtesyCarDatabase.getBorrowContractDao();
            mCarDao = mCourtesyCarDatabase.getCarDao();
        }
    }

    public LiveData<List<CarDBO>> getCars() {
        // can transfer here
        return mCarDao.getAllContract();
    }

    public Completable asyncSaveCar(final CarDBO carDBO) {
        return Completable.fromAction(new Action0() {
            @Override
            public void call() {
                mCarDao.insertCar(carDBO);
            }
        });
    }

    public Completable asyncDeleteCar(long id) {
        return Completable.fromAction(new Action0() {
            @Override
            public void call() {
                CarDBO carDBO = mCarDao.findCarById(id);
                mCarDao.deleteCar(carDBO);
            }
        });
    }
}
