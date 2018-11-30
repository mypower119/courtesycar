package vn.mtouch.courtesycar.data.db.local.room;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

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
                    instance = new RoomDataManager();
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
}
