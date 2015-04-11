import httpserver.Set;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import qgb.T;
import qgb.text.QText;
import qgb.thread.TaskQueue;

public class DB {
	public static void main(String[] args) throws SQLException {
		Start.main();
	}

	private static void testSqlite() {
		PreparedStatement prep = null;
		String sql = QText.format("INSERT INTO %s(%s,%s) VALUES(?,?)",
				gsTableEntry, gsCEn, gsCTrans);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, "");
			prep.setString(2, "");
			prep.execute();
		} catch (SQLException e) {
			T.setOutStream("sql.txt");
			// T.print("%s\n[%s][%s]",sql,ae.word.stW.get(),ae.trans.toString());
			e.printStackTrace();
		}
	}

	private static void printField() {
		Class<DB> cDB = DB.class;
		Field[] yf = cDB.getDeclaredFields();

		for (int i = 0; i < yf.length; i++) {
			String str = yf[i].toString();
			int ia = str.indexOf("mh.database");
			str = QText.format("* import static %s;</br>",
					str.substring(ia, str.length()));
			T.print(str);
		}
	}

	/**************** Test End ************************/
	public static Connection gConn;
	public static Statement gStat;
	/** TODO:DB Test */
	final private static String gsDbName = T.autoPath("server.db"); // "./mh.db";
	/** ------User---------------- **/
	final static String gsTableUsr = "User";
	final static String giCUid = "id";
	final static String gsCName = "stName";
	final static String gsCMail = "stMail";

	/** gsCWord, gsCTrans **/
	final static String gsTableEntry = "Entry";
	final static String gsCTrans = "stTrans";
	/** gsCWord, gsCEn, gsCn, gsCUrl,gsCVoiceByte(blob) **/
	final static String gsTableSenten = "Sentences";
	final static String gsCEn = "stEn";
	final static String gsCn = "stCn";
	final static String gsCUrl = "stUrl";
	final static String gsCVoiceByte = "Bytes";
	/** gsCWord, gsCPhon, gsCountry, gsCUrl,gsCVoiceByte(blob) **/
	final static String gsTablePhon = "Phons";
	final static String gsCPhon = "stPhon";
	final static String gsCountry = "stCountry";
	private final static TaskQueue gDBThread = new TaskQueue();
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

	// Statement
	public static boolean UserMailExists(String ast) {
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", gsTableUsr, gsCMail));
			prep.setString(1, ast);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				//T.msgbox( rs.getString(gsCMail));
				return rs.getString(gsCMail).equals(ast);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String reg(String asName, String asMail) {
		if (asName == null || asMail == null)
			return Set.coment(-1, "GET Params NUll!");

		if (asName.length() < 3 || asMail.length() < 3) {
			return Set.coment(-2, "length() < 3");
		}

		if (!asMail.contains("@")||!!asMail.contains(".")) {
			return Set.coment(-3, "Mail Format Error!");
		}
		
		if (UserMailExists(asMail)) {
			return Set.coment(-4, "The Mail already exists");
		}

		PreparedStatement prep = null;
		String sql = QText.format("INSERT INTO %s(%s,%s) VALUES(?,?)",
				gsTableUsr, gsCName, gsCMail);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, asName);
			prep.setString(2, asMail);
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
			prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", gsTableUsr, gsCMail));
			prep.setString(1, asMail);
			ResultSet rs = prep.executeQuery();
			// QJDBT.print(rs);
			int id = -1;
			while (rs.next()) {
				if (rs.getString(gsCMail).equals(asMail)) {
					id = Integer.valueOf(rs.getString(giCUid));
					return Set.coment(id, "Successfully regist!");
				}
			}
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

	private static void error(SQLException e, PreparedStatement prep) {
		try {
			T.print(prep.getParameterMetaData().toString());
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private static void error(SQLException e, String... yst) {
		T.print(yst);
		e.printStackTrace();
	}

	public static boolean DbExists() {
		return new File(T.autoPath(gsDbName)).exists();
	}
//
	public static boolean tableExists() {
		try {
			createTable();
			return false;
		} catch (SQLException e) {
			//T.msgbox();
			if (e.getMessage().contains("table User already exists")) {
				return true;
			}
			e.printStackTrace();
		}
		return false;
	}
	/** 在没有数据库时才建表 **/
	private static void createTable()throws SQLException {
		if (DbExists())
			//return;
		try {
			// 在没有数据库时才做建表和插数据
			String sql = QText.format("create table %s("
					+ "%s integer,%s char(50) NOT NULL,"
					+ "%s char(50) NOT NULL," + "PRIMARY KEY (%s));",
					gsTableUsr, giCUid, gsCName, gsCMail, giCUid);
			gStat.executeUpdate(sql);

		}catch (SQLException e) {
			throw e;
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
