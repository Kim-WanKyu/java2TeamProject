package GUIPackage.StartPage.FindPackage;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import GUIPackage.StartPage.StartPackage.StartPageComponent;
import GUIPackage.pagePackage.PageComponent;
import GUIPackage.util.MessageBox;
//FindPage의 컴포넌트를 위한 클래스
public class FindPageComponent extends PageComponent {

	//StartPage의 모든 텍스트필드를 담는 ArrayList변수
	private ArrayList<JTextField> findTextComponent;
	
	//컴포넌트들
	private JTextField idTextField = new JTextField(15);	//id 텍스트필드
	private JTextField nameTextField = new JTextField(15);	//이름 텍스트필드
	private JButton findPWButton = new JButton("비밀번호 찾기");//비밀번호 찾기 버튼
	private JButton exitButton = new JButton("나가기");		//나가기 버튼
	
	//FindPageComponent생성자
	public FindPageComponent(JFrame frame)
	{
		super.frame= frame;
		
		findPWButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		findTextComponent = new ArrayList<>();
		findTextComponent.add(idTextField);
		findTextComponent.add(nameTextField);
	}
	
	//get 메소드들
	public JTextField getIdTextField() { return idTextField; }
	public JTextField getNameTextField() { return nameTextField; }
	public JButton getFindPWButton() { return findPWButton; }
	public JButton getExitButton() { return exitButton; }
	
	//버튼actionPerformed
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "비밀번호 찾기":
			onClickFindPwButton();
			break;
		
		case "나가기":
			frame.dispose();
			//TODO
			break;
		}
	}
	
	//비밀번호 찾기 작동 메소드
	public void onClickFindPwButton() {
		MessageBox.printInfoMessageBox("비밀번호 찾기");
	}
	
	
}
