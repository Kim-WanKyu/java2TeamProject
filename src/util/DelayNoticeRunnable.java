package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.awt.*;

import gui.page.mainPage.mainUserPage.MainUserPageComponent;

public class DelayNoticeRunnable implements Runnable{
	
	@Override
	public void run() {
		String bookName = null;
		String userName = null; /* = StartPage.loginUser.getName()*/ //?
		
		StringBuffer str1 = new StringBuffer();	//책 개별 연체정보 출력할 문자열버퍼변수
		StringBuffer str2 = new StringBuffer();	//연체 시 다시 빌릴 수 있는 날짜 출력할 문자열버퍼변수
		
		//임시 책 빌린 날짜 & 반납기한
		String[] string = {"2022-11-10", "2022-11-20", "2022-11-30", "2022-12-02"};
		LocalDate[] borrowDay = {LocalDate.parse(string[0], DateTimeFormatter.ISO_DATE), LocalDate.parse(string[1], DateTimeFormatter.ISO_DATE), LocalDate.parse(string[2], DateTimeFormatter.ISO_DATE)};
		LocalDate[] returnDay = {borrowDay[0].plusDays(7), borrowDay[1].plusDays(7), borrowDay[2].plusDays(7)};
		LocalDate delayDay = LocalDate.parse(string[3], DateTimeFormatter.ISO_DATE);
		
		//현재 날짜
		LocalDate now = LocalDate.now();
		
		//delayNoticeLabel의 폰트와 색 설정
		MainUserPageComponent.getDelayNoticeLabel1().setFont(new Font("Gothic", Font.BOLD, 10));
		MainUserPageComponent.getDelayNoticeLabel2().setFont(new Font("Gothic", Font.BOLD, 10));
		MainUserPageComponent.getDelayNoticeLabel2().setForeground(Color.RED);

		int index=0;
		while(true) {
			try {
				int dayDiff = (int) ChronoUnit.DAYS.between(returnDay[index], now);	//반납기한과 오늘날짜와의 차이
				
				//테이블에 있는 도서명으로 / (String) /*MyBookTable myBookTable; myBooktable.getMyBookTable().getValueAt(index, 0);*/
				bookName = "쿠키런ㅂ서바이벌ㅂ대작전ㅂ사사ㅂ:ㅃ새로운ㅃ모험의ㅃ시작ㅃ안전상식ㅃ학습만화"; /*임시*/
				
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
				if (/*임시*/ (ChronoUnit.DAYS.between(delayDay, now)) > 0 /*연체자이면,*/) {
					str2.append(userName); str2.append("님은 오늘 기준 ");
					str2.append(ChronoUnit.DAYS.between(delayDay, now)); str2.append("일 후 대여할 수 있습니다.");
				}
				else { str2.append(" "); }
				
				//알림 텍스트set
				MainUserPageComponent.getDelayNoticeLabel1().setText(str1.toString());
				MainUserPageComponent.getDelayNoticeLabel2().setText(str2.toString());
				
				Thread.sleep(5000);	//5000ms = 5sec
				
				index++;
				if (/*mainUserPageComponent.getMyBookTable().getRowCount()*/ /*임시*/ 3 <= index)	index = 0;
				
				//isDisplayable() = 화면에 표시될 수 있는 상태이면 true / 즉, 메인 유저 창을 벗어나면 스레드 종료
				if(! MainUserPageComponent.getDelayNoticeLabel1().isDisplayable()) {
					System.out.println("스레드break;");
					break;
				}
			} catch(InterruptedException e) {	//인터럽트 발생시 스레드 종료
				return;
			}
		}
	}
}