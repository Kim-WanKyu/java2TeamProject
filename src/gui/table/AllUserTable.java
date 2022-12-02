package gui.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.page.mainPage.MainPageComponent;
import user.User;
import user.UserManager;

public class AllUserTable {

	//userTable 전체도서 테이블 static
	//userTable의 컬럼명
	private final static String[] userColumnName = {"ID","이름"};
	public static String[] getAllUserColumnName() { return userColumnName; }
	
	//userTable의 데이터 //TODO DB에서 가져왔던 전체 유저 벡터로 초기화
	private static String [][]defaultUserData ;
	public static String[][] getAllUserData() { return defaultUserData; }
	
	//userTable의 테이블모델
	private static DefaultTableModel userTableModel = new DefaultTableModel(defaultUserData,userColumnName);
	public static DefaultTableModel getUserTableModel() {return userTableModel;};
	
	//userTable테이블
	private static JTable userTable = new JTable(userTableModel); //
	//userTable테이블 리턴하는 메소드
	public static JTable getAllUserTable() { return userTable; }
	
	private static Object[] setAllUserData;
	public static void InitAllUserTable() {
		//TODO make Table from DataBase 처음 프로그램 시작될 때 설정
		
		
		getAllUserTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setAllUserData = new Object[2];
		
		for(User setUser: UserManager.getInstance().getUserVector()) {
			if(setUser.getIsAdmin()==false) {
						setAllUserData[0]=setUser.getID();
						setAllUserData[1]=setUser.getName();
						getUserTableModel().addRow(setAllUserData);
						//borrowDateTextFields[]
					
				}
			}
		
		//테이블 열 위치 변경 불가
		getAllUserTable().getTableHeader().setReorderingAllowed(false);
		
		//테이블 내용 수정 불가 처리
		//MainPageComponent.getAllUserTable().setModel(new DefaultTableModel (MainPageComponent.getAllUserData(), MainPageComponent.getAllUserColumnName()) {
			//public boolean isCellEditable(int row, int column) { return false; }
		//} );
	}
}
