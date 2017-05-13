package in.anytimepayment.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import in.anytimepayment.android.AnyTimePayment;

/**
 * Created by Charan.Br on 5/13/2017.
 */

public final class AppPrefManager {

    private SharedPreferences mSharedPrefs;

    private static AppPrefManager APP_PREF_MANAGER;

    private AppPrefManager() {
        mSharedPrefs = AnyTimePayment.getAppContext().getSharedPreferences(Constants.PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    public static AppPrefManager getInstance() {
        if (null == APP_PREF_MANAGER) {
            APP_PREF_MANAGER = new AppPrefManager();
        }
        return APP_PREF_MANAGER;
    }

    public void set(String key, String value) {
        mSharedPrefs.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return mSharedPrefs.getString(key, null);
    }

    public void set(String key, int value) {
        mSharedPrefs.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return mSharedPrefs.getInt(key, -1);
    }
}
