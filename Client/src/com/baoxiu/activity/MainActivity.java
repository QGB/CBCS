package com.baoxiu.activity;

import qgb.U;
import frame.AU;
import frame.BX;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;

public class MainActivity extends Activity {
	Intent intent ;
	AU gAu = new AU(this);
	
	Handler gHtotast = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
        }   
   };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);// 显示主页面
		
	}

	// 我要报修
	public void baoxiu(View v) {
		Log.d("qgb-m","baoxiu");
		intent = new Intent(this, RepairActivity.class);
		startActivity(intent);
	}

	// 进入个人信息页面
	public void userInfo(View v) {
		Log.d("qgb-m","userinfo");
		intent = new Intent(this, UserInfoActivity.class);
		startActivity(intent);
	}

	// 进入报修记录页面
	public void bxRecord(View v) {
		Log.d("qgb-m","bxrecord");
		intent = new Intent(this, RecordActivity.class);
		startActivity(intent);
	}

	// 进入编号查询页面
	public void bxFind(View v) {
		Log.d("qgb-m","bxfind");
		intent = new Intent(this, FindActivity.class);
		startActivity(intent);
	}

	public void exit(View v) {
		Log.d("qgb-m","exit");
		onBackPressed();
	}
	
	public void more(View v) {
		Log.d("qgb-m","more");
		gAu.toast(BX.gUser.toString());
		U.exit();
	}

	@Override
	public void onBackPressed() {
		intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
	
}