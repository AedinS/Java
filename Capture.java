package ch11;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Capture {
	
	
	public static void main(String[] args) {
		JFrame capture = new JFrame();
		capture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension d;
		Rectangle rect = new Rectangle(500, 500);
		capture.setSize(d=new Dimension(500,500));
		
		
		try {
			// Robot 클래스- 테스트 목적으로 시스템의 입력 이벤트를 생성하는데 사용됨(주로 self-running demo)
			Robot robot = new Robot();
			// BufferedImage subclass- 접근 가능한 이미지 데이터 버퍼가 있는 이미지 묘
			final BufferedImage img = robot.createScreenCapture(rect);
			img.flush(); // 캡쳐
			
			JPanel panel = new JPanel() {
				public void paintComponent(Graphics g) {
					g.drawImage(img, 0, 0, d.width, d.height, this);
				}
			};
			
			panel.setOpaque(false); // true -> 범위 내 모든 픽셀을 그림
			panel.prepareImage(img, panel);
			panel.repaint();
			capture.getContentPane().add(panel);
		} catch (Exception e) {
			e.printStackTrace(); // Prints this throwable and its backtrace to the standard error stream
		}
	}
	
}

