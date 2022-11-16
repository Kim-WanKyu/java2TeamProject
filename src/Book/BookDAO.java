package Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class BookDAO {
	private static BookDAO instance = new BookDAO();
	public static BookDAO getInstance() {
		return instance;
	}
	private  static Vector<Book> Booklist;
	public static void main(String []args) throws SQLException {
		Book books = new Book();
		BookDAO.setAllBook();
		
	}
	//모든 책 가져오기
public Vector<Book> getBook() throws SQLException{
	return Booklist;
}






//모든 책 품목 설정
public static Vector<Book> setAllBook() throws SQLException{
	Vector<Book>list = new Vector<>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	String price = null;
	String sql = "select *from book_list";
	try
	{
		conn = JDBBOOK.connect();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getString("ID"));
			book.setName(rs.getString("서명"));
			book.setPublisher(rs.getString("출판사"));
			book.setCategory(rs.getString("KDC"));
			book.setTotalCount(rs.getInt("권"));
			book.setBorrowCount(rs.getInt("대출건수"));
			Booklist.add(book);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		JDBBOOK.close();
	}
	return Booklist;
}

//책 품목 추가
	public boolean insertBook(Book book) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//데베에 책을 추가
		String sql = "insert into booklist ('ID','서명','저자','출판사','KDC','권','대출건수') VALUES(?,?,?,?,?,?,?)";
		boolean result =false;
		try {
			conn = JDBBOOK.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getId());
			pstmt.setString(2,book.getName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPublisher());
			pstmt.setString(5,book.getCategory());
			pstmt.setInt(6, book.getTotalCount());
			pstmt.setInt(7, book.getBorrowCount());
			int r = pstmt.executeUpdate();
			System.out.println("return result = "+r);//처리된 row 개수
			if(r>0) {
				System.out.println("데베가 삽입됨");				return true;
			}
		}catch(Exception e){
			e.printStackTrace();//오류 출력 이 메소드를 호출하게 되면 예외 발생 당시의 호출 스택(Call stack)에 있던 메소드의 정보와 예외 결과를 화면에 출력한다.
		}finally {
			JDBBOOK.close();
		}
		return result;
	}

}
