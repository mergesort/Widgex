/*Joseph Fabisevich
 *Michael Knower
 */

import javax.swing.JFrame;
import java.awt.Scrollbar;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.*;
import java.awt.Font;

/**
 *@author Joseph Fabisevich
 */
public class GoogleBar extends Widget 
{
	private JTextField field;
	private JButton search;
	
	/**
	 *Creates a new GoogleBar
	 */
	public GoogleBar()
	{
		super("GoogleBar", Statics.standard);
		super.setBackground(java.awt.Color.BLACK);
		field = new JTextField("Google here");
		field.setLocation(0,0);
		field.setSize(103,Statics.standard);
		field.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					google();
			}
		});

		
		search = new JButton(new ImageIcon("google.jpg"));
		search.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				google();
			}
		});
		search.setLocation(105,0);
		search.setSize(49,Statics.standard);
		
		
		

		super.add(field);
		super.add(search);
	
		try{
			Thread.sleep(1500);
		}catch(Throwable t){System.out.println("Poopy, an error.");}
		finally{field.setText("");}

		
	}
	
	private void google()
	{
		String keywordString = field.getText().replace(' ', '+');
		
		try{
			Runtime.getRuntime().exec("rundll32 SHELL32.DLL ShellExec_RunDLL http://www.google.com/search?hl=en&q=" + keywordString);}
		catch(Throwable t){t.printStackTrace();}
	}
}