package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InsertBookPage extends Page{
	
	private JTextField bookNameTextField;		//도서명 텍스트필드
	private JTextField bookAuthorTextField;		//저자명 텍스트필드
	private JTextField bookPublisherTextField;	//출판사 텍스트필드
	private JTextField bookCategoryTextField;	//분류 텍스트필드
	private JTextField bookIdTextField;			//id 텍스트필드
	private JSpinner bookQuantitySpinner;		//수량 (정수)스피너
	
	private JButton insertButton;	//등록 버튼
	private JButton exitButton;		//취소 버튼
	
	//insertBookPage 생성자
	public InsertBookPage(){
		setTitle("등록 화면");			
		
		this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
			public void windowClosing(WindowEvent e) {
				MainPage.getInsertBookButton().setEnabled(true);
				dispose();
			}
		});
		
		setPage();
		
		packWindow();
	}

	@Override
	void setPage() {
		
		//editPanel = 회원 정보 변경 패널(비밀번호만) (editTextPanel 패널 포함)
		WhitePanel insertBookPanel = new WhitePanel(new FlowLayout());
		{
			WhitePanel insertTextPanel = new WhitePanel(new GridLayout(6,1));
			{
				WhitePanel insertBookNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookNamePanel.add(new JLabel("도서명 : "));
					insertBookNamePanel.add(bookNameTextField = new JTextField(12));
				}
				WhitePanel insertBookAuthorPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookAuthorPanel.add(new JLabel("저자명 : "));
					insertBookAuthorPanel.add(bookAuthorTextField = new JTextField(12));
				}
				WhitePanel insertBookPublisherPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookPublisherPanel.add(new JLabel("출판사 : "));
					insertBookPublisherPanel.add(bookPublisherTextField = new JTextField(12));
				}
				WhitePanel insertBookCategoryPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookCategoryPanel.add(new JLabel("분류 : "));
					insertBookCategoryPanel.add(bookCategoryTextField = new JTextField(12));
				}
				WhitePanel insertBookIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookIdPanel.add(new JLabel("book id : "));
					insertBookIdPanel.add(bookIdTextField = new JTextField(12));
				}
				WhitePanel insertBookAvailableStockPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookAvailableStockPanel.add(new JLabel("수량 : "));
					
					SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);// 초기값, 최소값, 최대값, 증가값
					insertBookAvailableStockPanel.add(bookQuantitySpinner = new JSpinner(model));
				 	/* bookAvailableStockSpinner 크기조절 */
					bookQuantitySpinner.setPreferredSize(
							new Dimension(bookQuantitySpinner.getPreferredSize().width * 2 + 18,
										  bookQuantitySpinner.getPreferredSize().height) );
				}
				insertTextPanel.add(insertBookNamePanel);
				insertTextPanel.add(insertBookAuthorPanel);
				insertTextPanel.add(insertBookPublisherPanel);
				insertTextPanel.add(insertBookCategoryPanel);
				insertTextPanel.add(insertBookIdPanel);
				insertTextPanel.add(insertBookAvailableStockPanel);
			}
			WhitePanel insertButtonPanel = new WhitePanel(new GridLayout(2,1,0,50));
			{
				insertButton = new JButton("등록");
				insertButton.addActionListener(this);
				exitButton = new JButton("취소");
				exitButton.addActionListener(this);

				insertButtonPanel.add(insertButton);
				insertButtonPanel.add(exitButton);
			}
			insertBookPanel.add(insertTextPanel, BorderLayout.WEST);
			insertBookPanel.add(insertButtonPanel, BorderLayout.EAST);
		}
		ct.add(insertBookPanel);	
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "등록":
			String name = bookNameTextField.getText();				//도서명
			String author = bookAuthorTextField.getText();			//저자명
			String publisher = bookPublisherTextField.getText();	//출판사
			String category = bookCategoryTextField.getText();		//분류
			String bookId = bookIdTextField.getText();				//id
			int bookTotalCount = Integer.parseInt(bookQuantitySpinner.getValue().toString());	//수량 / 총 권 수
			int bookBorrowCount = 0;	//빌린 권수

			//책 이름과 ID가 비어 있지 않아야
			if(name != null && bookId != null) {
				// DB에 책 추가하는 메소드 실행
				// (booklist? book정보 vector에도) 추가
				// 테이블에도 추가( 테이블 추가 메소드 : Jtable./**/insertRow/**/(b.length, new Object[]{"d1","d2","d3"});)
				
				//등록 성공 시 if(등록 성공)
				MessageBox.printInfoMessageBox("등록 성공");
				MainPage.getInsertBookButton().setEnabled(true);
				dispose();
				//else //(등록 실패)
				//	MessageBox.printWarningMessageBox("등록 실패");
			}
			else {
				MessageBox.printWarningMessageBox("입력한 값이 적절하지 않습니다.");
			}
			
			break;
			
		case "취소":
			MainPage.getInsertBookButton().setEnabled(true);
			dispose();
			break;
			
		default:
			break;
		}
	}
}