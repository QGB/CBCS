package handler;
import frame.Start;
import httpserver.HttpException;
import httpserver.HttpRequest;
import httpserver.HttpResponse;
import httpserver.HttpRouter;
import httpserver.Route;
import qgb.*;
import httpserver.*;

  public class myHandler extends HttpHandler {
     public static void main(String[] args) {
    	 Start.main(null);
     }

     static int gi=0;
	@Override
	public void handle(HttpRequest request, HttpResponse response) {
		U.print("------------------------------");
		U.printInnerSt(request); 
		//U.msgbox(request.getFullPath());
		U.print("------------%s",U.getCurrentTime());
		//response.setBody("Hello World!\n"+(++gi));
		response.setMimeType("");
		response.setBody(U.readByteArray("1.zip"));
		//response.
		response.respond();
	}
     
// 
//     public void index(HttpResponse resp) {
//         resp.setBody("Hello World!");
//     }
 }
