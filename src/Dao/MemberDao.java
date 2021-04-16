package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBClose;
import DB.DBConnection;
import Dto.MemberDto;


public class MemberDao {

private static MemberDao dao = new MemberDao();
	
	private MemberDao() {
		DBConnection.initConnection();
	}
	
	public static MemberDao getInstance() {		
		return dao;
	}
	
//TODO 일반 회원가입
	public boolean addMember(MemberDto dto) {
		String sql =  " INSERT INTO MYMEMBER "
					+ " (SEQ, MYID, PWD, MYNAME,"
					+ " PHONENUM, EMAIL, MYMANAGER)"
					+ " VALUES( SEQ_MYMEMBER.NEXTVAL, ?, ?, ?, ?, ?, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addMember success");
				
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 addMember success");
			
			psmt.setString(1, dto.getMyid());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getMyname());
			psmt.setString(4, dto.getPhonenum());
			psmt.setString(5, dto.getEmail());
			
			count = psmt.executeUpdate();
			System.out.println("3/3 addMember success");
			
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("addMember fail");
		} finally {
			DBClose.close(conn, psmt, null);			
		}
		
		return count>0?true:false;
	}
	
	
	
//TODO 일반 로그인
	  public MemberDto login(String Id, String pwd) {
		
		String sql =  " SELECT MYID, MYNAME, EMAIL, MYMANAGER "
					+ " FROM MYMEMBER "
					+ " WHERE MYID=? AND PWD=? ";  
		  
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		MemberDto mem = null; 
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			System.out.println("1/3 login suc");
			
			psmt.setString(1, Id);
			psmt.setString(2, pwd);
			
			System.out.println("2/3 login suc");
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String myid = rs.getString(1);
				String myname = rs.getString(2);
				String email = rs.getString(3);
				int mymanager = rs.getInt(4);
				
				mem = new MemberDto(myid, email, myname, mymanager);
			}
			System.out.println("3/3 login suc");
			
		} catch (Exception e) {
			System.out.println("login fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
				
		return mem;
		}
	  
	  
	  
	  
//TODO 카카오 로그인	  
	  public MemberDto kakaoLogin(String Id) {
			
		String sql =  " SELECT MYID, MYNAME, EMAIL, MYMANAGER "
					+ " FROM MYMEMBER "
					+ " WHERE MYID=? ";  
		  
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		MemberDto mem = null; 
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			System.out.println("1/3 kakaoLogin suc");
			
			psmt.setString(1, Id);
			
			System.out.println("2/3 kakaoLogin suc");
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String myid = rs.getString(1);
				String myname = rs.getString(2);
				String email = rs.getString(3);
				int mymanager = rs.getInt(4);
				
				mem = new MemberDto(myid, email, myname, mymanager);
			}
			System.out.println("3/3 kakaoLogin suc");
			
		} catch (Exception e) {
			System.out.println("kakaoLogin fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
				
		return mem;
		}
	
	  
	  
//TODO 아이디찾기	  
	  public String getLogId(String email) {
		String sql =  " SELECT MYID"
					+ " FROM MYMEMBER"
					+ " WHERE EMAIL=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String id = "";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getId success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			System.out.println("2/3 getId success");
			
			rs = psmt.executeQuery();
			System.out.println("3/3 getId success");
			
			if(rs.next()) {
				id = rs.getString(1);
			}			
			
		} catch (SQLException e) {
			System.out.println("getId fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
		return id;
	  }
	  
	  
// TODO 아이디 중복 확인
	  public boolean getId(String Id) {
		String sql =  " SELECT MYID"
					+ " FROM MYMEMBER"
					+ " WHERE MYID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean b = false;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getId success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, Id.trim());
			System.out.println("2/3 getId success");
			
			rs = psmt.executeQuery();
			System.out.println("3/3 getId success");
			
			if(rs.next()) {
				b = true;
			}			
			
		} catch (SQLException e) {
			System.out.println("getId fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
		
		return b;
	  }
	
	  
	  
  //===============================================회원관리==============================
	  
	  public List<MemberDto> getAllMemberList(String choice, String search, int page){
		  
		  String sql = " SELECT SEQ, MYID, PWD, EMAIL, MYNAME, PHONENUM, MYMANAGER "
		  		+ " FROM ";
		  
		  sql += " (SELECT ROW_NUMBER()OVER(ORDER BY SEQ DESC) AS RNUM,"
		  		+ " SEQ, MYID, PWD, EMAIL, MYNAME, PHONENUM, MYMANAGER "
		  		+ " FROM MYMEMBER ";
		  	
		  String sWord="";
		  
		  if(choice.equals("id")) {
			  sWord = " WHERE MYID LIKE '%" + search + "%' ";
		  } else if(choice.equals("name")) {
			  sWord = " WHERE MYNAME LIKE '%" + search + "%' ";
		  }
		  
		  sql = sql + sWord;
		  sql = sql + " ORDER BY RNUM ASC) ";
		  
		  sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
		  
		  int start, end;
			start = 1 + 9 * page;
			end = 9 + 9 * page;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			
			List<MemberDto>list = new ArrayList<MemberDto>();
			
			
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/4 getAllMemberList");
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, start);
				psmt.setInt(2, end);
				System.out.println("2/4 getAllMemberList");
				
				rs = psmt.executeQuery();
				System.out.println("3/4 getAllMemberList");

				while (rs.next()) {
					MemberDto dto = new MemberDto(
												rs.getInt(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5),
												rs.getString(6),
												rs.getInt(7));
					list.add(dto);
				}
				System.out.println("4/4 getAllMemberList");

			} catch (SQLException e) {
				System.out.println("getAllMemberList fail");

				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			return list;
			
			
			
			
	  }
	
	  public int getAllMemberCount(String choice, String search) {
		  String sql = " SELECT COUNT(*) FROM MYMEMBER ";
		  
		  String sWord = "";
			if(sWord!="") {
				if(choice.equals("id")) {
					sWord = " WHERE MYID LIKE '%" + search + "%'";
				} else if (choice.equals("name")) {
					sWord = " WHERE MYNAME='" + search + "'";
				}
			}
			sql = sql + sWord;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			int len = 0;
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/4 getAllMemberCount success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("2/4 getAllMemberCount success");
				rs = psmt.executeQuery();
				System.out.println("3/4 getAllMemberCount success");
				if(rs.next()) {
					len = rs.getInt(1);
				}
				System.out.println("4/4 getAllMemberCount success");
			} catch (SQLException e) {
				System.out.println("getAllMemberCount fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			
			return len;
	  }
	  public MemberDto getMemberDetail(int seq) {
		  String sql = " SELECT SEQ, MYID, PWD, EMAIL, MYNAME, PHONENUM, MYMANAGER "
		  		+ " FROM MYMEMBER "
		  		+ " WHERE SEQ=? ";
		  
		  Connection conn = null;
		  PreparedStatement psmt = null;
		  ResultSet rs = null;
		  
		  MemberDto dto = null;
				  
		
		  conn = DBConnection.getConnection();
		  
		  try {
			conn = DBConnection.getConnection(); 
			 System.out.println("1/4 getMemberDetail"); 
			psmt = conn.prepareStatement(sql);
			 System.out.println("2/4 getMemberDetail"); 
			 psmt.setInt(1, seq);
			 rs= psmt.executeQuery();
			 System.out.println("3/4 getMemberDetail"); 

			 while (rs.next()) {
				 dto = new MemberDto(
						 			rs.getInt(1),
						 			rs.getString(2),
						 			rs.getString(3),
						 			rs.getString(4),
						 			rs.getString(5),
						 			rs.getString(6),
						 			rs.getInt(7)
						 );
				 
			 }
			 System.out.println("4/4 getMemberDetail"); 

		} catch (SQLException e) {
			 System.out.println("getMemberDetail fail"); 
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		  return dto;
		  
				
	  }
	  public boolean updateMember(int seq, String pwd, String email, String name, String phonenum) {
		  String sql = " UPDATE MYMEMBER"
		  		+ " SET PWD=?, EMAIL=?, MYNAME=?, PHONENUM=? "
		  		+ " WHERE SEQ=? ";
		  
		  Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
		
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 updateMember success");
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, pwd);
				psmt.setString(2, email);
				psmt.setString(3, name);
				psmt.setString(4, phonenum);
				psmt.setInt(5, seq);
				System.out.println("2/3 updateMember success");
				
		
				
				count = psmt.executeUpdate();
				System.out.println("3/3 updateMember success");
			} catch (SQLException e) {
				System.out.println("updateMember fail");

				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, null);
			}
			return count>0?true:false; 
	  }
	  
	  public boolean deleteMember(int seq) {
		  String sql = " DELETE FROM MYMEMBER "
					+ " WHERE SEQ=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 deleteMember");
				
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				System.out.println("2/3 deleteMember");

				count = psmt.executeUpdate();
				System.out.println("3/3 deleteMember");
				
				
			} catch (SQLException e) {
				System.out.println("deleteMember fail");
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, null);
			}
			
			return count>0?true:false;
		
	  }
	  //회원이 내 정보 보는 것!
	  public MemberDto getMyDetail(String Myid) {
		  String sql = " SELECT SEQ, MYID, PWD, EMAIL, MYNAME, PHONENUM, MYMANAGER "
		  		+ " FROM MYMEMBER "
		  		+ " WHERE MYID=? ";
		  
		  Connection conn = null;
		  PreparedStatement psmt = null;
		  ResultSet rs = null;
		  
		  MemberDto dto = null;
				  
		
		  conn = DBConnection.getConnection();
		  
		  try {
			conn = DBConnection.getConnection(); 
			 System.out.println("1/4 getMemberDetail"); 
			psmt = conn.prepareStatement(sql);
			 System.out.println("2/4 getMemberDetail"); 
			 psmt.setString(1, Myid);
			 rs= psmt.executeQuery();
			 System.out.println("3/4 getMemberDetail"); 

			 while (rs.next()) {
				 dto = new MemberDto(
						 			rs.getInt(1),
						 			rs.getString(2),
						 			rs.getString(3),
						 			rs.getString(4),
						 			rs.getString(5),
						 			rs.getString(6),
						 			rs.getInt(7)
						 );
				 
			 }
			 System.out.println("4/4 getMemberDetail"); 

		} catch (SQLException e) {
			 System.out.println("getMemberDetail fail"); 
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		  return dto;
		  
				
	  }
	  
	
	  
	  
	  
	
}
