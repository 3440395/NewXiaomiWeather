package com.xk.xiaomiweather.model.bean;

/**
 * 当前的aqi
 * Created by xuekai on 2016/11/2.
 */
public class CurrentAQIWeather {
    private String PM25;
    private String time;
    private String rdesc;
    private String PM10;
    private String SO2;
    private String o3;
    private String NO2;
    private String primary;
    private String rcode;
    private String CO;
    private String AQI;

    @Override
    public String toString() {
        return "CurrentAQIWeather{" +
                "PM25='" + PM25 + '\'' +
                ", time='" + time + '\'' +
                ", rdesc='" + rdesc + '\'' +
                ", PM10='" + PM10 + '\'' +
                ", SO2='" + SO2 + '\'' +
                ", o3='" + o3 + '\'' +
                ", NO2='" + NO2 + '\'' +
                ", primary='" + primary + '\'' +
                ", rcode='" + rcode + '\'' +
                ", CO='" + CO + '\'' +
                ", AQI='" + AQI + '\'' +
                '}';
    }

    public String getPM25() {
        return PM25;
    }

    public void setPM25(String PM25) {
        this.PM25 = PM25;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public String getPM10() {
        return PM10;
    }

    public void setPM10(String PM10) {
        this.PM10 = PM10;
    }

    public String getSO2() {
        return SO2;
    }

    public void setSO2(String SO2) {
        this.SO2 = SO2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getNO2() {
        return NO2;
    }

    public void setNO2(String NO2) {
        this.NO2 = NO2;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public String getCO() {
        return CO;
    }

    public void setCO(String CO) {
        this.CO = CO;
    }

    public String getAQI() {
        return AQI;
    }

    public void setAQI(String AQI) {
        this.AQI = AQI;
    }
}
