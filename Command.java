/*Joseph Fabisevich
 *Michael Knower
 */

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * A Command Line widget with a helpful "quickie" popup
 * @author Michael Knower
 */
public class Command extends Widget
{
	//the text field
	private JTextArea textbox;
	
	/**
     * Create a new Command Instance
     */
	public Command()
	{
		super("Commander", 40);
		init();
	}
	
	/**
     * Initialize the buttons and such
     */
	private void init()
	{
		textbox = new JTextArea();
		//textbox.setLineWrap(true);
		textbox.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		textbox.setBackground(Color.black);
		textbox.setForeground(Color.white);
		textbox.setSize(getWidth()-20, 40);
		textbox.setLocation(0, 0);
		resetTxt();
		textbox.setAutoscrolls(true);
		textbox.addKeyListener(new KeyAdapter()
		{public void keyPressed(KeyEvent e){if(e.getKeyCode() == KeyEvent.VK_ENTER){exec();}}});
		
		JButton exec = new JButton(new ImageIcon("exec.png"));
		exec.setSize(20, 20);
		exec.setLocation(textbox.getWidth(), textbox.getY());
		exec.setBackground(Color.black);
		exec.setForeground(Color.red);
		exec.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		exec.addMouseListener(new MouseAdapter()
		{public void mousePressed(MouseEvent e){exec();}});
		
		JButton help = new JButton(new ImageIcon("help.png"));
		help.setSize(20, 20);
		help.setLocation(textbox.getWidth(), textbox.getY()+exec.getHeight());
		help.setBackground(Color.black);
		help.setForeground(Color.red);
		help.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		help.addMouseListener(new MouseAdapter()
		{public void mousePressed(MouseEvent e){help();}});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textbox);
		scrollPane.setSize(getWidth()-20, 40);
		scrollPane.setLocation(0,0);
		
		add(scrollPane);
		add(exec);
		add(help);
		
		scrollPane.setVisible(true);
		exec.setVisible(true);
		help.setVisible(true);
	}
	
	
	/**
     * Resets the Text Field to C:\
     */
	public void resetTxt()
	{textbox.setText("C:\\");}
	
	/**
     * Execute the command
     */
	private void exec()
	{
		String cmd = textbox.getText();
		//resetTxt();
		try 
	    {Runtime.getRuntime().exec(cmd);}
	    catch (IOException ioe) {ioe.printStackTrace();}
	}
	
	/**
     * Pop up the quickie menu and send the command to the prompt box
     */
	private void help()
	{
		CommandProperty choiceArray[] = 
		{
			new CommandProperty("mspaint", "MS Paint"),
			new CommandProperty("calc", "Calculator"),
			new CommandProperty("notepad", "NotePad"),
			new CommandProperty("write", "WordPad"),
			new CommandProperty("", "-----"),
			new CommandProperty("winmine", "MineSweeper"),
			new CommandProperty("magnify", "Magnifier"),
			new CommandProperty("freecell", "FreeCell"),
			new CommandProperty("mshearts", "Hearts"),
			new CommandProperty("", "-----"),
			new CommandProperty("magnify", "Magnifier"),
			new CommandProperty("taskmgr", "Task Manager"),
			new CommandProperty("explorer", "Explorer"),
			new CommandProperty("regedit", "Registry Editor"),
			new CommandProperty("clipbrd", "Clipboard Manager"),
			new CommandProperty("charmap", "Character Mapper"),
			new CommandProperty("perfmon", "Performance Monitor")
		};

		Object choice = JOptionPane.showInputDialog(this, "Choose A Quick-Command:", "Choose", 
									JOptionPane.PLAIN_MESSAGE, new ImageIcon("choose.png"), 
									choiceArray, choiceArray[choiceArray.length-1]);
		if(choice  ==  null)
			return;
		else
		{
			textbox.setText( (  (CommandProperty)choice  ).getCommand());							
			exec();
		}
	}
	
	
	/**
     * Inner Class for the Quickie Menu itmes
     */
	private class CommandProperty
	{
		//globals
		private String cmd, label;
		
		/**
	     * Create a new Command Property
	     *@param cmd the command 
	     *@param label the label to display to end-user
	     */
		public CommandProperty(String cmd, String label)
		{
			this.cmd = cmd;
			this.label = label;
		}
		/**
	     * @return the label
	     */
		public String toString()	{return label;}
		
		/**
	     * @return the command
	     */
		public String getCommand()	{return cmd;}
	}
	
	
}