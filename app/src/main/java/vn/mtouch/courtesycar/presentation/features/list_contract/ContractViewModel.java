package vn.mtouch.courtesycar.presentation.features.list_contract;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import vn.mtouch.courtesycar.data.db.Repository;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;

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

    public void init() {
        contracts = mRepository.getContracts();
    }

    public LiveData<List<BorrowContractModel>> getContracts() {
        return contracts;
    }

    public void deleteContracts(long id) {
        mRepository.deleteContract(id);
    }

    public void deleteListContracts(long[] ids) {

    }
}
