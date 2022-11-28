package user;

import java.util.HashMap;
import java.sql.*;
public class User {

	//아이디와 비밀번호를 트리맵에 넣음 로그인과 정보갱신등에 사용할 거임

	private String name;			//회원 이름
	private String id;				//학번/교번(=ID)
	private String password;		//비밀번호
	private boolean isAdmin;			//관리자여부
	private Date delay_date;			

	//대여한 책(들)
	private String[] borrowBook = new String[3];
	private java.sql.Date[] borrowDates = new java.sql.Date[3];//대여한 날짜(들)
		//대여한 책의 연체 정보(들)

	private HashMap<String,String>BooksandBorrowMap = new HashMap<String,String>();

	


	//User 생성자
	public User(){}
	

	//get 메소드들
	public String getName() { return this.name; }
	public String getID() { return this.id; }
	public String getPassword() { return this.password; }
	public boolean getIsAdmin() { return this.isAdmin; }
	
	public java.sql.Date[] getBorrowDates() { return this.borrowDates; }
	public String[] getBorrowBooks() { return this.borrowBook; }
	public Date getIsDelay() {return this.delay_date;}
	//set 메소드들
	public void setName(String name) { this.name = name; }
	public void setID(String id) { this.id = id; }
	public void setPassword(String password) { this.password = password; }
	public void setIsAdmin(boolean isAdmin2) { this.isAdmin = isAdmin2; }
	public void setDelay(java.sql.Date delay_date) {this.delay_date = delay_date;}
	public void setBorrowBooks(int count, String book) 
	{
		borrowBook[count] =book;
	}
	
	public void setBorrowDates(int date,java.sql.Date date2) { this.borrowDates[date] = date2; 
}


	


	
	}


	

