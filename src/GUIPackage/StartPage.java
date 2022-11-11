package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartPage extends Page{	

	JTextField idTextField;			//id입력 텍스트필드
	JPasswordField pwPasswordField;	//pw입력 패스워드필드 (JPasswordField = 입력한 값과 관계없이 지정한 문자로 출력)
	JButton loginButton;			//로그인 버튼
	
	JButton signUpButton;			//회원등록 버튼
	JButton findButton;				//ID/PW찾기 버튼
	JButton quitButton;				//종료하기 버튼
	
	//StartPage 생성자
	public StartPage(){
		setTitle(super.getTitle() + "_초기 화면");
		
		setPage();		//페이지 설정
		
		packWindow();	//창 크기 및 위치 맟춤
	}
	
	//setPage 메소드
	@Override
	void setPage() {
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
				//textPanel패널 (idPanel, pwPanel 패널 포함)
				WhitePanel textPanel = new WhitePanel(new BorderLayout());
				{
					//id패널 (("아이디 : ") 레이블, idTextField 텍스트필드 포함)
					WhitePanel idPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						idPanel.add(new JLabel("아이디 : "));						//id패널에 ("아이디 : ")레이블을 부착(오른쪽에 붙여서)
						idPanel.add(idTextField = new JTextField(15));			//id패널에 id텍스트필드를 부착
					}
					//pw패널 (("비밀번호 : ") 레이블 포함, pwTextField 패스워드필드 포함)
					WhitePanel pwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						pwPanel.add(new JLabel("비밀번호 : "));					//pw패널에 ("비밀번호 : ")레이블을 부착
						pwPanel.add(pwPasswordField = new JPasswordField(15));	//pw패널에 pw텍스트필드를 부착
						pwPasswordField.setEchoChar('*');						//표시되는 문자를 "*" 로 표시
					}
					textPanel.add(idPanel, BorderLayout.NORTH);
					textPanel.add(pwPanel, BorderLayout.SOUTH);
				}
				//loginButton 버튼
				{
					loginButton = new JButton("로그인");
					loginButton.addActionListener(this);
				 	/* loginButton 크기조절 */
					loginButton.setPreferredSize(
							new Dimension(loginButton.getPreferredSize().width, loginButton.getPreferredSize().height * 2) );
				}
				loginPanel.add(textPanel);
				loginPanel.add(loginButton);
			}
			//optionButtonPanel 패널(signUpButton,findButton,quitButton 버튼 포함)
			WhitePanel optionButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
			{
				signUpButton = new JButton("회원등록");	//회원등록 버튼
				signUpButton.addActionListener(this);
				
				findButton = new JButton("ID/PW찾기");	//ID/PW찾기 버튼
				findButton.addActionListener(this);
				
				quitButton = new JButton("종료하기");		//종료하기 버튼
				quitButton.addActionListener(this);
				
				optionButtonPanel.add(signUpButton);
				optionButtonPanel.add(findButton);
				optionButtonPanel.add(quitButton);
			}
			rightPanel.add(loginPanel,BorderLayout.NORTH);
			rightPanel.add(new JLabel(" "), BorderLayout.CENTER);	//위치 맟추기 위한 빈 레이블 추가
			rightPanel.add(optionButtonPanel, BorderLayout.SOUTH);
		}
		
		//startPagePanel = 초기화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel startPagePanel = new WhitePanel();
		startPagePanel.add(leftPanel);
		startPagePanel.add(rightPanel);
		
		//컨테이너 ct에 startPagePanel 부착
		ct.add(startPagePanel);
	}

	//버튼 클릭 시 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		
		case "로그인":
			//로그인 메소드 추가 필요
			
			//임시로 지정
			if(idTextField.getText() == "123")
			{
				//user가 맞는지 확인 if(user's_id&pw isin userDB)
				dispose();		//초기화면 창 끄고
				new MainPage();	//메인화면 창 생성 / 추후에 MainPage클래스 생성자에 (user정보) 추가 필요
			}
			//else 아니면 에러 메세지
			else
			{
				//메시지창 띄우는 메소드
				JOptionPane.showMessageDialog(null, "입력한 정보가 없거나, \n비밀번호가 일치하지 않습니다.", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
			
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
