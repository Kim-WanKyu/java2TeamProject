package User;
import java.sql.Connection;
import java.time.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import com.mysql.cj.xdevapi.Statement;

import Book.JDBBOOK;
public class USERDAO {
	//USERDAO 데이터 종류
	private static	Vector<User>list;	
	
	
//	public static void main(String args[]) throws SQLException {
//		
//		USERDAO user_instance =	USERDAO.getInstance();
//		User user = new User();
//		user_instance.insertUser(user);
//		user_instance.setAllUser();
//		JDBBOOK jdb = new JDBBOOK();
//	}
//	
	private static USERDAO user_instance = new USERDAO();
		public static USERDAO getInstance() {
			System.out.print("인스턴스 반환");
			return user_instance;
		}
		//모든 책 가져오기

	
	//모든 유저 가져와 초기화 메서드
	//관리지가 사용할 예정
	public void setAllUser() throws SQLException{
		list = new Vector<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String price = null;
		String sql = "select *from student";
		try
		{
			conn = JDBBOOK.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user = new User();
				user.setName(rs.getString("Name"));
				user.setID(rs.getString("hakbun"));
				user.setIsAdmin(rs.getInt("isAdmin"));
				user.setPassword(rs.getString("password"));
				list.add(user);
				System.out.println(list);
				System.out.println(user.getID()+ " "+user.getName()+" "+user.getPassword()+" "+user.getBorrowDates());
				//반목문 안의 반복문으로 구현해야함!!
			}
			sql = "select *from borrowbooksanddates";
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Timestamp accessDate;
			while(rs.next()) {
				User user = new User();
				 user.setBorrowBooks(0,rs.getString("book1"));
				user.setBorrowBooks(1, rs.getString("book2"));
				user.setBorrowBooks(2, rs.getString("book3"));
				user.setBorrowDates(0,rs.getTime("date1"));
				user.setBorrowDates(1, rs.getTime("date2"));
				user.setBorrowDates(2, rs.getTime("date3"));
				list.add(user);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBBOOK.close();
		}

	}
	//빌린 책 그리고 연체 날짜 저장 메서드
	//연체 여부 메서드
	public boolean isDelay(User delayuser){
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDateTime dt = LocalDateTime.now();
		for(String localdate :delayuser.getDelayInfo()) {
			
		}
		return false;
	}
	//책 빌리는 메서드
	public void borrowBooks(User nowuser) {
		if(nowuser.getIsDelay()) {
			System.out.println("책 연체!!");
		}
		else if(nowuser.getBorrowDates().length==3) {
			System.out.println("빌릴 수 있는 책 초과");
		}
		else
		{
			//책을 빌릴 수 있게 함
		}
	}
	
	
	//유저 추가
	public void insertUser(User newuser) {
		Connection conn=null;
		java.sql.Statement stmt;
		ResultSet result;
		
		
		try {
			conn=JDBBOOK.connect();
			System.out.println("데베 유저 추가");
			stmt = conn.createStatement();
			String hakbun = newuser.getID();
			String name  = newuser.getName();
			String password = newuser.getPassword();
			int isadmin = newuser.getIsAdmin();
			String sql =  " insert into student(hakbun, name, password, is_admin) VALUES('"+hakbun+"', '"+name+"' ,'"+password+"' ,'"+isadmin+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			sql = "insert into borrowbooksanddates(hakbun) VALUES('"+hakbun+"')";
			stmt.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		JDBBOOK.close();
	}
	
}
	//유저 삭제 메서드
	public void deleteUser(User deleteuser)  {
		
		Connection conn=null;
		java.sql.Statement stmt;
		ResultSet result;
		try {
		conn=JDBBOOK.connect();
		stmt = conn.createStatement();
		String delete = "delete from student where hakbun";
		stmt.executeUpdate(delete);
		System.out.println("데베 유저 삭제");
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		JDBBOOK.close();
	}
	}
	
	//로그인도와주는 메서드
	private boolean isuser=false;
	public boolean Login(String getId, String getPass) {
		 System.out.println("유저 데베 찾기");
		for(User isUser : list) {
			if(getId.equals(isUser.getID())) {
				if(getPass.equals(isUser.getPassword())) {
				isuser = true;
				System.out.println("여기 값있음");
				break;
				}
				else {
					System.out.println("여기 비밀번호가 없습니다");
					break;
				}
					
			}	
			else
				System.out.println("여기 학번이 없음");
				isuser = false;
				break;
		}
		return isuser;
		
		}
	}

