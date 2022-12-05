package gui.page.optionPage.startOption.findPage;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import gui.page.PageComponent;
import gui.util.MessageBox;
import user.User;
import user.UserManager;
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
		String id = getIdTextField().getText(); //입력한 id
		String name = getNameTextField().getText(); //입력한 이름
		if(id.equals("") || name.equals("")) {
			MessageBox.printWarningMessageBox("ID 또는 이름이 \n입력되지 않았습니다.");			
		}
		else {
			String password = UserManager.getInstance().findPassword(id, name);
			if(!password.equals("")) {
				MessageBox.printInfoMessageBox("" + name + "님의 password는\n " + password +"입니다.");
			}
			else {
				MessageBox.printWarningMessageBox("입력된 정보가 존재하지 않습니다.");
			}
		}
	}
	
	
}
