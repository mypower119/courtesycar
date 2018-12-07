package vn.mtouch.courtesycar.presentation.features.filter_contracts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.mtouch.courtesycar.CourtesyCarApp;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.prefs.ConstantsPrefs;
import vn.mtouch.courtesycar.data.prefs.SharePreferenceManager;
import vn.mtouch.courtesycar.presentation.base_view.BaseDialog;
import vn.mtouch.courtesycar.presentation.custom_view.CustomFontTextView;
import vn.mtouch.courtesycar.utils.ConvertUtil;
import vn.mtouch.courtesycar.utils.DialogUtils;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/28/18
 */

public class FilterContractDialog extends BaseDialog {
    @BindView(R.id.btn_ok)
    Button btnOk;

    @BindView(R.id.view_from_date_to_date)
    View viewFromDateToDate;
    @BindView(R.id.cb_filter_by_date)
    CheckBox cbFilterByDate;
    @BindView(R.id.tv_from_date)
    CustomFontTextView tvFromDate;
    @BindView(R.id.tv_to_date)
    CustomFontTextView tvToDate;

    @BindView(R.id.spn_status)
    Spinner spnStatus;

    String[] mStatusList;

    public static FilterContractDialog newInstance() {
        return new FilterContractDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_filter, container, false);
        ButterKnife.bind(this, view);
        mStatusList = getActivity().getResources().getStringArray(R.array.status_arr);
        setupUI();
        setUpEvent();
        return view;
    }
    DateFormat dateFormat = DateFormat.getDateInstance();
    boolean isFilterByDate;
    long toTime, fromTime;
    int filterByStatusPostion;
    private void setupUI() {
        isFilterByDate = SharePreferenceManager.getBool(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_BY_DATE_FLAG, false);
        cbFilterByDate.setChecked(isFilterByDate);
        if (isFilterByDate) {
            viewFromDateToDate.setVisibility(View.VISIBLE);
        } else {
            viewFromDateToDate.setVisibility(View.GONE);
        }

        toTime = SharePreferenceManager.getLong(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_TO_DATE_VALUE, ConvertUtil.setMaximumCalendar(Calendar.getInstance()).getTimeInMillis());
        fromTime =  SharePreferenceManager.getLong(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_FROM_DATE_VALUE, ConvertUtil.setMaximumCalendar(Calendar.getInstance()).getTimeInMillis());
        tvToDate.setText(dateFormat.format(new Date(toTime)));
        tvFromDate.setText(dateFormat.format(new Date(fromTime)));
        spnStatus.setAdapter(getListStatus());
        filterByStatusPostion = SharePreferenceManager.getInt(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_BY_STATUS, BorrowContractModel.ALL_STATE);
        spnStatus.setSelection(filterByStatusPostion);
    }

    public interface OnFilterDialogListener {
        void onClickDone();
    }

    OnFilterDialogListener listener;

    public void setListener(OnFilterDialogListener listener) {
        this.listener = listener;
    }

    private void setUpEvent() {
        btnOk.setOnClickListener(v -> {
            SharePreferenceManager.putLong(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_FROM_DATE_VALUE, fromTime);
            SharePreferenceManager.putLong(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_TO_DATE_VALUE, toTime);
            SharePreferenceManager.putBool(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_BY_DATE_FLAG, isFilterByDate);
            SharePreferenceManager.putInt(CourtesyCarApp.getAppContext(), ConstantsPrefs.FILTER_BY_STATUS, filterByStatusPostion);
            if(listener != null) {
                listener.onClickDone();
            }
            dismiss();
        });

        tvFromDate.setOnClickListener(v -> {
            DialogUtils.showDatePicker(getActivity(),calendar -> {
                ConvertUtil.setMinimumCalendar(calendar);
                fromTime = calendar.getTimeInMillis();
                tvFromDate.setText(dateFormat.format(calendar.getTime()));
            });
        });

        tvToDate.setOnClickListener(v -> {
            DialogUtils.showDatePicker(getActivity(),calendar -> {
                ConvertUtil.setMaximumCalendar(calendar);
                toTime = calendar.getTimeInMillis();
                tvToDate.setText(dateFormat.format(calendar.getTime()));
            });
        });

        cbFilterByDate.setOnCheckedChangeListener((view, isChecked) -> {
            isFilterByDate = isChecked;
            if(isChecked) {
                viewFromDateToDate.setVisibility(View.VISIBLE);
            } else {
                viewFromDateToDate.setVisibility(View.GONE);
            }
        });

        spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterByStatusPostion = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayAdapter<String> getListStatus() {
        ArrayList<String> listStrAccount = new ArrayList<>();
        ArrayAdapter<String> adapter;
        for(String item : mStatusList) {
            listStrAccount.add(item);
        }
        String[] items = new String[listStrAccount.size()];
        items = listStrAccount.toArray(items);
        adapter = new ArrayAdapter<>(CourtesyCarApp.getActivity(), R.layout.row_spn_1, items);
        adapter.setDropDownViewResource(R.layout.row_spn_dropdown_1);
        return adapter;
    }
}
