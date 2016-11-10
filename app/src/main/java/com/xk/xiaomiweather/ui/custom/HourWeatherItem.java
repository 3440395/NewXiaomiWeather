package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

/**
 * Created by xuekai on 2016/11/10.
 */

public class HourWeatherItem extends View {
    private int maxTemp = 50;//最高温度
    private int minTemp = 0;//最低温度
    private int currentTemp = 15;//当前温度
    private int lastTemp=10;//上一个温度
    private int nextTemp=10;//下一个温度
    private String time;//时间
    private Paint mPaint;
    private int viewHeight;
    private int viewWidth;
    private int pointTopY = 10;//最高点的Y坐标 130
    private int pointBottomY = 255;//最低点的Y坐标 255
    private int pointX;//所有点的x坐标
    private int pointY;//当前点的Y


    private boolean drawLeftLine = true;//是否画左边的线
    private boolean drawRightLine = true;//是否画右边的线

    public HourWeatherItem(Context context) {
        super(context);
        setBackgroundColor(0xffffffff);
        init();
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
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
        pointY = (int) ((pointBottomY - pointTopY) * 1f / (maxTemp - minTemp) * (maxTemp - currentTemp + minTemp) + pointTopY);

        drawTime(canvas);
        drawLine(canvas);
        drawPoint(canvas);
        drawGraph(canvas);
        drawTemp(canvas);
    }

    private void drawTemp(Canvas canvas) {
        mPaint.setTextSize(35);
        mPaint.setColor(Color.parseColor("#ff333333"));
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine1 = pointY - fontMetrics.bottom*4;
        canvas.drawText(currentTemp+"°", viewWidth / 2, baseLine1, mPaint);
    }

    public void setLastTemp(int lastTemp) {
        this.lastTemp = lastTemp;
    }

    public void setNextTemp(int nextTemp) {
        this.nextTemp = nextTemp;
    }

    /**
     * 画折线
     * @param canvas
     */
    private void drawGraph(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xff24C3F1);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        if (drawLeftLine) {
            float middleTemp =currentTemp-(currentTemp - lastTemp) / 2f;
            Log.e("HourWeatherItem","drawGraph"+middleTemp);
            float middleY = ((pointBottomY - pointTopY) * 1f / (maxTemp - minTemp) * (maxTemp - middleTemp + minTemp) + pointTopY);
            canvas.drawLine(0, middleY, pointX-15, pointY, mPaint);
        }
        if (drawRightLine) {
            float middleTemp =currentTemp-  (currentTemp - nextTemp) / 2f;
            float middleY = ((pointBottomY - pointTopY) * 1f / (maxTemp - minTemp) * (maxTemp - middleTemp + minTemp) + pointTopY);
            canvas.drawLine(pointX+15, pointY, viewWidth, middleY, mPaint);
        }
    }

    private void drawPoint(Canvas canvas) {
        mPaint.setColor(0xff24C3F1);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(pointX, pointY, 10, mPaint);
    }

    private void drawLine(Canvas canvas) {
        mPaint.setColor(0xffD3D3D3);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        if (drawLeftLine) {
            canvas.drawLine(0, 425, viewWidth / 2, 425, mPaint);
        }
        if (drawRightLine) {
            canvas.drawLine(viewWidth / 2, 425, viewWidth, 425, mPaint);
        }
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

//{weatherid='01',weather='多云',temp1='4',temp2='2',sh='05',eh='08',date='20161110',sfdate='05:00',efdate='20161110080000'},
//{weatherid='00',weather='晴',temp1='2',temp2='8',sh='08',eh='11',date='20161110',sfdate='08:00',efdate='20161110110000'},
//{weatherid='00',weather='晴',temp1='8',temp2='8',sh='11',eh='14',date='20161110',sfdate='11:00',efdate='20161110140000'},
//{weatherid='00',weather='晴',temp1='8',temp2='2',sh='14',eh='17',date='20161110',sfdate='14:00',efdate='20161110170000'},
//{weatherid='00',weather='晴',temp1='2',temp2='1',sh='17',eh='20',date='20161110',sfdate='17:00',efdate='20161110200000'},
//{weatherid='00',weather='晴',temp1='1',temp2='0',sh='20',eh='23',date='20161110',sfdate='20:00',efdate='20161110230000'},
//{weatherid='00',weather='晴',temp1='0',temp2='0',sh='23',eh='02',date='20161110',sfdate='23:00',efdate='20161111020000'},
//{weatherid='00',weather='晴',temp1='0',temp2='-1',sh='02',eh='05',date='20161111',sfdate='02:00',efdate='20161111050000'},
//{weatherid='00',weather='晴',temp1='-1',temp2='11',sh='05',eh='08',date='20161111',sfdate='05:00',efdate='20161111080000'},
//{weatherid='00',weather='晴',temp1='11',temp2='15',sh='08',eh='11',date='20161111',sfdate='08:00',efdate='20161111110000'},
//{weatherid='00',weather='晴',temp1='15',temp2='11',sh='11',eh='14',date='20161111',sfdate='11:00',efdate='20161111140000'},
//{weatherid='00',weather='晴',temp1='11',temp2='3',sh='14',eh='17',date='20161111',sfdate='14:00',efdate='20161111170000'},
//{weatherid='00',weather='晴',temp1='3',temp2='2',sh='17',eh='20',date='20161111',sfdate='17:00',efdate='20161111200000'},
//{weatherid='00',weather='晴',temp1='2',temp2='0',sh='20',eh='23',date='20161111',sfdate='20:00',efdate='20161111230000'},
//r{weatherid='00',weather='晴',temp1='0',temp2='-1',sh='23',eh='02',date='20161111',sfdate='23:00',efdate='20161112020000'},
//{weatherid='00',weather='晴',temp1='-1',temp2='-1',sh='02',eh='05',date='20161112',sfdate='02:00',efdate='20161112050000'},
//{weatherid='00',weather='晴',temp1='-1',temp2='11',sh='05',eh='08',date='20161112',sfdate='05:00',efdate='20161112080000'},
//{weatherid='00',weather='晴',temp1='11',temp2='15',sh='08',eh='11',date='20161112',sfdate='08:00',efdate='20161112110000'},
