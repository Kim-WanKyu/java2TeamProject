package Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import User.User;
public class USERDAO {
	public static void main(String args[]) throws SQLException {
		USERDAO user = new USERDAO();
		user.insertUser();
	}
		private static USERDAO user_instance = new USERDAO();
		public static USERDAO getInstance() {
			return user_instance;
		}
		//모든 책 가져오기

	
	//모든 유저 가져와 초기화 메서드
	public Vector<User> getAllUser() throws SQLException{
		Vector<User>list = new Vector<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String price = null;
		String sql = "select *from User";
		User user;
		try
		{
			conn = JDBBOOK.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setName(rs.getString("Name"));
				user.setID(rs.getString("id"));
				user.setuserdata(user.getID(),user.getName());
				user.setIsAdmin(rs.getBoolean("isAdmin"));
				user.setBorrowBooksandDates(rs.getString("BorrowbooksandBooks"));
				
				//반목문 안의 반복문으로 구현해야함!!
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBBOOK.close();
		}
		return list;
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
				BooksandBorrow = rs.getString("BorrowbooksAndBorrowdates");
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
	public boolean insertUser() {
		Connection conn =null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO student('Name','id','password','isAdmin','Borrowbooks','delayInfo','Studentcol')VALUES(?,?,?,?,?,?,?)";
		boolean result = false;
		try {
		 conn=JDBBOOK.connect();
		 pstmt = conn.prepareStatement(sql);
		 pstmt.setString(1, "정동주");
		 pstmt.setString(2, "202001826");
		 pstmt.setString(3,"jdj2001@@");
		 pstmt.setInt(4, 1);
		 pstmt.setString(5,"오만과 편견 11:59");
		 pstmt.setString(6,"sadasdasdas");
		 pstmt.setString(7,"asdasdad");
		 System.out.println("삽입 성공");
//		 int r = pstmt.executeUpdate();
// 		System.out.println("return result = "+r);
//		 if(r>0) {
//			 result = true;
//		 }
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		JDBBOOK.close();
	}
	return result;
}
}
