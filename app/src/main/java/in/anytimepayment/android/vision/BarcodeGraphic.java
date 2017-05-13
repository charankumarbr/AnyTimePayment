/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.anytimepayment.android.vision;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.LocalBroadcastManager;

import in.anytimepayment.android.AnyTimePayment;
import in.anytimepayment.android.ui.camera.GraphicOverlay;
import in.anytimepayment.android.util.AppLog;
import in.anytimepayment.android.util.AppUtil;
import in.anytimepayment.android.util.Constants;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Graphic instance for rendering barcode position, size, and ID within an associated graphic
 * overlay view.
 */
public class BarcodeGraphic extends GraphicOverlay.Graphic {

    private int mId;

    private static final int COLOR_CHOICES[] = {
            Color.BLUE,
            Color.CYAN,
            Color.GREEN
    };

    private static int mCurrentColorIndex = 0;

    private Paint mRectPaint;
    private Paint mTextPaint;
    private volatile Barcode mBarcode;

    BarcodeGraphic(GraphicOverlay overlay) {
        super(overlay);

        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
        final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];

        mRectPaint = new Paint();
        mRectPaint.setColor(selectedColor);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(4.0f);

        mTextPaint = new Paint();
        mTextPaint.setColor(selectedColor);
        mTextPaint.setTextSize(36.0f);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public Barcode getBarcode() {
        return mBarcode;
    }

    /**
     * Updates the barcode instance from the detection of the most recent frame.  Invalidates the
     * relevant portions of the overlay to trigger a redraw.
     */
    void updateItem(Barcode barcode) {
        mBarcode = barcode;
        postInvalidate();
    }

    /**
     * Draws the barcode annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        Barcode barcode = mBarcode;
        if (barcode == null) {
            return;
        }

        // Draws the bounding box around the barcode.
        /*RectF rect = new RectF(barcode.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, mRectPaint);*/

        // Draws a label at the bottom of the barcode indicate the barcode value that was detected.
        //canvas.drawText(barcode.rawValue, rect.left, rect.bottom, mTextPaint);

        AppLog.debug(getClass().getSimpleName(), barcode.rawValue);
        String barcodeValue = barcode.rawValue;
        Intent barcodeIntent = new Intent(Constants.EVENT_BARCODE_SCANNED);
        if (barcodeValue.startsWith(Constants.BARCODE_PREFIX) && barcodeValue.contains(Constants.BARCODE_SEPARATOR)) {
            barcodeIntent.putExtra(Constants.BARCODE_SCAN_VALUE, barcodeValue);
            barcodeIntent.putExtra(Constants.BARCODE_SCAN_STATUS, CommonStatusCodes.SUCCESS);

        } else {
            barcodeIntent.putExtra(Constants.BARCODE_SCAN_STATUS, CommonStatusCodes.ERROR);
        }
        LocalBroadcastManager.getInstance(AnyTimePayment.getAppContext()).sendBroadcast(barcodeIntent);
    }
}
