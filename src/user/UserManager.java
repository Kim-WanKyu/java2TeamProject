package user;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

import book.Book;
import book.BookManager;
import book.CategorizeKDC;

import java.util.Calendar;

import db.DBManager;
import gui.util.MessageBox;


public class UserManager {
	//UserManager 데이터 종류
	private static	Vector<User> userList;
	
	private static UserManager userInstance = new UserManager();
	public static UserManager getInstance() {
		System.out.print("인스턴스 반환");
		return userInstance;
	}
	public Vector<User> getUserVector(){
		return userList;
	}
	
	//특정 유저 
	//유저 정보찾기
	public User findUser(String id){
		
		for( User findUser : userList) {
			if(id.equals(findUser.getID())) {
				System.out.println("여기 학번 찾음");
				return findUser;
			}
		}
		System.out.println("여기 학번이 없음");
		return null;
	}
	
	//검색한 유저들 찾기 메소드
	public Vector<User> searchUser(String category, String word){
		Vector<User> searchedUsersVector = new Vector<User>();		
		switch(category) {
		case "ID(학번)":
			for(User user : userList)
			{
				if(user.getIsAdmin()==false) {
					if (user.getID().contains(word))
						searchedUsersVector.add(user);
				}
			}
			break;
			
		case "이름":
			for(User user : userList)
			{
				if(user.getIsAdmin()==false) {
					if (user.getName().contains(word))
						searchedUsersVector.add(user);
				}
			}
			break;
			
		default:
			MessageBox.printWarningMessageBox("검색분류가 올바르지 않습니다.");
		}
		return searchedUsersVector;
	}
	
	//비밀번호 찾기 메소드
	public String findPassword(String id, String name) {
		System.out.println("유저 비밀번호 찾기");
		String password = "";
		for(User isUser : userList) {
			if(id.equals(isUser.getID())) {
				System.out.println("아이디 일치");
				if(name.equals(isUser.getName())) {
					System.out.println("이름 일치");
					password = isUser.getPassword();
					break;
				}
				else {
					System.out.println("이름 불일치");
					break;
				}
			}	
			else {
				System.out.println("아이디 없음"+id);
				
			}
		}
		return password;
	}
	
	//모든 유저 가져와 초기화 메서드
	//관리지가 사용할 예정
	public void setAllUser() throws SQLException{
		userList = new Vector<>();
		try {
			String sql = "select * from student";
			Connection conn = DBManager.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user = new User();
				user.setName(rs.getString("Name"));
				user.setID(rs.getString("hakbun"));
				user.setIsAdmin(rs.getBoolean("is_admin"));
				user.setPassword(rs.getString("password"));
				userList.add(user);
				
				System.out.println("유저 정보 리스트 출력(유저 매니저 setAllUser) : "+user.getID()+ " "+user.getName()+" "+user.getPassword());
			}	
			
			sql = "select * from borrowbooksanddates ";
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int n=0;
			while(rs.next()) {
				System.out.println(""+ n + ':' + userList.get(n).getID());
				userList.get(n).setBorrowBooks(0, rs.getString("book1"));
				userList.get(n).setBorrowDates(0, rs.getDate("date1"));
				userList.get(n).setBorrowBooks(1, rs.getString("book2"));
				userList.get(n).setBorrowDates(1, rs.getDate("date2"));
				userList.get(n).setBorrowBooks(2, rs.getString("book3"));
				userList.get(n).setBorrowDates(2, rs.getDate("date3"));
				userList.get(n).setDelay(rs.getDate("delay_info"));
				n++;
			}
			
			//회원 정보 및 대여 정보 출력
			for(int i =0; i<userList.size();  i++) {
				String[] book = userList.get(i).getBorrowBooks();
				java.sql.Date[] date = userList.get(i).getBorrowDates();
				System.out.println(userList.get(i).getID() + " " + userList.get(i).getName());
				System.out.println(book[0]+": "+date[0]);
				System.out.println(book[1]+": "+date[1]);
				System.out.println(book[2]+": "+date[2]);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close();
		}
	}
	
	//도서 대여 메서드
	public String borrowBooks(User nowuser,Book borrowbook) {			
		java.sql.Date date1 = null;
		String book[];
		java.sql.Date borrowDates[];
		
		//대여 유저 찾기
		int count = 0;
		for(User findUser : userList) {
			System.out.println("findUser.getID()."+count+".:" + findUser.getID());
			if(findUser.getID().equals(nowuser.getID())) {
				count = userList.indexOf(findUser);
				break;
			}			
		}
		nowuser = userList.get(count);
		
		//연체 확인 / 연체기록
		java.util.Date dt = new java.util.Date();	//현재 날짜
		java.sql.Date date = new java.sql.Date(dt.getTime());
		Calendar c = Calendar.getInstance();
		System.out.println(nowuser.getDelayDate());
		if(nowuser.getDelayDate()!=null &&nowuser.getDelayDate().after(date)) {
			System.out.println("135번째 줄!! 책 연체됨 빌리기 불가!! 함수 종료");
			return "delay";
		}
		//연체확인 / 대여한 미반납 도서
		borrowDates = nowuser.getBorrowDates();
		for(int i=0;i<borrowDates.length;i++) {
			if(borrowDates[i]!=null) {
				c.setTime(borrowDates[i]);
				c.add(Calendar.DATE, 7);
				date1 = new java.sql.Date(c.getTimeInMillis());
				if(date1.before(date)) {
					System.out.println("140번째 줄!!! 연체됨 빌리기 불가!!");
					return "delay";
				}
			}
		}
		book = nowuser.getBorrowBooks();
		if(borrowbook.getTotalCount()==borrowbook.getBorrowCount()) {	//재고 수량 확인
			System.out.println("책빌리기 불가 책 수량 없음!!");
			return "bookStockOver";
		}
		else if(book[0]!=null&&book[1]!=null&&book[2]!=null) {	//대여가능 수량 확인
			System.out.println("빌릴 수 있는 책 개수 초과");
			return "bookOver";
		}
		else {	//동일 도서 중복 대여 확인
			for(int i=0;i<3;i++) {
				if(nowuser.getBorrowBooks()[i]!=null&&borrowbook.getId().equals(nowuser.getBorrowBooks()[i])) {
					System.out.println("동일 도서 중복 대여 불가");
					return "bookOverlap";
				}
			}
		}		
		
		try {
			Connection conn = DBManager.connect();
			PreparedStatement pstmt = null;
			String sql = "update borrowbooksanddates set ";
			
			dt = new java.util.Date();
			date = new java.sql.Date(dt.getTime());
			c = Calendar.getInstance();
			c.setTime(date);
			book = nowuser.getBorrowBooks();
			//동일 도서 중복 대여 방지
			if(book[0] == null) {
				sql +="book1 = ?, date1 = ?, delay_info = ? where(hakbun = ?)";
				System.out.println(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, borrowbook.getId());
				pstmt.setDate(2, date);//날짜 비교 연산 메서드
				pstmt.setDate(3, null);
				pstmt.setString(4, nowuser.getID());
				pstmt.executeUpdate();
				sql = "update book_list set borrowcount = ? where(id = ?)";
				pstmt = conn.prepareStatement(sql);
				borrowbook.addCount();
				System.out.println(borrowbook.getBorrowCount());
				pstmt.setInt(1,borrowbook.getBorrowCount());
				pstmt.setString(2, borrowbook.getId());
				pstmt.executeUpdate();
				nowuser.setBorrowBooks(0, borrowbook.getId());
				nowuser.setBorrowDates(0, date);
			}
			else if(book[1] == null) {
				sql +="book2 = ?, date2 = ?, delay_info = ? where(hakbun = ?)";
				System.out.println(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, borrowbook.getId());
				pstmt.setDate(2, date);
				pstmt.setDate(3, null);
				pstmt.setString(4,nowuser.getID());
				pstmt.executeUpdate();
				sql = "update book_list set borrowcount = ? where(id = ?)";
				pstmt = conn.prepareStatement(sql);
				borrowbook.addCount();
				System.out.println(borrowbook.getBorrowCount());
			 
				pstmt.setInt(1,borrowbook.getBorrowCount());
				pstmt.setString(2, borrowbook.getId());
				pstmt.executeUpdate();
				nowuser.setBorrowBooks(1, borrowbook.getId());
				nowuser.setBorrowDates(1, date);
			}
			else if(book[2] == null) {
				sql +="book3 = ?, date3 = ?, delay_info = ? where(hakbun = ?)";
				System.out.println(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, borrowbook.getId());
				pstmt.setDate(2, date);
				pstmt.setDate(3, null);
				pstmt.setString(4,nowuser.getID());
				pstmt.executeUpdate();
				sql = "update book_list set borrowcount = ? where(id = ?)";
				pstmt = conn.prepareStatement(sql);
				borrowbook.addCount();
			 
				pstmt.setInt(1,borrowbook.getBorrowCount());
				pstmt.setString(2, borrowbook.getId());
				pstmt.executeUpdate();
				nowuser.setBorrowBooks(2, borrowbook.getId());
				nowuser.setBorrowDates(2, date);
			}
			return "Success";
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close();
		}
		return null;

	}
	
	//도서 반납 메서드
	public void returnBook (User user, Book book) {
		book =  BookManager.getInstance().getlist().get(book.getId());
		int count = 0;
		
		for(User findUser : userList) {
			System.out.println(findUser.getID());
			if(findUser.getID().equals(user.getID()))
			{
				count = userList.indexOf(findUser);
				break;
			}
		}
		
		user = userList.get(count);
		System.out.println("282줄 : 유저 id : "+user.getID());
		Connection conn = null;
		String[] borrowbookslist = user.getBorrowBooks();
		System.out.println(borrowbookslist[0]);
		
		int countfind ;
		for(countfind = 0 ; countfind < user.getBorrowBooks().length; countfind++) {
			if(borrowbookslist[countfind]!=null && borrowbookslist[countfind].equals(book.getId())) {
				System.out.println("인덱스 발견"+countfind);
				break;
			}
		}
		
		java.sql.Date delay_info = null;
		delay_info = user.getDelayDate();
		java.util.Date dt = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dt.getTime());
		Calendar c1 = Calendar.getInstance();
		PreparedStatement pstmt = null;
		try {
			System.out.println("책 반납 실행!!");
			System.out.println(book.getName());
			
			java.util.Date set_Date;
			int diffDays;
			set_Date = user.getBorrowDates()[countfind];
			diffDays = (int) ((dt.getTime()-set_Date.getTime()) / (24*60*60*1000));
			System.out.println(user.getDelayDate());
			
		
			 if(diffDays>7 && user.getDelayDate()==null)
			{
				c1.setTime(date);
				c1.add(Calendar.DATE, diffDays-7);
				delay_info = new java.sql.Date(c1.getTimeInMillis());
			}
			else if(diffDays>7 && user.getDelayDate()!=null)
			{	System.out.println(delay_info);
				c1.setTime(delay_info);
				c1.add(Calendar.DATE, diffDays-7);
				delay_info = new java.sql.Date(c1.getTimeInMillis());
			}
			
			user.setBorrowDates(countfind,null);
			user.setBorrowBooks(countfind, null);
			user.setDelay(delay_info);
			String sql = "update borrowbooksanddates set ";
			sql += "book"+(countfind +1)+ " = ?,";
			sql += "date"+(countfind +1)+" = ?,";
			sql += "delay_info = ? where(hakbun = ?)";
			conn = DBManager.connect();	
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setDate(2, null);
			pstmt.setDate(3,delay_info);
			pstmt.setString(4,user.getID());
			pstmt.executeUpdate();
			book.subCount();
			sql = "update book_list set borrowcount = ? where(id = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,book.getBorrowCount());
			pstmt.setString(2,book.getId());
			pstmt.executeUpdate();
			System.out.println(delay_info);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close();
		}
	}
	
	//유저 등록 메소드
	public void insertUser(User newuser) {
		System.out.println("데베 유저 추가");
		try {
			Connection conn = DBManager.connect();
			java.sql.Statement stmt = conn.createStatement();
			
			String hakbun = newuser.getID();
			String name = newuser.getName();
			String password = newuser.getPassword();
			int isAdmin = 0;
			if(newuser.getIsAdmin()) { isAdmin = 1; }
			
			String sql =  " insert into student(hakbun, name, password, is_admin) VALUES('"+hakbun+"', '"+name+"' ,'"+password+"' ,'"+isAdmin+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			sql = "insert into borrowbooksanddates(hakbun) VALUES('"+hakbun+"')";
			stmt.executeUpdate(sql);
			
			userList.add(newuser);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close();
		}
	}
	
	//유저 삭제 메서드
	public boolean deleteUser(User deleteuser)  {
		//유저가 빌린 도서를 전부 반납하지 않으면 탈퇴 불가
		for(User isUser : userList) {
			if(deleteuser.getID().equals(isUser.getID())) {
				deleteuser = isUser;
				System.out.println("여기 유저 찾음....");
				for(int i =0; i<deleteuser.getBorrowBooks().length;i++) {
					if(deleteuser.getBorrowBooks()[i]!=null) {
						System.out.println("책 반납후 회원탈퇴 바람!!");
						return false;
					}
				}
			}
		}
		
		//탈퇴한 회원 DB 삭제
		try {
			Connection conn = DBManager.connect();
			java.sql.Statement stmt = conn.createStatement();
			
			String delete = "delete from student where hakbun = "+deleteuser.getID();
			stmt.executeUpdate(delete);
			System.out.println("데베 유저 삭제");

			//탈퇴한 회원 정보 삭제
			userList.remove(deleteuser);
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close();			
		}
		
		return true;
	}
	
	//유저 정보변경
	public void changeInform(User userInformation) {
		//유저 정보 DB 변경 
		try {
			String sql = "update student set name = ?, password = ? where(hakbun = ?)";

			Connection conn = DBManager.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userInformation.getName());
			pstmt.setString(2,userInformation.getPassword());
			pstmt.setString(3, userInformation.getID());
			pstmt.executeUpdate();
			
			//유저 정보 변경
			for(User changeUser : userList) {
				int count;
				System.out.println(userInformation.getID());
				if(userInformation.getID().equals(changeUser.getID()))
				{
					count = userList.indexOf(changeUser);
					userList.set(count, userInformation);
					break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		finally {
			DBManager.close();
		}
	}
	
	//로그인 메서드
	public boolean login(String getId, String getPass) {
		boolean isUser = false;	//ID존재하는 유저인지
		System.out.println("유저 데베 찾기");
		for(User user : userList) {
			if(getId.equals(user.getID())) {
				if(getPass.equals(user.getPassword())) {
					System.out.println("여기 값있음");
					isUser= true;
					break;
				}
				else
					System.out.println("비밀번호가 일치하지 않습니다");
			}	
			else
				System.out.println("여기 학번이 없음");
		}
		return isUser;
	}
}