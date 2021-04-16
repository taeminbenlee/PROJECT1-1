package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBClose;
import DB.DBConnection;
import Dto.RescuedDogDto;

public class RescuedDogDao {

	// Singleton 셋팅
	private static RescuedDogDao dao = new RescuedDogDao();

	private RescuedDogDao() {
		DBConnection.initConnection();
	}

	public static RescuedDogDao getInstance() {
		return dao;
	}

	///////////////// DB와 연결하여 작업할 Method ///////////////////

// TODO 작성 
	public boolean writeRescued(RescuedDogDto dto) {
		String sql = " INSERT INTO RESCUEDDOG (SEQ, WDATE, VCOUNT, CONDITION, TITLE, MYCONTENT, FILENAME, DEL) "
				+ " VALUES(SEQ_RESCUEDDOG.NEXTVAL, SYSDATE, 0, 0, ?, ?, ?, 0) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 writeRescued success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 writeRescued success");

			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getMyContent());
			psmt.setString(3, dto.getFilename());

			count = psmt.executeUpdate();
			System.out.println("3/3 writeRescued success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("writeRescued fail");

		} finally {
			DBClose.close(conn, psmt, null);
		}
		System.out.println("-----------------------");
		return count > 0 ? true : false;
	}

// TODO 테이블 출력용 List
	public List<RescuedDogDto> RescuedList(String choice, String search, int page) {
		String sql = " SELECT SEQ, WDATE, VCOUNT, CONDITION, TITLE, MYCONTENT, FILENAME, DEL " 
				   + " FROM ";
												 // 입양 진행중이거나 완료된 것은 뒤에 글로 내린다. 글작성 순으로 올린다
		sql += "         (SELECT ROW_NUMBER()OVER(ORDER BY CONDITION ASC, SEQ DESC) AS RNUM, "
				+ "              SEQ, WDATE, VCOUNT, CONDITION, TITLE, MYCONTENT, FILENAME, DEL "
				+ "         FROM RESCUEDDOG " 
				+ "         WHERE DEL = 0 ";

		String sWord = "";
		if (choice.equals("title")) {
			sWord = " AND TITLE LIKE '%" + search + "%' ";
		} else if (choice.equals("content")) {
			sWord = " AND CONTENT LIKE '%" + search + "%' ";
		}
		sql = sql + sWord;
		sql = sql + " ORDER BY CONDITION ASC, SEQ DESC) ";
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";

		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;

		System.out.println("start" + start);
		System.out.println("end" + end);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<RescuedDogDto> list = new ArrayList<RescuedDogDto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 RescuedList success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 RescuedList success");

			rs = psmt.executeQuery();
			System.out.println("3/4 RescuedList success");

			while (rs.next()) {
				RescuedDogDto dto = new RescuedDogDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				list.add(dto);
			}
			System.out.println("4/4 RescuedList success");

		} catch (SQLException e) {
			System.out.println("getBbsSearchList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		System.out.println("-----------------------");
		return list;
	}

// TODO 작성된 글의 총 수 구하기
	public int getAllList(String choice, String search) {
		String sql = " SELECT COUNT (*) " 
	               + " FROM RESCUEDDOG " 
				   + " WHERE DEL = 0 ";

		String sWord = "";
		
		 if(choice.equals("title")) { 
			 sWord = " AND TITLE LIKE '%" + search + "%' ";
		 }else if(choice.equals("content")) { 
			 sWord = " AND CONTENT LIKE '%" + search+ "%' "; } 
		 sql = sql + sWord;
		 
		 
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int len = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getAllList success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 getAllList success");

			rs = psmt.executeQuery();
			if (rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("3/3 getAllList success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getAllList fail");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		System.out.println("-----------------------");
		return len;
	}

// TODO 디테일 출력
	public RescuedDogDto rescuedDetail(int seq) {
		String sql = " SELECT SEQ, WDATE, VCOUNT, CONDITION, TITLE, MYCONTENT, FILENAME, DEL "
				   + " FROM RESCUEDDOG " + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		RescuedDogDto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 rescuedDetail success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 rescuedDetail success");

			rs = psmt.executeQuery();

			if (rs.next()) {
				dto = new RescuedDogDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
			}
			System.out.println("3/3 rescuedDetail success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("rescuedDetail Fail");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		System.out.println("-----------------------");
		return dto;
	}

// TODO 조회수 올리기
	public void vcountUp(int seq) {
		String sql = " UPDATE RESCUEDDOG " + " SET VCOUNT=VCOUNT+1 " + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		System.out.println("1/3 vcountUp success");

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 vcountUp success");

			psmt.setInt(1, seq);
			psmt.executeUpdate();
			System.out.println("3/3 vcountUp success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("vcountUp fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}

	}

// TODO 수정
	public boolean updateRescued(RescuedDogDto dto, int seq) {
		String sql = " UPDATE RESCUEDDOG " 
				   + " SET WDATE=SYSDATE, TITLE=?, MYCONTENT=?, FILENAME=? "
			       + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		System.out.println("1/3 updateRescued success");

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 updateRescued success");

			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getMyContent());
			psmt.setString(3, dto.getFilename());
			psmt.setInt(4, seq);
			count = psmt.executeUpdate();
			System.out.println("3/3 updateRescued success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateRescued fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}

		return count > 0 ? true : false;

	}

	////////////////////// Condition 값 바꾸기 /////////////////////////

// TODO 입양 취소 
	public void conditionChange0(int seq, String title) {
		String sql = " UPDATE RESCUEDDOG " 
	               + " SET CONDITION=0, TITLE=? " 
				   + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int num = title.lastIndexOf("]");
		String changeTitle = title.substring(num + 1);
		System.out.println("변경 전 이름 : " + title);
		System.out.println("변경 후 이름 : " + changeTitle);

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 conditionChange0 success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 conditionChange0 success");

			psmt.setString(1, changeTitle);
			psmt.setInt(2, seq);
			System.out.println("3/3 conditionChange0 success");
			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("conditionChange0 fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}

	}

//TODO 입양진행중
	public void conditionChange1(int seq, String title) {
		String sql = " UPDATE RESCUEDDOG " 
	               + " SET CONDITION=1, TITLE=? " 
				   + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		String changeTitle = "";
		
		//입양완료에서 입양진행중으로 가는경우 
		int num = title.lastIndexOf("]");
		if(num != 0) {
			title = title.substring(num + 1);			
		}
		changeTitle = "[입양진행중] " + title;
		System.out.println("변경 전 이름 : " + title);
		System.out.println("변경 후 이름 : " + changeTitle);

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 conditionChange1 success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 conditionChange1 success");

			psmt.setString(1, changeTitle);
			psmt.setInt(2, seq);

			psmt.executeUpdate();
			System.out.println("3/3 conditionChange1 success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("conditionChange1 fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}

	}

// TODO 입양 완료
	public void conditionChange2(int seq, String title) {
		String sql = " UPDATE RESCUEDDOG " 
	               + " SET CONDITION=2, TITLE=?" 
				   + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int num = title.lastIndexOf("]");
		String changeTitle = title.substring(num + 1);
		changeTitle = "[입양완료] " + changeTitle;
		System.out.println("변경 전 이름 : " + title);
		System.out.println("변경 후 이름 : " + changeTitle);

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 conditionChange2 success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 conditionChange2 success");

			psmt.setString(1, changeTitle);
			psmt.setInt(2, seq);

			psmt.executeUpdate();
			System.out.println("3/3 conditionChange2 success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("conditionChange2 fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}

	}

	// 삭제
	public void deleteRescued(int seq) {
		String sql = " UPDATE RESCUEDDOG " 
	               + " SET DEL=1 " 
				   + " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		System.out.println("1/3 deleteRescued success");

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 deleteRescued success");

			psmt.setInt(1, seq);
			psmt.executeUpdate();
			System.out.println("3/3 deleteRescued success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("deleteRescued fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}

	}

} // class
