package com.example.login;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SMSLoginActivity extends ActionBarActivity {
	private static final String APPKEY = "157a915b980ab";
	private static final String APPSECRET = "fe201d69e2adb559fc96a49a9b1332e4";
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smslogin);
		mTextView = (TextView) findViewById(R.id.tv_sms_login);
		SMSSDK.initSDK(this, APPKEY, APPSECRET);

		// ��ע��ҳ��
		RegisterPage registerPage = new RegisterPage();
		registerPage.setRegisterCallback(new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				// ����ע����
				if (result == SMSSDK.RESULT_COMPLETE) {
					@SuppressWarnings("unchecked")
					HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
					String country = (String) phoneMap.get("country");
					String phone = (String) phoneMap.get("phone");
					System.out.println("����" + country + "\n" + "�绰" + phone);
					mTextView.setText(phone + "����¼�ɹ�GUI");

					// �ύ�û���Ϣ
					// registerUser(country, phone);
				}
			}
		});
		registerPage.show(SMSLoginActivity.this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smslogin, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
