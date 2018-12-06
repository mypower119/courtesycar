package vn.mtouch.courtesycar.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import rx.Completable;
import rx.schedulers.Schedulers;
import vn.mtouch.courtesycar.data.db.local.room.Mappers;
import vn.mtouch.courtesycar.data.db.local.room.RoomDataManager;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.data.db.model.roomdb.BorrowContractDBO;
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

    public List<CarModel> findCarByQrCode(String qrCode) {
        List<CarDBO> cars = mRoomDataManager.findCarByQrCode(qrCode);
        List<CarModel> ret = new ArrayList<>();
        for(CarDBO itemBDO: cars) {
            ret.add(Mappers.FROM_DBO_TO_CAR.map(itemBDO));
        }
        return ret;
    }

    public List<BorrowContractModel> findContactBorrowingByQrcode(String qrcode) {
        List<BorrowContractDBO> contracts = mRoomDataManager.findContactBorrowingByQrcode(qrcode);
        List<BorrowContractModel> ret = new ArrayList<>();
        for(BorrowContractDBO itemBDO: contracts) {
            ret.add(Mappers.FROM_DBO_TO_CONTRACT.map(itemBDO));
        }
        return ret;
    }

    public LiveData<List<BorrowContractModel>> getContracts() {
        LiveData<List<BorrowContractDBO>> contracts = mRoomDataManager.getContracts();
        return Transformations.map(contracts, dboList -> {
            List<BorrowContractModel> ret = new ArrayList<>();
            for(BorrowContractDBO itemBDO: dboList) {
                ret.add(Mappers.FROM_DBO_TO_CONTRACT.map(itemBDO));
            }
            return ret;
        });
    }

    public Completable initDummyData() {
        return Completable.fromAction(() -> {
            List<CarModel> cars = CarModel.getInitDataFirst();
            for(CarModel item : cars) {
                saveCar(item);
            }
        });
    }

    public void saveCar(CarModel carModel) {
        mRoomDataManager.asyncSaveCar(carModel).subscribeOn(Schedulers.io()).subscribe(new SimpleCompletableSubscriber() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
            }
        });
    }

    public void addContract(BorrowContractModel contract) {
        mRoomDataManager.asyncSaveContract(contract).subscribeOn(Schedulers.io()).subscribe(new SimpleCompletableSubscriber() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
            }
        });
    }

    public void updateContract(BorrowContractModel contract) {
        mRoomDataManager.asyncUpdateContract(contract).subscribeOn(Schedulers.io()).subscribe(new SimpleCompletableSubscriber() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
            }
        });
    }

    public void updateCar(CarModel carModel) {
        mRoomDataManager.asyncUpdateCar(carModel).subscribeOn(Schedulers.io()).subscribe(new SimpleCompletableSubscriber() {
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

    public void deleteContract(long id) {
        mRoomDataManager.asyncDeleteContract(id)
                .subscribeOn(Schedulers.io())
                .subscribe(new SimpleCompletableSubscriber() {
                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
