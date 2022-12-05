package util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.awt.*;

import gui.page.mainPage.mainUserPage.MainUserPageComponent;
import gui.page.startPage.StartPageComponent;
import gui.table.MyBookTable;
import user.User;

public class DelayNoticeRunnable implements Runnable{
	
	@Override
	public void run() {
		System.out.println("스레드");
		User userInfo = user.UserManager.getInstance().findUser(StartPageComponent.getUser().getID());
		String userName = userInfo.getName();
		
		StringBuffer str1 = new StringBuffer();	//책 개별 연체정보 출력할 문자열버퍼변수
		StringBuffer str2 = new StringBuffer();	//연체 시 다시 빌릴 수 있는 날짜 출력할 문자열버퍼변수
		//책 빌린 날짜 & 반납기한
		Date[] borrowDay = userInfo.getBorrowDates();
		
		Date[] returnDay = new Date[3];
		Calendar c = Calendar.getInstance();
		
		Date delayDate = userInfo.getDelayDate();
		
		//현재 날짜
		Date now = new Date();
		

		String bookName = null;
		
		//delayNoticeLabel의 폰트와 색 설정
		MainUserPageComponent.getDelayNoticeLabel1().setFont(new Font("Gothic", Font.BOLD, 10));
		MainUserPageComponent.getDelayNoticeLabel2().setFont(new Font("Gothic", Font.BOLD, 10));
		MainUserPageComponent.getDelayNoticeLabel2().setForeground(Color.RED);

		int index=0;
		while(true) {
			MyBookTable myBookTable = new MyBookTable();
			try {
				//returnDay 계산
				for(int i=0;i<3;i++) {
					if(borrowDay[i]!=null) {
						c.setTime(borrowDay[i]);
						c.add(Calendar.DATE,7);
						returnDay[i] = new Date(c.getTimeInMillis());
					}
				}
				
				if(returnDay[index] != null) {
					LocalDate localReturnDay = LocalDate.ofInstant(returnDay[index].toInstant(), ZoneId.systemDefault());	//반납일
					LocalDate localNow = LocalDate.ofInstant(now.toInstant(), ZoneId.systemDefault());	//현재

					int dayDiff = (int) ChronoUnit.DAYS.between(localReturnDay, localNow);	//반납기한과 오늘날짜와의 차이
					
					//도서명
					bookName = myBookTable.getMyBookTable().getValueAt(index, 0).toString();
					
					//이름이 너무 긴 경우 문자열 자름
					if(bookName.length() > 20)
						bookName = bookName.substring(0, 20) + "…";
					
					//개별 책 연체 알림
					str1.delete(0, str1.length());	//문자열버퍼 지움
					if(dayDiff < 0) {
						MainUserPageComponent.getDelayNoticeLabel1().setForeground(Color.BLUE);
						str1.append(bookName); str1.append("은(는) 반납일까지 "); str1.append(-dayDiff); str1.append("일 남았습니다.");
					}
					else {
						MainUserPageComponent.getDelayNoticeLabel1().setForeground(Color.RED);
						str1.append(bookName); str1.append("은(는) 반납일로부터 "); str1.append(dayDiff); str1.append("일 지났습니다.");
					}

					//재대출 가능 기간 알림
					str2.delete(0, str2.length());	//문자열버퍼 지움
					LocalDate localDelayDate;
					if(delayDate != null) {
						localDelayDate = LocalDate.ofInstant(delayDate.toInstant(), ZoneId.systemDefault());
						
						if (/*임시*/ (int) (ChronoUnit.DAYS.between(localDelayDate, localNow)) > 0 /*연체자이면,*/) {					
							str2.append(userName); str2.append("님은 오늘 기준 ");
							str2.append(ChronoUnit.DAYS.between(localDelayDate, localNow)); str2.append("일 후 대여할 수 있습니다.");
						}
						else { str2.append(" "); }
					}
					//알림 텍스트set
					MainUserPageComponent.getDelayNoticeLabel1().setText(str1.toString());
					MainUserPageComponent.getDelayNoticeLabel2().setText(str2.toString());
				}
				else {	//빌린 도서가 없는 경우
					MainUserPageComponent.getDelayNoticeLabel1().setText(" ");
					MainUserPageComponent.getDelayNoticeLabel2().setText(" ");
				}
				
				Thread.sleep(5000);	//5000ms = 5sec
				
				
				index++;
				if (myBookTable.getMyBookTable().getRowCount() <= index)
					index = 0;
				
				//isDisplayable() = 화면에 표시될 수 있는 상태이면 true / 즉, 메인 유저 창을 벗어나면 스레드 종료
				if(! MainUserPageComponent.getDelayNoticeLabel1().isDisplayable()) {
					System.out.println("스레드break;");
					break;
				}
			} catch(InterruptedException e) {
				System.out.print("");	//인터럽트 발생시 스레드 종료
				return;
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.print("삭제");	//스레드 실행 중 도서 반납 시 발생하는 인덱스 오류
				return;
			}
		}
	}
}