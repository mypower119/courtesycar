package vn.mtouch.courtesycar.presentation.features.list_car;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.presentation.base_view.BaseFragment;
import vn.mtouch.courtesycar.presentation.features.add_car.EditCarDialog;

public class ListCarFragment extends BaseFragment {

    @BindView(R.id.rv_cars)
    RecyclerView rvCars;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    private Unbinder mUnbinder;
    private ListCarViewModel mViewModel;
    private CarsAdapter mAdapter;

    public static ListCarFragment newInstance() {
        return new ListCarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_car_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mViewModel = ViewModelProviders.of(this).get(ListCarViewModel.class);
        mViewModel.init();
        initUi();
        setupEvent();
        return view;
    }

    private void initUi() {
        mAdapter = new CarsAdapter(getActivity(), new ArrayList<>(), mViewModel);
        rvCars.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCars.setAdapter(mAdapter);
        mViewModel.getCars().observe(this, carDBOS -> {
            mAdapter.setItems(carDBOS);
        });
    }

    private void setupEvent( ) {
        fabAdd.setOnClickListener(v -> {
            EditCarDialog addCarDialog = EditCarDialog.newInstance();
            addCarDialog.show(getFragmentManager(), null);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
