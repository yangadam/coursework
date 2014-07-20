package com.mworld.weibo.entities;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * 评论（comment）对象结构体
 * 
 * @author MengMeng
 * 
 */
public class Comment {
	private static final String TAG = "Comment";

	/** 评论创建时间 */
	public String created_at;
	/** 评论的 ID */
	public long id;
	/** 评论的内容 */
	public String text;
	/** 评论的来源 */
	public String source;
	/** 评论作者的用户信息字段 */
	public User user;
	/** 评论的 MID */
	public String mid;
	/** 字符串型的评论 ID */
	public String idstr;
	/** 评论的微博信息字段 */
	public Status status;
	/** 评论来源评论，当本评论属于对另一评论的回复时返回此字段 */
	public Comment reply_comment;

	/**
	 * 将json字符串解析成Comment对象
	 * 
	 * @param jsonString
	 *            待解析的json字符串
	 * @return 解析出来的Comment对象
	 */
	public static Comment parse(String jsonString) {
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return Comment.parse(JSON.parseObject(jsonString));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}

		return null;
	}

	/**
	 * 将JSONObject解析成Comment对象
	 * 
	 * @param jsonObject
	 *            待解析的JSONObject
	 * @return 解析出来的Comment对象
	 */
	public static Comment parse(JSONObject jsonObject) {
		if (null == jsonObject) {
			return null;
		}

		Comment comment = new Comment();
		comment.created_at = jsonObject.getString("created_at");
		comment.id = jsonObject.getLongValue("id");
		comment.text = jsonObject.getString("text");
		comment.source = jsonObject.getString("source");
		comment.user = User.parse(jsonObject.getJSONObject("user"));
		comment.mid = jsonObject.getString("mid");
		comment.idstr = jsonObject.getString("idstr");
		comment.status = Status.parse(jsonObject.getJSONObject("status"));
		comment.reply_comment = Comment.parse(jsonObject
				.getJSONObject("reply_comment"));

		return comment;
	}

}
