package vn.mtouch.courtesycar.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import vn.mtouch.courtesycar.data.db.local.room.Mapper;
import vn.mtouch.courtesycar.data.db.local.room.Mappers;
import vn.mtouch.courtesycar.data.db.local.room.RoomDataManager;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;
import vn.mtouch.courtesycar.utils.SimpleCompletableSubscriber;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 8/20/18
 */

public class Repository {
    private static Repository sInstance;

    private RoomDataManager mRoomDataManager;

    public Repository() {
        mRoomDataManager = RoomDataManager.getInstance();
    }

    public static Repository getInstance() {
        Repository instance = sInstance;
        if(instance == null) {
            synchronized (Repository.class) {
                instance = sInstance;
                if(instance == null) {
                    instance = sInstance = new Repository();
                }
            }
        }
        return instance;
    }

    public LiveData<List<CarModel>> getCars() {
        LiveData<List<CarDBO>> cars = mRoomDataManager.getCars();
        return Transformations.map(cars, dboList -> {
            List<CarModel> ret = new ArrayList<>();
            for(CarDBO itemBDO: dboList) {
                ret.add(Mappers.FROM_DBO_TO_CAR.map(itemBDO));
            }
            return ret;
        });
    }

    public void saveCar(CarDBO carDBO) {
        mRoomDataManager.asyncSaveCar(carDBO).subscribeOn(Schedulers.io()).subscribe(new SimpleCompletableSubscriber() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
            }
        });
    }

    public void deleteCar(long id) {
        mRoomDataManager.asyncDeleteCar(id)
                .subscribeOn(Schedulers.io())
                .subscribe(new SimpleCompletableSubscriber() {
            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
