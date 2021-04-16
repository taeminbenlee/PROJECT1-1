package Dto;

import java.io.Serializable;

public class BbsDto implements Serializable{
	private int rnum;
	private int seq; 
	private String myid; 
	private String title; 
	private String mycontent; 
	private String wdate; 
	private String filename; 
	private int vcount;
	private int del;
	private int free;
	
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getMyid() {
		return myid;
	}
	public void setMyid(String myid) {
		this.myid = myid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMycontent() {
		return mycontent;
	}
	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getVcount() {
		return vcount;
	}
	public void setVcount(int vcount) {
		this.vcount = vcount;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public BbsDto(int rnum, int seq, String myid, String title, String mycontent, String wdate, String filename,
			int vcount, int del) {
		super();
		this.rnum = rnum;
		this.seq = seq;
		this.myid = myid;
		this.title = title;
		this.mycontent = mycontent;
		this.wdate = wdate;
		this.filename = filename;
		this.vcount = vcount;
		this.del = del;
	}
	@Override
	public String toString() {
		return "BbsDto [rnum=" + rnum + ", seq=" + seq + ", myid=" + myid + ", title=" + title + ", mycontent="
				+ mycontent + ", wdate=" + wdate + ", filename=" + filename + ", vcount=" + vcount + ", del=" + del
				+ "]";
	}
	
	public BbsDto(int seq, String myid, String title, String mycontent, String wdate, String filename, int vcount,
			int del) {
		super();
		this.seq = seq;
		this.myid = myid;
		this.title = title;
		this.mycontent = mycontent;
		this.wdate = wdate;
		this.filename = filename;
		this.vcount = vcount;
		this.del = del;
	}
	
	public BbsDto(int seq, String myid, String title, String mycontent, String wdate, String filename, int vcount,
			int del, int free) {
		this.seq = seq;
		this.myid = myid;
		this.title = title;
		this.mycontent = mycontent;
		this.wdate = wdate;
		this.filename = filename;
		this.vcount = vcount;
		this.del = del;
		this.free = free;
	}
	
	public BbsDto(String myid, String title, String mycontent, String filename) {
		this.myid = myid;
		this.title = title;
		this.mycontent = mycontent;
		this.filename = filename;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}

	
	
	
	
	
	
	
	
	
}
