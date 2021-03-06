package vn.mtouch.courtesycar.presentation.features.ui_utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.presentation.base_view.BaseActivity;
import vn.mtouch.courtesycar.presentation.features.add_contract.AddContractFragment;
import vn.mtouch.courtesycar.presentation.features.list_contract.ContractFragment;

public class TransferActivity extends BaseActivity {
    private Unbinder mUnbinder;
    private static final String EXTRA_OBJECT = "EXTRA_OBJECT";
    private static final String EXTRA_IS_CREATE = "EXTRA_IS_CREATE";

    public static Intent getCallingIntent(Context context, BorrowContractModel contractModel, boolean isCreate) {
        Intent intent = new Intent(context, TransferActivity.class);
        intent.putExtra(EXTRA_OBJECT, new Gson().toJson(contractModel) + "");
        intent.putExtra(EXTRA_IS_CREATE, isCreate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        mUnbinder = ButterKnife.bind(this);
        boolean isCreate =  getIntent().getBooleanExtra(EXTRA_IS_CREATE, false);
        replaceFragment(R.id.fragment, AddContractFragment.newInstance(getIntent().getStringExtra(EXTRA_OBJECT), isCreate));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
