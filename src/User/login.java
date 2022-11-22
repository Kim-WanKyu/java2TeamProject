package User;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import Book.Book;
import Book.BookDAO;

public class login {
	public static void main(String[] args) throws SQLException {
			User user = new User();
			USERDAO loguser = new USERDAO().getInstance();
			BookDAO BOOKDB = new BookDAO().getInstance();
			BOOKDB.setAllBook();
			Book a = new Book();
			user.setID("201801826");
			user.setPassword("jdj01@@");
			loguser.setAllUser();
			loguser.Login(user.getID(),user.getPassword());
		}
}
