package com.xk.xiaomiweather.model.bean;

import java.io.Serializable;

/**
 * Created by xuekai on 2016/11/2.
 */

public class ThreeHourBaseWeather  implements Serializable {
    //"weatherid": "00",/*天气标识ID*/
    //"weather": "晴", /*天气*/
    //"temp1": "27", /*低温*/
    //"temp2": "31", /*高温*/
    //"sh": "08", /*开始小时*/
    //"eh": "11", /*结束小时*/
    //"date": "20140530", /*日期*/
    //"sfdate": "20140530080000", /*完整开始时间*/
    //"efdate": "20140530110000" /*完整结束时间*/
    private String weatherid;
    private String weather;
    private String temp1;
    private String temp2;
    private String sh;
    private String eh;
    private String date;
    private String sfdate;
    private String efdate;

    @Override
    public String toString() {
        return "ThreeHourBaseWeather{" +
                "weatherid='" + weatherid + '\'' +
                ", weather='" + weather + '\'' +
                ", temp1='" + temp1 + '\'' +
                ", temp2='" + temp2 + '\'' +
                ", sh='" + sh + '\'' +
                ", eh='" + eh + '\'' +
                ", date='" + date + '\'' +
                ", sfdate='" + sfdate + '\'' +
                ", efdate='" + efdate + '\'' +
                '}';
    }

    public String getWeatherid() {
        return weatherid;
    }

    public void setWeatherid(String weatherid) {
        this.weatherid = weatherid;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getSh() {
        return sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }

    public String getEh() {
        return eh;
    }

    public void setEh(String eh) {
        this.eh = eh;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSfdate() {
        return sfdate;
    }

    public void setSfdate(String sfdate) {
        this.sfdate = sfdate;
    }

    public String getEfdate() {
        return efdate;
    }

    public void setEfdate(String efdate) {
        this.efdate = efdate;
    }
}
