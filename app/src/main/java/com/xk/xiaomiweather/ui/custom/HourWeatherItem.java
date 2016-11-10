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
    private int maxTemp;
    private int minTemp;
    private int currentTemp;
    private String time;
    private Paint mPaint;
    private int viewHeight;
    private int viewWidth;

    public HourWeatherItem(Context context) {
        super(context);
        setBackgroundColor(0xffffffff);
        init();
    }

    private void init() {
        mPaint=new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initValues();
    }

    private void initValues() {
        viewHeight=getMeasuredHeight();
        viewWidth=getMeasuredWidth();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawTime(canvas);
        drawLine(canvas);

    }

    private void drawLine(Canvas canvas) {
        mPaint.setColor(0xffD3D3D3);
        mPaint.setStrokeWidth(2);

        canvas.drawLine(0,425,viewWidth,425,mPaint);
    }

    /**
     * 总高度545 文字下边的距离60
     * @param canvas
     */
    private void drawTime(Canvas canvas) {
        mPaint.setTextSize(40);
        mPaint.setColor(Color.parseColor("#ff333333"));
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine1 = viewHeight/11f*10 -fontMetrics.bottom;
        canvas.drawText("16:00", viewWidth/2, baseLine1, mPaint);
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
