package gui.page.startPage;

import java.awt.*;

import java.sql.SQLException;


import javax.swing.*;

import gui.page.Page;
import gui.util.WhitePanel;
import user.UserManager;

public class StartPage extends Page{
	
//	private final static StartPage object = new StartPage();
//	public static StartPage getStartPage() { return object; }
	
	StartPageComponent startPageComponent = new StartPageComponent();
	
	//StartPage 생성자


	public StartPage(){

		setTitle(super.getTitle() + "_초기 화면");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	//x 누를 시 전체 창 끄기

		setPage();		//화면 구성
		
		packWindow();	//창 크기 및 위치 맟춤
	}
	
	//화면 구성 메소드 setPage 메소드
	@Override
	protected void setPage() {
		//startPagePanel = 초기화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel startPagePanel = new WhitePanel();	 //startPagePanel = 초기화면 패널
		{
			//leftPanel = 왼쪽 패널(초기화면 패널 왼쪽 구역)
			WhitePanel leftPanel = new WhitePanel(new GridLayout(1,1));
			{
				ImageIcon logoImage = new ImageIcon("Images/logo_color1.jpg");	//logoImage 이미지아이콘 / "Images/logo_color1.jpg"
				JLabel logoImageLabel = new JLabel(logoImage);					//logoImageLabel레이블 / 로고 이미지
				leftPanel.add(logoImageLabel);
			}
			//rightPanel = 오른쪽 패널(초기화면 패널 오른쪽 구역) (loginPanel 패널, optionButtonPanel 패널)
			WhitePanel rightPanel = new WhitePanel(new BorderLayout());
			{
				//loginPanel패널 (textPanel 패널, loginButton 버튼 포함)
				WhitePanel loginPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					//textPanel패널 (idPanel 패널, pwPanel 패널 포함)
					WhitePanel textPanel = new WhitePanel(new BorderLayout());
					{
						//idPanel패널 (("아이디 : ") 레이블, idTextField 텍스트필드 포함)
						WhitePanel idPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
						{
							idPanel.add(new JLabel("아이디 : "));		//id패널에 ("아이디 : ")레이블을 부착
							idPanel.add(startPageComponent.getIdTextField());			//id패널에 id텍스트필드를 부착
						}
						//pwPanel패널 (("비밀번호 : ") 레이블 포함, pwTextField 패스워드필드 포함)
						WhitePanel pwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
						{
							pwPanel.add(new JLabel("비밀번호 : "));	//pw패널에 ("비밀번호 : ")레이블을 부착
							pwPanel.add(startPageComponent.getPwPasswordField());		//pw패널에 pw텍스트필드를 부착
							startPageComponent.getPwPasswordField().setEchoChar('*');	//표시되는 문자를 "*" 로 표시
						}
						textPanel.add(idPanel, BorderLayout.NORTH);
						textPanel.add(pwPanel, BorderLayout.SOUTH);
					}
					//loginButton 버튼
					{	/* loginButton 크기조절 */
						startPageComponent.getLoginButton().setPreferredSize(
								new Dimension(	startPageComponent.getLoginButton().getPreferredSize().width,
												startPageComponent.getLoginButton().getPreferredSize().height * 2)	);
					}
					loginPanel.add(textPanel);
					loginPanel.add(startPageComponent.getLoginButton());
				}
				//optionButtonPanel 패널(signUpButton,findButton,quitButton 버튼 포함)
				WhitePanel optionButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					optionButtonPanel.add(startPageComponent.getSignUpButton());
					optionButtonPanel.add(startPageComponent.getFindButton());
					optionButtonPanel.add(startPageComponent.getQuitButton());
				}
				rightPanel.add(loginPanel,BorderLayout.NORTH);
				rightPanel.add(new JLabel(" "), BorderLayout.CENTER);	//위치 맟추기 위한 빈 레이블 추가
				rightPanel.add(optionButtonPanel, BorderLayout.SOUTH);
			}
			startPagePanel.add(leftPanel);
			startPagePanel.add(rightPanel);
		}
		//컨테이너 ct에 startPagePanel 부착
		ct.add(startPagePanel);
	}
}