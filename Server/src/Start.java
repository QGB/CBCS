import httpserver.HttpRouter;
import httpserver.HttpServer;

import java.lang.reflect.Field;

import qgb.T;

public class Start {
	public static void main(){main(null);}
	public static void main(String[] a){		
		T.print(T.getSourcePath(Start.class));
		try {
			Class.forName(DB.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
   	 T.setErrStream("err.txt");
     HttpServer s = new HttpServer(8080);
	 HttpRouter r = new HttpRouter();
	 r.addHandler("t", new myHandler());
	 r.addHandler("reg", new Reg());
	 s.setRouter(r);
	 s.run();
	}
}