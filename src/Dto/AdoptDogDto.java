package Dto;

import java.io.Serializable;

public class AdoptDogDto implements Serializable{

	private int seq;
	private String myid;
	private String wdate;
	private String rdate;
	private String mycontent;
	private String phoneNum;
	private String title;
	private int del;	// 게시글 삭제
	private int ref;
	///////////////////////////////////
	private String rdwdate;
	private String rdtitle;
	private String rdmycontent;
	private String rdfilename;
	

	
	public AdoptDogDto() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public AdoptDogDto(int seq, String myid, String wdate, String rdate, String mycontent, String phoneNum, String title,
			String rdwdate, String rdtitle, String rdmycontent, String rdfilename) {
		super();
		this.seq = seq;
		this.myid = myid;
		this.wdate = wdate;
		this.rdate = rdate;
		this.mycontent = mycontent;
		this.phoneNum = phoneNum;
		this.title = title;
		this.rdwdate = rdwdate;
		this.rdtitle = rdtitle;
		this.rdmycontent = rdmycontent;
		this.rdfilename = rdfilename;
	}




	public AdoptDogDto(String myid, String rdate, String mycontent, String phoneNum, String title, int ref) {
		super();
		this.myid = myid;
		this.rdate = rdate;
		this.mycontent = mycontent;
		this.phoneNum = phoneNum;
		this.title = title;
		this.ref = ref;
	}
	


	public AdoptDogDto(int seq, String myid, String wdate, String rdate, String mycontent, String phoneNum,
			String title, int del) {
		super();
		this.seq = seq;
		this.myid = myid;
		this.wdate = wdate;
		this.rdate = rdate;
		this.mycontent = mycontent;
		this.phoneNum = phoneNum;
		this.title = title;
		this.del = del;
		
	}

	




	public AdoptDogDto(int seq, String myid, String wdate, String rdate, String mycontent, String phoneNum,
			String title, int del, int ref) {
		super();
		this.seq = seq;
		this.myid = myid;
		this.wdate = wdate;
		this.rdate = rdate;
		this.mycontent = mycontent;
		this.phoneNum = phoneNum;
		this.title = title;
		this.del = del;
		this.ref = ref;
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





	public String getWdate() {
		return wdate;
	}





	public void setWdate(String wdate) {
		this.wdate = wdate;
	}





	public String getRdate() {
		return rdate;
	}





	public void setRdate(String rdate) {
		this.rdate = rdate;
	}





	public String getMycontent() {
		return mycontent;
	}





	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}





	public String getPhoneNum() {
		return phoneNum;
	}





	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public int getDel() {
		return del;
	}





	public void setDel(int del) {
		this.del = del;
	}


	


	public int getRef() {
		return ref;
	}




	public void setRef(int ref) {
		this.ref = ref;
	}

	


	public String getRdwdate() {
		return rdwdate;
	}




	public void setRdwdate(String rdwdate) {
		this.rdwdate = rdwdate;
	}




	public String getRdtitle() {
		return rdtitle;
	}




	public void setRdtitle(String rdtitle) {
		this.rdtitle = rdtitle;
	}




	public String getRdmycontent() {
		return rdmycontent;
	}




	public void setRdmycontent(String rdmycontent) {
		this.rdmycontent = rdmycontent;
	}




	public String getRdfilename() {
		return rdfilename;
	}




	public void setRdfilename(String rdfilename) {
		this.rdfilename = rdfilename;
	}




	@Override
	public String toString() {
		return "AdoptDogDto [seq=" + seq + ", myid=" + myid + ", wdate=" + wdate + ", rdate=" + rdate + ", mycontent="
				+ mycontent + ", phoneNum=" + phoneNum + ", title=" + title + ", del=" + del + "]";
	}

}
