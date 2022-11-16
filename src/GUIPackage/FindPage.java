package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FindPage  extends Page{
	
	JTextField idTextField;			//id 입력 텍스트필드
	JTextField nameTextField;		//이름 입력 텍스트필드

	JButton findPWButton;			//비밀번호 찾기 버튼
	JButton exitButton;				//나가기 버튼
	
	JTabbedPane tableTabbedPane;
	
	//FindPage 생성자
		public FindPage(){
			setTitle(super.getTitle() + "_찾기 화면");			
			
			this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
				public void windowClosing(WindowEvent e) {
					StartPage.getFindButton().setEnabled(true);
					dispose();
				}
			});
			
			setPage();
			
			packWindow();
		}
		
		@Override
		void setPage() {
			//findPanel = ID/PW찾기 패널 (findIDPanel 패널, findPWPanel 패널 포함)
			WhitePanel findPanel = new WhitePanel(new BorderLayout());
			{
				WhitePanel findIDPanel = new WhitePanel(new BorderLayout());
				{
					findIDPanel.add(new JLabel("  ID는 회원님의 학번 또는 교번입니다."), BorderLayout.CENTER);
					findIDPanel.add(exitButton = new JButton("나가기"), BorderLayout.SOUTH);
					exitButton.addActionListener(this);
				}
				WhitePanel findPWPanel = new WhitePanel(new BorderLayout());
				{
					WhitePanel textPanel = new WhitePanel(new BorderLayout());
					{
						WhitePanel idPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
						{
							idPanel.add(new JLabel("ID(학번/교번) : "));
							idPanel.add(idTextField = new JTextField(15));
						}
						WhitePanel namePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
						{
							namePanel.add(new JLabel("이름 : "));
							namePanel.add(nameTextField = new JTextField(15));
						}
						textPanel.add(idPanel, BorderLayout.NORTH);
						textPanel.add(namePanel, BorderLayout.SOUTH);
					}
					WhitePanel buttonPanel = new WhitePanel(new BorderLayout());
					{
						findPWButton = new JButton("비밀번호 찾기");
						findPWButton.addActionListener(this);
						exitButton = new JButton("나가기");
						exitButton.addActionListener(this);

						buttonPanel.add(findPWButton, BorderLayout.WEST);
						buttonPanel.add(exitButton, BorderLayout.EAST);
					}
					findPWPanel.add(textPanel, BorderLayout.NORTH);
					findPWPanel.add(buttonPanel, BorderLayout.SOUTH);
				}
				findPanel.add(tableTabbedPane = new JTabbedPane());	//그룹 홀더
				tableTabbedPane.addTab("ID", findIDPanel);
				tableTabbedPane.addTab("PW", findPWPanel);
			}
			ct.add(findPanel);	
		}

		public void actionPerformed(ActionEvent ae) {
			switch (ae.getActionCommand()) {	
			case "나가기" :
				StartPage.getFindButton().setEnabled(true);
				this.dispose();
				break;
				
			case "비밀번호 찾기":
				//String findPw;
				//비밀번호 찾는 메소드(id, 이름) 추가 필요
				//DB에서 id확인후 해당 비밀번호 추출
				//if( (findPw = 비밀번호 찾는 메소드(id, 이름)) != null) //성공
				//{
				//	MessageBox.printInfoMessageBox("회원님의 PW는 " + findPw + "입니다.");
				//	StartPage.getFindButton().setEnabled(true);
				//	dispose();
				//}
				//else
				//{
				//	//메시지창(경고) 띄우는 메소드
				//	MessageBox.printWarningMessageBox("입력한 정보가 없거나, \nID 또는 이름이 일치하지 않습니다.");
				//}

				//임시
				String findPw = "abc123";
//				if( idTextField.getText().equals("유저db") && nameTextField.getText().equals("유저db"))
					MessageBox.printInfoMessageBox("회원님의 PW는\n \"" + findPw + "\"\n입니다.");
				
				break;
				
			default:
				break;
			}
		}

}