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
 * m:mail
 * 
 * Success:uid 1-...
 * fail:< -1
 * */
public class Reg extends HttpHandler {

	public static void main(String[] args) {
		Start.main();
	}
	
	@Override
	public void handle(HttpRequest req, HttpResponse rsp) {
		if (req.getParams().size()<3) {
			Set.coment(rsp,-1,"GET Params Count Error!");
			return;
		}
		
		String sname="",smail="",spw="",stel="",sdorm="",sdepart="",sclass="",sex="";
		for(Map.Entry<String, String> me: req.getParams().entrySet()){
			if (me.getKey().equals("n")) {
				sname=me.getValue();
			}
			if (me.getKey().equals("m")) {
				smail=me.getValue();
			}
			if (me.getKey().equals("p")) {
				spw=me.getValue();
			}
			if (me.getKey().equals("tel")) {
				stel=me.getValue();
			}
			if (me.getKey().equals("dorm")) {
				sdorm=me.getValue();
			}
			if (me.getKey().equals("dep")) {
				sdepart=me.getValue();
			}
			if (me.getKey().equals("class")) {
				sclass=me.getValue();
			}
			if (me.getKey().equals("sex")) {
				sex=me.getValue();
			}
		}
		rsp.setBody(DB.reg(sname,smail, spw,stel,sdorm,sdepart,sclass,sex));
	}

//	private String regUesr(String sname, String smail) {
//		return "0";
//	}
//	

}
