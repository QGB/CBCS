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
 * t(type)w:write[t=w,o],
 * 
 * r:read[t=r,uid];
 * */
public class UserObj extends HttpHandler {

	public static void main(String[] args) {
		 //Start.main();
		U.print(U.gstEclipseA);
	}

	@Override
	public void handle(HttpRequest req, HttpResponse rsp) {
		if (req.getParams().size() < 2) {
			Set.coment(rsp, -1, "GET Params Count Error!");
			return;
		}

		String sObj = "", suid = "", st = "";
		for (Map.Entry<String, String> me : req.getParams().entrySet()) {
			if (me.getKey().equals("obj")) {
				sObj = me.getValue();
			}
			if (me.getKey().equals("t")) {
				st = me.getValue();
			}
			if (me.getKey().equals("uid")) {
				suid = me.getValue();
			}
		}
		if (st != null) {
			st = st.toLowerCase();
			if (st.equals("w")) {
				try {
					if (sObj == null || sObj.length() < 2)
						U.argsError(sObj, st);
					User u = (User) U.HexToObj(sObj);
					rsp.setBody(DB.writeUser(u));
				} catch (Exception e) {
					Set.coment(rsp, -21, e.toString());
				}
			} else if (st.equals("r")) {
				try {
					if (suid == null || suid.length() < 1)
						U.argsError(suid, st);
					rsp.setBody(DB.getUserHex(suid));
				} catch (Exception e) {
					Set.coment(rsp, -21, e.toString());
				}
			}
		} else {
			Set.coment(rsp, -22, "Parms Null");
		}
		return;

	}

}
