package vn.mtouch.courtesycar.presentation.features.list_car;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;
import vn.mtouch.courtesycar.presentation.base_view.BaseFragment;

public class ListCarFragment extends BaseFragment {

    private ListCarViewModel mViewModel;

    public static ListCarFragment newInstance() {
        return new ListCarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_car_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListCarViewModel.class);
        mViewModel.getCars().observe(this, carDBOS -> {

        });
    }

}
