package handler;
import java.util.Map;

import qgb.U;
import frame.DB;
import frame.Set;
import frame.Start;
import frame.User;
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
public class Reg extends HttpHandler {

	public static void main(String[] args) {
		Start.main();
		String sh="ACED00057372000A6672616D652E55736572B6407C3C6465C39102000949000467696475490004696E756D4C0005734E616D657400124C6A6176612F6C616E672F537472696E673B4C000673636C61737371007E00014C00077364657061727471007E00014C000573646F726D71007E00014C000373657871007E00014C000373707771007E00014C00047374656C71007E00017870FFFFFFFF0000007374000BE5988EE5988EE5988E515174000074000074000074000157740003717171740000";
		User u=(User) U.HexToObj(sh);
		U.print(u);
	}
	
	@Override
	public void handle(HttpRequest req, HttpResponse rsp) {
		if (req.getParams().size()<1) {
			Set.coment(rsp,-1,"GET Params Count Error!");
			return;
		}
		
		String sObj="",sname="",smail="",spw="",stel="",sdorm="",sdepart="",sclass="",sex="";
		for(Map.Entry<String, String> me: req.getParams().entrySet()){
			if (me.getKey().equals("obj")) {
				sObj=me.getValue();
			}
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
		
		try {
			if (sObj==null||sObj.length()<2) {
				throw new Exception("obj User is null");
			}
			User u = (User) U.HexToObj(sObj);
			if (u==null)throw new Exception(sObj);
			rsp.setBody(DB.reg(u));
		} catch (Exception e) {
			Set.coment(rsp, -21, e.toString());
		}
	
		
	}

//	private String regUesr(String sname, String smail) {
//		return "0";
//	}
//	

}
