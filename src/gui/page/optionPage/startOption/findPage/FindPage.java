package gui.page.optionPage.startOption.findPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.page.Page;
import gui.page.startPage.StartPageComponent;
import gui.util.WhitePanel;

public class FindPage extends Page{
	
	private FindPageComponent findPageComponent = new FindPageComponent(this);
	
	//FindPage 생성자
	public FindPage(){
		setTitle(super.getTitle() + "_찾기 화면");			
		
		this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
			public void windowClosing(WindowEvent e) {
				StartPageComponent startPageComponent = new StartPageComponent();
				startPageComponent.getFindButton().setEnabled(false);
				dispose();
			}
		});
		
		setPage();		//페이지 설정
		
		packWindow();	//창 크기 및 위치 맟춤
	}
	
	//화면 구성 메소드 setPage 메소드
	@Override
	protected void setPage() {
		//findPanel = ID/PW찾기 패널 (findTab 그룹홀더(탭), exitButtonPanel 패널 포함)
		WhitePanel findPanel = new WhitePanel(new BorderLayout());
		{
			//findTab = 그룹 홀더(탭) (ID탭, PW탭 포함)
			JTabbedPane findTab = new JTabbedPane();
			{
				//findIDTabPanel = ID탭으로 추가할 패널 (msgLabel 레이블 포함)
				WhitePanel findIDTabPanel = new WhitePanel(new BorderLayout());
				{
					JLabel msgLabel = new JLabel("ID는 회원님의 학번 또는 교번입니다.");	//msgLabel = 메세지레이블
					{	//레이블 가운데 맟춤
						msgLabel.setVerticalAlignment(SwingConstants.CENTER);
						msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
					}
					findIDTabPanel.add(msgLabel, BorderLayout.CENTER);
				}
				//findPWTabPanel = PW탭으로 추가할 패널 (textPanel, buttonPanel 패널 포함)
				WhitePanel findPWPanel = new WhitePanel(new BorderLayout());
				{
					//textPanel패널 (idPanel 패널, pwPanel 패널 포함)
					WhitePanel textPanel = new WhitePanel(new BorderLayout());
					{
						//idPanel패널 (("ID(학번/교번) : ") 레이블, idTextField 텍스트필드 포함)
						WhitePanel idPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
						{
							idPanel.add(new JLabel("ID(학번/교번) : "));
							idPanel.add(findPageComponent.getIdTextField());
						}
						//namePanel패널 (("이름 : ") 레이블, nameTextField 텍스트필드 포함)
						WhitePanel namePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
						{
							namePanel.add(new JLabel("이름 : "));
							namePanel.add(findPageComponent.getNameTextField());
						}
						textPanel.add(idPanel, BorderLayout.NORTH);
						textPanel.add(namePanel, BorderLayout.SOUTH);
					}
					//buttonPanel패널 (findPWButton 버튼 포함)
					WhitePanel buttonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
					{
						buttonPanel.add(findPageComponent.getFindPWButton());
					}
					findPWPanel.add(textPanel, BorderLayout.NORTH);
					findPWPanel.add(buttonPanel, BorderLayout.SOUTH);
				}
				findTab.addTab("ID", findIDTabPanel);
				findTab.addTab("PW", findPWPanel);
			}
			//exitButtonPanel패널 (exitButton 버튼 포함)
			WhitePanel exitButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
			{
				exitButtonPanel.add(findPageComponent.getExitButton());
			}
			findPanel.add(findTab, BorderLayout.CENTER);
			findPanel.add(exitButtonPanel, BorderLayout.SOUTH);	
		}
		//컨테이너 ct에 startPagePanel 부착
		ct.add(findPanel);	
	}
}