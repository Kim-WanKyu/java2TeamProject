package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPage extends Page{	

	
	JButton quitButton;		//종료하기 버튼
	JButton logoutButton;	//로그아웃 버튼
	
	//MainPage 생성자
	public MainPage(){
		//창 설정
		setTitle(super.getTitle() + " 메인 화면");
		setSize(800,600);	//창 크기 800x600
		setLocation(getX()-getWidth()/2,getY()-getHeight()/2);	//StartPage 창 정가운데에 띄우기
		
		//초기화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel mainPagePanel = new WhitePanel();

		//buttonPanel패널(signUpButton,findButton,quitButton 포함)
		WhitePanel buttonPanel = new WhitePanel(new GridLayout(1,3,20,0));
		{
			logoutButton = new JButton("로그아웃");	//로그아웃 버튼
			logoutButton.addActionListener(this);

			quitButton = new JButton("종료하기");		//종료하기 버튼
			quitButton.addActionListener(this);
					
			//(GridLayout(1x3))
			buttonPanel.add(new JLabel());	//위치 맟추기 위한 빈 레이블 부착
			buttonPanel.add(logoutButton);	//로그아웃 버튼 부착
			buttonPanel.add(quitButton);	//종료하기 버튼 부착
		}
		
		JTable infoTable = new JTable(5, 1);
		
		
		mainPagePanel.add(buttonPanel);
		mainPagePanel.add(infoTable );
		
		
		ct.add(mainPagePanel);
		
		
		mainPagePanel.setVisible(true);
		setVisible(true);
	}

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
			
		default:
			break;
		}
	}
}

