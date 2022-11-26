package GUIPackage.MainOptionPage.editSignoutUserPackage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import GUIPackage.pagePackage.PageComponent;
import GUIPackage.util.MessageBox;

public class EditUserPageComponent extends PageComponent {

	private ArrayList<JTextField> editUserTextComponent;
	

	private JTextField idTextField = new JTextField(15);	//id 입력 텍스트필드
	private JTextField nameTextField = new JTextField(15);	//이름 입력 텍스트필드
	private JTextField oldPwTextField = new JTextField(15);	//원래 pw 입력 텍스트필드
	private JTextField newPwTextField = new JTextField(15);	//새 pw 입력 텍스트필드

	private JButton changePwButton = new JButton("비밀번호 변경");	//비밀번호 변경 버튼
	private JButton exitButton = new JButton("나가기");			//나가기 버튼
		
	public EditUserPageComponent(JFrame frame) {
		super.frame = frame;

		changePwButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		editUserTextComponent = new ArrayList<>();
		editUserTextComponent.add(idTextField);
		editUserTextComponent.add(nameTextField);
		editUserTextComponent.add(oldPwTextField);
		editUserTextComponent.add(newPwTextField);
	}
	//get메소드들
	public JTextField getIdTextField() {return idTextField; }		//id 입력 텍스트필드
	public JTextField getNameTextField() {return nameTextField; }	//id 입력 텍스트필드
	public JTextField getOldPwTextField() {return oldPwTextField; }	//id 입력 텍스트필드
	public JTextField getNewPwTextField() {return newPwTextField; }	//id 입력 텍스트필드

	public JButton getChangePwButton() { return changePwButton; }	//비밀번호 변경 버튼
	public JButton getExitButton() { return exitButton; }			//나가기 버튼
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {	
		case "나가기" :
			frame.dispose();
			break;
			
		case "비밀번호 변경":
			onChangePwButton();
			break;
		}
	}
	
	//TODO
	//비밀번호 변경 메소드
	public void onChangePwButton() {
		try {
			boolean isInDB = true;
			if(isInDB = idTextField.getText().equals("123"/*해당 유저의 DB 정보*/)) {
				boolean isCorrect;// = true;
				if(isCorrect = true/*해당 유저의 DB 정보의 이름과 비말번호가 모두 일치하면*/) {
					/*새 비밀번호의 텍스트로 비밀번호 변경 메소드 실행*/
					///*유저의 비밀번호 = */newPwTextField.getText();
					
					/*그 후에 메세지 띄우고 창 나감*/
					MessageBox.printInfoMessageBox("정보가 변경되었습니다.");
					MessageBox.printInfoMessageBox("회원 정보 변경 창을 닫습니다.");
					frame.dispose();
				}
				else {
					MessageBox.printWarningMessageBox("입력하신 회원 정보가 일치하지 않습니다.");
					//텍스트필드 지우기
					eraseTextComponent(editUserTextComponent);
				}
			}
			else {
				MessageBox.printWarningMessageBox("입력하신 ID 정보가 존재하지 않습니다.");
				//텍스트필드 지우기
				eraseTextComponent(editUserTextComponent);
			}
		}
		catch(Exception e) {
			MessageBox.printErrorMessageBox("!오류 발생!");
			MessageBox.printErrorMessageBox("회원 정보 변경 창을 닫습니다.");
			frame.dispose();
		}

	}

}
