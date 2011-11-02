/*Joseph Fabisevich
 *Michael Knower
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.Label;

import java.awt.dnd.DropTarget;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import helliker.id3.ID3TagReader;

import ez.util.EZ;
import ez.util.Utility;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *MP3 Widget
 *
 *@author Michael Knower
 */
public class MP3Player extends Widget implements DropTargetListener, ActionListener
{
	private String currTrack;
	private JLabel artist, track, album;
	private JButton play, stop;
	private JSoundSimple player;
	
	/**
     * Create a new MP3 Widet
     */
	public MP3Player()
	{
		super("MP3Widget", 135);
		init();
	}
	/**
     * Intialize the MP3 Widget
     */
	public void init()
	{
		super.setBackground(Color.black);
		
		//widget title label
		JLabel title = new JLabel("MP3Player", SwingConstants.CENTER);
		//title.setAlignment(JLabel.BOTTOM_ALIGNMENT);
		title.setBackground(Color.black);
		title.setForeground(Color.red);
		title.setSize(getWidth(), 20);
		title.setLocation(0,0);
		title.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		
		//drag here label
		JLabel dd = new JLabel("< [ Drag File Here ] >", SwingConstants.CENTER);
		//dd.setAlignment((int)JLabel.BOTTOM_ALIGNMENT);
		dd.setBackground(Color.black);
		dd.setForeground(Color.red);
		dd.setSize(getWidth(), 20);
		dd.setLocation(0,20);
		dd.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		
		
		//artist label*mod
		artist = new JLabel("");
		artist.setSize(getWidth(), 15);
		artist.setBackground(Color.black);
		artist.setForeground(Color.red);
		artist.setLocation(0, 40);
		artist.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		
		//track label*mod
		track = new JLabel("No Track");
		track.setSize(getWidth(), 15);
		track.setBackground(Color.black);
		track.setForeground(Color.red);
		track.setLocation(0, 70);
		track.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		
		//album label*mod
		album = new JLabel("");
		album.setSize(getWidth(), 15);
		album.setBackground(Color.black);
		album.setForeground(Color.red);
		album.setLocation(0, 55);
		album.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		
		
		//play button
		play = new JButton(new ImageIcon(ez.io.EZFile.projectDir()+"\\playico.jpg"));
		play.setActionCommand("play");
		play.setSize(46, 50);
		play.setLocation(0, 85);
		play.addActionListener(this);
		play.setEnabled(false);
		
		//stop button
		stop = new JButton(new ImageIcon(ez.io.EZFile.projectDir()+"\\stopico.jpg"));
		stop.setActionCommand("stop");
		stop.setSize(46, 50);
		stop.setLocation(getWidth()-stop.getWidth(), 85);
		stop.addActionListener(this);
		stop.setEnabled(false);
		
		//music buddy!
		JLabel ico = new JLabel(new ImageIcon("music_buddy.gif"));
		ico.setSize(stop.getX()-(play.getX()+play.getWidth()), 50);
		ico.setLocation((getWidth()/2)-(ico.getWidth()/2), 85);
		
		//add the screen elements to the WidgetPanel
		
		////topLabels
		super.add(title);
		super.add(dd);
		////modular labels
		super.add(artist);
		super.add(album);
		super.add(track);
		////buttons and musicbuddy
		super.add(play);
		super.add(ico);
		super.add(stop);
		
		//shows
		title.setVisible(true);
		dd.setVisible(true);
		
		artist.setVisible(true);
		album.setVisible(true);
		track.setVisible(true);
		
		play.setVisible(true);
		ico.setVisible(true);
		stop.setVisible(true);
		
		//enable DnD operations
		super.setDropTarget(new DropTarget(this, this));
	}
	
	
	
	/**
     * Processes the Drop event. There are NO preconditions to this method.
     * The data comes in unaltered and is then manipulated to handle 
     * ONLY mp3 type files. Any other file, or groups fo files are discarded
     */
	public boolean processDrop(Object o)
	{
		//tag reader(speedy one)
		ID3TagReader tag = null;
		
		String tmp = o.toString();
		tmp = tmp.substring(1, tmp.length()-1);
		String paths[] = tmp.split(", ");
		
		//finds the first mp3 file in the files (or file) and readys
		//if none are found return false
		for (int i = 0; i<paths.length; i++)
		{	
			if(paths[i].endsWith(".mp3"))
			{				
				//display in window
				try{tag = new ID3TagReader(paths[i]);}
			    catch(IOException ioe){ioe.printStackTrace();}
			    
			    //set labels
				artist.setText("- " + tag.getArtist());
				album.setText ("- " + tag.getAlbum() );
				track.setText ("- " + tag.getTitle() );
				
				//currTrack 
				currTrack = paths[i];
				
				//enable buttons
				play.setEnabled(true);
				stop.setEnabled(true);
				
				actionPerformed(new ActionEvent(play, ActionEvent.ACTION_PERFORMED, "play"));
				return true;
			}
		}
		return false;
	}
	
	/**
	 *plays 
	 */
	private void play()
	{
		if(player != null)
			player.stop();
		player = new JSoundSimple(this.currTrack);
		player.start();
	}


	/**
	 *Perform button actions
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equalsIgnoreCase("play"))
			play();
		if(e.getActionCommand().equalsIgnoreCase("stop"))
			player.stop();
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
          	if(!processDrop(s))
          		JOptionPane.showMessageDialog(this, "Non mp3 File(s)");
            
        } 
        catch (java.io.IOException ioe){e.rejectDrop();}
        catch (UnsupportedFlavorException ioe){e.rejectDrop();}
    }
}


/**
 * Minimalist Version of JSoundController.
 * Has the most basic functions and only works for MP3 Files.
 */
class JSoundSimple
{
	private File soundFile;
	private AdvancedPlayer player;
	private boolean ON;
	
	
	/**
     * Create a new instance with the specified sound source
     */
	public JSoundSimple(String filePath)
	{
		soundFile = new File(filePath);
		init();
	}
	
	
	/**
     * Initialize
     */
	private void init()
	{
		ON = false;
		try 
		{player = new AdvancedPlayer(new BufferedInputStream(new FileInputStream(soundFile)));}
	    catch(FileNotFoundException fnfe){fnfe.printStackTrace();}
	    catch(JavaLayerException jle){jle.printStackTrace();}
	}
	
	/**
     * Get current Status: true for ON, false for OFF
     * @return boolean: true of playback is currently in progress, else false
     */
    public boolean getStatus()						{return ON;}
	
	/**
     * Stop Playback
     */
	public void stop()
	{
		ON = false;
		player.close();
	}
	
	/**
     * Start Playback
     */
	public void start()
	{
		if(!ON)
		{
			ON = true;
			new Thread(){
			public void run()
			{
				try {player.play(0, Integer.MAX_VALUE);}
			    catch (JavaLayerException jle){jle.printStackTrace();}
			}}.start();
		}
	}		
}