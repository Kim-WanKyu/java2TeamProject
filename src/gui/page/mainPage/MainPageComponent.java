package gui.page.mainPage;

import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import gui.page.PageComponent;
import gui.page.optionPage.mainOption.book.editBookPage.EditBookPage;
import gui.page.optionPage.mainOption.user.editSignoutUserPage.EditSignoutUserPage;
import gui.page.startPage.StartPage;
import gui.util.MessageBox;

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
	
	//검색 카테고리 콤보박스 내용에 들어갈 배열
	private final String[] searchCategoryString = {"도서명", "저자명", "출판사", "분류", "ID(학번)", "이름"};
	private JComboBox<String> searchCategoryComboBox = new JComboBox<String>(searchCategoryString);
	
	//테이블
	////allBookTable 전체도서 테이블 static
	//allBookTable의 컬럼명
	private final static String []allBookColumnName = {"도서명","저자명","출판사","분류","KDC","bookID","총 권 수","빌린 권 수"}; //TODO 분류가 없고,KDC만 있는거같음?. 7~8개 컬럼
	public static String[] getAllBookColumnName() { return allBookColumnName; }
	
	//allBookTable의 데이터 //TODO DB에서 가져왔던 전체 도서 벡터로 초기화
	private static String [][]allBookData = {	{"a1","a2","a3","a4","a5","a6","a7","a8"},
												{"b1","b2","b3","a4","a5","a6","a7","a8"},
												{"c1","c2","c3","a4","a5","a6","a7","a8"}	};
	public static String[][] getAllBookData() { return allBookData; }
	
	//allBookTable의 테이블모델
	private static DefaultTableModel allBookTableModel = new DefaultTableModel(allBookData,allBookColumnName);
	/*
	 * //TODO make Table from DataBase 처음 프로그램 시작될 때 설정
					allBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					allBookTable.setModel(new DefaultTableModel (new Object[][][] {}, new String[] { "셀1","셀2","셀3","셀4","셀5" })  {
						public boolean isCellEditable(int row, int column) { return false; }
					} );
	 */
	//allBookTable테이블
	private static JTable allBookTable = new JTable(allBookTableModel); //

	//allBookTable테이블 리턴하는 메소드
	public static JTable getAllBookTable() { return allBookTable; }
	
	
	/////
	//userTable 전체도서 테이블 static
	//userTable의 컬럼명
	private final static String[] userColumnName = {"ID","이름"};
	public static String[] getAllUserColumnName() { return userColumnName; }
	
	//userTable의 데이터 //TODO DB에서 가져왔던 전체 유저 벡터로 초기화
	private static String [][]userData = {	{"a1","a2"},	//내용 //TODO 이거는 전체 유저 벡터로 초기화
											{"b1","b2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"},
											{"c1","c2"}	};
	public static String[][] getAllUserData() { return userData; }
	
	//userTable의 테이블모델
	private static DefaultTableModel userTableModel = new DefaultTableModel(userData,userColumnName);

	//userTable테이블
	private static JTable userTable = new JTable(userTableModel); //

	//userTable테이블 리턴하는 메소드
	public static JTable getAllUserTable() { return userTable; }

	//변경사항있으면 업데이트해주는 메소드
	//allBookTable.updateUI();
	
	//MainPageComponent생성자
	public MainPageComponent(JFrame frame)
	{
		super.frame= frame;

		searchButton.addActionListener(this);
		editSignoutButton.addActionListener(this);
		quitButton.addActionListener(this);
		logoutButton.addActionListener(this);
		
		InitAllBookTable();
	}
	
	public static void InitAllBookTable() {
		//TODO make Table from DataBase 처음 프로그램 시작될 때 설정
		allBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//테이블 열 위치 변경 불가
		allBookTable.getTableHeader().setReorderingAllowed(false);
		//테이블 내용 수정 불가 처리
		allBookTable.setModel(new DefaultTableModel (allBookData, allBookColumnName)  {
			public boolean isCellEditable(int row, int column) { return false; }
		} );
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
			onClickLogoutButton();
			break;

		case "종료하기":
			onClickExitButton();
			break;
		
			//사용자
		case "대여하기":
			//대여
			/*if(유저가 대출가능한지 && 재고가 남았는지)
			 * 해당 도서 정보 유저에 저장하고 도서 재고-1
			 * else
			 * 	경고메세지 (대출 불가)
			 */
			break;

			//사용자
		case "반납하기":	//TODO 버튼 아예 없음
			//반납
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
			MessageBox.printInfoMessageBox(category + '\n' +str);
		}
		else {
			MessageBox.printWarningMessageBox("검색어가 너무 짧습니다.");
		}
		//검색 메소드 실행
		if (tabbedPane.getSelectedIndex() == 0) {
			//탭이 전체목록 일 때
		}
		else {
			//탭이 내 책 목록일 때
		}
		//검색
		/* if(일치)
		 * 	텍스트필드에서 추출해서 전체 책과 비교해서 일치하는 것 들만 테이블에 출력
		 * elseq
		 * 	경고 메세지 (일치하는 책이 없습니다.)
		 */
	}
	
	//변경/탈퇴 버튼 작동 메소드 TODO
	public void onClickEditSignoutButton() {
		new EditSignoutUserPage();
	}
	
	//로그아웃 버튼 작동 메소드 TODO
	public void onClickLogoutButton() {
		//켜져있는 모든 창 끄고
		for(int i = 0; i< MainPage.getOwnerlessWindows().length;i++)
			MainPage.getOwnerlessWindows()[i].dispose();
		new StartPage();//초기화면 창 생성
	}
	
	//종료하기 버튼 작동 메소드 TODO
	public void onClickExitButton() {
		System.exit(0);
	}
}
