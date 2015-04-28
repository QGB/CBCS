package frame;

import java.io.Serializable;


import qgb.U;

public class User implements Serializable {
public static void main(String[] args) throws Exception {
    }
	
	final public int gidu;
	public String gsName;
	public String gspw;
	public int ginum;
	public	String gstel="";
	public String gsdorm="";
	public String gsdepart="";
	public String gsclass="";
	public String gsex="";
	
	public User(int aidu, String asName, String asPw) {
		this.gidu = aidu;
		this.gsName = asName;
		gspw=asPw;
	}

	public User(int aidu) {
		gidu=aidu;
	}

	@Override
	public String toString() {
		return "User [gidu=" + gidu + ", gsName=" + gsName + ", gspw=" + gspw
				+ ", ginum=" + ginum + ", gstel=" + gstel + ", gsdorm="
				+ gsdorm + ", gsdepart=" + gsdepart + ", gsclass=" + gsclass
				+ ", gsex=" + gsex + "]";
	}
	
}
