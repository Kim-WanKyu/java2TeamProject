package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class StartPage extends Page{	

	JButton signUpButton;	//
	JButton quitButton;		//
	JButton findButton;		//
	
	
	//StartPage 생성자
	public StartPage(){
		String strTitle = super.getTitle() + " 초기 화면";
		setTitle(strTitle);

		ct.setLayout(new FlowLayout());	//Layout은 BorderLayout으로 
		

		//왼쪽
		//로고 이미지
		JPanel leftPanel = new JPanel();
		leftPanel.setVisible(true);
		leftPanel.setLayout(new BorderLayout());
		ImageIcon logoImage = new ImageIcon("Images/logo_color3.jpg");

		JLabel logoLabel = new JLabel(logoImage);
		logoLabel.setSize(logoImage.getIconWidth(), logoImage.getIconHeight());
		
		leftPanel.add(logoLabel);
		System.out.println(getHeight());
		
		//System.out.println(super.getJMenuBar().getHeight());
		
		//super.setBackground(Color.BLACK);
		//leftPanel.setBounds(0, super.getHeight()/2 - logoLabel.getHeight()/2 , logoLabel.getWidth(), logoLabel.getHeight());
		
		//오른쪽
		//아이디, 비밀번호 입력할 텍스트필드 / 로그인 버튼
		//화원 등록, ID/PW찾기, 종료하기 버튼
		JPanel rightPanel = new JPanel();
		rightPanel.setVisible(true);
		rightPanel.setLayout(new BorderLayout());
		
		
		
		
		JLabel idLabel = new JLabel("ID");			//id레이블
		JLabel pwLabel = new JLabel("PW");			//pw레이블
		JTextField idTextField = new JTextField(20);	//id텍스트필드
		JTextField pwTextField = new JTextField(20);	//pw텍스트필드
		
		JPanel textPanel = new JPanel(new GridLayout(2,2,20,20));
		textPanel.add(idLabel);
		textPanel.add(idTextField);
		textPanel.add(pwLabel);
		textPanel.add(pwTextField);
		
		JPanel loginPanel = new JPanel(new BorderLayout());
		
		JButton loginButton = new JButton("로그인");		//로그인 버튼
		
		loginPanel.add(textPanel, BorderLayout.CENTER);
		loginPanel.add(loginButton, BorderLayout.EAST);

		JPanel buttonPanel = new JPanel(new GridLayout(1,3,20,50));
		
		signUpButton = new JButton();	//회원가입 버튼
		
		quitButton = new JButton();		//종료하기 버튼
		
		findButton = new JButton();		//ID/PW찾기 버튼
		
		signUpButton = new JButton("회원등록");
		signUpButton.setVisible(true);
		signUpButton.setSize(20,10);
		signUpButton.addActionListener(this);
		
		quitButton = new JButton("종료하기");
		quitButton.setVisible(true);
		quitButton.setSize(20,10);
		quitButton.addActionListener(this);
		
		findButton = new JButton("ID/PW찾기");
		findButton.setVisible(true);
		findButton.setSize(20,10);
		findButton.addActionListener(this);
		
		
		buttonPanel.add(signUpButton);
		buttonPanel.add(findButton);
		buttonPanel.add(quitButton);

		rightPanel.add(loginPanel,BorderLayout.CENTER);
		rightPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		//ct.setBackground(Color.WHITE);
		
		ct.add(leftPanel);
		ct.add(rightPanel);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {

		case "회원등록":
			signUpButton.setEnabled(false);
			//new MainPage();
			break;
			
		case "ID/PW찾기":
			findButton.setEnabled(false);
			//new MainPage();
			break;
		
		case "종료하기":
			System.exit(0);
			break;
			
		default:
			break;
		}
		
	}
}

