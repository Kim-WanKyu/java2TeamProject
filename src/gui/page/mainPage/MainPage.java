package gui.page.mainPage;

import java.awt.*;
import javax.swing.*;

import gui.page.Page;
import gui.util.WhitePanel;

public abstract class MainPage extends Page{	
	
	private MainPageComponent mainPageComponent = new MainPageComponent(this);
	
	//MainPageComponent를 리턴하는 get메소드
	public MainPageComponent getMainPageComponent() { return mainPageComponent; }
	
	//상단 패널 생성하는 메소드
 	public JPanel makeUpPanel() {
		//upPanel 패널 = 메인 화면 상단 영역
		WhitePanel upPanel = new WhitePanel(new BorderLayout());
		{
			//editSignoutUserPanel 패널 = 메인 화면 상단 오른쪽 버튼 영역
			WhitePanel editSignoutUserPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
			{
				editSignoutUserPanel.add(mainPageComponent.getEditSignoutButton());
			}
			upPanel.add(editSignoutUserPanel, BorderLayout.EAST);
		}
		return upPanel;
	}	
	
 	//하단 패널 생성하는 메소드
 	public JPanel makeDownPanel() {
		//downPanel 패널 = 메인 화면 하단 영역
		WhitePanel downPanel = new WhitePanel(new BorderLayout());
		{
			//searchPanel 패널 = 메인 화면 하단 왼쪽 검색 영역
			WhitePanel searchPanel = new WhitePanel(new FlowLayout(FlowLayout.LEFT));
			{
				searchPanel.add(mainPageComponent.getSearchCategoryComboBox());
				searchPanel.add(mainPageComponent.getSearchTextField());
				searchPanel.add(mainPageComponent.getSearchButton());
			}
			//optionButtonPanel 패널 = 메인 화면 하단 오른쪽 버튼 영역
			WhitePanel optionButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
			{
				optionButtonPanel.add(mainPageComponent.getLogoutButton());
				optionButtonPanel.add(mainPageComponent.getQuitButton());
			}
			downPanel.add(searchPanel, BorderLayout.WEST);
			downPanel.add(optionButtonPanel, BorderLayout.EAST);
		}
		return downPanel;
	}
 	
	//setPage 메소드
	@Override
	protected void setPage() {}
}

