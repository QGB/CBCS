package frame;

import java.io.Serializable;

public class Server implements Serializable{
	public Server(int idu, String stitle, String sdetail) {
		this.idu = idu;
		this.stitle = stitle;
		this.sdetail = sdetail;
	}
	public Server(int idu) {
		this.idu = idu;
	}
	public final int idu;
	
	private int ids=-1,idm=-1;
	public synchronized void setIds(String as) {
		this.ids =  Integer.valueOf(as);
	}
	private String stitle="";
	private String sdetail="";
	@Override
	public String toString() {
		return "Server [idu=" + idu + ", ids=" + ids + ", idm=" + idm
				+ ", stitle=" + stitle + ", sdetail=" + sdetail + ", stime="
				+ stime + ", sprogress=" + sprogress + ", sendTime=" + sendTime
				+ ", sexplain=" + sexplain + "]";
	}
	private String stime="";
	private String sprogress="";
	private String sendTime="";
	public synchronized int getIdm() {
		return idm;
	}
	public synchronized void setIdm(String as) {
		if (as==null||as.length()<1) {
			return;
		}
		this.idm = Integer.valueOf(as);
	}
	public synchronized String getStime() {
		return stime;
	}
	public synchronized void setStime(String stime) {
		this.stime = stime;
	}
	public synchronized String getSprogress() {
		return sprogress;
	}
	public synchronized void setSprogress(String sprogress) {
		this.sprogress = sprogress;
	}
	public synchronized String getSendTime() {
		return sendTime;
	}
	public synchronized void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public synchronized String getSexplain() {
		return sexplain;
	}
	public synchronized void setSexplain(String sexplain) {
		this.sexplain = sexplain;
	}
	public synchronized int getIds() {
		return ids;
	}
	private String sexplain="";

	public synchronized String getStitle() {
		return stitle;
	}
	public synchronized void setStitle(String stitle) {
		this.stitle = stitle;
	}
	public synchronized String getSdetail() {
		return sdetail;
	}
	public synchronized void setSdetail(String sdetail) {
		this.sdetail = sdetail;
	}
	

	

}
