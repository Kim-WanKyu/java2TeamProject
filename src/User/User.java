package User;

import java.util.ArrayList;

import java.time.LocalDate;

import Book.Book;	//Book패키지의 Book클래스 import


public class User {
	private String name;			//회원 이름
	private String id;				//학번/교번(=ID)
	private String password;		//비밀번호
	private Boolean isAdmin;		//관리자여부
	private Book[] borrowBooks;		//대여한 책(들)
	private LocalDate[] borrowDates;//대여한 날짜(들)
	private String[] delayInfo;		//대여한 책의 연체 정보(들)
	
	//User 생성자
	public User(String name, String id, String password, Boolean isAdmin
			, Book[] borrowBooks, LocalDate[] borrowDates, String[] delayInfo) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.isAdmin = isAdmin;
		this.borrowBooks = borrowBooks;
		this.borrowDates = borrowDates;
		this.delayInfo = delayInfo;
	}
	
	//get 메소드들
	public String getName() { return this.name; }
	public String getID() { return this.id; }
	public String getPassword() { return this.password; }
	public Boolean getIsAdmin() { return this.isAdmin; }
	public Book[] getBorrowBooks() { return this.borrowBooks; }
	public LocalDate[] getBorrowDates() { return this.borrowDates; }
	public String[] getDelayInfo() { return this.delayInfo; }
	
	//set 메소드들
	public void setName(String name) { this.name = name; }
	public void setID(String id) { this.id = id; }
	public void setPassword(String password) { this.password = password; }
	public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }
	public void setBorrowBooks(Book[] borrowBooks) { this.borrowBooks = borrowBooks; }
	public void setBorrowDates(LocalDate[] borrowDates) { this.borrowDates = borrowDates; }
	public void setDelayInfo(String[] delayInfo) { this.delayInfo = delayInfo; }

}
