package ch12;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

public class Capture {
	JButton btn;
	JFrame capture;
	
	public static void main(String[] args) {
		Capture c = new Capture();
	}

	Capture() {
		long systemTime = System.currentTimeMillis();

		
		JFrame capture = new JFrame();
		capture.setBounds(100, 100, 380, 300);
		capture.setLocation(100,200);;
		capture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn = new JButton("capture");
		ActionListener listener = new Click();
		btn.addActionListener(listener);
		
		JLabel quote = new JLabel("To know your Enemy, you must become your Enemy");
		quote.setHorizontalAlignment(JLabel.CENTER);
		quote.setForeground(Color.RED);
		
		// 출력 형태
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		// format에 맞게 출력하기 위한 문자열 변환
		String dTime = formatter.format(systemTime);
		JLabel time = new JLabel(dTime);
		time.setHorizontalAlignment(JLabel.CENTER);
		
		capture.add(btn, BorderLayout.SOUTH);
		capture.add(time, BorderLayout.NORTH);
		capture.add(quote, BorderLayout.CENTER);
		capture.setVisible(true);
	}
		
}

class Click implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		JFrame capture = new JFrame();	
		Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		capture.setSize(d);

		try {
			// Robot 클래스- 테스트 목적으로 시스템의 입력 이벤트를 생성하는데 사용됨(주로 self-running demo)
			Robot robot = new Robot();
			// BufferedImage subclass- 접근 가능한 이미지 데이터 버퍼가 있는 이미지 묘
			final BufferedImage img = robot.createScreenCapture(rect);
			JPanel panel = new JPanel() {
				public void paintComponent(Graphics g) {
					g.drawImage(img, 0, 0, d.width, d.height, this);
				}
			};
			
			panel.setOpaque(false); // true -> 범위 내 모든 픽셀을 그림
			panel.prepareImage(img, panel);
			panel.repaint();
			capture.getContentPane().add(panel);
			img.flush(); // 캡쳐
				
			capture.setVisible(true);

		} catch (Exception ex) {
			ex.printStackTrace(); // Prints this throwable and its backtrace to the standard error stream
		}
	}
}
