package Dto;

public class afterStoryDto {

	private int seq;			// 시퀀스
	private String myid; 		// 아이디
	private String title;		// 제목
	private String mycontent; 	// 내용
	private String wdate; 		// 작성일
	private String filename; 	// 파일네임
	private int vcount; 		// 조회수
	private int del;		// 삭제
	
	public afterStoryDto() {
		// TODO Auto-generated constructor stub
	}


	
	public afterStoryDto(int seq, String myid, String title, String mycontent, String wdate, String filename,
			int vcount, int del) {
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



	public afterStoryDto(String myid, String title, String mycontent, String filename) {
		super();
		this.myid = myid;
		this.title = title;
		this.mycontent = mycontent;
		this.filename = filename;
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



	@Override
	public String toString() {
		return "afterStoryDto [seq=" + seq + ", myid=" + myid + ", title=" + title + ", mycontent=" + mycontent
				+ ", wdate=" + wdate + ", filename=" + filename + ", vcount=" + vcount + ", del=" + del + "]";
	}


	
	
	
	
}
	