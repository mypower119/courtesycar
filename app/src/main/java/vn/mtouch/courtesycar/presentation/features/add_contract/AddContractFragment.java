package vn.mtouch.courtesycar.presentation.features.add_contract;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.mtouch.courtesycar.CourtesyCarApp;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.data.db.model.LicenseModel;
import vn.mtouch.courtesycar.data.prefs.ConstantsPrefs;
import vn.mtouch.courtesycar.utils.CollectionUtils;
import vn.mtouch.courtesycar.utils.ConvertUtil;
import vn.mtouch.courtesycar.utils.DialogUtils;

public class AddContractFragment extends Fragment {
    @BindView(R.id.edt_time_in)
    TextView tvTimeIn;
    @BindView(R.id.edt_time_out)
    TextView tvTimeOut;
    @BindView(R.id.edt_full_name)
    EditText edtFullName;
    @BindView(R.id.edt_date_of_birth)
    EditText edtDateOfBirth;
    @BindView(R.id.edt_phone_number)
    EditText edtPhoneNumber;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.view_time_in)
    View viewTimeIn;
    @BindView(R.id.view_time_out)
    View viewTimeOut;
    @BindView(R.id.spn_car)
    Spinner spinner;
    @BindView(R.id.spn_license_type)
    Spinner spnLicenseType;
    @BindView(R.id.wv_contract)
    WebView wvContract;
    @BindView(R.id.tv_contract)
    TextView tvContract;

    private static final String EXTRA_CONTRACT = "EXTRA_CONTRACT";
    private AddContractViewModel mViewModel;
    private BorrowContractModel contractModel;
    private boolean isCreate = false;
    private List<CarModel> mCars;
    ArrayAdapter<String> mAdapterCars;
    DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);

    public static AddContractFragment newInstance(String strContactModel) {
        AddContractFragment fragment = new AddContractFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CONTRACT, strContactModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_contract_fragment, container, false);
        ButterKnife.bind(this, view);
        mViewModel = ViewModelProviders.of(this).get(AddContractViewModel.class);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle.containsKey(EXTRA_CONTRACT)) {
            contractModel = new Gson().fromJson(bundle.getString(EXTRA_CONTRACT), BorrowContractModel.class);
        }
        setupUi();
        setupEvent();
    }

    private void setupUi() {
        wvContract.setVisibility(View.GONE);
//        WebSettings webSettings = wvContract.getSettings();
//        webSettings.setBuiltInZoomControls(false);
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDisplayZoomControls(false);
//        webSettings.setLoadWithOverviewMode(true);
//
//        wvContract.setVerticalScrollBarEnabled(false);
//        wvContract.setHorizontalScrollBarEnabled(false);
//        wvContract.setDrawingCacheEnabled(true);
//
//        wvContract.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//
//        wvContract.setWebViewClient(new WebViewClient());
        // Force links and redirects to open in the WebView instead of in a browser
//        wvContract.setWebViewClient(new AppWebViewClients(mProgressbar, new AppWebViewClients.OnWebViewClientsListener() {
//            @Override
//            public void onPageFinished() {
//                scrollView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                print();
//                            }
//                        }, 500);
//                    }
//                });
//            }
//        }));
        String contextHtML = ConstantsPrefs.getStrHTML(getActivity());
        //wvContract.loadData(contextHtML,"text/html", "utf-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvContract.setText(Html.fromHtml(contextHtML, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvContract.setText(Html.fromHtml(contextHtML));
        }
        if (contractModel != null) {
            isCreate = false;
            Calendar timeOut = Calendar.getInstance();
            if(contractModel.getTimeOut() != null) {
                timeOut.setTimeInMillis(contractModel.getTimeOut());
                try {
                    tvTimeOut.setText(df.format(timeOut.getTime()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //edtCar.setText(contractModel.getCarName() + " - " + contractModel.getCarCode());
            edtFullName.setText(ConvertUtil.convertObjectToString(contractModel.getFullName()));
            edtDateOfBirth.setText(ConvertUtil.convertObjectToString(contractModel.getDateOfBirth()));
            edtPhoneNumber.setText(ConvertUtil.convertObjectToString(contractModel.getPhoneNumber()));
            cbAgree.setChecked(true);
        } else {
            contractModel = new BorrowContractModel();
            contractModel.setTimeIn(Calendar.getInstance().getTimeInMillis());
            isCreate = true;
            cbAgree.setChecked(false);
        }
        Calendar timeIn = Calendar.getInstance();
        if(contractModel.getTimeIn() != null) {
            timeIn.setTimeInMillis(contractModel.getTimeIn());
            try {
                tvTimeIn.setText(df.format(timeIn.getTime()));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        mAdapterCars = new ArrayAdapter<>(getActivity(), R.layout.row_spn_1, new ArrayList<>());
        mAdapterCars.setDropDownViewResource(R.layout.row_spn_dropdown_1);
        spinner.setAdapter(mAdapterCars);
        mViewModel.getCars().observe(this, carModels -> {
            mCars = carModels;
            int positionSelect = -1;
            ArrayList<String> listStrAccount = new ArrayList<>();
            for(int i = 0; i < carModels.size(); i++) {
                if((contractModel.getCarCode() + "").equals(carModels.get(i).getCarCode())
                        && (contractModel.getCarName() + "").equals(carModels.get(i).getCarName()) ) {
                    positionSelect = i;
                }
                listStrAccount.add(carModels.get(i).getCarName() + " " + carModels.get(i).getCarCode());
            }
            String[] items = new String[listStrAccount.size()];
            items = listStrAccount.toArray(items);
            if(positionSelect != -1) {
                spinner.setSelection(positionSelect);
            }
            mAdapterCars.addAll(items);
        });
        spnLicenseType.setAdapter(getLicenseTypeAdapter());
        Collections.reverse(lstLicenseModel);
        for( int i = 0; i < lstLicenseModel.size(); i++) {
            if(lstLicenseModel.get(i).getName().equals(contractModel.getLicenseType())) {
                spnLicenseType.setSelection(i);
                break;
            }
        }
    }

    List<LicenseModel> lstLicenseModel = LicenseModel.getAllLicense();
    private ArrayAdapter<String> getLicenseTypeAdapter() {
        ArrayList<String> listStrAccount = new ArrayList<>();
        ArrayAdapter<String> adapter;
        for(LicenseModel item : lstLicenseModel) {
            listStrAccount.add(item.getName());
        }
        String[] items = new String[listStrAccount.size()];
        items = listStrAccount.toArray(items);
        adapter = new ArrayAdapter<>(CourtesyCarApp.getActivity(), R.layout.row_spn_1, items);
        adapter.setDropDownViewResource(R.layout.row_spn_dropdown_1);
        return adapter;
    }

    private boolean isValidInput() {
        // từ trên xuống
        if(TextUtils.isEmpty(edtFullName.getText().toString())) {
            edtFullName.setError("empty");
            edtFullName.requestFocus();
            showErrorToast("input invalid");
            return false;
        }

        if(TextUtils.isEmpty(edtDateOfBirth.getText().toString())) {
            edtDateOfBirth.setError("empty");
            edtDateOfBirth.requestFocus();
            showErrorToast("input invalid");
            return false;
        }

        if(TextUtils.isEmpty(edtPhoneNumber.getText().toString())) {
            edtPhoneNumber.setError("empty");
            edtPhoneNumber.requestFocus();
            showErrorToast("input invalid");
            return false;
        }

        if(!cbAgree.isChecked()) {
            cbAgree.requestFocus();
            showErrorToast("must to agree contract");
            return false;
        }
        return true;
    }

    private void showErrorToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void setupEvent() {
        btnSave.setOnClickListener(v -> {
            if(isValidInput()) {
                contractModel.setFullName(edtFullName.getText().toString());
                contractModel.setDateOfBirth(edtDateOfBirth.getText().toString());
                contractModel.setPhoneNumber(edtPhoneNumber.getText().toString());
                if (isCreate) {
                    mViewModel.addContract(contractModel);
                } else {
                    mViewModel.updateContract(contractModel);
                }
                getActivity().finish();
            }
        });

        viewTimeIn.setOnClickListener(v -> {
            DialogUtils.showDateTimePicker(getActivity(), time -> {
                contractModel.setTimeIn(time);
                tvTimeIn.setText(df.format(new Date(time)));
            });
        });

        viewTimeOut.setOnClickListener(v -> {
            DialogUtils.showDateTimePicker(getActivity(), time -> {
                contractModel.setTimeOut(time);
                tvTimeOut.setText(df.format(new Date(time)));
            });
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
                contractModel.setCarCode(mCars.get(position).getCarCode());
                contractModel.setCarName(mCars.get(position).getCarName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnLicenseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //contractModel.setLicenseType(LicenseModel.getAllLicense().get(position).getName());
                spnLicenseType.setSelection(position);
                contractModel.setLicenseType(lstLicenseModel.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
