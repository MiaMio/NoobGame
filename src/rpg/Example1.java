package rpg;

import java.awt.Container;

import javax.swing.JFrame;

public class Example1 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Example1(){
		setTitle("Example1[Map]");
		MyPanel panel=new MyPanel();
		Container contentPane=getContentPane();
		contentPane.add(panel);
		pack();
	}
	public static void main(String[] args) {
		Example1 example1=new Example1();
		example1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		example1.setVisible(true);
	}
}
