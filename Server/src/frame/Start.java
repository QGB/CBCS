package frame;
import handler.*;
import httpserver.HttpRouter;
import httpserver.HttpServer;
import qgb.*;

public class Start {
	public static void main(){main(null);}
	public static void main(String[] a){		
		U.print(U.getSourcePath(Start.class));
		try {
			Class.forName(DB.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
	 U.setErrStream("err.txt");
     HttpServer s = new HttpServer(8765);
	 HttpRouter r = new HttpRouter();
	// r.addHandler("t.zip", new myHandler());
	 r.addHandler("reg", new Reg());
	 r.addHandler("login", new LogIn());
	 r.addHandler("read", new ReadDB());
	 r.addHandler("write", new WriteDB());
	 r.addHandler("user", new UserObj());
	 r.addHandler("server", new ServerObj());
	 s.setRouter(r);
	 s.run();
	}
}