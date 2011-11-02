///*Joseph Fabisevich
// *Michael Knower
// */
// 
//package widget;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.Arrays;
//import ez.util.Utility;
//
///**
// *Organizer Widget.  A widget that gives you a phonebook to store phone numbers
// *in the WidgetBar.  Simplified version of my level 8, so it would be WidgetBar
// *accessible
// *@author Joseph Fabisevich
// */
//public class Organizer extends Widget
//{
//
//	private PersonArray parray;
//	private FileWorker file;
//	private TextArea field;
//	private JButton add, sort;
//	private JLabel display, fir, las, pho;
//	private JTextField first, last, phone;
//
//	/**
//	 *Simple constructor with no parameters, which calls init which is used to 
//	 *initialize attributes in the program.
//	 */
//	public Organizer()
//	{
//		super("Organizer",350);
//		super.setBackground(java.awt.Color.BLACK);
//		init();
//	}
//
//	/**
//	 *Init method used to initialize everything related to the progam.
//	 */
//	public void init()
//	{
//		file = new FileWorker("Organizer");
//
//		display = new JLabel("Phonebook",javax.swing.SwingConstants.CENTER);
//		display.setSize(154,20);
//		display.setLocation(0,0);
//		display.setBackground(java.awt.Color.BLACK);
//		display.setForeground(java.awt.Color.RED);
//		display.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
//
//		fir = new JLabel("Enter a First Name:");
//		fir.setSize(154, 20);
//		fir.setLocation(0,20);
//		fir.setForeground(java.awt.Color.WHITE);
//
//		first = new JTextField();
//		first.setSize(154, 20);
//		first.setLocation(0,45);
//
//		las = new JLabel("Enter a Last Name:");
//		las.setSize(154, 20);
//		las.setLocation(0,70);
//		las.setForeground(java.awt.Color.WHITE);
//
//		last = new JTextField();
//		last.setSize(154, 20);
//		last.setLocation(0, 95);
//
//		pho = new JLabel("Enter a phone number:");
//		pho.setSize(154, 20);
//		pho.setLocation(0,120);
//		pho.setForeground(java.awt.Color.WHITE);
//
//		phone = new JTextField();
//		phone.setSize(154, 20);
//		phone.setLocation(0,145);
//
//		add = new JButton("Add");
//		add.setSize(154, 20);
//		add.setLocation(0,170);
//		add.addMouseListener(new MouseAdapter()
//		{
//			public void mousePressed(MouseEvent e)
//			{add();}
//		});
//
//		if(file.openForReading());
//		field = new TextArea(convertToString(file.readLines()), 5, 40, TextArea.SCROLLBARS_BOTH);
//		field.setEditable(false);
//		field.setSize(154,155);
//		field.setLocation(0,195);
//
//		super.add(display);
//		super.add(field);
//		super.add(add);
//		super.add(first);
//		super.add(last);
//		super.add(phone);
//		super.add(fir);
//		super.add(las);
//		super.add(pho);
//		
//		display.show();
//		field.show();
//		add.show();
//		first.show();
//		last.show();
//		phone.show();
//	}
//
//	/**
//	 *A method that converts a String [] into a String
//	 *by adding a line break.
//	 *@param array String [] to convert
//	 */
//	public String convertToString(String[] array)
//	{
//		String str = "";
//
//		for(int i = 0; i < array.length; i++)
//	      	str += array[i] + '\n';
//
//	  	return str;
//	}
//
//	/**
//	*This method adds the file to the name.  It checks to see that the names are 
//	*letters, phone numbers are not, and that phone numbers are of a certain length.
//	*If the user input complies with all these limits, then the file is written to,
//	*with all the data.
//	*
//	*Side note: A new WidgetStart() call must be made, due to ammend file in FileWorker.
//	*Does not ammend instantaneously.  File would not be read from new data, thanks to that.
//	*/
//	public void add()
//	{
//		if(!(StringHelper.hasAllLetters(first.getText()) && StringHelper.hasAllLetters(last.getText())))
//			JOptionPane.showMessageDialog(null,"First and Last Names must contain only letters.","Change Fields",JOptionPane.INFORMATION_MESSAGE);
//		if(StringHelper.hasAllLetters(phone.getText()))
//				JOptionPane.showMessageDialog(null,"Phone Numbers must have numbers only.","Change Fields",JOptionPane.INFORMATION_MESSAGE);
//		if(StringHelper.hasAllLetters(first.getText()) && StringHelper.hasAllLetters(last.getText()) && !StringHelper.hasAllLetters(phone.getText()))
//		{
//			if(phone.getText().length() != 7 && phone.getText().length() != 10 && phone.getText().length() !=11)
//				JOptionPane.showMessageDialog(null,"Phone Numbers cannot be less then 7 letters or more then 10.","Change Fields",JOptionPane.INFORMATION_MESSAGE);
//			if(phone.getText().length() == 7 || phone.getText().length() == 10 || phone.getText().length() == 11)
//			{
//				if(file.openForWriting(true))
//				{
//					JOptionPane.showMessageDialog(null,first.getText() + " " + last.getText() + " was stored." ,"Name Stored",JOptionPane.INFORMATION_MESSAGE);
//					file.println(first.getText() + ", " + last.getText() + ", " + phone.getText());
//					WidgetBar.frame.dispose();	
//					new WidgetStart();				
//				}
//			}
//		}
//	}
//
//}