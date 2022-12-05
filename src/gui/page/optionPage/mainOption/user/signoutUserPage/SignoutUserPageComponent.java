package gui.page.optionPage.mainOption.user.signoutUserPage;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import gui.page.PageComponent;
import gui.page.startPage.StartPage;
import gui.page.startPage.StartPageComponent;
import gui.util.MessageBox;
import user.User;
import user.UserManager;

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
			for(JTextField jtf : signoutUserTextComponent) {
				if(jtf.getText().equals("")) {
					MessageBox.printWarningMessageBox("입력한 정보가 없습니다. \n모든 정보를 입력하세요.");
					return;
				}
			}
			onClickSignoutButton();
			break;
		}
	}
	
	
	//회원 탈퇴 버튼 작동 메소드
	public void onClickSignoutButton() {
		try {
			//삭제할 유저
			User deleteUser = new User();
			deleteUser = UserManager.getInstance().findUser(StartPageComponent.getUser().getID());

			//해당 유저의 DB 정보와 일치한지 확인
			//ID 일치 여부 확인
			if(idTextField.getText().equals(deleteUser.getID())) {
				//이름, 비밀번호 일치 여부 확인
				if(deleteUser.getName().equals(nameTextField.getText()) && deleteUser.getPassword().equals(pwTextField.getText())) {
					//회원 탈퇴 성공 시
					if(UserManager.getInstance().deleteUser(deleteUser)) {
						MessageBox.printInfoMessageBox("탈퇴 되었습니다.");
						//켜져있는 모든 창 끄기
						for(int i=0; i<Window.getOwnerlessWindows().length;i++)
							Window.getOwnerlessWindows()[i].dispose();
						
						new StartPage();	//초기화면 창 생성						
					}
					else { //반납하지 않은 도서가 있는 경우
						MessageBox.printWarningMessageBox("반납하지 않은 도서가 있습니다. \n전부 반납 하신 후 탈퇴가 가능합니다.");
						eraseTextComponent(signoutUserTextComponent);
					}
				}
				else {	//이름 또는 비밀번호가 일치하지 않는 경우
					MessageBox.printWarningMessageBox("입력하신 이름 또는 비밀번호가 일치하지 않습니다.");
					eraseTextComponent(signoutUserTextComponent);					
				}				
			}
			else {	//ID가 일치하지 않는 경우
				MessageBox.printWarningMessageBox("입력하신 ID 정보가 존재하지 않습니다.");
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
