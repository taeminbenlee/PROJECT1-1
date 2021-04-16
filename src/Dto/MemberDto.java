package Dto;

import java.io.Serializable;

public class MemberDto implements Serializable{
	
	private int seq; 
	String myid; 
	String pwd; 
	String email;
	String myname;
	String phonenum; 
	int mymanager;
	
		
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMyname() {
		return myname;
	}
	public void setMyname(String myname) {
		this.myname = myname;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public int getMymanager() {
		return mymanager;
	}
	public void setMymanager(int mymanager) {
		this.mymanager = mymanager;
	}
	public MemberDto(int seq, String myid, String pwd, String email, String myname, String phonenum, int mymanager) {
		super();
		this.seq = seq;
		this.myid = myid;
		this.pwd = pwd;
		this.email = email;
		this.myname = myname;
		this.phonenum = phonenum;
		this.mymanager = mymanager;
	}
	@Override
	public String toString() {
		return "MemberDto [seq=" + seq + ", myid=" + myid + ", pwd=" + pwd + ", email=" + email + ", myname=" + myname
				+ ", phonenum=" + phonenum + ", mymanager=" + mymanager + "]";
	}
	public MemberDto(String myid, String email, String myname, int mymanager) {
		super();
		this.myid = myid;
		this.email = email;
		this.myname = myname;
		this.mymanager = mymanager;
	}

	
	
	
}
