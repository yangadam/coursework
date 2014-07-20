package com.mworld.ui.main;

import java.util.ArrayList;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.mworld.R;
import com.mworld.support.utils.GlobalContext;
import com.mworld.ui.adapter.ProfileAdapter;
import com.mworld.ui.holder.ProfTabHolder;
import com.mworld.weibo.api.FriendshipsAPI;
import com.mworld.weibo.api.StatusAPI;
import com.mworld.weibo.entities.StatusList;
import com.mworld.weibo.entities.User;
import com.mworld.weibo.entities.UserList;

public class ProfileActivity extends Activity {

	public User mUser;

	public ProfileAdapter adapter;

	private StatusAPI mStatusAPI;

	private FriendshipsAPI mFriendshipsAPI;

	@SuppressWarnings("rawtypes")
	public ArrayList mArrayList = new ArrayList();

	private ProfTabHolder tabHolder;

	@ViewInject(id = R.id.profile_timeline)
	private ListView mList;
	@ViewInject(id = R.id.header)
	private View header;
	@ViewInject(id = R.id.statuses_tab, click = "click")
	private View statusesTab;
	@ViewInject(id = R.id.friends_tab, click = "click")
	private View friendsTab;
	@ViewInject(id = R.id.followers_tab, click = "click")
	private View followersTab;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		mUser = (User) getIntent().getParcelableExtra("user");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.profileactivity_layout);
		FinalActivity.initInjectedView(this);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setTitle(mUser == null ? "" : mUser.getScreenName());

		tabHolder = new ProfTabHolder(this, header);
		tabHolder.inflate(mUser);
		header.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				view.performClick();
				return true;
			}
		});

		mArrayList.add(mUser);
		mArrayList.add(mUser);
		String accessToken = GlobalContext.getInstance().getAccount()
				.getAccessToken();
		mStatusAPI = new StatusAPI(accessToken);
		mFriendshipsAPI = new FriendshipsAPI(accessToken);
		mStatusAPI.userTimeline(Long.parseLong(mUser.getId()), 0, 0, 20,
				page++, false, 0, false, new AjaxCallBack<String>() {

					@Override
					public void onSuccess(String jsonString) {
						super.onSuccess(jsonString);
						StatusList statusesList = StatusList.parse(jsonString);
						if (statusesList.statuses != null)
							mArrayList.addAll(statusesList.statuses);
						adapter.notifyDataSetChanged();
					}

				});

		adapter = new ProfileAdapter(this, mArrayList);
		mList.setAdapter(adapter);
		mList.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				int lastItem = firstVisibleItem + visibleItemCount;
				if (lastItem == totalItemCount && firstVisibleItem != 0) {
					View lastItemView = (View) mList.getChildAt(mList
							.getChildCount() - 1);
					if (mList.getBottom() == lastItemView.getBottom()) {
						performLoad(GlobalContext.getInstance().getCurTab());
					}
				}

				if (firstVisibleItem == 0) {
					header.setVisibility(View.GONE);
				} else {
					header.setVisibility(View.VISIBLE);
				}
			}

		});
	}

	public int page = 1;

	public void performLoad(int tab) {
		tabHolder.inflate(mUser);
		switch (tab) {
		case 0:
			mStatusAPI.userTimeline(Long.parseLong(mUser.getId()), 0, 0, 20,
					page++, false, 0, false, new AjaxCallBack<String>() {

						@SuppressWarnings("unchecked")
						@Override
						public void onSuccess(String jsonString) {
							super.onSuccess(jsonString);
							if (GlobalContext.getInstance().getCurTab() != 0)
								return;
							StatusList statusesList = StatusList
									.parse(jsonString);
							if (statusesList.statuses != null)
								mArrayList.addAll(statusesList.statuses);
							adapter.notifyDataSetChanged();
						}

					});
			break;
		case 1:
			mFriendshipsAPI.friends(Long.parseLong(mUser.getId()), 20,
					friendsCur, true, new AjaxCallBack<String>() {

						@SuppressWarnings("unchecked")
						@Override
						public void onSuccess(String jsonString) {
							super.onSuccess(jsonString);
							if (GlobalContext.getInstance().getCurTab() != 1)
								return;
							UserList usersList = UserList.parse(jsonString);
							if (usersList.users != null) {
								friendsCur = usersList.next_cursor;
								mArrayList.addAll(usersList.users);
							}
							adapter.notifyDataSetChanged();
						}

					});
			break;
		case 2:
			mFriendshipsAPI.followers(Long.parseLong(mUser.getId()), 20,
					followerCur, true, new AjaxCallBack<String>() {

						@SuppressWarnings("unchecked")
						@Override
						public void onSuccess(String jsonString) {
							super.onSuccess(jsonString);
							if (GlobalContext.getInstance().getCurTab() != 2)
								return;
							UserList usersList = UserList.parse(jsonString);
							if (usersList.users != null) {
								followerCur = usersList.next_cursor;
								mArrayList.addAll(usersList.users);
							}
							adapter.notifyDataSetChanged();
						}
					});
			break;
		default:
			break;
		}
	}

	public int friendsCur = 0;
	public int followerCur = 0;

	public void click(View view) {
		tabHolder.inflate(mUser);
		switch (view.getId()) {
		case R.id.statuses_tab:
			if (GlobalContext.getInstance().getCurTab() != 0) {
				GlobalContext.getInstance().setCurTab(0);
				for (int i = mArrayList.size() - 1; i > 1; i--)
					mArrayList.remove(i);
				performLoad(0);
				friendsCur = 0;
				followerCur = 0;
			}
			break;
		case R.id.friends_tab:
			if (GlobalContext.getInstance().getCurTab() != 1) {
				GlobalContext.getInstance().setCurTab(1);
				for (int i = mArrayList.size() - 1; i > 1; i--)
					mArrayList.remove(i);
				performLoad(1);
				followerCur = 0;
				page = 1;
			}
			break;
		case R.id.followers_tab:
			if (GlobalContext.getInstance().getCurTab() != 2) {
				GlobalContext.getInstance().setCurTab(2);
				for (int i = mArrayList.size() - 1; i > 1; i--)
					mArrayList.remove(i);
				performLoad(2);
				friendsCur = 0;
				page = 1;
			}
			break;
		default:
			break;
		}

	}

}