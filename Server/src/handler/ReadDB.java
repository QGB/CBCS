package handler;
import java.util.Map;

import qgb.*;
import frame.DB;
import frame.Set;
import frame.Start;
import httpserver.HttpHandler;
import httpserver.HttpRequest;
import httpserver.HttpResponse;

/**
 * params: 
 * f(field),
 * id
 * t:table
 * */
public class ReadDB extends HttpHandler {
	
	public static void main(String[] args) {
		U.browser("start http://172.17.5.26:8080/read?id=1&f=stDepart&t=User",999);
		Start.main();
		
	}
	
	@Override
	public void handle(HttpRequest req, HttpResponse rsp) {
		if (req.getParams().size()<3) {
			Set.coment(rsp,-1,"GET Params Count Error!");
			return;
		}
		
		String sfield="",sid="",stable="";
		for(Map.Entry<String, String> me: req.getParams().entrySet()){
			if (me.getKey().equals("f")) {
				sfield=me.getValue();
			}
			if (me.getKey().equals("id")) {
				sid=me.getValue();
			}
			if (me.getKey().equals("t")) {
				stable=me.getValue();
			}
			
		}
		
		rsp.setBody(DB.readDbByField(sid,sfield,stable));
	}

}
