package gui.table;

import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import book.Book;
import book.BookManager;
import gui.page.startPage.StartPageComponent;
import user.User;
import user.UserManager;

//myBookTable 내도서 테이블
public class MyBookTable {

	//myBookTable의 컬럼명
	private final String[] myBookColumnName = {"도서명","저자명","출판사","분류","KDC","BookID","대여일자","반납기한"};
	
	//myBookTable의 데이터
	private Object defaultMyBookData[][];
	
	//myBookTable의 테이블모델
	private DefaultTableModel myBookTableModel;
	public DefaultTableModel getMyBookTableModel() { return myBookTableModel; }
	
	//myBookTable테이블
	private NonEditableTable myBookTable; //
	public NonEditableTable getMyBookTable() { return myBookTable; }
	
	//myBookTable 생성자
	public MyBookTable() {
		initMyBookTable();
	}
	
	//myBookTable 초기화 메소드
	public void initMyBookTable() {
		myBookTableModel = new DefaultTableModel(defaultMyBookData,myBookColumnName);
		myBookTable = new NonEditableTable(myBookTableModel);
		myBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		User myUser = new User();
		Book myBook = new Book();
		myUser = UserManager.getInstance().findUser(StartPageComponent.getUser().getID());	//로그인한 유저
		Calendar c = Calendar.getInstance();
		java.util.Date afterDate;
		for(int i=0;i<3;i++) {
			if(myUser.getBorrowBooks()[i]!=null) {
				myBook = BookManager.getInstance().getlist().get(myUser.getBorrowBooks()[i]);
				Object myBookData[] = new Object[8];

				myBookData[0] = myBook.getName();
				myBookData[1] = myBook.getAuthor();
				myBookData[2] = myBook.getPublisher();
				myBookData[3] = book.CategorizeKDC.getCategoryname(myBook.getCategory());
				myBookData[4] = myBook.getCategory();
				myBookData[5] = myBook.getId();
				myBookData[6] = myUser.getBorrowDates()[i];
				c.setTime(myUser.getBorrowDates()[i]);
				c.add(Calendar.DATE, 7);
				afterDate =new java.sql.Date(c.getTimeInMillis());
				myBookData[7] = afterDate;
				myBookTableModel.addRow(myBookData);
			}	
		}
		
		//테이블 열 위치 변경 불가
		myBookTable.getTableHeader().setReorderingAllowed(false);
	}
}
