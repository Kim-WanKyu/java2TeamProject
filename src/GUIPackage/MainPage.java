package GUIPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainPage extends Page{	

	//
	private JTabbedPane bookTabbedPane;	//탭

	//하단 검색 구역
	private JComboBox<String> categoryComboBox;	//분류 콤보박스
	private JTextField searchTextField;	//검색 텍스트필드
	private JButton searchButton;		//검색 버튼
	
	//상단 변경 구역
	private static JButton editSignoutUserButton = new JButton("변경/탈퇴");	//변경/탈퇴 버튼

	//우측 정보 필드 구역
	private JTextField bookNameTextField;			//도서명 텍스트필드
	private JTextField bookAuthorTextField;			//저자 텍스트필드
	private JTextField bookPublisherTextField;		//출판사 텍스트필드
	private JTextField bookCategoryTextField;		//분류 텍스트필드
	private JTextField bookIdTextField;				//id 텍스트필드

	//우측 정보 필드 구역
	private JTextField bookAvailableStockTextField;	//재고 텍스트필드
	
	//우측 정보 필드 구역
	private JTextField delayCheckBorrowDateTextField;	//대여일 텍스트필드
	private JTextField delayCheckReturnDateTextField;	//반납일 텍스트필드
	private JTextField delayUserInfoTextField;			//연체자 텍스트필드

	private JLabel myBookIsDelayLabel;		//연체여부 표시 레이블
	
	private JButton quitButton;			//종료하기 버튼
	private JButton logoutButton;		//로그아웃 버튼
	private JButton borrowButton;		//대여하기 버튼

	private JButton returnBookButton;	//반납하기 버튼
	
	private JButton insertBookButton;	//등룍 버튼
	private JButton deleteBookButton;	//삭제 버튼
	private JButton EditBookButton;		//수정 버튼
	
	private static JLabel delayNoticeLabel;	//연체 알림 메세지 레이블
	
	private JPanel mainPanel;
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
	
	public static JButton getEditSignoutUserButton() {
		return editSignoutUserButton;
	}
	public static JLabel getDelayNoticeLabel() {
		return delayNoticeLabel;
	}
	//setPage 메소드
	@Override
	void setPage() {
		boolean isAdmin = false;	//사용자 기본
		// userDB의 isAdmin 값을 추출하여 isAdmin에 넣음.
		//TODO JPanel이 제대로 뜰 수 있도록
	    //TODO 할 수 있으면, 클래스 분할해야 함.
		if(isAdmin == true) {
			//관리자
			setAdminPanel();
		}
		else {
			//사용자
			setUserPanel();
		}
	}
	
	void setAdminPanel() {
		
		//전체목록 탭에 들어갈 패널
		//allBookListPanel 패널(allBookListLeftPanel 패널, allBookListRightPanel 패널 포함)
		WhitePanel allBookListPanel = new WhitePanel();
		{
			/*allBookListPanel의 왼쪽*/
			//allBookListLeftPanel 패널 (allBookTable 테이블 포함)
			WhitePanel allBookListLeftPanel = new WhitePanel(new BorderLayout());
			{
				JTable allBookTable = new JTable(1,5);
				JScrollPane allBookScrollPane = new JScrollPane(allBookTable);
				{
					//TODO make Table from DataBase
					allBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					allBookTable.setModel(new DefaultTableModel (new Object[][][] {}, new String[] { "셀1","셀2","셀3","셀4","셀5" })  {
						public boolean isCellEditable(int row, int column) { return false; }
					} );
				}
				allBookListLeftPanel.add(allBookScrollPane, BorderLayout.CENTER);
			}
			/*allBookListPanel의 오른쪽*/
			//allBookListRightPanel 패널 (allBookListInfoPanel 패널, allBookListOptionButtonPanel 패널 포함)
			WhitePanel allBookListRightPanel = new WhitePanel(new BorderLayout());
			{
				//allBookListInfoPanel 패널 (allBookListTextPanel 패널 포함)
				JPanel allBookListInfoPanel = new JPanel(new BorderLayout());
				{
					JPanel allBookListTextPanel = new JPanel(new GridLayout(6,1));
					{
						JPanel allBookListNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							allBookListNamePanel.add(new JLabel("도서명 : "));
							allBookListNamePanel.add(bookNameTextField = new JTextField(10));
							allBookListNamePanel.setVisible(true);
							bookNameTextField.setVisible(true);
							allBookListNamePanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel allBookListAuthorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							allBookListAuthorPanel.add(new JLabel("저자명 : "));
							allBookListAuthorPanel.add(bookAuthorTextField = new JTextField(10));
							allBookListAuthorPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel allBookListPublisherPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							allBookListPublisherPanel.add(new JLabel("출판사 : "));
							allBookListPublisherPanel.add(bookPublisherTextField = new JTextField(10));
							allBookListPublisherPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel allBookListCategoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							allBookListCategoryPanel.add(new JLabel("분류 : "));
							allBookListCategoryPanel.add(bookCategoryTextField = new JTextField(10));
							allBookListCategoryPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel allBookListIdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							allBookListIdPanel.add(new JLabel("ID : "));
							allBookListIdPanel.add(bookIdTextField = new JTextField(10));
							allBookListIdPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel allBookListAvailableStockPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							allBookListAvailableStockPanel.add(new JLabel("재고 : "));
							allBookListAvailableStockPanel.add(bookAvailableStockTextField = new JTextField(10));
							allBookListAvailableStockPanel.setBackground(Color.LIGHT_GRAY);
						}				
						allBookListTextPanel.add(allBookListNamePanel);
						allBookListTextPanel.add(allBookListAuthorPanel);
						allBookListTextPanel.add(allBookListPublisherPanel);
						allBookListTextPanel.add(allBookListCategoryPanel);
						allBookListTextPanel.add(allBookListIdPanel);
						allBookListTextPanel.add(allBookListAvailableStockPanel);
						allBookListTextPanel.setBackground(Color.LIGHT_GRAY);
					}
					JPanel allBookListLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					allBookListLabelPanel.add(new JLabel("<<< 도서  정보 >>>"));
					allBookListLabelPanel.setBackground(new Color(128, 128, 255));
					
					allBookListInfoPanel.add(allBookListLabelPanel, BorderLayout.NORTH);
					allBookListInfoPanel.add(allBookListTextPanel, BorderLayout.SOUTH);
					allBookListInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 255), 4));
				}
				WhitePanel allBookListOptionButtonPanel = new WhitePanel();
				{
					insertBookButton = new JButton("등록");
					insertBookButton.addActionListener(this);
					deleteBookButton = new JButton("삭제");
					deleteBookButton.addActionListener(this);
					EditBookButton = new JButton("수정");
					EditBookButton.addActionListener(this);
					
					allBookListOptionButtonPanel.add(insertBookButton);
					allBookListOptionButtonPanel.add(deleteBookButton);
					allBookListOptionButtonPanel.add(EditBookButton);
				}
				allBookListRightPanel.add(allBookListInfoPanel, BorderLayout.NORTH);
				allBookListRightPanel.add(allBookListOptionButtonPanel, BorderLayout.SOUTH);
			}
			allBookListPanel.add(allBookListLeftPanel);
			allBookListPanel.add(allBookListRightPanel);
		}
		
		//연체확인 탭에 들어갈 패널
		//delayCheckPanel(delayCheckLeftPanel 패널, delayCheckRightPanel 패널 포함)
		WhitePanel delayCheckPanel = new WhitePanel();
		{
			//delayCheckLeftPanel 패널 (bookTable 테이블, searchPanel 패널 포함)
			WhitePanel delayCheckLeftPanel = new WhitePanel(new BorderLayout());
			{
				JTable delayCheckTable = new JTable(1,5);
				JScrollPane delayCheckScrollPane = new JScrollPane(delayCheckTable);
//					{
//						bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//						bookTable.setModel(new DefaultTableModel (new Object[][][] {}, new String[] { "셀1","셀2","셀3","셀4","셀5" })  {
//							public boolean isCellEditable(int row, int column) { return false; }
//						} );
//						
//					}
				///////////////////////////////////////////////////////////
				delayCheckLeftPanel.add(delayCheckScrollPane, BorderLayout.CENTER);
			}
			/*오른쪽*/
			//delayCheckRightPanel 패널
			WhitePanel delayCheckRightPanel = new WhitePanel(new BorderLayout());
			{
				JPanel delayCheckInfoPanel = new JPanel(new BorderLayout());
				{
					JPanel delayCheckTextPanel = new JPanel(new GridLayout(7,1));
					{
						JPanel delayCheckNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							delayCheckNamePanel.add(new JLabel("도서명 : "));
							delayCheckNamePanel.add(bookNameTextField = new JTextField(10));
							delayCheckNamePanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel delayCheckAuthorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							delayCheckAuthorPanel.add(new JLabel("저자명 : "));
							delayCheckAuthorPanel.add(bookAuthorTextField = new JTextField(10));
							delayCheckAuthorPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel delayCheckPublisherPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							delayCheckPublisherPanel.add(new JLabel("출판사 : "));
							delayCheckPublisherPanel.add(bookPublisherTextField = new JTextField(10));
							delayCheckPublisherPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel delayCheckIdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							delayCheckIdPanel.add(new JLabel("ID : "));
							delayCheckIdPanel.add(bookIdTextField = new JTextField(10));
							delayCheckIdPanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel delayCheckBorrowDatePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							delayCheckBorrowDatePanel.add(new JLabel("대여일 : "));
							delayCheckBorrowDatePanel.add(delayCheckBorrowDateTextField = new JTextField(10));
							delayCheckBorrowDatePanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel delayCheckReturnDatePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							delayCheckReturnDatePanel.add(new JLabel("반납일 : "));
							delayCheckReturnDatePanel.add(delayCheckReturnDateTextField = new JTextField(10));
							delayCheckReturnDatePanel.setBackground(Color.LIGHT_GRAY);
						}
						JPanel delayCheckUserInfoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						{
							delayCheckUserInfoPanel.add(new JLabel("대여자 : "));
							delayCheckUserInfoPanel.add(delayUserInfoTextField = new JTextField(10));
							delayCheckUserInfoPanel.setBackground(Color.LIGHT_GRAY);
						}				
						delayCheckTextPanel.add(delayCheckNamePanel);
						delayCheckTextPanel.add(delayCheckAuthorPanel);
						delayCheckTextPanel.add(delayCheckPublisherPanel);
						delayCheckTextPanel.add(delayCheckIdPanel);
						delayCheckTextPanel.add(delayCheckBorrowDatePanel);
						delayCheckTextPanel.add(delayCheckReturnDatePanel);
						delayCheckTextPanel.add(delayCheckUserInfoPanel);
						delayCheckTextPanel.setBackground(Color.LIGHT_GRAY);
					}
					JPanel delayCheckLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					delayCheckLabelPanel.add(new JLabel("<<< 도서  정보 >>>"));
					delayCheckLabelPanel.setBackground(new Color(127, 255, 127));
					
					delayCheckInfoPanel.add(delayCheckLabelPanel, BorderLayout.NORTH);
					delayCheckInfoPanel.add(delayCheckTextPanel, BorderLayout.SOUTH);
					delayCheckInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(127, 255, 127), 4));
				}
				borrowButton = new JButton("대여하기");
				borrowButton.addActionListener(this);
					
				delayCheckRightPanel.add(delayCheckInfoPanel, BorderLayout.NORTH);
				delayCheckRightPanel.add(borrowButton, BorderLayout.SOUTH);
			}
			delayCheckPanel.add(delayCheckLeftPanel);
			delayCheckPanel.add(delayCheckRightPanel);
		}
		
		bookTabbedPane = new JTabbedPane();
		{
			bookTabbedPane.addTab("전체도서", allBookListPanel);
			bookTabbedPane.addTab("연체확인", delayCheckPanel);
		}
		
		WhitePanel downButtonPanel = new WhitePanel(new BorderLayout());
		{
			WhitePanel searchPanel = new WhitePanel(new FlowLayout(FlowLayout.LEFT));
			{
				//임시
				String[] categoryString = {"도서명", "저자명", "출판사", "분류"};
				categoryComboBox = new JComboBox<String>(categoryString);

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
		
		//변경 버튼 패널
		WhitePanel upButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
		{
			MainPage.getEditSignoutUserButton().addActionListener(this);
			upButtonPanel.add(MainPage.getEditSignoutUserButton(), BorderLayout.EAST);
		}
		
		//mainPagePanel = 메인화면 패널 (leftPanel패널, rightPanel 포함)
		WhitePanel mainPagePanel = new WhitePanel(new BorderLayout());
		mainPagePanel.add(upButtonPanel, BorderLayout.NORTH);
		mainPagePanel.add(bookTabbedPane, BorderLayout.CENTER);
		mainPagePanel.add(downButtonPanel, BorderLayout.SOUTH);
		
		ct.add(mainPagePanel);
	}
	
	//사용자 화면으로 세팅하는 메소드
	void setUserPanel() {
		//myBookPanel(myBookLeftPanel 패널, myBookRightPanel 패널 포함)
				WhitePanel myBookPanel = new WhitePanel();
				{
					//myBookLeftPanel 패널 (bookTable 테이블, searchPanel 패널 포함)
					WhitePanel myBookLeftPanel = new WhitePanel(new BorderLayout());
					{
						JTable myBookTable = new JTable(1,5);
						JScrollPane myBookScrollPane = new JScrollPane(myBookTable);
						//{
						//	TODO make Table from DataBase
						//	bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						//	bookTable.setModel(new DefaultTableModel (new Object[][][] {}, new String[] { "셀1","셀2","셀3","셀4","셀5" })  {
						//		public boolean isCellEditable(int row, int column) { return false; }
						//	} );
						//}
						myBookLeftPanel.add(myBookScrollPane, BorderLayout.CENTER);
						
						delayNoticeLabel = new JLabel("123");
						myBookLeftPanel.add(delayNoticeLabel, BorderLayout.SOUTH);
					}
					////////////////////////////////////////////수정필요
					/*오른쪽*/
					WhitePanel myBookRightPanel = new WhitePanel(new BorderLayout());
					{
						JPanel myBookInfoPanel = new JPanel(new BorderLayout());
						{
							JPanel myBookTextPanel = new JPanel(new GridLayout(6,1));
							{
								JPanel myBookNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
								{
									myBookNamePanel.add(new JLabel("도서명 : "));
									myBookNamePanel.add(bookNameTextField = new JTextField(10));
									myBookNamePanel.setBackground(Color.LIGHT_GRAY);
								}
								JPanel myBookAuthorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
								{
									myBookAuthorPanel.add(new JLabel("저자명 : "));
									myBookAuthorPanel.add(bookAuthorTextField = new JTextField(10));
									myBookAuthorPanel.setBackground(Color.LIGHT_GRAY);
								}
								JPanel myBookPublisherPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
								{
									myBookPublisherPanel.add(new JLabel("출판사 : "));
									myBookPublisherPanel.add(bookPublisherTextField = new JTextField(10));
									myBookPublisherPanel.setBackground(Color.LIGHT_GRAY);
								}
								JPanel myBookCategoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
								{
									myBookCategoryPanel.add(new JLabel("분류 : "));
									myBookCategoryPanel.add(bookCategoryTextField = new JTextField(10));
									myBookCategoryPanel.setBackground(Color.LIGHT_GRAY);
								}
								JPanel myBookIdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
								{
									myBookIdPanel.add(new JLabel("ID : "));
									myBookIdPanel.add(bookIdTextField = new JTextField(10));
									myBookIdPanel.setBackground(Color.LIGHT_GRAY);
								}
								JPanel myBookIsDelayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
								{
									//TODO do better
									myBookIsDelayPanel.add(new JLabel("연체여부 : "));
									myBookIsDelayPanel.add(myBookIsDelayLabel = new JLabel());
									myBookIsDelayLabel.setText("V");
									
									myBookIsDelayPanel.setBackground(Color.LIGHT_GRAY);
								}
								myBookTextPanel.add(myBookNamePanel);
								myBookTextPanel.add(myBookAuthorPanel);
								myBookTextPanel.add(myBookPublisherPanel);
								myBookTextPanel.add(myBookCategoryPanel);
								myBookTextPanel.add(myBookIdPanel);
								myBookTextPanel.add(myBookIsDelayPanel);
								myBookTextPanel.setBackground(Color.LIGHT_GRAY);
							}
							JPanel myBookLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
							myBookLabelPanel.add(new JLabel("<<< 빌린 도서  정보 >>>"));
							myBookLabelPanel.setBackground(new Color(128, 128, 255));
							myBookInfoPanel.add(myBookLabelPanel, BorderLayout.NORTH);
							myBookInfoPanel.add(myBookTextPanel, BorderLayout.SOUTH);
							myBookInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 255), 4));
						}
						returnBookButton = new JButton("반납하기");
						returnBookButton.addActionListener(this);
						myBookRightPanel.add(myBookInfoPanel, BorderLayout.NORTH);
						myBookRightPanel.add(returnBookButton, BorderLayout.SOUTH);
					}
					myBookPanel.add(myBookLeftPanel);
					myBookPanel.add(myBookRightPanel);
				}
				
				////////////////////////////////////////////////////////////////////////////
				
				//bookPanel(bookLeftPanel 패널, bookRightPanel 패널 포함)
				WhitePanel bookPanel = new WhitePanel();
				{
					//bookLeftPanel 패널 (bookTable 테이블, searchPanel 패널 포함)
					WhitePanel bookLeftPanel = new WhitePanel(new BorderLayout());
					{
						JTable bookTable = new JTable(1,5);
						JScrollPane bookScrollPane = new JScrollPane(bookTable);
//						{
//							bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//							bookTable.setModel(new DefaultTableModel (new Object[][][] {}, new String[] { "셀1","셀2","셀3","셀4","셀5" })  {
//								public boolean isCellEditable(int row, int column) { return false; }
//							} );
//							
//						}
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
						        //add(sc);
						        
						        //테이블에 데이터 추가하기
						        //원본데이터를 건들지 않고 table의 매개변수인 model에 있는 데이터를 변경합니다
						        DefaultTableModel m =
						                (DefaultTableModel)bookTable.getModel();
						        //모델에 데이터 추가 , 1번째 출에 새로운 데이터를 추가합니다
						        m.insertRow(b.length, new Object[]{"d1","d2","d3"});
						        //추가를 마치고 데이터 갱신을 알립니다.
						        bookTable.updateUI();
						    
						        
						        
//						        //------- 그 외 메소드들 ---------
//						        //테이블의 데이터를 가져오는 메소드
//						        System.out.println(bookTable.getValueAt(1,1));
//						        //테이블의 데이터를 바꾸는 메소드
//						        bookTable.setValueAt("picachu",2,2);
//						        //테이블 row 갯수 가져오기
//						        System.out.println(bookTable.getRowCount());
//						        //테이블 colum 갯수 가져오기
//						        System.out.println(bookTable.getColumnCount());
//						        //테이블 Colum 이름 가져오기
//						        System.out.println(bookTable.getColumnName(0));
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
							bookLabelPanel.setBackground(new Color(127, 255, 127));
							
							bookInfoPanel.add(bookLabelPanel, BorderLayout.NORTH);
							bookInfoPanel.add(bookTextPanel, BorderLayout.SOUTH);
							bookInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(127, 255, 127), 4));
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
						categoryComboBox = new JComboBox<String>(categoryString);
						
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
				
				//변경 버튼 패널
				WhitePanel upButtonPanel = new WhitePanel(new FlowLayout(FlowLayout.RIGHT));
				{
					editSignoutUserButton = new JButton("변경/탈퇴");
					editSignoutUserButton.addActionListener(this);
					upButtonPanel.add(editSignoutUserButton, BorderLayout.EAST);
				}
				
				//mainPagePanel = 메인화면 패널 (leftPanel패널, rightPanel 포함)
				WhitePanel mainPagePanel = new WhitePanel(new BorderLayout());
				mainPagePanel.add(upButtonPanel, BorderLayout.NORTH);
				mainPagePanel.add(bookTabbedPane, BorderLayout.CENTER);
				mainPagePanel.add(downButtonPanel, BorderLayout.SOUTH);

				
				//컨테이너 ct에 startPagePanel 부착
				ct.add(mainPagePanel);
			}
	
	//버튼 클릭 시 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "로그아웃":
			//켜져있는 모든 창 끄고
			for(int i = 0; i<getOwnerlessWindows().length;i++)
				getOwnerlessWindows()[i].dispose();
			
			new StartPage();//초기화면 창 생성
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
			getEditSignoutUserButton().setEnabled(false);
			new EditSignoutUserPage();
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
			
		case "등록":
			MessageBox.printInfoMessageBox("등록");
			//등록
			/*
			 * DB에 추가 테이블에서도 추가
			 */
			break;
			
		case "삭제":
			MessageBox.printInfoMessageBox("삭제");
			//삭제
			/*
			 * DB에서 삭제 테이블에서도 삭제 
			 */
			break;

		case "종료하기":
			System.exit(0);
			break;
			
		default:
			break;
		}
	}
}

