package com.baoxiu.activity;

import qgb.annotation.tested;
import  frame.AU;
import static frame.BX.*;
import frame.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

public class RepairActivity extends Activity {	
	EditText getName,getTel,getDorm,getTitle,getDetail;
	AU gAu;
	Handler gHtotast = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
        }   
   };
	Handler gHAction = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
          	onBackPressed();
        }   
   };
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		//testData();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wo_yao_bao_xiu);
	
		gAu=new AU(this);
		gAu.setHtotast(gHtotast);
		gAu.setHAction(gHAction);
		getName=(EditText)findViewById(R.id.et_bx_name);
		getTel=(EditText)findViewById(R.id.et_bx_tel);
		getDorm=(EditText)findViewById(R.id.et_bx_dorm);
		getTitle=(EditText)findViewById(R.id.et_bx_title);
		getDetail=(EditText)findViewById(R.id.et_bx_detail);
		
		getName.setText(gUser.getsName());
		getTel.setText(gUser.getStel());
		getDorm.setText(gUser.getSdorm());
	}

    private void testData() {
    	gUser=new User(1, "asName", "asPw");
		gUser.setSdorm("dorm");
		gUser.setStel("123456");
	}

    //提交
    public void submit(View v){
    	String stitle=getTitle.getText().toString();
    	String sDetail=getDetail.getText().toString();

    	String stel=getTel.getText().toString();
    	String sdorm=getDorm.getText().toString();
    	
    	if (stitle.length()<1||sDetail.length()<1||stel.length()<1||sdorm.length()<1) {
			gAu.toast("Please Input Title and Detail !");
		}
    	if (!stel.equals(gUser.getStel())||!sdorm.equals(gUser.getSdorm())) {
    		gUser.setStel(stel);
    		gUser.setSdorm(sdorm);
    		writeUser(gAu);
		}
    	//gAu.toast("submit--");
    	repair(gAu,stitle,sDetail);
    }
  //返回
    public void repairBack(View v){
    	onBackPressed();
    }

	@Override
	public void onBackPressed() {
		Intent intent=new Intent(this, MainActivity.class);
    	startActivity(intent);
		finish();
	}
}