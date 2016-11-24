package com.bwf.yiqizhuangxiu.utils.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Gravity;
import android.widget.CheckedTextView;

/**
 * Created by Administrator on 2016/10/20.
 */

public class IndicatorDefultView extends CheckedTextView {
    private boolean isHasSelector = false;
    private Paint paint;
    private float width;
    private float height;
    private float radius;

    public void setHasSelector(boolean hasSelector) {
        isHasSelector = hasSelector;
    }

    public IndicatorDefultView(Context context) {
        super(context);
        this.setGravity(Gravity.CENTER);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xff888888);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isHasSelector)
            return;
        if (width == 0) {
            width = getWidth();
            height = getHeight();
            radius = (width > height ? height : width - 4) / 2;
        }
        if (isChecked()) {
            paint.setColor(0xff888888);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(width / 2, height / 2, radius, paint);
        } else {
            paint.setColor(0xffffffff);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(width / 2, height / 2, radius, paint);
//            paint.setStrokeWidth(4);
//            canvas.drawCircle(width / 2, height / 2, radius - 2, paint);
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (isChecked() != checked) {
            invalidate();
        }
        super.setChecked(checked);
    }

}
