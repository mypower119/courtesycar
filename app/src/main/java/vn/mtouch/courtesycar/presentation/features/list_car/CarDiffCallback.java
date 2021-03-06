package vn.mtouch.courtesycar.presentation.features.list_car;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import vn.mtouch.courtesycar.data.db.model.CarModel;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/2/18
 */

public class CarDiffCallback extends DiffUtil.Callback {
    public static final String KEY_NAME = "name";
    public static final String KEY_CODE = "code";
    public static final String KEY_QR_CODE = "qr_code";
    public static final String KEY_STATUS = "status";
    List<CarModel> mOldCars;
    List<CarModel> mNewCars;

    public CarDiffCallback(List<CarModel> oldCars, List<CarModel> newCars) {
        this.mOldCars = oldCars;
        this.mNewCars = newCars;
    }

    @Override
    public int getOldListSize() {
        return mOldCars == null ? 0 : mOldCars.size();
    }

    @Override
    public int getNewListSize() {
        return mNewCars == null ? 0 : mNewCars.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldCars.get(oldItemPosition).getId() == mNewCars.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldCars.get(oldItemPosition).compareTo(mNewCars.get(newItemPosition)) == 0 ? true : false;
    }

    //areItemsTheSame == true && areContentsTheSame == false
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        CarModel newCar = mNewCars.get(newItemPosition);
        CarModel oldCar = mOldCars.get(oldItemPosition);

        Bundle bundleDiff = new Bundle();
        if(!newCar.getCarName().equals(oldCar.getCarName())) {
            bundleDiff.putString(KEY_NAME, newCar.getCarName());
        }
        if(!newCar.getCarCode().equals(oldCar.getCarCode())) {
            bundleDiff.putString(KEY_CODE, newCar.getCarCode());
        }
        if(!newCar.getQrCode().equals(oldCar.getQrCode())) {
            bundleDiff.putString(KEY_QR_CODE, newCar.getQrCode());
        }
        if(newCar.getStatus() != oldCar.getStatus()) {
            bundleDiff.putInt(KEY_QR_CODE, newCar.getStatus());
        }
        if(bundleDiff.size() == 0) {
            return null;
        }
        return bundleDiff;
    }
}
