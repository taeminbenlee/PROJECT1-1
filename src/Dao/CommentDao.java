package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBClose;
import DB.DBConnection;
import Dto.CommentDto;
import Dto.RescuedDogDto;

public class CommentDao {
	
	private static CommentDao dao = new CommentDao();
	
	public CommentDao() {
		DBConnection.initConnection();
	}
	public static CommentDao getInstance() {
		return dao;
	}



	//TODO 댓글 쓰기 
	public boolean writeComment(CommentDto dto) {
		String sql = " INSERT INTO MYCOMMENT (MYID, CONTENT, REF, FREE, DEL, SEQ) "
			       + " VALUES(?, ?, ?, ?, 0, SEQ_MYCOMMENT.NEXTVAL) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 writeComment success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 writeComment success");

			psmt.setString(1, dto.getMyID());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getRef());
			psmt.setInt(4, dto.getFree());

			count = psmt.executeUpdate();
			System.out.println("3/3 writeComment success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("writeComment fail");

		} finally {
			DBClose.close(conn, psmt, null);
		}
		System.out.println("-----------------------");
		return count > 0 ? true : false;
	}
	
	
	
	//TODO 작성된 댓글의 총 수 
	public int getAllComment(int seq, int free) {
		String sql = " SELECT COUNT (*) " 
	               + " FROM MYCOMMENT " 
				   + " WHERE DEL = 0 AND REF=? AND FREE=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		int len = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getAllComment success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			psmt.setInt(2, free);
			System.out.println("2/3 getAllComment success");

			rs = psmt.executeQuery();
			if (rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("3/3 getAllComment success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getAllComment fail");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		System.out.println("-----------------------");
		return len;
	}
	
	
	// TODO 테이블 출력용 List (Free값이 같고 부모글의 SEQ와 같은 REF값을 가진것만 출력)
	public List<CommentDto> CommentList(int page, int seq, int free) {
		String sql = " SELECT MYID, CONTENT, REF, FREE, DEL, SEQ " 
				   + " FROM "
		        + "         (SELECT ROW_NUMBER()OVER(ORDER BY SEQ) AS RNUM, "
				+ "              MYID, CONTENT, REF, FREE, DEL, SEQ "
				+ "         FROM MYCOMMENT " 
				+ "         WHERE DEL = 0 AND REF=? AND FREE=? "
                + "         ORDER BY SEQ) "
		        + " WHERE RNUM >= ? AND RNUM <= ? ";

		int start, end;
		start = 1 + 5 * page;
		end = 5 + 5 * page;

		System.out.println("start" + start);
		System.out.println("end" + end);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<CommentDto> list = new ArrayList<CommentDto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 CommentList success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			psmt.setInt(2, free);
			psmt.setInt(3, start);
			psmt.setInt(4, end);
			System.out.println("2/4 CommentList success");

			rs = psmt.executeQuery();
			System.out.println("3/4 CommentList success");

			while (rs.next()) {
				CommentDto dto = new CommentDto(rs.getString(1),
												rs.getString(2),
											    rs.getInt(3),
											    rs.getInt(4),
											    rs.getInt(5),
												rs.getInt(6));
				list.add(dto);
			}
			System.out.println("4/4 CommentList success");

		} catch (SQLException e) {
			System.out.println("CommentList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		System.out.println("-----------------------");
		return list;
	}

	
		
		//TODO 댓글 수정
		public boolean updateComment(String content, int commSeq) {
			String sql = " UPDATE MYCOMMENT " 
		               + " SET CONTENT=? "
					   + " WHERE SEQ=? ";

			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			System.out.println("1/3 updateComment success");

			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				System.out.println("2/3 updateComment success");

				psmt.setString(1, content);
				psmt.setInt(2, commSeq);

				count = psmt.executeUpdate();
				System.out.println("3/3 updateComment success");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("updateComment fail");
			} finally {
				DBClose.close(conn, psmt, null);
			}

			return count > 0 ? true : false;
		}
		
		
		
		//TODO 댓글 삭제
		public boolean deleteComment(int commSeq) {
			String sql = " UPDATE MYCOMMENT " 
		               + " SET DEL=1 "
					   + " WHERE SEQ=? ";

			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			System.out.println("1/3 deleteComment success");

			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				System.out.println("2/3 deleteComment success");

				psmt.setInt(1, commSeq);

				count = psmt.executeUpdate();
				System.out.println("3/3 deleteComment success");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("deleteComment fail");
			} finally {
				DBClose.close(conn, psmt, null);
			}

			return count > 0 ? true : false;
		}
		

	
	
}
