package main;

import java.sql.SQLException;

import book.BookManager;
import gui.page.startPage.StartPage;
import user.UserManager;

public class Main {
	
	//main 메소드
	public static void main(String args[]) throws SQLException {
		//초기화면 실행
		new StartPage();
		UserManager.getInstance().setAllUser();
		BookManager.getInstance().setAllBook();
	}
}