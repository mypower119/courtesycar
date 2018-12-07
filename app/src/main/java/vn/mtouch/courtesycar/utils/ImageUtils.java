package vn.mtouch.courtesycar.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/18/18
 */

public class ImageUtils {
    public static String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    public static Bitmap convertImageViewToBitmap(ImageView img) {
        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        return drawable.getBitmap();
    }

    public static Bitmap getBitmap(String fileName) {
        return BitmapFactory.decodeFile(returnLicenseFilePath() + "/" + fileName);
    }

    public static void saveImageToStorage(String fileName, Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
        InputStream inputStream = bs;

        File file = new File(returnLicenseFilePath());
        if (!file.exists()) {
            file.mkdirs();
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file + "/"+ fileName);
            try {
                byte[] buffer = new byte[2 * 1024];
                int read;
                while ((read = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, read);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (Exception e) {
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String returnLicenseFilePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/CourtesyCar";
    }

    public static final int REQUEST_PERMISSION_WRITE_READ = 8;
    public static void askStoragePermission(Context context) {

        int permissionWrite = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionRead == PackageManager.PERMISSION_DENIED || permissionWrite == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (permissionRead == PackageManager.PERMISSION_DENIED || permissionWrite == PackageManager.PERMISSION_DENIED) {
                    String[] permissions = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions((Activity) context, permissions, REQUEST_PERMISSION_WRITE_READ);
                }
            }
        }
    }
}
