package gui.page.optionPage.mainOption.book.editBookPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.page.Page;
import gui.util.WhitePanel;

//수정 창
public class EditBookPage extends Page{
	
	private EditBookPageComponent editBookPageComponent = new EditBookPageComponent(this);
	
	//EditBookPage 생성자
	public EditBookPage(){
		setTitle("");			
		
		this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setPage();
		
		packWindow();
	}
	
	public EditBookPage(String bookName, String author, String publisher, String category, String id, int totalCount) {
		editBookPageComponent.getBookNameTextField().setText(bookName);
		editBookPageComponent.getBookAuthorTextField().setText(author);
		editBookPageComponent.getBookPublisherTextField().setText(publisher);
		editBookPageComponent.getBookCategoryTextField().setText(category);
		editBookPageComponent.getBookIdTextField().setText(id);
		editBookPageComponent.getBookTotalCountSpinner().setValue(totalCount);
		
		this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		 
		setPage();
		
		packWindow();
		
	}

	@Override
	protected void setPage() {
		//editPanel = 회원 정보 변경 패널(비밀번호만) (editTextPanel 패널 포함)
		WhitePanel editBookPanel = new WhitePanel(new FlowLayout());
		{
			WhitePanel editTextPanel = new WhitePanel(new GridLayout(6,1));
			{
				WhitePanel editBookNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editBookNamePanel.add(new JLabel("도서명 : "));
					editBookNamePanel.add(editBookPageComponent.getBookNameTextField());
				}
				WhitePanel editBookAuthorPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editBookAuthorPanel.add(new JLabel("저자명 : "));
					editBookAuthorPanel.add(editBookPageComponent.getBookAuthorTextField());
				}
				WhitePanel editBookPublisherPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editBookPublisherPanel.add(new JLabel("출판사 : "));
					editBookPublisherPanel.add(editBookPageComponent.getBookPublisherTextField());
				}
				WhitePanel editBookCategoryPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editBookCategoryPanel.add(new JLabel("KDC : "));
					editBookCategoryPanel.add(editBookPageComponent.getBookCategoryTextField());
				}
				WhitePanel editBookIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editBookIdPanel.add(new JLabel("book id : "));
					editBookIdPanel.add(editBookPageComponent.getBookIdTextField());
				}
				WhitePanel editBookAvailableStockPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editBookAvailableStockPanel.add(new JLabel("수량 : "));

					editBookAvailableStockPanel.add(editBookPageComponent.getBookTotalCountSpinner());
				 	/* bookAvailableStockSpinner 크기조절 */
					editBookPageComponent.getBookTotalCountSpinner().setPreferredSize(
							new Dimension(	editBookPageComponent.getBookTotalCountSpinner().getPreferredSize().width * 2 + 18,
											editBookPageComponent.getBookTotalCountSpinner().getPreferredSize().height) );
				}
				editTextPanel.add(editBookNamePanel);
				editTextPanel.add(editBookAuthorPanel);
				editTextPanel.add(editBookPublisherPanel);
				editTextPanel.add(editBookCategoryPanel);
				editTextPanel.add(editBookIdPanel);
				editTextPanel.add(editBookAvailableStockPanel);
			}
			WhitePanel editButtonPanel = new WhitePanel(new GridLayout(2,1,0,50));
			{
				editButtonPanel.add(editBookPageComponent.getEditButton());
				editButtonPanel.add(editBookPageComponent.getExitButton());
			}
			editBookPanel.add(editTextPanel, BorderLayout.WEST);
			editBookPanel.add(editButtonPanel, BorderLayout.EAST);
		}
		ct.add(editBookPanel);	
	}
	
	
}