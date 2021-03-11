package ch11;

import java.awt.*;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.*;

public class QuadraticFunction extends JPanel implements ActionListener {

	
	private JTextField aField, bField, cField;
	private double aCE = 1.0, bCE=-5.0, cCE=6.0;
	private Random random = new Random();
	
	
	public QuadraticFunction() {
		aField = new JTextField("1.0",10);
		bField = new JTextField("-5.0",10);
		cField = new JTextField("6.0",10);
		
		this.add(aField);
		this.add(bField);
		this.add(cField);
		
		JButton drawButton = new JButton("Draw");
		this.add(drawButton);
		drawButton.addActionListener(this);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawLine(100,200,500,200);
		g2.drawLine(300, 0, 300, 400);
		
		for(int i = -20; i<20; i++) {
			int x=i;
			int y=(int) (aCE*x*x -bCE * x + cCE);
			g2.setPaint(getColor());
			Shape s = new Ellipse2D.Float(300+x-2, 200-y+2,4,4);
			g2.fill(s);
		}

	
	}
	
	public Color getColor() {
		int[] rgb = new int[3];
		for (int i=0; i<rgb.length;i++)
			rgb[i]=random.nextInt(256); //0~255
		return new Color(rgb[0], rgb[1], rgb[2]);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		aCE = Double.parseDouble(aField.getText());
		bCE = Double.parseDouble(bField.getText());
		cCE = Double.parseDouble(cField.getText());
		
		System.out.println("!aCE:"+aCE+" bCE:"+bCE+" cCE:"+cCE);
		repaint();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new QuadraticFunction());
		frame.setTitle("QuadraticFunction");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
}
