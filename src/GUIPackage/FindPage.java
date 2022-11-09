package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.Scanner;


public class FindPage  extends Page{
	
	//FindPage 생성자
		public FindPage(){
			String strTitle = super.getTitle() + "ID/PW찾기 화면";
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

			//test
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
