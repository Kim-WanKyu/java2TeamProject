package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

import db.DBManager;


public class BookManager {
	private static BookManager instance = new BookManager();
	public static BookManager getInstance() {
		return instance;
	}
	private  static TreeMap<String,Book> Booklist ; //전체 책 빌리고 책 목록 출력할때
	private LinkedList <Book> bookqueue = new LinkedList<Book>();
	
	//모든 책 가져오기
	public TreeMap<String,Book> getlist(){
		return Booklist;
	}

//	public String[][] returnBookData(){
//	ArrayList<ArrayList<String>> allBookDataString = new ArrayList<ArrayList<String>>();
//	String allBookData[][];
//		for(Entry<String, book.Book> entry : Booklist.entrySet()) {
//			System.out.println("52번째 줄 실행");
//			ArrayList<String> a1 = new ArrayList<String>();
//			book.Book  getValue= entry.getValue();
//			a1.add(getValue.getName());
//			a1.add(getValue.getAuthor());
//			a1.add(getValue.getPublisher());
//			a1.add(getValue.getCategory());
//			a1.add(getValue.getCategory());
//			a1.add(String.valueOf(getValue.getTotalCount()));
//			a1.add(String.valueOf(getValue.getBorrowCount()));
//			allBookDataString.add(a1);
//		}
//			System.out.println(allBookDataString.get(0));
//			allBookData = allBookDataString.toArray(new String[allBookDataString.size()][7]);
//		for(int i=0;i<allBookData.length;i++) {
//				System.out.println(allBookData[i][0]);
//			System.out.println("출력 실행");			}
//			return allBookData;
//	} 
	public LinkedList<Book> findBook(String getClass, String word) {
		
		switch (getClass) {
		case "도서명":
			for(Entry<String,Book> entry : Booklist.entrySet())
			{
				System.out.println("여기 실행");
				Book getBook = entry.getValue();
				if(getBook.getName().contains(word))
					bookqueue.offer(getBook);
			}
			break;
			
		case "저자명":
			for(Entry<String,Book> entry : Booklist.entrySet())
			{
				System.out.println("여기 실행");
				Book getBook = entry.getValue();
				if(getBook.getAuthor().contains(word))
					bookqueue.offer(getBook);
			}
			break;
			
		case "출판사":
			for(Entry<String,Book> entry : Booklist.entrySet())
			{
				Book getBook = entry.getValue();
				if(getBook.getPublisher().contains(word))
					bookqueue.offer(getBook);
			}
			break;
			
		case "분류":
			
		
		String category =CategorizeKDC.getKDCCode(word);
		System.out.println(category);
		String categoryDiff;	
		for(Entry<String,Book> entry : Booklist.entrySet())
			{	
				Book getBook = entry.getValue();
				if(getBook.getCategory()!=null&&getBook.getCategory().length()!=0) {
				
					categoryDiff =getBook.getCategory().substring(0, 2)+'0';
				System.out.println("if 문 실행"+" 첫 번째 카테고리 숫자");
				if(categoryDiff.equals(category)) {
					System.out.println("여기 있어용 값이");
					bookqueue.offer(getBook);
					}
				}
				
			}
			break;
		}
		System.out.println("여긴 실행");
		return bookqueue;
	}
	
	
	//책 카테고리 분류
	

	
		//분류 카테고리명
		
	

	
	//모든 책 품목 설정
	public  void setAllBook() throws SQLException{
		System.out.println("책 초기화 실행");
		Booklist = new TreeMap<String,Book>();//isbn 책 객체
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String price = null;
		String sql = "select *from book_list";
		try
		{
			conn = DBManager.connect();
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
			DBManager.close();
		}
		
	}

	//책 품목 추가
	public void insertBook(Book book) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//데베에 책을 추가
		String sql = "insert into book_list (id, name, author, publisher, kdc, totalcount, borrowcount) VALUES(?,?,?,?,?,?,?)";
		
		try {
			conn = DBManager.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getId());
			pstmt.setString(2, book.getName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPublisher());
			pstmt.setString(5, book.getCategory());
			pstmt.setInt(6, book.getTotalCount());
			pstmt.setInt(7, book.getBorrowCount());
			pstmt.executeUpdate();
			Booklist.put(book.getId(),book);
			
		}catch(Exception e){
			//오류 출력 이 메소드를 호출하게 되면 예외 발생 당시의 호출 스택(Call stack)에 있던 메소드의 정보와 예외 결과를 화면에 출력한다.
			e.printStackTrace();
		}finally {
			DBManager.close();
		}
		
	}
	//책 삭제
	public void deleteBook(String id ) {
		if(Booklist.get(id).getBorrowCount()!=0) {
			System.out.println("책을 모두 회수한 다음 책을 삭제해주세요");
			return;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql ="delete from book_list (where id = ?)";
		try {
			conn = DBManager.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			Booklist.remove(id);
		}catch(Exception e) {
			
		}finally {
			DBManager.close();
		}
	}

}

