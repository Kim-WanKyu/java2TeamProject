package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Page 
public abstract class Page extends JFrame implements ActionListener{
	Container ct = getContentPane();

	Page(){
		super("도서 관리 프로그램");
		setSize(500,400);
		//setResizable(false);	//창 크기 변경 불가
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);	//창 끄기 버튼 비활성화

		ct.setLayout(new FlowLayout());
	}
	
	
	

}
