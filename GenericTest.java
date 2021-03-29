package ch14;

public class GenericTest {

	public static void main(String[] args) {
		GenericBox<String> box1 = new GenericBox<>();
		
		box1.setContent("동해물과");
		// box1.setContent(100); // 오류 
		String s = box1.getContent();
		System.out.println(s);
		
		GenericBox<Integer> box2 = new GenericBox<Integer>();
		box2.setContent(10);
		// box2.setContent("동해물과"); // 오류
		
		GenericBox<Student> box3 = new GenericBox<>();
		box3.setContent(new Student());
		Student std = box3.getContent();
		// box3.setContent("동해물과"); // 오류
		// box3.setContent(10); // 오류
	}

}
