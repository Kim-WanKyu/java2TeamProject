package User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import com.mysql.cj.xdevapi.Statement;

import Book.JDBBOOK;
public class USERDAO {
	//USERDAO 데이터 종류
	private static	Vector<User>list;	
	private	User user;
	
	public static void main(String args[]) throws SQLException {
		
		USERDAO user_instance =	USERDAO.getInstance();
		User user = new User();
		user_instance.insertUser(user);
		user_instance.deleteUser(user);
		JDBBOOK jdb = new JDBBOOK();
	}
	
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
				user = new User();
				user.setName(rs.getString("Name"));
				user.setID(rs.getInt("id"));
				user.setuserdata(user.getID(),user.getName());
				user.setIsAdmin(rs.getBoolean("isAdmin"));
				user.setBorrowBooks(rs.getString("Borrowbook"));
				System.out.println("유저 세팅 유저디에이오");
				//반목문 안의 반복문으로 구현해야함!!
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBBOOK.close();
		}

	}
	//빌린 책 그리고 연체 날짜 저장 메서드
public HashMap<String,String> getborrowbooksanddata(String id)throws SQLException{
	Connection conn = null;
	PreparedStatement ptsmt = null;
	ResultSet rs = null;
	String BooksandBorrow = null;
	String sql = "select BorrowandBooks from student where id=? ";
	User user;
	HashMap<String,String> BooksandBorrowMap = new HashMap<String,String>();
		try {
			conn=JDBBOOK.connect();
			ptsmt=conn.prepareStatement(sql);
			ptsmt.setString(1, id);
			rs = ptsmt.executeQuery();
			if(rs.next()) {
				BooksandBorrow = rs.getString("Borrowbooks");
				System.out.println(BooksandBorrow);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBBOOK.close();
		}
		return BooksandBorrowMap;
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
			//stmt.executeUpdate("insert into student values('정동주', 202001826, '정동주', '1','정동주','정동주')");
			//stmt.executeUpdate("insert into student values('김완규', 201901769, '정동주', '1','정동주','정동주')");
			
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
		stmt.executeUpdate("delete from student where hakbun = 202001826");
		System.out.println("데베 유저 삭제");
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		JDBBOOK.close();
	}
	}
	//책 빌리는 메서드
	public void borrowBooks() {

	}
}
