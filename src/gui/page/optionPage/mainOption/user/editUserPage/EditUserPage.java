package gui.page.optionPage.mainOption.user.editUserPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.page.Page;
import gui.util.WhitePanel;

public class EditUserPage extends Page{
	
	EditUserPageComponent editUserPageComponent = new EditUserPageComponent(this);
	
	//EditUserPage 생성자
	public EditUserPage(){
		setTitle(super.getTitle() + "_회원 정보 변경 화면");
		
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
		//editPanel = 회원 정보 변경 패널(비밀번호만) (editTextPanel 패널 포함)
		WhitePanel editPanel = new WhitePanel(new FlowLayout());
		{
			WhitePanel editTextPanel = new WhitePanel(new GridLayout(4,1));
			{
				WhitePanel editIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editIdPanel.add(new JLabel("ID : "));
					editIdPanel.add(editUserPageComponent.getIdTextField());
				}
				WhitePanel editNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editNamePanel.add(new JLabel("이름 : "));
					editNamePanel.add(editUserPageComponent.getNameTextField());
				}
				WhitePanel editOldPwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editOldPwPanel.add(new JLabel("비밀번호 : "));
					editOldPwPanel.add(editUserPageComponent.getOldPwTextField());
				}
				WhitePanel editNewPwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editNewPwPanel.add(new JLabel("새 비밀번호 : "));
					editNewPwPanel.add(editUserPageComponent.getNewPwTextField());
				}
				editTextPanel.add(editIdPanel);
				editTextPanel.add(editNamePanel);
				editTextPanel.add(editOldPwPanel);
				editTextPanel.add(editNewPwPanel);
			}
			WhitePanel editButtonPanel = new WhitePanel(new GridLayout(3,1,0,5));
			{
				editButtonPanel.add(editUserPageComponent.getChangePwButton());
				editButtonPanel.add(new JLabel(""));	//위치 맟추기 위한 빈 레이블 추가
				editButtonPanel.add(editUserPageComponent.getExitButton());
			}
			editPanel.add(editTextPanel);
			editPanel.add(editButtonPanel);
		}
		ct.add(editPanel);	
	}



}