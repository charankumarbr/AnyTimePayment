package in.anytimepayment.android.ui.screens;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import in.anytimepayment.android.R;
import in.anytimepayment.android.util.Constants;
import in.anytimepayment.android.vision.BarcodeHelper;

/**
 * Created by Charan.Br on 5/13/2017.
 */

public final class DisplayBarcodeActivity extends AppCompatActivity {

    private ImageView mIvBarcode;
    private TextView mTvAmount;
    private TextView mTvReceiver;

    private String mAmount;
    private String mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_barcode);

        init();
    }

    private void init() {

        if (getIntent().hasExtra(Constants.AMOUNT) && getIntent().hasExtra(Constants.RECEIVER)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mTvAmount = (TextView) findViewById(R.id.agb_textview_amount);
            mIvBarcode = (ImageView) findViewById(R.id.agb_imageview_bitmap);
            mTvReceiver = (TextView) findViewById(R.id.agb_textview_to);

            mAmount = getIntent().getStringExtra(Constants.AMOUNT);
            mTvAmount.setText(getString(R.string.Rs) + " " + mAmount);
            mReceiver = getIntent().getStringExtra(Constants.RECEIVER);
            mTvReceiver.setText(mReceiver);

            generateBarcode();

        } else {
            onDestroy();
        }
    }

    private void generateBarcode() {
        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... params) {
                Bitmap bitmap = null;

                try {
                    bitmap = BarcodeHelper.generateBarcode(mReceiver, mAmount);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if (null != bitmap) {
                    mIvBarcode.setImageBitmap(bitmap);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DisplayBarcodeActivity.this);

        alertDialogBuilder.setTitle("Confirm");
        alertDialogBuilder.setMessage("Are you sure you are done with this transaction?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialogBuilder.create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}