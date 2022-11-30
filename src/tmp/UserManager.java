package tmp;
import java.sql.Connection;
import java.time.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;

import Book.Book;
import Book.BookManager;
public class UserManager {
	//UserManager 데이터 종류
	private static	Vector<User>list;	
	
	

	private static UserManager userInstance = new UserManager();
		public static UserManager getInstance() {
			System.out.print("인스턴스 반환");
			return userInstance;
		}
		//모든 책 가져오기

	
	//모든 유저 가져와 초기화 메서드
	//관리지가 사용할 예정
	public void setAllUser() throws SQLException{
		list = new Vector<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		String sql = "select *from student";
		try
		{
			conn = DBManager.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user = new User();
				user.setName(rs.getString("Name"));
				user.setID(rs.getString("hakbun"));
				user.setIsAdmin(rs.getInt("is_admin"));
				user.setPassword(rs.getString("password"));
				list.add(user);
				System.out.println(list);
				System.out.println(user.getID()+ " "+user.getName()+" "+user.getPassword()+" "+user.getBorrowDates());
				//반목문 안의 반복문으로 구현해야함!!
			}
			sql = "select *from borrowbooksanddates";
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				 user.setBorrowBooks(0,rs.getString("book1"));
				user.setBorrowBooks(1, rs.getString("book2"));
				user.setBorrowBooks(2, rs.getString("book3"));
				user.setBorrowDates(0,rs.getTimestamp("date1"));
				user.setBorrowDates(1, rs.getTimestamp("date2"));
				user.setBorrowDates(2, rs.getTimestamp("date3"));
				list.add(user);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close();
		}

	}
	//빌린 책 그리고 연체 날짜 저장 메서드
	//연체 여부 메서드
	
	//책 빌리는 메서드
	public void borrowBooks(User nowuser,Book borrowbook) {
		BookManager BOOKDB = new BookManager().getInstance();
		borrowbook =  BOOKDB.getlist().get(borrowbook.getId());
		System.out.println(borrowbook.getTotalCount());
		System.out.println(borrowbook.getTotalCount()>=borrowbook.getBorrowCount());
		System.out.println(borrowbook.getBorrowCount());
	
		
		
		Connection conn = null;
	
		String book1=null;
		String book2=null;
		String book3=null;
		java.sql.Date date1 = null;
		java.sql.Date date2 = null;
		java.sql.Date date3 = null;
		java.sql.Date delay_info = null;
		try {
		java.sql.Statement stmt;
		PreparedStatement pstmt = null;
		
		ResultSet result = null;
		System.out.println("책 빌리기 실행!!");
		String sql = "select * from borrowbooksanddates where hakbun = " +nowuser.getID() ;
		conn = DBManager.connect();	
		pstmt =conn.prepareStatement(sql);
		result = pstmt.executeQuery();
		java.util.Date dt = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dt.getTime());
			if(result.next()) {
				book1 = result.getString("book1");
				book2 = result.getString("book2");
				book3 = result.getString("book3");
				date1 = result.getDate("date1");
				date2 = result.getDate("date2");
				date3 = result.getDate("date3");
				delay_info = result.getDate("delay_info");
				System.out.println("여기까진 수행");
			}
			Calendar c = Calendar.getInstance();
			java.util.Date set_Date;
			if(date1!=null) {
				set_Date = new java.util.Date(date1.getTime());
				int diffDays = (int) ((dt.getTime()-set_Date.getTime()) / (24*60*60));
				System.out.println("날짜 차이"+diffDays);
			}
			if(date2!=null) {
				c.setTime(date2);
				c.add(Calendar.DATE, 7);
				date2 = new java.sql.Date(c.getTimeInMillis());
				if(date2.after(date))
					return;
						
			}
			if(date3!=null) {
				c.setTime(date3);
				c.add(Calendar.DATE, 7);
				date3 = new java.sql.Date(c.getTimeInMillis());
				if(date3.after(date))
					return;
			
			}
			
			System.out.println(delay_info);
			if(delay_info!=null && delay_info.after(date)) {
				System.out.println("책 연체 빌리기 불가!!");
				return;
			}
			else if(borrowbook.getTotalCount()==borrowbook.getBorrowCount())
			{
				
					
					System.out.println("책빌리기 불가 책 수량 없음!!");
					return;
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("데이터 베이스 연결 종료");
			DBManager.close();
		}
		
		
		try {
			conn = DBManager.connect();
			PreparedStatement pstmt = null;
			String sql = "update borrowbooksanddates set ";
			
			java.util.Date dt = new java.util.Date();
			java.sql.Date date = new java.sql.Date(dt.getTime());
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			
			if(book1 ==null) {
			
			sql +="book1 = ?, date1 = ? where(hakbun = ?)";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, borrowbook.getName());
			pstmt.setDate(2, date);//날짜 비교 연산 메서드
			
			pstmt.setString(3, nowuser.getID());
			pstmt.executeUpdate();
			sql = "update book_list set borrowcount = ? where(id = ?)";
			pstmt = conn.prepareStatement(sql);
			borrowbook.addCount();
			System.out.println(borrowbook.getBorrowCount());
			pstmt.setInt(1,borrowbook.getBorrowCount());
			pstmt.setString(2, borrowbook.getId());
			pstmt.executeUpdate();
		}
		else if(book2 == null) {
			sql +="book2 = ?, date2 = ? where(hakbun = ?)";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, borrowbook.getName());
			pstmt.setDate(2, date);
//			pstmt.setDate(3, delay_date);
			
			pstmt.setString(3,nowuser.getID());
			pstmt.executeUpdate();
			sql = "update book_list set borrowcount = ? where(id = ?)";
			pstmt = conn.prepareStatement(sql);
			 borrowbook.addCount();
			 System.out.println(borrowbook.getBorrowCount());
			 
			pstmt.setInt(1,borrowbook.getBorrowCount());
			pstmt.setString(2, borrowbook.getId());
			pstmt.executeUpdate();
		}
		
		else if(book3 == null) {
			sql +="book3 = ?, date3 = ? where(hakbun = ?)";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, borrowbook.getName());
			pstmt.setDate(2, date);
//			pstmt.setDate(3, delay_date);
			pstmt.setString(3,nowuser.getID());
			pstmt.executeUpdate();
			sql = "update book_list set borrowcount = ? where(id = ?)";
			pstmt = conn.prepareStatement(sql);
			 borrowbook.addCount();
			 
			pstmt.setInt(1,borrowbook.getBorrowCount());
			pstmt.setString(2, borrowbook.getId());
			pstmt.executeUpdate();

		}
		
		else
			{
				System.out.println("책 빌리기 불가");
				System.out.println("책 수량 없음");
				return;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
		
			
			DBManager.close();
		}

	}
	//책 반납 메서드
public void returnbook (User returnuser, Book returnbook) 
	{
	BookManager BOOKDB = new BookManager().getInstance();
	returnbook =  BOOKDB.getlist().get(returnbook.getId());
	Connection conn = null;
	String book1=null;
	String book2=null;
	String book3=null;
	String returndate;
	String bookreturn;
	
	java.sql.Date date1 = null;
	java.sql.Date date2 = null;	
	java.sql.Date date3 = null;
	java.sql.Date delay_info = null;
	
	java.util.Date dt = new java.util.Date();
	java.sql.Date date = new java.sql.Date(dt.getTime());
	java.sql.Date delay_date;
	Calendar c1 = Calendar.getInstance();
	Calendar c2 = Calendar.getInstance();
	try {
	java.sql.Statement stmt;
	PreparedStatement pstmt = null;
	
	ResultSet result = null;
	System.out.println("책 반납 정보가져오기!!");
	String sql = "select * from borrowbooksanddates where hakbun = " +returnuser.getID() ;
	conn = DBManager.connect();	
	pstmt =conn.prepareStatement(sql);
	result = pstmt.executeQuery();
	
		if(result.next()) {
			book1 = result.getString("book1");
			book2 = result.getString("book2");
			book3 = result.getString("book3");
			date1 = result.getDate("date1");
			date2 = result.getDate("date2");
			date3 = result.getDate("date3");
			delay_info = result.getDate("delay_info");
			System.out.println("여기까진 수행");
		}
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		DBManager.close();
		
	}
	try {
		System.out.println("책 반납 실행!!");
		System.out.println(returnbook.getName());
		java.sql.Statement stmt;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		if(book1!=null &&book1.equals(returnbook.getName())) 
		{
			returndate ="date1" ;
			bookreturn = "book1";

			
		}
		else if(book2!=null&&book2.equals(returnbook.getName()))
		{
			returndate  = "date2";
			bookreturn  = "book2";
		}
		else if(book3!=null&&book3.equals(returnbook.getName()))
		{
			returndate = "date3";
			bookreturn = "book3";
		}
		else {
			System.out.println("책이 읎어요");
			return;
		}
		java.util.Date set_Date;
		if(date1!=null) {
			set_Date = new java.util.Date(date1.getTime());
			int diffDays = (int) ((dt.getTime()-set_Date.getTime()) / (24*60*60));
			c1.setTime(date);
			c1.add(Calendar.DATE, diffDays+7);
			delay_info = new java.sql.Date(c1.getTimeInMillis());
			
		}	
		if(date2!=null) {
			set_Date = new java.util.Date(date2.getTime());
			int diffDays = (int) ((dt.getTime()-set_Date.getTime()) / (24*60*60));
			c1.setTime(date);
			c1.add(Calendar.DATE, diffDays+7);
			delay_info = new java.sql.Date(c1.getTimeInMillis());
		}
		if(date3!=null) {
			
			set_Date = new java.util.Date(date3.getTime());
			int diffDays = (int) ((dt.getTime()-set_Date.getTime()) / (24*60*60));
			c1.setTime(date);
			c1.add(Calendar.DATE, diffDays+7);
			delay_info = new java.sql.Date(c1.getTimeInMillis());
			
		}
		
		
		
		String sql = "update borrowbooksanddates set ";
		sql += bookreturn + "= ?,";
		sql += returndate +"= ?,";
		sql += "delay_info = ? where(hakbun = ?)";
		conn = DBManager.connect();	
		pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, null);
		pstmt.setDate(2, null);
		pstmt.setDate(3,delay_info);
		pstmt.setString(4,returnuser.getID());
		 pstmt.executeUpdate();
		
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	


	//유저 추가
	public void insertUser(User newuser) {
		Connection conn=null;
		java.sql.Statement stmt;
		ResultSet result;
		
		
		try {
			conn=DBManager.connect();
			System.out.println("데베 유저 추가");
			stmt = conn.createStatement();
			String hakbun = newuser.getID();
			String name  = newuser.getName();
			String password = newuser.getPassword();
			int isadmin = newuser.getIsAdmin();
			String sql =  " insert into student(hakbun, name, password, is_admin) VALUES('"+hakbun+"', '"+name+"' ,'"+password+"' ,'"+isadmin+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			 sql =  " insert into borrowbooksanddates(hakbun) VALUES('"+hakbun+"')";
			 stmt.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		DBManager.close();
	}
	
}
	//유저 삭제 메서드
	public void deleteUser(User deleteuser)  {
		
		Connection conn=null;
		java.sql.Statement stmt;
		ResultSet result;
		try {
		conn=DBManager.connect();
		stmt = conn.createStatement();
		String delete = "delete from student where hakbun = "+deleteuser.getID();
		stmt.executeUpdate(delete);
		System.out.println("데베 유저 삭제");
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		DBManager.close();
	}
	}
	
	//로그인도와주는 메서드
	
	public boolean Login(String getId, String getPass) {
		boolean isuser = false;
		System.out.println("유저 데베 찾기");
		for(User isUser : list) {
			if(getId.equals(isUser.getID())) {
				if(getPass.equals(isUser.getPassword())) {
				
				System.out.println("여기 값있음");
				return true;
				}
				else {
					System.out.println("여기 비밀번호가 없습니다");
					return false;
				}
				
			}	
			else {
				System.out.println("여기 학번이 없음");
				return false;
		}
		
		
		}
		return isuser;
	
	}
}
	