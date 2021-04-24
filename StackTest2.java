package ch15;

import java.util.StringTokenizer;

public class StackTest2 {

	public static void main(String[] args) {
		String str = "apple banana carrot pineapple grape mango strawberry watermelon melon orange coconut kiwi lemon tomato cherry blueberry peach cramberry rasberry";

		String s = str.substring(0,"apple".length());
		System.out.println(s);
		// 공백 문자를 기준으로 하나씩 잘라 주세요.
		// 구분자를 입력으로 주고, 그 구분자로 구분되는 문자열들을 하나씩 하나씩 뽑아쓸 수 있게 해주는 java.util 패키지의 클래스가 뭐냐 하면
		// StringTokennizer
		
		StringTokenizer st = new StringTokenizer(str, " ");
		String ss = st.nextToken();
		System.out.println(ss);
		
		ss=st.nextToken();
		System.out.println(ss);
		
		System.out.println("token 수: " + st.countTokens());
		// 토큰을 다 찍어 보는 방법1 : 토큰의 수만큼 for문 돌기
		// 토큰을 다 찍어 보는 방법2 : 아직 나에게 줄 토큰이 남아 있는지 물어보고 있으면 nextToken() 메소드를 호출해서 하나씩 받아서 사용하기
		// 주로 2번째 방법을 많이 사용
		
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println("["+token+"]");
		}
	}

}
