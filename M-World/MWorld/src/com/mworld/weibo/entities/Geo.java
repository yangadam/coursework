package com.mworld.weibo.entities;

import java.io.Serializable;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * 地理信息（geo）对象数据结构
 * 
 * @author MengMeng
 * 
 */
public class Geo implements Serializable {
	private static final String TAG = "Geo";

	/**
	 * 
	 */
	private static final long serialVersionUID = 5885669474617454511L;

	/** 经度坐标 */
	public String longitude;
	/** 维度坐标 */
	public String latitude;
	/** 所在城市的城市代码 */
	public String city;
	/** 所在省份的省份代码 */
	public String province;
	/** 所在城市的城市名称 */
	public String city_name;
	/** 所在省份的省份名称 */
	public String province_name;
	/** 所在的实际地址，可以为空 */
	public String address;
	/** 地址的汉语拼音，不是所有情况都会返回该字段 */
	public String pinyin;
	/** 更多信息，不是所有情况都会返回该字段 */
	public String more;

	/**
	 * 将json字符串解析成Geo对象
	 * 
	 * @param jsonString
	 *            待解析的json字符串
	 * @return 解析出来的Geo对象
	 */
	public static Geo parse(String jsonString) {
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return Geo.parse(JSON.parseObject(jsonString));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}

		return null;
	}

	/**
	 * 将JSONObject解析成Geo对象
	 * 
	 * @param jsonObject
	 *            待解析的JSONObject
	 * @return 解析出来的Geo对象
	 */
	public static Geo parse(JSONObject jsonObject) {
		if (null == jsonObject) {
			return null;
		}
		Geo geo = new Geo();

		geo.longitude = jsonObject.getString("longitude");
		geo.latitude = jsonObject.getString("latitude");
		geo.city = jsonObject.getString("city");
		geo.province = jsonObject.getString("province");
		geo.city_name = jsonObject.getString("city_name");
		geo.province_name = jsonObject.getString("province_name");
		geo.address = jsonObject.getString("address");
		geo.pinyin = jsonObject.getString("pinyin");
		geo.more = jsonObject.getString("more");
		return geo;
	}

}