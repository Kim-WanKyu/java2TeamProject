package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartPage extends Page{	

	JButton loginButton;	//로그인 버튼
	JButton signUpButton;	//회원등록 버튼
	JButton findButton;		//ID/PW찾기 버튼
	JButton quitButton;		//종료하기 버튼

	
	//StartPage 생성자
	public StartPage(){
		//창 설정
		setTitle(super.getTitle() + " 초기 화면");
		setSize(750,250);	//창 크기 750x250
		setLocation(getX()-getWidth()/2,getY()-getHeight()/2);	//StartPage 창 정가운데에 띄우기

		
		//초기화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel startPagePanel = new WhitePanel();
		
		//초기화면 패널 왼쪽 구역
		//왼쪽 패널 / (logoLabel레이블 포함)
		WhitePanel leftPanel = new WhitePanel(new BorderLayout());
		{
			ImageIcon logoImage = new ImageIcon("Images/logo_color1.jpg");	//로고 이미지
			JLabel logoLabel = new JLabel(logoImage);	//logoLabel레이블 / logoImage포함

			leftPanel.add(logoLabel); //logoLabel 부착
		}
		
		//초기화면 패널 오른쪽 구역
		//오른쪽 패널
		//login패널 포함 / (textPanel패널, loginButton버튼 포함)
		//textPanel패널 포함 / ((grid2x2) / idPanel패널,idTextField텍스트필드,pwPanel패널,pwTextField텍스트필드 포함)
		//buttonPanel패널 포함 / ((grid2x4) / signUpButton버튼,findButton버튼,quitButton버튼 포함)
		WhitePanel rightPanel = new WhitePanel(new BorderLayout());
		{
			//rightPanel.setLayout();
			//rightPanel.setVisible(true);
		}

		//textPanel패널(idPanel, pwPanel 패널, idTextField, pwTextField 텍스트필드 포함)
		WhitePanel textPanel = new WhitePanel(new GridLayout(2,2));
//		textPanel.setLayout();
		{
			WhitePanel idPanel = new WhitePanel(new BorderLayout());	//id패널 (id레이블 포함)
		//	idPanel.setLayout();
			{
				JLabel idLabel = new JLabel("아이디 : ");				//id레이블
				idPanel.add(idLabel ,BorderLayout.EAST);			//id패널에 id레이블을 부착(오른쪽에 붙여서)
			}
			JTextField idTextField = new JTextField();		//id텍스트필드
			
			WhitePanel pwPanel = new WhitePanel(new BorderLayout());	//pw패널 (pw레이블 포함)
		//	pwPanel.setLayout();
			{
				JLabel pwLabel = new JLabel("비밀번호 : ");			//pw레이블
				pwPanel.add(pwLabel ,BorderLayout.EAST);			//pw패널에 pw레이블을 부착(오른쪽에 붙여서)
			}
			JTextField pwTextField = new JTextField();		//pw텍스트필드

			//textPanel에 부착
			textPanel.add(idPanel);
			textPanel.add(idTextField);
			textPanel.add(pwPanel);
			textPanel.add(pwTextField);
		}
		
		
		//loginPanel패널(loginButton 포함)
		WhitePanel loginPanel = new WhitePanel(new BorderLayout());
	//	loginPanel.setLayout();
		{
			loginButton = new JButton("로그인");		//로그인 버튼
			loginButton.addActionListener(this);
			
			loginPanel.add(textPanel, BorderLayout.CENTER);
			loginPanel.add(loginButton, BorderLayout.EAST);
		}
		
		
		//buttonPanel패널(signUpButton,findButton,quitButton 포함)
		WhitePanel buttonPanel = new WhitePanel(new GridLayout(2,4,20,45));
		{	/*
			 * GridLayout(2x4) 배치
			 *  '빈레이블'  '빈레이블'   '빈레이블'   '빈레이블'
			 *  '빈레이블'  '회원등록'  'ID/PW찾기'  '종료하기'
			 * */
			
			signUpButton = new JButton("회원등록");	//회원등록 버튼
			signUpButton.addActionListener(this);

			findButton = new JButton("ID/PW찾기");	//ID/PW찾기 버튼
			findButton.addActionListener(this);
		
			quitButton = new JButton("종료하기");		//종료하기 버튼
			quitButton.addActionListener(this);
			
			//(GridLayout(2x4))
			buttonPanel.add(new JLabel());	//위치 맟추기 위한 빈 레이블 부착
			buttonPanel.add(new JLabel());
			buttonPanel.add(new JLabel());
			buttonPanel.add(new JLabel());
			
			buttonPanel.add(new JLabel());
			buttonPanel.add(signUpButton);	//회원등록 버튼 부착
			buttonPanel.add(findButton);	//ID/PW찾기 버튼 부착
			buttonPanel.add(quitButton);	//종료하기 버튼 부착
		}
	
		
		rightPanel.add(loginPanel,BorderLayout.NORTH);
		rightPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		
		startPagePanel.add(leftPanel);
		startPagePanel.add(rightPanel);


		ct.add(startPagePanel);
		
		
		startPagePanel.setVisible(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		
		case "로그인":
			dispose();		//초기화면 창 끄고
			new MainPage(); //메인화면 창 생성
			break;
		
		case "회원등록":
			signUpButton.setEnabled(false);
			//new MainPage();
			break;
			
		case "ID/PW찾기":
			findButton.setEnabled(false);
			//new FindPage();
			break;
		
		case "종료하기":
			System.exit(0);
			break;
			
		default:
			break;
		}
		
	}
}

