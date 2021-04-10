package ch15;
import java.io.*;
import java.util.*;

public class SetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	public static void test1() {
		File file = new File("src/ch15/wordbook.txt");
		Set<String> set = new HashSet<>();
		if (file.exists()==true) {
			System.out.println(file.getAbsolutePath() + ": 존재함");
		} else {
			System.out.println(file.getAbsolutePath() + "존재하지 않음");
		}
		
		/*
		파일 내용을 읽자
		파일에 읽고 쓰려면 stream 객체를 이용해야 한다.
		읽을 때는 Input Stream / 쓸 때는 Output Stream
		그런데 문자 단위로 읽고 쓸 때는 문자 스트링을 이용하는 것이 편리
		문자단위 입력 스트링은 Reader 객체로 표현된다
		문자단위의 출력 스트링은 writer 객체로 표현된다
		*/
		
		int cnt=0;
		BufferedReader bReader = null;
		try {
			// 한	 문자씩 읽음 
			FileReader fileReader;
			fileReader = new FileReader(file);
			// 라인 단위로 읽음
			bReader = new BufferedReader(fileReader);
		
			String line=null;
			
//			while(true) {
//				line = bReader.readLine();
//				if(line==null) break;
//				System.out.println(line);
//				cnt++;
//			}
			
			while((line=bReader.readLine()) != null) {
				System.out.println(line);
				set.add(line); //중복되지 않은 문자열만 들어감
				cnt++;
			}
			
		} /*catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// IOException<-FileNotFoundException
		// 자식을 위에 올려야 함.
		
		finally {
			try {
				bReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("단어 수(중복포함): " + cnt);
		System.out.println("단어 수(미포함): "+ set.size());
	}
}
