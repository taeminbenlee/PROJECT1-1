package Dto;

import java.io.Serializable;

public class CommentDto  implements Serializable{
	private String myID;
	private String content;
	private int ref;        //부모  seq와 같이 감 
	private int free;       //글 카테고리를 구분짓기위한 변수 (bbs_0, freeboard_1, after_2)
	
	private int del;        //default(0), 삭제(1) 
	private int seq;
	
	
	
	public CommentDto(String myID, String content, int ref, int free) {
		this.myID = myID;
		this.content = content;
		this.ref = ref;
		this.free = free;
	}


	public CommentDto(String myID, String content, int ref, int free, int del, int seq) {
		this.myID = myID;
		this.content = content;
		this.ref = ref;
		this.del = del;
		this.seq = seq;
		this.free = free;
	}


	public String getMyID() {
		return myID;
	}


	public void setMyID(String myID) {
		this.myID = myID;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getRef() {
		return ref;
	}


	public void setRef(int ref) {
		this.ref = ref;
	}


	public int getFree() {
		return free;
	}


	public void setFree(int free) {
		this.free = free;
	}


	public int getDel() {
		return del;
	}


	public void setDel(int del) {
		this.del = del;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	} 
	
	
	
	
	
}		