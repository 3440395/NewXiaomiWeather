package com.xk.xiaomiweather.ui.custom;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xk.xiaomiweather.R;
import com.xk.xiaomiweather.model.bean.TodayBaseWeather;
import com.xk.xiaomiweather.ui.IVUpdateable;

import org.w3c.dom.Text;

/**
 * Created by xk on 2016/11/8 20:15.
 */

public class AdviceItemView extends FrameLayout implements IVUpdateable<TodayBaseWeather> {

    private int iconId;
    private TextView intro;
    private TextView des;

    public AdviceItemView(Context context, @DrawableRes int icon) {
        super(context);
        this.iconId = icon;
        init(icon);
    }

    private void init(int iconId) {
        View item = View.inflate(getContext(), R.layout.layout_advice_item, null);
        ImageView icon = (ImageView) item.findViewById(R.id.icon);
        intro = (TextView) item.findViewById(R.id.intro);
        des = (TextView) item.findViewById(R.id.des);
        icon.setImageResource(iconId);
        addView(item);
    }

    @Override
    public void update(TodayBaseWeather data) {
        switch (iconId) {
            case R.mipmap.icon_dress:
                //穿衣建议
                dressAdvice(data);
                break;
            case R.mipmap.icon_wash:
                //洗车建议
                washAdvice(data);
                break;
            case R.mipmap.icon_exercise:
                exerciseAdvice(data);
                break;
            case R.mipmap.icon_san:
                sanAdvice(data);
                break;
            case R.mipmap.icon_uv:
                break;
        }

    }

    private void sanAdvice(TodayBaseWeather data) {
        String weather = data.getWeather();
        String temp = data.getTemperature().replace("℃", "");
        String[] temps = temp.split("~");

        if(weather.contains("雨")){
            des.setText("不好，今天下雨了，漂亮的小伞撑起来。");
        }else if(weather.contains("雪")){
            des.setText("今天有雪，撑伞前行记得常常抖抖伞上边的雪哦，不然你会变成一个大白蘑菇。");
        }else if(Integer.parseInt(temps[1])>32&&(!weather.contains("雪")&&(!weather.contains("雨")))){
            des.setText("出行带个伞吧，不然非洲人民会认亲的。");
        }else{
            des.setText("今天的天气不用带伞。");
        }


//
//        判断最高温度大雨32℃且今天不下雨不下雪，建议
    }

    private void exerciseAdvice(TodayBaseWeather data) {
        String weather = data.getWeather();
        if(weather.contains("雨")||weather.contains("雪")||weather.contains("雾")||weather.contains("霾")||weather.contains("尘")||weather.contains("沙")){
            des.setText("今天天气特殊，不建议室外运动。");
        }

        String temp = data.getTemperature().replace("℃", "");
        String[] temps = temp.split("~");
        if(Integer.parseInt(temps[0])>5&&(weather.contains("晴")||weather.contains("多云")||weather.contains("阴"))){
            des.setText("今天天气不错，大家开心动起来吧。");
        }
    }

    private void washAdvice(TodayBaseWeather data) {
        String weather = data.getWeather();
        if(weather.contains("多云")||weather.contains("阴")){
            des.setText("注意查看明天雨水情况哦，否则洗了车就下雨可悲催了。");
        }else if(weather.contains("雾")){
            des.setText("今天有雾开车可得小心，双闪走起来吧。");
        }else if(weather.contains("尘")||weather.contains("沙")){
            des.setText("不要洗车了，今天沙子多。");
        }else if(weather.contains("大雪")||weather.contains("暴雪")){
            des.setText("今天有大雪或特大暴雪还是尽量别出行了，把你的爱车放到他的小屋里把。");
        }else if(weather.contains("冰雹")||weather.contains("雨夹雪")||weather.contains("阵雪")||weather.contains("小雪")||weather.equals("中雪")){
            des.setText("今天的道路会很湿滑，千万注意小心慢行。");
        }else if(weather.contains("暴雨")){
            des.setText("今天有大暴雨或特大暴雨哦，还是尽量不要开车出行了，毕竟安全是第一位的。");
        }else if(weather.contains("雨")){
            des.setText("今天有雨哦，驾车出行会有视线、路面湿滑的影响哦，要注意慢行。");
        }else if(weather.contains("晴")){
            des.setText("天气晴朗，是自驾游的好时光，洗车什么的最好了。");
        }
    }

    private void dressAdvice(TodayBaseWeather data) {
        String temp = data.getTemperature().replace("℃", "");
        String[] temps = temp.split("~");

        if(Integer.parseInt(temps[0])<23&&(Integer.parseInt(temps[1])-Integer.parseInt(temps[0]))>8){
            des.setText("今日温差很大哦  注意夜间保暖");
        }else if(Integer.parseInt(temps[0])<-10){
            des.setText("天气极冷，厚棉衣，厚羽绒服统统怼起来，还是尽量少出去玩吧，不然感冒要吃药的哦，老年人小朋友就不要外出了哈。");
        }else if(Integer.parseInt(temps[0])<-5){
            des.setText("天气寒冷，羽绒服、冬大衣终于可以派上用场了，大家注意保暖哦，老年人小朋友就少外出了哦。");
        }else if(Integer.parseInt(temps[0])<8){
            des.setText("天气凉，厚外套、帅气的大风衣穿起来，要光顾着耍酷，要加毛衣哦。");
        }else if(Integer.parseInt(temps[0])<15){
            des.setText("天气温凉，西服套装，薄薄羊毛衫都可以来了，老年人注意穿风衣保暖哦。");
        }else if(Integer.parseInt(temps[0])<19){
            des.setText("天气温和，长袖衣服穿起来，老年人注意穿秋裤保暖喽。");
        }else if(Integer.parseInt(temps[0])<23){
            des.setText("天气暖和，短套装、薄牛仔可以拿出来了哦，表在秀腿了。");
        }else if(Integer.parseInt(temps[1])>34){
            des.setText("今天会热到爆，裤衩背心花裙子走一组吧，老年人注意保护腿部保暖，可以穿单裤进行防风哦。");
        }
    }
}
