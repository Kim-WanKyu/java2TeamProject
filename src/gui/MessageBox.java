<<<<<<<< HEAD:src/gui/MessageBox.java
package GUIPackage;
========
package gui.util;
>>>>>>>> 511e71a (패키지 분류 및 이름 변경):src/gui/util/MessageBox.java

import javax.swing.*;

public class MessageBox extends JFrame{
	//에러메세지 출력 메소드
	public static void printErrorMessageBox(String str) {
		JOptionPane.showMessageDialog(null, str, "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	}
	//경고메세지 출력 메소드
	public static void printWarningMessageBox(String str) {
		JOptionPane.showMessageDialog(null, str, "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
	}
	//정보메세지 출력 메소드
	public static void printInfoMessageBox(String str) {
		JOptionPane.showMessageDialog(null, str, "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
	}
}
