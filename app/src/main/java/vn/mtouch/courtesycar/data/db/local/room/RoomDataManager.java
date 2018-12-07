package vn.mtouch.courtesycar.data.db.local.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.List;

import rx.Completable;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import vn.mtouch.courtesycar.CourtesyCarApp;
import vn.mtouch.courtesycar.data.db.Repository;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.data.db.model.roomdb.BorrowContractDBO;
import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;
import vn.mtouch.courtesycar.data.prefs.ConstantsPrefs;
import vn.mtouch.courtesycar.data.prefs.SharePreferenceManager;
import vn.mtouch.courtesycar.utils.ConvertUtil;
import vn.mtouch.courtesycar.utils.SimpleCompletableSubscriber;

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

    private static final Migration mUserDBMigration_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //database.execSQL("CREATE TABLE friend_requests(id INTEGER PRIMARY KEY AUTOINCREMENT, total_friend_requests INTEGER)");
            database.execSQL("ALTER TABLE borrow_contract ADD COLUMN path_front_license TEXT");
            database.execSQL("ALTER TABLE borrow_contract ADD COLUMN path_back_license TEXT");
        }
    };

    private static final RoomDatabase.Callback mUserDBCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            db.execSQL("CREATE INDEX index_conversation_friend_id ON chat_rooms (friend_id)");
//            db.execSQL("CREATE INDEX index_message_id ON messages (message_id)");
//            db.execSQL("CREATE INDEX index_message_receiver_id ON messages (receiver_id)");
//            db.execSQL("CREATE INDEX index_message_sender_id ON messages (sender_id)");
//            db.execSQL("CREATE INDEX index_user_profile_id ON chat_profiles (user_id)");
//            db.execSQL("CREATE INDEX index_block_user_profile_id ON block_profiles (profile_id)");
//            db.execSQL("CREATE TRIGGER update_room_on_insert_message AFTER INSERT ON messages WHEN new.unread = 1 BEGIN UPDATE chat_rooms SET unread_count = unread_count + 1 WHERE room_id = new.room_id; END");
//            db.execSQL("CREATE TRIGGER update_unread_count_on_update_message AFTER UPDATE OF unread ON messages WHEN new.unread = 0 BEGIN UPDATE chat_rooms SET unread_count = unread_count - 1 WHERE room_id = new.room_id; END");
//            db.execSQL("CREATE TRIGGER update_message_count_on_insert_message AFTER INSERT ON messages BEGIN UPDATE chat_rooms SET message_count = message_count + 1 WHERE room_id = new.room_id; END");
        }
    };

    public synchronized void initForUser(Context context) {
        if (mCourtesyCarDatabase == null) {
            mCourtesyCarDatabase = Room.databaseBuilder(context, CourtesyCarDatabase.class, "courtesy_car_db")
                    .addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    Repository.getInstance().initDummyData()
                            .subscribeOn(Schedulers.io())
                            .subscribe(new SimpleCompletableSubscriber() {
                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                }
                            });
                }

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            })
                    .addMigrations(mUserDBMigration_1_2)
                    .addCallback(mUserDBCallback)
                    .build();

            mBorrowContractDao = mCourtesyCarDatabase.getBorrowContractDao();
            mCarDao = mCourtesyCarDatabase.getCarDao();
        }
    }

    public LiveData<List<CarDBO>> getCars() {
        // can transfer here
        return mCarDao.getAllCars();
    }

    public List<CarDBO> findCarByQrCode(String qrCode) {
        // can transfer here
        return mCarDao.findCarByQrCode(qrCode);
    }

    public List<BorrowContractDBO> findContactBorrowingByQrcode(String qrCode) {
        // can transfer here
        return mBorrowContractDao.findContactBorrowingByQrcode(qrCode, BorrowContractModel.STATE_NEW_BORROW);
    }

    public LiveData<List<BorrowContractDBO>> getContracts(String query) {
        // can transfer here
        int state = SharePreferenceManager.getInt(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_BY_STATUS, BorrowContractModel.ALL_STATE);
        int isReportByDate = SharePreferenceManager.getBool(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_BY_DATE_FLAG, false) ? 1 : 0;
        long dateTo = SharePreferenceManager.getLong(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_TO_DATE_VALUE, ConvertUtil.setMaximumCalendar(Calendar.getInstance()).getTimeInMillis());
        long dateFrom = SharePreferenceManager.getLong(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_FROM_DATE_VALUE, ConvertUtil.setMinimumCalendar(Calendar.getInstance()).getTimeInMillis());
        return mBorrowContractDao.getAllContract(query, state, isReportByDate, dateFrom, dateTo);
    }

    public Completable asyncSaveCar(final CarModel carModel) {
        return Completable.fromAction(() -> {
            CarDBO carDBO = new CarDBO();
            carDBO.carName = carModel.getCarName();
            carDBO.carCode = carModel.getCarCode();
            carDBO.qrCode = carModel.getQrCode();
            mCarDao.insertCar(carDBO);
        });
    }

    public Completable asyncSaveContract(final BorrowContractModel contract) {
        return Completable.fromAction(() -> {
            BorrowContractDBO contractDBO = new BorrowContractDBO();
            if(contract.getTimeOut() != null) {
                contract.setState(BorrowContractModel.STATE_RETURNED);
            } else {
                contract.setState(BorrowContractModel.STATE_NEW_BORROW);
            }
            contractDBO.carName = contract.getCarName();
            contractDBO.carCode = contract.getCarCode();
            contractDBO.qrCode = contract.getQrCode();
            contractDBO.timeIn = contract.getTimeIn();
            contractDBO.timeOut = contract.getTimeOut();
            contractDBO.fullName = contract.getFullName();
            contractDBO.dateOfBirth = contract.getDateOfBirth();
            contractDBO.phoneNumber = contract.getPhoneNumber();
            contractDBO.state = contract.getState();
            contractDBO.licenseType = contract.getLicenseType();
            contractDBO.pathBackLicense = contract.getPathBackLicense();
            contractDBO.pathFrontLicense = contract.getPathFrontLicense();
            mBorrowContractDao.insertBorrowContract(contractDBO);
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
            contractDBO.qrCode = contract.getQrCode();
            contractDBO.timeIn = contract.getTimeIn();
            contractDBO.timeOut = contract.getTimeOut();
            contractDBO.fullName = contract.getFullName();
            contractDBO.dateOfBirth = contract.getDateOfBirth();
            contractDBO.phoneNumber = contract.getPhoneNumber();
            contractDBO.state = contract.getState();
            contractDBO.licenseType = contract.getLicenseType();
            contractDBO.pathBackLicense = contract.getPathBackLicense();
            contractDBO.pathFrontLicense = contract.getPathFrontLicense();
            mBorrowContractDao.updateBorrowContract(contractDBO);
        });
    }

    public Completable asyncUpdateCar(final CarModel carModel) {
        return Completable.fromAction(() -> {
            CarDBO carDBO = mCarDao.findCarById(carModel.getId());
            carDBO.carName = carModel.getCarName();
            carDBO.qrCode = carModel.getQrCode();
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
