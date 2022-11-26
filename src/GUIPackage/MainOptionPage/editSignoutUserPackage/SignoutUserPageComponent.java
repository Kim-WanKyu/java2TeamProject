package GUIPackage.MainOptionPage.editSignoutUserPackage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import GUIPackage.MainPage.mainAdminPackage.MainAdminPage;
import GUIPackage.MainPage.mainUserPackage.MainUserPage;
import GUIPackage.StartPage.StartPackage.StartPage;
import GUIPackage.pagePackage.PageComponent;
import GUIPackage.util.MessageBox;

public class SignoutUserPageComponent extends PageComponent {

	//StartPage의 모든 텍스트필드를 담는 ArrayList변수
	private ArrayList<JTextField> signoutUserTextComponent;
	
	private JTextField idTextField = new JTextField(15);	//id 입력 텍스트필드
	private JTextField nameTextField = new JTextField(15);	//이름 입력 텍스트필드
	private JTextField pwTextField = new JTextField(15);	//원 pw 입력 텍스트필드

	private JButton signoutButton = new JButton("회원 탈퇴");	//회원 탈퇴 버튼
	private JButton exitButton = new JButton("나가기");		//나가기 버튼
	
	SignoutUserPageComponent(JFrame frame) {
		super.frame = frame;
		
		signoutButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		signoutUserTextComponent = new ArrayList<>();
		signoutUserTextComponent.add(idTextField);
		signoutUserTextComponent.add(nameTextField);
		signoutUserTextComponent.add(pwTextField);
	}
	
	//get메소드들
	public JTextField getIdTextField() { return idTextField; }		//id 입력 텍스트필드
	public JTextField getNameTextField() { return nameTextField; }	//이름 입력 텍스트필드
	public JTextField getPwTextField() { return pwTextField; }		//pw 입력 텍스트필드
	public JButton getSignoutButton() { return signoutButton; }	//회원 탈퇴 버튼
	public JButton getExitButton() { return exitButton; }		//나가기 버튼
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {	
		case "나가기" :
			frame.dispose();
			break;
			
		case "회원 탈퇴":
			onClickSignoutButton();
			break;
		}
	}
	
	
	//로그인 버튼 작동 메소드
	public void onClickSignoutButton() {
		try {
			boolean isInDB = true;
			if(isInDB = idTextField.getText().equals("123"/*해당 유저의 DB 정보*/)) {
				boolean isCorrect;// = true;
				if(isCorrect = true/*해당 유저의 DB 정보의 이름과 비말번호가 모두 일치하면*/) {
					/*새 비밀번호의 텍스트로 비밀번호 변경 메소드 실행*/
					///*유저의 비밀번호 = */newPwTextField.getText();
					
					/*그 후에 메세지 띄우고 모든 창 나가고, 다시 초기화면 띄움*/
					MessageBox.printInfoMessageBox("탈퇴 되었습니다.");
					
					//켜져있는 모든 창 끄기
					for(int i=0; i<frame.getOwnerlessWindows().length;i++)
						frame.getOwnerlessWindows()[i].dispose();
					new StartPage();	//초기화면 창 생성
				}
				else {
					MessageBox.printWarningMessageBox("입력하신 회원 정보가 일치하지 않습니다.");
					//텍스트필드 지우기
					eraseTextComponent(signoutUserTextComponent);
				}
			}
			else {
				MessageBox.printWarningMessageBox("입력하신 ID 정보가 존재하지 않습니다.");
				//텍스트필드 지우기
				eraseTextComponent(signoutUserTextComponent);
			}
		}
		catch(Exception e) {
			MessageBox.printErrorMessageBox("!오류 발생!");
			MessageBox.printErrorMessageBox("회원 탈퇴 창을 닫습니다.");
			frame.dispose();
		}
	}

}
