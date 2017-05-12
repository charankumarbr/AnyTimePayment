package in.anytimepayment.android.ui.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import in.anytimepayment.android.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}
