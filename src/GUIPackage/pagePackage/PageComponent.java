package GUIPackage.pagePackage;

import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

public abstract class PageComponent implements ActionListener{
	protected JFrame frame;
	
	//텍스트필드 지우는 메소드
	protected void eraseTextComponent(ArrayList<JTextField> textFieldComponents) {
		for (JTextField c : textFieldComponents) {
			c.setText(null);
		}
	}
}
