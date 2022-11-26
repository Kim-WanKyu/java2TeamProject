package GUIPackage.MainOptionPage.editSignoutUserPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import GUIPackage.pagePackage.Page;
import GUIPackage.util.WhitePanel;

public class EditSignoutUserPage extends Page{

	EditSignoutUserPageComponent editSignoutUserPageComponent = new EditSignoutUserPageComponent(this);
	
	//EditSignoutUserPage 생성자
	public EditSignoutUserPage(){
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
		WhitePanel buttonPanel = new WhitePanel(new GridLayout(4,1,100,5));
		{
			buttonPanel.add(new JLabel(" 원하시는 메뉴를 클릭하세요. "));
			buttonPanel.add(editSignoutUserPageComponent.getEditButton());
			buttonPanel.add(editSignoutUserPageComponent.getSignoutButton());
			buttonPanel.add(editSignoutUserPageComponent.getCancelButton());
		}
		ct.add(buttonPanel);
	}
}