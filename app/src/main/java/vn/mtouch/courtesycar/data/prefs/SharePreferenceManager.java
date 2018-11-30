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
}
