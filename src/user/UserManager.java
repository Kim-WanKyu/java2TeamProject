package user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;
import java.util.Calendar;

import book.Book;
import book.BookManager;
import db.DBManager;


public class UserManager {
	//UserManager 데이터 종류
	private static	Vector<User>list;
	private static UserManager userInstance = new UserManager();
		public static UserManager getInstance() {
			System.out.print("인스턴스 반환");
			return userInstance;
		}
		public Vector<User> getUserVector(){
			return list;
		}
		
		//모든 책 가져오기
	public User findUser(String id){
		
		for( User findUser : list) {
			
			
			if(findUser.getID().equals(id))
			{
				return findUser;
				
			}
	}
		return null;
	}
	//모든 유저 가져와 초기화 메서드
	//관리지가 사용할 예정
	public void setAllUser() throws SQLException{
		list = new Vector<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		String sql ;
		try
		{	sql =  "select * from student";
			conn = DBManager.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user = new User();
				user.setName(rs.getString("Name"));
				user.setID(rs.getString("hakbun"));
				user.setIsAdmin(rs.getBoolean("is_admin"));
				user.setPassword(rs.getString("password"));
				list.add(user);
				
				System.out.println("유저 정보 리스트 출력(유저 매니저 setAllUser) : "+user.getID()+ " "+user.getName()+" "+user.getPassword());
			}	
				sql = "select * from borrowbooksanddates ";
				pstmt= conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				int n=0;
				while(rs.next()) {
					System.out.println(n);
					System.out.println(list.get(n).getID());
					list.get(n).setBorrowBooks(0, rs.getString("book1"));
					list.get(n).setBorrowDates(0, rs.getDate("date1"));
					list.get(n).setBorrowBooks(1, rs.getString("book2"));
					list.get(n).setBorrowDates(1, rs.getDate("date2"));
					list.get(n).setBorrowBooks(2, rs.getString("book3"));
					list.get(n).setBorrowDates(2, rs.getDate("date3"));
					list.get(n).setDelay(rs.getDate("delay_info"));
					n++;
				}
			
			for(int i =0; i<list.size();  i++) {
				String[] book =  list.get(i).getBorrowBooks();
				java.sql.Date[] date = list.get(i).getBorrowDates();
				System.out.println(list.get(i).getID()+" "+list.get(i).getName());
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
	//빌린 책 그리고 연체 날짜 저장 메서드
	//연체 여부 메서드
	
	//책 빌리는 메서드
	public String borrowBooks(User nowuser,Book borrowbook) {
		BookManager BOOKDB = new BookManager().getInstance();
		borrowbook =  BOOKDB.getlist().get(borrowbook.getId());
		System.out.println(borrowbook.getTotalCount());
		System.out.println(borrowbook.getTotalCount()>=borrowbook.getBorrowCount());
		System.out.println(borrowbook.getBorrowCount());
	
		
		
		Connection conn = null;
	
		java.sql.Date date1 =null ;
		String book[];
		java.sql.Date borrowDates[];
		try {
		java.util.Date dt = new java.util.Date();
		java.sql.Date date = new java.sql.Date(dt.getTime());
		int count = 0;
		for(User findUser : list) {
			
			System.out.println(findUser.getID());
			if(findUser.getID().equals(nowuser.getID()))
			{
				count = list.indexOf(findUser);
				break;
			}
			
		}
		nowuser = list.get(count);
		 borrowDates = nowuser.getBorrowDates();
		Calendar c = Calendar.getInstance();
		System.out.println("130번째 실행");
		if(nowuser.getIsDelay()!=null&&nowuser.getIsDelay().before(date)) {
			System.out.println("135번째 줄!! 책 연체됨 빌리기 불가!! 함수 종료");
			return "delay";
		}
		
		System.out.println("136번째 실행");
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
			
			
		
		if(borrowbook.getTotalCount()==borrowbook.getBorrowCount())
			{
				
					
					System.out.println("책빌리기 불가 책 수량 없음!!");
					
					return "bookStockOver";
				
			}
		else if(book[0]!=null&&book[1]!=null&&book[2]!=null) {
			System.out.println("빌릴 수 있는 책 개수 초과");
			return "bookOver";
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("데이터 베이스 연결 종료");
			DBManager.close();
		}
		
		
		try {
			conn = DBManager.connect();
			PreparedStatement pstmt = null;
			String sql = "update borrowbooksanddates set ";
			
			java.util.Date dt = new java.util.Date();
			java.sql.Date date = new java.sql.Date(dt.getTime());
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			book = nowuser.getBorrowBooks();
			if(book[0] ==null) {
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
	//책 반납 메서드
public void returnbook (User returnuser, Book returnbook) 
	{
	
	returnbook =  BookManager.getInstance().getlist().get(returnbook.getId());
	int count = 0;
	
	
	for(User findUser : list) {
		
		System.out.println(findUser.getID());
		if(findUser.getID().equals(returnuser.getID()))
		{
			count = list.indexOf(findUser);
			break;
		}
		
		}
	returnuser = list.get(count);
	System.out.println("282줄 : 유저 id : "+returnuser.getID());
	Connection conn = null;
	String[] borrowbookslist = returnuser.getBorrowBooks();
	System.out.println(borrowbookslist[0]);
	
	int countfind ;
	for(countfind=0 ; countfind < returnuser.getBorrowBooks().length; countfind++) {
		 if(borrowbookslist[countfind]!=null && borrowbookslist[countfind].equals(returnbook.getId())) {
			 System.out.println("인덱스 발견"+countfind);
			 break;
		 }
		 
		}
	
	
	java.sql.Date delay_info = null;
	
	java.util.Date dt = new java.util.Date();
	java.sql.Date date = new java.sql.Date(dt.getTime());
	Calendar c1 = Calendar.getInstance();
	PreparedStatement pstmt = null;
	try {
			System.out.println("책 반납 실행!!");
			System.out.println(returnbook.getName());
			java.sql.Statement stmt;
			
			java.util.Date set_Date;
			int diffDays;
			set_Date = returnuser.getBorrowDates()[countfind];
			diffDays = (int) ((dt.getTime()-set_Date.getTime()) / (24*60*60*1000));
			 System.out.println(returnuser.getIsDelay());
			 
				 if(diffDays<7 && returnuser.getIsDelay()==null) {
				delay_info = null;
				
				 }
				 else if(diffDays>7&&returnuser.getIsDelay()==null)
				 {
				   c1.setTime(date);
				   c1.add(Calendar.DATE, diffDays-7);
				   delay_info = new java.sql.Date(c1.getTimeInMillis());
				 }
				 else if(diffDays>7 && returnuser.getIsDelay()!=null) {
					 c1.setTime(delay_info);
					 c1.add(Calendar.DATE, diffDays-7);
					 delay_info = new java.sql.Date(c1.getTimeInMillis());
				 }
				 else {
					 delay_info=null;
				 }
				 
				 
			 
			 returnuser.setBorrowDates(countfind,null);
			 returnuser.setBorrowBooks(countfind, null);
			
			String sql = "update borrowbooksanddates set ";
			sql += "book"+(countfind +1)+ " = ?,";
			sql += "date"+(countfind +1)+" = ?,";
			sql += "delay_info = ? where(hakbun = ?)";
			conn = DBManager.connect();	
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setDate(2, null);
			pstmt.setDate(3,delay_info);
			pstmt.setString(4,returnuser.getID());
			pstmt.executeUpdate();
			returnbook.subCount();
			sql = "update book_list set borrowcount = ? where(id = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,returnbook.getBorrowCount());
			pstmt.setString(2,returnbook.getId());
			pstmt.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			DBManager.close();
			}
}
		
	
	
	
	
	
	


	//유저 추가
	public void insertUser(User newuser) {
		Connection conn=null;
		java.sql.Statement stmt;
		
		
		
		try {
			conn=DBManager.connect();
			System.out.println("데베 유저 추가");
			stmt = conn.createStatement();
			String hakbun = newuser.getID();
			String name  = newuser.getName();
			String password = newuser.getPassword();
			int isadmin ;
			if(newuser.getIsAdmin()) {
				isadmin = 1;
			
			}
			else
				isadmin = 0;
			
			String sql =  " insert into student(hakbun, name, password, is_admin) VALUES('"+hakbun+"', '"+name+"' ,'"+password+"' ,'"+isadmin+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			 sql =  " insert into borrowbooksanddates(hakbun) VALUES('"+hakbun+"')";
			 stmt.executeUpdate(sql);
			 list.add(newuser);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		DBManager.close();
	}
	
}
	//유저 삭제 메서드
	public void deleteUser(User deleteuser)  {
	
		Connection conn=null;
		java.sql.Statement stmt;
		ResultSet result;
		for(User isUser : list) {
			if(deleteuser.getID().equals(isUser.getID())) {
				deleteuser = isUser;
					System.out.println("여기 값있음....");
					for(int i =0; i<deleteuser.getBorrowBooks().length;i++)
					{
						if(deleteuser.getBorrowBooks()[i]!=null) {
							System.out.println("책 반납후 회원탈퇴 바람!!");
							return;
					}
						}
					System.out.println("유저 리스트 삭제");
					list.remove(isUser);
					break;
			
			}
		}
		
		try {
			
		conn=DBManager.connect();
		stmt = conn.createStatement();
		String delete = "delete from student where hakbun = "+deleteuser.getID();
		stmt.executeUpdate(delete);
		System.out.println("데베 유저 삭제");
		
				
				
		
		}	
	catch(Exception e) {
		e.printStackTrace();
	}finally {
		DBManager.close();
		
	}
		
	}
	//유저 정보변경
	public void changeInform(User userInformation) {
		for(User changeUser : list) {
			int count;
			System.out.println(userInformation.getID());
			if(userInformation.getID().equals(changeUser.getID()))
			{
				count = list.indexOf(changeUser);
				list.set(count, userInformation);
				break;
			}
			
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "update student set password = ? where(hakbun = ?)";
		try {
		conn = DBManager.connect();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,userInformation.getPassword());
		pstmt.setString(2, userInformation.getID());
		pstmt.executeUpdate();
			}
		catch(Exception e) {
			e.printStackTrace();
			}
		finally {
			DBManager.close();
		}
		}
	
	//로그인도와주는 메서드
	
	public boolean Login(String getId, String getPass) {
		boolean isuser = false;
		System.out.println("유저 데베 찾기");
		for(User isUser : list) {
			if(getId.equals(isUser.getID())) {
				if(getPass.equals(isUser.getPassword())) {
					System.out.println("여기 값있음");
					isuser= true;
					
					break;
				}
				else {
					System.out.println("여기 비밀번호가 없습니다");
					isuser= false;
				}
			}	
			else {
				System.out.println("여기 학번이 없음");
				isuser= false;
			}
		}
		return isuser;
	}
	
	//관리자 정보 출력 메서드
	

}
	