package main;

<<<<<<< HEAD:src/Main/Main.java
import GUIPackage.StartPage;
import User.*;
import Book.BookManager;
import DB.DBManager;
=======
import gui.page.startPage.StartPage;
>>>>>>> 511e71a49c983c0204bada924bf7061cfc19a596:src/main/Main.java

public class Main {
	
	//main 메소드
<<<<<<< HEAD:src/Main/Main.java
	public static void main(String args[]) throws Exception {
		UserManager.getInstance().setAllUser();
		BookManager.getInstance().setAllBook();
		
=======
	public static void main(String args[]) {
>>>>>>> 511e71a49c983c0204bada924bf7061cfc19a596:src/main/Main.java
		//초기화면 실행
		new StartPage();
	}
}