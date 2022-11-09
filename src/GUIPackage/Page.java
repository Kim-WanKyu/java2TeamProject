package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Page 클래스 (추상클래스)
public abstract class Page extends JFrame implements ActionListener{
	Container ct = getContentPane();

	//Page 생성자
	Page(){
		super("도서 관리 프로그램");
		//setResizable(false);							//창 크기 변경 불가
		setLocationRelativeTo(null);					//윈도우창 정가운데에 띄우기
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);	//창 끄기 버튼 비활성화	
		setVisible(true);								//창 보이게
		
		ct.setBackground(Color.WHITE); 					//컨테이너 배경 하얗게 처리

		ct.setLayout(new FlowLayout(FlowLayout.CENTER)); //Layout은 FlowLayout(가운데 정렬)으로
	}
	
	
}
