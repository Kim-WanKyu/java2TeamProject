package gui.page.optionPage.mainOption.book.editBookPage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;

import gui.page.PageComponent;

//EditBookPage의 컴포넌트를 위한 클래스
public class EditBookPageComponent extends PageComponent {

	//EditBookPage의 모든 텍스트필드를 담는 ArrayList변수
	private ArrayList<JTextField> editBookTextComponent;

	private JTextField bookNameTextField = new JTextField(15);		//도서명 텍스트필드
	private JTextField bookAuthorTextField = new JTextField(15);	//저자명 텍스트필드
	private JTextField bookPublisherTextField = new JTextField(15);	//출판사 텍스트필드
	private JTextField bookCategoryTextField = new JTextField(15);	//분류 텍스트필드	
	private JTextField bookIdTextField = new JTextField(15);		//id 텍스트필드

	private SpinnerModel numberModel = new SpinnerNumberModel(1, 1, 100, 1);	// 초기값, 최소값, 최대값, 증가값
	private JSpinner bookTotalCountSpinner = new JSpinner(numberModel);	//수량 (정수)스피너
	private JSpinner bookBorrowCountSpinner = new JSpinner(numberModel);//빌린 권 수 (정수)스피너

	private JButton editButton = new JButton("수정");
	private JButton exitButton = new JButton("취소");


	public EditBookPageComponent(JFrame frame) {
		super.frame = frame;
		
		editButton.addActionListener(this);
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
	
	//public JButton getInsertButton() { return insertButton; }
	public JButton getEditButton() { return editButton; }
	public JButton getExitButton() { return exitButton; }
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "수정":
			onClickEditButton();
			break;
		
		case "취소":
			onClickExitButton();
			break;
		}	
	}
	
	//TODO
	public void onClickEditButton() {
		frame.dispose();
		//책 정보 수정 DB도 수정 테이블이나, 벡터도 수정
	}
	
	//TODO
	public void onClickExitButton() {
		//나가기
		frame.dispose();
	}
}
