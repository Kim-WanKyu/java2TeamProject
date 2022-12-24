package gui.page.optionPage.mainOption.user.editSignoutUserPage;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.page.PageComponent;
import gui.page.optionPage.mainOption.user.editUserPage.EditUserPage;
import gui.page.optionPage.mainOption.user.signoutUserPage.SignoutUserPage;

public class EditSignoutUserPageComponent extends PageComponent{

	private JButton editButton = new JButton("회원 변경");		//변경 버튼
	private JButton signoutButton = new JButton("회원 탈퇴");	//탈퇴 버튼
	private JButton cancelButton = new JButton("취소");		//취소 버튼
	
	//생성자
	EditSignoutUserPageComponent(JFrame frame){
		super.frame = frame;
		
		editButton.addActionListener(this);
		signoutButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	
	public JButton getEditButton() {return editButton; }
	public JButton getSignoutButton() {return signoutButton; }
	public JButton getCancelButton() {return cancelButton; }
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "회원 변경":
			onClickEditButton();
			break;
		case "회원 탈퇴":
			onClickSognoutButton();
			break;
		case "취소":
			onClickCancelButton();
			break;
		}
	}

	//회원 변경 버튼 작동 메소드
	public void onClickEditButton() {
		frame.dispose();
		new EditUserPage();
	}
	
	//회원 탈퇴 버튼 작동 메소드
	public void onClickSognoutButton() {
		frame.dispose();
		new SignoutUserPage();
	}
	
	//취소 버튼 작동 메소드
	public void onClickCancelButton() {
		frame.dispose();
	}
}
