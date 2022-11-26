<<<<<<<< HEAD:src/gui/DelayNoticeRunnable.java
package GUIPackage;
========
package gui.util;
>>>>>>>> 511e71a (패키지 분류 및 이름 변경):src/gui/util/DelayNoticeRunnable.java
//package GUIPackage;
//
//import java.time.LocalDate;
//
//public class delayNoticeRunnable implements Runnable{
//
//	@Override
//	public void run() {
//		int index = 0;
//		String bookName = null;
//		LocalDate borrowDay = null;
//		LocalDate delayDay = null;
//		
//		
//		while(true) {
//			try {
//				Thread.sleep(30000);	//30000ms(=30sec) 대기 
//				
//				bookName = null;
//				delayDay = null;
//				borrowDay.
//				LocalDate now = LocalDate.now();
//					
//				str = MainPage.getDelayNoticeLabel().setText(str);
//				
//				
//				MainPage.getDelayNoticeLabel().setText(str);
//				
//				index++;
//				if(index>=3)
//					index = 0;
//
//			}
//			catch(InterruptedException e) {	//인터럽트 발생시 스레드 종료
//				return;
//			}
//
//		}
//	}
//}
