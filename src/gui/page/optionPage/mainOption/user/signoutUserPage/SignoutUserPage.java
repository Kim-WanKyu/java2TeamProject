package gui.page.optionPage.mainOption.user.signoutUserPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.page.Page;
import gui.util.WhitePanel;

public class SignoutUserPage  extends Page{
	
	private SignoutUserPageComponent signoutUserPageComponent = new SignoutUserPageComponent(this);
	
	//SignoutButton 생성자
	public SignoutUserPage(){
		setTitle(super.getTitle() + "_회원 탈퇴 화면");
		
		this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setPage();
		
		packWindow();
	}
	
	@Override
	protected void setPage() {
		//SignoutPanel = 회원 탈퇴 패널 (SignoutTextPanel 패널 포함)
		WhitePanel SignoutPanel = new WhitePanel(new FlowLayout());
		{
			WhitePanel SignoutTextPanel = new WhitePanel(new GridLayout(3,1));
			{
				WhitePanel SignoutIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					SignoutIdPanel.add(new JLabel("ID : "));
					SignoutIdPanel.add(signoutUserPageComponent.getIdTextField());
				}
				WhitePanel SignoutNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					SignoutNamePanel.add(new JLabel("이름 : "));
					SignoutNamePanel.add(signoutUserPageComponent.getNameTextField());
				}
				WhitePanel SignoutPwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					SignoutPwPanel.add(new JLabel("비밀번호 : "));
					SignoutPwPanel.add(signoutUserPageComponent.getPwTextField());
				}
				SignoutTextPanel.add(SignoutIdPanel);
				SignoutTextPanel.add(SignoutNamePanel);
				SignoutTextPanel.add(SignoutPwPanel);
			}
			WhitePanel SignoutButtonPanel = new WhitePanel(new GridLayout(2,1,0,10));
			{
				SignoutButtonPanel.add(signoutUserPageComponent.getSignoutButton());
				SignoutButtonPanel.add(signoutUserPageComponent.getExitButton());
			}
			SignoutPanel.add(SignoutTextPanel);
			SignoutPanel.add(SignoutButtonPanel);
		}
		ct.add(SignoutPanel);	
	}


}