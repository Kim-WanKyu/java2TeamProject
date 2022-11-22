package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditUserPage extends Page{
	
	JTextField idTextField;		//id 입력 텍스트필드
	JTextField nameTextField;	//이름 입력 텍스트필드
	JTextField oldPwTextField;	//원래 pw 입력 텍스트필드
	JTextField newPwTextField;	//새 pw 입력 텍스트필드

	JButton ChangePwButton;		//비밀번호 변경 버튼
	JButton exitButton;			//나가기 버튼
		
	//EditUserPage 생성자
		public EditUserPage(){
			setTitle(super.getTitle() + "_회원 정보 변경 화면");
			
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
			//editPanel = 회원 정보 변경 패널(비밀번호만) (editTextPanel 패널 포함)
			WhitePanel editPanel = new WhitePanel(new FlowLayout());
			{
				WhitePanel editTextPanel = new WhitePanel(new GridLayout(4,1));
				{
					WhitePanel editIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						editIdPanel.add(new JLabel("ID : "));
						editIdPanel.add(idTextField = new JTextField(15));
					}
					WhitePanel editNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						editNamePanel.add(new JLabel("이름 : "));
						editNamePanel.add(nameTextField = new JTextField(15));
					}
					WhitePanel editOldPwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						editOldPwPanel.add(new JLabel("비밀번호 : "));
						editOldPwPanel.add(oldPwTextField = new JTextField(15));
					}
					WhitePanel editNewPwPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						editNewPwPanel.add(new JLabel("새 비밀번호 : "));
						editNewPwPanel.add(newPwTextField = new JTextField(15));
					}
					editTextPanel.add(editIdPanel);
					editTextPanel.add(editNamePanel);
					editTextPanel.add(editOldPwPanel);
					editTextPanel.add(editNewPwPanel);
				}
				WhitePanel editButtonPanel = new WhitePanel(new GridLayout(3,1,0,5));
				{
					ChangePwButton = new JButton("비밀번호 변경");
					ChangePwButton.addActionListener(this);
					exitButton = new JButton("나가기");
					exitButton.addActionListener(this);

					editButtonPanel.add(ChangePwButton);
					editButtonPanel.add(new JLabel(""));	//위치 맟추기 위한 빈 레이블 추가
					editButtonPanel.add(exitButton);
				}
				editPanel.add(editTextPanel);
				editPanel.add(editButtonPanel);
			}
			ct.add(editPanel);	
		}

		public void actionPerformed(ActionEvent ae) {
			switch (ae.getActionCommand()) {	
			case "나가기" :
				MainPage.getEditSignoutUserButton().setEnabled(true);
				this.dispose();
				break;
				
			case "비밀번호 변경":
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
							MainPage.getEditSignoutUserButton().setEnabled(true);
							this.dispose();
						}
						else {
							MessageBox.printWarningMessageBox("입력하신 회원 정보가 일치하지 않습니다.");
							//텍스트필드 지우기
							idTextField.setText("");
							nameTextField.setText("");
							oldPwTextField.setText("");
							newPwTextField.setText("");
						}
					}
					else {
						MessageBox.printWarningMessageBox("입력하신 ID 정보가 존재하지 않습니다.");
						//텍스트필드 지우기
						idTextField.setText("");
						nameTextField.setText("");
						oldPwTextField.setText("");
						newPwTextField.setText("");
					}
				}
				catch(Exception e) {
					MessageBox.printErrorMessageBox("!오류 발생!");
					MessageBox.printErrorMessageBox("회원 정보 변경 창을 닫습니다.");
					MainPage.getEditSignoutUserButton().setEnabled(true);
					this.dispose();
				}
				break;
				
			default:
				break;
			}
		}

}