package vn.mtouch.courtesycar.presentation.features.list_car;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/1/18
 */

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder>{
    private Context mContext;
    public CarsAdapter(Context context, List<CarDBO> items) {

    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder carViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CarViewHolder extends RecyclerView.ViewHolder{

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
