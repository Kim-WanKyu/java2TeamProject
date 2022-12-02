package gui.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import book.Book;
import book.BookManager;
import gui.page.startPage.StartPageComponent;
import user.User;
import user.UserManager;

public class MyBookTable {
	//myBookTable 전체도서 테이블 static
		//myBookTable의 컬럼명
		private final String[] myBookColumnName = {"도서명","저자명","출판사","분류","KDC","BookID","대여일자","반납기한"};
		
		//allBookTable의 데이터 //TODO DB에서 가져왔던 내 도서 벡터로 초기화
		private Object myBookData[];
		private Object defaultMyBookData[][];
		//allBookTable의 테이블모델
		private DefaultTableModel myBookTableModel;
		public DefaultTableModel getMyBookTableModel() { return myBookTableModel; }
		
		//allBookTable테이블
		private JTable myBookTable; //
		//allBookTable테이블 리턴하는 메소드
		public JTable getMyBookTable() { return myBookTable; }
		
		public void initMyBookTable() {
			//TODO make myBookTable은 로그인 성공 시 마다 설정
			myBookTableModel = new DefaultTableModel(defaultMyBookData,myBookColumnName);
			myBookTable = new JTable(myBookTableModel);
			myBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			User myUser = new User(); 
			Book myBook = new Book();
			myUser = UserManager.getInstance().findUser(StartPageComponent.getUser().getID());
			Calendar c = Calendar.getInstance();
			java.util.Date afterDate;
			myBookData = new Object[8];
			for(int i=0;i<3;i++) {
				if(myUser.getBorrowBooks()[i]!=null) {
					myBook = BookManager.getInstance().getlist().get(myUser.getBorrowBooks()[i]);
					myBookData[0] = myBook.getName();
					myBookData[1] = myBook.getAuthor();
					myBookData[2] = myBook.getPublisher();
					myBookData[3] = myBook.getCategory();
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
			//테이블 내용 수정 불가 처리
//			myBookTable.setModel(new DefaultTableModel(defaultMyBookData, myBookColumnName) {
//				public boolean isCellEditable(int row, int column) { return false; }
//			} );
//			
			
			
		}
}
