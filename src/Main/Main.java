package main;

import java.sql.SQLException;

import gui.page.startPage.StartPage;

public class Main {
	
	//main 메소드
	public static void main(String args[]) throws SQLException {
		//초기화면 실행
		new StartPage();
	}
}