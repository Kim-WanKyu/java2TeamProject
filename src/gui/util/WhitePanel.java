<<<<<<<< HEAD:src/gui/WhitePanel.java
package GUIPackage;
========
package gui.util;
>>>>>>>> 511e71a (패키지 분류 및 이름 변경):src/gui/util/WhitePanel.java

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

//WhitePanel 패널 클래스
public class WhitePanel extends JPanel{
	
	//WhitePanel생성자
	WhitePanel() {
		this(new FlowLayout());
	}
	
	//WhitePanel생성자(레이아웃)
	WhitePanel(LayoutManager manager) {
		setLayout(manager);
		setBackground(Color.WHITE);
	}
	
}
