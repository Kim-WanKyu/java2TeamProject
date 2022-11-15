package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPage extends Page{	

	JButton quitButton;		//종료하기 버튼
	JButton logoutButton;	//로그아웃 버튼
	JButton borrowButton;	//대여하기 버튼
	JButton changeButton;	//변경/탈퇴 버튼

	//MainPage 생성자
	public MainPage(){
		//창 설정
		setTitle(super.getTitle() + "_메인 화면");
		
		this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
			public void windowClosing(WindowEvent e) {
				new StartPage();
			}
		});
		
		setPage();
		/////////////////////////////////////////////////////
//		logoutButton = new JButton("로그아웃");
//		logoutButton.addActionListener(this);
//		ct.add(logoutButton);
		setSize(1000,100);
		////////////////////////////////////////////////////////
		//packWindow();
	}
	
	//setPage 메소드
	@Override
	void setPage() {
		boolean isAdmin = false;	//사용자 기본

		WhitePanel leftPanel = new WhitePanel(new BorderLayout());
		WhitePanel rightPanel = new WhitePanel(new BorderLayout());
		
		JTabbedPane tableTabbedPane = new JTabbedPane();	//
		JTable bookTable = new JTable(1,5);
		JTable myBookTable = new JTable();
		
		tableTabbedPane.addTab("전체목록", bookTable);
		tableTabbedPane.addTab("내 도서", myBookTable);
		
		// userDB의 isAdmin 값을 추출하여 isAdmin에 넣음.
		if(isAdmin == true) {
			{
				
			}
			//관리자
			//setAdminPage();
		}
		else {
			//사용자
			//setUserPage();
		}
		
		//mainPagePanel = 메인화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel mainPagePanel = new WhitePanel();
		mainPagePanel.add(leftPanel);
		mainPagePanel.add(rightPanel);
		mainPagePanel.add(tableTabbedPane);
		//컨테이너 ct에 startPagePanel 부착
		ct.add(mainPagePanel);
	}
	
	//관리자 화면으로 세팅하는 메소드
	void setAdminPage(WhitePanel leftPanel, WhitePanel rightPanel) {
		
	}
	
	//사용자 화면으로 세팅하는 메소드
	void setUserPage(WhitePanel leftPanel, WhitePanel rightPanel) {
		
	}
	
	//버튼 클릭 시 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "로그아웃":
			dispose();		//메인화면 창 끄고
			new StartPage();//초기화면 창 생성
			break;
			
		case "종료하기":
			System.exit(0);
			break;
			
		case "대여하기":
			//대여
			break;
			
		case "변경/탈퇴":
			//변경/탈퇴
			break;
			
		default:
			break;
		}
	}
}

