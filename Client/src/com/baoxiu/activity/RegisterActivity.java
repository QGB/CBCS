package com.baoxiu.activity;

import frame.AU;
import frame.BX;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends Activity {	
	private EditText getName; 
	private EditText getNum; 
	private EditText getPW;
	private EditText getPW1;
	private EditText getTel;
	private EditText getDepart;
	private EditText getDorm;
	private EditText getClass;

	AU gAu = new AU(this);
	Handler gHAction = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
         	finish();
			Intent i=new Intent(RegisterActivity.this, MainActivity.class);
			startActivity(i);
        }
   };
	Handler gHtotast = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
        }   
   };
	private Button gbtnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_user);//显示注册页面
		getNum = (EditText) findViewById(R.id.et_reg_num);
		getName = (EditText) findViewById(R.id.et_reg_name);
		getPW=(EditText)findViewById(R.id.et_reg_pw);
		getPW1=(EditText)findViewById(R.id.et_reg_pw1);
		getTel = (EditText) findViewById(R.id.et_reg_tel);
		getDorm = (EditText) findViewById(R.id.et_reg_dorm);
		getDepart=(EditText)findViewById(R.id.et_reg_depart);
		getClass=(EditText)findViewById(R.id.et_reg_class);
		gbtnReg=(Button)findViewById(R.id.btn_reg_reg);
		
		gAu.setHtotast(gHtotast);
		gAu.setHAction(gHAction);
    }
    
    public void regist(View v){
    	String spw=getPW.getText().toString().trim();
    	String spw1=getPW1.getText().toString().trim();
    	if (spw1.length() < 1 || spw.length() < 1) {
			gAu.toast("Please Input  PassWord!");
			return;
		}
    	if (!spw.equals(spw1)) {
    		gAu.toast("Passwords do not match!");
			return;
		}
    	
    	String sname=getName.getText().toString().trim();
    	String snum=getNum.getText().toString().trim();
    	if (sname.length() < 1 || snum.length() < 1) {
			gAu.toast("Please Input  Name and Number!");
			return;
		}
    	
    	
    	String stel=getTel.getText().toString().trim();
    	String sdorm=getDorm.getText().toString().trim();
    	String sdepart=getDepart.getText().toString().trim();
    	String sclass=getClass.getText().toString().trim();
    	String sex="M";
    	RadioButton rboy=(RadioButton)findViewById(R.id.radio_reg_boy);
    	if (!rboy.isChecked()) {
			sex="W";
		}
    	Log.d("QGB-reg",sex);
    	//gAU.toast(sname+snum);
    	
    	//gbtnReg.setEnabled(false);
    	BX.reg(gAu, sname, snum, spw1, stel, sdorm, sdepart, sclass, sex);
 
    	//gbtnReg.setEnabled(true);
    }
    //注册返回
    public void registerBack(View V){
    	onBackPressed();
    }

	@Override
	public void onBackPressed() {
		Intent i=new Intent(RegisterActivity.this, LoginActivity.class);
    	startActivity(i);
    	finish();
	}
    
}