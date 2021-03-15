package ch11;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Translator extends JFrame {

	private JButton converter;
	private JButton canceler;
	
	
	private JTextArea textIn;
	private JTextArea textOut; 
	
	public Translator() {
		this.setTitle("텍스트 변환");
		
		textIn = new JTextArea(10,14);
		textOut = new JTextArea(10,14);
		textIn.setLineWrap(true); // 줄바꿈
		textOut.setLineWrap(true);
		textOut.setEnabled(false); // 편집 false
		
		JPanel textAreaPanel = new JPanel(new GridLayout(1,2,20,20));
		textAreaPanel.add(textIn);
		textAreaPanel.add(textOut);
		
		converter = new JButton("반환");
		canceler = new JButton("취소");
		converter.addActionListener(new ButtonActionListener());
		canceler.addActionListener(new ButtonActionListener());
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(converter);
		buttonPanel.add(canceler);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10,10));
		mainPanel.add(BorderLayout.CENTER, textAreaPanel);
		mainPanel.add(BorderLayout.SOUTH, buttonPanel);
		
		setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		add(mainPanel);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private class ButtonActionListener implements  ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == converter) {
				textOut.setText(""); // 결과창 초기화
				String result = toEnglish(textIn.getText()); // input 창의 텍스트들을 구해 toEnglish()에 넣은 후 result에 삽
				textOut.append(result); // 결과창에 result를 append
			}
			if (e.getSource() == canceler) {
				textOut.setText("");
			}
		}
		
		private String toEnglish(String korean) {
			String result = korean;
			result = result.replace("텍스트", "Text"); // 텍스트 -> Text
			result = result.replace("영어", "English");
			return result;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Translator t = new Translator();
	}

}
