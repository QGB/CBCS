package frame;

import qgb.U;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class AU {
	public final Context gContext;
	
	public AU(Context gContext) {
		this.gContext = gContext;
	}
	private static Toast gtoast;

	


	public void toast(String ast) {
		if (ast==null||ast.length()<1) {
			return;
		}
		//Log.d("qgb-totast",gtoast);
		//U.print(gtoast);
		//if(true) return;
		 if(gtoast==null){  
		        gtoast=Toast.makeText(gContext,ast, Toast.LENGTH_SHORT);
		    }else{  
		        gtoast.setText(ast);  
		 }  
		gtoast.show();
		//Toast.makeText(gContext,ast, Toast.LENGTH_LONG).show();
	}

	public Handler gHtotast;

	public void setHtotast(Handler myHandler) {
		gHtotast=myHandler;
	}
	
	public Handler gHAction;
	public void setHAction(Handler myHandler) {
		gHAction=myHandler;
	}

}
