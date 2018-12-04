package vn.mtouch.courtesycar.data.db.local.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import rx.Completable;
import rx.functions.Action0;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.data.db.model.roomdb.BorrowContractDBO;
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
        return mCarDao.getAllCars();
    }

    public LiveData<List<BorrowContractDBO>> getContracts() {
        // can transfer here
        return mBorrowContractDao.getAllContract();
    }

    public Completable asyncSaveCar(final CarModel carModel) {
        return Completable.fromAction(new Action0() {
            @Override
            public void call() {
                CarDBO carDBO = new CarDBO();
                carDBO.carName = carModel.getCarName();
                carDBO.carCode = carModel.getCarCode();
                mCarDao.insertCar(carDBO);
            }
        });
    }

    public Completable asyncSaveContract(final BorrowContractModel contract) {
        return Completable.fromAction(new Action0() {
            @Override
            public void call() {
                BorrowContractDBO contractDBO = new BorrowContractDBO();
                if(contract.getTimeOut() != null) {
                    contract.setState(BorrowContractModel.STATE_RETURNED);
                } else {
                    contract.setState(BorrowContractModel.STATE_NEW_BORROW);
                }
                contractDBO.carName = contract.getCarName();
                contractDBO.carCode = contract.getCarCode();
                contractDBO.timeIn = contract.getTimeIn();
                contractDBO.timeOut = contract.getTimeOut();
                contractDBO.fullName = contract.getFullName();
                contractDBO.dateOfBirth = contract.getDateOfBirth();
                contractDBO.phoneNumber = contract.getPhoneNumber();
                contractDBO.state = contract.getState();
                contractDBO.licenseType = contract.getLicenseType();
                mBorrowContractDao.insertBorrowContract(contractDBO);
            }
        });
    }

    public Completable asyncUpdateContract(final BorrowContractModel contract) {
        return Completable.fromAction(() -> {
            BorrowContractDBO contractDBO = mBorrowContractDao.findContactById(contract.getId());
            if(contract.getTimeOut() != null) {
                contract.setState(BorrowContractModel.STATE_RETURNED);
            } else {
                contract.setState(BorrowContractModel.STATE_NEW_BORROW);
            }
            contractDBO.carName = contract.getCarName();
            contractDBO.carCode = contract.getCarCode();
            contractDBO.timeIn = contract.getTimeIn();
            contractDBO.timeOut = contract.getTimeOut();
            contractDBO.fullName = contract.getFullName();
            contractDBO.dateOfBirth = contract.getDateOfBirth();
            contractDBO.phoneNumber = contract.getPhoneNumber();
            contractDBO.state = contract.getState();
            contractDBO.licenseType = contract.getLicenseType();
            mBorrowContractDao.updateBorrowContract(contractDBO);
        });
    }

    public Completable asyncUpdateCar(final CarModel carModel) {
        return Completable.fromAction(() -> {
            CarDBO carDBO = mCarDao.findCarById(carModel.getId());
            carDBO.carName = carModel.getCarName();
            carDBO.carCode = carModel.getCarCode();
            mCarDao.updateCar(carDBO);
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

    public Completable asyncDeleteContract(long id) {
        return Completable.fromAction(new Action0() {
            @Override
            public void call() {
                BorrowContractDBO carDBO = mBorrowContractDao.findContactById(id);
                mBorrowContractDao.deleteBorrowContract(carDBO);
            }
        });
    }
}
