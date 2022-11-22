package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SignUpPage extends Page implements ItemListener{
	
	JLabel idLabel = new JLabel();	//id레이블 ("학번 : "/"교번 : ")
	
	JTextField idTextField;			//id 입력 텍스트필드
	JTextField nameTextField;		//이름 입력 텍스트필드
	JTextField pwPasswordField;		//pw 입력 텍스트필드
	JTextField pwCheckPasswordField;//pw확인 입력 텍스트필드(패스워드필드)
	
	JButton signUpButton;			//등록하기 버튼
	JButton exitButton;				//나가기 버튼
	
	JRadioButton userRadioButton;	//user 라디오버튼
	JRadioButton adminRadioButton;	//admin 라디오버튼
	
	//RegisterPage 생성자
	public SignUpPage(){
		setTitle(super.getTitle() + "_회원 가입");
		
		this.addWindowListener(new WindowAdapter() {	//윈도우리스너 어댑터추가
			//창 끄기 버튼 누를 시 이벤트 처리
			public void windowClosing(WindowEvent e) {
				StartPage.getSignUpButton().setEnabled(true);
				dispose();
			}
		});
		setPage();
		
		packWindow();
	}
	
	@Override
	void setPage() {
		WhitePanel radioButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
		{
			WhitePanel userRadioPanel = new WhitePanel();
			{
				userRadioButton = new JRadioButton("사용자");
				userRadioButton.setBackground(Color.WHITE);
				userRadioPanel.add(userRadioButton);
			}
			WhitePanel adminRadioPanel = new WhitePanel();
			{
				adminRadioButton = new JRadioButton("관리자");
				adminRadioButton.setBackground(Color.WHITE);
				adminRadioPanel.add(adminRadioButton);
			}
			//라디오버튼 그룹화 객체
			ButtonGroup groupRadioButton = new ButtonGroup();
			{
				//라디오버튼그룹(user:사용자, admin:관리자)
				groupRadioButton.add(userRadioButton);
				groupRadioButton.add(adminRadioButton);
				
				userRadioButton.addItemListener(this);
				adminRadioButton.addItemListener(this);
			}
			radioButtonPanel.add(userRadioPanel);
			radioButtonPanel.add(adminRadioPanel);
		}
		
		WhitePanel leftPanel = new WhitePanel(new GridLayout(1,1));
		{
			ImageIcon logoImage = new ImageIcon("Images/logo_color1.jpg");	//logoImage 이미지아이콘 / "Images/logo_color1.jpg"
			JLabel logoImageLabel = new JLabel(logoImage);					//logoImageLabel레이블 / 로고 이미지
			leftPanel.add(logoImageLabel);
		}
		WhitePanel rightPanel = new WhitePanel(new BorderLayout());
		{
			WhitePanel textPanel = new WhitePanel(new GridLayout(4,1));
			{
				WhitePanel idPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					idPanel.add(idLabel = new JLabel("ID : "));
					idPanel.add(idTextField = new JTextField(15));
				}
				WhitePanel namePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					namePanel.add(new JLabel("이름 : "));
					namePanel.add(nameTextField = new JTextField(15));
				}
				WhitePanel pwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					pwPanel.add(new JLabel("비밀번호 : "));
					pwPanel.add(pwPasswordField = new JTextField(15));
				}
				WhitePanel pwCheckPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					pwCheckPanel.add(new JLabel("비밀번호확인 : "));
					pwCheckPanel.add(pwCheckPasswordField = new JPasswordField(15));
				}
				textPanel.add(idPanel);
				textPanel.add(namePanel);
				textPanel.add(pwPanel);
				textPanel.add(pwCheckPanel);
			}
			WhitePanel JDownButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
			{
				signUpButton = new JButton("화원등록");
				signUpButton.addActionListener(this);
				
				exitButton = new JButton("나가기");
				exitButton.addActionListener(this);	
				
				JDownButtonPanel.add(signUpButton);
				JDownButtonPanel.add(exitButton);
			}
			rightPanel.add(radioButtonPanel, BorderLayout.NORTH);
			rightPanel.add(textPanel, BorderLayout.CENTER);
			rightPanel.add(JDownButtonPanel, BorderLayout.SOUTH);
		}
		
		userRadioButton.setSelected(true);	//기본값으로 사용자 선택
		
		//signUpPagePanel = ID/PW찾기화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel signUpPagePanel;
		signUpPagePanel = new WhitePanel();
		signUpPagePanel.add(leftPanel);
		signUpPagePanel.add(rightPanel);
				
		//컨테이너 ct에 signUpPagePanel 부착
		ct.add(signUpPagePanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "등록하기":
			//if(DB에 idTextField.getText()없으면)
			//	DB 등록 메소드 추가
			break;
		
		case "나가기":
			StartPage.getSignUpButton().setEnabled(true);
			this.dispose();
			break;
			
		default:
			break;
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		 if(e.getStateChange()==ItemEvent.DESELECTED){
             return;	//선택된 것이 없는 경우
         }
		 else if(userRadioButton.isSelected()){
        	 idLabel.setText("ID(학번) : ");
         }
         else if(adminRadioButton.isSelected()){
        	 idLabel.setText("ID(교번) : ");
         }
	}
}