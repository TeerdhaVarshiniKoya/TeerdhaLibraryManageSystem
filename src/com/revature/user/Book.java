package com.revature.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class Book {
	Scanner sc=new Scanner(System.in);
	public boolean insertbook() throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		boolean b=false;
		String rep = "y";
		while(rep.equalsIgnoreCase("y") ) {
			System.out.println("enter book_id");
			int bookid=sc.nextInt();
			System.out.println("enter book name");
			String bname=sc.next();
			System.out.println("enter no of books");
			int count=sc.nextInt();
			String sql = "insert into book values(?,?,?)" ;
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bookid);
			pstmt.setString(2,bname);
			pstmt.setInt(3,count);
			int nor= pstmt.executeUpdate();
			if(nor>0) {
				b= true;
			}
			else {
				b=false;
			}
			System.out.println();
			System.out.print("Do you want to continue to add books (Y | N) ? ");
			rep = sc.next();
		}
		return b;	
	}	
}
