package gui.util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

//WhitePanel 패널 클래스
public class WhitePanel extends JPanel{
	
	//WhitePanel생성자
	public WhitePanel() {
		this(new FlowLayout());
	}
	
	//WhitePanel생성자(레이아웃)
	public WhitePanel(LayoutManager manager) {
		setLayout(manager);
		setBackground(Color.WHITE);
	}
}
