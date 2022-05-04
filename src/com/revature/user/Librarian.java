package com.revature.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.user.Book;
public class Librarian {
	UserManagement um=new UserManagement();
	public boolean login(int userid,String password ) throws ClassNotFoundException, SQLException {
		int username1=userid;
		String password1=password;
		String role=UserManagement.validateUser(username1, password1);
		if(role!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean add_book() throws ClassNotFoundException, SQLException {
		Book bk=new Book();
		return bk.insertbook();
	}
	public boolean issue_Book(int userid, int bookid) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		boolean bool=false;
		int username1=userid;
		String sql="select password from user where userid=?";
		PreparedStatement psmt=conn.prepareStatement(sql);
		psmt.setInt(1,userid);
		ResultSet rs= psmt.executeQuery();
		if(rs.next()) {
			String password1=rs.getString("password");
			String role=UserManagement.validateUser(username1, password1);
			if(role!=null) 
			{
				String sql1="select * from book where bookid=?";
				PreparedStatement psmt1=conn.prepareStatement(sql1);
				psmt1.setInt(1,bookid);		
				ResultSet rs1= psmt1.executeQuery();
				if(rs1.next()) {
					int q=rs1.getInt(3);
					//checking book availability
					//status update
					if(q>0) {
						q=q-1;
						String sql2="update book set count=? where bookid=?";
						PreparedStatement pst2=conn.prepareStatement(sql2);
						pst2.setInt(2,bookid);
						pst2.setInt(1,q);
						if(pst2.executeUpdate()==1)
						{
							bool=true;
						}
						else
						{
							bool=false;
						}
					}
					else {
						System.out.println("no books available");
					}
				}
			}
		}
		return bool;
	}

	public boolean return_Book(int bid) throws ClassNotFoundException, SQLException{
		Connection conn = ConnectionFactory.getConnection();
		boolean b=false;
		String sql1="select * from book where bookid=?";
		PreparedStatement psmt1=conn.prepareStatement(sql1);
		psmt1.setInt(1,bid);		
		ResultSet rs1= psmt1.executeQuery();
		if(rs1.next()) {
			int q1=rs1.getInt(3);
			if(q1>=0)
			{
				//status update
				q1=q1+1;
				String sql2="update book set count=? where bookid=?";
				PreparedStatement pst2=conn.prepareStatement(sql2);
				pst2.setInt(1,q1);
				pst2.setInt(2,bid);
				if(pst2.executeUpdate()==1)
				{
					b = true;
				}
				else
				{
					b = false;
				}  
			}
		}
		return b;
	}

}
