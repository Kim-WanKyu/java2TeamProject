package User;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import Book.Book;
import Book.BookManager;

public class login {
	public static void main(String[] args) throws SQLException {
			User user = new User();
			UserManager loguser = new UserManager().getInstance();
			BookManager BookDB = new BookManager().getInstance();
			BookDB.setAllBook();
			Book a = new Book();
			user.setID("201801826");
			user.setPassword("jdj01@@");
			loguser.setAllUser();
			loguser.Login(user.getID(),user.getPassword());
		}
}
