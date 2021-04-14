package ch15;

import java.util.*;

public class PQTest {

	public static void main(String[] args) {
		test1();
	}
	
	public static void test1() {
		Queue<String> q = new PriorityQueue<>();
		
		q.offer("PineApple"); // q.add()
		q.offer("Banana");
		q.offer("Carrot");
		q.offer("Cherry");
		q.offer("Strawberry");
		
		// 엿보기. 인출은 아냐.
		System.out.println(q.peek()); // q.element()
		System.out.println(q.peek());
		System.out.println(q.peek());
		System.out.println("###############");
		while(q.size() > 0 ) {
			System.out.println(q.poll()); // q.remove()
		}
		
	}

}
