package gui.page.mainPage.mainAdminPage;

import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

import book.BookManager;

import gui.page.mainPage.MainPageComponent;
import gui.page.optionPage.mainOption.book.editBookPage.EditBookPage;
import gui.page.optionPage.mainOption.book.insertBookPage.InsertBookPage;
import gui.table.AllBookTable;
import gui.table.AllUserTable;
import gui.util.MessageBox;

public class MainAdminPageComponent extends MainPageComponent {

	private MainPageComponent mainPageComponent = new MainPageComponent(this.frame);

	//mainAdminPage의 모든 텍스트필드를 담는 ArrayList변수
	private ArrayList<JTextField> mainAdminTextComponents;
	
	//컴포넌트들
	//전체목록 연체확인 탭에 추가되는 컴포넌트 (단, 전체도서인 경우 3번 / 전체회원의 경우 0~2번)
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
	private JTextField bookAvailableStockTextField;						//재고 텍스트필드
		
	//관리자 사용 버튼
	private JButton insertBookButton = new JButton("등록");	//등록 버튼
	private JButton editBookButton = new JButton("수정");		//수정 버튼
	private JButton deleteBookButton = new JButton("삭제");	//삭제 버튼
	
	//관리자화면의 탭 0.전체도서 1.전체회원
	private JTabbedPane adminTab;
		
	//MainAdminPageComponent생성자
	public MainAdminPageComponent(JFrame frame) {
		super(frame);
		
		adminTab = mainPageComponent.getTabbedPane();
				
		insertBookButton.addActionListener(this);
		editBookButton.addActionListener(this);
		deleteBookButton.addActionListener(this);
		
		AllBookTable.InitAllBookTable();
		AllUserTable.InitAllUserTable();
		InitTextField();
		setFieldFromAllBookTable();
		setFieldFromAllUserTable();
		
		//탭의 상태가 변할 시 이벤트 작동 (탭 이동 시 발동)
		adminTab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) {
				eraseTextComponent(mainAdminTextComponents);	//텍스트필드 지움
				MainPageComponent.setTabIndex(adminTab.getSelectedIndex());		//탭 인덱스를 누른 탭번호으로 설정
				AllBookTable.getAllBookTable().setModel(AllBookTable.getAllBookTableModel());	//전체도서 테이블 초기화
				AllUserTable.getAllUserTable().setModel(AllUserTable.getUserTableModel());		//전체유저 테이블 초기화
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
		AllBookTable.getAllBookTable().addMouseListener(new MouseAdapter() {
			@Override
			//클릭 시 정보 가져오기
			public void mouseClicked(MouseEvent me) {
				System.out.println("선택된 행"+AllBookTable.getAllBookTable().getSelectedRow());
				
				Object[] str = new Object[AllBookTable.getAllBookTable().getColumnCount()];
				for(int i=0; i<AllBookTable.getAllBookTable().getColumnCount();i++)
					str[i] =  AllBookTable.getAllBookTable().getValueAt(AllBookTable.getAllBookTable().getSelectedRow(), i);
				
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
	
	public void setFieldFromAllUserTable() {
		//마우스 이벤트 처리 추가
		AllUserTable.getAllUserTable().addMouseListener(new MouseAdapter() {
			@Override
			//클릭 시 정보 가져오기
			public void mouseClicked(MouseEvent me) {
				System.out.println("유저테이블 선택된 행"+AllUserTable.getAllUserTable().getSelectedRow());
				
				System.out.println("여기 유저테이블 실행 : ");
				//선택한 행의 학번
				String id = AllUserTable.getAllUserTable().getValueAt(AllUserTable.getAllUserTable().getSelectedRow(), 0).toString();
				//선택한 학번의 사용자
				user.User selectedUser = user.UserManager.getInstance().findUser(id);
				//선택한 학번의 사용자의 빌린 도서들
				book.Book[] bookOfSelectedUser = new book.Book[3];
				//선택한 학번의 사용자의 빌린 도서의 아이디들
				String bookId[] = {selectedUser.getBorrowBooks()[0], selectedUser.getBorrowBooks()[1], selectedUser.getBorrowBooks()[2]};
				
				for(int i=0; i<3; i++) {
					//유저가 빌린 책이 있으면
					if(bookId[i] != null) {							
						bookOfSelectedUser[i] = BookManager.getInstance().getlist().get(selectedUser.getBorrowBooks()[i]);
						
						bookNameTextFields[i].setText(bookOfSelectedUser[i].getName().toString());
						bookAuthorTextFields[i].setText(bookOfSelectedUser[i].getAuthor().toString());
						bookPublisherTextFields[i].setText(bookOfSelectedUser[i].getPublisher().toString());
						if(!bookOfSelectedUser[i].getCategory().toString().equals("")) {
							bookCategoryTextFields[i].setText(book.CategorizeKDC.getCategoryname(bookOfSelectedUser[i].getCategory().toString()));
						}
						else {
							bookCategoryTextFields[i].setText("");
						}
						bookIdTextFields[i].setText(bookOfSelectedUser[i].getId().toString());							
						borrowDateTextFields[i].setText(selectedUser.getBorrowDates()[i].toString());
						returnDateTextFields[i].setText(selectedUser.getBorrowDates()[i].toLocalDate().plusDays(7).toString());
						
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate returnDate = LocalDate.parse(returnDateTextFields[i].getText().toString(), formatter);
						LocalDate now = LocalDate.now();
						//도서 연체시
						if(now.isAfter(returnDate))
							isDelayTextFields[i].setText("true");
						else
							isDelayTextFields[i].setText("false");
					}
					else {
						bookNameTextFields[i].setText("");
						bookAuthorTextFields[i].setText("");
						bookPublisherTextFields[i].setText("");
						bookCategoryTextFields[i].setText("");
						bookIdTextFields[i].setText("");
						
						borrowDateTextFields[i].setText("");
						returnDateTextFields[i].setText("");
						isDelayTextFields[i].setText("");
					}
				}				
			}
		});
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
		bookAvailableStockTextField = new JTextField(10);
		
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
			break;
			
		case "수정":
			if(!bookIdTextFields[3].getText().equals(""))
				onClickEditBookButton();
			else
				MessageBox.printWarningMessageBox("선택된 도서가 없습니다.");
			break;
			
		case "삭제":
			if(!bookIdTextFields[3].getText().equals(""))
				onClickDeleteBookButton();
			else
				MessageBox.printWarningMessageBox("선택된 도서가 없습니다.");
			break;
		}
	}
	
	//등록 버튼 작동 메소드
	public void onClickInsertBookButton() {
		new InsertBookPage();
		eraseTextComponent(mainAdminTextComponents);
	}
	
	//수정 버튼 작동 메소드
	public void onClickEditBookButton() {
		int totalCount = (BookManager.getInstance().getlist().get(bookIdTextFields[3].getText()).getTotalCount());
		
		//생성자 호출
		new EditBookPage(
				bookNameTextFields[3].getText(),
				bookAuthorTextFields[3].getText(),
				bookPublisherTextFields[3].getText(),
				book.CategorizeKDC.getKDCCode(bookCategoryTextFields[3].getText()),
				bookIdTextFields[3].getText(),
				totalCount );
		eraseTextComponent(mainAdminTextComponents);
	}
	
	//삭제 버튼 작동 메소드
	public void onClickDeleteBookButton() {
		//삭제 성공시 테이블도 갱신
		if( BookManager.getInstance().deleteBook(bookIdTextFields[3].getText()) == true ) {
			AllBookTable.getAllBookTableModel().removeRow(AllBookTable.getAllBookTable().getSelectedRow());
			AllBookTable.getAllBookTable().updateUI();
			MessageBox.printInfoMessageBox("삭제");
		}
		eraseTextComponent(mainAdminTextComponents);
	}
}