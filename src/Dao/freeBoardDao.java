package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dto.BbsDto;
import DB.DBClose;
import DB.DBConnection;

public class freeBoardDao {
	
private static freeBoardDao dao = new freeBoardDao();

	private freeBoardDao() {
		DBConnection.initConnection();
	}
		
	public static freeBoardDao getInstance() {
		return dao;
	}
	
	
	
	
	public List<BbsDto> getFreeBoardPagingList(String choice, String search, int page){
		System.out.println("getFreeBoardPagingList page:" + page);
		
		String sql = " SELECT SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL "
				   + " FROM ";
		
		sql += " (SELECT ROW_NUMBER()OVER(ORDER BY SEQ DESC) AS RNUM, "
				+ " SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL, FREE "
				+ " FROM BBS "
				+ " WHERE DEL=0 AND FREE=1 ";
		
		
		String sWord ="";
		
		if(choice.equals("id")){
			sWord = " AND MYID LIKE '%" + search + "%'";
		} else if (choice.equals("address")){
			sWord = " AND ADDRESS LIKE '%" + search + "%' ";
		}
		
		sql = sql + sWord;
		sql = sql + " ORDER BY RNUM ASC) ";
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
					
		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/4 getFreeBoardPagingList success");

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getFreeBoardPagingList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getFreeBoardPagingList success");
			
			while (rs.next()) {
				 BbsDto dto = new BbsDto(				rs.getInt(1),
														rs.getString(2),
														rs.getString(3),
														rs.getString(4),
														rs.getString(5),
														rs.getString(6),
														rs.getInt(7),
														rs.getInt(8)
													);
				list.add(dto);
			}
			
			
			System.out.println("4/4 getFreeBoardPagingList success");

		} catch (SQLException e) {
			System.out.println("getFreeBoardPagingList fail");

			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
		
		
	}
	
	public int getAllReport(String choice, String search) {
		String sql = " SELECT COUNT(*) FROM BBS WHERE DEL=0 AND FREE=1 ";
		
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
	
	public boolean writeFree(BbsDto dto) {
		String sql = " INSERT INTO BBS (SEQ, MYID, TITLE,"
				+ " MYCONTENT, WDATE, FILENAME, VCOUNT, DEL, FREE)"
				+ " VALUES(SEQ_BBS.NEXTVAL, ?, ?, ?, SYSDATE, ?, 0, 0, 1) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S writeFree");
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getMyid());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getMycontent());
			psmt.setString(4, dto.getFilename());
			System.out.println("2/3 S writeFree");
			
			count = psmt.executeUpdate();	
			System.out.println("3/3 S writeFree");
	}catch (SQLException e) {
		System.out.println("fail writeFree");
		e.printStackTrace();
	} finally {
		DBClose.close(conn, psmt, null);
	}
		return count>0?true:false;
	}//end writeFree
	
	public void readcount(int seq) {
		int count=0;
		
		String sql =  " UPDATE BBS "
					+ " SET VCOUNT = VCOUNT+1 "
					+ " WHERE SEQ=? AND FREE=1 ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S  readcount");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq );
			System.out.println("2/3 S  readcount");
			
			count=psmt.executeUpdate();
			System.out.println("3/3 S  readcount");
			
			
		} catch (Exception e) {
			System.out.println("F readcount");
		}finally{
			DBClose.close(conn, psmt, null);
		}
		
	}//end readcount
	
	public BbsDto getFree(int seq) {
		String sql =  " SELECT SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL, VCOUNT "
					+ " FROM BBS "
					+ " WHERE SEQ = ? AND DEL = 0 AND FREE=1 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BbsDto bbs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getFree Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/4 getFree Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getFree Success");
			
			if(rs.next()) {
				int i = 1;				
				bbs = new BbsDto(rs.getInt(i++), 
								rs.getString(i++), 
								rs.getString(i++), 
								rs.getString(i++), 
								rs.getString(i++), 
								rs.getString(i++),
								rs.getInt(i++),
								rs.getInt(i++),
								rs.getInt(i++)
								);
				readcount(bbs.getSeq());
			}
			System.out.println("4/4 getFree Success");
			
		} catch (Exception e) {		
			System.out.println("getFree Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
				
		return bbs;
		
	}//end getFree
	public boolean updateFree(int seq, String title, String content, String filename) {
		System.out.println("updateFree Dao Process");
		String sql =  " UPDATE BBS"
					+ " SET TITLE=?, MYCONTENT=?, WDATE=SYSDATE, FILENAME=? "
					+ " WHERE SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S updateFree");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, filename);
			psmt.setInt(4, seq);
			
			
			System.out.println("2/3 S updateFree");

			count = psmt.executeUpdate();			
			System.out.println("3/3 S updateFree");
			
		} catch (SQLException e) {
			System.out.println("fail updateFree");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
				
		return count>0?true:false;
	}//end updateFree
	
	public boolean deleteFree(int seq) {
	      String sql =  " UPDATE BBS "
	               + " SET DEL = 1 "
	               + " WHERE SEQ = ? ";
	      
	      Connection conn = null;
	      PreparedStatement psmt = null;
	      int count = 0;
	      
	      try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/3 S deleteFree");
	         
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, seq);
	         System.out.println("2/3 S deleteFree");
	         
	         count = psmt.executeUpdate();
	         System.out.println("3/3 S deleteFree");
	         
	      } catch (Exception e) {      
	         System.out.println("fail deleteFree");
	         e.printStackTrace();
	      } finally {
	         DBClose.close(conn, psmt, null);         
	      }
	      
	      return count>0?true:false;
	   }//end deleteFree
	
	
}
