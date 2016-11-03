package com.xk.xiaomiweather.model.bean;

/**
 * Created by xk on 2016/11/2 20:45.
 */

public class City {
    private String province;//省
    private String district;//县（地区）
    private String city;//市
    private String envicloudId;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEnvicloudId() {
        return envicloudId;
    }

    public void setEnvicloudId(String envicloudId) {
        this.envicloudId = envicloudId;
    }

    @Override
    public String toString() {
        return "City{" +
                "province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", envicloudId='" + envicloudId + '\'' +
                '}';
    }
}
