package book;

import java.sql.*;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

import db.DBManager;
import gui.util.MessageBox;

public class BookManager {
	//BookManager변수
	private static BookManager instance = new BookManager();
	public static BookManager getInstance() {
		return instance;
	}
	
	//책 전체 리스트 담을 맵<bookId, Book>
	private  static TreeMap<String,Book> bookList ; 
	public TreeMap<String,Book> getlist() { return bookList; }
	
	//모든 도서를 담는 변수인 bookList 초기화 메소드
	public void setAllBook() throws SQLException{
		System.out.println("책 초기화 실행");
		try {
			String sql = "select *from book_list";

			Connection conn = DBManager.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			bookList = new TreeMap<String,Book>();	// <bookId, Book 객체>
			
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getString("id"));
				book.setName(rs.getString("name"));
				book.setPublisher(rs.getString("publisher"));
				book.setCategory(rs.getString("kdc"));	
				book.setTotalCount(rs.getInt("totalcount"));
				book.setAuthor(rs.getString("author"));
				book.setBorrowCount(rs.getInt("borrowcount"));
				
				bookList.put(book.getId(),book);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close();
		}
	}
	
	//도서 등록(추가) 메소드
	public void insertBook(Book book) {
		//ID중복 시 등록x
		if(bookList.get(book.getId()) != null) {
			MessageBox.printErrorMessageBox("도서 ID 중복");
			return;
		}
		
		//DB에 도서 추가
		try {
			String sql = "insert into book_list (id, name, author, publisher, kdc, totalcount, borrowcount) VALUES(?,?,?,?,?,?,?)";
			
			Connection conn = DBManager.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getId());
			pstmt.setString(2, book.getName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPublisher());
			pstmt.setString(5, book.getCategory());
			pstmt.setInt(6, book.getTotalCount());
			pstmt.setInt(7, book.getBorrowCount());
			pstmt.executeUpdate();
			
			bookList.put(book.getId(),book);
		}catch(Exception e){
			//오류 출력 이 메소드를 호출하게 되면 예외 발생 당시의 호출 스택(Call stack)에 있던 메소드의 정보와 예외 결과를 화면에 출력한다.
			e.printStackTrace();
		}finally {
			DBManager.close();
		}
	}
	
	//도서 수정 메소드
	public void editBook(String name, String author, String publisher, String kdc, int totalCount, String bookID) {
		//DB 도서 수정
		try {
			String sql = "update book_list set name = ?, author = ?, publisher = ?, kdc = ?, totalcount = ? where id = ?";

			Connection conn = DBManager.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, author);
			pstmt.setString(3, publisher);
			pstmt.setString(4, kdc);
			pstmt.setInt(5, totalCount);
			pstmt.setString(6, bookID);
			pstmt.executeUpdate();
			System.out.println("데베 수정 완료");
			
			//도서 정보 갱신
			Book book = bookList.get(bookID);
			book.setName(name);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setCategory(kdc);
			book.setTotalCount(totalCount);
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close();
		}
	}
	
	//도서 삭제 메소드
	public boolean deleteBook(String id ) {
		//대여수량이 남아있는 경우 삭제X
		if(bookList.get(id).getBorrowCount()!=0) {
			MessageBox.printErrorMessageBox("도서를 모두 회수한 후 \n삭제가 가능합니다.");
			System.out.println("책을 모두 회수한 다음 책을 삭제해주세요");
			return false;
		}

		//DB 도서 삭제
		try {
			String sql ="delete from book_list where id = ?";
			
			Connection conn = DBManager.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			bookList.remove(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close();
		}
		return true;
	}


	//도서 검색 메소드
	public Vector<Book> findBook(String getCategory, String word) {
		Vector <Book> searchedBooksVector = new Vector<Book>();		
		
		switch (getCategory) {
		case "도서명":
			for(Entry<String,Book> entry : bookList.entrySet())
			{
				Book getBook = entry.getValue();
				if(getBook.getName().contains(word))
					searchedBooksVector.add(getBook);
			}
			break;
			
		case "저자명":
			for(Entry<String,Book> entry : bookList.entrySet())
			{
				System.out.println("여기 저자 실행");
				Book getBook = entry.getValue();
				if(getBook.getAuthor().contains(word))
					searchedBooksVector.add(getBook);
			}
			break;
			
		case "출판사":
			for(Entry<String,Book> entry : bookList.entrySet())
			{
				Book getBook = entry.getValue();
				if(getBook.getPublisher().contains(word))
					searchedBooksVector.add(getBook);
			}
			break;
			
		case "분류":
			String categoryName;	
			for(Entry<String,Book> entry : bookList.entrySet())
			{	
				Book getBook = entry.getValue();
				if(getBook.getCategory()!=null && getBook.getCategory().length()!=0) {
					categoryName = CategorizeKDC.getCategoryname(getBook.getCategory());
					if(categoryName!=null && categoryName.contains(word))
						searchedBooksVector.add(getBook);
				}
			}
			break;
	
		default:
			MessageBox.printWarningMessageBox("검색분류가 올바르지 않습니다.");
			break;
		}
		return searchedBooksVector;
	}
}