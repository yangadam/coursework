package com.mworld.weibo.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * 微博的可见性及指定可见分组信息对象数据结构
 * 
 * @author MengMeng
 * 
 */
public class Visible implements Parcelable {

	private static final String TAG = "Visible";

	public static final int VISIBLE_NORMAL = 0;
	public static final int VISIBLE_PRIVACY = 1;
	public static final int VISIBLE_GROUPED = 2;
	public static final int VISIBLE_FRIEND = 3;

	/** type 取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博 */
	public int type;
	/** 分组的组号 */
	public int list_id;

	public Visible() {

	}

	/**
	 * 将json字符串解析成Visible对象
	 * 
	 * @param jsonString
	 *            待解析的json字符串
	 * @return 解析出来的Visible对象
	 */
	public static Visible parse(String jsonString) {
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return Visible.parse(JSON.parseObject(jsonString));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}

		return null;
	}

	/**
	 * 将JSONObject解析成Visible对象
	 * 
	 * @param jsonObject
	 *            jsonString 待解析的JSONObject
	 * @return 解析出来的Visible对象
	 */
	public static Visible parse(JSONObject jsonObject) {
		if (null == jsonObject) {
			return null;
		}
		Visible visible = new Visible();

		visible.type = jsonObject.getIntValue("type");
		visible.list_id = jsonObject.getIntValue("list_id");
		return visible;
	}

	public Visible(Parcel in) {
		type = in.readInt();
		list_id = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<Visible> CREATOR = new Parcelable.Creator<Visible>() {
		public Visible createFromParcel(Parcel in) {
			return new Visible(in);
		}

		public Visible[] newArray(int size) {
			return new Visible[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(type);
		dest.writeInt(list_id);
	}

}