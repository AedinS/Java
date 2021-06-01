package afterMid;

import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class BookListViewer extends JFrame implements ActionListener {

	
	private JTextField idField, titleField, publisherField,yearField, priceField;
	private JButton previousBtn, nextBtn, insertBtn, finishBtn;
	private Connection con;
	private ResultSet rs;
	
	public BookListViewer() throws Exception {
		/*
		 * DB에서 책 레코드들을 가져오자.
		 * 1. JDBC 드라이버 적재
		 * 2. DB 연결 
		 * 3. PreparedStatement 객체 생성 
		 * 4. SQL문 실행. 
		 * */
		// 레이아웃을 gridlayout으로 설정(0,2)
		Class.forName("org.oracle.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe");
		
		String sql = "select * from books order by book_id desc";
		con.prepareStatement(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		//select문을 실행할 때는 executeQuery() 메서드를 사용하고
		//그 외 insert, delete, update 문을 실행할 때는 
		// executeUpdate() 메서드를 사용한다.
		
		rs = pstmt.executeQuery();
		
		
		this.setLayout(new GridLayout(0,2));
		
		this.add(new JLabel("ID", JLabel.CENTER));
		idField = new JTextField(10);
		this.add(idField);
		
		this.add(new JLabel("Title", JLabel.CENTER));
		titleField = new JTextField(10);
		this.add(titleField);
		
		this.add(new JLabel("Publisher", JLabel.CENTER));
		publisherField = new JTextField(10);
		this.add(publisherField);
		
		
		this.add(new JLabel("Year", JLabel.CENTER));
		yearField = new JTextField(10);
		this.add(yearField);
		
		this.add(new JLabel("Price", JLabel.CENTER));
		priceField = new JTextField(10);
		this.add(priceField);
		
		
		
		previousBtn = new JButton("Previous");
		previousBtn.addActionListener(this);
		this.add(previousBtn);
		
		nextBtn = new JButton("Next");
		nextBtn.addActionListener(this);
		this.add(nextBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.addActionListener(this);
		this.add(insertBtn);
		
		
		finishBtn = new JButton("Finish");
		finishBtn.addActionListener(this);
		this.add(finishBtn);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(350,200);
		this.setVisible(true);
		
		// 컴포넌트들을 생성 
		// 생성된 컴포넌트들을 JFrame 객체(this)에 추가한다.
		
		// 버튼 컴포넌트들의 액션리스너를 이 객체(this)로 설정한다.
		
	}
	
	public static void main(String[] args) throws Exception {
		new BookListViewer();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if(e.getSource() == previousBtn) {
			System.out.println("previousBtn clicked");
			// 결과집합의 커서를 이전으로 이동하고
			// 그 커서가 가리키는 결과 레코드의 칼럼값을 뽑아 와서 
			// 적절한 JTextField 값으로 설정.
			rs.previous();
			setCurrentBookInfo2TextField();
			
		} else if (e.getSource() == nextBtn) {
			System.out.println("nextBtn clicked");
			rs.previous();
			setCurrentBookInfo2TextField();
			
		} else if (e.getSource() == insertBtn) {
			System.out.println("insertBtn clicked");
		} else if (e.getSource() == finishBtn) {
			System.out.println("finishBtn clicked");
			System.exit(0);
		}
		
		} catch(Exception err) {
			System.out.println(err.getMessage());
		}
	}
	
	private void setCurrentBookInfo2TextField() throws Exception {
		int bookId = rs.getInt("book_id");
		String title = rs.getString("title");
		String publisher = rs.getString("publisher");
		Date year = rs.getDate("year");
		int price = rs.getInt("price");
		
		idField.setText(String.valueOf(bookId));
		titleField.setText(title);
		publisherField.setText(publisher);
		priceField.setText(String.valueOf(price));
	}

}
