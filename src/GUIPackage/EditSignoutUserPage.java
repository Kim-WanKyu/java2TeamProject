package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class EditSignoutUserPage extends Page{

	JButton editButton;		//변경 버튼
	JButton signOutButton;	//탈퇴 버튼
	JButton cancelButton;	//취소 버튼

	//EditSignoutUserPage 생성자
		public EditSignoutUserPage(){
			setTitle("");
			
			this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
				public void windowClosing(WindowEvent e) {
					MainPage.getEditSignoutUserButton().setEnabled(true);
					dispose();
				}
			});
			
			setPage();
			
			packWindow();
		}
		
		@Override
		void setPage() {
			WhitePanel buttonPanel = new WhitePanel(new GridLayout(4,1,100,5));
			{
				editButton = new JButton("회원 변경");		//회원 변경 버튼
				editButton.addActionListener(this);
				signOutButton = new JButton("회원 탈퇴");	//회원 탈퇴 버튼
				signOutButton.addActionListener(this);
				cancelButton = new JButton("취소");	//취소 버튼
				cancelButton.addActionListener(this);
				
				buttonPanel.add(new JLabel(" 원하시는 메뉴를 클릭하세요. "));
				buttonPanel.add(editButton);
				buttonPanel.add(signOutButton);
				buttonPanel.add(cancelButton);
			}
			ct.add(buttonPanel);
		}

		public void actionPerformed(ActionEvent ae) {
			switch (ae.getActionCommand()) {	
			case "회원 변경":
				new EditUserPage();
				dispose();
				break;
				
			case "회원 탈퇴":
				new SignoutUserPage();
				dispose();
				break;
				
			case "취소":
				MainPage.getEditSignoutUserButton().setEnabled(true);
				dispose();
				break;
				
			default:
				break;
			}
		}

}