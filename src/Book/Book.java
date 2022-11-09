package Book;

public class Book {
	private String name;		//도서 이름
	private String author;		//저자명
	private String publisher;	//출판사명
	private String id;			//ID(ISBN)
	private String category;	//분류(KDC기준 간략하게)
	private int totalCount;		//총 권 수
	private int borrowCount;	//빌린 권 수
	
	//Book 생성자
	public Book(String name, String author, String publisher, String id
			, String category, int totalCount, int borrowCount) {
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.id = id;
		this.category = category;
		this.totalCount = totalCount;
		this.borrowCount = borrowCount;
	}
	
	//get 메소드들
	public String getName() { return this.name; }
	public String getAuthor() { return this.author; }
	public String getPublisher() { return this.publisher; }
	public String getId() { return this.id; }
	public String getCategory() { return this.category; }
	public int getTotalCount() { return this.totalCount; }
	public int getBorrowCount() { return this.borrowCount; }

	//set 메소드들
	public void setName(String name) { this.name = name; }
	public void setAuthor(String author) { this.author = author; }
	public void setPublisher(String publisher) { this.publisher = publisher; }
	public void setId(String id) { this.id = id; }
	public void setCategory(String category) { this.category = category; }
	public void setTotalCount(int totalCount) { this.totalCount = totalCount; }
	public void setBorrowCount(int borrowCount) { this.borrowCount = borrowCount; }
}
