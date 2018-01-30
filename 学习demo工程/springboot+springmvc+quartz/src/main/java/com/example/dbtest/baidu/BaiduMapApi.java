package com.example.dbtest.baidu;

import com.alibaba.fastjson.JSONObject;
import com.example.dbtest.model.BaiduCoordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 百度地图API相关接口
 * @author wangjiahui
 * @doc API文档地址：http://lbsyun.baidu.com/index.php
 */
public class BaiduMapApi {

    public static final String key = "mvcu15lfh3UQG74YeCjYvvFPmYd1FSKy";

    public static final String geocoderURL = "http://api.map.baidu.com/geocoder/v2";

    /**
     * 调用百度地图API，通过地址查询经纬度
     * @author wangjiahui
     * @doc API文档地址：http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
     * @param address:地址信息
     */
    public static BaiduCoordinate searchCoordinate(String address){
        if(address == null || address.equals(""))
            return null;

        String tempAddress = address;
        try {
            address = URLEncoder.encode(address, "UTF-8");
            address = address.replaceAll("\\+", "%20");
        }
        catch (Exception e){
            return null;
        }

        String url = geocoderURL + "/?address=" + address + "&output=json&ak=" + key;
        String resultJson = loadJSON(url);

        //处理百度地图API返回结果
        JSONObject baiduResultJson = JSONObject.parseObject(resultJson);
        System.out.println(baiduResultJson.toJSONString());

        if (baiduResultJson.getString("status").equals("0")) {
            //不判断precise和confidence
            //Integer precise = baiduResultJson.getJSONObject("result").getInteger("precise");
            //Integer confidence = baiduResultJson.getJSONObject("result").getInteger("confidence");
            double lng = baiduResultJson.getJSONObject("result").getJSONObject("location").getDouble("lng");
            double lat = baiduResultJson.getJSONObject("result").getJSONObject("location").getDouble("lat");
            BaiduCoordinate baiduCoordinate = new BaiduCoordinate(lng, lat);
            return baiduCoordinate;
        }
        return null;
    }

    public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }

}
