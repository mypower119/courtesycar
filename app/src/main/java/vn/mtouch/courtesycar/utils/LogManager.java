package vn.mtouch.courtesycar.utils;

import android.util.Log;

/**
 * Created by 14617 on 22/1/2018.
 */

public final class LogManager {
    private static final boolean DEBUG = true;
    private static final boolean INFO = true;
    private static final boolean VERBOSE = true;
    private static final boolean ERROR = true;
    private static final String TITLE_DEBUG = "DEBUG_ECOLA ";
    private static final String TITLE_INFO = "INFO_ECOLA ";
    private static final String TITLE_VERBOSE = "VERBOSE_ECOLA ";
    private static final String TITLE_ERROR = "ERROR_ECOLA ";

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(TITLE_DEBUG + tag, msg + "");
        }
    }

    public static void i(String tag, String msg) {
        if (INFO) {
            Log.i(TITLE_INFO + tag, msg + "");
        }
    }

    public static void v(String tag, String msg) {
        if (VERBOSE) {
            Log.d(TITLE_VERBOSE + tag, msg + "");
        }
    }

    public static void e(String tag, String msg) {
        if(ERROR) {
            Log.e(TITLE_ERROR + tag, msg + "");
        }
    }
}
