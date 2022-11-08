package User;

import java.util.ArrayList;

import java.time.LocalDate;

import Book.Book;	//Book패키지의 Book클래스 import


public class User {
	String name;			//회원 이름
	String id;				//학번/교번(=ID)
	String password;		//비밀번호
	Boolean isAdmin;		//관리자여부
	Book[] borrowBooks;		//대여한 책(들)
	LocalDate[] borrowDates;//대여한 날짜(들)
	String[] delayInfo;		//대여한 책의 연체 정보(들)
	
	//User 생성자
	User(String name, String id, String password, Boolean isAdmin
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
	String getName() { return this.name; }
	String getID() { return this.id; }
	String getPassword() { return this.password; }
	Boolean getIsAdmin() { return this.isAdmin; }
	Book[] getBorrowBooks() { return this.borrowBooks; }
	LocalDate[] getBorrowDates() { return this.borrowDates; }
	String[] getDelayInfo() { return this.delayInfo; }
	
	//set 메소드들
	void setName(String name) { this.name = name; }
	void setID(String id) { this.id = id; }
	void setPassword(String password) { this.password = password; }
	void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }
	void setBorrowBooks(Book[] borrowBooks) { this.borrowBooks = borrowBooks; }
	void setBorrowDates(LocalDate[] borrowDates) { this.borrowDates = borrowDates; }
	void setDelayInfo(String[] delayInfo) { this.delayInfo = delayInfo; }

}
