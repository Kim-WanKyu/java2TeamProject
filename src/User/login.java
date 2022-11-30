package User;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import book.Book;
import book.BookManager;

public class login {
	public static void main(String[] args) throws SQLException {
			User user = new User();
			UserManager loguser = new UserManager().getInstance();
			BookManager BookDB = new BookManager().getInstance();
			BookDB.setAllBook();
			Book a = new Book();
			user.setID("201801826");
			a.setId("9788901243658");
			loguser.borrowBooks(user, a);
		}
}
