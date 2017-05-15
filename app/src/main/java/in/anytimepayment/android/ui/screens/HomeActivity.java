package in.anytimepayment.android.ui.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;

import in.anytimepayment.android.R;
import in.anytimepayment.android.customview.MoneyValueFilter;
import in.anytimepayment.android.util.AppLog;
import in.anytimepayment.android.util.AppUtil;
import in.anytimepayment.android.util.Constants;
import in.anytimepayment.android.vision.BarcodeCaptureActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText mTietAmount;
    private TextInputLayout mTilAmount;
    private TextInputEditText mTietMobileNumber;
    private TextInputLayout mTilMobileNumber;

    private TextView mTvWalletBalance;

    private AppCompatButton mBtnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {
        mRootView = findViewById(R.id.ah_layout_complete);
        findViewById(R.id.ah_textview_receive_money).setOnClickListener(this);
        mBtnPay = (AppCompatButton) findViewById(R.id.ah_button_pay);
        mBtnPay.setOnClickListener(this);

        mTvWalletBalance = (TextView) findViewById(R.id.ah_textview_balance);
        mTvWalletBalance.setOnClickListener(this);

        mTietAmount = (TextInputEditText) findViewById(R.id.ah_tiet_amount);
        mTilAmount = (TextInputLayout) findViewById(R.id.ah_til_amount);
        InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(8);
        mTietAmount.setFilters(new InputFilter[]{new MoneyValueFilter(), lengthFilter});
        mTietAmount.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            mBtnPay.performClick();
                            return true;
                        }
                        return false;
                    }
                }
        );

        mTietMobileNumber = (TextInputEditText) findViewById(R.id.ah_tiet_mobile_number);
        mTilMobileNumber = (TextInputLayout) findViewById(R.id.ah_til_mobile_number);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ah_textview_receive_money:
                Intent intent = new Intent(this, BarcodeCaptureActivity.class);
                intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                intent.putExtra(BarcodeCaptureActivity.UseFlash, false);
                startActivityForResult(intent, Constants.RC_BARCODE_CAPTURE);
                break;

            case R.id.ah_button_pay:
                if (isValid()) {
                    if (canProceedToPay()) {
                        Intent prepareBarcodeIntent = new Intent(HomeActivity.this, DisplayBarcodeActivity.class);
                        prepareBarcodeIntent.putExtra(Constants.AMOUNT, AppUtil.getStringAmount(mTietAmount.getText().toString()));
                        prepareBarcodeIntent.putExtra(Constants.RECEIVER, mTietMobileNumber.getText().toString());
                        startActivity(prepareBarcodeIntent);

                    } else {

                    }
                } else {

                }
                break;

            case R.id.ah_textview_balance:
                if (AppUtil.isConnected()) {
                    startActivity(new Intent(HomeActivity.this, AddMoneyActivity.class));

                } else {
                    AppUtil.showSnackbar(mRootView, getString(R.string.no_internet));
                }
                break;
        }
    }

    private boolean isValid() {

        boolean validStatus = true;

        if (TextUtils.isEmpty(mTietMobileNumber.getText().toString())) {
            mTilMobileNumber.setErrorEnabled(true);
            mTilMobileNumber.setError("Mobile number is required");
            validStatus = false;

        } else if (mTietMobileNumber.getText().toString().length() != Constants.MOBILE_NUMBER_LENGTH) {
            mTilMobileNumber.setErrorEnabled(true);
            mTilMobileNumber.setError("Mobile number is not valid");
            validStatus = false;

        } else {
            mTilMobileNumber.setErrorEnabled(false);
        }

        if (TextUtils.isEmpty(mTietAmount.getText())) {
            mTilAmount.setErrorEnabled(true);
            mTilAmount.setError(getString(R.string.amount_is_required));
            validStatus = false;

        } else if (!AppUtil.isValidAmount(mTietAmount.getText().toString())) {
            mTilAmount.setErrorEnabled(true);
            mTilAmount.setError(getString(R.string.amount_is_not_valid));
            validStatus = false;

        } else {
            mTilAmount.setErrorEnabled(false);
        }

        if (validStatus) {
            AppUtil.hideKeyboard();
        }

        return validStatus;
    }

    private boolean canProceedToPay() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS ) {
                if (null != data) {
                    //Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    String barcodeValue = data.getStringExtra(BarcodeCaptureActivity.BarcodeObject);
                    AppLog.debug("Barcode", "Barcode read: " + barcodeValue);

                } else {
                    //statusMessage.setText(R.string.barcode_failure);
                    AppLog.debug("Barcode", "No barcode captured, intent data is null");
                }
            } else {
                AppUtil.showSnackbar(mRootView, getString(R.string.barcode_error));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profile:
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                return true;

            case R.id.menu_about_app:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
