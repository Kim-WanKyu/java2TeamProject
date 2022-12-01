package gui.page.mainPage.mainUserPage;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import book.Book;
import book.BookManager;
import gui.page.mainPage.MainPageComponent;
import gui.page.startPage.StartPageComponent;
import gui.util.MessageBox;
import user.User;
import user.UserManager;

import java.time.LocalDate;

public class MainUserPageComponent extends MainPageComponent {

	//mainAdminPage의 모든 텍스트필드를 담는 ArrayList변수
	private ArrayList<JTextField> mainUserTextComponents;
	public ArrayList<JTextField> getMainUserTextComponents() { return mainUserTextComponents; }

	private MainPageComponent mainPageComponent = new MainPageComponent(this.frame);
	
	private JTabbedPane userTab;
	
	//myBookTable 전체도서 테이블 static
	//myBookTable의 컬럼명
	private final String[] myBookColumnName = {"도서명","저자명","출판사","분류","KDC","BookID","대여일자","반납기한"};
	//allBookTable의 데이터 //TODO DB에서 가져왔던 내 도서 벡터로 초기화
	private Object myBookData[];
	private Object defaultMyBookData[][];
	//allBookTable의 테이블모델
	private DefaultTableModel myBookTableModel ;
	//allBookTable테이블
	private JTable myBookTable; //

	//allBookTable테이블 리턴하는 메소드
	public JTable getMyBookTable() { return myBookTable; }
	
	////////////
	
	//컴포넌트들
	//전체목록 연체확인 탭에 추가되는 컴포넌트 (단, 전체목록인 경우 0번 / 내도서의 경우 1번)
	private JTextField[] bookNameTextFields = new JTextField[2];		//도서명 텍스트필드
	private JTextField[] bookAuthorTextFields = new JTextField[2];		//저자명 텍스트필드
	private JTextField[] bookPublisherTextFields = new JTextField[2];	//출판사 텍스트필드
	private JTextField[] bookCategoryTextFields = new JTextField[2];	//분류 텍스트필드
	private JTextField[] bookIdTextFields = new JTextField[2];			//id 텍스트필드
	//연체확인 탭에만 추가되는 컴포넌트
	private JTextField borrowDateTextField = new JTextField(10);	//대여일자 텍스트필드
	private JTextField returnDateTextField = new JTextField(10);	//반납일자 텍스트필드
	private JTextField isDelayTextField = new JTextField(10);		//연체여부 텍스트필드
	//전체목록 탭에만 추가되는 컴포넌트
	private JTextField bookAvailableStockTextField = new JTextField(10);//재고 텍스트필드

	//사용자 사용 버튼
	private JButton borrowBookButton = new JButton("대여하기");	//대여하기 버튼
	private JButton returnBookButton = new JButton("반납하기");	//반납하기 버튼
	
	private JLabel delayNoticeLabel = new JLabel("");	//연체 알림 메세지 레이블
	
	
	//MainAdminPageComponent생성자
	public MainUserPageComponent(JFrame frame)  {
		super(frame);
		userTab = mainPageComponent.getTabbedPane();		
		borrowBookButton.addActionListener(this);
		returnBookButton.addActionListener(this);
		
		InitMyBookTable();
		InitTextField();
		setFieldFromAllBookTable();
		
		
		//탭의 상태가 변할 시 이벤트 작동 (탭 이동 시 발동)
		userTab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				eraseTextComponent(mainUserTextComponents);
			}
		});
		
		//mainUserTextComponents에 텍스트필드 넣어줌
		
		mainUserTextComponents = new ArrayList<JTextField>();
		
		mainUserTextComponents.add(bookAvailableStockTextField);
		for(int i=0;i<2;i++) {
			mainUserTextComponents.add(bookNameTextFields[i]);
			mainUserTextComponents.add(bookAuthorTextFields[i]);
			mainUserTextComponents.add(bookPublisherTextFields[i]);
			mainUserTextComponents.add(bookCategoryTextFields[i]);
			mainUserTextComponents.add(bookIdTextFields[i]);
		}
		mainUserTextComponents.add(borrowDateTextField);
		mainUserTextComponents.add(returnDateTextField);
		mainUserTextComponents.add(isDelayTextField);
		mainUserTextComponents.add(bookAvailableStockTextField);
	}
	
	
	
	public void InitMyBookTable() {
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
//		myBookTable.setModel(new DefaultTableModel(defaultMyBookData, myBookColumnName) {
//			public boolean isCellEditable(int row, int column) { return false; }
//		} );
//		
		
		//마우스 이벤트 처리 추가
		myBookTable.addMouseListener(new MouseAdapter() {
			Object[] str = new Object[myBookTable.getColumnCount()];
			@Override
			//클릭 시 정보 가져오기
			public void mousePressed(MouseEvent me) {
				for(int i=0; i<myBookTable.getColumnCount();i++)
				{	
					System.out.println(myBookTable.getSelectedRow());
					str[i] = myBookTable.getValueAt(myBookTable.getSelectedRow(), i);
				}
				try {
					bookNameTextFields[1].setText(str[0].toString());
					bookAuthorTextFields[1].setText(str[1].toString());
					bookPublisherTextFields[1].setText(str[2].toString());
					if(str[3].toString()!=null) {
						bookCategoryTextFields[1].setText(str[3].toString());
					}//KDC나 분류둥 하나 없음.
					else {
						bookCategoryTextFields[1].setText("");
					}
					bookIdTextFields[1].setText(str[5].toString());
					borrowDateTextField.setText(str[6].toString());
					returnDateTextField.setText(str[7].toString());
					java.util.Date now = new java.util.Date();
					boolean isDelay = LocalDate.now().isAfter((LocalDate)str[7]);
					String isDelayString = isDelay ? "True":"False";
					isDelayTextField.setText(isDelayString);
				}catch(Exception e) {
					e.getStackTrace();
				}
				
			}
		});
	}


	
	public void InitTextField(){
		//각 텍스트 배열의 할당 및 초기화
		for(int i=0; i<2 ;i++) {
			bookNameTextFields[i] = new JTextField(10);
			bookAuthorTextFields[i] = new JTextField(10);
			bookPublisherTextFields[i] = new JTextField(10);
			bookCategoryTextFields[i] = new JTextField(10);
			bookIdTextFields[i] = new JTextField(10);
		}

		//텍스트 필드 수정 불가 처리
		for(int i=0;i<2;i++) {
			bookNameTextFields[i].setEditable(false);
			bookAuthorTextFields[i].setEditable(false);
			bookPublisherTextFields[i].setEditable(false);
			bookCategoryTextFields[i].setEditable(false);
			bookIdTextFields[i].setEditable(false);
		}
		borrowDateTextField.setEditable(false);
		returnDateTextField.setEditable(false);
		isDelayTextField.setEditable(false);
		bookAvailableStockTextField.setEditable(false);
	}
	public void setFieldFromAllBookTable() {
		//마우스 이벤트 처리 추가
		getAllBookTable().addMouseListener(new MouseAdapter() {
			Object[] str = new Object[getAllBookTable().getColumnCount()];
			@Override
			//클릭 시 정보 가져오기
			public void mousePressed(MouseEvent me) {
				if(getAllBookTable().getSelectedRow() == -1)
					return;
				System.out.println("선택된 행"+getAllBookTable().getSelectedRow());
				for(int i=0; i<getAllBookTable().getColumnCount();i++)
						str[i] = getAllBookTable().getValueAt(getAllBookTable().getSelectedRow(), i);				
					bookNameTextFields[0].setText(str[0].toString());
					bookAuthorTextFields[0].setText(str[1].toString());
					bookPublisherTextFields[0].setText(str[2].toString());
					try {
						bookCategoryTextFields[0].setText(str[3].toString());
					}catch(Exception e){
						bookIdTextFields[0].setText("");
					}
					bookIdTextFields[0].setText(str[5].toString());
				try {
					int stockCount = Integer.parseInt(str[6].toString())-Integer.parseInt(str[7].toString());
					bookAvailableStockTextField.setText(""+stockCount);
				}
				catch(Exception e) {
					bookAvailableStockTextField.setText("0");
				}
			}
		});
	}
	//get 메소드들
	public JTabbedPane getUserTab() { return userTab; }
	
	public JButton getBorrowBookButton() { return borrowBookButton; }
	public JButton getReturnBookButton() { return returnBookButton; }
	
	public JTextField[] getBookNameTextFields() { return bookNameTextFields; }
	public JTextField[] getBookAuthorTextFields() { return bookAuthorTextFields; }
	public JTextField[] getBookPublisherTextFields() { return bookPublisherTextFields; }
	public JTextField[] getBookCategoryTextFields() { return bookCategoryTextFields; }
	public JTextField[] getBookIdTextFields() { return bookIdTextFields; }
	
	public JTextField getBorrowDateTextField() { return borrowDateTextField; }
	public JTextField getReturnDateTextField() { return returnDateTextField; }
	public JTextField getIsDelayTextField() { return isDelayTextField; }
	
	public JTextField getBookAvailableStockTextField() { return bookAvailableStockTextField; }

	//get메소드
	public JLabel getDelayNoticeLabel() { return delayNoticeLabel; }
	
	//버튼 클릭 시 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "검색":
			super.onClickSearchButton();
			break;
			
		case "변경/탈퇴":
			super.onClickEditSignoutButton();
			break;
			
		case "로그아웃":
			
			super.onClickLogoutButton();
			break;
				// TODO Auto-generated catch block
		
			
		case "종료하기":
			super.onClickExitButton();
			break;
			
		case "대여하기":
			onClickBorrowBookButton();
			//대여
			break;
			
		case "반납하기":
			onClickReturnBookButton();
			//반납
			break;
		
	}
	}
	
	//대여하기 버튼 작동 메소드 TODO
	public void onClickBorrowBookButton() {
		MessageBox.printInfoMessageBox("대여하기");
		Book borrowBooks = new Book();
		borrowBooks = BookManager.getInstance().getlist().get(bookIdTextFields[0].getText());
		 
		String getMessage = UserManager.getInstance().borrowBooks(StartPageComponent.getUser(), borrowBooks);
		
		switch(getMessage) {
			case "Success":
				getAllBookTable().setValueAt(Integer.toString(borrowBooks.getBorrowCount()), getAllBookTable().getSelectedRow(),7 );
				getAllBookTable().updateUI();
				LocalDate dateAndtime = LocalDate.now();
				String inputStr[] = new String[8];
				inputStr[0] = borrowBooks.getName();
				inputStr[1] = borrowBooks.getAuthor();
				inputStr[2] = borrowBooks.getPublisher();
				inputStr[3] = borrowBooks.getCategory();//TODO
				inputStr[4] = borrowBooks.getCategory();
				inputStr[5] = borrowBooks.getId();
				inputStr[6] = dateAndtime.toString();
				inputStr[7] = (dateAndtime.plusDays(7).toString());
				myBookTableModel.addRow(inputStr);
				break;
			case "bookOver":
			    MessageBox.printWarningMessageBox("빌릴 수 있는 책 개수 초과");
			    break;
			case "bookStockOver":
				MessageBox.printWarningMessageBox("책 수량 없음!!");
				 break;
			case "delay":
				MessageBox.printWarningMessageBox("책 빌리기 불가!! 책 연체 됨!!");
		}
	}
	//반납하기 버튼 작동 메소드 TODO
	public void onClickReturnBookButton() {
		MessageBox.printInfoMessageBox("반납하기");
		Book returnBooks = new Book();
		returnBooks = BookManager.getInstance().getlist().get(bookIdTextFields[1].getText());
		UserManager.getInstance().returnbook(StartPageComponent.getUser(), returnBooks);
		getAllBookTable().setValueAt(Integer.toString(returnBooks.getBorrowCount()), getAllBookTable().getSelectedRow(),7 );
		getAllBookTable().updateUI();
		myBookTableModel.removeRow(getMyBookTable().getSelectedRow());
	}
}

/*
 * addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e){
			System.out.println("aaa" + e.getSource());
		}
	}
 */