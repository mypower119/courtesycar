package vn.mtouch.courtesycar.presentation.features.list_contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.presentation.features.ui_utils.TransferActivity;
import vn.mtouch.courtesycar.utils.ConvertUtil;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> {

    private List<BorrowContractModel> mItems;
    private ContractViewModel mViewModel;
    private Context mContext;
    DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);

    public ContractAdapter(Context context, List<BorrowContractModel> items, ContractViewModel viewModel) {
        mItems = items;
        mViewModel = viewModel;
        mContext = context;
    }

    public void setItems(List<BorrowContractModel> mItems) {
        this.mItems.clear();
        this.mItems.addAll(mItems);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contract, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.onBind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_fullName)
        TextView tvFullName;
        @BindView(R.id.tv_car_code)
        TextView tvCarCode;
        @BindView(R.id.tv_car_name)
        TextView tvCarName;
        @BindView(R.id.tv_phone_number)
        TextView tvPhoneName;
        @BindView(R.id.tv_time_in)
        TextView tvTimeIn;
        @BindView(R.id.tv_time_out)
        TextView tvTimeOut;
        @BindView(R.id.img_remove)
        ImageView imgRemove;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(position != -1) {
                    mContext.startActivity(TransferActivity.getCallingIntent(mContext, mItems.get(position)));
                }
            });
        }

        public void onBind(BorrowContractModel item) {
            tvFullName.setText(ConvertUtil.convertObjectToString(item.getFullName()));
            tvCarCode.setText(ConvertUtil.convertObjectToString(item.getCarCode()));
            tvCarName.setText(ConvertUtil.convertObjectToString(item.getCarName()));
            tvPhoneName.setText(ConvertUtil.convertObjectToString(item.getPhoneNumber()));
            if(item.getTimeIn() != null) {
                tvTimeIn.setText(df.format(new Date(item.getTimeIn())));
            }
            if(item.getTimeOut() != null) {
                tvTimeOut.setText(df.format(new Date(item.getTimeOut())));
            }
            imgRemove.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(position != -1) {
                    mViewModel.deleteContracts(mItems.get(position).getId());
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvFullName.getText() + "'";
        }
    }
}
