/*Joseph Fabisevich
 *Michael Knower
 */
 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridLayout;

/**
 *QuickCalc Widget.  A widget that enables you to get a normal 4-function 
 *calculator in the WidgetBar.
 *@author Joseph Fabisevich
 */
public class QuickCalc extends Widget
{
	private JLabel display;
	private JPanel panel;
	private double result;
	private String lastCommand;
	private boolean start;
		
	/** 
	 *Creates a new QuickCalc
	 */
	public QuickCalc()
	{
		super("QuickCalc", 200);
		
		setLayout(new BorderLayout());
		result = 0;
		lastCommand = "=";
		start = true;
		
		display = new JLabel("0");
		super.add(display,BorderLayout.NORTH);
		
		ActionListener insert = new InsertAction();
		ActionListener command = new CommandAction();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,4));
	
		addButton("7", insert,false);
		addButton("8", insert,false);
		addButton("9", insert,false);
		addButton("/", command,false);
		
		addButton("4", insert,false);
		addButton("5", insert,false);
		addButton("6", insert,false);
		addButton("x", command,true);
		
		addButton("1", insert,false);
		addButton("2", insert,false);
		addButton("3", insert,false);
		addButton("+", command,false);
		
		addButton("0", insert,false);
		addButton(".", insert,false);
		addButton("=", command,false);
		addButton("--", command,false);
		super.add(panel,BorderLayout.CENTER);
	}

	private void addButton(String label, ActionListener listener, boolean b)
	{
		JButton button = null;
		if(b)
			button = new JButton("x");
		else
			button = new JButton(label);
		button.setFont(new Font("Century Gothic",Font.BOLD,7));
		button.addActionListener(listener);
		panel.add(button);
		button.show();
	}		
	
	private class InsertAction implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String input = evt.getActionCommand();
			if(start)
			{
				display.setText("");
				start = false;
			}
			display.setText(display.getText()+input);
		}
	}
	
	private class CommandAction implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			
			if(start)
			{
				if(command.equals("--"))
				{
					display.setText("-");
					start = false;
				}
				else
					lastCommand = command;
			}
			else
			{
				calculate(Double.parseDouble(display.getText()));
				lastCommand = command;
				start = true;
			}
		}
	}
	
	public void calculate(double x)
	{
		if(lastCommand.equals("+"))
			result+=x;
		else if(lastCommand.equals("--"))
			result-=x;
		else if(lastCommand.equals("x"))
			result*=x;
		else if(lastCommand.equals("/"))
			result/=x;
		else if(lastCommand.equals("="))
			result = x;	
		display.setText(""+result);
	}
}