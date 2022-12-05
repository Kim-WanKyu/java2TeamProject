package user;

import java.sql.*;
public class User {

	private String name;			//회원 이름
	private String id;				//학번/교번(=ID)
	private String password;		//비밀번호
	private boolean isAdmin;		//관리자여부
	private Date delayDate;			//대여 가능 일자
	private String[] borrowBooks = new String[3];		//대여한 책(들)
	private Date[] borrowDates = new java.sql.Date[3];	//대여한 날짜(들)
	
	//get 메소드들
	public String getName() { return this.name; }
	public String getID() { return this.id; }
	public String getPassword() { return this.password; }
	public boolean getIsAdmin() { return this.isAdmin; }
	public Date[] getBorrowDates() { return this.borrowDates; }
	public String[] getBorrowBooks() { return this.borrowBooks; }
	public Date getDelayDate() {return this.delayDate;}
	
	//set 메소드들
	public void setName(String name) { this.name = name; }
	public void setID(String id) { this.id = id; }
	public void setPassword(String password) { this.password = password; }
	public void setIsAdmin(boolean isAdmin2) { this.isAdmin = isAdmin2; }
	public void setDelay(java.sql.Date delay_date) {this.delayDate = delay_date;}
	public void setBorrowBooks(int count, String book) { borrowBooks[count] =book; }
	public void setBorrowDates(int date,Date date2) { this.borrowDates[date] = date2; }
}