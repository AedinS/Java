package ch11;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComboBox extends JFrame implements ActionListener {
	private JLabel label;
	private JComboBox characterList;
	
	public ComboBox() {
		this.setTitle("ComboBox");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		String[] characters = {"bee", "rabbit", "Lovely Boy"}; // 배열 생성
		characterList = new JComboBox(characters); // JComboBox에 배열 삽입
		characterList.setSelectedIndex(0); // 해당 인덱스에 해당하는 item을 고름 -> bee
		characterList.addActionListener(this);
		
		label = new JLabel(); 
		label.setHorizontalAlignment(SwingConstants.CENTER); // JLabel을 중앙에 정렬(가로)
		changePicture(characters[characterList.getSelectedIndex()]); // 사진 변경
		
		this.add(characterList,BorderLayout.NORTH); // 콤보박스 북쪽
		this.add(label,BorderLayout.CENTER); // 사진 센터
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = (String) characterList.getSelectedItem(); // 현재 선택된 item 리턴 -> 배멸에서 선택 -> 문자열로 변환
		changePicture(name);
	}
	
	protected void changePicture(String name) {
		ImageIcon icon = new ImageIcon("./src/ch11/" + name + ".png"); // 사진 탐색
		label.setIcon(icon);
		
		label.setText(null);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ComboBox();
	}

}
