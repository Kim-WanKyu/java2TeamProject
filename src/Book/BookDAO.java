package Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

public class BookDAO {
	private static BookDAO instance = new BookDAO();
	public static BookDAO getInstance() {
		return instance;
	}
	private  static TreeMap<String,Book> Booklist ; //전체 책 빌리고 책 목록 출력할때
	private LinkedList <Book> bookqueue = new LinkedList<Book>();
	
	//모든 책 가져오기
	public TreeMap<String,Book> getlist(){
		return Booklist;
	}

	
public   LinkedList<Book> findBook(String getClass, String word) {
	
	switch (getClass) {
	case "도서 명":
		
		for(Entry<String,Book> entry : Booklist.entrySet())
		{
			System.out.println("여기 실행");
			Book getBook = entry.getValue();
			if(getBook.getName().contains(word))
				bookqueue.offer(getBook);
		}
		
	
		return bookqueue;
		
		
	case "저자명":
		for(Entry<String,Book> entry : Booklist.entrySet())
		{
			System.out.println("여기 실행");
			Book getBook = entry.getValue();
			if(getBook.getAuthor().contains(word))
				bookqueue.offer(getBook);
		}
		return bookqueue;
		
		
		
	
		
	case "출판사":
		for(Entry<String,Book> entry : Booklist.entrySet())
		{
			
			Book getBook = entry.getValue();
			if(getBook.getPublisher().contains(word))
				bookqueue.offer(getBook);
		}
		
	
		return bookqueue;
		
	case "분류":
		for(Entry<String,Book> entry : Booklist.entrySet())
		{	
			Book getBook = entry.getValue();
			
			if(getBook.getCategory().contains(word))
				bookqueue.offer(getBook);
		}
		
		
	
		return bookqueue;
	
		
	}
	System.out.println("여긴 실행");
	return bookqueue;

}



	

//모든 책 품목 설정
public  void setAllBook() throws SQLException{
	Booklist = new TreeMap<String,Book>();//isbn 책 객체
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
			book.setId(rs.getString("id"));
			book.setName(rs.getString("name"));
			book.setPublisher(rs.getString("publisher"));
			book.setCategory(rs.getString("kdc"));
			book.setTotalCount(rs.getInt("totalcount"));
			book.setAuthor(rs.getString("author"));
			book.setBorrowCount(rs.getInt("borrowcount"));
			Booklist.put(book.getId(),book);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		JDBBOOK.close();
	}
	
}

//책 품목 추가
	public void insertBook(Book book) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//데베에 책을 추가
		String sql = "insert into booklist ('ID','서명','저자','출판사','KDC','권','대출건수') VALUES(?,?,?,?,?,?,?)";
		
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
			pstmt.executeUpdate();
			Booklist.put(book.getId(),book);
			
		}catch(Exception e){
			e.printStackTrace();//오류 출력 이 메소드를 호출하게 되면 예외 발생 당시의 호출 스택(Call stack)에 있던 메소드의 정보와 예외 결과를 화면에 출력한다.
		}finally {
			JDBBOOK.close();
		}
		
	}
	//책 삭제
	public void deleteBook(String id ) {
		
	}

}
