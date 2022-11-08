package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignUpPage extends Page{

	//RegisterPage 생성자
	public SignUpPage(){
		String strTitle = super.getTitle() + "등록화면";
		setTitle(strTitle);
		setSize(50,50);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		Container c = getContentPane();
//		c.setLayout(new FlowLayout());	//Layout은 FlowLayout으로 
//		
//		JPanel backgroundPanel = new JPanel();
//		
//		JPanel jp = new JPanel();
//		jp.setVisible(true);
//		jp.setLayout(new FlowLayout());

		
		JButton jb1 = new JButton("화원등록");
		jb1.setVisible(true);
		jb1.setSize(20,10);
		jb1.addActionListener(this);
			
//		jp.add(jb1);
//		
//		backgroundPanel.add(jp);
//		
		ct.add(jb1);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {

		case "등록하기":
			
			break;
		
		case "나가기":
			this.dispose();
			break;
			
		default:
			break;
		}
		
	}
}

class RegisterMessageBox extends MessageBox {

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {

		case "등록하기":

			break;
			
		case "나가기":
			dispose();
			break;
			
		default:
			break;
		}
	}
	
}