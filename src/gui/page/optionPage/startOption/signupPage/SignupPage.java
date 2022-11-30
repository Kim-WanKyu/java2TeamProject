package gui.page.optionPage.startOption.signupPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.page.Page;
import gui.util.WhitePanel;

public class SignupPage extends Page {
	
	private SignupPageComponent signupPageComponent = new SignupPageComponent(this);
	
	//RegisterPage 생성자
	public SignupPage(){
		setTitle(super.getTitle() + "_회원 가입");
		
		this.addWindowListener(new WindowAdapter() {	//윈도우리스너 어댑터추가
			//창 끄기 버튼 누를 시 이벤트 처리
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setPage();
		
		packWindow();
	}
	
	//화면 구성 메소드 setPage 메소드
	@Override
	protected void setPage() {
		//signUpPagePanel = ID/PW찾기화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel signUpPagePanel = new WhitePanel();
		{
			//leftPanel = 왼쪽 패널(회원등록화면 패널 왼쪽 구역)
			WhitePanel leftPanel = new WhitePanel(new GridLayout(1,1));
			{
				ImageIcon characterImage = new ImageIcon("Images/캐릭터-응용형2.jpg");	//logoImage 이미지아이콘 / "Images/캐릭터-응용형2.jpg"
				JLabel logoImageLabel = new JLabel(characterImage);					//logoImageLabel레이블 / 캐릭터 이미지
				leftPanel.add(logoImageLabel);
			}
			//leftPanel = 오른쪽 패널(회원등록화면 패널 오른쪽 구역) (radioButtonPanel패널)
			WhitePanel rightPanel = new WhitePanel(new BorderLayout());
			{
				//radioButtonPanel 패널(groupRadioButton(userRadioButton 버튼, adminRadioButton 버튼 포함)버튼그룹 포함)
				WhitePanel radioButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					//라디오버튼 그룹화 객체
					ButtonGroup groupRadioButton = new ButtonGroup();
					{	/*라디오버튼그룹(user:사용자, admin:관리자)*/
						groupRadioButton.add(signupPageComponent.getUserRadioButton());
						groupRadioButton.add(signupPageComponent.getAdminRadioButton());
						signupPageComponent.getUserRadioButton().setSelected(true);	//기본값으로 사용자 선택
					}
					radioButtonPanel.add(signupPageComponent.getUserRadioButton());
					radioButtonPanel.add(signupPageComponent.getAdminRadioButton());			
				}
				//textPanel패널 (idPanel 패널, idPanel 패널, pwPanel 패널, pwCheckPanel 패널 포함)
				WhitePanel textPanel = new WhitePanel(new GridLayout(4,1));
				{
					//idPanel패널  (idLabel 레이블, idTextField 텍스트필드 포함)
					WhitePanel idPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						idPanel.add(signupPageComponent.getIdLabel());
						idPanel.add(signupPageComponent.getIdTextField());
					}
					//namePanel패널 (("이름 : ") 레이블, nameTextField 텍스트필드 포함)
					WhitePanel namePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						namePanel.add(new JLabel("이름 : "));
						namePanel.add(signupPageComponent.getNameTextField());
					}
					//pwPanel패널 (("비밀번호 : ") 레이블, pwTextField 텍스트필드 포함)
					WhitePanel pwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						pwPanel.add(new JLabel("비밀번호 : "));
						pwPanel.add(signupPageComponent.getPwTextField());
					}
					//pwCheckPanel패널 (("비밀번호확인 : ") 레이블, pwCheckPasswordField 패스워드필드 포함)
					WhitePanel pwCheckPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						pwCheckPanel.add(new JLabel("비밀번호확인 : "));
						pwCheckPanel.add(signupPageComponent.getPwCheckPasswordField());
					}
					textPanel.add(idPanel);
					textPanel.add(namePanel);
					textPanel.add(pwPanel);
					textPanel.add(pwCheckPanel);
				}
				//downButtonPanel패널 (SignupButton 버튼, ExitButton 버튼 포함)
				WhitePanel downButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					downButtonPanel.add(signupPageComponent.getSignupButton());
					downButtonPanel.add(signupPageComponent.getExitButton());
				}
				rightPanel.add(radioButtonPanel, BorderLayout.NORTH);
				rightPanel.add(textPanel, BorderLayout.CENTER);
				rightPanel.add(downButtonPanel, BorderLayout.SOUTH);
			}
			signUpPagePanel.add(leftPanel);
			signUpPagePanel.add(rightPanel);
		}
		//컨테이너 ct에 signUpPagePanel 부착
		ct.add(signUpPagePanel);
	}
}