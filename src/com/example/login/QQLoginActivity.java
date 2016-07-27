package com.example.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class QQLoginActivity extends ActionBarActivity {
	private Tencent mTencent;
	private ImageView mImageView;
	private TextView mTextView;
	private static final String APP_ID = "222222";
	private static final String TAG = "QQLoginActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qqlogin);
		mImageView = (ImageView) findViewById(R.id.iv_qq);
		mTextView = (TextView) findViewById(R.id.tv_QQ_welcome);
		mTencent = Tencent.createInstance(APP_ID, this.getApplicationContext());
		mTencent.login(this, APP_ID, new IUiListener() {

			@Override
			public void onError(UiError arg0) {
				// TODO Auto-generated method stub
				System.out.println("mTencent1");
				Log.i("TAG", "mTencent" + 1);
			}

			@Override
			public void onComplete(Object arg0) {
				// TODO Auto-generated method stub
				System.out.println("mTencent2");
				Log.i("TAG", "mTencent" + 2);
				JSONObject object = (JSONObject) arg0;
				Log.i(TAG, "onComplete");

				try {
					String openId = object.getString("openid");
					String token = object.getString("access_token");
					String expires = object.getString("expires_in");
					if (!TextUtils.isEmpty(token)
							&& !TextUtils.isEmpty(expires)
							&& !TextUtils.isEmpty(openId)) {
						// String nickname = object.getString("nickname");
						// Log.i("TAG", "nickname"+nickname);
						Log.i("TAG", "openId" + openId);
						Log.i("TAG", "token" + token);
						Log.i("TAG", "expires" + expires);
						Toast.makeText(QQLoginActivity.this, token, 1).show();
						mTencent.setOpenId(openId);
						mTencent.setAccessToken(token, expires);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 获取登录身份信息

				UserInfo info = new UserInfo(getApplicationContext(), mTencent
						.getQQToken());
				info.getUserInfo(new IUiListener() {

					@Override
					public void onError(UiError arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onComplete(Object arg0) {
						// TODO Auto-generated method stub
						JSONObject object = (JSONObject) arg0;
						Log.i("TAG", "获取身份信息");
						try {
							String nickname = object.getString("nickname");
							Log.i("TAG", "获取身份信息nickname" + nickname);
							mTextView.setText(nickname + ",欢迎使用QQ登录");
							String figureurl_qq_2 = object
									.getString("figureurl_qq_2");
							Log.i("TAG", "获取身份信息头像路径" + nickname);
							Picasso.with(getApplicationContext())
									.load(figureurl_qq_2).into(mImageView);
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
				System.out.println("mTencent3");
				Log.i("TAG", "mTencent" + 3);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Tencent.onActivityResultData(requestCode, resultCode, data,null);
	}

}
