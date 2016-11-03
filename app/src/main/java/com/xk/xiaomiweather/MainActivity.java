package com.xk.xiaomiweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xk.xiaomiweather.model.bean.CurrentAQIWeather;
import com.xk.xiaomiweather.model.parser.CurrentAQIParser;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CurrentAQIParser currentAQIParser = new CurrentAQIParser();
//        currentAQIParser .setRequestParams("101010100");
        new Thread(new Runnable() {
            @Override
            public void run() {
                CurrentAQIWeather data = currentAQIParser.getData();

            }
        }).start();
    }
}
