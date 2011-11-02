/*Joseph Fabisevich
 *Michael Knower
 */

package ez.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.player.advanced.*;
import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.*;
import javax.sound.midi.*;

/**
 *A Sound Player by Michael Knower(c) 2006
 *<p> 
 *
 *This class provides an easy way to play music inside JAVA application.
 *The most severe limitation is that the music will only play if the main
 *thread is active.(Wav Files Only) This means that if the program terminates
 *before the song ends, then the song is cut off. However, by using 
 *<pre>
 *	Thread.sleep(JSound.songTimeMills);
 *</pre>
 *This problem can be avoided. 
 *
 *<p> 
 *
 *You CAN play multiple songs at the same time
 *(i.e. call a method from here more than once).
 *<p> 
 *
 *The Song does play independently of wherever it runs
 *(i.e. you can do other things simultaneously).
 *<p> 
 *
 *A new Thread can be created with and object of this class as
 *the Runnable parameter.
 *<p> 
 *
 * <pre>
 *	JSound.loop(song file goes here);
 *</pre>
 *
 *Will play the song file endlessly
 *<p><p>
 *
 *MP3 playing supported.
 *<p>
 *REQUIRES the JavaZoom JavaLayer Package to play MP3 Files which
 *can be downloaded at 
 *<p>
 *<code>http://www.javazoom.net/javalayer/javalayer.html</code>
 *
 *@Author Michael Knower
 *@version 	2.0,  Mar 04, 2006
 *@see javax.sound.sampled
 *@since	JDK 1.42
 */


public class JSound implements Runnable
{
/**Fields**/
	
	/**
     * The File to play
     */
	private File soundFile;
	
	/**
	 *Number of times to loop
	 * if Zero, clip plays once.
	 * <p>
	 * this is the number of times to REPEAT the clip 
	 *	*not*
	 * including the initial play
	 * <p>
	 */
	private int numLoops;
	
	/**
     * Boolean as to whether or not the file is playing
     */
	public static boolean isPlaying;
	
	/**
	 * Static variable for a continuous loop
	 * <p>
	 * Same as   <code>Clip.LOOP_CONTINUOUSLY</code>
	 * <p>
	 */
	public static final int LOOP_CONTINUOUSLY = Clip.LOOP_CONTINUOUSLY;
	
	/**
     * The length of the song in MilliSeconds. Can ONLY be accessed while 
     * the song is playing. Otherwise the value will be -1.
     */
	public static long songLengthMillis;
	
	 
/**Get Methods**/	
	/**
	 *	gets the number of times currently specified to loop
     * @returns the number of times specified to loop
     */
	public int getNumLoops()	{return numLoops;}
	/**
	 *	Gets The File currently hely by the Object
     * @returns the File that this object will play
     */
	public File getSoundFile()	{return soundFile;}
	
/**Set Methods**/		
	/**
	 *Sets the number of times to loop
     *@param numLoops the new number of times to loop
     */
	public boolean setNumLoops(int numLoops)
	{
		if(!isPlaying)
		{
			this.numLoops = numLoops;
			return true;
		}
		else
			return false;
	}
	/**
     *Sets the file that <code>play</code> will play
     *@param soundFile the new file to play
     */
	public boolean setSoundFile(File soundFile)
	{
		if(!isPlaying)
		{
			this.soundFile = soundFile;
			return true;
		}
		else
			return false;
	}
	/**
     *Sets the file that <code>play</code> will play
     *@param soundFile the new file to play
     */
	public boolean setSoundFile(String soundFile)
	{
		if(!isPlaying)
		{
			this.soundFile = new File(soundFile);
			return true;
		}
		else
			return false;
	}
	
	
	
/**Constructors**/
	
	
	/**
     *Takes In the path of the File
     *
     *@param  pathName   The path of the file to be played.
     */
	public JSound(String pathName)
	{
		this.soundFile = new File(pathName);
		this.numLoops = 0;
	}
	/**
     *Takes In the path of the File and the number of times to repeat
     *
     *@param  pathName   The path of the file to be played.
     *@param  numLoops   The number of times to repeat.
     */
	public JSound(String pathName, int numLoops)
	{
		this.soundFile = new File(pathName);
		this.numLoops = numLoops;
	}
	/**
     *Takes In the  File and the number of times to repeat
     *@param  soundFile   The file to be played.
     *@param  numLoops   The number of times to repeat.
     */
	public JSound(File soundFile, int numLoops)
	{
		this.soundFile = soundFile;
		this.numLoops = numLoops;
	}	/**
     *Takes In the path of the File and a boolean as to whether to loop
     *endlessly if true, or play once if false
     *@param  pathName   The path of the file to be played.
     *@param  numLoops   The number of times to repeat.
     */	
    public JSound(String pathName, boolean endless)
	{
		this.soundFile = new File(pathName);
		this.numLoops = (endless ? LOOP_CONTINUOUSLY : 0);
	}
	/**
     *Takes In the File and a boolean as to whether to loop
     *endlessly if true, or play once if false
     *@param  soundFile   The file to be played.
     *@param  numLoops   The number of times to repeat.
     */	
	public JSound(File soundFile, boolean endless)
	{
		this.soundFile = soundFile;
		this.numLoops = (endless ? LOOP_CONTINUOUSLY : 0);
	}
	
	
	
/**METHODS**/

	
//NON-STATIC
	/**
     * The non-static play method
     *<p>
     *Plays the sound file passed in to the Constructor
     *@param b Pass in true to loop for the number of times specified when
     *the object was created or false to play once.
     */
	public void loop()
	{loop(soundFile, numLoops);}
	/**
     * The non-static play method
     *<p>
     *Plays the sound file passed in to the Constructor once
     */
	public void play()
	{play(soundFile);}
	
	
//STATIC	
	/**
	 *Plays the file at the specified path ONCE
     *@param  pathName   The path of the file to be played.
	 */
    public static void play(String pathName)
    {play(new File(pathName));}
    
	/**
	 *Plays a continuous loop using the file at the given path
     *@param  pathName   The path of the file to be played.
	 */
    public static void loop(String pathName)
    {loop(new File(pathName), LOOP_CONTINUOUSLY);}
    
	/**
	 *Plays the file at the given path for the specifed number of times
     *@param  pathName   The path of the file to be played.
     *@param  numLoops   The number of times to repeat the clip.
	 */
    public static void loop(String pathName, int numLoops)
    {loop(new File(pathName), numLoops);}
    
    /**
	 *Plays the file ONCE
     *@param  pathName   The file to be played.
	 */
    public static void play(File soundFile)
    {loop(soundFile, 0);}
    
    /**
	 *Plays a continuous loop using the specified file
     *@param  pathName   The file to be played.
	 */
    public static void loop(File soundFile)
    {loop(soundFile, LOOP_CONTINUOUSLY);}
    
	/**
	 *Plays the specified file for the specifed number of times
     *@param  pathName   The file to be played.
     *@param  numLoops   The number of times to repeat the clip.
	 */
	public static void loop(File soundFile, int numLoops)
    {
    	String fileStr = soundFile.toString();
    	
    	if(  !(fileStr.endsWith(".mp3"))  )
    	{
    		if(  (fileStr.endsWith(".mid"))  ||  (fileStr.endsWith(".midi"))  )
    		{
				try 
				{
		            Sequencer sequencer = MidiSystem.getSequencer();
		            sequencer.open();
		            sequencer.setSequence(MidiSystem.getSequence(soundFile));
		            sequencer.start();
		            while(true) 
		            {
		                if(sequencer.isRunning()) 
		                {
				            try 
				            {
				                        Thread.sleep(1000);
				            } 
				            catch(InterruptedException ignore) 
				            {break;}
		                } 
		                else 
		                    break;
		            }
		            sequencer.stop();
		            sequencer.close();
		        } 
		        catch(MidiUnavailableException mue) 
		        {//System.out.println("Midi device unavailable");
		        	mue.printStackTrace();
		        } 
		        catch(InvalidMidiDataException imde) 
		        {System.out.println("Invalid Midi data");} 
		        catch(IOException ioe) 
		        {System.out.println("Error While Accessing File: I/O Exception Error");} 
    		}
    		else
    		{
    			try
				{
					AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
					DataLine.Info clipInfo = new DataLine.Info(Clip.class, stream.getFormat());
					Clip audio = (Clip)AudioSystem.getLine(clipInfo);
					audio.open(stream);
					songLengthMillis = audio.getMicrosecondLength()/1000;
					if(numLoops <= LOOP_CONTINUOUSLY)
						audio.loop(LOOP_CONTINUOUSLY);
					else
						audio.loop(numLoops);
				}
				catch(IOException ioe)
				{System.out.println("Error While Accessing File: I/O Exception Error");}
				catch(UnsupportedAudioFileException uafe)
				{System.out.println("Unsupported Audio File");}
				catch(LineUnavailableException lue)
				{System.out.println("Unable to Play Back File: Line Unavailable Error");}
    		}
    	}
    	else
    	{
    		try
		    {
		    	AdvancedPlayer player = new AdvancedPlayer(new BufferedInputStream(new FileInputStream(soundFile)));
		    	player.play(0, Integer.MAX_VALUE);
		    }
		    catch (Exception e)
		    {
		    	throw new RuntimeException(e.getMessage());
		    }
    	}
    }
	
	/**
     * Overrides the toString method in <code>Object</code>
     */
    public String toString()
    {	
		try
		{
			AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info clipInfo = new DataLine.Info(Clip.class, stream.getFormat());
			Clip audio = (Clip)AudioSystem.getLine(clipInfo);
			audio.open(stream);
			return "JSound instance with file: '"+soundFile+"' to loop "+numLoops+" time(s).\n"
					+audio.getFormat();
		}
		catch(Exception ex)
		{return "JSound instance with file: '"+soundFile+"' to loop "+numLoops+" time(s).\n"+
				 "File Type: Non-PCM, File Size: "+(soundFile.length()/1000)+"kb";}
	} 
    
    /**
     * Overrides the <code>Run</code> method in <code>Runnable</code>
     *<p>
     *Will Play the file specified when the object was created for the specified number of times
     *<p>
     */
    public void run()
    {loop();}
}