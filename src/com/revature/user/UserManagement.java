package com.revature.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.user.ConnectionFactory;;
public class UserManagement {
	public static String validateUser(int userid, String password) throws ClassNotFoundException, SQLException {
		String role = "";
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "SELECT role FROM user WHERE userid = ? AND password = ?" ;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, userid);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			System.out.println("");
			role=rs.getString("role");
		}
		else {
			System.out.println("user is invalid");
			role=null;
		}
		return role;
	}
	
	public boolean createUser(int userid,String username, String password, String role) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.getConnection();
		String sql = "INSERT INTO user (userid,username,password, role) VALUES (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, userid);
		pstmt.setString(2, username);
		pstmt.setString(3, password);
		pstmt.setString(4, role);
		if(pstmt.executeUpdate() == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean deleteUser(int userid) throws ClassNotFoundException, SQLException {
		Connection co = ConnectionFactory.getConnection();
		boolean b=false;
		String sql="select password from user where userid=?";
		PreparedStatement psmt=co.prepareStatement(sql);
		psmt.setInt(1,userid);
		ResultSet rs= psmt.executeQuery();
		if(rs.next()) {
		String password1=rs.getString("password");
		String role=UserManagement.validateUser(userid, password1);
		if(role!=null) {
			String sql1="delete from user where userid=?";
			PreparedStatement psmt1=co.prepareStatement(sql1);
			psmt1.setInt(1,userid);
			if(psmt1.executeUpdate()==1) {
				b= true ;
			}
			else {
				b= false;
			}
			
		}
		System.out.println("Deleting user "+userid);
	
		}
		return b;
	}

}