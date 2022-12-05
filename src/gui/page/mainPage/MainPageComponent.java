package gui.page.mainPage;

import java.awt.event.ActionEvent;
import java.awt.print.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import book.BookManager;
import book.CategorizeKDC;
import gui.page.PageComponent;
import gui.page.mainPage.mainAdminPage.MainAdminPage;
import gui.page.mainPage.mainAdminPage.MainAdminPageComponent;
import gui.page.optionPage.mainOption.book.editBookPage.EditBookPage;
import gui.page.optionPage.mainOption.user.editSignoutUserPage.EditSignoutUserPage;
import gui.page.startPage.StartPage;
import gui.page.startPage.StartPageComponent;
import gui.table.AllBookTable;
import gui.table.AllUserTable;
import gui.table.MyBookTable;
import gui.table.SearchTable;
import gui.util.MessageBox;
import user.User;
import user.UserManager;

//MainPage의 컴포넌트를 위한 클래스
public class MainPageComponent extends PageComponent {
	
	//컴포넌트들
	private JTabbedPane tabbedPane = new JTabbedPane();		//탭 그룹홀더


	private JTextField searchTextField = new JTextField(20);	//검색 필드	
	//버튼
	private JButton searchButton = new JButton("검색");			//검색 버튼
	private JButton editSignoutButton = new JButton("변경/탈퇴");	//변경/탈퇴 버튼
	private JButton logoutButton = new JButton("로그아웃");		//로그아웃 버튼
	private JButton quitButton = new JButton("종료하기");			//종료하기 버튼
	
	private static int tabIndex = 0;
	public static void setTabIndex(int n) { tabIndex = n; }
	
	//검색 카테고리 콤보박스 내용에 들어갈 배열
	private final String[] searchCategoryString = {"도서명", "저자명", "출판사", "분류", "ID(학번)", "이름"};
	private JComboBox<String> searchCategoryComboBox = new JComboBox<String>(searchCategoryString);
	
	
	//MainPageComponent생성자
	public MainPageComponent(JFrame frame)
	{
		super.frame= frame;
		
		searchButton.addActionListener(this);
		editSignoutButton.addActionListener(this);
		quitButton.addActionListener(this);
		logoutButton.addActionListener(this);
		
		AllBookTable.InitAllBookTable();
	}
	
	
	
	//get 메소드들
	public JComboBox<String> getSearchCategoryComboBox() { return searchCategoryComboBox; }
	
	public JTextField getSearchTextField() { return searchTextField; }
	
	public JTabbedPane getTabbedPane() { return tabbedPane; }

	public String[] getSearchCategoryString() { return searchCategoryString; }
	
	public JButton getSearchButton() { return searchButton;  }
	public JButton getEditSignoutButton() { return editSignoutButton; }
	public JButton getLogoutButton() { return logoutButton; }
	public JButton getQuitButton() { return quitButton; }
	
	//버튼 클릭 시 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent ae) {

		switch(ae.getActionCommand()) {
		case "검색":
			onClickSearchButton();
			break;
			
		case "변경/탈퇴":
			//변경/탈퇴
			new EditSignoutUserPage();
			/*
			 * 창 띄우고  버튼 비활성화
			 */
			break;
			
		case "로그아웃":
			try {
				onClickLogoutButton();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			onClickLogoutButton();

			break;

		case "종료하기":
			onClickExitButton();
			break;
			
			//관리자
		case "등록":
			new EditBookPage();
			//등록
			/*
			 * DB에 추가 테이블에서도 추가
			 */
			break;
			
			//관리자
		case "수정":
			new EditBookPage();
			//등록
			/*
			 * DB에 추가 테이블에서도 추가
			 */
			break;
			
			//관리자
		case "삭제":
			MessageBox.printInfoMessageBox("삭제");
			//삭제
			/*
			 * DB에서 삭제 테이블에서도 삭제 
			 */
			break;
		}
	}

	//검색 버튼 작동 메소드 TODO
	public void onClickSearchButton() {
		String category = this.getSearchCategoryComboBox().getSelectedItem().toString();
		String str = getSearchTextField().getText();
		MessageBox.printInfoMessageBox("검색");
		//2자 이상 입력 시 검색 가능
		if(str.length() >= 2) {
			
			MessageBox.printInfoMessageBox("검색분류:"+category + "\n검색어:" +str);
			
			String userId = StartPageComponent.getUser().getID();
			boolean userIsAdmin = UserManager.getInstance().findUser(userId).getIsAdmin();
			System.out.println("loginUser"+userId+":"+userIsAdmin);
			
			if(userIsAdmin == true) {	//관리자이면(0.전체도서/1.전체유저)
				if(tabIndex == 0)	//전체 도서
				{
					//도서 검색메서드 실행
					DefaultTableModel searchBookModel = new DefaultTableModel(null, AllBookTable.getAllBookColumnName());
					AllBookTable.getAllBookTable().setModel(searchBookModel);
					
					Vector<book.Book> bookList = BookManager.getInstance().findBook(category, str);					
					for(int i=0; i<bookList.size(); i++) {
						Vector<Object> vector = new Vector<Object>();
						vector.add(bookList.get(i).getName());
						vector.add(bookList.get(i).getAuthor());
						vector.add(bookList.get(i).getPublisher());
						vector.add(CategorizeKDC.getCategoryname(bookList.get(i).getCategory()));
						vector.add(bookList.get(i).getCategory());
						vector.add(bookList.get(i).getId());
						vector.add(bookList.get(i).getTotalCount());
						vector.add(bookList.get(i).getBorrowCount());
						
						searchBookModel.addRow(vector);
					}
					searchBookModel.fireTableRowsUpdated(0, searchBookModel.getRowCount() -1);
					AllBookTable.getAllBookTable().updateUI();				
				}
				else if(tabIndex == 1) {
					//유저 검색 메서드 실행
					DefaultTableModel searchUserModel = new DefaultTableModel(null, AllUserTable.getAllUserColumnName());
					AllUserTable.getAllUserTable().setModel(searchUserModel);
					
					Vector<user.User> userList = UserManager.getInstance().searchUser(category, str);					
					for(User setUser : userList ) {
						Vector<Object> vector = new Vector<Object>();
						
						vector.add(setUser.getID());
						vector.add(setUser.getName());
						
						searchUserModel.addRow(vector);
					}					
					searchUserModel.fireTableRowsUpdated(0, searchUserModel.getRowCount() -1);
					AllUserTable.getAllUserTable().updateUI();				
				}
			}
			else if(userIsAdmin == false) {
				if(tabIndex == 0) {
					//도서 검색메서드 실행
					DefaultTableModel searchBookModel = new DefaultTableModel(null, AllBookTable.getAllBookColumnName());
					AllBookTable.getAllBookTable().setModel(searchBookModel);
					
					Vector<book.Book> bookList = BookManager.getInstance().findBook(category, str);					
					for(int i=0; i<bookList.size(); i++) {
						Vector<Object> vector = new Vector<Object>();
						vector.add(bookList.get(i).getName());
						vector.add(bookList.get(i).getAuthor());
						vector.add(bookList.get(i).getPublisher());
						vector.add(CategorizeKDC.getCategoryname(bookList.get(i).getCategory()));
						vector.add(bookList.get(i).getCategory());
						vector.add(bookList.get(i).getId());
						vector.add(bookList.get(i).getTotalCount());
						vector.add(bookList.get(i).getBorrowCount());
						
						searchBookModel.addRow(vector);
					}
					searchBookModel.fireTableRowsUpdated(0, searchBookModel.getRowCount() -1);
					AllBookTable.getAllBookTable().updateUI();	
				}
				else if(tabIndex == 1) {
					//내 도서 검색 메서드 실행
					DefaultTableModel searchBookModel = new DefaultTableModel(null, AllBookTable.getAllBookColumnName());
					MyBookTable myBookTable = new MyBookTable();
					myBookTable.getMyBookTable().setModel(searchBookModel);
					
					Vector<book.Book> bookList = BookManager.getInstance().findBook(category, str);					
					for(int i=0; i<bookList.size(); i++) {
						Vector<Object> vector = new Vector<Object>();
						vector.add(bookList.get(i).getName());
						vector.add(bookList.get(i).getAuthor());
						vector.add(bookList.get(i).getPublisher());
						vector.add(CategorizeKDC.getCategoryname(bookList.get(i).getCategory()));
						vector.add(bookList.get(i).getCategory());
						vector.add(bookList.get(i).getId());
						vector.add(bookList.get(i).getTotalCount());
						vector.add(bookList.get(i).getBorrowCount());
						
						searchBookModel.addRow(vector);
					}					
					searchBookModel.fireTableRowsUpdated(0, searchBookModel.getRowCount() -1);
					myBookTable.getMyBookTable().updateUI();
				}
			}

		}
		else {
			MessageBox.printWarningMessageBox("검색어가 너무 짧습니다.");
		}
	}
		
	//변경/탈퇴 버튼 작동 메소드 TODO
	public void onClickEditSignoutButton() {
		new EditSignoutUserPage();
	}
	
	//로그아웃 버튼 작동 메소드 TODO

	public void onClickLogoutButton()  {
		//켜져있는 모든 창 끄고
		try {
		for(int i = 0; i< MainPage.getOwnerlessWindows().length;i++)
			MainPage.getOwnerlessWindows()[i].dispose();
		new StartPage();//초기화면 창 생성
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	//종료하기 버튼 작동 메소드 TODO
	public void onClickExitButton() {
		System.exit(0);
	}
}
