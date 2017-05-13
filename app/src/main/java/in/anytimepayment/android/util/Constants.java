package in.anytimepayment.android.util;

import in.anytimepayment.android.BuildConfig;

/**
 * Created by Charan.Br on 5/12/2017.
 */

public interface Constants {

    int RC_BARCODE_CAPTURE = 4349;
    int MOBILE_NUMBER_LENGTH = 10;

    String BARCODE_SEPARATOR = "#";
    String BARCODE_PREFIX = "ATP-Android#";
    String EVENT_BARCODE_SCANNED = BuildConfig.APPLICATION_ID + BARCODE_SEPARATOR + "barcodeScanEvent";

    String BARCODE_SCAN_VALUE = "barcodeScanValue";
    String BARCODE_SCAN_STATUS = "barcodeScanStatus";

    String FLOAT_FORMAT = "%.02f";
    String AMOUNT = "amount";
    String PREFERENCE_NAME = BuildConfig.APPLICATION_ID + ".prefs";
    String RECEIVER = "receiver";

    interface PrefConstants {
        String MOBILE_NUMBER = "mobileNumber";
    }
}
