package com.baoxiu.activity;

import qgb.*;

import static frame.BX.*;


import frame.AU;
import frame.BX;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class LoginActivity extends Activity {
	//Intent intent = new Intent();
	private EditText getUser; 
	private EditText getPW;
	AU gAu = new AU(this);
	
	Handler gHtotast = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
        }   
   };
	
	Handler gHlogin = new Handler() {  
        public void handleMessage(Message msg) {  
        	gAu.toast(msg.obj.toString());
        	if (BX.gidu >0) {
        		//Log.d("qgb-login", "login success");
    			finish();
    			Intent i=new Intent(LoginActivity.this, MainActivity.class);
    			startActivity(i);
			}
        }   
   };
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//BX.login(gAu, "", "","");
		Log.d("qgb-r", BX.read(gsCDorm));
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);// 鏄剧ず鐧婚檰椤甸潰
		getUser = (EditText) findViewById(R.id.et_Login_num);
		getPW = (EditText) findViewById(R.id.et_Login_password);
		gAu.setHtotast(gHtotast);
		gAu.setHAction(gHlogin);
		
	}

	// 鐧婚檰浜嬩欢鏂规硶
	public void login(View v) {
		String su = getUser.getText().toString().trim();
		String spw = getPW.getText().toString().trim();
		if (su.length() < 1 || spw.length() < 1) {
			gAu.toast("Please Input UserName And PassWord!");
			return;
		}
		BX.login(gAu,su,spw,"u");
	}



	public void register(View v) {
		// 娉ㄥ唽
	//	get
		//findViewById(R.layout.activity_register_user)
		Class c=null; 
		try {
			c= Class.forName(RegisterActivity.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Intent intent = new Intent();
		intent.setClass(LoginActivity.this, c);
		Log.w("QGB",c.toString()+"|"+ intent.toString());
		//= new Intent(this, RegisterActivity.class);
		startActivity(intent);
		//startService(intent);
		finish();
	}

	@Override
	public void onBackPressed() {
		finish();
		U.exit();
	}


}