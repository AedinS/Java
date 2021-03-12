package ch11;

import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;

public class SliderDemo extends JFrame implements ChangeListener {

	static final int INIT_VALUE=30;
	private JSlider slider;
	private JButton btn;
	
	public SliderDemo() {
		JPanel panel;
		
		this.setTitle("슬라이더 테스트");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		JLabel label = new JLabel("슬라이더를 움직여보세요.", SwingConstants.CENTER);
		
		panel.add(label);
		
		slider = new JSlider(0,100,INIT_VALUE);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		panel.add(slider);
		
		btn = new JButton(" ");
		ImageIcon icon = new ImageIcon("bee.png");
		btn.setIcon(icon);
		btn.setSize(INIT_VALUE * 10, INIT_VALUE*10);
		panel.add(btn);
		
		this.add(panel);
		this.setSize(500,500);
		this.setVisible(true);
	}
	
	@Override
	public void stateChanged (ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if(!source.getValueIsAdjusting()) {
			int value = source.getValue();
			btn.setSize(value*10,value*10);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SliderDemo();
	}

}
