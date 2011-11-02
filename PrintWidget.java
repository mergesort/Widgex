/*Joseph Fabisevich
 *Michael Knower
 */

import java.awt.Font;
import java.awt.Label;
import java.awt.Color;

import javax.swing.JOptionPane;

import ez.util.Utility;
import ez.print.TextPrinter;
import ez.print.ImagePrinter;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.dnd.DropTarget;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import java.io.IOException;


/**
 *Quick Print Widget. Drag an image or a TEXT file onto it and it print yo!
 *whoazers! JIhAD! 
 *
 *Supported Image Formats: PNG, JPG, GIF, 
 *
 *@author Michael Knower
 */
public class PrintWidget extends Widget implements DropTargetListener
{
	private String errMsg = "<no error def.>";
	
	/** Create a new PrintWidget */
	public PrintWidget()
	{
		super("PrintWidget", 40);
		init();
	}
	
	/** Initialize */
	private void init()
	{
		
		//widget title label
		Label title = new Label("QuickPrint");
		title.setAlignment((int)Label.BOTTOM_ALIGNMENT);
		title.setBackground(Color.black);
		title.setForeground(Color.red);
		title.setSize(getWidth()-30, 20);
		title.setLocation(0,0);
		title.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		
		//drag here label
		Label dd = new Label("Drag Image/Txt File Here");
		dd.setAlignment((int)Label.BOTTOM_ALIGNMENT);
		dd.setBackground(Color.black);
		dd.setForeground(Color.red);
		dd.setSize(getWidth()-30, 20);
		dd.setLocation(0,20);
		dd.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		
		JLabel ico = new JLabel(new ImageIcon("print.png"));
		ico.setBackground(Color.black);
		ico.setForeground(Color.black);
		ico.setLocation(title.getWidth(), title.getY());
		ico.setSize(getWidth()-title.getWidth(),title.getHeight()+dd.getHeight());
		
		super.add(title);
		super.add(dd);
		super.add(ico);
		
		title.setVisible(true);
		dd.setVisible(true);
		ico.setVisible(true);
		
		//enable DnD operations
		super.setDropTarget(new DropTarget(this, this));
	}
	
	/**
	 *Process the Drop event
	 */
	private boolean processDrop(String s)
	{
		s = s.substring(1, s.length()-1);
		
		
		if(isImage(s))
		{
			boolean fnfe = ImagePrinter.printImage(s);
			if(!fnfe)
				errMsg = "Print Error";
			return fnfe;
		}	
		if(isText(s))
			return printText(s);
		return false;
	}
	/**
	 * Print Helper Method. Reads from file and send to printer
	 */
	private boolean printText(String path)
	{
		BufferedReader in = null;
		
		try{in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			errMsg = "File Error";
			return false;
		}
		
		String lines[] = new String[0];
		String currLine = null;
		int lineCtr = 0;
		
		try{currLine = in.readLine();}
		catch(IOException ioe){ioe.printStackTrace();}
		
		while(currLine != null)
		{
			lines = Utility.arrayResizer(lines, 1);
			lines[lineCtr] = currLine;
			lineCtr++;
			
			try{currLine = in.readLine();}
			catch(IOException ioe){ioe.printStackTrace();}
		}
		
		boolean end =  TextPrinter.sendToPrinter(lines);
		
		if(!end)
			errMsg = "Print Job Cancelled or Failed to Execute Completely";
		return end;
		
	}
	
	
	/**
	 *@return whether or not the file is a .txt file
	 */
	private boolean isText(String path)
	{
		return	(
					path.endsWith(".txt") ||
					path.endsWith(".TXT")
				);
	}
	/**
	 *@return if the file at the String location is an Image file of supported Type
	 */
	private boolean isImage(String path)
	{
		return	(
					path.endsWith(".jpg") ||	//jpgs
					path.endsWith(".JPG") ||
					path.endsWith(".jpeg")||
					path.endsWith(".JPEG")||
					path.endsWith(".jpe") ||
					path.endsWith(".JPE") ||	
					path.endsWith(".gif") ||	//gifs
					path.endsWith(".GIF") ||
					path.endsWith(".png") ||	//pngs
					path.endsWith(".PNG")
				);
	}
	
	/**
     * No Event handle of this type
     */
	public void dragEnter(DropTargetDragEvent e){}
    /**
     * No Event handle of this type
     */
	public void dragOver(DropTargetDragEvent e){}
    /**
     * No Event handle of this type
     */
	public void dragExit(DropTargetEvent e){}
    /**
     * No Event handle of this type
     */
	public void dropActionChanged(DropTargetDragEvent e){}
    /**
     * Handles the drop event onto the widget
     */
	public void drop(DropTargetDropEvent e)
    {
		try 
    	{
            Transferable t = e.getTransferable();
            
          	e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
            Object s = t.getTransferData(DataFlavor.javaFileListFlavor);
            e.getDropTargetContext().dropComplete(true);
          	if(!processDrop(s.toString()))
          		JOptionPane.showMessageDialog(this, "Error: "+errMsg);
            
        }
        catch (IOException ioe){e.rejectDrop();}
        catch (UnsupportedFlavorException ioe){e.rejectDrop();}
    }
}