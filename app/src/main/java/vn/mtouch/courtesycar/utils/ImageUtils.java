package vn.mtouch.courtesycar.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static Bitmap getBitmapScale(String fileName) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds =true;
        BitmapFactory.decodeFile(returnLicenseFilePath() + "/" + fileName, options);

        // Decode bitmap with inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 500, 500);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(returnLicenseFilePath() + "/" + fileName, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight)
    {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize =1;
        if(height > reqHeight || width > reqWidth){
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while((halfHeight / inSampleSize)> reqHeight && (halfWidth / inSampleSize)> reqWidth){
                inSampleSize *=2;
            }
        }
        return inSampleSize;
    }

    public static String getPathSavedImage(String fileName) {
        return returnLicenseFilePath() + "/" + fileName;
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

    private static boolean isVirtualFile(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!DocumentsContract.isDocumentUri(context, uri)) {
                return false;
            }
            Cursor cursor = context.getContentResolver().query(
                    uri,
                    new String[]{DocumentsContract.Document.COLUMN_FLAGS},
                    null, null, null);
            int flags = 0;
            if (cursor.moveToFirst()) {
                flags = cursor.getInt(0);
            }
            cursor.close();
            return (flags & DocumentsContract.Document.FLAG_VIRTUAL_DOCUMENT) != 0;
        } else {
            return false;
        }
    }

    private static InputStream getInputStreamForVirtualFile(Context context, Uri uri, String mimeTypeFilter)
            throws IOException {

        ContentResolver resolver = context.getContentResolver();
        String[] openableMimeTypes = resolver.getStreamTypes(uri, mimeTypeFilter);
        if (openableMimeTypes == null || openableMimeTypes.length < 1) {
            throw new FileNotFoundException();
        }
        return resolver
                .openTypedAssetFileDescriptor(uri, openableMimeTypes[0], null)
                .createInputStream();
    }

    private static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public static boolean saveFile(Context context, String name, Uri sourceuri, String destinationDir, String destFileName) {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        InputStream input = null;
        boolean hasError = false;

        try {
            if (isVirtualFile(context, sourceuri)) {
                input = getInputStreamForVirtualFile(context, sourceuri, getMimeType(name));
            } else {
                input = context.getContentResolver().openInputStream(sourceuri);
            }

            boolean directorySetupResult;
            File destDir = new File(destinationDir);
            if (!destDir.exists()) {
                directorySetupResult = destDir.mkdirs();
            } else if (!destDir.isDirectory()) {
                directorySetupResult = replaceFileWithDir(destinationDir);
            } else {
                directorySetupResult = true;
            }

            if (!directorySetupResult) {
                hasError = true;
            } else {
                String destination = destinationDir + File.separator + destFileName;
                int originalsize = input.available();

                bis = new BufferedInputStream(input);
                bos = new BufferedOutputStream(new FileOutputStream(destination));
                byte[] buf = new byte[originalsize];
                bis.read(buf);
                do {
                    bos.write(buf);
                } while (bis.read(buf) != -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            hasError = true;
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (Exception ignored) {
            }
        }

        return !hasError;
    }

    private static boolean replaceFileWithDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return true;
            }
        } else if (file.delete()) {
            File folder = new File(path);
            if (folder.mkdirs()) {
                return true;
            }
        }
        return false;
    }

    public static boolean deleteImage(String fileName) {
        if(TextUtils.isEmpty(fileName)) {
            return false;
        }
        String filePath = returnLicenseFilePath() + "/" + fileName;
        boolean isDelete = false;
        try {
            File file = new File(filePath);
            if(file.exists()) {
                file.delete();
                isDelete = true;
            }
        } catch (Exception e) {

        } finally {
            return isDelete;
        }
    }

    public static String returnLicenseFilePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/CourtesyCar";
    }

    public static final int REQUEST_PERMISSION_WRITE_READ = 8;
    public static void askStoragePermission(Context context) {
        int permissionWrite = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCamera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);

        if (permissionRead == PackageManager.PERMISSION_DENIED || permissionWrite == PackageManager.PERMISSION_DENIED || permissionCamera == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= 23) {
                String[] permissions = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                ActivityCompat.requestPermissions((Activity) context, permissions, REQUEST_PERMISSION_WRITE_READ);
            }
        }
    }
}
