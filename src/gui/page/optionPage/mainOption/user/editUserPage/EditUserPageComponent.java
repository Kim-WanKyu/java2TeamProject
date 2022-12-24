package gui.page.optionPage.mainOption.user.editUserPage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import gui.page.PageComponent;
import gui.page.startPage.StartPageComponent;
import gui.util.MessageBox;
import user.User;
import user.UserManager;

public class EditUserPageComponent extends PageComponent {

	private ArrayList<JTextField> editUserTextComponent;
	

	private JTextField idTextField = new JTextField(15);	//id 입력 텍스트필드
	private JTextField nameTextField = new JTextField(15);	//이름 입력 텍스트필드
	private JTextField oldPwTextField = new JTextField(15);	//원래 pw 입력 텍스트필드
	private JTextField newPwTextField = new JTextField(15);	//새 pw 입력 텍스트필드

	private JButton changeUserInfoButton = new JButton("회원 정보 변경");	//회원 정보 변경 버튼
	private JButton exitButton = new JButton("나가기");	//나가기 버튼
		
	public EditUserPageComponent(JFrame frame) {
		super.frame = frame;

		changeUserInfoButton.addActionListener(this);
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

	public JButton getChangeUserInfoButton() { return changeUserInfoButton; }	//회원 정보 변경 버튼
	public JButton getExitButton() { return exitButton; }						//나가기 버튼
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {	
		case "나가기" :
			frame.dispose();
			break;
			
		case "회원 정보 변경":
			onChangeUserInfoButton();
			break;
		}
	}
	
	//TODO
	//회원 정보 변경 메소드
	public void onChangeUserInfoButton() {
		try {
			User editUser = UserManager.getInstance().findUser(StartPageComponent.getUser().getID());
			
			//ID가 일지하는 경우
			if(idTextField.getText().equals(editUser.getID())) {
				String name = editUser.getName();
				String password = editUser.getPassword();
				nameTextField.getText().equals("");
				
				//이름과 비밀번호 모두 빈칸인 경우 갱신 X
				if(nameTextField.getText().equals("") && oldPwTextField.getText().equals(""))
				{
					MessageBox.printWarningMessageBox("이름 또는 비밀번호를 입력하세요.");
					return;
				}
				else {	//갱신
					//비밀번호가 입력된 경우 비밀번호 갱신
					if(!oldPwTextField.getText().equals("")) {
						//비밀번호가 일치하는 경우
						if(oldPwTextField.getText().equals(editUser.getPassword())) {
							//새 비밀번호가 입력된 경우
							if(!newPwTextField.getText().equals("")) {
								password = newPwTextField.getText();
							}
							else {
								MessageBox.printWarningMessageBox("변경할 새 비밀번호를 입력하세요.");
								return;
							}
						}
						//비밀번호가 일치하지 않는 경우
						else {
							MessageBox.printWarningMessageBox("입력한 현재 비밀번호가 일치하지 않습니다.");
							return;
						}
					}
					//이름이 입력된 경우 이름 갱신
					if(!nameTextField.getText().equals("")) {
						name = nameTextField.getText();
					}

					//정보 갱신 준비
					editUser.setName(name);
					editUser.setPassword(password);
					
					//정보변경 메소드 실행
					UserManager.getInstance().changeInform(editUser);
					MessageBox.printInfoMessageBox("변경된 이름 : " + name + "\n변경된 비밀번호 : " + password);
					frame.dispose();
				}
			}
			else {	//ID가 일지하지 않는 경우
				MessageBox.printWarningMessageBox("입력하신 ID 정보가 존재하지 않습니다.");
				eraseTextComponent(editUserTextComponent);	//텍스트필드 지우기
				return;
			}
		}
		catch(Exception e) {
			MessageBox.printErrorMessageBox("!오류 발생!");
			MessageBox.printErrorMessageBox("회원 정보 변경 창을 닫습니다.");
			frame.dispose();
		}
	}
}