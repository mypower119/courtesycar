package vn.mtouch.courtesycar.presentation.features.signature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.gcacace.signaturepad.views.SignaturePad;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.presentation.base_view.BaseActivity;
import vn.mtouch.courtesycar.utils.ImageUtils;

public class SignatureActivity extends BaseActivity {
    @BindView(R.id.signaturePad)
    SignaturePad signaturePad;
    @BindView(R.id.saveButton)
    Button saveButton;
    @BindView(R.id.clearButton)
    Button clearButton;

    public static Intent getCallingActivity(Context context) {
        return new Intent(context, SignatureActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        ButterKnife.bind(this);

        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write code for saving the signature here
                Bitmap bitmap = signaturePad.getSignatureBitmap();

                String imageName = "signature_" + System.currentTimeMillis() + ".png";
                ImageUtils.saveImageToStorage(imageName, bitmap);
                Intent intent = new Intent();
                intent.putExtra("imageName", imageName);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });
    }
}
