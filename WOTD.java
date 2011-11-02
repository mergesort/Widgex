/*Joseph Fabisevich
 *Michael Knower
 */

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Label;
import java.awt.Color;
import java.awt.Font;

import ez.util.Utility;

/**
 *Dictionary.com Word Of The Day Widget
 *
 *@author Michael Knower
 */
public class WOTD extends Widget
{
	private boolean JIHAD;
	private String word = "", definition = "";
	private JTextArea DEF;
	
	/** instantiate a new WIDGET */
	public WOTD()
	{
		super("WOTD", 70);
		getFeed();
		init();
	}
	
	
	/** Retrieve the Relavent feed info */
	private void getFeed()
	{
		String x[] = {};
		try 
		{
			x = Utility.getHtml("http://dictionary.reference.com/wordoftheday/wotd.rss");
			
			String def = "";
			
			for (int i = 0; i<x.length; i++)
			{
				if(x[i].startsWith("<item>"))
				{
					for (int j = i; j<x.length; j++)
					{
						if(x[j].startsWith("<description>"))
						{
							def = x[j].substring("<description>".length(), (x[j].length()-1)-13);
							break;
						}
					}
					JIHAD = true;
					break;
				}
			}	
			
			String WandD[] = def.split(":");
			if(WandD.length == 2)
			{
				word = WandD[0];
				definition = WandD[1];
			}
			else if(WandD.length > 0)
			{
				word = WandD[0];
				for(int i = 1; i< WandD.length; i++)
				   	definition+=WandD[i];
			}
		}
	    catch(Exception ex)
	    {
	    	word = "ERROR";
	    	definition = "CONNECTION REFUSED OR \nFAILED";
	    	JIHAD = false;
	    	return;

	    }
		 
	}
	
	/** init*/
	private void init()
	{
		Label title = new Label("Word of the Day");
		title.setSize(getWidth()-20, 20);
		title.setLocation(0,0);
		title.setForeground(Color.red);
		title.setBackground(Color.black);
		title.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		
		JButton refresh = new JButton(new ImageIcon("refresh.png"));
		refresh.setSize(20,20);
		refresh.setLocation(title.getX()+title.getWidth(), title.getY());
		refresh.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{refresh();}
		});
		
		DEF = new JTextArea(word+":\n"+definition);
		DEF.setSize(getWidth(), 50);
		DEF.setLineWrap(true);
		DEF.setAutoscrolls(true);
		DEF.setLocation(0,title.getHeight());
		DEF.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		DEF.setBackground(Color.LIGHT_GRAY);
		DEF.setEditable(false);
		
		super.add(title);
		super.add(refresh);
		super.add(DEF);
		
		title.show();
		refresh.show();
		DEF.show();
	}
	
	/** refresh */
	private void refresh()
	{
		if(JIHAD)
		{
			getFeed();
			DEF.setText(word+":\n"+definition);
		}
	}
	
}