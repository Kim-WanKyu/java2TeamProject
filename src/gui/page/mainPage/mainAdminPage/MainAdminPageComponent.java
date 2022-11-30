package gui.page.mainPage.mainAdminPage;

import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import gui.page.mainPage.MainPageComponent;
import gui.page.optionPage.mainOption.book.editBookPage.EditBookPage;
import gui.page.optionPage.mainOption.book.insertBookPage.InsertBookPage;
import gui.util.MessageBox;

public class MainAdminPageComponent extends MainPageComponent {

	private MainPageComponent mainPageComponent = new MainPageComponent(this.frame);

	//mainAdminPage의 모든 텍스트필드를 담는 ArrayList변수
	private ArrayList<JTextField> mainAdminTextComponents;
	
	private JTabbedPane adminTab;
	
	//컴포넌트들
	//전체목록 연체확인 탭에 추가되는 컴포넌트 (단, 전체목록인 경우 3번 / 연체확인의 경우 0~2번)
	private JTextField[] bookNameTextFields = new JTextField[4];		//도서명 텍스트필드
	private JTextField[] bookAuthorTextFields = new JTextField[4];		//저자명 텍스트필드
	private JTextField[] bookPublisherTextFields = new JTextField[4];	//출판사 텍스트필드
	private JTextField[] bookCategoryTextFields = new JTextField[4];	//분류 텍스트필드
	private JTextField[] bookIdTextFields = new JTextField[4];			//id 텍스트필드
	//연체확인 탭에만 추가되는 컴포넌트
	private JTextField[] borrowDateTextFields = new JTextField[3];		//대여일자 텍스트필드
	private JTextField[] returnDateTextFields = new JTextField[3];		//반납일자 텍스트필드
	private JTextField[] isDelayTextFields = new JTextField[3];			//연체여부 텍스트필드
	//전체목록 탭에만 추가되는 컴포넌트
	private JTextField bookAvailableStockTextField = new JTextField(10);//재고 텍스트필드
		
	//관리자 사용 버튼
	private JButton insertBookButton = new JButton("등록");	//등록 버튼
	private JButton editBookButton = new JButton("수정");		//수정 버튼
	private JButton deleteBookButton = new JButton("삭제");	//삭제 버튼
	
	//MainAdminPageComponent생성자
	public MainAdminPageComponent(JFrame frame) {
		super(frame);
		
		adminTab = mainPageComponent.getTabbedPane();
				
		insertBookButton.addActionListener(this);
		editBookButton.addActionListener(this);
		deleteBookButton.addActionListener(this);
		
		InitAllBookTable();
		InitAllUserTable();
		InitTextField();
		setFieldFromAllBookTable();
		
		//탭의 상태가 변할 시 이벤트 작동 (탭 이동 시 발동)
		adminTab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				eraseTextComponent(mainAdminTextComponents);
			}
		});
		
		//mainAdminTextComponents에 텍스트필드 넣어줌
		mainAdminTextComponents = new ArrayList<JTextField>();
		mainAdminTextComponents.add(bookAvailableStockTextField);
		for(int i=0;i<4;i++) {
			mainAdminTextComponents.add(bookNameTextFields[i]);
			mainAdminTextComponents.add(bookAuthorTextFields[i]);
			mainAdminTextComponents.add(bookPublisherTextFields[i]);
			mainAdminTextComponents.add(bookCategoryTextFields[i]);
			mainAdminTextComponents.add(bookIdTextFields[i]);
		}
		for(int i=0;i<3;i++) {
			mainAdminTextComponents.add(borrowDateTextFields[i]);
			mainAdminTextComponents.add(returnDateTextFields[i]);
			mainAdminTextComponents.add(isDelayTextFields[i]);				
		}
	
	}
	
	public void setFieldFromAllBookTable() {
		//마우스 이벤트 처리 추가
		getAllBookTable().addMouseListener(new MouseAdapter() {
			Object[] str = new Object[getAllBookTable().getColumnCount()];
			@Override
			//클릭 시 정보 가져오기
			public void mousePressed(MouseEvent me) {
				for(int i=0; i<getAllBookTable().getColumnCount();i++)
					str[i] =  getAllBookTable().getValueAt(getAllBookTable().getSelectedRow(), i);
				
				bookNameTextFields[3].setText(str[0].toString());
				bookAuthorTextFields[3].setText(str[1].toString());
				bookPublisherTextFields[3].setText(str[2].toString());
				try {
					bookCategoryTextFields[3].setText(str[3].toString());
				} catch(Exception e) {
					bookCategoryTextFields[3].setText("");
				}
				bookIdTextFields[3].setText(str[5].toString());
				try {
					int stockCount = Integer.parseInt(str[6].toString())-Integer.parseInt(str[7].toString());
					bookAvailableStockTextField.setText(""+stockCount);
				} catch(Exception e) {
					bookAvailableStockTextField.setText("0");
				}
			}
		});
	}
	
	public void InitAllUserTable() {
		//TODO make Table from DataBase 처음 프로그램 시작될 때 설정
		MainPageComponent.getAllUserTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//테이블 열 위치 변경 불가
		MainPageComponent.getAllUserTable().getTableHeader().setReorderingAllowed(false);
		//테이블 내용 수정 불가 처리
		MainPageComponent.getAllUserTable().setModel(new DefaultTableModel (MainPageComponent.getAllUserData(), MainPageComponent.getAllUserColumnName()) {
			public boolean isCellEditable(int row, int column) { return false; }
		} );
	}
	
	public void InitTextField() {
		//각 텍스트 필드 배열 할당 및 초기화
		for(int i=0; i<4 ;i++) {
			bookNameTextFields[i] = new JTextField(10);
			bookAuthorTextFields[i] = new JTextField(10);
			bookPublisherTextFields[i] = new JTextField(10);
			bookCategoryTextFields[i] = new JTextField(10);
			bookIdTextFields[i] = new JTextField(10);
		}
		for(int i=0;i<3;i++) {
			borrowDateTextFields[i] = new JTextField(10);
			returnDateTextFields[i] = new JTextField(10);
			isDelayTextFields[i] = new JTextField(10);
		}
		
		//텍스트 필드 수정 불가 처리
		for(int i=0; i<4 ;i++) {
			bookNameTextFields[i].setEditable(false);
			bookAuthorTextFields[i].setEditable(false);
			bookPublisherTextFields[i].setEditable(false);
			bookCategoryTextFields[i].setEditable(false);
			bookIdTextFields[i].setEditable(false);
		}
		for(int i=0;i<3;i++) {
			borrowDateTextFields[i].setEditable(false);
			returnDateTextFields[i].setEditable(false);
			isDelayTextFields[i].setEditable(false);
		}
		bookAvailableStockTextField.setEditable(false);
	}
	
	//get 메소드들
	public JTabbedPane getAdminTab() { return adminTab; }
	
	public JButton getInsertBookButton() { return insertBookButton; }
	public JButton getEditBookButton() { return editBookButton; }
	public JButton getDeleteBookButton() { return deleteBookButton; }
	
	public JTextField[] getBookNameTextFields() { return bookNameTextFields; }
	public JTextField[] getBookAuthorTextFields() { return bookAuthorTextFields; }
	public JTextField[] getBookPublisherTextFields() { return bookPublisherTextFields; }
	public JTextField[] getBookCategoryTextFields() { return bookCategoryTextFields; }
	public JTextField[] getBookIdTextFields() { return bookIdTextFields; }
	
	public JTextField[] getBorrowDateTextFields() { return borrowDateTextFields; }
	public JTextField[] getReturnDateTextFields() { return returnDateTextFields; }
	public JTextField[] getIsDelayTextFields() { return isDelayTextFields; }
	
	public JTextField getBookAvailableStockTextField() { return bookAvailableStockTextField; }
	
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
			
		case "등록":
			onClickInsertBookButton();
			//등록
			/*
			 * DB에 추가 테이블에서도 추가
			 */
			break;
			
		case "수정":
			onClickEditBookButton();
			//등록
			/*
			 * DB에 추가 테이블에서도 추가
			 */
			break;
			
		case "삭제":
			onClickDeleteBookButton();
			
			//삭제
			/*
			 * DB에서 삭제 테이블에서도 삭제 
			 */
			break;
		}
	}
	
	//등록 버튼 작동 메소드 TODO
	public void onClickInsertBookButton() {
		new InsertBookPage();
	}
	
	//수정 버튼 작동 메소드 TODO
	public void onClickEditBookButton() {
		int totalCount=0;
		try {
			totalCount = Integer.parseInt(bookAvailableStockTextField.getText());
		} catch(NumberFormatException e) {
			totalCount = 0;
		}
		//생성자 호출
		new EditBookPage(
				bookNameTextFields[3].getText(),
				bookAuthorTextFields[3].getText(),
				bookPublisherTextFields[3].getText(),
				bookCategoryTextFields[3].getText(),
				bookIdTextFields[3].getText(),
				totalCount );	
		
	}
	
	//삭제 버튼 작동 메소드 TODO
	public void onClickDeleteBookButton() {
		MessageBox.printInfoMessageBox("삭제");
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