package handler;
import java.util.Map;

import frame.DB;
import frame.Set;
import frame.Start;
import httpserver.HttpHandler;
import httpserver.HttpRequest;
import httpserver.HttpResponse;

/**
 * n:name
 * m:mail
 * 
 * Success:uid 1-...
 * fail:< -1
 * */
public class Up extends HttpHandler {

	public static void main(String[] args) {
		Start.main();
	}
	
	@Override
	public void handle(HttpRequest req, HttpResponse rsp) {
		if (req.getParams().size()<2) {
			Set.coment(rsp,-1,"GET Params Count Error!");
			return;
		}
		
		String smail="",spw="";
		for(Map.Entry<String, String> me: req.getParams().entrySet()){
			if (me.getKey().equals("m")) {
				smail=me.getValue();
			}
			if (me.getKey().equals("p")) {
				spw=me.getValue();
			}
		}
		rsp.setBody(DB.checkUser(smail, spw));
	}

}
