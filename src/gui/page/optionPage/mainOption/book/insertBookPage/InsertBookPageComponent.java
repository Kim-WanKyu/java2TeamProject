package gui.page.optionPage.mainOption.book.insertBookPage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import book.Book;
import book.BookManager;
import gui.page.PageComponent;
import gui.page.mainPage.MainPageComponent;
import gui.table.AllBookTable;
import gui.util.MessageBox;

public class InsertBookPageComponent extends PageComponent{
		//InsertBookPage의 모든 텍스트필드를 담는 ArrayList변수
		private ArrayList<JTextField> insertBookTextComponent;

		private AllBookTable allBookTable = new AllBookTable();
		
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
			Book newBook = new Book();
			if(getBookIdTextField().getText().toString().equals("")) {
				MessageBox.printWarningMessageBox("책 ID를 지정해주세요");
				return;
			}
			else if(getBookNameTextField().getText().toString().equals("")) {
				MessageBox.printWarningMessageBox("책 이름을 지정해주세요");
				return;
			}
			else if(getBookAuthorTextField().getText().toString().equals("")) {
				MessageBox.printWarningMessageBox("저자를 지정해주세요");
				return;
			}
			else if(getBookPublisherTextField().getText().toString().equals("")) {
				MessageBox.printWarningMessageBox("출판사 지정해주세요");
				return;
			}
			else if(getBookTotalCountSpinner().getValue().toString().equals("")) {
				MessageBox.printWarningMessageBox("수량을 지정해주세요");
				return;
			}
			//kdc가 빈칸이 아니고, && 정수부가 3자릿수이면 (소수점x)
			else if((!getBookCategoryTextField().getText().equals("") ) && (!getBookCategoryTextField().getText().matches("^\\d{3}")))
			{
				MessageBox.printWarningMessageBox("KDC가 올바르지 않습니다.");
				return;
			}
			System.out.println("정보 출력"+getBookPublisherTextField().getText() +'.');
			newBook.setName(getBookNameTextField().getText());
			newBook.setAuthor(getBookAuthorTextField().getText());
			newBook.setPublisher(getBookPublisherTextField().getText());
			newBook.setCategory(getBookCategoryTextField().getText());
			newBook.setId(getBookIdTextField().getText());
			newBook.setTotalCount(Integer.parseInt(getBookTotalCountSpinner().getValue().toString()));
			newBook.setBorrowCount(0);
			
			BookManager.getInstance().insertBook(newBook);
			
			Object [] newBookData = new Object[8];
			newBookData[0] = newBook.getName();
			newBookData[1] = newBook.getAuthor();
			newBookData[2] = newBook.getPublisher();
			newBookData[3] = newBook.getCategory();
			newBookData[4] = book.CategorizeKDC.getCategoryname(newBook.getCategory());
			newBookData[5] = newBook.getId();
			newBookData[6] = newBook.getTotalCount();
			newBookData[7] = newBook.getBorrowCount();
			
//			MainPageComponent.getAllBookTable().setModel(new DefaultTableModel(MainPageComponent.setAllBookData(),MainPageComponent.getAllBookColumnName()));
			AllBookTable.getAllBookTableModel().addRow(newBookData);
			AllBookTable.getAllBookTable().updateUI();
			

			frame.dispose();
		}
		
		//TODO
		public void onClickExitButton() {
			//나가기
			frame.dispose();
		}
}
