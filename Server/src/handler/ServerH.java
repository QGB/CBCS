package handler;

import java.util.Map;

import qgb.U;
import frame.DB;
import frame.Set;
import frame.Start;
import httpserver.HttpHandler;
import httpserver.HttpRequest;
import httpserver.HttpResponse;

public class ServerH extends HttpHandler {

	public static void main(String[] args) {
		//Start.main();
		U.print(U.gstEclipseA);
	}
	
	@Override
	public void handle(HttpRequest req, HttpResponse rsp) {
		if (req.getParams().size()<3) {
			Set.coment(rsp,-1,"GET Params Count Error!");
			return;
		}
		
		String suid="",stitle="",sdetail="";
		for(Map.Entry<String, String> me: req.getParams().entrySet()){
			if (me.getKey().equals("uid")) {
				suid=me.getValue();
			}

			if (me.getKey().equals("title")) {
				stitle=me.getValue();
			}
			if (me.getKey().equals("detail")) {
				sdetail=me.getValue();
			}

		}
		rsp.setBody(DB.server(suid,stitle,sdetail));
	}


}