package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MessageBox extends JFrame{
	//경고메세지 출력 메소드
	public static void printWarningMessageBox(String str) {
		JOptionPane.showMessageDialog(null, str, "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
	}
	public static void printInfoMessageBox(String str) {
		JOptionPane.showMessageDialog(null, str, "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
	}
}
