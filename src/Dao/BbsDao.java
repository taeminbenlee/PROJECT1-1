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



public class BbsDao {
   
   private static BbsDao dao= new BbsDao();
   
   private BbsDao() {
      
   }
   
   public static BbsDao getInstance() {
      return dao;
   }
   
public boolean writeBbs(BbsDto bbs) {
      String sql =  " INSERT INTO BBS (SEQ, MYID, "
               + " TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL, FREE)"
               + " VALUES(SEQ_BBS.NEXTVAL, ?, ?, ?, SYSDATE, ?, 0, 0, 0) ";
      
      Connection conn = null;
      PreparedStatement psmt = null;      
      int count = 0;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/3 S writeBbs");
            
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, bbs.getMyid());
         psmt.setString(2, bbs.getTitle());
         psmt.setString(3, bbs.getMycontent());
         psmt.setString(4, bbs.getFilename());
         
         System.out.println("2/3 S writeBbs");
         
         count = psmt.executeUpdate();   
         System.out.println("3/3 S writeBbs");
         
      } catch (SQLException e) {
         System.out.println("fail writeBbs");
         e.printStackTrace();
      } finally {
         DBClose.close(conn, psmt, null);
      }

      return count>0?true:false;
   }//end writeBbs
   
   
public List<BbsDto> getBbsList() {
      
      String sql =  " SELECT SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL "
               + " FROM BBS "
               + " WHERE DEL = 0 AND FREE=0 "
               + " ORDER BY SEQ DESC ";
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      List<BbsDto> list = new ArrayList<BbsDto>();
      
conn = DBConnection.getConnection();
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/4 S getBbsList");
         
         psmt = conn.prepareStatement(sql);
         System.out.println("2/4 S getBbsList");
         
         rs = psmt.executeQuery();
         System.out.println("3/4 S getBbsList");
         
         while(rs.next()) {
            int i = 1;
            BbsDto dto = new BbsDto(rs.getInt(i++), 
                              rs.getString(i++), 
                              rs.getString(i++), 
                              rs.getString(i++), 
                              rs.getString(i++), 
                              rs.getString(i++),
                              rs.getInt(i++), 
                              rs.getInt(i++) 
                              );
            list.add(dto);
         }
         System.out.println("4/4 S getBbsList");
         
      } catch (SQLException e) {   
         System.out.println("Fail getBbsList");
         e.printStackTrace();
      } finally {
         DBClose.close(conn, psmt, rs);         
      }
      
      return list;
   }//end getBbsList
   
public BbsDto getBbs(int seq) {
      String sql =  " SELECT SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL "
               + " FROM BBS "
               + " WHERE SEQ = ? AND DEL = 0 AND FREE=0 ";
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      BbsDto bbs = null;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/4 getBbs Success");
         
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, seq);
         System.out.println("2/4 getBbs Success");
         
         rs = psmt.executeQuery();
         System.out.println("3/4 getBbs Success");
         
         if(rs.next()) {
            int i = 1;            
            bbs = new BbsDto(rs.getInt(i++), 
                        rs.getString(i++), 
                        rs.getString(i++), 
                        rs.getString(i++), 
                        rs.getString(i++), 
                        rs.getString(i++),
                        rs.getInt(i++),
                        rs.getInt(i++)
                        );            
         }
         System.out.println("4/4 getBbs Success");
         
      } catch (Exception e) {      
         System.out.println("getBbs Fail");
         e.printStackTrace();
      } finally {
         DBClose.close(conn, psmt, rs);         
      }
            
      return bbs;
      
   }//end getBbs
   
public boolean deleteBbs(int seq) {
      String sql =  " UPDATE BBS "
               + " SET DEL = 1 "
               + " WHERE SEQ = ? ";
      
      Connection conn = null;
      PreparedStatement psmt = null;
      int count = 0;
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/3 S deleteBbs");
         
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, seq);
         System.out.println("2/3 S deleteBbs");
         
         count = psmt.executeUpdate();
         System.out.println("3/3 S deleteBbs");
         
      } catch (Exception e) {      
         System.out.println("fail deleteBbs");
         e.printStackTrace();
      } finally {
         DBClose.close(conn, psmt, null);         
      }
      
      return count>0?true:false;
   }//end deleteBbs
   
public void readcount(int seq) {
         int count=0;
         
         String sql =  " UPDATE BBS "
                  + " SET VCOUNT = VCOUNT+1 "
                  + " WHERE SEQ=? ";
         
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

public boolean updateBbs(int seq, String title, String content, String filename) {
	System.out.println("updateBbs Dao Process");
	String sql =  " UPDATE BBS"
				+ " SET TITLE=?, MYCONTENT=?, WDATE=SYSDATE, FILENAME=? "
				+ " WHERE SEQ=? ";
	
	Connection conn=null;
	PreparedStatement psmt=null;
	
	int count = 0;
	
	try {
		conn = DBConnection.getConnection();
		System.out.println("1/3 S updateBbs");
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, title);
		psmt.setString(2, content);
		psmt.setString(3, filename);
		psmt.setInt(4, seq);
		
		System.out.println("2/3 S updateBbs");

		count = psmt.executeUpdate();			
		System.out.println("3/3 S updateBbs");
		
	} catch (SQLException e) {
		System.out.println("fail updateBbs");
		e.printStackTrace();
	} finally {
		DBClose.close(conn, psmt, null);
	}
			
	return count>0?true:false;
}//end updateBbs


public List<BbsDto> getBbsPagingList(String choice, String search, int pageNumber) {
      String sql =  " SELECT RNUM, SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL "
               + " FROM ";
      sql += " (SELECT ROW_NUMBER()OVER(ORDER BY SEQ DESC) AS RNUM, "
         +  " SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL, FREE "
         +  " FROM BBS "
         +  " WHERE DEL = 0 AND FREE=0 ";
      
      String sWord = "";
      if(!search.equals("")) {
      if(choice.equals("title")) {
         sWord = " AND TITLE LIKE '%"+ search + "%' ";
      }else if (choice.equals("content")) {
         sWord = " AND CONTENT LIKE '%"+ search + "%' ";
      }else if (choice.equals("writer")) {
         sWord = " AND ID= '"+ search + "'";
      }
      }
      
      sql = sql +sWord;
      
      sql = sql + " ORDER BY RNUM ASC) ";
      
      sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
      
      int start, end; 
      start = 1+9*pageNumber;
      end = 9+9 * pageNumber;
      
      Connection conn = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      List<BbsDto> list = new ArrayList<BbsDto>();
      
      try {
         conn = DBConnection.getConnection();
         System.out.println("1/4 getBbsPagingList success");
            
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, start);
         psmt.setInt(2, end);
         System.out.println("2/4 getBbsPagingList success");
         
         System.out.println("겟페이징리스트 sql : "+sql);
         
         rs = psmt.executeQuery();         
         System.out.println("3/4 getBbsPagingList success");
         
         int i =1;
         while(rs.next()) {
            BbsDto dto = new BbsDto(
                              rs.getInt(1), 
                              rs.getInt(2), 
                              rs.getString(3), 
                              rs.getString(4), 
                              rs.getString(5), 
                              rs.getString(6), 
                              rs.getString(7), 
                              rs.getInt(8), 
                              rs.getInt(9) );
                           
            list.add(dto);
         }         
         System.out.println("4/4 getBbsPagingList success");
         
      } catch (SQLException e) {   
         System.out.println("getBbsPagingList fail");
         e.printStackTrace();
      } finally {         
         DBClose.close(conn, psmt, rs);         
      }
      
      return list;
   }//end getBbsPagingList

   /*
    * public List<BbsDto> searchBbsList(String choice, String keyword) {
    * System.out.println("searchBbsList 실행");
    * 
    * String sql =
    * " SELECT RNUM, SEQ, MYID, TITLE, MYCONTENT, WDATE, FILENAME, VCOUNT, DEL" +
    * " FROM BBS ";
    * 
    * String sWord = " WHERE ";
    * 
    * if(!keyword.equals("")) { if(choice.equals("title")) { sWord =
    * " TITLE LIKE '%" + keyword + "%' AND"; }else if(choice.equals("content")) {
    * sWord = " CONTENT LIKE '%" + keyword + "%' AND"; }else
    * if(choice.equals("writer")) { sWord = " WRITER LIKE '%" + keyword + "%' AND";
    * } }
    * 
    * sWord += " DEL = 0 ";
    * 
    * sql = sql + sWord;
    * 
    * sql = sql + " ORDER BY SEQ RNUM ";
    * 
    * System.out.println("sql : "+ sql);
    * 
    * Connection conn = null; PreparedStatement psmt = null; ResultSet rs = null;
    * 
    * List<BbsDto> list = new ArrayList<BbsDto>();
    * 
    * try { conn = DBConnection.getConnection();
    * System.out.println("1/4 searchBbsList success");
    * 
    * psmt = conn.prepareStatement(sql);
    * System.out.println("2/4 searchBbsList success");
    * 
    * rs = psmt.executeQuery(); System.out.println("3/4 searchBbsList success");
    * 
    * int i = 1; while(rs.next()) { BbsDto dto = new BbsDto(rs.getInt(i++),
    * rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
    * rs.getString(i++), rs.getInt(i++), rs.getInt(i++)); list.add(dto); }
    * System.out.println("4/4 searchBbsList success");
    * 
    * } catch (SQLException e) { System.out.println("searchBbsList fail");
    * e.printStackTrace(); } finally { DBClose.close(conn, psmt, rs); }
    * 
    * return list; }
    *///end searchBbsList

public int getAllBbs(String choice, String search) {
   String sql =  " SELECT COUNT(*) "
            + " FROM BBS"
            + " WHERE DEL = 0 AND FREE = 0 ";
            
   
   String sWord = "";
   if(!search.equals("")) {
      if(choice.equals("title")) {
         sWord = " AND TITLE LIKE '%" + search + "%' ";
      }else if(choice.equals("content")) {
         sWord = " AND CONTENT LIKE '%" + search + "%' ";
      }else if(choice.equals("writer")) {
         sWord = " AND ID='" + search + "'";
      }
   } 
    
   sql = sql + sWord;
   
   
   
      /*
       * String sql = " SELECT COUNT(*) FROM BBS ";
       * 
       * String sWord = "WHERE DEL = 0 ";
       * 
       * if(search.equals(null) || search.equals("")) {
       * 
       * }else { if(choice.equals("title")) { if(!search.equals("")) { sWord +=
       * " AND TITLE LIKE '" + search + "' "; } }else if(choice.equals("content")) {
       * if(!search.equals("")) { sWord += " AND MYCONTENT LIKE '" + search + "' "; }
       * 
       * }else if(choice.equals("writer")) { if(!search.equals("")) { sWord +=
       * " AND MYID='" + search + "' "; } } }
       */
      
   System.out.println("sql : "+ sql);
   Connection conn = null;
   PreparedStatement psmt = null;
   ResultSet rs = null;
   int len = 0;
   
   try {
      conn = DBConnection.getConnection();
      System.out.println("1/3 getAllBbs success");
      
      psmt = conn.prepareStatement(sql);
      System.out.println("2/3 getAllBbs success");
      
      rs = psmt.executeQuery();
      
      if(rs.next()) {
         len = rs.getInt(1);
      }
      
      System.out.println("3/3 getAllBbs success");
      
   } catch (SQLException e) {
      System.out.println("getAllBbs fail");
      e.printStackTrace();
   } finally {
      DBClose.close(conn, psmt, rs);         
   }
   
   return len;
   
}


}