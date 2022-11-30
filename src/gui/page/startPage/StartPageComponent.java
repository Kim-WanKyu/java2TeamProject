package gui.page.startPage;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import gui.page.PageComponent;
import gui.page.mainPage.mainAdminPage.MainAdminPage;
import gui.page.mainPage.mainUserPage.MainUserPage;
import gui.page.optionPage.startOption.findPage.FindPage;
import gui.page.optionPage.startOption.signupPage.SignupPage;
import gui.util.MessageBox;

//StartPage의 컴포넌트를 위한 클래스
public class StartPageComponent extends PageComponent {

	//StartPage의 모든 텍스트필드를 담는 ArrayList변수
	private ArrayList<JTextField> startTextComponent;

	//컴포넌트들
	private JTextField idTextField = new JTextField(15);			//id 텍스트필드
	private JPasswordField pwPasswordField = new JPasswordField(15);//pw 패스워드필드
	private JButton loginButton = new JButton("로그인");	//로그인 버튼
	private JButton quitButton = new JButton("종료하기");	//종료하기 버튼
	private JButton signUpButton = new JButton("회원등록");//회원등록 버튼
	private JButton findButton = new JButton("ID/PW찾기");//ID/PW찾기 버튼
	
	//StartPageComponent생성자
	public StartPageComponent()
	{
		loginButton.addActionListener(this);
		quitButton.addActionListener(this);
		signUpButton.addActionListener(this);
		findButton.addActionListener(this);
		
		//startTextComponent에 텍스트필드,패스워드필드 넣어줌
		startTextComponent = new ArrayList<>();
		startTextComponent.add(idTextField);
		startTextComponent.add(pwPasswordField);
	}
	
	//get 메소드들
	public JButton getLoginButton() { return loginButton; }
	public JButton getQuitButton() { return quitButton; }
	public JButton getSignUpButton() { return signUpButton; }
	public JButton getFindButton() { return findButton; }
	public JTextField getIdTextField() { return idTextField; }
	public JPasswordField getPwPasswordField() { return pwPasswordField; }
	
	//버튼actionPerformed
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "로그인":
			onClickLoginButton();
			break;
			
		case "회원등록":
			new SignupPage();
			break;
			
		case "ID/PW찾기":
			new FindPage();
			break;
			
		case "종료하기":
			System.exit(0);
			break;
		}
	}
	
	//로그인 버튼 작동 메소드
	public void onClickLoginButton() {
		String id = getIdTextField().getText(); //입력한 id
		String password = new String(getPwPasswordField().getPassword()); //입력한 pw
		
		//new MainPage(); //메인화면 창 생성
		
		//로그인 메소드 성공
		if(/*로그인메소드*/id.equals("123") && password.equals("123"))		//임시로 지정
		{	//TODO 임시
			boolean isAdmin = false;	//임시 사용자 기본 //관리자
			// 로그인 할 때, userDB의 isAdmin 값을 추출하여 isAdmin에 넣음.
			//관리자
			if(isAdmin == true) {
				//켜져있는 모든 창 끄기
				for(int i=0; i<StartPage.getOwnerlessWindows().length;i++)
					StartPage.getOwnerlessWindows()[i].dispose();
				new MainAdminPage();
			}
			//사용자
			else {
				//켜져있는 모든 창 끄기
				for(int i=0; i<StartPage.getOwnerlessWindows().length;i++)
					StartPage.getOwnerlessWindows()[i].dispose();
				new MainUserPage();
			}
			//user가 맞는지 확인 if(user's_id & id's_pw isIn userDB)
			
		}
		else if(id.equals("qwe")) {
			//켜져있는 모든 창 끄기
			for(int i=0; i<StartPage.getOwnerlessWindows().length;i++)
				StartPage.getOwnerlessWindows()[i].dispose();
			new MainAdminPage();
		}
		else
		{	/*입력한 내용을 지우고, 메세지를 띄움*/
			eraseTextComponent(startTextComponent);
			MessageBox.printWarningMessageBox("입력한 정보가 없거나, \n비밀번호가 일치하지 않습니다.");
		}
	}
}
