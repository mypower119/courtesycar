package vn.mtouch.courtesycar.presentation.features.list_car;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.presentation.custom_view.CustomFontTextView;
import vn.mtouch.courtesycar.utils.LogManager;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/1/18
 */

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder>{
    private Context mContext;
    private List<CarModel> mItems;
    private static final Object LOCK = new Object();
    ListCarViewModel mViewModel;

    public CarsAdapter(Context context, List<CarModel> items, ListCarViewModel viewModel) {
        mContext = context;
        mItems = items;
        mViewModel = viewModel;
    }

    public void setItems(List<CarModel> newItems) {
        synchronized (LOCK){
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new CarDiffCallback(mItems, newItems));
            diffResult.dispatchUpdatesTo(this);
            this.mItems.clear();
            this.mItems.addAll(newItems);
            //notifyDataSetChanged();
            LogManager.e("Difff", "init data");
        }
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_car,viewGroup, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position, @NonNull List<Object> payloads) {
        if(payloads.isEmpty()) {
            LogManager.e("Difff", "onBindViewHolder payload isempty" + position);
            super.onBindViewHolder(holder, position, payloads);
        } else {
            LogManager.e("Difff", "onBindViewHolder change attribute " + position);
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if(key.equals(CarDiffCallback.KEY_CODE)){
                    holder.tvCarCode.setText(mItems.get(position).getCarCode());
                }
                if(key.equals(CarDiffCallback.KEY_NAME)){
                    holder.tvCarName.setText(mItems.get(position).getCarName());
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder carViewHolder, int i) {
        carViewHolder.onBind(mItems.get(i));
        LogManager.e("Difff", "onBindViewHolder " + i);
    }

    @Override
    public int getItemCount() {
        synchronized (LOCK) {
            return mItems == null ? 0 : mItems.size();
        }
    }

    public interface OnCarAdapterListener {
        void onItemClick(CarModel carModel);
    }

    OnCarAdapterListener mListener;

    public void setListener(OnCarAdapterListener listener) {
        this.mListener = listener;
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_car_name)
        CustomFontTextView tvCarName;
        @BindView(R.id.tv_car_code)
        CustomFontTextView tvCarCode;
        @BindView(R.id.img_delete)
        ImageView imgDelete;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(mListener != null) {
                    mListener.onItemClick(mItems.get(getAdapterPosition()));
                }
            });
        }

        public void onBind(CarModel item) {
            tvCarCode.setText(item.getCarCode() + "");
            tvCarName.setText(item.getCarName());
            imgDelete.setOnClickListener(v -> {
                int positionChange = getAdapterPosition();
                if(positionChange != -1) {
                    mViewModel.asyncDeleteCar(mItems.get(positionChange).getId());
                }
            });
        }
    }
}
