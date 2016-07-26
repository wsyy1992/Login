package com.example.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class QQLoginActivity extends ActionBarActivity {
	private Tencent mTencent;
	private ImageView mImageView;
	private static final String APP_ID = "1105494493";
	private static final String TAG = "QQLoginActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qqlogin);
		mImageView = (ImageView) findViewById(R.id.iv_qq);
		mTencent = Tencent.createInstance(APP_ID, this.getApplicationContext());
		mTencent.login(this, "all", new IUiListener() {

			@Override
			public void onError(UiError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComplete(Object arg0) {
				JSONObject object = (JSONObject) arg0;
				Log.i(TAG, "onComplete");
				try {
					String openId = object.getString("openid");
					String token = object.getString("access_token");
					String expires = object.getString("expires_in");
					if (!TextUtils.isEmpty(token)
							&& !TextUtils.isEmpty(expires)
							&& !TextUtils.isEmpty(openId)) {
						Log.i(TAG, openId);
						Log.i(TAG, token);
						Log.i(TAG, expires);
						mTencent.setOpenId(openId);
						mTencent.setAccessToken(token, expires);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 获取登录成功的用户信息
				UserInfo info = new UserInfo(QQLoginActivity.this, mTencent
						.getQQToken());
				info.getUserInfo(new IUiListener() {

					@Override
					public void onError(UiError arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onComplete(Object arg0) {
						JSONObject object = (JSONObject) arg0;
						try {
							String nickname = object.getString("nickname");
							String icon = object.getString("figureurl_qq_2");
							String gender = object.getString("gender");
							Log.i(TAG, nickname);
							Log.i(TAG, icon);
							Log.i(TAG, gender);
							System.out.println(nickname + icon + gender);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub

					}
				});
			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}
		});
	}
}
