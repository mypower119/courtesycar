package vn.mtouch.courtesycar.presentation.features.add_contract;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import vn.mtouch.courtesycar.presentation.custom_view.CustomFontTextView;
import vn.mtouch.courtesycar.presentation.features.signature.SignatureActivity;
import vn.mtouch.courtesycar.utils.ConvertUtil;
import vn.mtouch.courtesycar.utils.DialogUtils;
import vn.mtouch.courtesycar.utils.ImageUtils;
import vn.mtouch.courtesycar.utils.ProgressDialogCustom;

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
    @BindView(R.id.edt_address)
    EditText edtAddress;
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
    @BindView(R.id.tvCarInfo)
    CustomFontTextView tvCarInfo;

    @BindView(R.id.front_license_diploma)
    ImageView imgFrontLicenseDiploma;
    @BindView(R.id.back_license_diploma)
    ImageView imgBackLicenseDiploma;
    @BindView(R.id.img_signature)
    ImageView imgSignature;

    private static final String EXTRA_CONTRACT = "EXTRA_CONTRACT";
    private static final String EXTRA_IS_CREATE = "EXTRA_IS_CREATE";
    private AddContractViewModel mViewModel;
    private BorrowContractModel contractModel;
    private boolean isCreate = false;
    private List<CarModel> mCars;
    ArrayAdapter<String> mAdapterCars;
    DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
    ProgressDialogCustom progressDialogCustom;

    public static AddContractFragment newInstance(String strContactModel, boolean isCreate) {
        AddContractFragment fragment = new AddContractFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CONTRACT, strContactModel);
        bundle.putBoolean(EXTRA_IS_CREATE, isCreate);
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
        if (bundle.containsKey(EXTRA_IS_CREATE)) {
            isCreate = bundle.getBoolean(EXTRA_IS_CREATE);
        }
        setupUi();
        setupEvent();
    }

    private void setupUi() {
        progressDialogCustom = new ProgressDialogCustom(getActivity());
        wvContract.setVisibility(View.GONE);
        String contextHtML = ConstantsPrefs.getStrHTML(getActivity());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvContract.setText(Html.fromHtml(contextHtML, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvContract.setText(Html.fromHtml(contextHtML));
        }

        if (isCreate) {
            cbAgree.setChecked(false);
        } else {
            cbAgree.setChecked(true);
            contractModel.setTimeOut(Calendar.getInstance().getTimeInMillis());
        }
        if (contractModel != null) {
            Calendar timeOut = Calendar.getInstance();
            if (contractModel.getTimeOut() != null) {
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
            edtAddress.setText(ConvertUtil.convertObjectToString(contractModel.getAddress()));
//            new AsyncTask<Void, Void, List<Bitmap>>() {
//                @Override
//                protected List<Bitmap> doInBackground(Void... voids) {
//                    ArrayList<Bitmap> bitmaps = new ArrayList<>();
//                    bitmaps.add(ImageUtils.getBitmap(contractModel.getPathBackLicense()));
//                    bitmaps.add(ImageUtils.getBitmap(contractModel.getPathFrontLicense()));
//                    bitmaps.add(ImageUtils.getBitmap(contractModel.getPathSignature()));
//                    return bitmaps;
//                }
//
//                @Override
//                protected void onPostExecute(List<Bitmap> bitmaps) {
//                    super.onPostExecute(bitmaps);
//                    if(bitmaps.size() >= 2) {
//                        imgBackLicenseDiploma.setImageBitmap(bitmaps.get(0));
//                        imgFrontLicenseDiploma.setImageBitmap(bitmaps.get(1));
//                        imgSignature.setImageBitmap(bitmaps.get(2));
//
//                        pathBack = contractModel.getPathBackLicense();
//                        pathFront = contractModel.getPathFrontLicense();
//                        Picasso.with(getActivity()).load(getImageUri(getActivity(), bitmaps.get(0))).resize(350,350).into(imgBackLicenseDiploma);
//                        Picasso.with(getActivity()).load(getImageUri(getActivity(), bitmaps.get(1))).resize(350,350).into(imgFrontLicenseDiploma);
//                        Picasso.with(getActivity()).load(getImageUri(getActivity(), bitmaps.get(2))).resize(350,350).into(imgSignature);
//                    }
//                }
//            }.execute();

            pathBack = contractModel.getPathBackLicense();
            pathFront = contractModel.getPathFrontLicense();
            File file1 = new File(ImageUtils.getPathSavedImage(pathBack));
            File file2 = new File(ImageUtils.getPathSavedImage(pathFront));
            File file3 = new File(ImageUtils.getPathSavedImage(contractModel.getPathSignature()));

            Picasso.with(getActivity()).load(file1).fit().centerInside().into(imgBackLicenseDiploma);
            Picasso.with(getActivity()).load(file2).fit().centerInside().into(imgFrontLicenseDiploma);
            Picasso.with(getActivity()).load(file3).fit().centerInside().into(imgSignature);
        } else {
            contractModel = new BorrowContractModel();
            contractModel.setTimeIn(Calendar.getInstance().getTimeInMillis());
        }
        Calendar timeIn = Calendar.getInstance();
        if (contractModel.getTimeIn() != null) {
            timeIn.setTimeInMillis(contractModel.getTimeIn());
            try {
                tvTimeIn.setText(df.format(timeIn.getTime()));
            } catch (Exception e) {
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
            for (int i = 0; i < carModels.size(); i++) {
                if ((contractModel.getQrCode() + "").equals(carModels.get(i).getQrCode())) {
                    positionSelect = i;
                }
                listStrAccount.add(carModels.get(i).getCarName() + " " + carModels.get(i).getCarCode());
            }
            String[] items = new String[listStrAccount.size()];
            items = listStrAccount.toArray(items);
            if (positionSelect != -1) {
                spinner.setSelection(positionSelect);
                setInfoCar(mCars.get(positionSelect));
            }

            mAdapterCars.addAll(items);
        });
        spnLicenseType.setAdapter(getLicenseTypeAdapter());
        Collections.reverse(lstLicenseModel);
        for (int i = 0; i < lstLicenseModel.size(); i++) {
            if (lstLicenseModel.get(i).getName().equals(contractModel.getLicenseType())) {
                spnLicenseType.setSelection(i);
                break;
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void setInfoCar(CarModel car) {
        tvCarInfo.setText(car.getCarName() + " " + car.getCarCode());
    }

    List<LicenseModel> lstLicenseModel = LicenseModel.getAllLicense();

    private ArrayAdapter<String> getLicenseTypeAdapter() {
        ArrayList<String> listStrAccount = new ArrayList<>();
        ArrayAdapter<String> adapter;
        for (LicenseModel item : lstLicenseModel) {
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
        if (TextUtils.isEmpty(edtFullName.getText().toString())) {
            edtFullName.setError("empty");
            edtFullName.requestFocus();
            showErrorToast("input invalid");
            return false;
        }

        if (TextUtils.isEmpty(edtDateOfBirth.getText().toString())) {
            edtDateOfBirth.setError("empty");
            edtDateOfBirth.requestFocus();
            showErrorToast("input invalid");
            return false;
        }

        if (TextUtils.isEmpty(edtPhoneNumber.getText().toString())) {
            edtPhoneNumber.setError("empty");
            edtPhoneNumber.requestFocus();
            showErrorToast("input invalid");
            return false;
        }

        if (!cbAgree.isChecked()) {
            cbAgree.requestFocus();
            showErrorToast("must to agree contract");
            return false;
        }

        return true;
    }

    private void showErrorToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private Uri photoUriBack;
    private Uri photoUriFront;
    private void showCamera(int actionCamera) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            File file = null;
            try {
                file = createImageFile(actionCamera);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (file != null) {
                switch (actionCamera) {
                    case PICK_IMAGE_BACK:
                        photoUriBack = null;
                        photoUriBack = Uri.fromFile(file);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUriBack);
                        break;
                    case PICK_IMAGE_FRONT:
                        photoUriFront = null;
                        photoUriFront = Uri.fromFile(file);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUriFront);
                        break;
                }
                startActivityForResult(intent, actionCamera);
            }
        }
    }

    String pathBack = "";
    String pathFront = "";
    private File createImageFile(int actionCamera) throws IOException {
        // Create an image file name
        String timeStamp = "";
        long timestamp = System.currentTimeMillis();
        switch (actionCamera) {
            case PICK_IMAGE_BACK:
                pathBack = timeStamp = "back_" + timestamp;
                break;
            case PICK_IMAGE_FRONT:
                pathFront = timeStamp = "front_" + timestamp;
                break;
            default:
                timeStamp = "lost_" + timestamp;
                break;
        }
        File storageDir = getContext().getExternalFilesDir(ImageUtils.returnLicenseFilePath());
        return File.createTempFile(timeStamp, ".png", storageDir);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if(requestCode == SIGNATURE) {
                String imageName = data.getStringExtra("imageName");
                imgSignature.setImageBitmap(ImageUtils.getBitmap(imageName));
                contractModel.setPathSignature(imageName);
            } else {
                if (requestCode == PICK_IMAGE_FRONT) {
                    if (photoUriFront != null) {
                        //imgFrontLicenseDiploma.setImageURI(photoUri);
                        Picasso.with(getActivity()).load(photoUriFront).resize(350,350).into(imgFrontLicenseDiploma);
                    }
                    //imgFrontLicenseDiploma.setImageBitmap(bmp);
                } else if (requestCode == PICK_IMAGE_BACK) {
                    if (photoUriBack != null) {
                        //imgBackLicenseDiploma.setImageURI(photoUri);
                        Picasso.with(getActivity()).load(photoUriBack).resize(350,350).into(imgBackLicenseDiploma);
                    }
                    //imgBackLicenseDiploma.setImageBitmap(bmp);
                }
            }
        }

//        if (resultCode == Activity.RESULT_OK) {
//            if (data == null) {
//                //Display an error
//                return;
//            }
//            try {
////                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
////                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
////                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
//                if(requestCode == SIGNATURE) {
//                    String imageName = data.getStringExtra("imageName");
//                    imgSignature.setImageBitmap(ImageUtils.getBitmap(imageName));
//                    contractModel.setPathSignature(imageName);
//                } else {
//                    Bitmap bmp = (Bitmap) data.getExtras().get("data");
//                    //bmp = Bitmap.createScaledBitmap(bmp, 80, 80, false);
//
//                    Uri uri = data.getData();
//                    if (requestCode == PICK_IMAGE_FRONT) {
//                        imgFrontLicenseDiploma.setImageBitmap(bmp);
//                    } else if (requestCode == PICK_IMAGE_BACK) {
//                        imgBackLicenseDiploma.setImageBitmap(bmp);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    AsyncTask<Void, Void, Void> mAsyncTask;
    public static final int PICK_IMAGE_FRONT = 101;
    public static final int PICK_IMAGE_BACK = 102;
    public static final int SIGNATURE = 103;

    private void setupEvent() {
        imgSignature.setOnClickListener(v -> {
            startActivityForResult(SignatureActivity.getCallingActivity(getActivity()), SIGNATURE);
        });
        imgBackLicenseDiploma.setOnClickListener(v -> {
//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_BACK);

//            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//            startActivityForResult(intent, PICK_IMAGE_BACK);
            showCamera(PICK_IMAGE_BACK);
        });
        imgFrontLicenseDiploma.setOnClickListener(v -> {
//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_FRONT);

//            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//            startActivityForResult(intent, PICK_IMAGE_FRONT);
            showCamera(PICK_IMAGE_FRONT);
        });
        btnSave.setOnClickListener(v -> {
            if (isValidInput()) {
                if (mAsyncTask != null && !mAsyncTask.isCancelled()) {
                    return;
                }
                progressDialogCustom.show();
                mAsyncTask = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        contractModel.setFullName(edtFullName.getText().toString());
                        contractModel.setDateOfBirth(edtDateOfBirth.getText().toString());
                        contractModel.setPhoneNumber(edtPhoneNumber.getText().toString());
                        contractModel.setAddress(edtAddress.getText().toString());

                        if(photoUriBack != null) {
                            contractModel.setPathBackLicense(pathBack + ".png");
                            ImageUtils.saveFile(getActivity(), "", photoUriBack, ImageUtils.returnLicenseFilePath(), contractModel.getPathBackLicense());
                        }
                        if(photoUriFront != null) {
                            contractModel.setPathFrontLicense(pathFront + ".png");
                            ImageUtils.saveFile(getActivity(), "", photoUriFront, ImageUtils.returnLicenseFilePath(), contractModel.getPathFrontLicense());
                        }
                        if (isCreate) {
                            mViewModel.addContract(contractModel);
                        } else {
                            mViewModel.updateContract(contractModel);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        mAsyncTask = null;
                        progressDialogCustom.dismiss();
                        getActivity().finish();
                    }
                };
                mAsyncTask.execute();
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
                contractModel.setQrCode(mCars.get(position).getQrCode());
                setInfoCar(mCars.get(position));
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
