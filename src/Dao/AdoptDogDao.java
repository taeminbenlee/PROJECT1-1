package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import DB.DBClose;
import DB.DBConnection;
import Dto.AdoptDogDto;


public class AdoptDogDao {
	
	private static AdoptDogDao dao = new AdoptDogDao();
	
	private AdoptDogDao() {
		DBConnection.initConnection();
	}
	
	public static AdoptDogDao getInstance() {
		return dao;
	}
	
	
	public List<AdoptDogDto> adoptPagingList(String choice, String search, int page){
		System.out.println("관리자확인용 입양신청목록 페이지 adoptPagingList: " + page);
		String sql = " SELECT SEQ, MYID, WDATE, RDATE, MYCONTENT, PHONENUM, TITLE, DEL "
					+ " FROM ";
		
		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY SEQ DESC) AS RNUM, " + 
				"	SEQ, MYID, WDATE, RDATE, MYCONTENT, PHONENUM, TITLE, DEL " + 
				"	FROM ADOPTDOG " +
				"	WHERE DEL = 0 ";
		
		
		String sWord = "";
	//	if(sWord!="") {
			if(choice.equals("title")) {
				sWord = "AND TITLE LIKE '%" + search + "%' ";
			}else if(choice.equals("mycontent")) {
				sWord = " AND MYCONTENT LIKE '%" + search + "%' ";
			}else if(choice.equals("myid")) {
				sWord = " AND MYID='" + search + "' ";
			}
	//	}
		
		sql = sql + sWord;
		
		sql = sql + " ORDER BY SEQ DESC) ";
		
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ?";

		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;	
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<AdoptDogDto> list = new ArrayList<AdoptDogDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 adoptPagingList success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 adoptPagingList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 adoptPagingList success");
			
			while(rs.next()) {
				AdoptDogDto dto = new AdoptDogDto(rs.getInt(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5),
												rs.getString(6),
												rs.getString(7),
												rs.getInt(8));
			list.add(dto);
						
			}
			System.out.println("4/4 adoptPagingList success");
			
		} catch (SQLException e) {
			System.out.println("adoptPagingList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
				
	}
	
	// 자신이 작성한 신청서 목록확인
	public List<AdoptDogDto> myAdoptPagingList(String myid, String choice, String search, int page) {
		System.out.println("자신이 올린 입양신청서 페이지 : " + page);
		
		String sql = " SELECT SEQ, MYID, WDATE, RDATE, MYCONTENT, PHONENUM, TITLE, DEL "
				+ " FROM ";
		
		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY SEQ DESC) AS RNUM, " + 
				"	SEQ, MYID, WDATE, RDATE, MYCONTENT, PHONENUM, TITLE, DEL " + 
				"	FROM ADOPTDOG " +
				" 	WHERE MYID=? AND DEL=0 ";
		
		String sWord = "";
	//	if(sWord!="") {
			if(choice.equals("title")) {
				sWord = "AND TITLE LIKE '%" + search + "%' ";
			}else if(choice.equals("mycontent")) {
				sWord = " AND MYCONTENT LIKE '%" + search + "%' ";
			}else if(choice.equals("myid")) {
				sWord = " AND MYID='" + search + "' ";
			}
//		}
		
		sql = sql + sWord;
		sql = sql + " ORDER BY SEQ DESC) ";
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ?";
		
		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;	
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<AdoptDogDto> list = new ArrayList<AdoptDogDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 myAdoptPagingList success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, myid);
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			System.out.println("2/4 myAdoptPagingList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 myAdoptPagingList success");
			
			while(rs.next()) {
				AdoptDogDto dto = new AdoptDogDto(rs.getInt(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5),
												rs.getString(6),
												rs.getString(7),
												rs.getInt(8));
			list.add(dto);
						
			}
			System.out.println("4/4 myAdoptPagingList success");
			
		} catch (SQLException e) {
			System.out.println("myAdoptPagingList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
		
		
	}
	
	
	public AdoptDogDto adoptAdmin(int seq) {
		String sql = " SELECT SEQ, MYID, WDATE, RDATE, MYCONTENT, PHONENUM, TITLE, DEL "
					+ " FROM ADOPTDOG "
					+ " WHERE SEQ=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		AdoptDogDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 adoptAdmin success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/4 adoptAdmin success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 adoptAdmin success");
			
			while(rs.next()) {
				dto = new AdoptDogDto(rs.getInt(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5),
												rs.getString(6),
												rs.getString(7),
												rs.getInt(8));
			}
			System.out.println("4/4 adoptAdmin success");
			
		}catch (SQLException e) {
			System.out.println("adoptAdmin fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return dto;
		
	}
	
	////////////////    이거수정      //////////////////
	//관리자
	public int getAllAdoptDog(String choice, String search) {
		String sql = " SELECT COUNT(*) FROM ADOPTDOG WHERE DEL=0 ";
		
		String sWord = "";
		if(sWord!="") {
			if(choice.equals("title")) {
				sWord = "AND TITLE LIKE '%" + search + "%' ";
			}else if(choice.equals("mycontent")) {
				sWord = " AND MYCONTENT LIKE '%" + search + "%' ";
			}else if(choice.equals("myid")) {
				sWord = " AND MYID='" + search + "' ";
			}
		}
		sql = sql + sWord;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;
		
		try {
		
			conn = DBConnection.getConnection();
			System.out.println("1/4 getAllAdoptDog success");
		
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getAllAdoptDog success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getAllAdoptDog success");
			
			if(rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("4/4 getAllAdoptDog success");
			
			
		} catch (SQLException e) {
			System.out.println("getAllAdoptDog fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		return len;
	}
	
	//user's 
	public int getMyAdoptDog(String myid, String choice, String search) {
		String sql = " SELECT COUNT(*) FROM ADOPTDOG WHERE DEL=0 AND MYID=? ";
		
		String sWord = "";
		if(sWord!="") {
			if(choice.equals("title")) {
				sWord = "AND TITLE LIKE '%" + search + "%' ";
			}else if(choice.equals("mycontent")) {
				sWord = " AND MYCONTENT LIKE '%" + search + "%' ";
			}else if(choice.equals("myid")) {
				sWord = " AND MYID='" + search + "' ";
			}
		}
		sql = sql + sWord;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;
		
		try {
		
			conn = DBConnection.getConnection();
			System.out.println("1/4 getMyAdoptDog success");
		
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, myid);
			System.out.println("2/4 getMyAdoptDog success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getMyAdoptDog success");
			
			if(rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("4/4 getAllAdoptDog success");
			
			
		} catch (SQLException e) {
			System.out.println("getMyAdoptDog fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		return len;
	}
	
	
	
public boolean writeAdopt(AdoptDogDto dto) {
		
		String sql = " INSERT INTO ADOPTDOG "
					+ " (SEQ, MYID, WDATE, RDATE, MYCONTENT, PHONENUM, TITLE, DEL, REF) "
					+ " VALUES( SEQ_ADOPTDOG.NEXTVAL, ?, SYSDATE, ?, ?, ?, ?, 0, ?)";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 writeAdopt success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMyid());
			psmt.setString(2, dto.getRdate());
			psmt.setString(3, dto.getMycontent());
			psmt.setString(4, dto.getPhoneNum());
			psmt.setString(5, dto.getTitle());
			psmt.setInt(6, dto.getRef());
			System.out.println("2/3 writeAdopt success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 writeAdopt success");
			
		} catch (SQLException e) {
			System.out.println("writeAdopt fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
			
		}
		return count>0?true:false;
	}
	
	public AdoptDogDto getAdopt(int seq) {
		
		System.out.println("seq:" + seq);
		String sql = " SELECT SEQ, MYID, WDATE, RDATE, MYCONTENT, PHONENUM, TITLE, DEL "
					+ " FROM ADOPTDOG "
					+ " WHERE SEQ=? AND DEL =0  ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		AdoptDogDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getAdopt success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getAdopt success");
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getAdopt success");
			
			if(rs.next()) {
				dto = new AdoptDogDto(			rs.getInt(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5),
												rs.getString(6),
												rs.getString(7),
												rs.getInt(8));
				
			}
			System.out.println("4/4 getAdopt success");
			
		}catch (SQLException e) {
			System.out.println("getAdopt fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return dto;
	}
	
	public AdoptDogDto adoptDetail(int seq) {
		String sql = " SELECT A.SEQ, A.MYID, A.WDATE, A.RDATE, A.MYCONTENT, A.PHONENUM, A.TITLE, "
				+ " R.WDATE AS RDWDATE, R.TITLE AS RDTITLE, R.MYCONTENT AS RDMYCONTENT, R.FILENAME AS RDFILENAME "
					+ " FROM ADOPTDOG A, RESCUEDDOG R "
					+ " WHERE A.SEQ=? AND A.REF = R.SEQ ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		AdoptDogDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 adoptDetail success");
		
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 adoptDetail success");
			System.out.println("seq: "+seq);
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();
			System.out.println("3/4 adoptDetail success");
			
			while(rs.next()) {
				dto = new AdoptDogDto(
										rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getString(7),
										rs.getString(8),
										rs.getString(9),
										rs.getString(10),
										rs.getString(11));
							}
			System.out.println("4/4 adoptDetail success");
			System.out.println("dto: "+ dto);
		} catch (SQLException e) {
			System.out.println("adoptDetail fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
			
		}
		return dto;
	}
	
	public boolean updateAdopt(int seq, String title, String mycontent, String rdate, String phoneNum) {
		String sql = " UPDATE ADOPTDOG SET TITLE=?, MYCONTENT=?, RDATE=?, PHONENUM=? "
				+ "WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 updateAdopt success");
		
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, mycontent);
			psmt.setString(3, rdate);
			psmt.setString(4, phoneNum);
			psmt.setInt(5, seq);
			System.out.println("2/3 updateAdopt success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 updateAdopt success");
			
			
			
		} catch (SQLException e) {
			System.out.println("updateAdopt fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
			
		}
		
		return count>0?true:false;
	}
	
	public boolean deleteAdopt(int seq) {
		
	//	String sql = " DELETE FROM ADOPTDOG WHERE SEQ=? ";
	
		String sql = " UPDATE ADOPTDOG SET DEL=1 WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 deleteAdopt success");
		
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 deleteAdopt success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 deleteAdopt success");
			
		} catch (SQLException e) {
			System.out.println("deleteAdopt fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
			
		}
		
		return count>0?true:false;
	}
	
	public void name() {
		
	}

}



