package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.Scanner;


public class FindPage  extends Page{
	
	JButton exitButton = new JButton("나가기");
	
	//FindPage 생성자
		public FindPage(){
			setTitle(super.getTitle() + "_찾기 화면");			
			setLocationRelativeTo(null);
			this.setVisible(true);
			
			Scanner stdin = new Scanner(System.in);
			
			
			exitButton.setSize(20,20);
			exitButton.setVisible(true);
			exitButton.addActionListener(this);
			
			add(exitButton);
			

			setSize(1000,200);
			//test
			//test2
		}
		
		public void actionPerformed(ActionEvent ae) {
			
			switch (ae.getActionCommand()) {	
			case "나가기":
				StartPage.getFindButton().setEnabled(true);
				this.dispose();
				break;
				
			default:
				break;
			}
			
		}

		@Override
		void setPage() {
			// TODO Auto-generated method stub
			
		}
}
