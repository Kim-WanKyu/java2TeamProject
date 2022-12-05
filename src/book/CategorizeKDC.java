package book;

//KDC와 분류명 (KDC코드의 100, 10의 자리만 사용함)
public class CategorizeKDC {
		 //분류 카테고리명
	private final static String[][] categoryName =
		{
			{//000 총류
				"총류",							//000
				"총류_도서학, 서지학",				//010
				"총류_문헌정보학",					//020
				"총류_백과사전",					//030
				"총류_일반 논문집",					//040
				"총류_일반 연속간행물",				//050
				"총류_일반 학회, 단체, 협회, 기관",		//060
				"총류_신문, 언론, 저널리즘",			//070
				"총류_일반 전집, 총서",				//080
				"총류_X" },						//090
			
			{//100 철학
				"철학",							//100
				"철학_형이상학",					//110
				"철학_X",							//120
				"철학_철학의 체계",					//130
				"철학_경학",						//140
				"철학_아시아(동양) 철학, 사상",			//150
				"철학_서양철학",					//160
				"철학_논리학",						//170
				"철학_심리학",						//180
				"철학_윤리학, 도덕철학" },			//190
			
			{//200 종교
				"종교",							//200
				"종교_비교종교학",					//210
				"종교_불교",						//220
				"종교_기독교",						//230
				"종교_도교",						//240
				"종교_천도교",						//250
				"종교_신도",						//260
				"종교_바라문교, 인도교",				//270
				"종교_회교(이슬람교)",				//280
				"종교_기타 제종교" },				//290

			{//300 사회과학
				"사회과학",						//300
				"사회과학_통계학",					//310
				"사회과학_경제학",					//320
				"사회과학_사회학, 사회문제",			//330
				"사회과학_정치학",					//340
				"사회과학_행정학",					//350
				"사회과학_법학",					//360
				"사회과학_교육학",					//370
				"사회과학_풍속, 민속학",				//380
				"사회과학_국방, 군사학" },			//390
			
			{//400 순수과학
				"순수과학",						//400
				"순수과학_수학",					//410
				"순수과학_물리학",					//420
				"순수과학_화학",					//430
				"순수과학_천문학",					//440
				"순수과학_지학",					//450
				"순수과학_광물학",					//460
				"순수과학_생물과학",					//470
				"순수과학_식물학",					//480
				"순수과학_동물학" },					//490

			{//500 기술과학
				"기술과학",						//500
				"기술과학_의학",					//510
				"기술과학_농업, 농학",				//520
				"기술과학_공학, 공업일반",				//530
				"기술과학_건축공학",					//540
				"기술과학_기계공학",					//550
				"기술과학_전기공학, 전자공학",			//560
				"기술과학_화학공학",					//570
				"기술과학_제조업",					//580
				"기술과학_가정학 및 가정생활" },		//590

			{//600 예술
				"예술",							//600
				"예술_건축술",						//610
				"예술_조각",						//620
				"예술_공예, 장식미술",				//630
				"예술_서예",						//640
				"예술_회화, 도화",					//650
				"예술_사진술",						//660
				"예술_음악",						//670
				"예술_연극",						//680
				"예술_오락, 운동" },				//690

			{//700 언어
				"언어",							//700
				"언어_한국어",						//710
				"언어_중국어",						//720
				"언어_일본어",						//730
				"언어_영어",						//740
				"언어_독일어",						//750
				"언어_프랑스어",					//760
				"언어_스페인어",					//770
				"언어_이탈리아어",					//780
				"언어_기타 제어" },					//790

			{//800 문학
				"문학",							//800
				"문학_한국문학",					//810
				"문학_중국문학",					//820
				"문학_일본문학",					//830
				"문학_영미문학",					//840
				"문학_독일문학",					//850
				"문학_프랑스문학",					//860
				"문학_스페인문학",					//870
				"문학_이탈리아문학",					//880
				"문학_기타 제문학" },				//890
			
			{//900 역사
				"역사",							//900
				"역사_아시아(아세아)",				//910
				"역사_유럽(구라파)",					//920
				"역사_아프리카",					//930
				"역사_북아메리카(북미)",				//940
				"역사_남아메리카(남미)",				//950
				"역사_오세아니아(대양주)",				//960
				"역사_양극지방",					//970
				"역사_지리",						//980
				"역사_전기" }						//990
				
		};//String[][] categoryName 끝 지점

	//KDC코드로 분류명 찾아내 리턴
	public static String getCategoryname(String kdc) {
		String category = null;
		if(kdc!=null && kdc.length()!=0) {
			int i = Character.getNumericValue(kdc.charAt(0));
			int j = Character.getNumericValue(kdc.charAt(1));
			category = categoryName[i][j];
		}
		return category;
	}

	//분류명으로 KDC코드 찾아내 리턴
	public static String getKDCCode(String category) {
		String code = null;		//리턴할 KDC코드 문자열
		boolean isFind = false;	//찾았는지 체크하는 boolean변수
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(category.equals(categoryName[i][j])==true) {
					code = "" + i + j + '0';
					isFind = true;
					break;
				}
			}
			if(isFind) { break; }
		}
		
		return code;
	}
}