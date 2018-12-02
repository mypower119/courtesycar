package vn.mtouch.courtesycar.data.db.local.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import vn.mtouch.courtesycar.data.db.model.roomdb.BorrowContractDBO;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/29/18
 */

@Dao
public interface BorrowContractDao {
    @Query("Select * from borrow_contract")
    LiveData<List<BorrowContractDBO>> getAllContract();

    @Insert
    long insertBorrowContract(BorrowContractDBO dbo);

    @Update
    void updateBorrowContract(BorrowContractDBO dbo);

    @Delete
    void deleteBorrowContract(BorrowContractDBO dbo);

    @Delete
    void deleteAllContract(BorrowContractDBO... dbos);
}
