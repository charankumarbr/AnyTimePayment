package in.anytimepayment.android.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import in.anytimepayment.android.AnyTimePayment;

/**
 * Created by Charan.Br on 5/12/2017.
 */

public final class AppUtil {

    public static boolean isConnected() {
        return true;
    }

    public static int requiredWidth() {
        int widthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;
        float densityPixels = Resources.getSystem().getDisplayMetrics().density * 16;
        AppLog.debug("Barcode", "Width:" + widthPixels + "::Margin:" + densityPixels);
        return (int) (widthPixels - (densityPixels * 2));
    }

    public static String getStringAmount(String amountInString) throws NumberFormatException {
        Float amount = Float.valueOf(amountInString);
        return String.format(Constants.FLOAT_FORMAT, amount);
    }

    public static boolean isValidAmount(String amountInString) {
        if (null == amountInString) {
            return false;
        }

        Float amt = Float.valueOf(amountInString);
        return (amt != 0f);
    }

    public static void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) AnyTimePayment.getAppContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static void showSnackbar(View view, String message) {
        Snackbar.make(view, message,
                Snackbar.LENGTH_LONG).show();
    }
}
