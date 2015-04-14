package handler;
import java.util.Map;

import frame.DB;
import frame.Start;
import httpserver.HttpHandler;
import httpserver.HttpRequest;
import httpserver.HttpResponse;
import httpserver.Set;

/**
 * n:name
 * p:pW
 * 
 * Success:uid 1-...
 * fail:< -1
 * */
public class ManLogIn extends HttpHandler {

	public static void main(String[] args) {
		Start.main();
	}
	
	@Override
	public void handle(HttpRequest req, HttpResponse rsp) {
		if (req.getParams().size()<2) {
			Set.coment(rsp,-1,"GET Params Count Error!");
			return;
		}
		
		String sname="",spw="";
		for(Map.Entry<String, String> me: req.getParams().entrySet()){
			if (me.getKey().equals("n")) {
				sname=me.getValue();
			}
			if (me.getKey().equals("p")) {
				spw=me.getValue();
			}
		}
		rsp.setBody(DB.checkMan(sname, spw));
	}

}