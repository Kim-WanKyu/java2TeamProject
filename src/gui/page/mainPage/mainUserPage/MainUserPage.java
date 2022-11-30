package gui.page.mainPage.mainUserPage;

import java.awt.*;
import javax.swing.*;

import gui.page.mainPage.MainPage;
import gui.page.mainPage.MainPageComponent;
import gui.util.WhitePanel;

public class MainUserPage extends MainPage {

	MainUserPageComponent mainUserPageComponent = new MainUserPageComponent(this);
	
	//MainUserPage 생성자
	public MainUserPage() {
		setTitle(super.getTitle() + "_사용자 화면");
		
		setPage();
		
		packWindow();
	}
	
	//화면 구성 메소드 setPage 메소드
	@Override
	protected void setPage() {
		WhitePanel mainUserPanel = new WhitePanel(new BorderLayout());
		{
			//userTab 사용자탭 = 화면 중단부분
			JTabbedPane userTab = mainUserPageComponent.getUserTab();
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
								bookNamePanel.add(mainUserPageComponent.getBookNameTextFields()[0]);
								bookNamePanel.setBackground(new Color(255,255,200));
							}
							//bookAuthorPanel 저자명
							WhitePanel bookAuthorPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookAuthorPanel.add(new JLabel("저자명 : "));
								bookAuthorPanel.add(mainUserPageComponent.getBookAuthorTextFields()[0]);
								bookAuthorPanel.setBackground(new Color(255,255,200));
							}
							//bookPublisherPanel 출판사
							WhitePanel bookPublisherPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookPublisherPanel.add(new JLabel("출판사 : "));
								bookPublisherPanel.add(mainUserPageComponent.getBookPublisherTextFields()[0]);
								bookPublisherPanel.setBackground(new Color(255,255,200));
							}
							//bookCategoryPanel KDC
							WhitePanel bookCategoryPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookCategoryPanel.add(new JLabel("KDC : "));
								bookCategoryPanel.add(mainUserPageComponent.getBookCategoryTextFields()[0]);
								bookCategoryPanel.setBackground(new Color(255,255,200));
							}
							//bookIdPanel BookID
							WhitePanel bookIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookIdPanel.add(new JLabel("BookID : "));
								bookIdPanel.add(mainUserPageComponent.getBookIdTextFields()[0]);
								bookIdPanel.setBackground(new Color(255,255,200));
							}
							//bookStockPanel 재고수량
							WhitePanel bookStockPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookStockPanel.add(new JLabel("재고 : "));
								bookStockPanel.add(mainUserPageComponent.getBookAvailableStockTextField());
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
						rightPanel.add(textPanel, BorderLayout.CENTER);
						rightPanel.add(mainUserPageComponent.getBorrowBookButton(), BorderLayout.SOUTH);
					}
					//전체도서 테이블 (왼쪽)
					JScrollPane allBookScrollPane = new JScrollPane(MainPageComponent.getAllBookTable());
					
					allBookPanel.add(allBookScrollPane);
					allBookPanel.add(rightPanel);
				}
				//2.내도서 / myBookPanel = 사용자 탭의 내도서로 들어갈 패널
				WhitePanel myBookPanel = new WhitePanel();
				{
					//myBookLeftPanel 패널 (bookTable 테이블, searchPanel 패널 포함)
					WhitePanel myBookLeftPanel = new WhitePanel(new BorderLayout());
					{
						JScrollPane myBookScrollPane = new JScrollPane(mainUserPageComponent.getMyBookTable());
						myBookLeftPanel.add(myBookScrollPane, BorderLayout.CENTER);
					}
					/*오른쪽*/
					//myBookRightPanel 패널
					WhitePanel myBookRightPanel = new WhitePanel(new BorderLayout());
					{//정보 출력 패널
						WhitePanel infoPanel = new WhitePanel(new GridLayout(9,1));
						{
							WhitePanel labelPanel = new WhitePanel();
							{
								labelPanel.add(new JLabel("<도서 정보>"));
								labelPanel.setBackground(new Color(00,240,240));
							}
							WhitePanel bookNamePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookNamePanel.add(new JLabel("도서명 : "));
								bookNamePanel.add(mainUserPageComponent.getBookNameTextFields()[1]);
								bookNamePanel.setBackground(new Color(127, 245, 127));
							}
							WhitePanel bookAuthorPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookAuthorPanel.add(new JLabel("저자명 : "));
								bookAuthorPanel.add(mainUserPageComponent.getBookAuthorTextFields()[1]);
								bookAuthorPanel.setBackground(new Color(127, 235, 127));
							}
							WhitePanel bookPublisherPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookPublisherPanel.add(new JLabel("출판사 : "));
								bookPublisherPanel.add(mainUserPageComponent.getBookPublisherTextFields()[1]);
								bookPublisherPanel.setBackground(new Color(127, 225, 127));
							}
							WhitePanel bookCategoryPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookCategoryPanel.add(new JLabel("분류 : "));
								bookCategoryPanel.add(mainUserPageComponent.getBookCategoryTextFields()[1]);
								bookCategoryPanel.setBackground(new Color(127, 215, 127));
							}
							WhitePanel bookIdPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								bookIdPanel.add(new JLabel("BookID : "));
								bookIdPanel.add(mainUserPageComponent.getBookIdTextFields()[1]);
								bookIdPanel.setBackground(new Color(127, 205, 127));
							}
							WhitePanel borrowDatePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								borrowDatePanel.add(new JLabel("대여일자 : "));
								borrowDatePanel.add(mainUserPageComponent.getBorrowDateTextField());
								borrowDatePanel.setBackground(new Color(127, 195, 127));
							}
							WhitePanel returnDatePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								returnDatePanel.add(new JLabel("반납기한 : "));
								returnDatePanel.add(mainUserPageComponent.getReturnDateTextField());
								returnDatePanel.setBackground(new Color(127, 185, 127));
							}
							WhitePanel isDelayDatePanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
							{
								isDelayDatePanel.add(new JLabel("연체여부 : "));
								isDelayDatePanel.add(mainUserPageComponent.getIsDelayTextField());
								isDelayDatePanel.setBackground(new Color(127, 175, 127));
							}
							infoPanel.add(labelPanel);
							infoPanel.add(bookNamePanel);
							infoPanel.add(bookAuthorPanel);
							infoPanel.add(bookPublisherPanel);
							infoPanel.add(bookCategoryPanel);
							infoPanel.add(bookIdPanel);
							infoPanel.add(borrowDatePanel);
							infoPanel.add(returnDatePanel);
							infoPanel.add(isDelayDatePanel);
						
							infoPanel.setBorder(BorderFactory.createLineBorder(new Color(127, 255, 127), 4));
							
						}
						myBookRightPanel.add(infoPanel);
						myBookRightPanel.add(mainUserPageComponent.getReturnBookButton(), BorderLayout.SOUTH);
					}
					myBookPanel.add(myBookLeftPanel);
					myBookPanel.add(myBookRightPanel);
				}
				userTab.add("전체도서", allBookPanel);
				userTab.add("내 도서", myBookPanel);				
			}
			mainUserPanel.add(super.makeUpPanel(), BorderLayout.NORTH);
			mainUserPanel.add(userTab, BorderLayout.CENTER);
			mainUserPanel.add(super.makeDownPanel(), BorderLayout.SOUTH);
		}
		ct.add(mainUserPanel);
	}
}




/*

case "대여하기":
	//대여
	/*if(유저가 대출가능한지 && 재고가 남았는지)
	 * 해당 도서 정보 유저에 저장하고 도서 재고-1
	 * else
	 * 	경고메세지 (대출 불가)
	 *
	 * break;
	 * 
	 * 
	 * + case "반납하기":
	 * 
	 * 	break;
	 * 
	 * */
