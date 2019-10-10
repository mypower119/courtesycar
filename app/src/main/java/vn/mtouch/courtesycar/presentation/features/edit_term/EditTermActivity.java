package vn.mtouch.courtesycar.presentation.features.edit_term;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.prefs.ConstantsPrefs;
import vn.mtouch.courtesycar.presentation.base_view.BaseActivity;

public class EditTermActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.edt_term_content)
    EditText edtTermContent;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, EditTermActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        ButterKnife.bind(this);
        setupToolbar(mToolbar, R.string.edit_term);
        init();
        setEvent();
    }

    private void init() {
        edtTermContent.setText(ConstantsPrefs.getStrHTML(this));
    }

    private void setEvent() {
        btnUpdate.setOnClickListener(v -> {
            ConstantsPrefs.saveTerm(this, edtTermContent.getText().toString());
            finish();
        });
    }
}
