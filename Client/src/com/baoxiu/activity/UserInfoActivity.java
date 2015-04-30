package com.baoxiu.activity;

import static frame.BX.gUser;
import frame.AU;
import frame.BX;
import frame.User;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
public class UserInfoActivity extends Activity{
	EditText getName,getTel,getDorm,getClass,getDepart,getNum;
	AU gAu;
	Handler gHtotast = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
        }   
   };
	Handler gHAction = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
         	finish();
			Intent i=new Intent(UserInfoActivity.this, MainActivity.class);
			startActivity(i);
        }
   };
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.person_information);
			
			gAu=new AU(this);
			gAu.setHtotast(gHtotast);
			gAu.setHAction(gHAction);
			getName=(EditText)findViewById(R.id.et_ui_name);
			getTel=(EditText)findViewById(R.id.et_ui_tel);
			getDorm=(EditText)findViewById(R.id.et_ui_dorm);
			getClass=(EditText)findViewById(R.id.et_ui_class);
			getDepart=(EditText)findViewById(R.id.et_ui_depart);
			getNum=(EditText)findViewById(R.id.et_ui_num);
			
			getName.setText(gUser.getsName());
			getTel.setText(gUser.getStel());
			getDorm.setText(gUser.getSdorm());
			
			getClass.setText(gUser.getSclass());
			getDepart.setText(gUser.getSdepart());
			getNum.setText(gUser.getInum());
	  }
	    //修改个人资料
	    public void submit(View v){
	    	String sname=getName.getText().toString();
	    	String stel=getTel.getText().toString();
	    	String sdorm=getDorm.getText().toString();
	    	String sclass=getClass.getText().toString();
	    	String sdepart=getDepart.getText().toString();
	    	String snum=getNum.getText().toString();
	    	
	    	if (sname.length()<1||stel.length()<1||sdorm.length()<1||
	    			sclass.length()<1||sdepart.length()<1||snum.length()<1) {
				gAu.toast("Not null !");
				return;
			}
	    	int inum=Integer.valueOf(snum);
	    	if (inum<1) {
				gAu.toast("Num Format Error!");
			}
	    	gUser.setsName(sname);
	    	gUser.setSclass(sclass);
	    	gUser.setInum(inum+"");
	    	gUser.setSdepart(sdepart);
	    	gUser.setSdorm(sdorm);
	    	gUser.setStel(stel);
	    	BX.writeUser(gAu);
	    }
	    //个人资料返回
	    public void back(View v){
	    	onBackPressed();
	    }

		@Override
		public void onBackPressed() {
			Intent intent=new Intent(this, MainActivity.class);
	    	startActivity(intent);
			finish();
		}
	    
}
