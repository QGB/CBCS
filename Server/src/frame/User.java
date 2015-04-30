package frame;

import java.io.Serializable;


import qgb.U;

public class User implements Serializable {

public static void main(String[] args) throws Exception {

}
	
	final public int gidu;
	private String sName;
	private String spw;
	private int inum;
	private String stel="";
	private String sdorm="";
	private String sdepart="";
	private String sclass="";
	private String sex="";
	
	@Override
	public String toString() {
		return "User [gidu=" + gidu + ", sName=" + sName + ", spw=" + spw
				+ ", inum=" + inum + ", stel=" + stel + ", sdorm=" + sdorm
				+ ", sdepart=" + sdepart + ", sclass=" + sclass + ", sex="
				+ sex + "]";
	}

	public User(int aidu, String asName, String asPw) {
		this.gidu = aidu;
		sName= asName;
		spw=asPw;
	}

	
	public User() {
		gidu=-1;
	}

	public User(int aidu) {
		gidu=aidu;
	}

	public synchronized String getsName() {
		return sName;
	}

	public synchronized void setsName(String sName) {
		this.sName = sName;
	}

	public synchronized String getSpw() {
		return spw;
	}

	public synchronized void setSpw(String spw) {
		this.spw = spw;
	}



	public synchronized String getInum() {
		return inum+"";
	}

	public synchronized void setInum(String snum) {
		this.inum = 	 Integer.valueOf(snum);
	}

	public synchronized String getStel() {
		return stel;
	}

	public synchronized void setStel(String stel) {
		this.stel = stel;
	}

	public synchronized String getSdorm() {
		return sdorm;
	}

	public synchronized void setSdorm(String sdorm) {
		this.sdorm = sdorm;
	}

	public synchronized String getSdepart() {
		return sdepart;
	}

	public synchronized void setSdepart(String sdepart) {
		this.sdepart = sdepart;
	}

	public synchronized String getSclass() {
		return sclass;
	}

	public synchronized void setSclass(String sclass) {
		this.sclass = sclass;
	}

	public synchronized String getSex() {
		return sex;
	}

	public synchronized void setSex(String sex) {
		this.sex = sex;
	}

	
}
