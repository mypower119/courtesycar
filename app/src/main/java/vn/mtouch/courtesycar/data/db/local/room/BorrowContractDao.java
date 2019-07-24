package vn.mtouch.courtesycar.data.db.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import rx.Observable;
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


    @Query("Select * " +
            "from borrow_contract ")
    List<BorrowContractDBO> getAllContracts();

    @Insert
    long insertBorrowContract(BorrowContractDBO dbo);

    @Update
    void updateBorrowContract(BorrowContractDBO dbo);

    @Delete
    void deleteBorrowContract(BorrowContractDBO dbo);

    @Delete
    void deleteAllContract(List<BorrowContractDBO> dbos);

    @Query("Select * from borrow_contract where id = :id")
    BorrowContractDBO findContactById(long id);

    @Query("Select * from borrow_contract where qr_code = :qrCode and state = :state")
    List<BorrowContractDBO> findContactBorrowingByQrcode(String qrCode, int state);
}
