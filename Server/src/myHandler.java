import httpserver.HttpException;
import httpserver.HttpRequest;
import httpserver.HttpResponse;
import httpserver.HttpRouter;
import httpserver.Route;
import qgb.T;
import httpserver.*;

  public class myHandler extends HttpHandler {
     public static void main(String[] args) {
    	 Start.main(null);
     }

     static int gi=0;
	@Override
	public void handle(HttpRequest request, HttpResponse response) {
		T.print("------------------------------");
		T.printInnerSt(request); 
		//T.msgbox(request.getFullPath());
		T.print("------------%s",T.getCurrentTime());
		response.setBody("Hello World!\n"+(++gi));
	}
     
// 
//     public void index(HttpResponse resp) {
//         resp.setBody("Hello World!");
//     }
 }
