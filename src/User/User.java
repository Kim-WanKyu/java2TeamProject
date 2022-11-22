package User;

import java.util.HashMap;
import java.sql.*;
public class User {

	//아이디와 비밀번호를 트리맵에 넣음 로그인과 정보갱신등에 사용할 거임

	private String name;			//회원 이름
	private String id;				//학번/교번(=ID)
	private String password;		//비밀번호
	private int isAdmin;			//관리자여부
	private Boolean delay;			

	//대여한 책(들)
	private String[] borrowBook = new String[3];
	private Timestamp[] borrowDates = new Timestamp[3];//대여한 날짜(들)
	private String[] delayInfo = new String[3];		//대여한 책의 연체 정보(들)

	private HashMap<String,String>BooksandBorrowMap = new HashMap<String,String>();

	//User 생성자
	public User(){}
	
	//get 메소드들
	public String getName() { return this.name; }
	public String getID() { return this.id; }
	public String getPassword() { return this.password; }
	public int getIsAdmin() { return this.isAdmin; }
	
	public Timestamp[] getBorrowDates() { return this.borrowDates; }
	public String[] getBorrowBooks() { return this.borrowBook; }
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
	public void setBorrowDates(int date,Timestamp timestamp) { this.borrowDates[date] = timestamp; }

	//user 찾는 메서드
	
	//빌린 책 그리고 연체 날짜 저장메서드 
}
