package logintest;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import Book.Book;
import Book.BookManager;
import User.User;
import User.UserManager;
public class login {
	public static void main(String args[]) throws SQLException {
		BookManager.getInstance().setAllBook();
		
		LinkedList<Book> books = BookManager.getInstance().findBook("분류", "문학_한국문학");
		 
		System.out.print(books);
		Iterator<Book> iter = books.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next().getName());
		}
	}
}
