package frame;
import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import qgb.*;

public class DB {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//U.print(server(1+""," stitle", "sdetail")); 
		//testSqlite();
		//printField();
		Start.main();
		//U.print("id=1&f=%s&t=%s",gsCDepart,gsTableUsr);
		//testSqlite();
//		U.print(UIdExists("1"));
		String st=getServersByUID("3");
		st=T.sub(st, "","\n");
		U.print("%s\n%s",st,st.length());
		
		ArrayList<Server> als=(ArrayList<Server>) U.HexToObj(st);
		U.print(als );
		
		
//		U.print(st);
//		st=T.sub(st, "", "\n");
//		User u=(User) U.HexToObj(st);
//		U.print(u.toString());
//		u.gsName="qgb--";
//		st=U.objToHex(u);
//		U.print(writeUser(u));

	}

	private static void testSqlite() throws ClassNotFoundException {
		Class.forName(DB.class.getName());
		U.print(reg("name","311","qqq"," stel", "置还能提高服务器的", "置还能"," sclass","W"));
		U.print(writeDbByField("1",gsCDepart, gsTableUsr,"ttt"));
		U.print(readDbByField("1", gsCDepart,gsTableUsr));
		U.exit();
	}

	private static void printField() {
		Class<DB> cDB = DB.class;
		Field[] yf = cDB.getDeclaredFields();
		//U.msgbox(cDB.getName());
		U.print("/**");
		for (int i = 0; i < yf.length; i++) {
			String str = yf[i].toString();
			str = T.format("* import static %s;</br>",
					str.substring("static final java.lang.String ".length(), str.length()));
			U.print(str);
		}
		U.print("*/");
	}

	/**************** Test End ************************/
	public static Connection gConn;
	public static Statement gStat;
	/** TODO:DB Test */
	final private static String gsDbName = U.autoPath("server.db"); // "./mh.db";
	/** ------User---------------- **/
	final static String gsTableUsr = "User";
	final static String gsCUid = "idu";
	final static String gsCName = "stName";
	final static String gsCNum = "stNum";
	final static String gsCPW = "stPW";
	final static String gsCTel = "stTel";
	final static String gsCDorm = "stDorm";
	final static String gsCDepart = "stDepart";
	final static String gsClass = "stClass";
	final static String gsCSex = "sex";
	/**----------Maintainer----------------------- **/
	final static String gsTableMan = "Maintainer";
	final static String gsCMid = "idM";
	final static String gsCAge = "stage";
	/**----------Service----------------------- **/
	final static String gsTableServ = "Service";
	final static String gsCSid = "idS";
	final static String gsCTitle = "stitle";
	final static String gsCDetail = "sdetail";
	final static String gsCTime = "stime";
	final static String gsCProgress= "sProgress";
	final static String gsCEndTime= "sEndTime";
	final static String gsCExplain = "sExplain";
	/** gsCWord, gsCPhon, gsCountry, gsCUrl,gsCVoiceByte(blob) **/
	final static String gsTablePhon = "Phons";
	final static String gsCPhon = "stPhon";
	final static String gsCountry = "stCountry";
	//private final static TaskQueue gDBThread = new TaskQueue();
	static {
		try {
			Class.forName("org.sqlite.JDBC");
			// 建立连接
			gConn = DriverManager.getConnection("jdbc:sqlite:" + gsDbName, "",
					"");
			gConn.setAutoCommit(true);
			gStat = gConn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				gConn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		tableExists();
		
	}
	
	public static String writeDbByField(String asid, String asfield,String asTable,String asValue) {
		if (asid == null||asfield==null||asTable==null||asValue==null)
			return Set.coment(-2, "GET Params NUll!");

		if (asid.length() < 1||asfield.length()<2||asTable.length()<1) {
			return Set.coment(-3, "length() <1,2");
		}

		PreparedStatement prep = null;
		String sql="",sCid="",stable="";
		if (asTable.equals(gsTableUsr)) {
			stable=gsTableUsr;sCid=gsCUid;
		}else if (asTable.equals(gsTableMan)) {
			stable=gsTableMan;sCid=gsCMid;
		}else if (asTable.equals(gsTableServ)) {
			stable=gsTableServ;sCid=gsCSid;
		}
		
		sql = T.format("UPDATE %s SET %s = ? WHERE %s = ?;",stable,asfield,sCid);
		//sql= T.format("UPDATE %s SET %s = '%s' WHERE %s = %s;",stable,asfield,asValue,sCid,asid);
		//U.msgbox(sql);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, asValue);
			prep.setString(2, asid);
			prep.execute();
			return Set.coment(1, "Successfuly excute:"+sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Set.coment(-5, "Unknow Error!");
	}
	
	public static boolean UIdExists(String asuid) {
		if (asuid==null||asuid.length()<1) {
			return false;
		}
		try {
			PreparedStatement prep = gConn.prepareStatement(T.format(
					"Select * From %s where %s=?;", gsTableUsr, gsCUid));
			prep.setString(1, asuid);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				//U.msgbox( rs.getString(gsCNum));
				return rs.getString(gsCUid).equals(asuid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	// Statement
	public static boolean UserNumExists(String ast) {
		try {
			PreparedStatement prep = gConn.prepareStatement(T.format(
					"Select * From %s where %s=?;", gsTableUsr, gsCNum));
			prep.setString(1, ast);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				//U.msgbox( rs.getString(gsCNum));
				return rs.getString(gsCNum).equals(ast);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static String checkUser(String asNum,String asPW){
		if (asNum == null||asPW==null)
			return Set.coment(-1, "GET Params NUll!");

		if (asNum.length() < 3||asPW.length()<3) {
			return Set.coment(-2, "length() < 3");
		}
		if (!UserNumExists(asNum)) {
			return Set.coment(-6, "The User Num Not exists");
		}
		
		PreparedStatement prep = null;
		try {
			prep = gConn.prepareStatement(T.format(
					"Select * From %s where %s=?;", gsTableUsr, gsCNum));
			prep.setString(1, asNum);
			ResultSet rs = prep.executeQuery();
			// QJDBU.print(rs);
			int id = -1;
			while (rs.next()) {
				if (rs.getString(gsCNum).equals(asNum)) {
					if (rs.getString(gsCPW).equals(asPW)){
						id = Integer.valueOf(rs.getString(gsCUid));
						return Set.coment(id, "Successfully logIn!");
					}
				}
			}
			return Set.coment(-7, "PassWord incorrect!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Set.coment(-5, "Unknow Error!");
	}

	public static String reg(User u) {
		return reg(u.getsName(), u.getInum(), u.getSpw(),u.getStel(),u.getSdorm(),u.getSdepart(),u.getSclass(),u.getSex());
	}
	
	public static String reg(String asName, String asNum,String asPW, String stel, String sdorm, String sdepart, String sclass, String sex) {
		if (asName == null || asNum == null||asPW==null)
			return Set.coment(-1, "GET Params NUll!");

		if (asName.length() < 3 || asNum.length() < 3||asPW.length()<3) {
			return Set.coment(-2, "length() < 3");
		}

		try {
			if (Integer.valueOf(asNum)<1) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e1) {
			return Set.coment(-3, "Num Format Error!");
		}
		
		if (UserNumExists(asNum)) {
			return Set.coment(-4, "The Num already exists");
		}
		if (sex!=null) {
			if (sex.length()>1) {
				return Set.coment(-8, "Sex Format Error!");
			}
		}
		PreparedStatement prep = null;
		String sql = T.format("INSERT INTO %s(%s,%s,%s  ,%s,%s,%s,%s,%s) VALUES(?,?,?  ,?,?,?,?,?);",
				gsTableUsr, gsCName, gsCNum,gsCPW   ,gsCTel,gsCDorm,gsCDepart,gsClass,gsCSex);
		//U.msgbox("n=%s,m=%s,p=%s",asName,asNum,asPW);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, asName);
			prep.setString(2, asNum);
			prep.setString(3, asPW);
			
			prep.setString(4, stel);
			prep.setString(5, sdorm);
			prep.setString(6, sdepart);
			prep.setString(7, sclass);
			prep.setString(8, sex);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			prep = gConn.prepareStatement(T.format(
					"Select * From %s where %s=?;", gsTableUsr, gsCNum));
			prep.setString(1, asNum);
			ResultSet rs = prep.executeQuery();
			// QJDBU.print(rs);
			//int id = -1;
			while (rs.next()) {
				if (rs.getString(gsCNum).equals(asNum)) {
					String str=rs.getString(gsCUid);
					//U.print("[%s]",str);
					if (str==null||str.length()<1) {
						throw new Exception("select UID is null");
					}
					//id = Integer.valueOf(rs.getString(gsCUid));
					return Set.coment(str, "Successfully regist!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Set.coment(-4, e.toString());
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Set.coment(-5, "Unknow Error!");
	}
	/*************Ma******************/
	public static String checkMan(String asName,String asPW){
		if (asName == null||asPW==null)
			return Set.coment(-1, "GET Params NUll!");

		if (asName.length() < 3||asPW.length()<3) {
			return Set.coment(-2, "length() < 3");
		}
		if (!ManExists(asName)) {
			return Set.coment(-6, "The Man name Not exists");
		}
		
		PreparedStatement prep = null;
		try {
			prep = gConn.prepareStatement(T.format(
					"Select * From %s where %s=?;", gsTableMan, gsCName));
			prep.setString(1, asName);
			ResultSet rs = prep.executeQuery();
			// QJDBU.print(rs);
			int id = -1;
			while (rs.next()) {
				if (rs.getString(gsCName).equals(asName)) {
					if (rs.getString(gsCPW).equals(asPW)){
						id = Integer.valueOf(rs.getString(gsCUid));
						return Set.coment(id, "Man Successfully logIn!");
					}
				}
			}
			return Set.coment(-7, "Man PassWord incorrect!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Set.coment(-5, "CheckMan Unknow Error!");
	}
	
	public static boolean ManExists(String asName) {
		try {
			PreparedStatement prep = gConn.prepareStatement(T.format(
					"Select * From %s where %s=?;", gsTableMan, gsCName));
			prep.setString(1, asName);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				return rs.getString(gsCName).equals(asName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void error(SQLException e, PreparedStatement prep) {
		try {
			U.print(prep.getParameterMetaData().toString());
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private static void error(SQLException e, String... yst) {
		U.print(yst);
		e.printStackTrace();
	}

	public static boolean DbExists() {
		return new File(U.autoPath(gsDbName)).exists();
	}
//
	public static boolean tableExists() {
		try {
			createTable();
			return false;
		} catch (SQLException e) {
			//U.msgbox();
			if (e.getMessage().contains("table User already exists")) {
				return true;
			}
			e.printStackTrace();
		}
		return false;
	}
	/** 在没有数据库时才建表 **/
	private static void createTable()throws SQLException {
		String sql ="";
		try {
			sql = T.format("create table %s("
					+ "%s integer,%s char(50) NOT NULL,"
					+ "%s char(50) NOT NULL,%s char(50) NOT NULL,%s char(50),%s char(50),%s char(50),%s char(50),%s char(2)"  + ",PRIMARY KEY (%s));",
					gsTableUsr, gsCUid, gsCName, gsCNum,gsCPW,gsCTel,gsCDorm,gsCDepart,gsClass,gsCSex, gsCUid);
			gStat.executeUpdate(sql);
			
			sql = T.format("create table %s("
					+ "%s integer,%s char(50) NOT NULL,"
					+ "%s char(50) NOT NULL,%s char(20),%s char(3),%s char(1)"  + ",PRIMARY KEY (%s));",
					gsTableMan, gsCMid, gsCName,gsCPW,gsCTel,gsCAge,gsCSex,gsCMid);
			gStat.executeUpdate(sql);
			
			sql = T.format("create table %s("
					+ "%s integer,%s integer NOT NULL,"
					+ "%s char(50) NOT NULL,%s char(255),%s char(20) NOT NULL,%s char(10),%s char(20),%s char(255),%s integer"  + ",PRIMARY KEY (%s));",
					gsTableServ, gsCSid, gsCUid,gsCTitle,gsCDetail,gsCTime,gsCProgress,gsCEndTime,gsCExplain,gsCMid,gsCSid);
			//U.msgbox(sql);
			gStat.executeUpdate(sql);

		}catch (SQLException e) {
			throw e;
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public static String readDbByField(String asid, String asfield,String asTable) {
		if (asid == null||asfield==null||asTable==null)
			return Set.coment("", "GET Params NUll!");

		if (asid.length() < 1||asfield.length()<2||asTable.length()<1) {
			return Set.coment("", "length() <1,2");
		}

		PreparedStatement prep = null;
		String sql="",sCid="";
		try {
			if (asTable.equals(gsTableUsr)) {
				sCid=gsCUid;
				sql=T.format("Select * From %s where %s=?;", gsTableUsr, gsCUid);
			}else if (asTable.equals(gsTableMan)) {
				sCid=gsCMid;
				sql=T.format("Select * From %s where %s=?;", gsTableMan, gsCMid);
			}else if (asTable.equals(gsTableServ)) {
				sCid=gsCSid;
				sql=T.format("Select * From %s where %s=?;", gsTableServ, gsCSid);
			}
			if (sql.length()<1) {
				return Set.coment("", "Table not found!");
			}
			prep = gConn.prepareStatement(sql);
			prep.setString(1, asid);
			ResultSet rs = prep.executeQuery();
			// QJDBU.print(rs);
			int id = -1;
			while (rs.next()) {
				if (rs.getString(sCid).equals(asid)) {
					String str=rs.getString(asfield);
					if (str!=null){
						return Set.coment(str, "Successfully get field:"+asfield);
					}else {
						return Set.coment("", "rs.getString(asfield)=null");
					}
				}
			}
			return Set.coment("", "id not found!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Set.coment("", "Unknow Error!");
	}

	public static String writeUser(User au) {
		if (!UIdExists(au.gidu+"")) {
			return reg(au);
		}
		PreparedStatement prep = null;
		String sql = T.format("%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?",
				gsCName, gsCNum,gsCPW      ,gsCTel,gsCDorm,gsCDepart,gsClass,gsCSex);
		sql = T.format("UPDATE %s SET "+sql+" WHERE %s = ?;",gsTableUsr,gsCUid);
		//U.msgbox("n=%s,m=%s,p=%s",asName,asNum,asPW);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, au.getsName());
			prep.setString(2, au.getInum());
			prep.setString(3, au.getSpw());
			
			prep.setString(4, au.getStel());
			prep.setString(5, au.getSdorm());
			prep.setString(6, au.getSdepart());
			prep.setString(7, au.getSclass());
			prep.setString(8, au.getSex());
			
			prep.setString(9, au.gidu+"");
			prep.execute();
			return Set.coment(1,"Sucess write:"+au );
		} catch (SQLException e) {
			e.printStackTrace();
			return Set.coment(-6,e.toString() );
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	public static String getUserHex(final String asuid) {
		if (!UIdExists(asuid+"")) {
			return Set.coment(-1, "uid"+asuid +"not exists!");
		}
		PreparedStatement prep = null;
		String sql="";
		try {
			sql=T.format("Select * From %s where %s=?;", gsTableUsr, gsCUid);
			prep = gConn.prepareStatement(sql);
			prep.setString(1, asuid);
			ResultSet rs = prep.executeQuery();
			// QJDBU.print(rs);
			User u=new User(Integer.valueOf(asuid));
			while (rs.next()) {
				if (rs.getString(gsCUid).equals(asuid)) {
					u.setsName(rs.getString(gsCName));
					u.setInum(rs.getString(gsCNum));
					u.setSclass(rs.getString(gsClass));
					u.setSdepart(rs.getString(gsCDepart));
					u.setSdorm(rs.getString(gsCDorm));
					u.setSex(rs.getString(gsCSex));
					u.setSpw(rs.getString(gsCPW));
					u.setStel(rs.getString(gsCTel));
				}
			}
			return Set.coment(U.objToHex(u), "success read uid="+u.gidu) ;
		} catch (Exception e) {
			e.printStackTrace();
			return Set.coment(-2, e.toString());
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
/**TODO: muti title*/
	public static String server(String suid,String stitle, String sdetail) {
		if (suid == null ||stitle==null||sdetail==null)
			return Set.coment(-1, "GET Params NUll!");

		if (suid.length() < 1 ||stitle.length()<1||sdetail.length()<1) {
			return Set.coment(-2, "length() < 1");
		}
		
		if (!UIdExists(suid)) {
			return Set.coment(-4, "The UId not exists");
		}
		PreparedStatement prep = null;
		String sql = T.format("INSERT INTO %s(%s,%s,%s,%s) VALUES(?,?,?,?);",
				gsTableServ, gsCUid, gsCTitle,gsCDetail,gsCTime);
		//U.msgbox("n=%s,m=%s,p=%s",asName,asNum,asPW);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, suid);
			prep.setString(2, stitle);
			prep.setString(3,sdetail);
			prep.setString(4,U.getCurrentTime());
			prep.execute();
			return Set.coment(1,"Sucessfully server:"+suid+","+stitle);
		} catch (SQLException e) {
			e.printStackTrace();
			return Set.coment(-6,e.toString() );
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

public static String getServersByUID(String asuid) {
	if (!UIdExists(asuid+"")) {
		return Set.coment(-1, "uid"+asuid +"not exists!");
	}
	PreparedStatement prep = null;
	String sql="";
	try {
		sql=T.format("Select * From %s where %s=?;", gsTableServ, gsCUid);
		prep = gConn.prepareStatement(sql);
		prep.setString(1, asuid);
		ResultSet rs = prep.executeQuery();
		// QJDBU.print(rs);
		ArrayList<Server> als=new ArrayList<Server>();
		int idu=Integer.valueOf(asuid);
		while (rs.next()) {
			if (rs.getString(gsCUid).equals(asuid)) {
				Server s=new Server(idu);
				
				s.setIdm(rs.getString(gsCMid));
				s.setIds(rs.getString(gsCSid));
				s.setSdetail(rs.getString(gsCDetail));
				s.setSendTime(rs.getString(gsCEndTime));
				s.setSexplain(rs.getString(gsCExplain));
				s.setSprogress(rs.getString(gsCProgress));
				s.setStime(rs.getString(gsCTime));
				s.setStitle(rs.getString(gsCTitle));
				als.add(s);
			}
		}
		
		return Set.coment(U.objToHex(als), "success read uid="+asuid) ;
	} catch (Exception e) {
		e.printStackTrace();
		return Set.coment(-2, e.toString());
	} finally {
		try {
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
}
