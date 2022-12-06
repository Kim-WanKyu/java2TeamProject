package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.sql.Date;
import java.awt.*;

import gui.page.mainPage.mainUserPage.MainUserPageComponent;
import gui.page.startPage.StartPageComponent;
import gui.table.MyBookTable;
import user.User;

public class DelayNoticeRunnable implements Runnable{
	
	@Override
	public void run() {
		User userInfo = user.UserManager.getInstance().findUser(StartPageComponent.getUser().getID());
		String userName = userInfo.getName();	//유저 이름
		
		StringBuffer str1 = new StringBuffer();	//책 개별 연체정보 출력할 문자열버퍼변수
		StringBuffer str2 = new StringBuffer();	//연체 시 다시 빌릴 수 있는 날짜 출력할 문자열버퍼변수
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");	//LocalDate 포맷 설정
		
		//delayNoticeLabel의 폰트와 색 설정
		MainUserPageComponent.getDelayNoticeLabel1().setFont(new Font("Gothic", Font.BOLD, 10));
		MainUserPageComponent.getDelayNoticeLabel2().setFont(new Font("Gothic", Font.BOLD, 10));
		MainUserPageComponent.getDelayNoticeLabel2().setForeground(Color.RED);

		int index=0;
		while(true) {
			MyBookTable myBookTable = new MyBookTable();

			str1.delete(0, str1.length());	//문자열버퍼 지움
			str2.delete(0, str2.length());	//문자열버퍼 지움
			
			LocalDate now = LocalDate.now();	//현재 날짜			
			try {
				//1. 개별 도서 연체 알림
				//빌린 도서가 1개 이상인 경우
				if(myBookTable.getMyBookTable().getRowCount() > 0) {
					//책 반납기한
					LocalDate bookReturnDate = LocalDate.parse(myBookTable.getMyBookTable().getValueAt(index, 7).toString(), formatter);	//LocalDate 포맷에 맞게 테이블의 문자열 파싱

					String bookName = myBookTable.getMyBookTable().getValueAt(index, 0).toString();	//도서명
					//이름이 너무 긴 경우 문자열 자름(최대25자)
					if(bookName.length() > 25) { bookName = bookName.substring(0, 25) + "…"; }
					
					if(bookReturnDate != null) {	//bookReturnDate가 null이 아니면 => 미반납 도서 존재
						int dayDiff = (int) ChronoUnit.DAYS.between(bookReturnDate, now);	//dayDiff = 반납기한과 오늘의 일수 차이 계산						
						if(dayDiff > 0) {	//미반납 도서 연체O
							MainUserPageComponent.getDelayNoticeLabel1().setForeground(Color.RED);
							str1.append(bookName); str1.append("은(는) 반납일로부터 "); str1.append(dayDiff); str1.append("일 지났습니다.");
						}
						else {	//미반납 도서 연체X
							MainUserPageComponent.getDelayNoticeLabel1().setForeground(Color.BLUE);
							str1.append(bookName); str1.append("은(는) 반납일까지 "); str1.append(-dayDiff); str1.append("일 남았습니다.");
						}
					}
				}
				else {	//빌린 도서가 하나도 없는 경우
					MainUserPageComponent.getDelayNoticeLabel1().setForeground(Color.BLACK);
					str1.append(userName); str1.append("님은 대여중인 도서가 없습니다.");
				}
				MainUserPageComponent.getDelayNoticeLabel1().setText(str1.toString());	//개별 책 알림 텍스트 세팅
				
				
				//2. 연체 시 재대출 가능 일자 알림
				Date delayDate = userInfo.getDelayDate();	//연체기록일자
				if(delayDate != null) {	//연체기록이 있는 경우
					LocalDate localDelayDate = delayDate.toLocalDate();	//연체기록일자
					
					int delayDiff = 0;	//미반납 도서의 연체일수
					if(myBookTable.getMyBookTable().getRowCount() > 0){	//미반납 도서가 있는 경우
						LocalDate[] bookReturnDates = new LocalDate[3];	//책 반납기한 
						//반납기한과 오늘의 일수 차이 계산
						for(int i=0; i<myBookTable.getMyBookTable().getRowCount(); i++) {
							bookReturnDates[i] = LocalDate.parse(myBookTable.getMyBookTable().getValueAt(i, 7).toString(), formatter);	//빌린 모든 도서를 반납일자를 체크
							//미반납 도서가 연체된 도서이면 연체일수 더해줌
							if(bookReturnDates[i].isBefore(now))
								delayDiff += ChronoUnit.DAYS.between(bookReturnDates[i], now);
						}
					}
					localDelayDate = localDelayDate.plusDays(delayDiff);	//연체 기록 일자 += 미반납 도서의 연체일수
					
					//연체기록의 재대출 가능 일자가 오늘 이후이면, (기록상 아직 연체 남음)
					if ( localDelayDate.isAfter(now) ) {
						MainUserPageComponent.getDelayNoticeLabel2().setForeground(Color.RED);
						str2.append(userName); str2.append("님은 오늘 기준 ");
						str2.append(ChronoUnit.DAYS.between(now ,localDelayDate)); str2.append("일 후 대여할 수 있습니다.");
					}
				}
				else if(myBookTable.getMyBookTable().getRowCount() > 0){	//연체기록은 없으나, 미반납 도서가 있는 경우
					LocalDate[] bookReturnDates = new LocalDate[3];	//책 반납기한 
					//반납기한과 오늘의 일수 차이 계산
					int delayDiff = 0;
					for(int i=0; i<myBookTable.getMyBookTable().getRowCount(); i++) {
						bookReturnDates[i] = LocalDate.parse(myBookTable.getMyBookTable().getValueAt(i, 7).toString(), formatter);	//빌린 모든 도서를 반납일자를 체크
						//미반납 도서가 연체된 도서이면 연체일수 더해줌
						if(bookReturnDates[i].isBefore(now))
							delayDiff += ChronoUnit.DAYS.between(bookReturnDates[i], now);
					}
					
					if(delayDiff > 0) {	//미반납 도서가 있고, 연체이면
						MainUserPageComponent.getDelayNoticeLabel2().setForeground(Color.RED);
						str2.append(userName); str2.append("님은 오늘 기준 "); str2.append(delayDiff); str2.append("일 후 대여할 수 있습니다.");
					}
					else {	//미반납 도서는 있으나 연체X이면
						MainUserPageComponent.getDelayNoticeLabel2().setForeground(Color.BLACK);
						str2.append(userName); str2.append("님은 현재 대여 가능합니다.");
					}
				}
				else {	//연체기록이 없고, 빌린 도서도 없는 경우
					MainUserPageComponent.getDelayNoticeLabel2().setForeground(Color.BLACK);
					str2.append(userName); str2.append("님은 현재 대여 가능합니다.");
				}
				MainUserPageComponent.getDelayNoticeLabel2().setText(str2.toString());	//재대출 가능 일자 알림 텍스트 세팅


				Thread.sleep(5000);	//5000ms = 5sec 대기
				
				index++;
				if (myBookTable.getMyBookTable().getRowCount() <= index)
					index = 0;
				
				//isDisplayable() = 화면에 표시될 수 있는 상태이면 true / 즉, 메인 유저 창을 벗어나면 스레드 종료
				if(! MainUserPageComponent.getDelayNoticeLabel1().isDisplayable()) {
					System.out.println("스레드break;");
					break;
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.print("도서 삭제");	//스레드 실행 중 도서 반납 시 발생하는 인덱스 오류
			} catch(InterruptedException e) {
				System.out.print("스레드종료");	//인터럽트 발생시 스레드 종료
				return;
			} 
		}
	}
}