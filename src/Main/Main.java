package Main;

import GUIPackage.StartPage;
import User.*;
import Book.BookManager;
import DB.DBManager;

public class Main {
	
	//main 메소드
	public static void main(String args[]) throws Exception {
		UserManager.getInstance().setAllUser();
		BookManager.getInstance().setAllBook();
		
		//초기화면 실행
		new StartPage();
	}
}