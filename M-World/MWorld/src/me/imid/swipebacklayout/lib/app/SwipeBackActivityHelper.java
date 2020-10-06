package me.imid.swipebacklayout.lib.app;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.mworld.R;

/**
 * @author Yrom
 */
public class SwipeBackActivityHelper {
	private Activity mActivity;

	private SwipeBackLayout mSwipeBackLayout;

	public SwipeBackActivityHelper(Activity activity) {
		mActivity = activity;
	}

	@SuppressLint("InflateParams") @SuppressWarnings("deprecation")
	public void onActivityCreate() {
		mActivity.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		mActivity.getWindow().getDecorView().setBackgroundDrawable(null);
		mSwipeBackLayout = (SwipeBackLayout) LayoutInflater.from(mActivity)
				.inflate(R.layout.swipeback_layout, null);
		mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
			@Override
			public void onScrollStateChange(int state, float scrollPercent) {
				if (state == SwipeBackLayout.STATE_IDLE && scrollPercent == 0) {
					Utils.convertActivityFromTranslucent(mActivity);
				}
			}

			@Override
			public void onEdgeTouch(int edgeFlag) {
				Utils.convertActivityToTranslucent(mActivity);
			}

			@Override
			public void onScrollOverThreshold() {

			}
		});
	}

	public void onPostCreate() {
		mSwipeBackLayout.attachToActivity(mActivity);
		Utils.convertActivityFromTranslucent(mActivity);
	}

	public View findViewById(int id) {
		if (mSwipeBackLayout != null) {
			return mSwipeBackLayout.findViewById(id);
		}
		return null;
	}

	public SwipeBackLayout getSwipeBackLayout() {
		return mSwipeBackLayout;
	}
}
