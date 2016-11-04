package com.xk.xiaomiweather.ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ScreenManager {

	private static ScreenManager myScreenUtils = null;
	private Context mContext = null;

	private ScreenManager(){
	}

	private int screenHeigth = 0;
	private int screenWidth = 0;
	private float density = 0;

	public int getScreenHeigth() {
		return screenHeigth;
	}

	/**
	 * 获取屏幕的宽度
	 * @return 返回屏幕的宽度
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * 获取屏幕的高度
	 * @return 返回屏幕的高度
	 */
	public float getDensity(){
		return density;
	}

	/**
	 * MyScreenUtils 获取该工具类的単例
	 * @return MyScreenUtils
	 */
	public static ScreenManager getInstance(){
		if(myScreenUtils == null){
			myScreenUtils = new ScreenManager();
		}
		return myScreenUtils;
	}

	public void initScreenUtils(Context context){
		this.mContext = context;
		getScreen();
	}

	private void getScreen(){
		Resources resources = mContext.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		density = dm.density;
		screenWidth = dm.widthPixels;
		screenHeigth = dm.heightPixels;
		if(screenHeigth == 672){ // 这是个坑，有些盒子就是这么变态，明明高度是720，非得给我返回672，你说2b不2b
			screenHeigth = 720;
		}
	}

	/**
	 * 计算字体大小
	 * @param currentSize 输入UI效果图上的字体大小(按照1080P来切图)
	 * @return 实际需要设置的宽度
	 */
	public int getTextSize(int currentSize){
		if(screenWidth >= 1080){
			return factHeightProportion((int)(currentSize/density));
		}else if(screenWidth < 1080){
			return factHeightProportion(currentSize);
		}
		return currentSize;
	}

	/**
	 * 计算宽度比例
	 * @param currentWidth 输入UI效果图上的宽度(按照1080P来切图)
	 * @return 实际需要设置的宽度
	 */
	public int factWidthProportion(int currentWidth){
		return currentWidth*screenWidth/1920;
	}

	/**
	 * 计算宽度比例
	 * @param currentHeight 输入UI效果图上的高度(按照1080P来切图)
	 * @return 实际需要设置的高度
	 */
	public int factHeightProportion(int currentHeight){
		return currentHeight*screenHeigth/1080;
	}
}
