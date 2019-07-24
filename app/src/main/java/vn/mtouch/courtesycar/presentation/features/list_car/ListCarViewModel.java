package vn.mtouch.courtesycar.presentation.features.list_car;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import vn.mtouch.courtesycar.data.db.Repository;
import vn.mtouch.courtesycar.data.db.model.CarModel;

public class ListCarViewModel extends ViewModel {
    private Repository mRepository;

    private LiveData<List<CarModel>> cars;

    public ListCarViewModel() {
        mRepository = Repository.getInstance();
    }

    public void init() {
        if (cars == null) {
//            mRepository.initDummyData().subscribeOn(Schedulers.io()).subscribe(new SimpleCompletableSubscriber(){
//                @Override
//                public void onError(Throwable e) {
//                    super.onError(e);
//                }
//            });
            cars = mRepository.getCars();
        }
    }

    public LiveData<List<CarModel>> getCars() {
        return cars;
    }

    public void asyncDeleteCar(long id) {
        mRepository.deleteCar(id);
    }
}
