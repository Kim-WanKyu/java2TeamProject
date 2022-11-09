package Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import User.User;
public class USERDAO {
	public class UserDAO {
		private static USERDAO user_instance = new USERDAO();
		public static USERDAO getInstance() {
			return user_instance;
		}
		//모든 책 가져오기
	public Vector<String> getUser() throws SQLException{
		Vector<User> dbUserlist = getAllUser();
		Vector<String> Userlist = new Vector<String>();
		for(User user : dbUserlist) {
			Userlist.add(user.getName());
		}
		return Userlist;
	}
	//모든 책 품목 가져오기
	public Vector<User> getAllUser() throws SQLException{
		Vector<User>list = new Vector<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String price = null;
		String sql = "select *from User";
		try
		{
			conn = JDBBOOK.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("Name"));
				user.setID(rs.getString("id"));
				user.setuserdata(user.getID(),user.getName());
				user.setIsAdmin(rs.getBoolean("isAdmin"));
				user.setBorrowBooks(rs.getString("BorrowbooksandBooks"));
				
				//반목문 안의 반복문으로 구현해야함!!
				user.setUserbook(rs.getString("borrowbooks"), rs.getString("borrowdates"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBBOOK.close();
		}
		return list;
	}
	//연체 리스트 뽑기
	public void PrintLoanlist() {
		User user = new User();
		
	}
	
	//책 품목 추가

}
}