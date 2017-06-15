package rpg;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;

public class MyPanel extends JPanel implements KeyListener{
	private static final int WIDTH=480;
	private static final int HEIGHT=480;
	private static final int ROW=15;
	private static final int COL=15;
	private static final int CS=32;
//	private static final int RATE=10; //this can control the character's speed
	private static final int LEFT=0;
	private static final int RIGHT=1;
	private static final int UP=2;
	private static final int DOWN=3;
	private int direction;
	private Thread animationThread;
	private int count; //count character's step number
	private int[][] map={
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
	private Image floorImage;
	private Image wallImage;
	private Image roleImage;
	private int x, y; //the coordinates of the character
	public MyPanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		loadImage();
		x=8;
		y=8;
		direction=DOWN;
		count=0;
		setFocusable(true);
		addKeyListener(this);
		animationThread=new AnimationThread();
		animationThread.start();
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawMap(g);
		drawRole(g);
	}
	private void loadImage(){
		ImageIcon icon=new ImageIcon("D:\\javatest\\rpg\\floorImage.jpg");
		floorImage=icon.getImage();
		icon=new ImageIcon("D:\\javatest\\rpg\\wallImage.jpg");
		wallImage=icon.getImage();
		icon=new ImageIcon("D:\\javatest\\rpg\\allcharacter.png");
		roleImage=icon.getImage();
	}
	
	private void drawMap(Graphics g){
		for(int x=0; x<ROW; x++){
			for(int j=0; j<COL; j++){
				switch(map[x][j]){
					case 0:
						g.drawImage(floorImage, j*CS, x*CS, this);
						break;
					case 1:
						g.drawImage(wallImage, j*CS, x*CS, this);
						break;
					default:
						break;
				}
			}
		}
		
	}
	private void drawRole(Graphics g){
		g.drawImage(roleImage, x*CS, y*CS, x*CS+CS, y*CS+CS, count*CS, direction*CS, count*CS+CS, direction*CS+CS, this);
	}
	public void keyPressed(KeyEvent e){
		int keyCode=e.getKeyCode();
		switch(keyCode){
			case KeyEvent.VK_LEFT:
				move(LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				move(RIGHT);
				break;
			case KeyEvent.VK_UP:
				move(UP);
				break;
			case KeyEvent.VK_DOWN:
				move(DOWN);
				break;
			
		}
		repaint();
	}
	private boolean isAllow(int x, int y){
		if(map[x][y]==1)
			return false;
		return true;
	}
	private void move(int event){
		switch(event){
			case LEFT:
				if(isAllow(x-1, y))	x--;
				direction=LEFT;
				break;
			case RIGHT:
				if(isAllow(x+1, y)) x++;
				direction=RIGHT;
				break;
			case UP:
				if(isAllow(x, y-1)) y--;
				direction=UP;
				break;
			case DOWN:
				if(isAllow(x, y+1)) y++;
				direction=DOWN;
				break;
			default:
				break;
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	private class AnimationThread extends Thread{
		public void run(){
			while(true){
				if(count==0)
					count=1;
				else count=0;
				repaint();
				try{
					Thread.sleep(300);
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
}
