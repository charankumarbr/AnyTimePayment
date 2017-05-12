package in.anytimepayment.android;

import android.app.Application;
import android.content.Context;

/**
 * Created by Charan.Br on 5/12/2017.
 */

public final class AnyTimePayment extends Application {

    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
