package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.xk.xiaomiweather.ui.util.ScreenManager;

import static android.R.attr.bottom;
import static android.R.attr.max;
import static android.R.attr.top;

/**
 * 未来几天温度曲线预报中的每一个item中的双曲线部分
 * Created by xuekai on 2016/11/14.
 */

public class DoubleTempGraphView extends View {
    private int maxTemp1;
    private int maxTemp2;
    private int minTemp1;
    private int minTemp2;
    private int currentTemp1;
    private int currentTemp2;
    private int lastTemp1;
    private int lastTemp2;
    private int nextTemp1;
    private int nextTemp2;
    private int topY1;
    private int topY2;
    private int bottomY1;
    private int bottomY2;
    private int currentY1;
    private int currentY2;
    private int lastPointY1;
    private int lastPointY2;
    private int nextPointY1;
    private int nextPointY2;
    private int viewHeight;
    private int viewWidth;
    private Paint paint;
    private boolean select = true;

    private boolean drawLeft = false;
    private boolean drawRight = false;

    public DoubleTempGraphView(Context context) {
        super(context);
        init();

    }

    public void setDrawRight(boolean drawRight) {
        this.drawRight = drawRight;
    }

    public void setDrawLeft(boolean drawLeft) {
        this.drawLeft = drawLeft;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void setData(int maxTemp1, int maxTemp2, int minTemp1, int minTemp2, int currentTemp1, int currentTemp2, int lastTemp1, int lastTemp2, int nextTemp1, int nextTemp2) {
        this.maxTemp1 = maxTemp1;
        this.maxTemp2 = maxTemp2;
        this.minTemp1 = minTemp1;
        this.minTemp2 = minTemp2;
        this.currentTemp1 = currentTemp1;
        this.currentTemp2 = currentTemp2;
        this.lastTemp1 = lastTemp1;
        this.lastTemp2 = lastTemp2;
        this.nextTemp1 = nextTemp1;
        this.nextTemp2 = nextTemp2;
    }

    public void setData1(int maxTemp1, int minTemp1, int currentTemp1, int lastTemp1, int nextTemp1) {
        this.maxTemp1 = maxTemp1;
        this.minTemp1 = minTemp1;
        this.currentTemp1 = currentTemp1;
        this.lastTemp1 = lastTemp1;
        this.nextTemp1 = nextTemp1;
        invalidate();
    }

    public void setData2(int maxTemp2, int minTemp2, int currentTemp2, int lastTemp2, int nextTemp2) {
        this.maxTemp2 = maxTemp2;
        this.minTemp2 = minTemp2;
        this.currentTemp2 = currentTemp2;
        this.lastTemp2 = lastTemp2;
        this.nextTemp2 = nextTemp2;
        invalidate();
    }

    private void initValue() {
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
        topY1 = (int) (viewHeight / 5f);
        bottomY1 = (int) (viewHeight / 5f * 2);
        topY2 = (int) (viewHeight / 5f * 3);
        bottomY2 = (int) (viewHeight / 5f * 4);

        currentY1 = (int) ((bottomY1 - topY1) * 1f / (maxTemp1 - minTemp1) * (maxTemp1 - currentTemp1) + topY1);
        currentY2 = (int) ((bottomY2 - topY2) * 1f / (maxTemp2 - minTemp2) * (maxTemp2 - currentTemp2) + topY2);

        lastPointY1 = (int) (currentY1 + (currentTemp1 - lastTemp1) / 2f * ((bottomY1 - topY1) * 1f / (maxTemp1 - minTemp1)));
        lastPointY2 = (int) (currentY2 + (currentTemp2 - lastTemp2) / 2f * ((bottomY2 - topY2) * 1f / (maxTemp2 - minTemp2)));

        nextPointY1 = (int) (currentY1 + (currentTemp1 - nextTemp1) / 2f * ((bottomY1 - topY1) * 1f / (maxTemp1 - minTemp1)));
        nextPointY2 = (int) (currentY2 + (currentTemp2 - nextTemp2) / 2f * ((bottomY2 - topY2) * 1f / (maxTemp2 - minTemp2)));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initValue();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        setData(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setColor(0xffC5C5C5);
        paint.setStrokeWidth(3);

        if (drawLeft) {
            canvas.drawLine(0, lastPointY1, viewWidth / 2, currentY1, paint);
            canvas.drawLine(0, lastPointY2, viewWidth / 2, currentY2, paint);
        }
        if (drawRight) {
            canvas.drawLine(viewWidth / 2, currentY1, viewWidth, nextPointY1, paint);
            canvas.drawLine(viewWidth / 2, currentY2, viewWidth, nextPointY2, paint);
        }
        drawTemp(canvas);

        drawPoint(canvas);
    }

    private void drawPoint(Canvas canvas) {
        if (select) {
            paint.setColor(0xfffafafa);
        } else {
            paint.setColor(0xffffffff);
        }

        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(viewWidth / 2, currentY1, 15, paint);
        canvas.drawCircle(viewWidth / 2, currentY2, 15, paint);
        paint.setColor(0xffC5C5C5);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(viewWidth / 2, currentY1, 10, paint);
        canvas.drawCircle(viewWidth / 2, currentY2, 10, paint);
    }

    private void drawTemp(Canvas canvas) {
        paint.setTextSize(ScreenManager.getInstance().adpH(35));
        paint.setColor(Color.parseColor("#ff333333"));
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baseLine1 = currentY1 - fontMetrics.bottom * 4;
        canvas.drawText(currentTemp1+"°", viewWidth / 2, baseLine1, paint);
        float baseLine2 = currentY2 +fontMetrics.bottom * 6;
        canvas.drawText(currentTemp2+"°", viewWidth / 2, baseLine2, paint);
    }
}
