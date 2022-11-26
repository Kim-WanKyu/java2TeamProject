package GUIPackage.MainPage.mainAdminPackage;

import java.awt.*;
import javax.swing.*;

import GUIPackage.MainPage.mainPackage.MainPage;
import GUIPackage.MainPage.mainPackage.MainPageComponent;
import GUIPackage.util.WhitePanel;

public class MainAdminPage extends MainPage {
	
	MainAdminPageComponent mainAdminPageComponent = new MainAdminPageComponent(this);
	
	//MainAdminPage 생성자
	public MainAdminPage(){
		setTitle(super.getTitle() + "_관리자 화면");
		
		setPage();
		
		packWindow();
	}
	
	//화면 구성 메소드 setPage 메소드
	@Override
	protected void setPage() {
		//mainAdminPanel = 관리자메인화면 패널 (메인상단 중단(), 메인하단 포함)
		WhitePanel mainAdminPanel = new WhitePanel(new BorderLayout());
		{
			//adminTab 관리자탭 = 화면 중단부분
			JTabbedPane adminTab = mainAdminPageComponent.getAdminTab();
			{
				//1.전체도서 / allBookPanel = 관리자 탭의 전체도서로 들어갈 패널
				WhitePanel allBookPanel = new WhitePanel();
				{
					//rightPanel = 오른쪽 패널
					WhitePanel rightPanel = new WhitePanel(new BorderLayout());
					{
						//textPanel = 정보 출력 패널
						WhitePanel textPanel = new WhitePanel(new GridLayout(7,1));
						{
							WhitePanel infoLabelPanel = new WhitePanel(new FlowLayout(FlowLayout.CENTER));
							{
								infoLabelPanel.add(new JLabel("<<< 도서  정보 >>>"));
								infoLabelPanel.setBackground(new Color(224,224,150));
							}
							//bookNamePanel 도서명
							WhitePanel bookNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookNamePanel.add(new JLabel("도서명 : "));
								bookNamePanel.add(mainAdminPageComponent.getBookNameTextFields()[3]);
								bookNamePanel.setBackground(new Color(255,255,200));
							}
							//bookAuthorPanel 저자명
							WhitePanel bookAuthorPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookAuthorPanel.add(new JLabel("저자명 : "));
								bookAuthorPanel.add(mainAdminPageComponent.getBookAuthorTextFields()[3]);
								bookAuthorPanel.setBackground(new Color(255,255,200));
							}
							//bookPublisherPanel 출판사
							WhitePanel bookPublisherPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookPublisherPanel.add(new JLabel("출판사 : "));
								bookPublisherPanel.add(mainAdminPageComponent.getBookPublisherTextFields()[3]);
								bookPublisherPanel.setBackground(new Color(255,255,200));
							}
							//bookCategoryPanel KDC
							WhitePanel bookCategoryPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookCategoryPanel.add(new JLabel("KDC : "));
								bookCategoryPanel.add(mainAdminPageComponent.getBookCategoryTextFields()[3]);
								bookCategoryPanel.setBackground(new Color(255,255,200));
							}
							//bookIdPanel BookID
							WhitePanel bookIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookIdPanel.add(new JLabel("BookID : "));
								bookIdPanel.add(mainAdminPageComponent.getBookIdTextFields()[3]);
								bookIdPanel.setBackground(new Color(255,255,200));
							}
							//bookStockPanel 재고수량
							WhitePanel bookStockPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookStockPanel.add(new JLabel("재고 : "));
								bookStockPanel.add(mainAdminPageComponent.getBookAvailableStockTextField());
								bookStockPanel.setBackground(new Color(255,255,200));
							}
							textPanel.add(infoLabelPanel);
							textPanel.add(bookNamePanel);
							textPanel.add(bookAuthorPanel);
							textPanel.add(bookPublisherPanel);
							textPanel.add(bookCategoryPanel);
							textPanel.add(bookIdPanel);
							textPanel.add(bookStockPanel);
							textPanel.setBorder(BorderFactory.createLineBorder(new Color(224,224,150), 4));
						}
						//optionButtonPanel = 버튼 패널
						WhitePanel optionButtonPanel = new WhitePanel();
						{
							optionButtonPanel.add(mainAdminPageComponent.getInsertBookButton());
							optionButtonPanel.add(mainAdminPageComponent.getEditBookButton());
							optionButtonPanel.add(mainAdminPageComponent.getDeleteBookButton());
						}
						rightPanel.add(textPanel, BorderLayout.CENTER);
						rightPanel.add(optionButtonPanel, BorderLayout.SOUTH);
					}
					//전체도서 테이블 (왼쪽)
					JScrollPane allBookScrollPane = new JScrollPane(MainPageComponent.getAllBookTable());
					
					allBookPanel.add(allBookScrollPane);
					allBookPanel.add(rightPanel);
				}
				//2.전체회원 / allUserPanel = 관리자 탭의 전체회원으로 들어갈 패널
				WhitePanel allUserPanel = new WhitePanel();
				{
					//allUserLeftPanel 패널 (bookTable 테이블, searchPanel 패널 포함)
					WhitePanel allUserLeftPanel = new WhitePanel(new BorderLayout());
					{
						JScrollPane allUserScrollPane = new JScrollPane(MainPageComponent.getAllUserTable());
						allUserLeftPanel.add(allUserScrollPane, BorderLayout.CENTER);
						allUserScrollPane.setPreferredSize(
								new Dimension(	allUserScrollPane.getPreferredSize().width/2,
												allUserScrollPane.getPreferredSize().height	));
					}
					/*오른쪽*/
					//allUserRightPanel 패널
					WhitePanel allUserRightPanel = new WhitePanel(new BorderLayout());
					{//정보 출력 패널
						WhitePanel infoPanel = new WhitePanel(new GridLayout(1,3));
						{
							//textPanels = 각 정보 출력 패널
							WhitePanel[] textPanels = new WhitePanel[3];
							{
								for(int i=0; i<3;i++) //(0~2)3번 반복
								{
									textPanels[i] = new WhitePanel(new GridLayout(9,1));
									{
										WhitePanel labelPanel = new WhitePanel();
										{
											labelPanel.add(new JLabel("<도서 정보 " + (i+1)+ ">"));
											labelPanel.setBackground(new Color(127, 255, 127));
										}
										WhitePanel bookNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
										{
											bookNamePanel.add(new JLabel("도서명 : "));
											bookNamePanel.add(mainAdminPageComponent.getBookNameTextFields()[i]);
											bookNamePanel.setBackground(new Color(127, 245, 127));
										}
										WhitePanel bookAuthorPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
										{
											bookAuthorPanel.add(new JLabel("저자명 : "));
											bookAuthorPanel.add(mainAdminPageComponent.getBookAuthorTextFields()[i]);
											bookAuthorPanel.setBackground(new Color(127, 235, 127));
										}
										WhitePanel bookPublisherPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
										{
											bookPublisherPanel.add(new JLabel("출판사 : "));
											bookPublisherPanel.add(mainAdminPageComponent.getBookPublisherTextFields()[i]);
											bookPublisherPanel.setBackground(new Color(127, 225, 127));
										}
										WhitePanel bookCategoryPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
										{
											bookCategoryPanel.add(new JLabel("분류 : "));
											bookCategoryPanel.add(mainAdminPageComponent.getBookCategoryTextFields()[i]);
											bookCategoryPanel.setBackground(new Color(127, 215, 127));
										}
										WhitePanel bookIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
										{
											bookIdPanel.add(new JLabel("BookID : "));
											bookIdPanel.add(mainAdminPageComponent.getBookIdTextFields()[i]);
											bookIdPanel.setBackground(new Color(127, 205, 127));
										}
										WhitePanel borrowDatePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
										{
											borrowDatePanel.add(new JLabel("대여일자 : "));
											borrowDatePanel.add(mainAdminPageComponent.getBorrowDateTextFields()[i]);
											borrowDatePanel.setBackground(new Color(127, 195, 127));
										}
										WhitePanel returnDatePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
										{
											returnDatePanel.add(new JLabel("반납기한 : "));
											returnDatePanel.add(mainAdminPageComponent.getReturnDateTextFields()[i]);
											returnDatePanel.setBackground(new Color(127, 185, 127));
										}
										WhitePanel isDelayDatePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
										{
											isDelayDatePanel.add(new JLabel("연체여부 : "));
											isDelayDatePanel.add(mainAdminPageComponent.getIsDelayTextFields()[i]);
											isDelayDatePanel.setBackground(new Color(127, 175, 127));
										}
										textPanels[i].add(labelPanel);
										textPanels[i].add(bookNamePanel);
										textPanels[i].add(bookAuthorPanel);
										textPanels[i].add(bookPublisherPanel);
										textPanels[i].add(bookCategoryPanel);
										textPanels[i].add(bookIdPanel);
										textPanels[i].add(borrowDatePanel);
										textPanels[i].add(returnDatePanel);
										textPanels[i].add(isDelayDatePanel);
									}
									textPanels[i].setBorder(BorderFactory.createLineBorder(new Color(127, 255, 127), 1));	//테두리그리기
									infoPanel.add(textPanels[i]);
								}
							}
							infoPanel.setBorder(BorderFactory.createLineBorder(new Color(127, 255, 127), 4));
						}
						allUserRightPanel.add(infoPanel);
					}
					allUserPanel.add(allUserLeftPanel);
					allUserPanel.add(allUserRightPanel);
					///////////////////////////////////
				}
				adminTab.add("전체도서", allBookPanel);
				adminTab.add("전체회원", allUserPanel);
			}
			mainAdminPanel.add(super.makeUpPanel(), BorderLayout.NORTH);	//상단 패널
			mainAdminPanel.add(adminTab, BorderLayout.CENTER);				//중단 패널
			mainAdminPanel.add(super.makeDownPanel(), BorderLayout.SOUTH);	//하단 패널
		}
		ct.add(mainAdminPanel);		
	}
}

