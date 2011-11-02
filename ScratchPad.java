///*Joseph Fabisevich
// *Michael Knower
// */
//
//package widget;
//
//import java.io.FileNotFoundException;
//import java.io.BufferedOutputStream;
//import java.io.BufferedInputStream;
//import java.io.FileOutputStream;
//import java.io.FileInputStream;
//
//import java.beans.XMLDecoder;
//import java.beans.XMLEncoder;
//
//import java.awt.Font;
//import java.awt.Color;
//import java.awt.Label;
//
//import ez.util.EZ;
//import ez.io.EZFile;
//
//import javax.swing.ImageIcon;
//import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import javax.swing.JButton;
//import javax.swing.JTextArea;
//
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//
///**
// *ScratchPad Widget. A quick notepad to jot down simple notes. No Formatting is 
// *supported but the text will remain in the scratchpad even after a restart. 
// *This is accomplished using the Beans package <code>XMLEncoder</code> which 
// *writes the scratchpad to file and reloads it on the next start
// *
// *@author Michael Knower
// */
//public class ScratchPad extends Widget
//{
//	/*
//	 *use XMLEncoder for version compatibility
//	 *Note: Remember the warnings about the serializable incompatibilites in future versions? yeah 
//	 */
//	 
//	/** The File to use for the text in the pad */
//	private String fileLoc = "txtbx";
//	/** The textBox itself */
//	private JTextArea textbox;
//	
//	/** Creates a new ScratchPad */
//	public ScratchPad()
//	{
//		super("ScratchPad", 100);
//		init();
//		loadText();
//	}
//	
//	/** Init */
//	private void init()
//	{
//		//widget title label
//		Label title = new Label("ScratchPad");
//		title.setAlignment((int)Label.BOTTOM_ALIGNMENT);
//		title.setBackground(Color.black);
//		title.setForeground(Color.red);
//		title.setSize(getWidth()-20, 20);
//		title.setLocation(0,0);
//		title.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
//		
//		JButton save = new JButton(new ImageIcon("save.png"));
//		save.setSize(20, 20);
//		save.setLocation(title.getWidth(), title.getY());
//		save.setBackground(Color.black);
//		save.setForeground(Color.red);
//		save.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
//		
//		textbox= new JTextArea();
//		textbox.setLineWrap(true);
//		textbox.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
//		textbox.setSize(getWidth(), 20);
//		textbox.setLocation(0, save.getY()+save.getHeight());
//		textbox.setAutoscrolls(true);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setViewportView(textbox);
//		scrollPane.setSize(getWidth(), 80);
//		scrollPane.setLocation(0, title.getY()+title.getHeight());
//		
//		super.add(title);
//		super.add(save);
//		super.add(scrollPane);
//		
//		save.show();
//		title.show();
//		scrollPane.show();
//		
//		save.addMouseListener(new MouseAdapter()
//		{
//			public void mousePressed(MouseEvent e)
//			{saveText();}
//		});
//	}
//	
//	/** Saves the text to file */
//	private void saveText()
//	{
//		//Thread this to make it run smoother
//		new Thread(){public void run(){storeObject(textbox, fileLoc);}}.start();
//	}	
//	
//	/** loads text to textbox */
//	private void loadText()
//	{
//		//load the object itself form file and access the text field
//		String text = (  (JTextArea)retrieveObject(fileLoc)  ).getText();
//		if(text!=null)
//			textbox.setText(text);
//		else
//			JOptionPane.showMessageDialog(this, "Unable to load ScratchPad Document. Make sure "+fileLoc+" is present.");
//	}
//	
//	/**
//     * Stores the object parameter as an XML file
//     * @param o the object to be stored
//     * @param fileName the filename to store as
//     */
//	private void storeObject(Object o, String fileName)
//	{
//		try 
//		{
//			XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName + ".xml")));
//			e.writeObject(o);
//			e.close();
//			JOptionPane.showMessageDialog(this, "Saved!");
//	    }
//	    catch (FileNotFoundException ex) 
//	    {
//	    	JOptionPane.showMessageDialog(this, "Unable to load ScratchPad Document. Make sure "+fileLoc+" is present.");
//	    	System.out.println("Unable to Store Object");
//	    }
//	}
//	/**
//     * Retrieves a stored object from an XML file
//     * @param filePath the path of the object to be Retrieved
//     * @return the retrieved object, if retrieval failed, returns null
//     */
//	private Object retrieveObject(String filePath)
//	{
//		try 
//		{
//			XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath + ".xml")));
//			Object result = d.readObject();
//			d.close();
//			return result;
//		}
//	    catch (FileNotFoundException ex) 
//	    {
//	    	System.out.println("Unable to get Object");
//	    	return null;
//	    }
//	}
//
//	
//	
//	
//}