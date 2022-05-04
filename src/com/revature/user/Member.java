package com.revature.user;
import java.sql.SQLException;

public class Member {
	UserManagement um=new UserManagement();
	Librarian lb=new Librarian();
	public boolean register(int userid,String username,String password,String role) throws ClassNotFoundException, SQLException {
		int uid=userid;
		String un=username;
		String pw=password;
		String r=role;
		return um.createUser(uid,un, pw, r);
	}
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
	public boolean issuebook(int id,int book_id) throws ClassNotFoundException, SQLException {
		int i=id;
		int bid=book_id;
		return lb.issue_Book(i,bid);
	}
	public boolean returnbook(int book_id) throws ClassNotFoundException, SQLException {
		int bid=book_id;
		return lb.return_Book(bid);
	}
}
