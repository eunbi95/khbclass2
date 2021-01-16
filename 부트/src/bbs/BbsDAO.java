package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {

	private Connection conn;	//db에 접근하는 객체
	private ResultSet rs;
	
	public BbsDAO() {
		try {
			String dbURL="jdbc:mysql://khgthree.cafe24.com:3306/khgthree?characterEncoding=utf8";
	        String dbID ="khgthree";
	        String dbPassword ="wjdqhrydbrdnjs3";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //데이터베이스 오류
	}
	public int getNext() {
		String SQL = "SELECT bbsID FROM bbs ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 1; //첫번째 게시물인 경우
	}
	
	public int getCount() {
		String SQL = "SELECT COUNT(*) FROM bbs";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {	
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getCount(String search) {
		String SQL = "SELECT COUNT(*) FROM bbs WHERE bbsTitle like ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			if (rs.next()) {	
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL = "INSERT INTO bbs(bbsTitle,userID,bbsDate,bbsContent,bbsreadcount) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, userID);
			pstmt.setString(3, getDate());
			pstmt.setString(4, bbsContent);
			pstmt.setInt(5, 1);

			pstmt.executeUpdate();
			return getNext();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	//글리스트 가져오는것
	public ArrayList<Bbs> getList(int pageNumber){
		String SQL = "SELECT * FROM bbs WHERE bbsID < ?  ORDER BY bbsID DESC LIMIT 10"; 
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsreadcount(rs.getInt(6));
				list.add(bbs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	
	public boolean nextPage (int pageNumber) {
		String SQL = "SELECT * FROM bbs WHEREbbsID < ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false; //데이터베이스 오류
	}
	
	public boolean searchNextPage (int boardID, int pageNumber, String search) {
		String SQL = "SELECT * FROM bbs WHERE bbsID < ? AND (bbsTitle like ? OR bbsContent like ?)ORDER BY bbsID DESC LIMIT 10"; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  getNext() - (pageNumber - 1) * 10);
			pstmt.setString(2, "%"+search+"%");
			pstmt.setString(3, "%"+search+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false; //데이터베이스 오류
	}
	
	public Bbs getBbs(int bbsID) {
		String SQL = "SELECT * FROM bbs WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  bbsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsreadcount(rs.getInt(6));
				return bbs;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		String SQL = "UPDATE bbs SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public int delete(int bbsID) {
		String SQL2 =  "SET foreign_key_checks = 0";
		String SQL = "delete from bbs where bbsid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL2);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public void setting(int allCount) {
	       
        try {
            String sql = "set @cnt=0";//열카운팅할때 많이씀 @
            String sql2= "update bbs set bbsid=@cnt:=@cnt+1";
            String sql3 = "alter table bbs auto_increment="+allCount;//전체게시
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(sql3);
            pstmt.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	  public int getAllCount() {
		  
	        int listCount = 0;
	       
	        try {
	            String sql = "select count(*) from bbs";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	                listCount = rs.getInt(1);
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } return listCount;
	    }    
	  
	  public ArrayList<Bbs> SgetList(int pageNumber,String search,String col)
		{
		  //전체 
		  if(col.equals("none")) {
			  
			String SQL ="select * from (SELECT  @ROWNUM := @ROWNUM + 1 AS rs,T.*FROM  bbs T ,(SELECT @ROWNUM := 0 )TMP where bbsTitle like ? OR bbsContent like ? or userID like ?  ORDER BY bbsID DESC ) a where rs < ? order by rs desc LIMIT 10";
			ArrayList<Bbs> list = new ArrayList<Bbs>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1,"%"+search+"%");
				pstmt.setString(2,"%"+search+"%");
				pstmt.setString(3,"%"+search+"%");
				pstmt.setInt(4,  SgetNext(search,col) - (pageNumber - 1) * 10);
			
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Bbs bbs = new Bbs();
				
					bbs.setBbsID(rs.getInt(2));
					bbs.setBbsTitle(rs.getString(3));
					bbs.setUserID(rs.getString(4));
					bbs.setBbsDate(rs.getString(5));
					bbs.setBbsContent(rs.getString(6));
					bbs.setBbsreadcount(rs.getInt(7));
			
					list.add(bbs);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return list; //데이터베이스 오류
		  }
		  // 제목
		  else if(col.equals("title"))
		  {
			  String SQL ="select * from (SELECT  @ROWNUM := @ROWNUM + 1 AS rs,T.*FROM  bbs T ,(SELECT @ROWNUM := 0 )TMP where bbsTitle like ?  ORDER BY bbsID DESC ) a where rs < ? order by rs desc LIMIT 10";
				ArrayList<Bbs> list = new ArrayList<Bbs>();
				try {
					PreparedStatement pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1,"%"+search+"%");
					pstmt.setInt(2,  SgetNext(search,col) - (pageNumber - 1) * 10);
				
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Bbs bbs = new Bbs();
					
						bbs.setBbsID(rs.getInt(2));
						bbs.setBbsTitle(rs.getString(3));
						bbs.setUserID(rs.getString(4));
						bbs.setBbsDate(rs.getString(5));
						bbs.setBbsContent(rs.getString(6));
						bbs.setBbsreadcount(rs.getInt(7));
				
						list.add(bbs);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				return list; //데이터베이스 오류
		  }else if(col.equals("content"))
		  {
			  String SQL ="select * from (SELECT  @ROWNUM := @ROWNUM + 1 AS rs,T.*FROM  bbs T ,(SELECT @ROWNUM := 0 )TMP where bbsContent like ?  ORDER BY bbsID DESC ) a where rs < ? order by rs desc LIMIT 10";
				ArrayList<Bbs> list = new ArrayList<Bbs>();
				try {
					PreparedStatement pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1,"%"+search+"%");
					pstmt.setInt(2,  SgetNext(search,col) - (pageNumber - 1) * 10);
				
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Bbs bbs = new Bbs();
					
						bbs.setBbsID(rs.getInt(2));
						bbs.setBbsTitle(rs.getString(3));
						bbs.setUserID(rs.getString(4));
						bbs.setBbsDate(rs.getString(5));
						bbs.setBbsContent(rs.getString(6));
						bbs.setBbsreadcount(rs.getInt(7));
				
						list.add(bbs);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				return list; //데이터베이스 오류
		  }
		  else if(col.equals("author"))
		  {
			  String SQL ="select * from (SELECT  @ROWNUM := @ROWNUM + 1 AS rs,T.*FROM  bbs T ,(SELECT @ROWNUM := 0 )TMP where userID like ?  ORDER BY bbsID DESC ) a where rs < ? order by rs desc LIMIT 10";
				ArrayList<Bbs> list = new ArrayList<Bbs>();
				try {
					PreparedStatement pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1,"%"+search+"%");
					pstmt.setInt(2,  SgetNext(search,col) - (pageNumber - 1) * 10);
				
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Bbs bbs = new Bbs();
					
						bbs.setBbsID(rs.getInt(2));
						bbs.setBbsTitle(rs.getString(3));
						bbs.setUserID(rs.getString(4));
						bbs.setBbsDate(rs.getString(5));
						bbs.setBbsContent(rs.getString(6));
						bbs.setBbsreadcount(rs.getInt(7));
				
						list.add(bbs);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				return list; //데이터베이스 오류
		  }
		  else
		  {
			  return null;
		  }
		  
		  
		}

	  
	  public int SgetNext(String search,String col) {
		  	
		  	int k=0;
			String SQL;
			PreparedStatement pstmt = null;
			try {
				
				if(col.equals("none"))
				{	
					SQL ="SELECT  @ROWNUM := @ROWNUM + 1 AS ROWNUM,T.*FROM  bbs T ,(SELECT @ROWNUM := 0 ) TMP where bbsTitle like ? OR bbsContent like ? or userID like ? ORDER BY bbsID DESC";
					pstmt=conn.prepareStatement(SQL);
					pstmt.setString(1,"%"+search+"%");
					pstmt.setString(2,"%"+search+"%");
					pstmt.setString(3,"%"+search+"%");
					
					
				}
				else if(col.equals("title"))
				{
					SQL ="SELECT  @ROWNUM := @ROWNUM + 1 AS ROWNUM,T.*FROM  bbs T ,(SELECT @ROWNUM := 0 ) TMP where bbsTitle like ? ORDER BY bbsID DESC";
					pstmt=conn.prepareStatement(SQL);
					pstmt.setString(1,"%"+search+"%");
				}
				else if(col.equals("content"))
				{
					SQL ="SELECT  @ROWNUM := @ROWNUM + 1 AS ROWNUM,T.*FROM  bbs T ,(SELECT @ROWNUM := 0 ) TMP where bbsContent like ? ORDER BY bbsID DESC";
					pstmt=conn.prepareStatement(SQL);
					pstmt.setString(1,"%"+search+"%");
				}
				else if(col.equals("author"))
				{
					SQL ="SELECT  @ROWNUM := @ROWNUM + 1 AS ROWNUM,T.*FROM  bbs T ,(SELECT @ROWNUM := 0 ) TMP where userID like ? ORDER BY bbsID DESC";
					pstmt=conn.prepareStatement(SQL);
					pstmt.setString(1,"%"+search+"%");
				}
			
				
				rs= pstmt.executeQuery();
				while(rs.next())
				{
					k=rs.getInt(1)+1 ;
					
				} 
				return k;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return -1;
		}
	  public int readcount(int bbsid) {
		  String SQL ="select bbsreadcount from bbs where bbsid=?";
			try {
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				pstmt.setInt(1,bbsid);
				rs= pstmt.executeQuery();
				if(rs.next())
				{
					return rs.getInt(1);
				} 
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return -1;
		  
	  }
	  public int readcountUpdate(int bbsid) {
		  String SQL ="UPDATE bbs SET bbsreadcount=bbsreadcount+1 WHERE bbsid=?";
			try {
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				pstmt.setInt(1,bbsid);
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return -1;
		  
	  }
	  
	  
	
}