package vn.mtouch.courtesycar.data.db.local.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import vn.mtouch.courtesycar.data.db.model.roomdb.BorrowContractDBO;
import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/29/18
 */

@Database(entities = {BorrowContractDBO.class, CarDBO.class}, version = 5)
public abstract class CourtesyCarDatabase extends RoomDatabase {

    public abstract BorrowContractDao getBorrowContractDao();

    public abstract CarDao getCarDao();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }


}
