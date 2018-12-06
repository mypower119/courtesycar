package vn.mtouch.courtesycar.presentation.features.add_car;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.db.Repository;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;
import vn.mtouch.courtesycar.presentation.base_view.BaseDialog;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/1/18
 */

public class EditCarDialog extends BaseDialog {
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_qr_code)
    EditText edtQrCode;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.btn_save)
    Button btnSave;
    CarModel carModel;

    public static EditCarDialog newInstance() {
        return new EditCarDialog();
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = new CarModel(carModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_car, container, false);
        ButterKnife.bind(this, view);
        setupUi();
        setupEvent();
        return view;
    }

    private void setupUi(){
        if(carModel != null) {
            edtCode.setText(carModel.getCarCode() + "");
            edtName.setText(carModel.getCarName() + "");
            edtQrCode.setText(carModel.getQrCode() + "");
        }
    }

    private void setupEvent(){
        btnSave.setOnClickListener(v -> {
            if(carModel == null) {
                carModel = new CarModel();
                carModel.setCarCode(edtCode.getText().toString());
                carModel.setCarName(edtName.getText().toString());
                carModel.setQrCode(edtQrCode.getText().toString());
                Repository.getInstance().saveCar(carModel);
            } else {
                carModel.setCarCode(edtCode.getText().toString());
                carModel.setCarName(edtName.getText().toString());
                carModel.setQrCode(edtQrCode.getText().toString());
                Repository.getInstance().updateCar(carModel);
            }
            dismiss();
        });
    }
}
