
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Sketch
{
	JFrame frame;
	public Sketch()
	{
		frame = new JFrame();
		frame.dispose();
		frame.setTitle("Kybo Etch-A-Sketch");
		frame.setSize(300,300);
		frame.setLocation(Statics.getScreenWidth()-frame.getWidth()-180,0);
		frame.getContentPane().add(new SketchPanel());
		frame.show();
	}

/**
 *Sketch Widget.  A widget that brings you the joy of using that old etch-a-sketch,
 *but now modernized to be used on your computer.
 *@author Joseph Fabisevich
 */
class SketchWidget extends Widget
{
	private JLabel display;
	private JButton refresh;
	
	/** 
	 *Creates a new SketchWidget
	 */
	public SketchWidget()
	{
		super("Sketch",20);
		super.setBackground(java.awt.Color.BLACK);
		display = new JLabel("Kybo Etch-A-Sketch",javax.swing.SwingConstants.CENTER);
		display.setSize(134,20);
		display.setLocation(0,0);
		display.setForeground(java.awt.Color.RED);
		display.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));

		refresh = new JButton(new ImageIcon("refresh.png"));
		refresh.setSize(20,20);
		refresh.setLocation(134,0);
		refresh.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{refresh();}
		});
		

		super.add(display);
		super.add(refresh);

		display.show();
		refresh.show();		
	}
	
	/**
	 *Calls a new sketch so that way when the mouse is pressed on the refresh
	 *button, it will call the sketch.
	 */
	public void refresh()
	{
		new Sketch();
	}
}

class SketchPanel extends JPanel
{
	private Point2D last;
	private ArrayList lines;
	
	private static final int SMALL_INCREMENT = 1;
	private static final int LARGE_INCREMENT = 5;
	
	public SketchPanel()
	{
		last = new Point2D.Double(150,150);
		lines = new ArrayList();
		KeyHandler listener = new KeyHandler();
		addKeyListener(listener);
		setFocusable(true);
	}
	
	/**
	 *This method adds a new line segment to the sketch
	 *@param dx The movement in the x direction
	 *@param dy The movement in the y direction
	 */
	public void add(int dx, int dy)
	{
		Point2D end = new Point2D.Double(last.getX() + dx, last.getY() + dy);
		Line2D line = new Line2D.Double(last, end);
		lines.add(line);
		repaint();
		last = end;
	}
	
	/**
	 *Calls for the Graphics2D objet to draw the lines as they are being taken
	 *from the ArrayList
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		for(int i = 0; i < lines.size(); i++)
			g2.draw((Line2D)lines.get(i));
	}
	
	private class KeyHandler implements KeyListener
	{
		/**
		 *Makes the keyPressed method, if the up, down, left, or right is pressed
		 *it moves the linesegment accordingly
		 */
		public void keyPressed(KeyEvent e)
		{
			int d;
			if(e.isShiftDown())
				d = LARGE_INCREMENT;
			else
				d = SMALL_INCREMENT;
				
			if(e.getKeyCode() == KeyEvent.VK_LEFT) add(-d,0);
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) add(d,0);
			else if(e.getKeyCode() == KeyEvent.VK_UP) add(0,-d);
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) add(0,d);	
		}
		
		/**
		 *Makes the keyTyped method, if the a,d,w, or s is pressed it moves the 
		 *linesegment accordingly
		 */
		public void keyTyped(KeyEvent e)
		{
			int d;
			char keyChar = e.getKeyChar();
			if(Character.isUpperCase(e.getKeyChar()))
			{
				d = LARGE_INCREMENT;
				keyChar = Character.toLowerCase(e.getKeyChar());
			}	
			else
				d = SMALL_INCREMENT;
				
			if(keyChar == 'a') add(-d,0);
			else if(keyChar == 'd') add(d,0);
			else if(keyChar == 'w') add(0,-d);
			else if(keyChar == 's') add(0,d);		
		}
		
		public void keyReleased(KeyEvent e){} 
	}
}
}