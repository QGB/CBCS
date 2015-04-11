package httpserver;

public class Set {
	/**开启返回注释*/
	public final static boolean gbOpenRespComment = true;

	public static void coment(HttpResponse rsp,int ai,String string) {
		if(gbOpenRespComment){
			rsp.setBody(ai+"\n"+string);
		}else {
			rsp.setBody(ai+"");
		}
		
	}
	public static String coment(int ai, String string) {
		if(gbOpenRespComment){
			return ai+"\n"+string;
		}else {
			return ai+"";
		}
		
	}
}
