package in.anytimepayment.android.ui.screens;

import android.os.Bundle;

import in.anytimepayment.android.R;

/**
 * Created by Charan.Br on 5/13/2017.
 */

public class AddMoneyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
