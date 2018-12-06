package vn.mtouch.courtesycar.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 14617 on 22/1/2018.
 */

public class SharePreferenceManager {

    public static void putString(Context context, String key, String value){
        putString(context, ConstantsPrefs.KEY_SHARED, key, value);
    }

    public static String getString(Context context, String key){
        return getString(context, ConstantsPrefs.KEY_SHARED, key, "");
    }

    public static String getString(Context context, String key, String strDefault){
        return getString(context, ConstantsPrefs.KEY_SHARED, key, strDefault);
    }

    public static void putString(Context context, String keyShared, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(keyShared, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String keyShare, String key, String defValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(keyShare, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defValue);
    }

    public static void putLong(Context context, String key, long value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsPrefs.KEY_SHARED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLong(Context context, String key, long defValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsPrefs.KEY_SHARED, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defValue);
    }

    public static void putBool(Context context, String key, boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsPrefs.KEY_SHARED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBool(Context context, String key, boolean defValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsPrefs.KEY_SHARED, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void putInt(Context context, String key, int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsPrefs.KEY_SHARED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String key, int defValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConstantsPrefs.KEY_SHARED, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defValue);
    }
}
