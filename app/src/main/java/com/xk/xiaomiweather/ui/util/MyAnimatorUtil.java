package com.xk.xiaomiweather.ui.util;


import android.R.anim;
import android.animation.PropertyValuesHolder;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyAnimatorUtil {

	
	/**
	 * 左右移动时的动画时间
	 */
	private static final int LEFT_RIGHT_ANIMATION_DURATION = 300;

	/**
	 * 上下移动时的动画时间
	 */
	private static final int TOP_DOWN_ANIMATION_DURATION = 300;

	/**
	 * 平移动画
	 * 
	 * @param view
	 *            需要平移的view
	 * @param start
	 *            平移的开始X坐标
	 * @param end
	 *            平移的开始X坐标
	 * @param animationEnd
	 *            移动结束的回调
	 */
	@SuppressLint("NewApi")
	public static void TranslationX(final View view, long start,
			final long end, final AnimationEnd animationEnd) {

		ObjectAnimator oAnimator = ObjectAnimator.ofFloat(view, "translationX",
				start, end);
		oAnimator.setInterpolator(new DecelerateInterpolator());
		oAnimator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (animationEnd != null)
					animationEnd.endCallback();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
		oAnimator.setDuration(LEFT_RIGHT_ANIMATION_DURATION);
		oAnimator.start();
	}
/**
 * 
 * @param v
 * @param startAlph 0x_ _000000 空格处是00-99之间的数字
 * @param endAlph 0x_ _000000 空格处是00-99之间的数字
 * @param color 0x00_ _ _ _ _ _ 
 * @param duration 
 */
	public static void alphaBackground(final View v,int startAlph,int endAlph,final int color,final int duration){
		ValueAnimator ofInt = ValueAnimator.ofInt(startAlph,endAlph);
		ofInt.setDuration(duration);
		ofInt.addUpdateListener(new  AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int values=(Integer)animation.getAnimatedValue();
				
					int newColor=color+(values>>24<<24);				
					v.setBackgroundColor(newColor);
					System.out.println(newColor);
			}
			
		});
		ofInt.start();
		
	}
	
	/**
	 * 平移动画
	 * 
	 * @param view
	 *            需要平移的view
	 * @param start
	 *            平移的开始X坐标
	 * @param end
	 *            平移的开始X坐标
	 * @param duration
	 *            移动的时间
	 * @param animationEnd
	 *            移动结束的回调
	 */
	public static void TranslationX(final View view, long start,
			final long end, final int duration, final AnimationEnd animationEnd) {

		ObjectAnimator oAnimator = ObjectAnimator.ofFloat(view, "translationX",
				start, end);
		oAnimator.setInterpolator(new DecelerateInterpolator());
		oAnimator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (animationEnd != null)
					animationEnd.endCallback();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
		oAnimator.setDuration(duration);
		oAnimator.start();
	}

	/**
	 * 平移动画
	 * 
	 * @param view
	 *            需要平移的view
	 * @param start
	 *            平移的开始Y坐标
	 * @param end
	 *            平移的开始Y坐标
	 * @param animationEnd
	 *            移动结束的回调
	 */
	public static void TranslationY(final View view, float start,
			final float end, final AnimationEnd animationEnd) {

		
		
		ObjectAnimator oAnimator = ObjectAnimator.ofFloat(view, "translationY",
				start, end);
		oAnimator.setInterpolator(new DecelerateInterpolator());
		oAnimator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (animationEnd != null)
					animationEnd.endCallback();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
		oAnimator.setDuration(TOP_DOWN_ANIMATION_DURATION);
		oAnimator.start();
	}

	/**
	 * 平移动画
	 * 
	 * @param view
	 *            需要平移的view
	 * @param start
	 *            平移的开始Y坐标
	 * @param end
	 *            平移的开始Y坐标
	 * @param animationEnd
	 *            移动结束的回调
	 */
	public static void TranslationY(final View view, long start,
			final long end, final int duration, final AnimationEnd animationEnd) {
		// view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		ObjectAnimator oAnimator = ObjectAnimator.ofFloat(view, "translationY",
				start, end);
		oAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
		oAnimator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (animationEnd != null)
					animationEnd.endCallback();
				// view.setLayerType(View.LAYER_TYPE_NONE, null);
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
		oAnimator.setDuration(duration);
		oAnimator.start();
	}

	/**
	 * 渐隐渐显 消失view
	 * 
	 * @param view
	 *            要消失的view
	 * @param start
	 *            开始的透明度
	 * @param end
	 *            结束时的透明度
	 */
	public static void Alpha(final View view, float start, float end) {
		ValueAnimator fadeAnim1 = ObjectAnimator.ofFloat(view, "alpha", start,
				end);
		fadeAnim1.setDuration(1200);
		fadeAnim1.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator arg0) {
			}

			@Override
			public void onAnimationRepeat(Animator arg0) {
			}

			@Override
			public void onAnimationEnd(Animator arg0) {
			}

			@Override
			public void onAnimationCancel(Animator arg0) {
			}
		});
		fadeAnim1.start();
	}

	/**
	 * 渐隐渐显 消失view
	 * 
	 * @param view
	 *            要消失的view
	 * @param start
	 *            开始的透明度
	 * @param end
	 *            结束时的透明度
	 */
	public static void Alpha(final View view, float start, float end,
			int duration) {
		ValueAnimator fadeAnim1 = ObjectAnimator.ofFloat(view, "alpha", start,
				end);
		fadeAnim1.setDuration(duration);
		fadeAnim1.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator arg0) {
			}

			@Override
			public void onAnimationRepeat(Animator arg0) {
			}

			@Override
			public void onAnimationEnd(Animator arg0) {
			}

			@Override
			public void onAnimationCancel(Animator arg0) {
			}
		});
		fadeAnim1.start();
	}

	public static void Alpha(final View view, float start, float end,
			int duration, final AnimationEnd animationEnd) {
		ValueAnimator fadeAnim1 = ObjectAnimator.ofFloat(view, "alpha", start,
				end);
		fadeAnim1.setDuration(duration);
		fadeAnim1.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator arg0) {
			}

			@Override
			public void onAnimationRepeat(Animator arg0) {
			}

			@Override
			public void onAnimationEnd(Animator arg0) {
				if (animationEnd != null)
					animationEnd.endCallback();
			}

			@Override
			public void onAnimationCancel(Animator arg0) {
			}
		});
		fadeAnim1.start();
	}

	/**
	 * 回馈动画
	 * 
	 * @param view
	 *            需要平移的view
	 * @param start
	 *            平移的开始Y坐标
	 */
	public static void TranslationYO(final View view, long start) {

		ObjectAnimator oAnimator = ObjectAnimator.ofFloat(view, "translationY",
				start + 20, start);
		oAnimator.setInterpolator(new OvershootInterpolator());
		oAnimator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
		oAnimator.setDuration(LEFT_RIGHT_ANIMATION_DURATION);
		oAnimator.start();
	}

	/**
	 * 回馈动画
	 * 
	 * @param view
	 *            需要平移的view
	 * @param start
	 *            平移的开始Y坐标
	 */
	public static void TranslationXO(final View view, long start, boolean isLeft) {

		ObjectAnimator oAnimator1 = null;
		ObjectAnimator oAnimator2 = null;
		if (isLeft) {
			oAnimator2 = ObjectAnimator.ofFloat(view, "translationX", start,
					start + 40);
			oAnimator1 = ObjectAnimator.ofFloat(view, "translationX",
					start + 40, start);
		} else {
			oAnimator2 = ObjectAnimator.ofFloat(view, "translationX", start,
					start - 40);
			oAnimator1 = ObjectAnimator.ofFloat(view, "translationX",
					start - 40, start);
		}
		// oAnimator1.setInterpolator(new OvershootInterpolator());
		// oAnimator1.setInterpolator(new DecelerateInterpolator());
		// oAnimator2.setInterpolator(new DecelerateInterpolator());
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(oAnimator1).after(oAnimator2);
		animatorSet.setDuration(100);
		animatorSet.setInterpolator(new DecelerateInterpolator());
		animatorSet.start();
	}

	public static void scaleAnimationX(final View view, final float fromX,
			final float toX, final float sizeY) {

		ScaleAnimation animation = new ScaleAnimation(fromX, toX, sizeY, sizeY);
		animation.setDuration(LEFT_RIGHT_ANIMATION_DURATION);
		animation.setInterpolator(new DecelerateInterpolator());
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {

			}
		});
		animation.start();
	}

	public static void EnlargeItem(final View view, int fromXSize, int toXSize,
			int fromYSize, int toYSize, int duration) {
		PropertyValuesHolder animatorx = PropertyValuesHolder.ofInt("xx",
				fromXSize, toXSize);
		PropertyValuesHolder animatory = PropertyValuesHolder.ofInt("yy",
				fromYSize, toYSize);
		ValueAnimator bouncer = ValueAnimator.ofPropertyValuesHolder(animatorx,
				animatory).setDuration(duration);
		bouncer.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Integer x = (Integer) animation.getAnimatedValue("xx");
				Integer y = (Integer) animation.getAnimatedValue("yy");

				ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view
						.getLayoutParams();
				if(((x - layoutParams.width) % 2 == 0)){
					layoutParams.width = x;
				}else{
					layoutParams.width = x+1;
				}
				if(((y - layoutParams.height) % 2 == 0)){
					layoutParams.height = y;
				}else{
					layoutParams.height = y+1;
				}
				view.setLayoutParams(layoutParams);

			}
		});

		bouncer.start();

	}

	public static void Enlarge(final View view, int size, boolean isEnlarge,
			int duration) {

		if (!isEnlarge) {
			size = size * (-1);
		}

		ValueAnimator animator3 = ValueAnimator.ofInt(view.getHeight(),
				view.getHeight() + size);

		animator3.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {

				ViewGroup.MarginLayoutParams layoutParams3 = (ViewGroup.MarginLayoutParams) view
						.getLayoutParams();
				layoutParams3.height = (Integer) animation.getAnimatedValue();
				view.setLayoutParams(layoutParams3);
			}
		});

		ValueAnimator animator4 = ValueAnimator.ofInt(view.getWidth(),
				view.getWidth() + size / 5 * 3);

		animator4.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {

				ViewGroup.LayoutParams layoutParams3 = (ViewGroup.LayoutParams) view
						.getLayoutParams();
				layoutParams3.width = (Integer) animation.getAnimatedValue();
				view.setLayoutParams(layoutParams3);
			}
		});

		AnimatorSet bouncer = new AnimatorSet();

		bouncer.play(animator3).with(animator4);
		bouncer.setDuration(duration);
		bouncer.start();

	}

	public static void moveBottom(View view, int bottom, boolean isBottom) {

		ObjectAnimator oAnimator = null;

		if (isBottom) {
			oAnimator = ObjectAnimator
					.ofFloat(view, "translationY", 0, -bottom);
		} else {
			oAnimator = ObjectAnimator
					.ofFloat(view, "translationY", -bottom, 0);
		}

		oAnimator.setInterpolator(new DecelerateInterpolator());
		oAnimator.setDuration(LEFT_RIGHT_ANIMATION_DURATION);
		oAnimator.start();
	}

	public static void EnlargeHeight(final View view, int size,
			boolean isEnlarge) {

		if (!isEnlarge) {
			size = size * (-1);
		}

		ValueAnimator animator = ValueAnimator.ofInt(view.getHeight(),
				view.getHeight() + size);

		animator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {

				ViewGroup.MarginLayoutParams layoutParams3 = (ViewGroup.MarginLayoutParams) view
						.getLayoutParams();
				layoutParams3.height = (Integer) animation.getAnimatedValue();
				view.setLayoutParams(layoutParams3);
			}
		});
		animator.setInterpolator(new DecelerateInterpolator());
		animator.setDuration(LEFT_RIGHT_ANIMATION_DURATION);
		animator.start();

	}

	/**
	 * 
	 * @param view
	 * @param start
	 * @param end
	 * @param duration
	 * @param animationEnd
	 */
	public static void ScrollY(final View view, final int start,
			final int end, final int duration, final AnimationEnd animationEnd) {
		ValueAnimator ofInt = ValueAnimator.ofInt(start,end);
		ofInt.setInterpolator(new AccelerateDecelerateInterpolator());

		ofInt.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				view.setScrollY((Integer) animation.getAnimatedValue());
			}
		});
		
		ofInt.setDuration(duration);
		ofInt.start();
	}
	
	/**
	 * 动画结束的回调接口
	 */
	public interface AnimationEnd {

		/**
		 * 动画结束的回调方法
		 */
		public void endCallback();
	}
}
