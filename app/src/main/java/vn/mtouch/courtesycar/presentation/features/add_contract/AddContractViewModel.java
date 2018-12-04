package vn.mtouch.courtesycar.presentation.features.add_contract;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import vn.mtouch.courtesycar.data.db.Repository;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;

public class AddContractViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    Repository mRepository;
    LiveData<List<CarModel>> cars;

    public AddContractViewModel() {
        this.mRepository = Repository.getInstance();
        cars = mRepository.getCars();
    }

    public void addContract(BorrowContractModel item) {
        mRepository.addContract(item);
    }

    public void updateContract(BorrowContractModel item) {
        mRepository.updateContract(item);
    }

    public void deleteContract(long id) {
        mRepository.deleteContract(id);
    }

    public LiveData<List<CarModel>> getCars() {
        return cars;
    }
}
