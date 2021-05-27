package ch15;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dictionary extends JPanel implements ActionListener {

   // 단어 입력 받을 수 있는 JTextField
   // 검색 버튼, 추가 버튼
   // 단어장 구현을 위한 자료 구조로 Map 객체 생성
   
   private JTextField inputField = new JTextField(30);
   private JButton searchBtn = new JButton("검색");
   private JButton addBtn = new JButton("추가");
   
   private static final String DIC_FILE_NAME= " dict.props";
   private static final String DB_URL="jdbc:oracle:thin:@localhost:1521:xe"; 
   private static final String DB_USER="system";
   private static final String DB_PASSWORD="oracle";
   
   // Map객체를 단어장 구현으로 사용
   // key는 한글단어, value는 대응되는 영어 단어를 저장한다
   private Map<String, String> words = new HashMap<>();

   public Dictionary() {
      // Panel의 기본 레이아웃은 FlowLayout
      this.add(inputField);
      this.add(searchBtn);
      this.add(addBtn);
      
      // 각 버튼에 글릭 이벤트가 발생 했을때 처리할 리스너를 지정
      searchBtn.addActionListener(this);
      addBtn.addActionListener(this);
      
      this.setPreferredSize(new Dimension(600, 50));
      
      
      // 파일에 key=value 형태로 저장된 엔트리들을 읽어서, words를 구성하기
      // DB에서 레코드를 읽고, 그 레코드들을 이용해서 dict 맵을 구성하자.
      buildDictionaryFromDB();
      
      
      
      // buildDictionaryFromFile();
   }
   
   private void buildDictionaryFromDB() {
	   // 1. DB server에 연결한다.
	   //	a. JDBC 드라이버를 메모리에 로딩(적재)
	   //	b. DriverManager (java.sql 패키지에 정의된 클래스)
	   //	Connection con = DriverManager.getConnection();
	   //		메서드 호출에 연결을 establish
	   //		이때 연결 정보를 getConnection() 메서드에 전달해야 함.
	   //		연결 정보: DB server의 URL
	   //		=> (ip주소, port 번호)
	   //		db 사용자의 아이디와 암호
	   
	   /*
	    * 2. Connection 객체를 통해 SQL문 실행을 서버에 요청하고 그 결과를 받아 처리한다.
	    * 첫째는 con.createStatement() 메소드 호출을 통해서 반환되는 Statement 객체를 이용하는 방법 (정적 SQL문)
	    * 두번째는 con.prepareStatement() 메서드 호출을 통해서 반환되는 PreparedStatement 객체를 이용하는 방법 (동적 SQL문)
	    * 
	    * 	정적 SQL문: 프로그래밍 시점에 실행할 SQL문 결정되고 고정된 경우.
	    * 		SELECT * FROM dict;
	    * 
	    * 	동적 SQL문: 프로그래밍 시점에 실행할 SQL문 결정되지 않고 변경되는 SQL문
	    * 		SELECT * FROM dict WHERE han = ?;
	    * 
	    * 	이 예시에서는 PreparedStatement 객체를 이용한다. -> 서버가 실행할 준비가 됨.
	    * 		String sql = "select * from dict";
	    * 													con.prepareStatement(sql);
	    * 		PreparedStatement pstnt = con.prepareStatement(sql);
	    * 
	    * 
	    * 		// 실행 준비가 된 PreparedStatement		실행시키는 방법은 크게 2가지
	    * 
	    * 		첫 번째: 실행할 SQL문이 insert, delete, 또는 Update문인 경우
	    * 			pstnt.executeUpdate();
	    * insert into ...    delete from dict...    update set eng = ... from ...
	    * 
	    * 
	    * 		두 번째: 실행할 SQL문이 select인 경우
	    * 			pstnt.executeQuery();
	    * 
	    * 
	    * 	what -> 선언적 언어 (sql문)
	    * 	how -> 절차적 언어
	    * 
	    * 	DB server 	1. 문법 검사		2. 정당성 검사		3. execution plan 	 4. execute
	    * 
	    * 
	    * 
	    * 3. DB Server와의 연결을 해제(close)한다.
	    * 	con.close();
	    * */
	   
	   //드라이브를 메모리에 적재 
	   // 드라이버 클래스 이름은 DBMS마다 다름.
	   
	    try {
			Class.forName(DIC_FILE_NAME);
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
			return;
		}
	    
	   try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

	    
	    // DB서버에 연결 
	    
	    
	   String sql = "select * from dict";
	   PreparedStatement pstnt = con.prepareStatement(sql);
	   ResultSet rs = pstnt.executeQuery();
	   while(rs.next()) {
		   //1, 2, 3.. record 호출 
		   // 현재 포인터가 가리키는 컬럼 값을 빼오면 됨.
		   // 각 칼럼의 타입에 따라, 호출할 메서드가 달라짐.
		   String key = rs.getString(1);
		   // or rs.getString("han");
		   String value = rs.getString(2);
		   
		   words.put(key, value);
		   words.put(value, key);
		   
		   // 파일에도 key=value 의 쌍으로 기록해놓자 >ㅁ<
		   // DB에 <key, value)의 쌍으로 하나의 레코드로 저장하다.
		   
		   addToDB(key, value);
		   JOptionPane.showMessageDialog(this, "["+value+"]", value, JOptionPane.INFORMATION_MESSAGE);
	   }
	    
	   } catch(Exception e) {
		   System.out.println(e.getMessage());
	   }
//	   finally {
//		   try {con.close();} catch(Exception ignore) {}
//	   } 
   }
   
   private void addToDB(String key, String value) {
//	   1. 드라이버 클래스는 딱 한 번만 메모리에 적재하면 ㅗ딤.근데 이미 객체가 생성될 때 생성자에서 적재되었음.여기서는 적재할필요가 없다.
//	   2. 데이터베이스에 연결
//	   3. sql문 실행
//	   4. 데이터베이스 연결 해제
	   
	   // 연결 요청!
	   try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
		   String sql = "insert into dict values(?,?)";
		   PreparedStatement pstmt = con.prepareStatement(sql);
		   
		   // ? 자리에 값을 채운 후에, 서버에게 실행 준비된 SQL문을 실행하라고 요청해야 한다.
		   pstmt.setString(1, key);
		   // 첫번째 물음표 자리에는 key값으로 세팅해/1
		   pstmt.setString(2, value);
		   
		   pstmt.executeUpdate(); // 실행 요청
		   
	   } catch(Exception e) {
		   System.out.println(e.getMessage());
		   e.printStackTrace();
	   }
   }
   
   
   private void buildDictionaryFromFile() {
      // Properties는
      // key, value의 타입이 각각 String, String으로 고정된 일종의 맵이다. 
      Properties props = new Properties();
//      props.put("사과", "apple");
      
      
      // 파일에서 읽어서 props 객체의 <key, value>쌍을 구성할 수 있다.
      // 파일을 읽으려면 FileReader를 생성해준다
      
//      FileReader fReader = new FileReader(DIC_FILE_NAME);
//      props.load(fReader);
      
      try(FileReader fReader = new FileReader(DIC_FILE_NAME)) {
         props.load(fReader);
      } catch(Exception e) {
         System.out.println(e.getMessage());
      }
      
      Set<Object> set = props.keySet();
      for(Object obj : set) {
         words.put((String)obj, (String)props.get(obj));
      }
   }
   
   

   @Override
   public void actionPerformed(ActionEvent e) {
      String key = inputField.getText();
      if(key.trim().length() == 0) return;
      
      if(e.getSource() == searchBtn) {
         // 입력된 단어를 추출하여
         // 그 단어를 key 값으로 가지는 대응된느 맵 엔트리가 있는지 검사 words.get(단어);
         // 그 단어에 대응되는 값이 있으면 JOptionPane.showMessageDialog() 메서드를 호출해서 대응되는 값을 보여준다
         // 값이 없으면 (null) JOptionPane.showMessageDialog() 메서드를 호출해서
         // 단어를 찾을 수 없습니다 라고 출력해준다
         // inputField를 빈 문자열로 설정한다
         
         System.out.println("[" + key + "]");
         String value = words.get(key);
         
         if(value != null) {
            // 대응되는 단어가 있는 경우
            JOptionPane.showMessageDialog(this, value, key, JOptionPane.INFORMATION_MESSAGE);
         } else {
            // 대응되는 단어가 없는 경우
            JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다.", key, JOptionPane.ERROR_MESSAGE);
         }
      } else if(e.getSource() == addBtn) {
         // 입력된 단어를 추출
         // JOptionPane.showInputDialog() 메서드를 호출해서 추가할 영어 단어를 입력 받는다
         // words.put(입력 필드에 입력된 단어, inputDialog에 입력된 단어)
         
         String value = JOptionPane.showInputDialog(this, key, "에 대응 되는 영어 단어를 입력하세요.");
         
         if(value.trim().length() == 0) return;
         words.put(key, value);
         words.put(value, key);
         // DB에 <키, 밸류>의 쌍을 하나의 레코드로 저장하자.
        // addWordToFile(key, value);
         addToDB(key, value);
         JOptionPane.showMessageDialog(this, "[" + value + "]" + "영어 단어가 추가되었습니다.", key, JOptionPane.INFORMATION_MESSAGE);
      }
      
     //  inputField.setText("");
      
   }
   
//   private void addToDB(String key, String value) {
//	   /*
//	    * 1. DB 연결
//	    * 	(1) JDBC 드라이버 메모리 적재(한 번만 하면 되니까, 여기서는 안해도 되겠다!)
//	    * 	(2) DriverManager.getConnection(url, id, pw) 호출해서 Connection 객체를 생성하고
//	    * 2. SQL문 실행
//	    * 	(1) Connection 객체에서 실행할 SQL문을 실행준비 요청하고 con.prepareStatement(sql);
//	    * 	(2) PreparedStatement 객체에게 서버에게 실행요청 보내라.
//	    * 		preparedStatement.executeUpdate(); < 실행할 sql문의 insert, delete, 또는 update 일 때
//	    * 		preparedStatement.executeQuery(); < 실행할 SQL문의 select문일 때
//	    * 3. DB 연결 객체
//	    * */
//	   
//	   // DB 서버에 연결
//	   try(Connection con =
//			   DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
//	   {
//		   // ?는 place holder 하고, 실행준비 시킨 후에,
//		   // 실행 직전에 저 ? 자리에 값을 설정하고 실행 요청을 보낸다.
//		   String sql = "insert into dict values(?,?)";
//		  //  String sql = "insert into dict values('장미', 'rose')";
//		   PreparedStatement pstmt = con.prepareStatement(sql); // SQL문을 DB 서버로 보내서 실행준비 시켜라.
//		   pstmt.setString(1, key);
//		   pstmt.setString(2,  value);
//		   
//		   // 실행할 SQL문이 insert, delete, 또는 update 문일 때, executeUpdate 메서드 호출
//		   // executeUpdate 메서드는 실행 후, 정수 값을 반환한다.
//		   // 그 정수값은 insert, delete, update문의 수행으로 변경된 레코드의 수,
//		   pstmt.executeUpdate();
//	   }
//
//   }
   
   private void addWordToFile(String key, String value) {
      try(FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true)) {
         // FileWriter의 write는 계속 덮어쓰므로 마지막에 추가된 것만 파일에 남는다.
         fWriter.write(key + "=" + value + "\n");
      } catch(Exception e) {
         System.out.println(e.getMessage());
      }
   }
   
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.add(new Dictionary());
      
      frame.setTitle("한영사전");
      frame.setResizable(false);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
   }
   
}