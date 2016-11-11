package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by xuekai on 2016/11/10.
 */

public class AQIItem extends View {
    private int maxAQI = 50;//最高温度
    private int minAQI = 0;//最低温度
    private int currentAQI = 15;//当前温度
    private int lastAQI = 10;//上一个温度
    private int nextAQI = 10;//下一个温度
    private String time;//时间
    private Paint mPaint;
    private int viewHeight;
    private int viewWidth;
    private int pointTopY = 240;//最高点的Y坐标 130
    private int pointBottomY = 260;//最低点的Y坐标 255
    private int pointX;//所有点的x坐标
    private int pointY;//当前点的Y

    private boolean drawLeftLine = true;//是否画左边的线
    private boolean drawRightLine = true;//是否画右边的线


    public AQIItem(Context context) {
        super(context);
        setBackgroundColor(0xffffffff);
        init();
    }

    public void setCurrentAQI(int currentAQI) {
        this.currentAQI = currentAQI;
        invalidate();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initValues();
    }

    private void initValues() {
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
        pointX = viewWidth / 2;

    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        pointY = (int) ((pointBottomY - pointTopY) * 1f / (maxAQI - minAQI) * (maxAQI- currentAQI+ minAQI) + pointTopY);

        drawTime(canvas);
        drawLine(canvas);
        drawGraph(canvas);
        drawPoint(canvas);
        drawAQI(canvas);
    }


    private void drawAQI(Canvas canvas) {
        mPaint.setTextSize(35);
        mPaint.setColor(Color.parseColor("#ff333333"));
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine1 = pointY - fontMetrics.bottom * 4;
        canvas.drawText(currentAQI+"", viewWidth / 2, baseLine1, mPaint);
    }

    public void setLastTemp(int lastAQI) {
        this.lastAQI = lastAQI;
    }

    public void setNextAQI(int nextAQI) {
        this.nextAQI = nextAQI;
    }

    /**
     * 画折线
     *
     * @param canvas
     */
    private void drawGraph(Canvas canvas) {
        mPaint.setPathEffect(null);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xff24C3F1);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        if (drawLeftLine) {
            float middleAQI = currentAQI - (currentAQI - lastAQI) / 2f;
            float middleY = ((pointBottomY - pointTopY) * 1f / (maxAQI -minAQI) * (maxAQI- middleAQI + minAQI) + pointTopY);
            canvas.drawLine(0, middleY, pointX, pointY, mPaint);
        }
        if (drawRightLine) {
            float middleAQI = currentAQI - (currentAQI - nextAQI) / 2f;
            float middleY = ((pointBottomY - pointTopY) * 1f / (maxAQI - minAQI) * (maxAQI - middleAQI + minAQI) + pointTopY);
            canvas.drawLine(pointX, pointY, viewWidth, middleY, mPaint);
        }
    }

    private void drawPoint(Canvas canvas) {
        mPaint.setColor(0xffffffff);
        mPaint.setPathEffect(null);

        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(pointX, pointY, 15, mPaint);
        mPaint.setColor(0xff24C3F1);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(pointX, pointY, 10, mPaint);
    }

    private void drawLine(Canvas canvas) {
        mPaint.setColor(0xffD3D3D3);
        mPaint.setPathEffect(null);

        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
            canvas.drawLine(0, 300, viewWidth / 2, 300, mPaint);
            canvas.drawLine(viewWidth / 2, 300, viewWidth, 300, mPaint);
    }

    public void setDrawLeftLine(boolean drawLeftLine) {
        this.drawLeftLine = drawLeftLine;
    }

    public void setDrawRightLine(boolean drawRightLine) {
        this.drawRightLine = drawRightLine;
    }

    /**
     * 总高度545 文字下边的距离60
     *
     * @param canvas
     */
    private void drawTime(Canvas canvas) {
        mPaint.setTextSize(40);
        mPaint.setColor(Color.parseColor("#ff333333"));
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine1 = viewHeight / 11f * 10 - fontMetrics.bottom;
        canvas.drawText(time, viewWidth / 2, baseLine1, mPaint);
    }
}

