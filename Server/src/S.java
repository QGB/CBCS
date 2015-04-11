import qgb.T;


import httpserver.*;
2 3 public class myHandler extends HTTPHandler {
4     public static void main(String[] args) {
5         try {
6             HTTPServer s = new HTTPServer();
7             HTTPRouter r = new HTTPRouter();
8             r.addHandler("*", new myHandler());
9 
10             s.setRouter(r);
11             s.run();
12         } catch (HTTPException e) {
13             System.out.println("Server encountered an exception...");
14             e.printStackTrace();
15         }
16     }
17 
18     public myHandler() throws HTTPException {
19         addGET("/", "index");
20     }
21 
22     public void index(HTTPResponse resp) {
23         resp.setBody("Hello World!");
24     }
25 }
