package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

import Dto.afterStoryDto;
import DB.DBClose;
import DB.DBConnection;




public class afterStoryDao {

	
	private static afterStoryDao dao = new afterStoryDao();
	
	public afterStoryDao() {
		DBConnection.initConnection();
	}
	
	public static afterStoryDao getInstance() {
		return dao;
	}
	
	public List<afterStoryDto> storyList(String choice, String search, int page){
		
		String sql =  " SELECT SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL "
					+ " FROM ";

		sql += " (SELECT ROW_NUMBER()OVER(ORDER BY SEQ DESC) AS RNUM, "
				+ " SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL "
				+ "	FROM AFTERSTORY "
				+ " WHERE DEL = 0 ";
	
		
		String sWord = "";
		
		  if(choice.equals("title")) {
			  sWord = " AND TITLE LIKE '%" + search + "%' "; 
		  } else if(choice.equals("content")) {
			  sWord = " AMD MYCONTENT LIKE '%" + search + "%' ";
		  
		  } else if(choice.equals("writer")) { 
			  sWord = " AND MYID='" + search + "'";
		  }
		
		sql = sql + sWord;
		
		sql = sql + " ORDER BY SEQ DESC) ";
		
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
		
		int start, end;
		start = 1 + 9 * page;
		end = 9 + 9 * page;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs =  null;
		
	List<afterStoryDto> list = new ArrayList<afterStoryDto>();
		
	try {
		
		conn = DBConnection.getConnection();	
		System.out.println("1/4 storyList success");
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, start);
		psmt.setInt(2, end);
		System.out.println("2/4 storyList success");
		
		rs = psmt.executeQuery();	
		System.out.println("3/4 storyList success");
		
		while (rs.next()) {
			afterStoryDto dto = new afterStoryDto(rs.getInt(1),
												  rs.getString(2),
												  rs.getString(3),
												  rs.getString(4),
												  rs.getString(5),
												  rs.getString(6),
												  rs.getInt(7),
												  rs.getInt(8));
												
				list.add(dto);
			}
			System.out.println("4/4 storyList success");
			
		} catch (Exception e) {
			  System.out.println("storyList fail");
		      e.printStackTrace();
		}	finally {
			   DBClose.close(conn, psmt, rs);
		}
			return list;	
		}
		
	// 작성용 
	public boolean writeStory(afterStoryDto dto) {
			
			String sql = " INSERT INTO AFTERSTORY "
						+ " (SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL) "
						+ " VALUES( SEQ_AFTERSTORY.NEXTVAL, ?, ?, ?, SYSDATE, ?, 0, 0)";

			
			Connection conn = null;
			PreparedStatement psmt = null;
			
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/6 writeStory success");
				
				psmt = conn.prepareStatement(sql);

				psmt.setString(1, dto.getMyid());
				psmt.setString(2, dto.getTitle());
				psmt.setString(3, dto.getMycontent());
				psmt.setString(4, dto.getFilename());
		
				System.out.println("2/6 writeStory success");
				
				count = psmt.executeUpdate();
				System.out.println("3/6 writeStory success");			
				
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				
				DBClose.close(conn, psmt, null);			
			}
			return count>0?true:false;
		}
			
	//	디테일
	public afterStoryDto storyDetail(int seq) {
		String sql = " SELECT SEQ, MYID, TITLE, MYCONTENT, "
				+ " WDATE, FILENAME, VCOUNT, DEL "
				+ " FROM AFTERSTORY "
				+ " WHERE SEQ=? ";
		
		Connection conn =null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		afterStoryDto afdto = null;		
		
		try {
			conn = DBConnection.getConnection();	; 
			System.out.println("1/4 storyDetail success");	
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq); 	
			System.out.println("2/4 storyDetail success");
			
			rs = psmt.executeQuery(); 	
			System.out.println("3/4 storyDetail success");	
			
	
			if(rs.next()) {
				int i = 1;	
			
				afdto = new afterStoryDto(rs.getInt(i++),
										  rs.getString(i++),
										  rs.getString(i++),
										  rs.getString(i++),
										  rs.getString(i++),
										  rs.getString(i++),
										  rs.getInt(i++),
										  rs.getInt(i++));
			}
			System.out.println("4/4 storyDetail success");	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
			return afdto; 
	}	
		// 수정용 
			public boolean updateStory(int seq, afterStoryDto dto) {
				String sql = " UPDATE AFTERSTORY SET "
						+ " TITLE=?, MYCONTENT=?, WDATE=SYSDATE, FILENAME=? "
						+ " WHERE SEQ=? ";
				
				Connection conn = null;
				PreparedStatement psmt = null;
				int count = 0;
				
				try {
					conn = DBConnection.getConnection();
					System.out.println("1/3 S updateStory success");
					
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, dto.getTitle());
					psmt.setString(2, dto.getMycontent());
					psmt.setString(3, dto.getFilename());
					psmt.setInt(4, seq);
					
					System.out.println("2/3 S updateStory success");
					
					count = psmt.executeUpdate();
					System.out.println("3/3 S updateStory success");
					
				} catch (Exception e) {			
					e.printStackTrace();
				} finally{
					DBClose.close(conn, psmt, null);			
				}		
				
				return count>0?true:false;
			}
			
			//	삭제용 
			public boolean deleteStory(int seq) {
				
	
				
				String sql = " UPDATE AFTERSTORY "
							+ " SET DEL=1 "
							+ " WHERE SEQ=? ";
				
				Connection conn = null;
				PreparedStatement psmt = null;
				int count = 0;
				
				try {
					conn = DBConnection.getConnection();
					System.out.println("1/3  deleteStory success");
					
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, seq);
					System.out.println("2/3  deleteStory success");
					
					count = psmt.executeUpdate();
					System.out.println("3/3  deleteStory success");
					
				} catch (Exception e) {		
					System.out.println("fail deleteStory success");
					e.printStackTrace();
				} finally {
					DBClose.close(conn, psmt, null);			
				}
				
				return count>0?true:false;
			}
			
			
		// 조회수  +  1	
		public void readcont(int seq) {
			String sql = " UPDATE AFTERSTORY " 
					+ " SET VCOUNT=VCOUNT+1 "	
					+ " WHERE SEQ=? ";

		
			Connection conn = null;
			PreparedStatement psmt = null;
		
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 readcont success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("2/3 readcont success");
				psmt.setInt(1, seq);
				System.out.println("3/3 readcont success");
				
				psmt.executeUpdate();
		
			} catch (Exception e) {
			
				System.out.println("readcount fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, null);	
			}
		}
		
		//	메인에서 후기로 이동할때 필요한 요소를 불러옴 
		public int allAfterStory(String choice, String search) {
			String sql = " SELECT COUNT(*) FROM AFTERSTORY WHERE DEL = 0 ";
					
		  String sWord = "";
		  
			if(choice.equals("title")) {
				sWord = " AND TITLE LIKE '%" + search + "%' "; } 
			else if(choice.equals("content")) {
				sWord = " AND CONTENT LIKE '%" + search + "%' ";
		  } else if(choice.equals("writer")) {
			  sWord = " AND ID='" + search + "'";
			  }
		  
		  sql = sql + sWord;

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs =  null;
			
			int len = 0;	
			
			try {
				conn = DBConnection.getConnection();	
				System.out.println("1/3 allAfterStory success");
				psmt = conn.prepareStatement(sql);	
				System.out.println("2/3 allAfterStory success");
				
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					len = rs.getInt(1);
					System.out.println("3/3 allAfterStory success");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		        DBClose.close(conn, psmt, rs);
			}
			return len;
		}
	}
	
	