package ch13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PingPong extends JPanel implements KeyListener {
	private Ball ball;
	private Racquet racquet1;
	private Racquet racquet2;
	private Score score;
	private int scoreL, scoreR;
	private static boolean start=true;
	
	public PingPong() {

		
		ball = new Ball(this, Color.white); // this - 라켓과 상호작용 하기 위해
		this.setBackground(Color.black);
		racquet1 = new Racquet(this, 10, 150, 1); // this - same
		racquet2 = new Racquet(this, 580, 150, 2);
		score = new Score();
		this.setFocusable(true);
		this.addKeyListener(this);

	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		racquet1.keyPressed(e);
		racquet2.keyPressed(e);
		
		if(start==false) {
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		racquet1.keyReleased(e);
		racquet2.keyReleased(e);
	}
	
	void move() {
		ball.move();
		racquet1.move();
		racquet2.move();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ball.draw(g2d);
		racquet1.draw(g2d);
		racquet2.draw(g2d);
		score.draw(g2d);
	}
	

	
	public static void main(String[] args) {
		JFrame frame = new JFrame("PingPong Game");
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		PingPong game = new PingPong();
		frame.add(game);
		frame.setResizable(false);
		frame.setVisible(true);

		while (start==true) {
			game.move();
			game.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

	}
	
	
	
	class Ball {
		private static final int RADIUS = 10;
		private int x = 50, y = 50, xSpeed = 2, ySpeed = 2;
		private PingPong game;
		private Color color;
		
		public Ball(PingPong game, Color color) {
			this.game = game;
			this.color = color;
		}
		
		void reborn() {
			try {
				Thread.sleep(3000);
			} catch(InterruptedException e) {
				
			}
			ball.x=300;
			ball.y=200;
			racquet1.x=10;
			racquet1.y=150;
			racquet2.x=560;
			racquet2.y=150;
		}
		
		void move() {
			
			if (x+xSpeed < 0) { //왼쪽 
				xSpeed=2;
				++scoreR;
				reborn();
			}
			

			if (x+xSpeed>game.getWidth()-2*RADIUS) { //오른쪽
				xSpeed=-2;
				++scoreL;
				reborn();
			}


			if (y+ySpeed<0) //위
				ySpeed = 2;
			
			if(y+ySpeed>game.getHeight()-2*RADIUS)
				ySpeed=-2; //아래 -1
			
			if (collision()) {
				xSpeed = -xSpeed;	
			}
			
			if (scoreL==10||scoreR==10) {
				gameOver();
			}

			x += xSpeed;
			y += ySpeed;
		}
		
		public void gameOver() {
			if(scoreL==10||scoreR==10) {
				start=false;
			}
		}
		
		private boolean collision() {
			return game.racquet1.getBounds().intersects(getBounds())
					|| game.racquet2.getBounds().intersects(getBounds());
		}
		
		
		public void draw(Graphics2D g) {
			g.setColor(color);
			g.fillOval(x, y, 2*RADIUS, 2*RADIUS);
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x,y,2*RADIUS,2*RADIUS);
		}
	
	}
	
	
	
	class Racquet {
		private static final int WIDTH = 10;
		private static final int HEIGHT = 80;
		private int x = 0, y = 0;
		private int ySpeed = 0;
		private PingPong game;
		private int id;
		
		public Racquet (PingPong game, int x, int y, int id) {
			this.game=game;
			this.x=x;
			this.y=y;
			this.id=id;
		}
		
		public void move() {
			if (y+ySpeed>0&&y+ySpeed<game.getHeight()-HEIGHT)
				y += ySpeed;
		}
		
		public void draw(Graphics2D g) {
			if(id==1) {
				g.setColor(Color.blue);
			}
			if(id==2) {
				g.setColor(Color.red);
			}
			g.fillRect(x, y, WIDTH, HEIGHT);

		}
		
		public void keyReleased(KeyEvent e) {
			ySpeed = 0;
		}
		
		public void keyPressed(KeyEvent e) {
			if(id==1) {
				if (e.getKeyCode()==KeyEvent.VK_W)
					racquet1.ySpeed=-3;
				else if (e.getKeyCode()==KeyEvent.VK_S)
					racquet1.ySpeed=3;
			}
			if(id==2) {
				if (e.getKeyCode()==KeyEvent.VK_UP)
					racquet2.ySpeed=-3;
				else if (e.getKeyCode()==KeyEvent.VK_DOWN)
					racquet2.ySpeed=3;
			}
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x,y,WIDTH,HEIGHT);
		}
		
		
	}
	
	
	
	public class Score {

		private int game_w=600;
		private int game_h=400;
		private String str="";
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		

		public void draw(Graphics g) {
			g.setColor(Color.white);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));

			g.drawLine(game_w/2, 0, game_w/2, game_h);

			g.drawString(String.valueOf(scoreL/10)+String.valueOf(scoreL%10), game_w/2-85, 50);
			g.drawString(String.valueOf(scoreR/10)+String.valueOf(scoreR%10), game_w/2+20, 50);
			
			if(start==false) {
				if(start==false) {
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, 600, 600);

					g.setColor(Color.RED);
					str=(scoreL==10) ? "Player1 wins" : "Player2 wins";
					g.drawString(str, 100, 170);
					
					g.setColor(Color.white);
					g.setFont(font);
					g.drawString("PRESS ESC TO QUIT", 200, 300);
				}
			}
		}
	}
	
	
}
