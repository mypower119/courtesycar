package vn.mtouch.courtesycar.presentation.features.backup_restore;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.CreateFileActivityOptions;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveClient;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.drive.widget.DataBufferAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import vn.mtouch.courtesycar.R;
import vn.mtouch.courtesycar.data.db.Repository;
import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.data.prefs.ConstantsPrefs;
import vn.mtouch.courtesycar.data.prefs.SharePreferenceManager;
import vn.mtouch.courtesycar.presentation.base_view.BaseGoogleDriveActivity;
import vn.mtouch.courtesycar.presentation.features.backup_restore.adapter.ResultsAdapter;
import vn.mtouch.courtesycar.presentation.features.backup_restore.model.SyncDataModel;
import vn.mtouch.courtesycar.utils.AndroidUtilities;
import vn.mtouch.courtesycar.utils.BitmapUtils;
import vn.mtouch.courtesycar.utils.ImageUtils;
import vn.mtouch.courtesycar.utils.StringUtils;

public class BackupRestoreActivity extends BaseGoogleDriveActivity implements ResultsAdapter.OnFileAdapterListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_files)
    RecyclerView rvFiles;
    @BindView(R.id.btn_take_backup)
    Button btnTakeBackup;
    @BindView(R.id.listViewResults)
    ListView mListView;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;


    private static final String TAG = "drive-quickstart";
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 1;
    private static final int REQUEST_CODE_CREATOR = 2;
    private static final String DATABASE_FILE_NAME = "CourtesyCarDB";
    private static final String DATABASE_FOLDER_NAME = "CourtesyCar Database";

    private DataBufferAdapter<Metadata> mResultsAdapter;
    private DriveFolder driveFolderCurrent;
    private String folderDriverId = "";
    private DriveClient mDriveClient;
    private DriveResourceClient mDriveResourceClient;
    private Bitmap mBitmapToSave;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, BackupRestoreActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_restore);
        ButterKnife.bind(this);
        setupToolbar(mToolbar, R.string.cloud_backup_restore);



        mResultsAdapter = new ResultsAdapter(this, this);
        mListView.setAdapter(mResultsAdapter);
        setupEvent();

        folderDriverId = SharePreferenceManager.getString(this, ConstantsPrefs.FOLDER_GOOGLE_DRIVE);

        Set<Scope> requiredScopes = new HashSet<>(2);
        requiredScopes.add(Drive.SCOPE_FILE);
        requiredScopes.add(Drive.SCOPE_APPFOLDER);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null && signInAccount.getGrantedScopes().containsAll(requiredScopes)) {
            initializeDriveClient(signInAccount);
            firstQueryAllFileDatabase();
        } else {
            GoogleSignInOptions signInOptions =
                    new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestScopes(Drive.SCOPE_FILE)
                            .requestScopes(Drive.SCOPE_APPFOLDER)
                            .build();
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, signInOptions);
            startActivityForResult(googleSignInClient.getSignInIntent(), REQUEST_CODE_SIGN_IN);
        }
    }

    private void firstQueryAllFileDatabase() {
        mResultsAdapter.clear();
        swLayout.setRefreshing(true);
        Query query = new Query.Builder()
                .addFilter(Filters.eq(SearchableField.TITLE, DATABASE_FOLDER_NAME))
                .build();
        // [START drive_android_query_children]
        Task<MetadataBuffer> queryTask = getDriveResourceClient().query(query);
        // END drive_android_query_children]
        queryTask.addOnSuccessListener(this,
                        metadataBuffer -> {
                            if(metadataBuffer != null && metadataBuffer.getCount() > 0 && metadataBuffer.get(0).isFolder() && metadataBuffer.get(0).getDriveId().asDriveFolder() != null) {
                                // query tồn tại folder -> vào cái đầu -> query list
                                driveFolderCurrent = metadataBuffer.get(0).getDriveId().asDriveFolder();
                                listFilesInFolder(driveFolderCurrent);
                            } else {
                                // ko tồn tại -> tạo folder mặc định
                                createFolderDefault();
                            }
                            swLayout.setRefreshing(false);
                        })
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "Error retrieving files", e);
                    showMessage(getString(R.string.query_failed));
                    swLayout.setRefreshing(false);
                });


//
//        mResultsAdapter.clear();
//        swLayout.setRefreshing(true);
//        Query query = new Query.Builder()
////                    .addFilter(Filters.eq(SearchableField.MIME_TYPE, "text/plain"))
//                .addFilter(Filters.contains(SearchableField.TITLE, DATABASE_FILE_NAME))
//                .build();
//        // [START drive_android_query_children]
//        Task<MetadataBuffer> queryTask = getDriveResourceClient().query(query);
//        // END drive_android_query_children]
//        queryTask
//                .addOnSuccessListener(this,
//                        metadataBuffer -> {
//                            swLayout.setRefreshing(false);
//                            mResultsAdapter.append(metadataBuffer);
//                        })
//                .addOnFailureListener(this, e -> {
//                    Log.e(TAG, "Error retrieving files", e);
//                    showMessage(getString(R.string.query_failed));
//                    swLayout.setRefreshing(false);
//                });
    }

    private void getFilesOnDrive() {
        swLayout.setRefreshing(true);
        listFilesInFolder(driveFolderCurrent);
    }

    int totalCallApi = 0;
    int countCallApi = 0;
    private void dismiss() {
        countCallApi++;
        if(countCallApi == totalCallApi) {
            swLayout.setRefreshing(false);
        }
    }
    private void setupEvent() {
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFilesOnDrive();
            }
        });
        btnTakeBackup.setOnClickListener(v -> {
            //createFileInFolder(driveFolderCurrent);
            if(driveFolderCurrent == null) {
                chooseFolderToTakeBackup();
                return;
            }
            swLayout.setRefreshing(true);
            totalCallApi = 1;
            countCallApi = 0;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String dateNow = StringUtils.returnNow();
                    SyncDataModel syncDataModel = new SyncDataModel();
                    syncDataModel.setTerm(ConstantsPrefs.getStrHTML(BackupRestoreActivity.this));
                    syncDataModel.setCars(Repository.getInstance().getAllCars());
                    List<BorrowContractModel> borrowContractModelList = Repository.getInstance().getAllContracts();
                    for(BorrowContractModel item : borrowContractModelList) {
                        File file1 = new File(ImageUtils.getPathSavedImage( item.getPathFrontLicense()));
                        if(file1.exists()) {
                            totalCallApi += 1;
                        }
                        File file2 = new File(ImageUtils.getPathSavedImage(item.getPathBackLicense()));
                        if(file2.exists()) {
                            totalCallApi += 1;
                        }
                        File file3 = new File(ImageUtils.getPathSavedImage(item.getPathSignature()));
                        if(file3.exists()) {
                            totalCallApi += 1;
                        }
                    }
                    int i = 1;
                    for(BorrowContractModel item : borrowContractModelList) {
                        String pathFront = item.getPathFrontLicense();
                        String pathBack = item.getPathBackLicense();
                        String signature = item.getPathSignature();

                        item.setPathFrontLicense(String.format("front_%s_%s.png", i + "", dateNow));
                        item.setPathBackLicense(String.format("back_%s_%s.png", i + "", dateNow));
                        item.setPathSignature(String.format("sign_%s_%s.png", i + "", dateNow));

                        saveFileToDrive(pathFront, item.getPathFrontLicense(), new OnSuccessUploadListener() {
                            @Override
                            public void onDone() {
                                AndroidUtilities.getsUIHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dismiss();
                                    }
                                });
                            }
                        });
                        saveFileToDrive(pathBack, item.getPathBackLicense(), new OnSuccessUploadListener() {
                            @Override
                            public void onDone() {
                                AndroidUtilities.getsUIHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dismiss();
                                    }
                                });
                            }
                        });
                        saveFileToDrive(signature, item.getPathSignature(), new OnSuccessUploadListener() {
                            @Override
                            public void onDone() {
                                AndroidUtilities.getsUIHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dismiss();
                                    }
                                });
                            }
                        });
                        i++;
                    }
                    syncDataModel.setContracts(borrowContractModelList);
                    String data = new Gson().toJson(syncDataModel);
                    uploadDataToDrive(dateNow, driveFolderCurrent, data, new OnSuccessUploadListener() {
                        @Override
                        public void onDone() {
                            AndroidUtilities.getsUIHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    dismiss();
                                }
                            });
                        }
                    });
                }
            });
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();

        });
    }

    @Override
    public void restore(DriveFile file) {
        retrieveContents(file);
    }

    private void retrieveContents(DriveFile file) {
        swLayout.setRefreshing(true);
        totalCallApi = 0;
        countCallApi = 0;
        // [START drive_android_open_file]
        Task<DriveContents> openFileTask =
                getDriveResourceClient().openFile(file, DriveFile.MODE_READ_ONLY);
        // [END drive_android_open_file]
        // [START drive_android_read_contents]
        openFileTask
                .continueWithTask(executor, task -> {
                    DriveContents contents = task.getResult();
                    // Process contents...
                    // [START_EXCLUDE]
                    // [START drive_android_read_as_string]
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(contents.getInputStream()))) {
                        StringBuilder builder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }

                        String dataJson = builder.toString();

                        try {
                            SyncDataModel syncDataModel = new Gson().fromJson(dataJson, SyncDataModel.class);
                            if(!TextUtils.isEmpty(syncDataModel.getTerm())) {
                                SharePreferenceManager.putString(BackupRestoreActivity.this, ConstantsPrefs.TERM, syncDataModel.getTerm());
                            }
                            Repository.getInstance().clearAllData();
                            for(CarModel car : syncDataModel.getCars()) {
                                Repository.getInstance().saveCar(car);

                            }
                            for(BorrowContractModel contract : syncDataModel.getContracts()) {
                                Repository.getInstance().addContract(contract);
                            }
                            retrieveImage(syncDataModel.getContracts(), new OnSuccessUploadListener() {
                                @Override
                                public void onDone() {
                                    AndroidUtilities.getsUIHandler().post(new Runnable() {
                                        @Override
                                        public void run() {
                                            dismiss();
                                        }
                                    });
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            AndroidUtilities.getsUIHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage(getString(R.string.read_failed) + " " + e.getMessage());
                                    swLayout.setRefreshing(false);
                                }
                            });
                        }

                    }
                    // [END drive_android_read_as_string]
                    // [END_EXCLUDE]
                    // [START drive_android_discard_contents]
                    Task<Void> discardTask = getDriveResourceClient().discardContents(contents);
                    // [END drive_android_discard_contents]
                    return discardTask;
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    // [START_EXCLUDE]
                    Log.e(TAG, "Unable to read contents", e);
                    showMessage(getString(R.string.read_failed));
                    // [END_EXCLUDE]
                });
        // [END drive_android_read_contents]
    }

    public static String CONTENT_MIME_TYPE = "image/png";

    private void retrieveImage(List<BorrowContractModel> contracts , OnSuccessUploadListener listener) {
        if(driveFolderCurrent != null) {
            Query query = new Query.Builder()
                    .addFilter(Filters.eq(SearchableField.MIME_TYPE, "image/png"))
//                    .addFilter(Filters.eq(SearchableField.MIME_TYPE, "image/jpeg"))
                    .build();
            // [START drive_android_query_children]
            Task<MetadataBuffer> queryTask = getDriveResourceClient().queryChildren(driveFolderCurrent, query);
            // END drive_android_query_children]
            queryTask
                    .addOnSuccessListener(this,
                            metadataBuffer -> {
                                for(BorrowContractModel contract : contracts) {
                                    for(Metadata metadata : metadataBuffer) {
                                        if(contract.getPathFrontLicense() != null && contract.getPathFrontLicense().equals(metadata.getTitle())) {
                                            totalCallApi += 1;
                                        }
                                        if(contract.getPathBackLicense() != null && contract.getPathBackLicense().equals(metadata.getTitle())) {
                                            totalCallApi += 1;
                                        }
                                        if(contract.getPathSignature() != null && contract.getPathSignature().equals(metadata.getTitle())) {
                                            totalCallApi += 1;
                                        }
                                    }
                                }
                                for(BorrowContractModel contract : contracts) {
                                    for(Metadata metadata : metadataBuffer) {
                                        if(contract.getPathFrontLicense() != null && contract.getPathFrontLicense().equals(metadata.getTitle())) {
                                            downloadImageAndUpdateDB(metadata, contract, 1, listener);
                                        }
                                        if(contract.getPathBackLicense() != null && contract.getPathBackLicense().equals(metadata.getTitle())) {
                                            downloadImageAndUpdateDB(metadata, contract, 0, listener);
                                        }
                                        if(contract.getPathSignature() != null && contract.getPathSignature().equals(metadata.getTitle())) {
                                            downloadImageAndUpdateDB(metadata, contract, 2, listener);
                                        }
                                    }
                                }

                    })
                    .addOnFailureListener(this, e -> {
                        swLayout.setRefreshing(false);
                    });
        } else {
            AndroidUtilities.getsUIHandler().post(new Runnable() {
                @Override
                public void run() {
                    swLayout.setRefreshing(false);
                }
            });
        }
    }

    private void downloadImageAndUpdateDB(Metadata metadata, BorrowContractModel contract, int isFront, OnSuccessUploadListener listener) {
        Task<DriveContents> openFileTask = getDriveResourceClient().openFile(metadata.getDriveId().asDriveFile(), DriveFile.MODE_READ_ONLY);
        openFileTask
                .continueWithTask(executor, task -> {
                    DriveContents contents = task.getResult();

                    try {
                        // TODO parse image lưu xuống folder app
                        Bitmap bitmap = BitmapFactory.decodeStream(contents.getInputStream());
                        if(isFront == 1 && bitmap != null) {
                            ImageUtils.saveImageToStorage(contract.getPathFrontLicense(), bitmap);
                        } else if(bitmap != null && isFront == 0) {
                            ImageUtils.saveImageToStorage(contract.getPathBackLicense(), bitmap);
                        } else if (bitmap != null && isFront == 2) {
                            ImageUtils.saveImageToStorage(contract.getPathSignature(), bitmap);
                        }

                        AndroidUtilities.getsUIHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onDone();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        AndroidUtilities.getsUIHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onDone();
                                showMessage(getString(R.string.read_failed) + " " + e.getMessage());
                            }
                        });
                    }

                    Task<Void> discardTask = getDriveResourceClient().discardContents(contents);
                    return discardTask;
                })
                .addOnFailureListener(e -> {
                    listener.onDone();
                    Log.e(TAG, "Unable to read contents", e);
                    showMessage(getString(R.string.read_failed));
                });
    }

    ExecutorService executor = Executors.newFixedThreadPool(5);
    /** Create a new file and save it to Drive. */
    private void saveFileToDrive(String path, String fileName, OnSuccessUploadListener listener) {
        // Start by creating a new contents, and setting a callback.
        Bitmap image = ImageUtils.getBitmapScale(path);
        getDriveResourceClient()
                .createContents()
                .continueWithTask(executor, (task) -> {
                    OutputStream outputStream = task.getResult().getOutputStream();
                    // Write the bitmap data from it.
                    ByteArrayOutputStream bitmapStream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 100, bitmapStream);
                    try {
                        outputStream.write(bitmapStream.toByteArray());
                    } catch (IOException e) {
                        Log.w(TAG, "Unable to write file contents.", e);
                    }

                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                            .setTitle(fileName)
                            .setMimeType("image/png")
                            .setStarred(true)
                            .build();

                    return getDriveResourceClient().createFile(driveFolderCurrent, changeSet, task.getResult());
                })
                .addOnSuccessListener(BackupRestoreActivity.this,
                        driveFile -> {
                            listener.onDone();
                        } )
                .addOnFailureListener(BackupRestoreActivity.this, e -> {
                    listener.onDone();
                });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_backup_restore, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void chooseFolderToTakeBackup() {
        pickFolder()
                .addOnSuccessListener(this,
                        driveId -> {
                            listFilesInFolder(driveId.asDriveFolder());
                        })
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "No folder selected", e);
                    showMessage(getString(R.string.folder_not_selected));
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                forceSignIn();
                break;
            case R.id.action_choose_folder_backup_restore:
                chooseFolderToTakeBackup();
                break;
            case R.id.action_create_folder_backup_restore:
                createFolderDefault();
                break;
            case R.id.action_sign_out:
                signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createFolderDefault() {
        getDriveResourceClient()
                .getRootFolder()
                .continueWithTask(task -> {
                    DriveFolder parentFolder = task.getResult();
                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                            .setTitle(DATABASE_FOLDER_NAME)
                            .setMimeType(DriveFolder.MIME_TYPE)
                            .setStarred(true)
                            .build();
                    return getDriveResourceClient().createFolder(parentFolder, changeSet);
                })
                .addOnSuccessListener(this,
                        driveFolder -> {
                            showMessage(getString(R.string.file_created,
                                    driveFolder.getDriveId().encodeToString()));
                            listFilesInFolder(driveFolder);
                        })
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "Unable to create file", e);
                    showMessage(getString(R.string.file_create_error));
                });
    }

    private void listFilesInFolder(DriveFolder folder) {
        mResultsAdapter.clear();
        driveFolderCurrent = folder;
        if(driveFolderCurrent != null) {
            Query query = new Query.Builder()
                    .addFilter(Filters.eq(SearchableField.MIME_TYPE, "text/plain"))
//                    .addFilter(Filters.eq(SearchableField.MIME_TYPE, "image/jpeg"))
                    .build();
            // [START drive_android_query_children]
            Task<MetadataBuffer> queryTask = getDriveResourceClient().queryChildren(folder, query);
            // END drive_android_query_children]
            queryTask
                    .addOnSuccessListener(this,
                            metadataBuffer -> {
                                swLayout.setRefreshing(false);
                                mResultsAdapter.append(metadataBuffer);
                                folderDriverId = driveFolderCurrent.getDriveId().encodeToString();
                                SharePreferenceManager.putString(getApplicationContext(), ConstantsPrefs.FOLDER_GOOGLE_DRIVE, folderDriverId);
                            })
                    .addOnFailureListener(this, e -> {
                        Log.e(TAG, "Error retrieving files", e);
                        showMessage(getString(R.string.query_failed));
                        swLayout.setRefreshing(false);
                    });
        } else {
            firstQueryAllFileDatabase();
        }
    }

    private void createFileInFolder(final DriveFolder parent) {
        if(parent == null) {
            showMessage(getString(R.string.file_create_error));
            return;
        }
        getDriveResourceClient()
                .createContents()
                .continueWithTask(task -> {
                    String packageName = getPackageName();
                    String DB_FILEPATH = "/data/data/" + packageName + "/databases/courtesy_car_db";
                    File dbFile = new File(DB_FILEPATH);


                    int size = (int) dbFile.length();
                    byte[] bytes = new byte[size];
                    try {
                        BufferedInputStream buf = new BufferedInputStream(new FileInputStream(dbFile));
                        buf.read(bytes, 0, bytes.length);
                        buf.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    DriveContents contents = task.getResult();
                    OutputStream outputStream = contents.getOutputStream();
                    outputStream.write(bytes);

                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                            .setTitle("CourtesyCarDB.db")
                            .setMimeType("*/*")
                            .setStarred(true)
                            .build();

                    return getDriveResourceClient().createFile(parent, changeSet, contents);
                })
                .addOnSuccessListener(this,
                        driveFile -> showMessage(getString(R.string.file_created,
                                driveFile.getDriveId().encodeToString())))
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "Unable to create file", e);
                    showMessage(getString(R.string.file_create_error));
                });
    }

    public interface OnSuccessUploadListener {
        void onDone();
    }
    private void uploadDataToDrive(String now, final DriveFolder parent, String data, OnSuccessUploadListener listener) {

        getDriveResourceClient()
                .createContents()
                .continueWithTask(executor ,task -> {
                    DriveContents contents = task.getResult();
                    OutputStream outputStream = contents.getOutputStream();

                    try (Writer writer = new OutputStreamWriter(outputStream)) {
                        writer.write(data);
                    }

                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                            .setTitle(DATABASE_FILE_NAME + "_" + now)
                            .setMimeType("text/plain")
                            .setStarred(true)
                            .build();

                    return getDriveResourceClient().createFile(parent, changeSet, contents);
                })
                .addOnSuccessListener(this,
                        driveFile -> {
                            listener.onDone();
                            getFilesOnDrive();
                            showMessage(getString(R.string.file_created,
                                    driveFile.getDriveId().encodeToString()));
                        } )
                .addOnFailureListener(this, e -> {
                    listener.onDone();
                    Log.e(TAG, "Unable to create file", e);
                    showMessage(getString(R.string.file_create_error));
                });
    }



    private Task<Void> createFileIntentSender(DriveContents driveContents, Bitmap image, String fileName) {
        Log.i(TAG, "New contents created.");
        // Get an output stream for the contents.
        OutputStream outputStream = driveContents.getOutputStream();
        // Write the bitmap data from it.
        ByteArrayOutputStream bitmapStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, bitmapStream);
        try {
            outputStream.write(bitmapStream.toByteArray());
        } catch (IOException e) {
            Log.w(TAG, "Unable to write file contents.", e);
        }

        // Create the initial metadata - MIME type and title.
        // Note that the user will be able to change the title later.
        MetadataChangeSet metadataChangeSet =
                new MetadataChangeSet.Builder()
                        .setMimeType("image/png")
                        .setTitle(fileName)
                        .build();
        // Set up options to configure and display the create file activity.
        CreateFileActivityOptions createFileActivityOptions =
                new CreateFileActivityOptions.Builder()
                        .setInitialMetadata(metadataChangeSet)
                        .setInitialDriveContents(driveContents)
                        .build();

        return mDriveClient
                .newCreateFileActivityIntentSender(createFileActivityOptions)
                .continueWith(
                        task -> {
                            startIntentSenderForResult(task.getResult(), REQUEST_CODE_CREATOR, null, 0, 0, 0);
                            return null;
                        });
    }

    @Override
    protected void onDriveClientReady() {

    }


    @Override
    public void deleteFile(DriveFile file) {
        // [START drive_android_delete_file]
        getDriveResourceClient()
                .delete(file)
                .addOnSuccessListener(this,
                        aVoid -> {
                            showMessage(getString(R.string.file_deleted));
                            getFilesOnDrive();
                        })
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "Unable to delete file", e);
                    showMessage(getString(R.string.delete_failed));
                });
        // [END drive_android_delete_file]
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SIGN_IN:
                Log.i(TAG, "Sign in request code");
                // Called after user is signed in.
                if (resultCode == RESULT_OK) {
                    Log.i(TAG, "Signed in successfully.");
                    // Use the last signed in account here since it already have a Drive scope.
                    mDriveClient = Drive.getDriveClient(this, GoogleSignIn.getLastSignedInAccount(this));
                    // Build a drive resource client.
                    mDriveResourceClient =
                            Drive.getDriveResourceClient(this, GoogleSignIn.getLastSignedInAccount(this));
//                    // Start camera.
//                    startActivityForResult(
//                            new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CODE_CAPTURE_IMAGE);
                    firstQueryAllFileDatabase();
                }
                break;
//            case REQUEST_CODE_CAPTURE_IMAGE:
//                Log.i(TAG, "capture image request code");
//                // Called after a photo has been taken.
//                if (resultCode == Activity.RESULT_OK) {
//                    Log.i(TAG, "Image captured successfully.");
//                    // Store the image data as a bitmap for writing later.
//                    mBitmapToSave = (Bitmap) data.getExtras().get("data");
//                    saveFileToDrive();
//                }
//                break;
//            case REQUEST_CODE_CREATOR:
//                Log.i(TAG, "creator request code");
//                // Called after a file is saved to Drive.
//                if (resultCode == RESULT_OK) {
//                    Log.i(TAG, "Image successfully saved.");
//                    mBitmapToSave = null;
//                    // Just start the camera again for another photo.
//                    startActivityForResult(
//                            new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CODE_CAPTURE_IMAGE);
//                }
//                break;
        }
    }

//
//    /**
//     * Creates an {@link IntentSender} to start a dialog activity with configured {@link
//     * CreateFileActivityOptions} for user to create a new photo in Drive.
//     */
//    private Task<Void> createFileIntentSender(DriveContents driveContents, Bitmap image) {
//        Log.i(TAG, "New contents created.");
//        // Get an output stream for the contents.
//        OutputStream outputStream = driveContents.getOutputStream();
//        // Write the bitmap data from it.
//        ByteArrayOutputStream bitmapStream = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.PNG, 100, bitmapStream);
//        try {
//            outputStream.write(bitmapStream.toByteArray());
//        } catch (IOException e) {
//            Log.w(TAG, "Unable to write file contents.", e);
//        }
//
//        // Create the initial metadata - MIME type and title.
//        // Note that the user will be able to change the title later.
//        MetadataChangeSet metadataChangeSet =
//                new MetadataChangeSet.Builder()
//                        .setMimeType("image/jpeg")
//                        .setTitle("Android Photo.png")
//                        .build();
//        // Set up options to configure and display the create file activity.
//        CreateFileActivityOptions createFileActivityOptions =
//                new CreateFileActivityOptions.Builder()
//                        .setInitialMetadata(metadataChangeSet)
//                        .setInitialDriveContents(driveContents)
//                        .build();
//
//        return mDriveClient
//                .newCreateFileActivityIntentSender(createFileActivityOptions)
//                .continueWith(
//                        task -> {
//                            startIntentSenderForResult(task.getResult(), REQUEST_CODE_CREATOR, null, 0, 0, 0);
//                            return null;
//                        });
//    }

    /** Start sign in activity. */
//    private void signIn() {
//        Log.i(TAG, "Start sign in");
//        GoogleSignInClient GoogleSignInClient = buildGoogleSignInClient();
//        startActivityForResult(GoogleSignInClient.getSignInIntent(), REQUEST_CODE_SIGN_IN);
//    }

    //    /** Build a Google SignIn client. */
//    private GoogleSignInClient buildGoogleSignInClient() {
//        GoogleSignInOptions signInOptions =
//                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                        .requestScopes(Drive.SCOPE_FILE)
//                        .build();
//        return GoogleSignIn.getClient(this, signInOptions);
//    }
}
