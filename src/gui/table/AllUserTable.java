package gui.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.page.mainPage.MainPageComponent;
import user.User;
import user.UserManager;

public class AllUserTable {
	//userTable의 컬럼명
	private final static String[] userColumnName = {"ID","이름"};
	public static String[] getAllUserColumnName() { return userColumnName; }
	
	//userTable의 데이터 //TODO DB에서 가져왔던 전체 유저 벡터로 초기화
	private static String [][]defaultUserData ;
	public static String[][] getAllUserData() { return defaultUserData; }
	
	//userTable의 테이블모델
	private static DefaultTableModel allUserTableModel = new DefaultTableModel(defaultUserData,userColumnName);
	public static DefaultTableModel getUserTableModel() {return allUserTableModel;};
	
	//userTable테이블
	private static JTable allUserTable = new JTable(allUserTableModel); //
	public static JTable getAllUserTable() { return allUserTable; }
	
	//allUserTable 초기화 메소드
	public static void InitAllUserTable() {
		allUserTable = new JTable(allUserTableModel);
		allUserTableModel = new DefaultTableModel(userColumnName, 0);

		//필드 너비 제한 해제
		getAllUserTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//테이블 열 위치 변경 불가
		getAllUserTable().getTableHeader().setReorderingAllowed(false);
		
		setDefaultVector();
		
	}
	
	//
	public static void setDefaultVector() {
		String[] dataVectorRow = new String[2];
		for(User setUser: UserManager.getInstance().getUserVector()) {
			if(setUser.getIsAdmin()==false) {
				dataVectorRow[0]=setUser.getID();
				dataVectorRow[1]=setUser.getName();
				allUserTableModel.addRow(dataVectorRow);
			}
		}
	}
}
