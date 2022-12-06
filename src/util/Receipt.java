package util;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import book.CategorizeKDC;
import gui.util.MessageBox;

public class Receipt {
	
	private String userId;
	private String userName;
	
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private String bookCategoryName;
	private String bookCategoryCode;
	private String bookId;
	
	private LocalDate borrowDay;
	private LocalDate returnDay;
	
	//Receipt 생성자
	public Receipt(user.User borrowUser, book.Book borrowBooks) {
		userId = borrowUser.getID();
		userName = user.UserManager.getInstance().findUser(userId).getName();
		
		bookName = borrowBooks.getName();
		bookAuthor = borrowBooks.getAuthor();
		bookPublisher = borrowBooks.getPublisher();
		
		if(!borrowBooks.getCategory().equals(""))
			bookCategoryName = CategorizeKDC.getCategoryname(borrowBooks.getCategory());
		else
			bookCategoryName = "";
		bookCategoryCode = borrowBooks.getCategory();
		
		bookId = borrowBooks.getId();
		
		borrowDay = LocalDate.now();
		returnDay = LocalDate.now().plusDays(7);
	}
	
	//영수증 출력 메소드
	public void printReceipt() {
		File receiptFile = makeFile();	//영수증 생성
		writeReceipt(receiptFile);		//영수증 작성
	}
	
	//영수증 파일 생성하는 메소드
	private File makeFile() {
		//fileName = "학번(ID)_대여일자.txt"를 생성하기 위한 파일이름
		String fileName = userId + "_" + borrowDay;
		//filePath = receipts폴더 하위에 fileName.txt 파일 경로
		String filePath = "receipts/" + fileName + ".txt";
		File file = new File(filePath);
		
		return file;
	}
	
	//영수증 파일에 문자열 출력하는 메소드
	private void writeReceipt(File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);

			fos.write(("==============================\n").getBytes());
			fos.write(("ID(학번) : " + userId + '\n').getBytes());
			fos.write(("이름 : " + userName + '\n').getBytes());
			fos.write(("------------------------------\n").getBytes());
			fos.write(("도서명 : " + bookName + '\n').getBytes());
			fos.write(("저자명 : " + bookAuthor + '\n').getBytes());
			fos.write(("출판사 : " + bookPublisher + '\n').getBytes());
			fos.write(("분류명 : " + bookCategoryName + '\n').getBytes());
			fos.write(("KDC : " + bookCategoryCode + '\n').getBytes());
			fos.write(("ID : " + bookId + '\n').getBytes());
			fos.write(("\n").getBytes());
			fos.write(("대여일자 : " + borrowDay + '\n').getBytes());
			fos.write(("반납기한 : " + returnDay + '\n').getBytes());
			fos.write(("------------------------------\n").getBytes());
			fos.write((LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd(E) HH:mm:ss")) + '\n').getBytes());
			fos.write(("==============================\n").getBytes());
	        
			fos.close();	//파일 닫기
			
			MessageBox.printInfoMessageBox("영수증 출력이 완료되었습니다.");
		} catch(Exception e) { 
			e.printStackTrace();
			MessageBox.printErrorMessageBox("영수증 출력 에러");
		}
	}
}