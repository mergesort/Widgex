/*Joseph Fabisevich
 *Michael Knower
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 *	The Frame for the WidgetBar Project. This bar not an actual JFrame, but more
 *	of a manager for the actual Frame.
 *
 *@author Michael Knower
 */
public class WidgetBar
{
	
	private Widget last = null;
	public static JFrame frame;
	private JPanel pan;

	/** Create a new WidgetBar */
	public WidgetBar(String title)
	{init(title);}	

	private void init(String title)
	{
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(0,0);
		frame.setSize(Statics.widgetWidth+35,0);
		
		pan = new JPanel();
		pan.setLayout(null);
		pan.setSize(Statics.widgetWidth+20,10);
	}
	
	/** Add A Widget */
	public void add(Widget w)
	{	
		pan.setSize(Statics.widgetWidth , pan.getHeight() + w.getHeight());
		
		if(last != null)
		{	//			X:0, The Y and the Height of last + 1 (offset)
			w.setLocation(0, last.getY() + last.getHeight()+1);
			pan.add(w);
		}
		else
			pan.add(w);
		last = w;
	}
	
	/**
	 *	The complex code to set the frame visible due the the horrors of the JScrollPane
	 */
	public void show()
	{
		int ht = pan.getHeight();
		if(ht > Statics.getScreenHeight()-220)
			ht = Statics.getScreenHeight()-220;
		
		//line of death
		pan.setPreferredSize(new Dimension(Statics.widgetWidth, pan.getHeight()));
		
		JScrollPane scrollpane = new JScrollPane(pan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	frame.getContentPane().add(scrollpane, BorderLayout.CENTER);
    	
    	frame.setSize(Statics.widgetWidth+28, ht+47);//ht+3);
    	
    	frame.setLocation(Statics.getScreenWidth()-frame.getWidth(),0);
		frame.setVisible(true);
		
		try {UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");}
	    catch(Exception ex){ex.printStackTrace();}
	}
}