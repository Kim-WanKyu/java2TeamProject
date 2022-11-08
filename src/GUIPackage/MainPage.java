package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.Scanner;



public class MainPage extends Page{
	//MainPage 생성자
	public MainPage(){
		String strTitle = super.getTitle() + "메인화면";
		setTitle(strTitle);
		
		setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
		
		Scanner stdin = new Scanner(System.in);
		
		
		JButton ExitButton = new JButton("나가기");
		ExitButton.setSize(20,20);
		ExitButton.setVisible(true);
		ExitButton.addActionListener(this);
		
		JButton Exit0Button = new JButton("종료");
		Exit0Button.setSize(20,20);
		Exit0Button.setVisible(true);
		Exit0Button.addActionListener(this);
		
		add(ExitButton);
		
		add(Exit0Button);
//		try {
//			String str = null;
//			str = stdin.next();
//
//			if (str.equals("종료"));
//				this.dispose();
//
//		}
//		catch(Exception e) {
//			
//		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		switch (ae.getActionCommand()) {	
		case "나가기":
			this.dispose();
			break;
			
		case "종료":
			System.exit(0);
			break;
			
		default:
			break;
		}
		
	}
}
