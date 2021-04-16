package Dto;

import java.io.Serializable;

public class abandonedDogDto implements Serializable{
	
	private int rnum;
	private int seq;			//글번호
	private String myid;		//작성자id
	private String phonenum;	//작성자 연락처
	private String address; 	//유기견의 발견 장소
	private String mycontent;	//내용
	private String filename;	//유기견 사진 
	private String newfilename; //이미지
	private int danger;			//위험등급(유기견의 상태)
	private String wdate;		//제보일자
	private int del;			//삭제(0:def, 1:삭제)
	private int ref;			
	
	public abandonedDogDto() {}

	

	public abandonedDogDto(int rnum, int seq, String myid, String phonenum, String address, String mycontent, String filename,
			String newfilename, int danger, String wdate, int del, int ref) {
		super();
		this.rnum = rnum;
		this.seq = seq;
		this.myid = myid;
		this.phonenum = phonenum;
		this.address = address;
		this.mycontent = mycontent;
		this.filename = filename;
		this.newfilename = newfilename;
		this.danger = danger;
		this.wdate = wdate;
		this.del = del;
		this.ref = ref;
		
	}
	


	public abandonedDogDto(int seq, String myid, String phonenum, String address, String mycontent, String filename,
			String newfilename, int danger, String wdate, int del, int ref) {
		super();
		this.seq = seq;
		this.myid = myid;
		this.phonenum = phonenum;
		this.address = address;
		this.mycontent = mycontent;
		this.filename = filename;
		this.newfilename = newfilename;
		this.danger = danger;
		this.wdate = wdate;
		this.del = del;
	}



	public abandonedDogDto(String myid, String phonenum, String address, String mycontent, String filename,
			String newfilename, int danger) {
		super();
		this.myid = myid;
		this.phonenum = phonenum;
		this.address = address;
		this.mycontent = mycontent;
		this.filename = filename;
		this.newfilename = newfilename;
		this.danger = danger;
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

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMycontent() {
		return mycontent;
	}

	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewfilename() {
		return newfilename;
	}

	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}


	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	
	
	
	public int getDel() {
		return del;
	}



	public void setDel(int del) {
		this.del = del;
	}



	public int getDanger() {
		return danger;
	}



	public void setDanger(int danger) {
		this.danger = danger;
	}



	public int getRnum() {
		return rnum;
	}



	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	

	public int getRef() {
		return ref;
	}



	public void setRef(int ref) {
		this.ref = ref;
	}



	@Override
	public String toString() {
		return "abandonedDogDto [seq=" + seq + ", myid=" + myid + ", phonenum=" + phonenum + ", address=" + address
				+ ", mycontent=" + mycontent + ", filename=" + filename + ", newfilename=" + newfilename + ", danger="
				+ danger + ", wdate=" + wdate + ", del=" + del + "]";
	}

	
	
	
	
}
