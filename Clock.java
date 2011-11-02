/*Joseph Fabisevich
 *Michael Knower
 */

import java.util.GregorianCalendar;
import java.awt.Label;
import java.awt.Color;
import java.awt.Font;

public class Clock extends Widget
{
	private Label date, time;
	
	/** Create A New Clock Widget */
	public Clock()
	{
		super("CLOCK", 40);
		init();
		runTime();
	}
	//init
	private void init()
	{
		date = new Label("Mon Jan 1");
		date.setSize(getWidth(), 20);
		date.setLocation(0, 0);
		date.setBackground(Color.black);
		date.setForeground(Color.red);
		date.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		date.setAlignment((int)Label.BOTTOM_ALIGNMENT);
		
		time = new Label("00:00:00");
		time.setSize(getWidth(), 20);
		time.setLocation(0,20);
		time.setBackground(Color.black);
		time.setForeground(Color.red);
		time.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		time.setAlignment((int)Label.BOTTOM_ALIGNMENT);
		
		add(time);
		add(date);
		date.setVisible(true);
		time.setVisible(true);
		
	}
	//run the time...updates..etc.
	private void runTime()
	{
		new Thread()
		{
			public void run()
			{
				String half = "AM";
				String a[], t[];
				while(true)
				{
					a = currTime();
					date.setText(a[0]+" "+a[1]+" "+a[2]);
					t = a[3].split(":");
					int hr = Integer.parseInt(t[0]);
					if(hr > 12)
					{
						hr = (Integer.parseInt(t[0])-12);
						half = "PM";
					}
					time.setText(hr+":"+t[1]+":"+t[2]+" "+half);
					try{Thread.sleep(1000);}
					catch(InterruptedException iE){iE.printStackTrace();}
				}
			}
		}.start();
	}
	
	
	//jihad
	private String[] currTime()			
	{return (""+new GregorianCalendar().getTime()).split(" ");}
}