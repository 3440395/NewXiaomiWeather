package com.xk.xiaomiweather.model.parser;

import android.util.Log;

import com.xk.xiaomiweather.comm.Constant;
import com.xk.xiaomiweather.model.bean.City;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xk on 2016/11/2 20:46.
 */

public class CityParser extends BaseParser<City,JSONObject> {
    private String url = Constant.BASE_ENVICLOUD_URL + "/v2/citycode/" + Constant.envicloudID;
    private RequestMethod method=RequestMethod.GET;

    @Override
    public void setRequestParams(Object... params) {
        url=url+"/"+params[0];
        request = NoHttp.createJsonObjectRequest(url, method);

    }

    @Override
    protected City parser(Response<JSONObject> response)  {
        if (response.get() != null) {
            JSONObject object = response.get();
            Log.e("CityParser","parser"+object.toString() );
            try {
                if (object.getInt("rcode")==200) {
                    City city = new City();
                    city.setDistrict(object.getString("cityname"));
                    city.setEnvicloudId(object.getString("citycode"));
                    return city;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
