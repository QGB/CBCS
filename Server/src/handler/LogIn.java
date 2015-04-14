package handler;
import java.util.Map;

import frame.DB;
import frame.Start;
import httpserver.HttpHandler;
import httpserver.HttpRequest;
import httpserver.HttpResponse;
import httpserver.Set;

/**
 * t(type)u:user,m:man
 * n:name
 * m:mail
 * 
 * Success:uid 1-...
 * fail:< -1
 * */
public class LogIn extends HttpHandler {

	public static void main(String[] args) {
		Start.main();
	}
	
	@Override
	public void handle(HttpRequest req, HttpResponse rsp) {
		if (req.getParams().size()<3) {
			Set.coment(rsp,-1,"GET Params Count Error!");
			return;
		}
		
		String sN="",spw="",st="";
		for(Map.Entry<String, String> me: req.getParams().entrySet()){
			if (me.getKey().equals("m")) {
				sN=me.getValue();
			}
			if (me.getKey().equals("p")) {
				spw=me.getValue();
			}
			if (me.getKey().equals("t")) {
				st=me.getValue();
			}
		}
		if (st!=null) {
			st=st.toLowerCase();
			if (st.equals("u")) {
				rsp.setBody(DB.checkUser(sN, spw));
				return;
			}else if (st.equals("m")) {
				rsp.setBody(DB.checkMan(sN, spw));
				return;
			}
		}
		rsp.setBody(Set.coment(-9,"Params Error!"));
		return;
		
	}

}
