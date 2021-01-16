package comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CommentDAO {
	private Connection conn;	//db에 접근하는 객체
	private ResultSet rs;
	
	public CommentDAO() {
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
		String SQL = "SELECT commentID FROM comment ORDER BY commentID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 1; //첫번째 댓글인 경우
	}
	public int write(int bbsID, String userID, String commentText) {
		String SQL = "INSERT INTO comment(bbsID,userID,commentDate,commentText) VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, bbsID);
			pstmt.setString(2, userID);
			pstmt.setString(3, getDate());
			pstmt.setString(4, commentText);

			pstmt.executeUpdate();
			return getNext();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	public String getUpdateComment(int commentID) {
		String SQL = "SELECT commentText FROM comment WHERE commentID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commentID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //오류
	}
	public ArrayList<Comment> getList(int bbsID){
		String SQL = "SELECT * FROM comment WHERE bbsID= ? ORDER BY bbsID DESC"; 
		ArrayList<Comment> list = new ArrayList<Comment>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1,  bbsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment cmt = new Comment();
			
				cmt.setCommentID(rs.getInt(1));
				cmt.setBbsID(rs.getInt(2));
				cmt.setUserID(rs.getString(3));
				cmt.setCommentDate(rs.getString(4));
				cmt.setCommentText(rs.getString(5));

				list.add(cmt);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	
	public int update(int commentID, String commentText) {
		String SQL = "UPDATE comment SET commentText = ? WHERE commentID LIKE ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, commentText);
			pstmt.setInt(2, commentID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	public Comment getComment(int commentID) {
		String SQL = "SELECT * FROM comment WHERE commentID = ? ORDER BY commentID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  commentID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment cmt = new Comment();
				
				cmt.setCommentID(rs.getInt(1));
				cmt.setBbsID(rs.getInt(2));
				cmt.setUserID(rs.getString(3));
				cmt.setCommentDate(rs.getString(4));
				cmt.setCommentText(rs.getString(5));

				return cmt;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int delete(int commentID) {
		String SQL2 =  "SET foreign_key_checks = 0";
		String SQL = "DELETE FROM comment WHERE commentID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL2);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, commentID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	
	public void setting(int allCount) {
	       
        try {
            String sql = "set @cnt=0 ";
            String sql2= "update comment set commentID=@cnt:=@cnt+1 ";
            String sql3 = "alter table bbs auto_increment="+allCount;
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
	            String sql = "select count(*) from comment";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	                listCount = rs.getInt(1);
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } return listCount;
	    }    
}
