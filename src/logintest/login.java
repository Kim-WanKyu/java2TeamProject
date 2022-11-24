package logintest;

import java.sql.SQLException;

import Book.Book;
import Book.BookManager;
import User.User;
import User.UserManager;
public class login {
	public static void main(String args[]) throws SQLException {
		BookManager.getInstance().setAllBook();
 UserManager.getInstance().setAllUser();
 User user =new User();
 user.setID("202101826");
 Book book = new Book();
UserManager.getInstance().deleteUser(user);

		//UserManager.getInstance().setAllUser();
BookManager.getInstance().deleteBook("9788901223032");

	}
}
