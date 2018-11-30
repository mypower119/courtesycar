package vn.mtouch.courtesycar.presentation.features.list_car;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import vn.mtouch.courtesycar.data.db.local.room.RoomDataManager;
import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;

public class ListCarViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<CarDBO>> cars;
    public LiveData<List<CarDBO>> getCars() {
        if (cars == null) {
            cars = new MutableLiveData<List<CarDBO>>();
            loadUsers();
        }
        return cars;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
         RoomDataManager.getInstance();
    }
}
