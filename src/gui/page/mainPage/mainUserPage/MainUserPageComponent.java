package gui.page.mainPage.mainUserPage;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import book.Book;
import book.BookManager;
import gui.page.mainPage.MainPageComponent;
import gui.page.startPage.StartPageComponent;
import gui.table.AllBookTable;
import gui.table.MyBookTable;
import gui.util.MessageBox;
import user.UserManager;

import java.time.LocalDate;

import util.DelayNoticeRunnable;
import util.Receipt;

public class MainUserPageComponent extends MainPageComponent {

	//mainAdminPage의 모든 텍스트필드를 담는 ArrayList변수
	private ArrayList<JTextField> mainUserTextComponents;
	public ArrayList<JTextField> getMainUserTextComponents() { return mainUserTextComponents; }

	private MainPageComponent mainPageComponent = new MainPageComponent(this.frame);
	
	private MyBookTable myBookTable = new MyBookTable();
	public MyBookTable getMyBookTable() { return myBookTable; }
	
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
	
	//사용자 화면 탭 0.전채도서 1. 내 도서
	private JTabbedPane userTab;
	
	private static JLabel delayNoticeLabel1 = new JLabel(" ");	//연체 알림 메세지 레이블
	private static JLabel delayNoticeLabel2 = new JLabel(" ");	//연체 알림 메세지 레이블
	
	//MainUserPageComponent생성자
	public MainUserPageComponent(JFrame frame)  {
		super(frame);
		userTab = mainPageComponent.getTabbedPane();		
		borrowBookButton.addActionListener(this);
		returnBookButton.addActionListener(this);
		
		myBookTable.initMyBookTable();
		InitTextField();
		setFieldFromAllBookTable();
		
		//테이블에 마우스 이벤트 처리 추가
		myBookTable.getMyBookTable().addMouseListener(new MouseAdapter() {
			Object[] obj = new Object[myBookTable.getMyBookTable().getColumnCount()];
			@Override
			//클릭 시 정보 가져오기
			public void mouseClicked(MouseEvent me) {
				for(int i=0; i<myBookTable.getMyBookTable().getColumnCount();i++)
				{	
					System.out.println(myBookTable.getMyBookTable().getSelectedRow());
					obj[i] = myBookTable.getMyBookTable().getValueAt(myBookTable.getMyBookTable().getSelectedRow(), i);
				}
				try {
					bookNameTextFields[1].setText(obj[0].toString());
					bookAuthorTextFields[1].setText(obj[1].toString());
					bookPublisherTextFields[1].setText(obj[2].toString());
					if(obj[3]!=null) {
						bookCategoryTextFields[1].setText(obj[3].toString());
					}
					else {
						bookCategoryTextFields[1].setText("");
					}
					bookIdTextFields[1].setText(obj[5].toString());
					borrowDateTextField.setText(obj[6].toString());
					returnDateTextField.setText(obj[7].toString());

					java.util.Date now = new java.util.Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date returnDate = format.parse(obj[7].toString());
					boolean isDelay =now.after(returnDate);
					String isDelayString = isDelay ? "True":"False";
					isDelayTextField.setText(isDelayString);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		//연체알림을 위한 runnable 객체 생성 및 스레드 실행
		DelayNoticeRunnable delayNoticeRunnable = new DelayNoticeRunnable();
		Thread thread = new Thread(delayNoticeRunnable);
		thread.start();
		
		//탭의 상태가 변할 시 이벤트 작동 (탭 이동 시 발동)
		userTab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				eraseTextComponent(mainUserTextComponents);	//텍스트필드 지움
				MainPageComponent.setTabIndex(userTab.getSelectedIndex());	//탭 인덱스를 누른 탭번호으로 설정
				AllBookTable.getAllBookTable().setModel(AllBookTable.getAllBookTableModel());	//전체도서 테이블 초기화
				myBookTable.getMyBookTable().setModel(myBookTable.getMyBookTableModel());		//내 도서 테이블 초기화
			}
		});
		
		//mainUserTextComponents에 텍스트필드 넣어줌		
		mainUserTextComponents = new ArrayList<JTextField>();
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
		mainUserTextComponents.add(bookAvailableStockTextField);
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

	public static JLabel getDelayNoticeLabel1() { return delayNoticeLabel1; }
	public static JLabel getDelayNoticeLabel2() { return delayNoticeLabel2; }

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
		AllBookTable.getAllBookTable().addMouseListener(new MouseAdapter() {
			@Override
			//클릭 시 정보 가져오기
			public void mouseClicked(MouseEvent me) {
				System.out.println("선택된 행"+AllBookTable.getAllBookTable().getSelectedRow());

				Object[] obj = new Object[AllBookTable.getAllBookTable().getColumnCount()];
				for(int i=0; i<AllBookTable.getAllBookTable().getColumnCount();i++)
						obj[i] = AllBookTable.getAllBookTable().getValueAt(AllBookTable.getAllBookTable().getSelectedRow(), i);
					bookNameTextFields[0].setText(obj[0].toString());
					bookAuthorTextFields[0].setText(obj[1].toString());
					bookPublisherTextFields[0].setText(obj[2].toString());
					try {
						bookCategoryTextFields[0].setText(obj[3].toString());
					}catch(Exception e){
						bookIdTextFields[0].setText("");
					}
					bookIdTextFields[0].setText(obj[5].toString());
				try {
					int stockCount = Integer.parseInt(obj[6].toString())-Integer.parseInt(obj[7].toString());
					bookAvailableStockTextField.setText(""+stockCount);
				}
				catch(Exception e) {
					bookAvailableStockTextField.setText("0");
				}
			}
		});
	}
		
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

		case "종료하기":
			super.onClickExitButton();
			break;
			
		case "대여하기":
			if(!bookIdTextFields[0].getText().equals(""))
				onClickBorrowBookButton();
			else
				MessageBox.printWarningMessageBox("선택된 도서가 없습니다.");
			break;
			
		case "반납하기":
			if(!bookIdTextFields[1].getText().equals(""))
				onClickReturnBookButton();
			else
				MessageBox.printWarningMessageBox("선택된 도서가 없습니다.");
			break;
		}
	}
	
	//대여하기 버튼 작동 메소드
	public void onClickBorrowBookButton() {
		Book borrowBooks = BookManager.getInstance().getlist().get(bookIdTextFields[0].getText());
		 
		String getMessage = UserManager.getInstance().borrowBooks(StartPageComponent.getUser(), borrowBooks);
		switch(getMessage) {
			case "Success":
				AllBookTable.getAllBookTable().setValueAt(Integer.toString(borrowBooks.getBorrowCount()), AllBookTable.getAllBookTable().getSelectedRow(),7 );
				AllBookTable.getAllBookTable().updateUI();
				
				LocalDate dateAndtime = LocalDate.now();
				
				String inputStr[] = new String[8];
				inputStr[0] = borrowBooks.getName();
				inputStr[1] = borrowBooks.getAuthor();
				inputStr[2] = borrowBooks.getPublisher();
				inputStr[3] = book.CategorizeKDC.getCategoryname(borrowBooks.getCategory());
				inputStr[4] = borrowBooks.getCategory();
				inputStr[5] = borrowBooks.getId();
				inputStr[6] = dateAndtime.toString();
				inputStr[7] = (dateAndtime.plusDays(7).toString());
				myBookTable.getMyBookTableModel().addRow(inputStr);

			    MessageBox.printInfoMessageBox("도서 대여:" + borrowBooks.getName());
			    eraseTextComponent(mainUserTextComponents);
			    //영수증 출력 메소드 실행
				Receipt receipt = new Receipt(StartPageComponent.getUser(), borrowBooks);
				receipt.printReceipt();
				break;
			case "bookOver":
			    MessageBox.printWarningMessageBox("빌릴 수 있는 책 개수 초과");
			    break;
			case "bookStockOver":
				MessageBox.printWarningMessageBox("책 수량 없음!!");
				 break;
			case "delay":
				MessageBox.printWarningMessageBox("책 빌리기 불가!! 책 연체 됨!!");
				break;
			case "bookOverlap":
				MessageBox.printWarningMessageBox("동일 도서 중복 대여 불가!!");
				break;
		}
	}
	
	//반납하기 버튼 작동 메소드
	public void onClickReturnBookButton() {
		Book returnBook = new Book();
		returnBook = BookManager.getInstance().getlist().get(bookIdTextFields[1].getText());
		UserManager.getInstance().returnBook(StartPageComponent.getUser(), returnBook);
		int tableRow = 0;
		for(int i=0; i<AllBookTable.getAllBookTable().getRowCount(); i++) {
			if(AllBookTable.getAllBookTable().getValueAt(tableRow, 5) == returnBook.getId())
				break;
			tableRow++;
		}
		
		AllBookTable.getAllBookTable().setValueAt(returnBook.getBorrowCount(), tableRow,7 );
		AllBookTable.getAllBookTable().updateUI();
		myBookTable.getMyBookTableModel().removeRow(myBookTable.getMyBookTable().getSelectedRow());

	    MessageBox.printInfoMessageBox("도서 반납:" + returnBook.getName());
	    eraseTextComponent(mainUserTextComponents);
	}
}