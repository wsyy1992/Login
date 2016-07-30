package com.example.login;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class WXEntryActivity extends ActionBarActivity implements
		IWXAPIEventHandler {
	private IWXAPI api;
	private static final String APP_ID = "wx5c7db6ddfff6761f";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wxentry);

		SendAuth.Req req = new SendAuth.Req();
		req.scope = "post_timeline";
		req.state = "none";
		api = WXAPIFactory.createWXAPI(this, APP_ID, false);
		api.sendReq(req);
		api.handleIntent(getIntent(), this);

		SendAuth.Resp resp = new SendAuth.Resp();
	}

	@Override
	public void onReq(BaseReq arg0) {
		// TODO Auto-generated method stub
		System.out.println("onReq");
	}

	@Override
	public void onResp(BaseResp arg0) {
		// TODO Auto-generated method stub
		System.out.println("onResp");

	}
}