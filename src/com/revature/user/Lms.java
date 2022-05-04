package com.revature.user;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Lms {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		UserManagement userManagement = new UserManagement();
		Librarian Lb=new Librarian();
		Member mb=new Member();

		try {
			System.out.println("welcome to LIBRARY MANAGEMENT SYSTEM ");
			System.out.println("---------------------------");
			System.out.println("1.Admin");
			System.out.println("2.Librarian");
			System.out.println("3.member");
			System.out.println("enter your role :");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("please login");
				System.out.println();
				System.out.println("enter userid:");
				int user=sc.nextInt();
				System.out.println("enter password:");
				String pass=sc.next();
				String ad=UserManagement.validateUser(user, pass);
				if(ad!=null) {
					String rep1 = "y";
					while(rep1.equalsIgnoreCase("y") ) {
						System.out.println("Welcome to Administrator");
						System.out.println("------------------------");
						System.out.println();
						System.out.println("1. Create User");
						System.out.println("2. Delete User");
						System.out.println("3. Exit");
						System.out.println();
						System.out.print("Enter your option : ");
						int ch = sc.nextInt();
						if(ch == 1) {
							System.out.print("Userid : ");
							int uid1 = sc.nextInt();
							System.out.println("enter username :");
							String un1=sc.next();
							System.out.print("Password : ");
							String pw = sc.next();
							int opt = 0;
							String r = "";
							do {
								System.out.println("1.Librarian 2.Member");
								System.out.print("Enter your option for the role : ");
								opt = sc.nextInt();
								if(opt == 1) {
									r = "librarian";
								}
								if(opt == 2) {
									r = "member";
								}
								if(opt >= 5 || opt < 1) {
									System.out.println("Invalid Role");
									System.out.println("Select again");
								}
							}while(opt >= 5 || opt < 1);
							if(userManagement.createUser(uid1,un1, pw, r)) {
								System.out.println("User created successfully");
							}
							else {
								System.out.println("User creation failed");
							}
						}
						if(ch == 2) {
							System.out.print("Enter the userid to delete : ");
							int u = sc.nextInt();
							if(userManagement.deleteUser(u)) {
								System.out.println("deleted user");
							}
							else {
								System.out.println("Cannot find user");
							}
						}
						System.out.println();
						System.out.print("Do you want to continue (Y | N) ? ");
						rep1 = sc.next();
					}
				}	
				System.out.println("Exiting the admin");
				break;
			case 2:
				String rep2 = "y";
				while(rep2.equalsIgnoreCase("y") ){
					System.out.println("Welcome to librarian");
					System.out.println("------------------------");
					System.out.println();
					System.out.println("1.login");
					System.out.println();
					System.out.print("Enter your option : ");
					int ch = sc.nextInt();
					if(ch == 1) {
						System.out.println("enter userid :");
						int id=sc.nextInt();
						System.out.print("Enter the password :");
						String pw= sc.next();
						if(Lb.login(id, pw)) {
							System.out.println("login success");
							System.out.println();
							String ep="y";
							while(ep.equalsIgnoreCase("y")){
								System.out.println("1.addbooks");
								System.out.println("2.issuebook");
								System.out.println("3.returnbook");
								System.out.println();
								System.out.print("Enter your option : ");
								int c = sc.nextInt();
								if(c == 1) {
									if(Lb.add_book()) {
										System.out.println("books added successfully");
									}
									else {
										System.out.println("books adding failed");
									}

								}
								if(c == 2) {
									Connection conn = ConnectionFactory.getConnection();
									System.out.println("enter userid :");
									int id1=sc.nextInt();
									System.out.println("BOOKS AVAILABLE");
									String s="Select * from book";
									Statement st=conn.createStatement();
									ResultSet s1=st.executeQuery(s);
									while(s1.next()) {
										System.out.println(s1.getInt(1)+" "+s1.getString(2)+" "+s1.getInt(3));
									}
									System.out.print("Enter the book id :");
									int book_id = sc.nextInt();
									if(Lb.issue_Book(id1,book_id)) {
										System.out.println("book issued successfully");
									}
									else {
										System.out.println("book issue failed");
									}
								}
								if(c == 3) {
									System.out.print("Enter the book id :");
									int bid = sc.nextInt();
									if(Lb.return_Book(bid)) {
										System.out.println("book returned successfully");
									}
									else {
										System.out.println();
										System.out.println("book return failed");
									}
								}
								System.out.println();
								System.out.print("Do you want to continue (Y | N) ? ");
								ep= sc.next();
							}
						}
						else {
							System.out.println();
							System.out.println("login failed");
							System.out.println();
							System.out.println("ask admin to add you ! y|n");
							String sw=sc.next();
							if(sw.equalsIgnoreCase("y")) {
								System.out.println("enter userid :");
								int uid=sc.nextInt();
								System.out.println("enter username :");
								String un=sc.next();
								System.out.println("Enter password :");
								String pd= sc.next();
								System.out.println("enter role :");
								String rol=sc.next();
								if(mb.register(uid,un,pd, rol)) {
									System.out.println("registered");
									System.out.println("please login if you want to continue !");
								}
								else {
									System.out.println("register failed");
								}
							}
						}
					}
					System.out.println();
					System.out.print("Do you want to login again (Y | N) ? ");
					rep2 = sc.next();
				}
				System.out.println("Exiting the Librarian");
				break;
			case 3:
				String rep3 = "y";
				while(rep3.equalsIgnoreCase("y") ) {
					System.out.println("Welcome to Member");
					System.out.println("------------------------");
					System.out.println();
					System.out.println("1.Register");
					System.out.println("2.login");
					System.out.println();
					System.out.print("Enter your option : ");
					int ch = sc.nextInt();
					if(ch == 1) {
						System.out.println("enter userid :");
						int uid=sc.nextInt();
						System.out.println("enter username :");
						String un=sc.next();
						System.out.println("Enter password :");
						String pd= sc.next();
						System.out.println("enter role :");
						String rol=sc.next();
						if(mb.register(uid,un,pd, rol)) {
							System.out.println("registered");
							System.out.println("please login if you want to continue !");
						}
						else {
							System.out.println("register failed");
						}
					}
					if(ch == 2) {
						System.out.println("enter userid :");
						int id=sc.nextInt();
						System.out.print("Enter the password : ");
						String pw= sc.next();
						if(mb.login(id, pw)) {
							System.out.println("login success");
							System.out.println();
							String re="y";
							while(re.equalsIgnoreCase("y")) {
								System.out.println("1.issuebook");
								System.out.println("2.returnbook");
								int h=sc.nextInt();
								if(h == 1) {
									Connection con = ConnectionFactory.getConnection();
									String s="Select * from book";
									Statement st=con.createStatement();
									ResultSet s1=st.executeQuery(s);
									while(s1.next()) {
										System.out.println(s1.getInt(1)+" "+s1.getString(2)+" "+s1.getInt(3));
									}
									System.out.print("Enter the book id :");
									int book_id = sc.nextInt();
									if(mb.issuebook(id,book_id)) {
										System.out.println("book issued successfully");
									}
									else {
										System.out.println("book issue failed");
									}
								}
								if(h == 2) {
									System.out.print("Enter the book id :");
									int bid = sc.nextInt();
									if(mb.returnbook(bid)) {
										System.out.println("book returned successfully");
									}
									else {
										System.out.println("book return failed");
									}
								}
								System.out.println();
								System.out.print("Do you want to continue to issue or return books(Y | N) ? ");
								re= sc.next();
							}
						}
						else {
							System.out.println("login failed");
						}
					}
					System.out.println();
					System.out.print("Do you want to continue (Y | N) ? ");
					rep3 = sc.next();
				}
				System.out.println("exiting the member");
				break;
			default:
				break;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
