package in.anytimepayment.android.util;

import android.util.Log;

import in.anytimepayment.android.BuildConfig;

/**
 * Created by Charan.Br on 5/12/2017.
 */

public final class AppLog {

    public static void debug(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void debug(String tag, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message, throwable);
        }
    }

    public static void warn(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, message);
        }
    }

    public static void info(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void error(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void error(String tag, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message, throwable);
        }
    }
}
