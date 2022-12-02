package gui.page.optionPage.startOption.signupPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.page.PageComponent;
import gui.util.MessageBox;
import user.User;
import user.UserManager;

import java.util.ArrayList;

//SignupPage의 컴포넌트를 위한 클래스
public class SignupPageComponent extends PageComponent implements ItemListener {

	//signupPage의 모든 텍스트필드를 담는 ArrayList변수
	private ArrayList<JTextField> signupTextComponent;

	//컴포넌트들
	private JLabel idLabel = new JLabel();							//id레이블 ("학번 : " / "교번 : ")
	private JTextField idTextField = new JTextField(15);					//id 입력 텍스트필드
	private JTextField nameTextField = new JTextField(15);					//이름 입력 텍스트필드
	private JTextField pwTextField = new JTextField(15);					//pw 입력 텍스트필드
	private JPasswordField pwCheckPasswordField = new JPasswordField(15);	//pw확인 입력 패스워드필드

	private JButton signupButton = new JButton("등록하기");	//등록하기 버튼
	private JButton exitButton = new JButton("나가기");		//나가기 버튼
	private JRadioButton userRadioButton = new JRadioButton("사용자");	//user 라디오버튼
	private JRadioButton adminRadioButton = new JRadioButton("관리자");	//admin 라디오버튼

	//SignupPageComponent생성자
	public SignupPageComponent(JFrame frame) {
		super.frame = frame;
		
		signupButton.addActionListener(this);
		exitButton.addActionListener(this);	

		userRadioButton.setBackground(Color.WHITE);
		userRadioButton.addItemListener(this);
		adminRadioButton.setBackground(Color.WHITE);
		adminRadioButton.addItemListener(this);
		
		//signupTextComponent에 텍스트필드,패스워드필드 넣어줌
		signupTextComponent = new ArrayList<>();
		signupTextComponent.add(idTextField);
		signupTextComponent.add(nameTextField);
		signupTextComponent.add(pwTextField);
		signupTextComponent.add(pwCheckPasswordField);
	}

	//get 메소드들
	public JLabel getIdLabel() { return idLabel; }
	public JTextField getIdTextField() { return idTextField; }
	public JTextField getNameTextField() { return nameTextField; }
	public JTextField getPwTextField() { return pwTextField; }
	public JPasswordField getPwCheckPasswordField() { return pwCheckPasswordField; }
	
	public JButton getSignupButton() { return signupButton; }
	public JButton getExitButton() { return exitButton; }
	public JRadioButton getUserRadioButton() { return userRadioButton; }
	public JRadioButton getAdminRadioButton() { return adminRadioButton; }
	
	//버튼 actionPerformed
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "등록하기":
			//빈 텍스트가 있으면 버튼 작동 전 리턴
			for(JTextField jtf : signupTextComponent) {
				if(jtf.getText().equals("")) {
					MessageBox.printWarningMessageBox("입력한 정보가 없습니다. \n모든 정보를 입력하세요.");
					return;
				}
			}
			//pwTextField의 내용과 pwCheckPasswordField의 내용이 일치하지 않으면 버튼 작동 전 리턴
			if(! getPwTextField().getText().equals( new String( getPwCheckPasswordField().getPassword() ) )) {
				MessageBox.printWarningMessageBox("비밀번호 확인 입력값이 \n입력하신 비밀번호와 \n일치하지 않습니다.");
				return;
			}
			//빈 텍스트가 없으면 버튼 작동
			onClickSignupButton();
			break;
		
		case "나가기":
			frame.dispose();
			break;
		}
	}

	//라디오버튼 itemStateChanged
	@Override
	public void itemStateChanged(ItemEvent ie) {
		 if(ie.getStateChange()==ItemEvent.DESELECTED){
             return;	//선택된 것이 없는 경우
         }
		 else if(userRadioButton.isSelected()){
        	 idLabel.setText("ID(학번) : ");
        	 eraseTextComponent(signupTextComponent);
         }
         else if(adminRadioButton.isSelected()){
        	 idLabel.setText("ID(교번) : ");
        	 eraseTextComponent(signupTextComponent);
         }
	}
	
	//등록하기 버튼 작동 메소드
	public void onClickSignupButton() {
		String userId = getIdTextField().getText();
		String userName = getNameTextField().getText();
		String userPassword = getPwTextField().getText();
		boolean isAdmin = getAdminRadioButton().isSelected();
		
		MessageBox.printInfoMessageBox(userId +'\n'+ userName +'\n'+ userPassword +'\n'+ isAdmin);

		User newuser = new User();
		newuser.setID(userId);
		newuser.setName(userName);
		newuser.setPassword(userPassword);
		newuser.setIsAdmin(isAdmin);
		UserManager.getInstance().insertUser(newuser);
		
		//if(id가 중복되지 않으면,)
		//	프로그램 내의 유저정보를 담는 벡터(나 배열)에도 추가하고, DB에도 추가한다.
	}
}
