package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dto.MemberDto;
import Dto.abandonedDogDto;
import DB.DBClose;
import DB.DBConnection;

	
public class abandonedDogDao {
	
	private static abandonedDogDao dao = new abandonedDogDao();
	
	private abandonedDogDao() {
		DBConnection.initConnection();
		
	}
	
	
	
	public static abandonedDogDao getInstance() {
		return dao;
	}
	// 나 자신이 올린 제보글 목록만 확인하고 리스트로 볼수있는 메소드 
	public List<abandonedDogDto> getMyAbandonedDogPagingList(String myid, String choice, String search, int page){
		System.out.println("getAbandonedDogPagingList page:" + page);
		
		String sql = " SELECT RNUM, SEQ, MYID, PHONENUM, ADDRESS, MYCONTENT, FILENAME, NEWFILENAME, DANGER, WDATE, DEL, REF "
				+ " FROM ";
		
		sql += " (SELECT ROW_NUMBER()OVER(ORDER BY REF ASC, SEQ DESC) AS RNUM, "
				+ " SEQ, MYID, PHONENUM, ADDRESS, MYCONTENT, FILENAME, NEWFILENAME, DANGER, WDATE, DEL, REF "
				+ " FROM ABANDONEDDOG "
				+ " WHERE MYID=? AND DEL=0 ";
		
		
		String sWord ="";
		
		if(choice.equals("id")){
			sWord = " AND MYID LIKE '%" + search + "%'";
		} else if (choice.equals("address")){
			sWord = " AND ADDRESS LIKE '%" + search + "%' ";
		}
		
		sql = sql + sWord;
		sql = sql + " ORDER BY RNUM ASC) ";
		
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
		sql = sql + " ORDER BY REF ASC, SEQ DESC ";
		
		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<abandonedDogDto> list = new ArrayList<abandonedDogDto>();
		
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/4 getMYAbandonedDogPagingList success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, myid);
			psmt.setInt(2, start);
			psmt.setInt(3, end);
			System.out.println("2/4 getMYAbandonedDogPagingList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getMYAbandonedDogPagingList success");
			
			while (rs.next()) {
				abandonedDogDto dto = new abandonedDogDto(
											rs.getInt(1),
											rs.getInt(2),
											rs.getString(3),
											rs.getString(4),
											rs.getString(5),
											rs.getString(6),
											rs.getString(7),
											rs.getString(8),
											rs.getInt(9),
											rs.getString(10),	
											rs.getInt(11),
											rs.getInt(12));
				list.add(dto);
			}
			
			
			
			System.out.println("4/4 getMYAbandonedDogPagingList success");

		} catch (SQLException e) {
			System.out.println("getMYAbandonedDogPagingList fail");

			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
	}
	
	
	//관리자확인용 페이지 에서 제보글 목록을 볼수있도록 하는 메소드 + search + paging 
	public List<abandonedDogDto> getAbandonedDogPagingList(String choice, String search, int page){
		System.out.println("getAbandonedDogPagingList page:" + page);
		
		String sql = " SELECT RNUM, SEQ, MYID, PHONENUM, ADDRESS, MYCONTENT, FILENAME, NEWFILENAME, DANGER, WDATE, DEL, REF "
				+ " FROM ";
		
		sql += " (SELECT ROW_NUMBER()OVER(ORDER BY REF ASC, SEQ DESC) AS RNUM, "
				+ " SEQ, MYID, PHONENUM, ADDRESS, MYCONTENT, FILENAME, NEWFILENAME, DANGER, WDATE, DEL, REF "
				+ " FROM ABANDONEDDOG "
				+ " WHERE DEL=0 ";
		
		
		String sWord ="";
		
		if(choice.equals("id")){
			sWord = " AND MYID LIKE '%" + search + "%'";
		} else if (choice.equals("address")){
			sWord = " AND ADDRESS LIKE '%" + search + "%' ";
		}
		
		sql = sql + sWord;
		sql = sql + " ORDER BY RNUM ASC) ";
		
		
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
		sql = sql + " ORDER BY REF ASC, SEQ DESC ";			
		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<abandonedDogDto> list = new ArrayList<abandonedDogDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/4 getAbandonedDogPagingList success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getAbandonedDogPagingList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getAbandonedDogPagingList success");
			
			while (rs.next()) {
				abandonedDogDto dto = new abandonedDogDto(rs.getInt(1),
														rs.getInt(2),
														rs.getString(3),
														rs.getString(4),
														rs.getString(5),
														rs.getString(6),
														rs.getString(7),
														rs.getString(8),
														rs.getInt(9),
														rs.getString(10),	
														rs.getInt(11),
														rs.getInt(12));
				list.add(dto);
			}
			
			/*
			 * for (abandonedDogDto ab : list) { System.out.println(ab.toString()); }
			 */
			
			System.out.println("4/4 getAbandonedDogPagingList success");

		} catch (SQLException e) {
			System.out.println("getAbandonedDogPagingList fail");

			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
	}
	//관리자용 모든 제보의 카운트 넘겨받기
	public int getAllReport(String choice, String search) {
		String sql = " SELECT COUNT(*) FROM ABANDONEDDOG WHERE DEL=0 ";
		
		String sWord = "";
		if(sWord!="") {
			if(choice.equals("ADDRESS")) {
				sWord = " AND ADDRESS LIKE '%" + search + "%'";
			} else if (choice.equals("id")) {
				sWord = " AND MYID='" + search + "'";
			}
		}
		sql = sql + sWord;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;
	
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getAllReport success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getAllReport success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getAllReport success");
			if(rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("4/4 getAllReport success");
		} catch (SQLException e) {
			System.out.println("getAllReport fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return len;
		
	}
	//유저용 아이별로 모든 글수 받아오기
	public int getMyReport(String myid, String choice, String search) {
		String sql = " SELECT COUNT(*) FROM ABANDONEDDOG WHERE MYID = ? AND DEL=0 ";
		
		String sWord = "";
		if(sWord!="") {
			if(choice.equals("ADDRESS")) {
				sWord = " AND ADDRESS LIKE '%" + search + "%'";
			} else if (choice.equals("id")) {
				sWord = " AND MYID='" + search + "'";
			}
		}
		sql = sql + sWord;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;
	
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getAllReport success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, myid);
			System.out.println("2/4 getAllReport success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getAllReport success");
			if(rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("4/4 getAllReport success");
		} catch (SQLException e) {
			System.out.println("getAllReport fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return len;
		
	}
	
	
	
	//제보자의 제보 디테일을 확인할수 있는 메소드 == 디테일
	public abandonedDogDto reportDetail(int seq) {
		String sql = " SELECT SEQ, MYID, PHONENUM, ADDRESS, MYCONTENT, FILENAME, NEWFILENAME, DANGER, WDATE, DEL, REF "
				+ " FROM ABANDONEDDOG "
				+ " WHERE SEQ=? ";
		

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		abandonedDogDto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 reportDetail success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 reportDetail success");
			psmt.setInt(1, seq);
			rs=psmt.executeQuery();
			System.out.println("3/4 reportDetail success");

			while (rs.next()) {
				dto = new abandonedDogDto(	rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getString(4),
											rs.getString(5),
											rs.getString(6),
											rs.getString(7),
											rs.getInt(8),
											rs.getString(9),
											rs.getInt(10),
											rs.getInt(11));
			}
			System.out.println("4/4 reportDetail success");

		} catch (SQLException e) {
			System.out.println("reportDetail fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return dto;
	}
	
	// 제보자가 제보를 위해 글을 작성하는 메소드
	public boolean writeReport(abandonedDogDto dto) {
		String sql = " INSERT INTO ABANDONEDDOG "
				+ " (SEQ, MYID, PHONENUM, ADDRESS, MYCONTENT, FILENAME, NEWFILENAME, DANGER, WDATE, DEL, REF) "
				+ " VALUES( SEQ_ABANDONEDDOG.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, SYSDATE, 0, 0 ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 writeReport success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 writeReport success");
			psmt.setString(1, dto.getMyid());
			psmt.setString(2, dto.getPhonenum());
			psmt.setString(3, dto.getAddress());
			psmt.setString(4, dto.getMycontent());
			psmt.setString(5, dto.getFilename());
			psmt.setString(6, dto.getNewfilename());
			psmt.setInt(7, dto.getDanger());
			System.out.println("3/4 writeReport success");
			
			count = psmt.executeUpdate();
			System.out.println("4/4 writeReport success");


		} catch (SQLException e) {
			System.out.println("writeReport fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}

		return count>0?true:false;
	}
	
	//제보 수정 메소드
	public boolean updateReport(int seq, abandonedDogDto dto) {
		String sql = " UPDATE ABANDONEDDOG "
					+ " SET MYID=?, PHONENUM=?, ADDRESS=?, MYCONTENT=?, FILENAME=?, NEWFILENAME=?, DANGER=?, WDATE=SYSDATE "
					+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
	
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 updateReport success");
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getMyid());
			psmt.setString(2, dto.getPhonenum());
			psmt.setString(3, dto.getAddress());
			psmt.setString(4, dto.getMycontent());
			psmt.setString(5, dto.getFilename());
			psmt.setString(6, dto.getNewfilename());
			psmt.setInt(7, dto.getDanger());
			psmt.setInt(8, seq);
			System.out.println("2/3 updateReport success");
			
	
			
			count = psmt.executeUpdate();
			System.out.println("3/3 updateReport success");
		} catch (SQLException e) {
			System.out.println("updateReport fail");

			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	//수정을 위해 디테일내용 가져오는 메소드
		public abandonedDogDto getReport(int seq) {
			String sql = " SELECT SEQ, MYID, PHONENUM, ADDRESS, MYCONTENT, FILENAME, NEWFILENAME, DANGER, WDATE, DEL, REF "
					+ " FROM ABANDONEDDOG "
					+ " WHERE SEQ = ? ";		
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			abandonedDogDto dto = null;
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/4 getReport success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("2/4 getReport success");
				psmt.setInt(1, seq);
				rs = psmt.executeQuery();
				System.out.println("3/4 getReport success");
				
				while (rs.next()) {
					dto = new abandonedDogDto(	rs.getInt(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5),
												rs.getString(6),
												rs.getString(7),
												rs.getInt(8),
												rs.getString(9),	
												rs.getInt(10),
												rs.getInt(11));
					
					}
				System.out.println("4/4 getReport success");

			} catch (SQLException e) {
				System.out.println("getReport fail");

				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			
			return dto;
		}
		
		public boolean deleteReport (int seq) {
			String sql = " UPDATE ABANDONEDDOG "
					+ " SET DEL=1 "
					+ " WHERE SEQ=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 deleteReport");
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, seq);
				System.out.println("2/3 deleteReport");

				count = psmt.executeUpdate();
				System.out.println("3/3 deleteReport");

			} catch (SQLException e) {
				System.out.println("deleteReport fail");

				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, null);
			}
			
			return count>0?true:false;
		}
	
		public boolean authorizeUpdate(int seq) {
		
			
			String sql = " UPDATE ABANDONEDDOG "
					+ " SET ADDRESS=CONCAT('[구조완료]', ADDRESS ), WDATE=SYSDATE, REF=REF+1"
					+ " WHERE SEQ=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 authorizeUpdate");
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				System.out.println("2/3 authorizeUpdate");
				count = psmt.executeUpdate();
				System.out.println("3/3 authorizeUpdate");

				
			} catch (SQLException e) {
				System.out.println("authorizeUpdate fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, null);
			}
			
			return count>0?true:false;
		}
	
	
	
	
	
	
	
	
	
}
