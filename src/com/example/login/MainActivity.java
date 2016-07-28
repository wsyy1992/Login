package com.example.login;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends ActionBarActivity implements
		IWXAPIEventHandler {
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void login(View view) {
		switch (view.getId()) {
		case R.id.btn_WX_login:

			intent = new Intent(this, WXLoginActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_QQ_login:
			intent = new Intent(this, QQLoginActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_SMS_loginGUI:
			intent = new Intent(this, SMSLoginActivity.class);
			startActivity(intent);

			break;
	

		default:
			break;
		}

	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	}

	@Override
	public void onReq(BaseReq arg0) {
		System.out.println("onReq");

	}

	@Override
	public void onResp(BaseResp arg0) {
		System.out.println("onResp");

	}

}
