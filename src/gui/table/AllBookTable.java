package gui.table;

import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import book.BookManager;
import book.CategorizeKDC;

public class AllBookTable {
	//allBookTable의 컬럼명
	private static final String[] allBookColumnName = {"도서명","저자명","출판사","분류","kdc","bookID","총수량","빌린횟수"} ;
	public static Object[] getAllBookColumnName() { return allBookColumnName; }
	
	//allBookTable의 데이터 //TODO DB에서 가져왔던 전체 도서 벡터로 초기화
	private static Object[][] allBookData;
	public static Object[][] getAllBookData() { return allBookData; }

	//allBookTable의 테이블모델
	private static DefaultTableModel allBookTableModel;
	/*
	 * //TODO make Table from DataBase 처음 프로그램 시작될 때 설정
					allBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					allBookTable.setModel(new DefaultTableModel (new Object[][][] {}, new String[] { "셀1","셀2","셀3","셀4","셀5" })  {
						public boolean isCellEditable(int row, int column) { return false; }
					} );
	 */
	public static DefaultTableModel getAllBookTableModel() { return allBookTableModel; }
	
	//allBookTable테이블
	private static JTable allBookTable = new JTable(allBookTableModel); //
	
	//allBookTable테이블 리턴하는 메소드
	public static JTable getAllBookTable() { return allBookTable; }
	/////
	
	public static void InitAllBookTable() {
//		setdefaultVector();
		allBookTable = new JTable(allBookTableModel);
		//TODO make Table from DataBase 처음 프로그램 시작될 때 설정
		allBookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//테이블 열 위치 변경 불가
		allBookTable.getTableHeader().setReorderingAllowed(false);
		allBookTableModel = new DefaultTableModel(allBookColumnName,0 );
		setdefaultVector();
		
		//테이블 내용 수정 불가 처리
//		allBookTable.setModel(new DefaultTableModel (allBookData, allBookColumnName)  {
//			public boolean isCellEditable(int row, int column) { return false; }
//		} );
		
		
	}
	public static void setdefaultVector(){
		String[] DataVectorRow = new String[8];
		TreeMap<String,book.Book> bookData = BookManager.getInstance().getlist();
		for(Entry<String , book.Book> entry : bookData.entrySet()) {
			DataVectorRow[0] = (entry.getValue().getName());
			DataVectorRow[1] = (entry.getValue().getAuthor());
			DataVectorRow[2] = (entry.getValue().getPublisher());
			DataVectorRow[3] = (CategorizeKDC.getCategoryname(entry.getValue().getCategory()));
			DataVectorRow[4] = (entry.getValue().getCategory());
			DataVectorRow[5] = (entry.getValue().getId());
			DataVectorRow[6] = (String.valueOf(entry.getValue().getTotalCount()));
			DataVectorRow[7] = (String.valueOf(entry.getValue().getBorrowCount()));
			allBookTableModel.addRow(DataVectorRow);
		}
	}
	
	
	
	
	
	//검색 테이블 생성
//	private static Object[][] defaultSearchData ;
//	private static DefaultTableModel searchModel = new DefaultTableModel(defaultSearchData,userColumnName);
//	public static DefaultTableModel getSearchBookTableModel() {return searchModel;}
	//변경사항있으면 업데이트해주는 메소드
	//allBookTable.updateUI();
	
	
}
