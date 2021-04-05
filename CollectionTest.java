package ch15;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.IntStream;

public class CollectionTest {

	
	public static void main(String[] args) {
		//test2();
		//test3();
		//setTest1();
		Lotto();
	}
	
	public static void test1() {
		/*
		 List : 순서가 있고, 중복이 허용됨
		 */
		List<Integer> list1 = new ArrayList<>();
		List<String> list2 = new LinkedList<>();
		List<Double> list3 = new Vector<>(); // 오래됨 -> 권장 X
		List<Integer> list4 = new Stack<>();
		
		for (int i =0;i<10;i++) {
			list1.add(i);
			list2.add(String.valueOf(i+1));
			list3.add((i+1*1.0));
			list4.add(10-i);
		}
		
		System.out.println(list1);
		System.out.println(list2);
		System.out.println(list3);
		System.out.println(list4);
		
		System.out.println("---리스트 생성---");
		
		
		
		for(int i=0;i<list1.size();i++) {
			System.out.print(list1.get(i)+" ");
		}
		System.out.println();
		
		
		for(String s : list2) {
			System.out.print(s+", ");
		}
		System.out.println();
		
		
		Iterator<Double> iter = list3.iterator();
		while(iter.hasNext()) {
			Double d = iter.next();
			System.out.print(d+" ");
		}
		
		System.out.println("\n---리스트 열람---");
		
		// linkedArray는 배열 X(연속된 공간이 아니라 동적 메모리 할당)
		// 중간 삽입/삭제 면에서 linkedArray가 우수함
	}

	public static void test2() {
		List<Integer> list = new ArrayList<>();
		// List<Integer> list = new LinkedList<>();
//		for (int i = 0; i <100000;i++) {
//			list.add(i+1);
//		}
		IntStream.rangeClosed(1,100000).forEach(i ->
		list.add(i));
		
		long start = System.currentTimeMillis();
//		for(int i=0;i<1000;i++) {
//			list.add(30,1000);
//		}
		
		//이것은 arraylist가 압승
		for(int i=0;i<list.size();i++) {
			list.get(i);
		}
		
		
		long end = System.currentTimeMillis();
		
		System.out.println((end - start) + "ms Elapsed...");
	}

	public static void test3() {
		ArrayList<String> list = new ArrayList<>();
		list.add("MILK");
		list.add("BREAD");
		list.add("BUTTER");
		System.out.println(list);
		list.add(1,"APPLE");
		System.out.println("Apple을 1번 인덱스에 추가한 후: " + list);
		list.set(2,  "GRAPE");
		System.out.println("2번 인덱스의 원소를 GRAPE로 변경한 후: " + list);
		list.remove(3);
		System.out.println("3번 인덱스의 원소를 삭제한 후: " + list);
		
		
		// for문을 사용할 수 없는 아이들?
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		iter = list.iterator(); // re-
		
		iter.next();
		System.out.println("끝");
	}

	public static void setTest1() {
		Set<String> set = new HashSet<>();
		String[] strArr = {"단어", "중복", "구절", "중복"};
		for(String s : strArr) {
			if(set.add(s)==false) {
				System.out.println(s+"값은 이미 존재!");
			}
		}
		System.out.println(set);
	}
	
	class MyComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	public static void Lotto() {
		// Set<Integer> lottoNums = new HashSet<>();
		// Set<Integer> lottoNums = new TreeSet<>();
		 Set<Integer> lottoNums = new LinkedHashSet<>(); // 들어간 순서대로
		
		
		/*
		MyComparator comp = new MyComparator();
		Set<Integer> lottoNums = new TreeSet<>(comp);
		*/
		
		
		int num;
		
		for(int i=0;i<6;i++) {
			num = (int)(Math.random()*45)+1;
			lottoNums.add(num);
		}
		
		System.out.println(lottoNums);
		
		Iterator<Integer> iter = lottoNums.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		
		System.out.println();
	}

}
