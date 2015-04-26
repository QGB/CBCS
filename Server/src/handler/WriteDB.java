package handler;
import java.util.Map;

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
 * v:value
 * */
public class WriteDB extends HttpHandler {

	public static void main(String[] args) {
		Start.main();
	}
	
	@Override
	public void handle(HttpRequest req, HttpResponse rsp) {
		if (req.getParams().size()<4) {
			Set.coment(rsp,-1,"GET Params Count Error!");
			return;
		}
		
		String sfield="",sid="",stable="",svalue="";
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
			if (me.getKey().equals("v")) {
				svalue=me.getValue();
			}
		}
		
		rsp.setBody(DB.writeDbByField(sid, sfield, stable, svalue));
	}

}
