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
    @Query("Select * " +
            "from borrow_contract " +
            "where " +
            "(phone_number like :query or full_name like :query or :query = '' or :query = null) " +
            "and (state = :stateQuery or :stateQuery = 0 ) " +
            "and (:isReportDateFromDateTo = 0 or (:isReportDateFromDateTo = 1 and time_in >= :timeIn and time_in <= :timeOut) )" +
            "")
    LiveData<List<BorrowContractDBO>> getAllContract(String query, int stateQuery, int isReportDateFromDateTo, long timeIn, long timeOut);

    @Insert
    long insertBorrowContract(BorrowContractDBO dbo);

    @Update
    void updateBorrowContract(BorrowContractDBO dbo);

    @Delete
    void deleteBorrowContract(BorrowContractDBO dbo);

    @Delete
    void deleteAllContract(BorrowContractDBO... dbos);

    @Query("Select * from borrow_contract where id = :id")
    BorrowContractDBO findContactById(long id);

    @Query("Select * from borrow_contract where qr_code = :qrCode and state = :state")
    List<BorrowContractDBO> findContactBorrowingByQrcode(String qrCode, int state);
}
