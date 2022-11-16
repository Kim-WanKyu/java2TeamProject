package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class MainPage extends Page{	

	JComboBox categoryComboBox;	//분류 콤보박스
	JTextField searchTextField;	//검색 텍스트필드
	JButton searchButton;		//검색 버튼

	JButton changeButton;	//변경/탈퇴 버튼

	JTextField bookNameTextField;			//도서명 텍스트필드
	JTextField bookAuthorTextField;			//저자 텍스트필드
	JTextField bookPublisherTextField;		//출판사 텍스트필드
	JTextField bookCategoryTextField;		//분류 텍스트필드
	JTextField bookIdTextField;				//id 텍스트필드
	JTextField bookAvailableStockTextField;	//재고 텍스트필드	
	
	JButton quitButton;		//종료하기 버튼
	JButton logoutButton;	//로그아웃 버튼
	JButton borrowButton;	//대여하기 버튼
	
	//MainPage 생성자
	public MainPage(){
		//창 설정
		setTitle(super.getTitle() + "_메인 화면");
		
		this.addWindowListener(new WindowAdapter() {	//창 끄기 버튼 누를 시 이벤트 처리
			public void windowClosing(WindowEvent e) {
				new StartPage();
			}
		});
		
		setPage();
		/////////////////////////////////////////////////////
//		logoutButton = new JButton("로그아웃");
//		logoutButton.addActionListener(this);
//		ct.add(logoutButton);
		setSize(1000,600);
		////////////////////////////////////////////////////////
		//packWindow();
	}
	
	//setPage 메소드
	@Override
	void setPage() {
		
		//tabbedPane (book myBook 패널 포함)
		//bookPanel (bookLeftPanel 패널, bookRightPanel 패널 포함)
		//bookLeftPanel (bookTable 테이블, search 패널 포함)
		//bookRightPanel (bookInfoPanel 패널, 포함)
		
		//myBookPanel ()
		/////////////////////////////////

		//myBookPanel(myBookLeftPanel 패널, myBookRightPanel 패널 포함)
		WhitePanel myBookPanel = new WhitePanel();
		{
			JTable myBookTable = new JTable(20,5);
			{
				myBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				JScrollPane myBookScrollPane = new JScrollPane(myBookTable);
				myBookTable.setModel(new DefaultTableModel (new Object[][][] {}, new String[] { "셀1","셀2","셀3" })  {
					public boolean isCellEditable(int row, int column) { return false; }
				} );

				myBookPanel.add(myBookScrollPane);
			}
		}
		
		//bookPanel(bookLeftPanel 패널, bookRightPanel 패널 포함)
		WhitePanel bookPanel = new WhitePanel();
		{
			//bookLeftPanel 패널 (bookTable 테이블, searchPanel 패널 포함)
			WhitePanel bookLeftPanel = new WhitePanel(new BorderLayout());
			{
				JTable bookTable = new JTable(20,5);
				JScrollPane bookScrollPane = new JScrollPane(bookTable);
				{
					bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					bookTable.setModel(new DefaultTableModel (new Object[][][] {}, new String[] { "셀1","셀2","셀3" })  {
						public boolean isCellEditable(int row, int column) { return false; }
					} );
				}
				WhitePanel searchPanel = new WhitePanel(new BorderLayout());
				{
					//임시
					String[] categoryString = {"도서명", "저자명", "출판사", "분류"};
					categoryComboBox = new JComboBox(categoryString);
					
					searchTextField = new JTextField(20);
					
					searchButton = new JButton("검색");		//검색 버튼
					searchButton.addActionListener(this);

					searchPanel.add(categoryComboBox, BorderLayout.WEST);
					searchPanel.add(searchTextField, BorderLayout.CENTER);
					searchPanel.add(searchButton, BorderLayout.EAST);
				}
				bookLeftPanel.add(bookScrollPane, BorderLayout.CENTER);
				bookLeftPanel.add(searchPanel, BorderLayout.SOUTH);
			}

			/*오른쪽*/
			WhitePanel bookRightPanel = new WhitePanel(new BorderLayout());
			{
				JPanel bookInfoPanel = new JPanel(new BorderLayout());
				{
					JPanel bookTextPanel = new JPanel(new GridLayout(6,1));
					{
						JPanel bookNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							bookNamePanel.add(new JLabel("도서명 : "));
							bookNamePanel.add(bookNameTextField = new JTextField(10));
							bookNamePanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel bookAuthorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							bookAuthorPanel.add(new JLabel("저자명 : "));
							bookAuthorPanel.add(bookAuthorTextField = new JTextField(10));
							bookAuthorPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel bookPublisherPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							bookPublisherPanel.add(new JLabel("출판사 : "));
							bookPublisherPanel.add(bookPublisherTextField = new JTextField(10));
							bookPublisherPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel bookCategoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							bookCategoryPanel.add(new JLabel("분류 : "));
							bookCategoryPanel.add(bookCategoryTextField = new JTextField(10));
							bookCategoryPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel bookIdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							bookIdPanel.add(new JLabel("ID : "));
							bookIdPanel.add(bookIdTextField = new JTextField(10));
							bookIdPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel bookAvailableStockPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							bookAvailableStockPanel.add(new JLabel("재고 : "));
							bookAvailableStockPanel.add(bookAvailableStockTextField = new JTextField(10));
							bookAvailableStockPanel.setBackground(Color.LIGHT_GRAY);
						}				
						bookTextPanel.add(bookNamePanel);
						bookTextPanel.add(bookAuthorPanel);
						bookTextPanel.add(bookPublisherPanel);
						bookTextPanel.add(bookCategoryPanel);
						bookTextPanel.add(bookIdPanel);
						bookTextPanel.add(bookAvailableStockPanel);
						bookTextPanel.setBackground(Color.LIGHT_GRAY);
					}
					JPanel bookLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					bookLabelPanel.add(new JLabel("<<< 도서  정보 >>>"));
					bookLabelPanel.setBackground(new Color(255, 128, 128));
					
					bookInfoPanel.add(bookLabelPanel, BorderLayout.NORTH);
					bookInfoPanel.add(bookTextPanel, BorderLayout.SOUTH);
					bookInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 128, 128), 4));
				}
				bookRightPanel.add(bookInfoPanel);
			}
			bookPanel.add(bookLeftPanel);
			bookPanel.add(bookRightPanel);
		}
		
		JTabbedPane bookTabbedPane = new JTabbedPane();	//

		bookTabbedPane.addTab("전체목록", bookPanel);
		bookTabbedPane.addTab("내 도서", myBookPanel);
		
		//mainPagePanel = 메인화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel mainPagePanel = new WhitePanel();
		mainPagePanel.add(bookTabbedPane);
		//mainPagePanel.add(bookTabbedPane);
		//컨테이너 ct에 startPagePanel 부착
		ct.add(mainPagePanel);

		
		
		/////////////////////////////////////////////////////////////////////////
		
		boolean isAdmin = false;	//사용자 기본
		// userDB의 isAdmin 값을 추출하여 isAdmin에 넣음.
		if(isAdmin == true) {
			//관리자
			//setAdminPage();
		}
		else {
			//사용자
			//setUserPage();
		}
		
		//////////////////////////////////////////////////////////////////////////

	}
	
	//관리자 화면으로 세팅하는 메소드
	void setAdminPage(WhitePanel leftPanel, WhitePanel rightPanel) {
		
	}
	
	//사용자 화면으로 세팅하는 메소드
	void setUserPage(WhitePanel leftPanel, WhitePanel rightPanel) {
		
	}
	
	//버튼 클릭 시 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "로그아웃":
			dispose();		//메인화면 창 끄고
			new StartPage();//초기화면 창 생성
			break;
			
		case "종료하기":
			System.exit(0);
			break;
			
		case "대여하기":
			//대여
			break;
			
		case "변경/탈퇴":
			//변경/탈퇴
			break;
			
		default:
			break;
		}
	}
}

