package vn.mtouch.courtesycar.presentation.features.list_contract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import vn.mtouch.courtesycar.data.db.Repository;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/2/18
 */

public class ContractViewModel extends ViewModel {
    private Repository mRepository;

    private LiveData<List<BorrowContractModel>> contracts;

    public ContractViewModel() {
        mRepository = Repository.getInstance();
    }

    public void init(String query) {
        contracts = mRepository.getContracts("%" + query + "%");
    }

    public LiveData<List<BorrowContractModel>> getContracts() {
        return contracts;
    }

    public void deleteContracts(long id) {
        mRepository.deleteContract(id);
    }

    public void deleteListContracts(long[] ids) {

    }

    public List<BorrowContractModel> findContactBorrowingByQrcode(String qrCode) {
        return mRepository.findContactBorrowingByQrcode(qrCode);
    }

    public List<CarModel> findCarByQrCode(String qrCode) {
        return mRepository.findCarByQrCode(qrCode);
    }
}
