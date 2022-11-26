package GUIPackage.MainOptionPage.insertBookPackage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import GUIPackage.pagePackage.PageComponent;

public class InsertBookPageComponent extends PageComponent{
	//InsertBookPage의 모든 텍스트필드를 담는 ArrayList변수
		private ArrayList<JTextField> insertBookTextComponent;

		private JTextField bookNameTextField = new JTextField(15);		//도서명 텍스트필드
		private JTextField bookAuthorTextField = new JTextField(15);	//저자명 텍스트필드
		private JTextField bookPublisherTextField = new JTextField(15);	//출판사 텍스트필드
		private JTextField bookCategoryTextField = new JTextField(15);	//분류 텍스트필드	
		private JTextField bookIdTextField = new JTextField(15);		//id 텍스트필드

		private SpinnerModel numberModel = new SpinnerNumberModel(1, 1, 100, 1);	// 초기값, 최소값, 최대값, 증가값
		private JSpinner bookTotalCountSpinner = new JSpinner(numberModel);	//수량 (정수)스피너
		private JSpinner bookBorrowCountSpinner = new JSpinner(numberModel);//빌린 권 수 (정수)스피너

		private JButton insertButton = new JButton("등록");
		private JButton exitButton = new JButton("취소");

		public InsertBookPageComponent(JFrame frame) {
			super.frame = frame;
			
			insertButton.addActionListener(this);
			exitButton.addActionListener(this);
		}
		
		//get 메소드
		public JTextField getBookNameTextField() { return bookNameTextField; }
		public JTextField getBookAuthorTextField() { return bookAuthorTextField; }
		public JTextField getBookPublisherTextField() { return bookPublisherTextField; }
		public JTextField getBookCategoryTextField() { return bookCategoryTextField; }
		public JTextField getBookIdTextField() { return bookIdTextField; }
		
		public JSpinner getBookTotalCountSpinner() { return bookTotalCountSpinner; }
		public JSpinner getBookBorrowCountSpinner() { return bookBorrowCountSpinner; }
		
		public JButton getInsertButton() { return insertButton; }
		public JButton getExitButton() { return exitButton; }
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			switch(ae.getActionCommand()) {
			case "등록":
				onClickInsertButton();
				break;
			
			case "취소":
				onClickExitButton();
				break;
			}	
		}
		
		//TODO
		public void onClickInsertButton() {
			frame.dispose();
			//책 정보 수정 DB도 추가 테이블이나, 벡터도 추가
		}
		
		//TODO
		public void onClickExitButton() {
			//나가기
			frame.dispose();
		}
}
