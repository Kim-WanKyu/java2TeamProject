package User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;
import java.time.LocalDate;
import Book.Book;
import Book.JDBBOOK;

import java.time.*;
import java.sql.*;
public class User {

	
	//아이디와 비밀번호를 트리맵에 넣음 로그인과 정보갱신등에 사용할 거임

	private String name;			//회원 이름
	private String id;				//학번/교번(=ID)
	private String password;		//비밀번호
	private int isAdmin;//관리자여부
	private Boolean delay;

			//대여한 책(들)
	private String[] borrowBook = new String[3];
	private Time[] borrowDates = new Time[3];//대여한 날짜(들)
	private String[] delayInfo = new String[3];		//대여한 책의 연체 정보(들)

	
	private HashMap<String,String>BooksandBorrowMap = new HashMap<String,String>();
	//연체 여부도 트리맵으로 구성!!
//	private HashMap<String,String> userbook = new HashMap<String,String>();
	//대여한 책의 연체 정보(들)
	//user 해쉬 맵써야 함
	//jsonArrray 사용
	//User 생성자

//	public User( String name,  String id, String password, boolean isAdmin, String borrowBooksandDates, String borrowDates, String delayInfo) {
//		this.name = name;
//		this.id = id;
//		this.password = password;
//		this.isAdmin = isAdmin;
//		this.borrowBooksandDates = borrowBooksandDates;
//		this.borrowDates = borrowDates;
//		this.delayInfo = delayInfo;
//	}
	public User(){}
	
	
//	public void setUserbook(String book,String bookborrowdates) {userbook.put(book, bookborrowdates);}
	//userdata 트리맵 구성

//	public User(String name, String id, String password, Boolean isAdmin
//			, Book[] borrowBooks, LocalDate[] borrowDates, String[] delayInfo) {
//		this.name = name;
//		this.id = id;
//		this.password = password;
//		this.isAdmin = isAdmin;
//		this.borrowBooks = borrowBooks;
//		this.borrowDates = borrowDates;
//		this.delayInfo = delayInfo;
//	}
	
	//get 메소드들
	public String getName() { return this.name; }
	public String getID() { return this.id; }
	public String getPassword() { return this.password; }
	public int getIsAdmin() { return this.isAdmin; }
	
	public Time[] getBorrowDates() { return this.borrowDates; }
	public String[] getDelayInfo() { return this.delayInfo; }
	public Boolean getIsDelay() {return this.delay;}
	//set 메소드들
	public void setName(String name) { this.name = name; }
	public void setID(String id) { this.id = id; }
	public void setPassword(String password) { this.password = password; }
	public void setIsAdmin(int b) { this.isAdmin = b; }
	public void setDelay(Boolean Delay) {this.delay = Delay;}
	public void setBorrowBooks(int count, String book) 
	{
		borrowBook[count] =book;
		
	}
	public void setBorrowDates(int date,Time time) { this.borrowDates[date] = time; }
	public void setDelayInfo(String[] delayInfo) { this.delayInfo = delayInfo; }


	

	//user 찾는 메서드
	
	//빌린 책 그리고 연체 날짜 저장메서드 

	
	
}
