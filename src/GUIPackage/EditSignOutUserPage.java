package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class EditSignOutUserPage  extends Page{
	
	JTextField idTextField;				//id 입력 텍스트필드
	JTextField nameTextField;			//이름 입력 텍스트필드
	JTextField passwordTextField;		//pw 입력 텍스트필드
	JTextField newPasswordTextField;	//새pw 입력 텍스트필드

	JButton editButton;				//비밀번호 변경 버튼
	JButton exitButton;				//나가기 버튼
	JButton signOutButton;				//비밀번호 변경 버튼
	
	JTabbedPane tableTabbedPane;
	
	//FindPage 생성자
		public EditSignOutUserPage(){
			setTitle(super.getTitle() + "_정보 변경 및 탈퇴 화면");
			
			this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
				public void windowClosing(WindowEvent e) {
					//메인화면의 정보변경/탈퇴 버튼 활성화 
					dispose();
				}
			});
			
			setPage();
			setSize(500,500);
			//packWindow();
		}
		
		@Override
		void setPage() {
			WhitePanel textPanel = new WhitePanel();
			{
				
			}
		}

		public void actionPerformed(ActionEvent ae) {
			switch (ae.getActionCommand()) {	
			case "탈퇴" :
				break;
				
			case "변경":				
				break;
				
			case "나가기":
				dispose();
				break;
				
			default:
				break;
			}
		}

}