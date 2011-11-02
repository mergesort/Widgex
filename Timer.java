/*Joseph Fabisevich
 *Michael Knower
 */

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Timer extends Widget
{
	private JFrame frame;
	private JTextField minute, second;
	private JLabel timer,display;
	private JButton start;
	long time = System.currentTimeMillis();
	
	/** 
	 *Creates a new Timer
	 */
	public Timer()
	{
		super("Timer",60);
		super.setBackground(java.awt.Color.BLACK);

		display = new JLabel("Timer",javax.swing.SwingConstants.CENTER);
		display.setSize(154,20);
		display.setLocation(0,0);
		display.setForeground(java.awt.Color.RED);
		display.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		
		
		minute = new JTextField();
		minute.setSize(30,20);
		minute.setLocation(0,20);


		second = new JTextField();
		second.setSize(30,20);
		second.setLocation(31,20);


		timer = new JLabel("Insert time",javax.swing.SwingConstants.CENTER);
		timer.setSize(92,20);
		timer.setLocation(62,20);
		timer.setForeground(java.awt.Color.WHITE);
		
		
		start = new JButton("Begin the countdown");
		start.setSize(154,20);
		start.setLocation(0,40);
		start.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				start.hide();
				timer();
			}
		});

	
		add(timer);
		add(minute);
		add(second);
		add(start);
		add(display);

		display.show();
		timer.show();
		minute.show();
		second.show();
		start.show();
	}
	
	/**
	 *This method takes the amount of minutes and seconds in the textfields and
	 *converts it to seconds.  It counts down in a for loopbased on that number
	 *and sleeps for a second, and after it has reached 0, it plays a noise, to 
	 *inform the person that the time has been reached.
	 */
	public void timer()
	{
		if(second.getText().equals(""))
			second.setText("0");
		if(minute.getText().equals(""))
			minute.setText("0");
			
			final int time = Integer.parseInt(second.getText()) + (Integer.parseInt(minute.getText())*60);
			
		try{
			new Thread()
			{
				public void run()
				{
					minute.setText("");
					second.setText("");
					for(int i = time; i >0; i--)
					{
						timer.setText("Timeleft: " +i);
						try{Thread.sleep(1000);}catch(Throwable t){}
					}
					start.show();
					new ez.sound.JSound("C:\\WINDOWS\\Media\\tada.wav").play();
				}
			}.start();
			}catch(Exception e){timer.setText("Invalid time");}
	}
}