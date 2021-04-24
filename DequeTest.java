package ch15;
import java.util.*;

public class DequeTest {

	public static void main(String[] args) {
		// deque = 인터페이스. 구현 클래스가 ArrayDeque.
		// 인터페이스의 인스턴스 생성은 X
		Deque<Integer> deque = new ArrayDeque<>();
		// Deque<Integer> deque = new LinkedList<>();
		
		/*
		Queue : a, b 메소드 있다면
		ArrayDeque : a, b, c, d, e 메소드 가능?
		q.a() q.b() <- O
		q.c() <- X
		*/
		
		// 1. 큐에 임의 수 10개 넣고
		// 2. 안에 어떤 순서로 들어가 있는지 보고
		// 3. 하나씩 인출해보자.
		
		for(int i=0;i<10;i++) {
			int n = (int)(Math.random()*10);
			deque.add(n);
		}
		
		// poll 메소드로 하나씩 인출. FIFO순으로 나와야 한다.
		
//		for(int i=0;i<10;i++) {
//			deque.add(i+1);
//		}
		
		System.out.println(deque);
		
		while  (!deque.isEmpty()) {
				Integer val = deque.poll(); // 큐의 사이즈 1씩 감소
				System.out.println(val);
			}
		
		System.out.println("큐의 상태...");
		System.out.println(deque);
		//queue에서는 poll하면 값 삭제. unlike Iterate type
		}
		
		

	}


