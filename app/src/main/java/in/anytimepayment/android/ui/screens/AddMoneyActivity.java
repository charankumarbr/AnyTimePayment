package in.anytimepayment.android.ui.screens;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import in.anytimepayment.android.R;
import in.anytimepayment.android.util.AppUtil;

/**
 * Created by Charan.Br on 5/13/2017.
 */

public class AddMoneyActivity extends BaseActivity implements View.OnClickListener {

    private View mVLayoutAddMoney;
    private View mVLayoutAddFrom;

    private EditText mEtAddMoney;
    private AppCompatButton mBtnAddMoney;

    private EditText mEtCardNumber;
    private EditText mEtCVV;
    private EditText mEtExpiry;
    private AppCompatButton mBtnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        mVLayoutAddMoney = findViewById(R.id.aam_layout_add_money);
        mVLayoutAddFrom = findViewById(R.id.aam_layout_add_from);

        mEtAddMoney = (EditText) findViewById(R.id.aam_edittext_money);
        mBtnAddMoney = (AppCompatButton) findViewById(R.id.aam_button_add_money);

        mEtCardNumber = (EditText) findViewById(R.id.aam_edittext_card_number);
        mEtCVV = (EditText) findViewById(R.id.aam_edittext_cvv);
        mEtExpiry = (EditText) findViewById(R.id.aam_edittext_expiry);
        mBtnConfirm = (AppCompatButton) findViewById(R.id.aam_button_confirm_add);

        displayView(true);
    }

    private void displayView(boolean acceptMoney) {
        if (acceptMoney) {
            mVLayoutAddMoney.setVisibility(View.VISIBLE);
            mVLayoutAddFrom.setVisibility(View.GONE);

        } else {
            mVLayoutAddMoney.setVisibility(View.GONE);
            mVLayoutAddFrom.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aam_button_add_money:
                break;

            case R.id.aam_button_confirm_add:
                if (AppUtil.isConnected()) {

                } else {
                    AppUtil.showSnackbar(mRootView, getString(R.string.no_internet));
                }
                break;
        }
    }
}