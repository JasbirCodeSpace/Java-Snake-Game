import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	private int[] snakeX = new int[750];
	private int[] snakeY = new int[750];
	private int snakeLength = 3;
	
	private int[] enemyX = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
							350, 375, 400, 425,	450, 475, 500, 525, 550, 575, 600, 625, 650,
							675, 700, 725, 750,	775, 800, 825, 850}; 
	
	private int[] enemyY = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
							350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	private Random random = new Random();
	private int posX = random.nextInt(enemyX.length);
	private int posY = random.nextInt(enemyY.length);
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon titleImage;
	private ImageIcon snakeImage;
	private ImageIcon enemyImage;
	
	private ImageIcon rightMouth;
	private ImageIcon leftMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	
	private Timer timer;
	private int delay = 100;
	private int moves = 0;

	public GamePlay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		if(moves == 0) {
			snakeX[2] = 50;
			snakeX[1] = 75;
			snakeX[0] = 100;
			
			snakeY[2] = 100;
			snakeY[1] = 100;
			snakeY[0] = 100;
		}
		
		//	draw title image border
		g.setColor(Color.WHITE);
		g.drawRect(24,10, 851, 55);
		
		// draw title image
		titleImage = new ImageIcon("assets/snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		// draw border for game play
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		// draw background for game play
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		rightMouth = new ImageIcon("assets/rightmouth.png");
		rightMouth.paintIcon(this, g, snakeX[0], snakeY[0]);
		
		for(int i=0; i<snakeLength; i++) {
			if(i==0 && left) {
				leftMouth = new ImageIcon("assets/leftmouth.png");
				leftMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
			}
			if(i==0 && right) {
				rightMouth = new ImageIcon("assets/rightmouth.png");
				rightMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
			}
			if(i==0 && up) {
				upMouth = new ImageIcon("assets/upmouth.png");
				upMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
			}
			if(i==0 && down) {
				downMouth = new ImageIcon("assets/downmouth.png");
				downMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
			}
			if(i!=0) {
				snakeImage = new ImageIcon("assets/snakeimage.png");
				snakeImage.paintIcon(this, g, snakeX[i], snakeY[i]);
			}
		}
		
		enemyImage = new ImageIcon("assets/enemy.png");
		
		if((enemyX[posX] == snakeX[0]) && (enemyY[posY] == snakeY[0])) {
			snakeLength++;
			posX = random.nextInt(enemyX.length);
			posY = random.nextInt(enemyY.length);
		}
		
		enemyImage.paintIcon(this, g, enemyX[posX], enemyY[posY]);
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		timer.start();
		
		if(right) {
			for(int r=snakeLength-1;r>=0;r--) {
				snakeY[r+1] = snakeY[r];
			}
			for(int r=snakeLength;r>=0;r--) {
				if(r==0)
					snakeX[r] = snakeX[r] + 25;
				else
					snakeX[r] = snakeX[r-1];
				
				if(snakeX[r] > 850)
					snakeX[r] = 25;
					
			}
			repaint();
		}
		
		if(left) {
			for(int r=snakeLength-1;r>=0;r--) {
				snakeY[r+1] = snakeY[r];
			}
			for(int r=snakeLength;r>=0;r--) {
				if(r==0)
					snakeX[r] = snakeX[r] - 25;
				else
					snakeX[r] = snakeX[r-1];
				
				if(snakeX[r] < 25)
					snakeX[r] = 850;
					
			}
			repaint();
		}
		
		if(down) {
			for(int r=snakeLength-1;r>=0;r--) {
				snakeX[r+1] = snakeX[r];
			}
			for(int r=snakeLength;r>=0;r--) {
				if(r==0)
					snakeY[r] = snakeY[r] + 25;
				else
					snakeY[r] = snakeY[r-1];
				
				if(snakeY[r] > 625)
					snakeY[r] = 75;
					
			}
			repaint();
		}
		
		if(up) {
			for(int r=snakeLength-1;r>=0;r--) {
				snakeX[r+1] = snakeX[r];
			}
			for(int r=snakeLength;r>=0;r--) {
				if(r==0)
					snakeY[r] = snakeY[r] - 25;
				else
					snakeY[r] = snakeY[r-1];
				
				if(snakeY[r] < 75)
					snakeY[r] = 625;
					
			}
			repaint();
		}
			
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			if(!right) {
				left = true;
			}else {
				left = false;
				right = true;
			}
			up = down = false;

		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			if(!left) {
				right = true;
			}else {
				right = false;
				left = true;
			}
			up = down = false;

		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			if(!down) {
				up = true;
			}else {
				up = false;
				down = true;
			}
			left = right = false;

		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			if(!up) {
				down = true;
			}else {
				down = false;
				up = true;
			}
			left = right = false;

		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
