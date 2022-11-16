package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class MainPage extends Page{	

	JTabbedPane bookTabbedPane;	//탭
	
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
		
		packWindow();
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
			JTable myBookTable = new JTable(1,5);
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
				JTable bookTable = new JTable(1,5);
				JScrollPane bookScrollPane = new JScrollPane(bookTable);
//				{
//					bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//					bookTable.setModel(new DefaultTableModel (new Object[][][] {}, new String[] { "셀1","셀2","셀3","셀4","셀5" })  {
//						public boolean isCellEditable(int row, int column) { return false; }
//					} );
//					
//				}
				///////////////////////////////////////////////////////////
				{
				       String []a = {"a","b","c"};
				        String [][]b = {{"a1","a2","a3"},
				                        {"b1","b2","b3"},
				                        {"c1","c2","c3"}};
				        
				        //1. 모델과 데이터를 연결
				        DefaultTableModel model = new DefaultTableModel(b,a);
				        
				        //2. Model을 매개변수로 설정, new JTable(b,a)도 가능하지만 
				        //삽입 삭제를 하기 위해 해당 방법을 사용합니다
				        bookTable = new JTable(model); //
				        
				        //3. 결과적으로는 JScrollPane를 추가합니다.
				        JScrollPane sc = new JScrollPane(bookTable);
				        
				        //4. 컴포넌트에  Table 추가
				        add(sc);
				        
				        //테이블에 데이터 추가하기
				        //원본데이터를 건들지 않고 table의 매개변수인 model에 있는 데이터를 변경합니다
				        DefaultTableModel m =
				                (DefaultTableModel)bookTable.getModel();
				        //모델에 데이터 추가 , 1번째 출에 새로운 데이터를 추가합니다
				        m.insertRow(b.length, new Object[]{"d1","d2","d3"});
				        //추가를 마치고 데이터 갱신을 알립니다.
				        bookTable.updateUI();
				    
				        
				        
//				        //------- 그 외 메소드들 ---------
//				        //테이블의 데이터를 가져오는 메소드
//				        System.out.println(bookTable.getValueAt(1,1));
//				        //테이블의 데이터를 바꾸는 메소드
//				        bookTable.setValueAt("picachu",2,2);
//				        //테이블 row 갯수 가져오기
//				        System.out.println(bookTable.getRowCount());
//				        //테이블 colum 갯수 가져오기
//				        System.out.println(bookTable.getColumnCount());
//				        //테이블 Colum 이름 가져오기
//				        System.out.println(bookTable.getColumnName(0));
				}
				bookLeftPanel.add(bookScrollPane, BorderLayout.CENTER);
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
				borrowButton = new JButton("대여하기");
				borrowButton.addActionListener(this);
					
				bookRightPanel.add(bookInfoPanel, BorderLayout.NORTH);
				bookRightPanel.add(borrowButton, BorderLayout.SOUTH);
			}
			bookPanel.add(bookLeftPanel);
			bookPanel.add(bookRightPanel);
		}
		
		bookTabbedPane = new JTabbedPane();	//
		
		//
		
		bookTabbedPane.addTab("전체목록", bookPanel);
		bookTabbedPane.addTab("내 도서", myBookPanel);
		
		WhitePanel downButtonPanel = new WhitePanel(new BorderLayout());
		{
			WhitePanel searchPanel = new WhitePanel(new FlowLayout(FlowLayout.LEFT));
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
			WhitePanel buttonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
			{
				logoutButton = new JButton("로그아웃");
				logoutButton.addActionListener(this);
				
				quitButton = new JButton("종료하기");
				quitButton.addActionListener(this);
				
				buttonPanel.add(logoutButton, BorderLayout.WEST);
				buttonPanel.add(quitButton, BorderLayout.EAST);
			}
			downButtonPanel.add(searchPanel, BorderLayout.WEST);
			downButtonPanel.add(buttonPanel, BorderLayout.EAST);
		}
		
		//mainPagePanel = 메인화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel mainPagePanel = new WhitePanel(new BorderLayout());
		mainPagePanel.add(bookTabbedPane, BorderLayout.CENTER);
		mainPagePanel.add(downButtonPanel, BorderLayout.SOUTH);
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
			for(int i=0; i<getOwnerlessWindows().length;i++)
				getOwnerlessWindows()[i].dispose();	//켜져있는 모든 창 끄고
			new StartPage();//초기화면 창 생성
			break;
			
		case "종료하기":
			System.exit(0);
			break;
			
		case "대여하기":
			//대여
			/*if(유저가 대출가능한지 && 재고가 남았는지)
			 * 해당 도서 정보 유저에 저장하고 도서 재고-1
			 * else
			 * 	경고메세지 (대출 불가)
			 */
			break;
			
		case "변경/탈퇴":
			//변경/탈퇴
			/*
			 * 창 띄우고  버튼 비활성화
			 */
			break;
			
		case "검색":
			if (bookTabbedPane.getSelectedIndex() == 0) {
				//택이 전체목록 일 때
			}
			else {
				//탭이 내 책 목록일 때
			}
			//검색
			/* if(일치)
			 * 	텍스트필드에서 추출해서 전체 책과 비교해서 일치하는 것 들만 테이블에 출력
			 * else
			 * 	경고 메세지 (일치하는 책이 없습니다.)
			 */
			break;
			
		default:
			break;
		}
	}
}

