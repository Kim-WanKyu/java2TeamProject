package gui.page.optionPage.mainOption.book.insertBookPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.page.Page;
import gui.util.WhitePanel;

//수정 창
public class InsertBookPage extends Page{
	
	private InsertBookPageComponent insertBookPageComponent = new InsertBookPageComponent(this);
	
	//InsertBookPage 생성자
	public InsertBookPage(){
		setTitle("");			
		
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
		//InsertPanel = 회원 정보 변경 패널(비밀번호만) (InsertTextPanel 패널 포함)
		WhitePanel insertBookPanel = new WhitePanel(new FlowLayout());
		{
			WhitePanel insertTextPanel = new WhitePanel(new GridLayout(6,1));
			{
				WhitePanel insertBookNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookNamePanel.add(new JLabel("도서명 : "));
					insertBookNamePanel.add(insertBookPageComponent.getBookNameTextField());
				}
				WhitePanel insertBookAuthorPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookAuthorPanel.add(new JLabel("저자명 : "));
					insertBookAuthorPanel.add(insertBookPageComponent.getBookAuthorTextField());
				}
				WhitePanel insertBookPublisherPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookPublisherPanel.add(new JLabel("출판사 : "));
					insertBookPublisherPanel.add(insertBookPageComponent.getBookPublisherTextField());
				}
				WhitePanel insertBookCategoryPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookCategoryPanel.add(new JLabel("KDC : "));
					insertBookCategoryPanel.add(insertBookPageComponent.getBookCategoryTextField());
				}
				WhitePanel insertBookIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookIdPanel.add(new JLabel("book id : "));
					insertBookIdPanel.add(insertBookPageComponent.getBookIdTextField());
				}
				WhitePanel insertBookAvailableStockPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					insertBookAvailableStockPanel.add(new JLabel("수량 : "));

					insertBookAvailableStockPanel.add(insertBookPageComponent.getBookTotalCountSpinner());
				 	/* bookAvailableStockSpinner 크기조절 */
					insertBookPageComponent.getBookTotalCountSpinner().setPreferredSize(
							new Dimension(	insertBookPageComponent.getBookTotalCountSpinner().getPreferredSize().width * 2 + 18,
											insertBookPageComponent.getBookTotalCountSpinner().getPreferredSize().height) );
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
				insertButtonPanel.add(insertBookPageComponent.getInsertButton());
				insertButtonPanel.add(insertBookPageComponent.getExitButton());
			}
			insertBookPanel.add(insertTextPanel, BorderLayout.WEST);
			insertBookPanel.add(insertButtonPanel, BorderLayout.EAST);
		}
		ct.add(insertBookPanel);	
	}
	
	
}