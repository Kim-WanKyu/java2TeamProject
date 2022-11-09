package GUIPackage;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.LayoutStyle;

//WhitePanel 패널 클래스
public class WhitePanel extends JPanel{
	
	//WhitePanel생성자
	WhitePanel(){
		setBackground(Color.WHITE);
	}
	
	//WhitePanel생성자(레이아웃)
	WhitePanel(LayoutManager manager){
		setLayout(manager);
		setBackground(Color.WHITE);
	}
	
}
