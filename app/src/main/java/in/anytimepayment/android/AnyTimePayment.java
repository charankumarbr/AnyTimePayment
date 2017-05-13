package in.anytimepayment.android;

import android.app.Application;
import android.content.Context;

import in.anytimepayment.android.util.AppPrefManager;
import in.anytimepayment.android.util.Constants;

/**
 * Created by Charan.Br on 5/12/2017.
 */

public final class AnyTimePayment extends Application {

    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        AppPrefManager.getInstance().set(Constants.PrefConstants.MOBILE_NUMBER, "+919611701061");
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
