package ch15;

import java.util.*;

public class ListTest {

	public static void main(String[] args) {
		test1();

	}

	public static void test1() {
		// List<String> list = new ArrayList<>();
		List<String> list = new LinkedList<>();
		String[] sArr = {"자동차", "운동장", "유치원", "학교"};
		

		for(String s : sArr) list.add(s);
		
		// 리스트 내 원소 확인법(1) : list출력
		System.out.println(list); // ArrayList의 toString()이 이용됨
		
		
		// 리스트 내 원소 확인법(2) : get()사용
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		System.out.println();
		
		
		// 리스트 내 원소 확인법(3): for-each문 사용
		for (String s : list) System.out.print(s+" ");
		System.out.println("\n");
		
		
		// 리스트 내 원소 확인법(4): iterator 사용
		Iterator<String> iter = list.iterator();
		// unchecked exception은 버그
		// 이런 exception 자체가 발생하지 않도록 코딩하자!
		// iter.hasNext(); // 줄 거 있으면 true, 없으면 false
		
		
		// 다시 순회하기
		while(iter.hasNext())
			System.out.println(iter.next()+" ");
		
		iter=list.iterator();
		while(iter.hasNext())
			System.out.println(iter.next()+" ");
		
		System.out.println();

		
		// overloading된 add 메소드로 원소를 중간에 삽입할 수 있다.
		list.add(1, "벚꽃");
		list.remove(3); // 네 번째 원소 삭제
		System.out.println(list);
		
	}
}
