package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import com.xk.xiaomiweather.model.bean.Weather;
import com.xk.xiaomiweather.ui.IVUpdateable;

import static java.lang.Integer.parseInt;

/**
 * Created by xuekai on 2016/11/10.
 */

public class DialView extends View {

    private   int MAIN_COLOR = Color.parseColor("#ff53C21B");

    private int minProgress = 0;
    private int maxProgress = 100;

    /**
     * view的实际宽度
     */
    private int viewWidth;

    /**
     * 内容中心的坐标
     */
    private int[] centerPoint = new int[2];


    private Paint mPaint;

    private int radius = 0;
    private int values=0;
    private String intro="";
    private String des="";

    public DialView(Context context) {
        super(context);
        init();
    }

    public void updata(String values, String intro, String des, int min, int max) {
        this.values = (int) Float.parseFloat(values);
        this.intro = intro;
        this.des = des;
        this.minProgress = min;
        this.maxProgress = max;
        invalidate();
    }

    public void setMinProgress(int minProgress) {
        this.minProgress = minProgress;
        invalidate();
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initValues();
    }

    private void initValues() {
        viewWidth = getRight() - getLeft();
        centerPoint[0] = viewWidth / 2;
        centerPoint[1] = viewWidth / 2;
        radius = viewWidth / 2 - 5;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initValues();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(values<=100&&values>=0){
            MAIN_COLOR = Color.parseColor("#ff53C21B");
        }else if(values<=200&&values>100){
            MAIN_COLOR = Color.parseColor("#ffF09837");
        }else if(values<=300&&values>200){
            MAIN_COLOR = Color.parseColor("#ffFF4401");
        }else{
            MAIN_COLOR = Color.parseColor("#FFB10209");
        }
        drawArcBg(canvas);
        drawArcProgress(canvas);
        drawValues(canvas);
        drawIntro(canvas);
        drawDes(canvas);
    }

    private void drawDes(Canvas canvas) {
        mPaint.setTextSize(40);
        mPaint.setColor(Color.parseColor("#ff8E8D8D"));
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine1 = centerPoint[1] - (fontMetrics.top - fontMetrics.bottom) * 3;
        canvas.drawText(des, centerPoint[0], baseLine1, mPaint);
    }

    private void drawIntro(Canvas canvas) {
        mPaint.setTextSize(45);
        mPaint.setColor(Color.parseColor("#ff9D9D9D"));
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine1 = centerPoint[1] - (fontMetrics.top - fontMetrics.bottom);
        canvas.drawText(intro, centerPoint[0], baseLine1, mPaint);
    }

    private void drawValues(Canvas canvas) {
        mPaint.setTextSize(95);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        float baseLine1 = centerPoint[1];
        canvas.drawText(values + "", centerPoint[0], baseLine1, mPaint);

    }

    private void drawArcProgress(Canvas canvas) {
        if (values>maxProgress) {
            values=maxProgress;
        }
        float allProgress = 270f / (maxProgress - minProgress) * values;
        mPaint.setColor(MAIN_COLOR);
        RectF rectF = new RectF(centerPoint[0] - radius, centerPoint[1] - radius, centerPoint[0] + radius, centerPoint[1] + radius);
        canvas.drawArc(rectF, 135, allProgress, false, mPaint);
    }

    private void drawArcBg(Canvas canvas) {

        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#ffEAEAEA"));
        RectF rectF = new RectF(centerPoint[0] - radius, centerPoint[1] - radius, centerPoint[0] + radius, centerPoint[1] + radius);
        canvas.drawArc(rectF, 135, 270, false, mPaint);
    }
}
