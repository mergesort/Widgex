/*Joseph Fabisevich
 *Michael Knower
 */

import java.io.IOException;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.util.GregorianCalendar;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JProgressBar;
import com.vladium.utils.SystemInformation;

/**
 *Utility Widget. Has a clock and shutdown/standby buttons
 */
public class Utility extends Widget
{
	private Runtime runtime =  Runtime.getRuntime();
	private JProgressBar memBar, cpuBar;
	private JLabel date, time, tooltip;
	
	/**
     * Create a new Utility Widget
     */
	public Utility()
	{
		super("utility", 71);
		init();
		runThread();
	}
	
	/**
     * Initializer
     */
	private void init()
	{
		super.setBackground(Color.black);
		
		JLabel sep = new JLabel();
		sep.setSize(getWidth(), 1);
		sep.setLocation(0, 53);
		sep.setBackground(Color.DARK_GRAY);
		
		tooltip = new JLabel("");
		tooltip.setSize(getWidth(), 17);
		tooltip.setLocation(0, 54);
		tooltip.setBackground(Color.black);
		tooltip.setForeground(Color.red);
		
		
		
		JButton standby = new JButton(new ImageIcon("standby.png"));
		standby.setSize(33, 33);
		standby.setLocation(getWidth()-standby.getWidth(),20);
		standby.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + new File("standby.exe").getAbsolutePath());}
			public void mouseEntered(MouseEvent e)
			{
				tooltip.setText("Stand By");
				tooltip.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			public void mouseExited(MouseEvent e)
			{tooltip.setText("");}
		});
		
		JButton logoff = new JButton(new ImageIcon("logoff.png"));
		logoff.setSize(33, 33);
		logoff.setLocation(0, 20);
		logoff.addMouseListener(new MouseAdapter()
		{
			/**
		     * CHANGE ME!!!! FIX ME!!!
		     *	shutdown -l
		     */
			public void mousePressed(MouseEvent e)
			{exec("shutdown -l");}
			public void mouseEntered(MouseEvent e)
			{
				tooltip.setText("Log Off");
				tooltip.setHorizontalAlignment(SwingConstants.LEFT);
			}
			public void mouseExited(MouseEvent e)
			{tooltip.setText("");}
		});
		
		date = new JLabel("Mon Jan 1", SwingConstants.CENTER);
		date.setSize(getWidth()-66, 20);
		date.setLocation(standby.getWidth(), 20);
		date.setBackground(Color.black);
		date.setForeground(Color.red);
		date.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		
		time = new JLabel("00:00:00", SwingConstants.CENTER);
		time.setSize(getWidth()-66, 13);
		time.setLocation(standby.getWidth(),40);
		time.setBackground(Color.black);
		time.setForeground(Color.red);
		time.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		
		memBar = new JProgressBar(0, (int)runtime.totalMemory());
		memBar.setSize(getWidth(), 20);
		memBar.setLocation(0,0);
        memBar.setValue(0);
        memBar.setStringPainted(true);
        
        
		
		add(tooltip);
		add(standby);
		add(logoff);
		add(time);
		add(date);
		add(sep);
		add(memBar);
		
		
        memBar.setVisible(true);
		tooltip.setVisible(true);
		standby.setVisible(true);
		logoff.setVisible(true);
		date.setVisible(true);
		time.setVisible(true);
		sep.setVisible(true);
	}
	
	/**
	 *Execute the thread for the Utility Widget. 
	 */
	private void runThread()
	{
		//thread this 
		new Thread()
		{
			public void run()
			{
				String half = "AM";
				String a[], t[];
				while(true)
				{
					//run  Garbage Collector to help with memory usage
					System.gc();
					
					//mem monitor
					memBar.setValue( (int)(runtime.totalMemory() - runtime.freeMemory()) );  
        			memBar.setString("Mem Usage: "+Math.round((memBar.getPercentComplete()*100))+"%"); 
        			
        			//utils
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
	
	/** execute a command */
	private void exec(String fileName)
	{
		try 
	    {Runtime.getRuntime().exec(fileName);}
	    catch (IOException ioe) {ioe.printStackTrace();}
	}
	/**
     * Current Time returned as such:
     * 0:DAY
     * 1:MONTH
     * 2:DAY
     * 3:TIME - hr:min:sec
     */
	private String[] currTime()			
	{return (""+new GregorianCalendar().getTime()).split(" ");}
}