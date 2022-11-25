package Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import DB.DBManager;

import java.util.TreeMap;
import java.util.Vector;

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
			
			
		String category =getKDCCode(word);
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
		static private String[][] categoryName =
		{
			{//000 총류
				"총류",							//000
				"총류_도서학, 서지학",				//010
				"총류_문헌정보학",					//020
				"총류_백과사전",					//030
				"총류_일반 논문집",					//040
				"총류_일반 연속간행물",				//050
				"총류_일반 학회, 단체, 협회, 기관",		//060
				"총류_신문, 언론, 저널리즘",			//070
				"총류_일반 전집, 총서",				//080
				"총류_X" },						//090

			{//100 철학
				"철학",
				"철학_형이상학",
				"철학_X",
				"철학_철학의 체계",
				"철학_경학",
				"철학_아시아(동양) 철학, 사상",
				"철학_서양철학",
				"철학_논리학",
				"철학_심리학",
				"철학_윤리학, 도덕철학" },

			{//200 종교
				"종교",
				"종교_비교종교학",
				"종교_불교",
				"종교_기독교",
				"종교_도교",
				"종교_천도교",
				"종교_신도",
				"종교_바라문교, 인도교",
				"종교_회교(이슬람교)",
				"종교_기타 제종교" },

			{//300 사회과학
				"사회과학",
				"사회과학_통계학",
				"사회과학_경제학",
				"사회과학_사회학, 사회문제",
				"사회과학_정치학",
				"사회과학_행정학",
				"사회과학_법학",
				"사회과학_교육학",
				"사회과학_풍속, 민속학",
				"사회과학_국방, 군사학" },

			{//400 순수과학
				"순수과학",
				"순수과학_수학",
				"순수과학_물리학",
				"순수과학_화학",
				"순수과학_천문학",
				"순수과학_지학",
				"순수과학_광물학",
				"순수과학_생물과학",
				"순수과학_식물학",
				"순수과학_동물학" },

			{//500 기술과학
				"기술과학",
				"기술과학_의학",
				"기술과학_농업, 농학",
				"기술과학_공학, 공업일반",
				"기술과학_건축공학",
				"기술과학_기계공학",
				"기술과학_전기공학, 전자공학",
				"기술과학_화학공학",
				"기술과학_제조업",
				"기술과학_가정학 및 가정생활" },

			{//600 예술
				"예술",
				"예술_건축술",
				"예술_조각",
				"예술_공예, 장식미술",
				"예술_서예",
				"예술_회화, 도화",
				"예술_사진술",
				"예술_음악",
				"예술_연극",
				"예술_오락, 운동" },

			{//700 언어
				"언어",
				"언어_한국어",
				"언어_중국어",
				"언어_일본어",
				"언어_영어",
				"언어_독일어",
				"언어_프랑스어",
				"언어_스페인어",
				"언어_이탈리아어",
				"언어_기타 제어" },

			{//800 문학
				"문학",
				"문학_한국문학",
				"문학_중국문학",
				"문학_일본문학",
				"문학_영미문학",
				"문학_독일문학",
				"문학_프랑스문학",
				"문학_스페인문학",
				"문학_이탈리아문학",
				"문학_기타 제문학" },

			{//900 역사
				"역사",
				"역사_아시아(아세아)",
				"역사_유럽(구라파)",
				"역사_아프리카",
				"역사_북아메리카(북미)",
				"역사_남아메리카(남미)",
				"역사_오세아니아(대양주)",
				"역사_양극지방",
				"역사_지리",
				"역사_전기" }

	};//String[][] categoryName 끝 지점


	//KDC코드로 분류명 찾아내 리턴
	public  String getCategoryname(String kdc) {
		String category = categoryName[kdc.charAt(0)][kdc.charAt(1)];
		return category;
	}

	//분류명으로 KDC코드 찾아내 리턴
	public String getKDCCode(String category) {
		String code = null;		//리턴할 KDC코드 문자열
		boolean isFind = false;	//찾았는지 체크하는 boolean변수

		for(int i=0; i<categoryName.length; i++) {
			for(int j=0; j<categoryName[i].length; j++) {
				if(category.equals(categoryName[i][j]) == true) {
					code = "" + i + j+ "0";
					isFind = true;
					break;
				}
			}
			if(isFind) { break; }
		}

		return code;
	}
	

	
	//모든 책 품목 설정
	public  void setAllBook() throws SQLException{
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
		String sql = "insert into booklist ('ID','서명','저자','출판사','KDC','권','대출건수') VALUES(?,?,?,?,?,?,?)";
		
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

