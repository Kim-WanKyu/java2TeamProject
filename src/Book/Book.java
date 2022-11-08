package Book;

public class Book {
	String name;		//도서 이름
	String author;		//저자명
	String publisher;	//출판사명
	String id;			//ID(ISBN)
	String category;	//분류(KDC기준 간략하게)
	int totalCount;		//총 권 수
	int borrowCount;	//빌린 권 수
	
	//Book 생성자
	Book(String name, String author, String publisher, String id
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
	String getName() { return this.name; }
	String getAuthor() { return this.author; }
	String getPublisher() { return this.publisher; }
	String getId() { return this.id; }
	String getCategory() { return this.category; }
	int getTotalCount() { return this.totalCount; }
	int getBorrowCount() { return this.borrowCount; }

	//set 메소드들
	void setName(String name) { this.name = name; }
	void setAuthor(String author) { this.author = author; }
	void setPublisher(String publisher) { this.publisher = publisher; }
	void setId(String id) { this.id = id; }
	void setCategory(String category) { this.category = category; }
	void setTotalCount(int totalCount) { this.totalCount = totalCount; }
	void setBorrowCount(int borrowCount) { this.borrowCount = borrowCount; }
	
}
