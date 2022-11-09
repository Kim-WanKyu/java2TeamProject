package User;

import java.util.ArrayList;
import java.util.TreeMap;
import java.time.LocalDate;

import Book.Book;	//Book패키지의 Book클래스 import
//트리 맵 구성!!

public class User {
	String name;			//회원 이름
	String id;				//학번/교번(=ID)
	String password;		//비밀번호
	Boolean isAdmin;		//관리자여부
	String  borrowBooksandDates;		//대여한 책(들)그리고 연체날짜
	String  borrowDates;//대여한 날짜(들)
	String  delayInfo;
	//아이디와 비밀번호를 트리맵에 넣음 로그인과 정보갱신등에 사용할 거임
	static private TreeMap<String,String> userdata = new TreeMap<String,String>();
	//연체 여부도 트리맵으로 구성!!
	private TreeMap<String,String> userbook = new TreeMap<String,String>();
	//대여한 책의 연체 정보(들)
	//user 해쉬 맵써야 함
	//jsonArrray 사용
	//User 생성자
	
	public User( String name,  String id, String password, boolean isAdmin, String borrowBooksandDates, String borrowDates, String delayInfo) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.isAdmin = isAdmin;
		this.borrowBooksandDates = borrowBooksandDates;
		this.borrowDates = borrowDates;
		this.delayInfo = delayInfo;
	}
	public User(){}
	
	//get 메소드들
	public	 String getName() { return this.name; }
	public	 String getID() { return this.id; }
	public	 String getPassword() { return this.password; }
	public	Boolean getIsAdmin() { return this.isAdmin; }
	String getBorrowBooks() { return this.borrowBooksandDates; }
	String getBorrowDates() { return this.borrowDates; }
    String getDelayInfo() { return this.delayInfo; }
	
	//set 메소드들
	public void setName(String name) { this.name = name; }
	public void setID(String id) { this.id = id; }
	public void setPassword(String password) { this.password = password; }
	public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }
	public void setBorrowBooks(String borrowBooks) { this.borrowBooksandDates = borrowBooks; }
	public void setBorrowDates(String borrowDates) { this.borrowDates = borrowDates; }
	public void setDelayInfo(String delayInfo) { this.delayInfo = delayInfo; }
	public void setuserdata(String id,String pass) {userdata.put(id,pass);}
	public void setUserbook(String book,String bookborrowdates) {userbook.put(book, bookborrowdates);}
}
