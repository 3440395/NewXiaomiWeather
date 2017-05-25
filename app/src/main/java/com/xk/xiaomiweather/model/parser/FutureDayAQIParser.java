package com.xk.xiaomiweather.model.parser;

import android.util.Log;

import com.xk.xiaomiweather.comm.Constant;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过名称
 * Created by xk on 2016/11/2 21:01.
 */
public class FutureDayAQIParser extends BaseParser<Map<String, String>, JSONObject> {
    private String url = Constant.BASE_ENVICLOUD_URL + "/v2/cityairforecast/"+Constant.envicloudID+"/";
    private RequestMethod method = RequestMethod.GET;

    @Override
    public void setRequestParams(Object... params) {
        url=url+ URLEncoder.encode((String) params[0]);
        request = NoHttp.createJsonObjectRequest(url, method);
    }
//    http://service.envicloud.cn:8082/v2/cityairlive/EGSZNDQWMZK1MTQ3NZKXNJIYNDAYOQ==/101010100
    @Override
    protected Map<String, String> parser(Response<JSONObject> response)  {
        if (response.get() != null) {
            JSONObject object = response.get();
            Log.e("FutureDayAQIParser","parser"+object.toString());
            try {
                if (object.getInt("rcode")==200) {
                    JSONArray futureDaysJson = object.getJSONArray("forecast");
                    HashMap<String, String> futureDaysAQIBean = new HashMap<>();
                    for (int i = 0; i < futureDaysJson.length(); i++) {
                        JSONObject futuredayJson = (JSONObject) futureDaysJson.get(i);
                        futureDaysAQIBean.put(futuredayJson.getString("date").replace("-",""),futuredayJson.getString("aqi_avg"));
                    }
                    Log.e("FutureDayAQIParser","parser"+futureDaysAQIBean.toString());
                    return futureDaysAQIBean;
                }else{
                    HashMap<String, String> futureDaysAQIBean = new HashMap<>();
                    futureDaysAQIBean.put("2222222201","10");
                    futureDaysAQIBean.put("2222222203","15");
                    futureDaysAQIBean.put("2222222205","2");
                    futureDaysAQIBean.put("2222222207","19");
                    futureDaysAQIBean.put("2222222209","20");
                    futureDaysAQIBean.put("2222222210","21");
                    futureDaysAQIBean.put("2222222212","10");
                    return futureDaysAQIBean;

                }
            } catch (JSONException e) {
                Log.e("FutureDayAQIParser","返回的json"+object.toString());
                e.printStackTrace();
                HashMap<String, String> futureDaysAQIBean = new HashMap<>();
                futureDaysAQIBean.put("2222222201","10");
                futureDaysAQIBean.put("2222222203","15");
                futureDaysAQIBean.put("2222222205","2");
                futureDaysAQIBean.put("2222222207","19");
                futureDaysAQIBean.put("2222222209","20");
                futureDaysAQIBean.put("2222222210","21");
                futureDaysAQIBean.put("2222222212","10");
                return futureDaysAQIBean;
            }
        }
        return null;
    }
}
