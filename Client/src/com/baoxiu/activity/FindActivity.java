package com.baoxiu.activity;

import qgb.U;
import frame.AU;
import static frame.BX.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FindActivity extends Activity{
	private EditText getName; 
	private EditText getSid; 
	private EditText getTitle;
	private EditText getProgress;
	private EditText getExplain;
	private EditText getEndtime;
	private EditText gettime;
	private EditText getDetail;
	AU gAu = new AU(this);
	Handler gHAction = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
         	finish();
			Intent i=new Intent(FindActivity.this, MainActivity.class);
			startActivity(i);
        }
   };
	Handler gHtotast = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
        }   
   };
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
		  if (gs==null) {
			return;
		}
			super.onCreate(savedInstanceState);
			setContentView(R.layout.select_baoxiu);
			//setContentView(R.layout.activity_register_user);
			
			gAu.setHtotast(gHtotast);
			gAu.setHAction(gHAction);
			
			getSid = (EditText) findViewById(R.id.et_f_sid);
			getName = (EditText) findViewById(R.id.et_f_name);
			getTitle=(EditText)findViewById(R.id.et_f_Title);
			getProgress=(EditText)findViewById(R.id.et_f_progress);
			getExplain = (EditText) findViewById(R.id.et_f_explain);
			getEndtime = (EditText) findViewById(R.id.et_f_endTime);
			gettime=(EditText)findViewById(R.id.et_f_Time);
			getDetail=(EditText)findViewById(R.id.et_f_detail);
		
			U.print("et=%s,gs=%s",getSid,gs);
			getSid.setText(gs.getIds()+"");
			getName.setText(gUser.getsName());
			getTitle.setText(gs.getStitle());
			getProgress.setText(gs.getSprogress());
			getExplain.setText(gs.getSexplain());
			getEndtime.setText(gs.getSendTime());
			gettime.setText(gs.getStime());
			getDetail.setText(gs.getSdetail());
			
	    }
	    
	    //查询
	    public void search(View v){
	    	
	    }
	    //查询返回
	    public void selectBack(View v){
	    	finish();
	    }
	   

	
}
