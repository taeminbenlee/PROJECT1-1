package Dto;

import java.io.Serializable;

public class RescuedDogDto implements Serializable {
	private int seq;              //시퀀스
	private String wdate;         //작성일
	private int vcount;           //조회수
	private int condition;	      //입양진행유무 (0:default, 1:입양진행중, 2:입양완료)
	
	private String title;         //제목
	private String myContent;     //내용
	private String filename;      //이미지파일
	private int del;              //삭제유무 (0:default, 1:삭제)
	
	
	
	
	public RescuedDogDto(String title, String myContent, String filename) {
		this.title = title;
		this.myContent = myContent;
		this.filename = filename;
	}




	public RescuedDogDto(int seq, String wdate, int vcount, int condition, String title, String myContent,
			String filename, int del) {
		this.seq = seq;
		this.wdate = wdate;
		this.vcount = vcount;
		this.condition = condition;
		this.title = title;
		this.myContent = myContent;
		this.filename = filename;
		this.del = del;
	}




	public int getSeq() {
		return seq;
	}




	public void setSeq(int seq) {
		this.seq = seq;
	}






	public String getWdate() {
		return wdate;
	}




	public void setWdate(String wdate) {
		this.wdate = wdate;
	}




	public int getVcount() {
		return vcount;
	}




	public void setVcount(int vcount) {
		this.vcount = vcount;
	}




	public int getCondition() {
		return condition;
	}




	public void setCondition(int condition) {
		this.condition = condition;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getMyContent() {
		return myContent;
	}




	public void setMyContent(String myConent) {
		this.myContent = myConent;
	}




	public String getFilename() {
		return filename;
	}




	public void setFilename(String filename) {
		this.filename = filename;
	}





	public int getDel() {
		return del;
	}




	public void setDel(int del) {
		this.del = del;
	}

	
	
	
	
}




