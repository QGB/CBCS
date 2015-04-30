package frame;

import java.util.ArrayList;

import android.os.Message;
import android.util.Log;
import qgb.T;
import qgb.U;
import qgb.net.QNet;


public final class BX {
	public static void main(String[] args) {
		
		String sfield = "", sid = "", stable = "", svalue = "";
		// write(sfield ,sid ,stable ,svalue);
		AU au;
		String sname="",smail="",spw="",stel="",sdorm="",sdepart="",sclass="",sex="";
		//reg(au,sname,smail, spw,stel,sdorm,sdepart,sclass,sex);
		try {
			U.print(QNet.html(gsDomain+gsHlogin));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int reg(final AU au, String sname, String snum, String spw,
			String stel, String sdorm, String sdepart, String sclass, String sex) {
		gUser=new User();
		gUser.setInum(snum);
		gUser.setSclass(sclass);
		gUser.setSdepart(sdepart);
		gUser.setSdorm(sdorm);
		gUser.setSex(sex);
		gUser.setsName(sname);
		gUser.setSpw(spw);
		gUser.setStel(stel);
		String shex=U.objToHex(gUser);
		final String surl = gsDomain
				+ gsHreg
				+ T.format("?obj=%s", shex);

		// au.toast(surl);
		final Message msg = new Message();   
		new Thread() {
			@Override
			public void run() {
				try {
					String str = QNet.html(surl);
					if (str==null||str.length()<2||str.startsWith("-")) {
						msg.obj=str;
						au.gHtotast.sendMessage(msg);
						return;
					}	
					str=T.sub(str, "","\n");
                    gidu =Integer.valueOf(str);
                	Log.d("QGBL",surl+"|"+str+"|"+gidu);
                    if(gidu<0){
			            msg.obj =str;  
			            au.gHtotast.sendMessage(msg); 
			        }else {
			        	loadUser();
			        	msg.obj="Hello "+gUser.getsName()+"(~~)";
						au.gHAction.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					msg.obj=e.toString();
					au.gHtotast.sendMessage(msg);
				}
			}

		}.start();
		return gidu;
	}

	public static int gidu=-1;
	public static User gUser;
	public static  ArrayList<Server> gals;
	public static Server gs;
	public static void loadUser() throws Exception{
		String sh =QNet.html(gsDomain+gsHuser+T.format("?uid=%s&t=r", gidu));
		sh=T.sub(sh, "","\n");
		gUser=null;
		gUser=(User) U.HexToObj(sh);
		if (gUser==null) {
			throw new Exception("User deSerialize Error!");
		}
	}
	public static String read(String asf) {
		if(gidu<0){
			return "";
		}
		String str=gsDomain+gsHread+ T.format("?f=%s&id=%s&t=%s", asf,gidu,gsTableUsr);
		Log.d("qgb-read",str);
		try {
			return QNet.html(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	public static void login(final AU au, String snum, String spw,String sType) {
		gidu=-11;
		final String surl = gsDomain
				+ gsHlogin
				+ T.format("?m=%s&p=%s&t=%s", snum, spw, sType);
		// au.toast(surl);
		new Thread() {
			@Override
			public void run() {
		        Message msg = new Message();   
				try {
					String str = QNet.html(surl);
					
					String  sti=T.sub(str, "","\n");
			        gidu=Integer.valueOf(sti);
			        Log.d("QGBL",str+"|"+gidu);

			        if(gidu<0){
			            msg.obj =str;  
			            au.gHtotast.sendMessage(msg); 
			        }else {
			        	loadUser();
			        	msg.obj="Hello "+gUser.getsName()+"(~~)";
						au.gHAction.sendMessage(msg);
					}
			        
				} catch (Exception e) {
					//gir=-11;
					e.printStackTrace();
					msg.obj=e.toString();
					au.gHtotast.sendMessage(msg);
				}
			}

		}.start();
		return;
	}
	
	
	/**
	 * params: f(field), id t:table v:value
	 * */
	public static void write(final AU au, int aid, String asfield,String asTable, String asValue) {
		if (aid < 0 || asfield == null || asTable == null || asValue == null) {
			au.toast("GET Params NUll!");
			return;
		}
		final String surl = gsDomain
				+ gsHwrite
				+ T.format("?id=%s&f=%s&t=%s&v=%s", aid, asfield, asTable,
						asValue);

		// au.toast(surl);
		new Thread() {
			@Override
			public void run() {
				try {
					final String str = QNet.html(surl);
					Message msg = new Message();   
                    msg.obj =str;   
                      
                    au.gHtotast.sendMessage(msg); 
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();   
                    msg.obj =e.toString();  
					au.gHtotast.sendMessage(msg);
				}
			}

		}.start();
	}

	public final static String gsDomain ="http://172.17.5.27:8765/";;// "http://192.168.137.1:8765/"
	public final static String gsHreg = "reg";
	public final static String gsHlogin = "login";
	public final static String gsHread = "read";
	public final static String gsHwrite = "write";
	public final static String gsHuser = "user";
	public final static String gsHserver = "server";
	/** ------User---------------- **/
	public final static String gsTableUsr = "User";
	public final static String gsCUid = "idu";
	public final static String gsCName = "stName";
	public final static String gsCNum = "stNum";
	public final static String gsCPW = "stPW";
	public final static String gsCTel = "stTel";
	public final static String gsCDorm = "stDorm";
	public final static String gsCDepart = "stDepart";
	public final static String gsClass = "stClass";
	public final static String gsCSex = "sex";
	/** ----------Maintainer----------------------- **/
	public final static String gsTableMan = "Maintainer";
	public final static String gsCMid = "idM";
	public final static String gsCAge = "stage";
	/** ----------Service----------------------- **/
	public final static String gsTableServ = "Service";
	public final static String gsCSid = "idS";
	public final static String gsCTitle = "stitle";
	public final static String gsCDetail = "sdetail";
	public final static String gsCTime = "stime";
	public final static String gsCProgress = "sProgress";
	public final static String gsCEndTime = "sEndTime";
	public final static String gsCExplain = "sExplain";
	/** gsCWord, gsCPhon, gsCountry, gsCUrl,gsCVoiceByte(blob) **/
	public final static String gsTablePhon = "Phons";
	public final static String gsCPhon = "stPhon";
	public final static String gsCountry = "stCountry";
	public static void repair(final AU au, String stitle, String sdetail) {
		//gAu.toast("Repair"+stitle);
		Server s=new Server(gUser.gidu, stitle, sdetail);
		Log.d("bx",s.toString());
		String sh = null;
		try {
			sh=U.objToHex(s);
		} catch (Exception e) {
			au.toast(e.toString());
			return;
		}
		final String surl = gsDomain	+ gsHserver
				+ T.format("?obj=%s&t=w",sh);
		// au.toast(surl);
		new Thread() {
			@Override
			public void run() {
		        Message msg = new Message();   
				try {
					String str = QNet.html(surl);
					
					String  sti=T.sub(str, "","\n");
			        int ir=Integer.valueOf(sti);
			        if(ir<0){
			            msg.obj =str;  
			            au.gHtotast.sendMessage(msg); 
			        }else {
			        	msg.obj="Successfully server:"+U.getCurrentTime();
						au.gHAction.sendMessage(msg);
					}
			        
				} catch (Exception e) {
					e.printStackTrace();
					msg.obj="Server Fail ."+e.toString();
					au.gHtotast.sendMessage(msg);
				}
			}

		}.start();
		return;
	}

	public static void writeUser(final AU au) {
		final String surl = gsDomain
				+ gsHuser
				+ T.format("?obj=%s&t=w",U.objToHex(gUser));
		// au.toast(surl);
		new Thread() {
			@Override
			public void run() {
		        Message msg = new Message();   
				try {
					String str = QNet.html(surl);
					
					String  sti=T.sub(str, "","\n");
			        int ir=Integer.valueOf(sti);
			        if(ir<0){
			            msg.obj =str;  
			            au.gHtotast.sendMessage(msg); 
			        }else {
			        	msg.obj="Successfully modified:"+U.getCurrentTime();
						au.gHAction.sendMessage(msg);
					}
			        
				} catch (Exception e) {
					e.printStackTrace();
					msg.obj="WriteUser Fail ."+e.toString();
					au.gHtotast.sendMessage(msg);
				}
			}

		}.start();
		return;
	}

	public static void loadServers(final AU au) {
		new Thread() {
			@Override
			public void run() {
		        Message msg = new Message();   
				try {
					String str =QNet.html(gsDomain+gsHserver+T.format("?uid=%s&t=r", gidu));
					if (str==null||str.length()<2||str.startsWith("-")) {
						msg.obj=str;
						au.gHtotast.sendMessage(msg);
						return;
					}	
					
					String sh=T.sub(str, "","\n");
					gals=null;
					gals=(ArrayList<Server>) U.HexToObj(sh);
					if (gals==null) {
						throw new Exception(str);
					}		
			        	msg.obj="Successfully Load Servers:"+U.getCurrentTime();
						au.gHAction.sendMessage(msg);
			        
				} catch (Exception e) {
					e.printStackTrace();
					msg.obj="load Servers Fail ."+e.toString();
					au.gHtotast.sendMessage(msg);
				}
			}

		}.start();
		
	}

	public static void setCurrentServer(String sid) {
		if (gals==null||gals.size()<1) {
			return;
		}
		for (Server s:gals) {
			if (sid.equals(s.getIds()+"") ) {
				gs=s;
				return;
			}
		}
	}
}
