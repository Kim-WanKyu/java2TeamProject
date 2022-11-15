package User;

public class login {
	public static void main(String[] args) {
			
			USERDAO loguser = new USERDAO().getInstance();
			User a = new User();
			a.setID("201601826");
			a.setName("유동주");
			a.setPassword("jdj2016@@");
			a.setIsAdmin(0);
			loguser.insertUser(a);
			
//	loguser = loguser.getInstance();
//	User a = new User();
//	
//	
//	try {
//	loguser.setAllUser();
//	boolean isUser = loguser.Login("201901769","정동주");//텍스트 필드에 넣었다고 가정한 값
//	System.out.println(isUser);
//	}catch(Exception e) {
//		
//	}
	}
}


//user list이거 이거는 static
//user 추가 메서드... 이거
//데베 연결해서 값만 추가.... 데베 연결 종료
//User 하나를 새로 만들어서
//그리고 user list에 새로운 user 추가
//이렇게 하면은 됨