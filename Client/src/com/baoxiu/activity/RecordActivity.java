package com.baoxiu.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import frame.AU;
import static frame.BX.*;
import frame.Server;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class RecordActivity extends Activity {
	AU gAu = new AU(this);
	
	Handler gHtotast = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
        }   
   };
	Handler gHAction = new Handler() {  
        public void handleMessage(Message msg) {   
             gAu.toast(msg.obj.toString());
         	
        	 List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
             for(Server s:gals){
             	HashMap<String,Object>map =	new HashMap<String,Object>();
             	map.put("id", s.getIds());
             	map.put("t", s.getStitle());
             	map.put("d", s.getSdetail());
             	data.add(map);
             }
             System.out.println(data);
             SimpleAdapter adapter = new SimpleAdapter(RecordActivity.this, data, R.layout.item, 
             		new String[]{"id","t","d"}, new int[]{R.id.i_id,R.id.i_t,R.id.i_d});
             glv.setAdapter(adapter);
        }
   };
   ListView glv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baoxiu_record);
        gAu.setHAction(gHAction);
        gAu.setHtotast(gHtotast);
        glv = (ListView)this.findViewById(R.id.listview);
       //BX
        glv.setOnItemClickListener(new ItemClickListener());
        loadServers(gAu);
    }
    public void refresh(View v){
    	 loadServers(gAu);
    }
  //返回
    public void back(View v){
    	onBackPressed();
    }

	@Override
	public void onBackPressed() {
		Intent intent=new Intent(this, MainActivity.class);
    	startActivity(intent);
		finish();
	}
	//获取点击事件    
    private final class ItemClickListener implements OnItemClickListener{

		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			ListView listView = (ListView) parent;
			HashMap<String, Object> data = (HashMap<String, Object>) listView.getItemAtPosition(position);
			String sid = data.get("id").toString();
			setCurrentServer(sid);
			if (gs==null) {
				gAu.toast("Error :can not find Server sid="+sid);
			}
			Intent intent=new Intent(RecordActivity.this, FindActivity.class);
	    	startActivity(intent);
			//gAu.toast(personid);
		}

		}
    }
