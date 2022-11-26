package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignoutUserPage  extends Page{
	
	JTextField idTextField;			//id 입력 텍스트필드
	JTextField nameTextField;		//이름 입력 텍스트필드
	JTextField PwTextField;		//원 pw 입력 텍스트필드

	JButton SignoutButton;			//회원 탈퇴 버튼
	JButton exitButton;				//나가기 버튼
		
	//SignoutButton 생성자
		public SignoutUserPage(){
			setTitle(super.getTitle() + "_회원 탈퇴 화면");
			
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
			//SignoutPanel = 회원 탈퇴 패널 (SignoutTextPanel 패널 포함)
			WhitePanel SignoutPanel = new WhitePanel(new FlowLayout());
			{
				WhitePanel SignoutTextPanel = new WhitePanel(new GridLayout(3,1));
				{
					WhitePanel SignoutIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						SignoutIdPanel.add(new JLabel("ID : "));
						SignoutIdPanel.add(idTextField = new JTextField(15));
					}
					WhitePanel SignoutNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						SignoutNamePanel.add(new JLabel("이름 : "));
						SignoutNamePanel.add(nameTextField = new JTextField(15));
					}
					WhitePanel SignoutPwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						SignoutPwPanel.add(new JLabel("비밀번호 : "));
						SignoutPwPanel.add(PwTextField = new JTextField(15));
					}
					SignoutTextPanel.add(SignoutIdPanel);
					SignoutTextPanel.add(SignoutNamePanel);
					SignoutTextPanel.add(SignoutPwPanel);
				}
				WhitePanel SignoutButtonPanel = new WhitePanel(new GridLayout(2,1,0,10));
				{
					SignoutButton = new JButton("회원 탈퇴");
					SignoutButton.addActionListener(this);
					exitButton = new JButton("나가기");
					exitButton.addActionListener(this);

					SignoutButtonPanel.add(SignoutButton);
					SignoutButtonPanel.add(exitButton);
				}
				SignoutPanel.add(SignoutTextPanel);
				SignoutPanel.add(SignoutButtonPanel);
			}
			ct.add(SignoutPanel);	
		}

		public void actionPerformed(ActionEvent ae) {
			switch (ae.getActionCommand()) {	
			case "나가기" :
				MainPage.getEditSignoutUserButton().setEnabled(true);
				this.dispose();
				break;
				
			case "회원 탈퇴":
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
							for(int i=0; i<getOwnerlessWindows().length;i++)
								getOwnerlessWindows()[i].dispose();
							new StartPage();	//초기화면 창 생성
						}
						else {
							MessageBox.printWarningMessageBox("입력하신 회원 정보가 일치하지 않습니다.");
							//텍스트필드 지우기
							idTextField.setText("");
							nameTextField.setText("");
							PwTextField.setText("");
						}
					}
					else {
						MessageBox.printWarningMessageBox("입력하신 ID 정보가 존재하지 않습니다.");
						//텍스트필드 지우기
						idTextField.setText("");
						nameTextField.setText("");
						PwTextField.setText("");
					}
				}
				catch(Exception e) {
					MessageBox.printErrorMessageBox("!오류 발생!");
					MessageBox.printErrorMessageBox("회원 탈퇴 창을 닫습니다.");
					MainPage.getEditSignoutUserButton().setEnabled(true);
					this.dispose();
				}
				break;
				
			default:
				break;
			}
		}

}