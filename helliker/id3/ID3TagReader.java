package helliker.id3;

/**
 *This class allows easy access to the ID3 Tags in MP3 Files. Each instance of
 *this class can only be used for one file at a time.
 *
 *@author Michael Knower
 *
 */
public class ID3TagReader
{
	/**
     * This info [] hold all relavent information from the MP3 File.
     * The information is odered by indices as such:
     * 
     *0 - Track Number
     *1 - Track Title
     *2 - Artist Name
     *3 - Album Name
     *4 - Album Year
     *5 - Genre
     *6 - Song Length (As A String)
     */
	private String tagInfo[] = new String[7];
	//Song Length (As A Long)
	private long songLengthMillis;
	
	
	/**
     * Create a new Instance of the ID3TagReader
     * @throws java.io.IOException if the filename parameter is invalid
     * @param filename the filename of the mp3 file
     */
	public ID3TagReader(String fileName) throws java.io.IOException
	{
		try 
	    {
	    	MP3File mp3 = new MP3File(fileName);
	    	
	    	String tNum = mp3.getTrack();
    		if(tNum.length() < 2)
	    		tNum = "0"+tNum;
	    		
	    	tagInfo[0] = tNum;
	    	tagInfo[1] = mp3.getTitle();
	    	tagInfo[2] = mp3.getArtist();
	    	tagInfo[3] = mp3.getAlbum();
	    	tagInfo[4] = mp3.getYear();
	    	tagInfo[5] = mp3.getGenre();
	    	tagInfo[6] = mp3.getPlayingTimeString();
	    	songLengthMillis = mp3.getPlayingTime();
	    }
	    catch(Exception e){throw new java.io.IOException();}
//	    catch(Exception e){System.out.println("Tag Parse Error in file: "+fileName);}
	}
	
	
	/** @return the title of the mp3file*/
    public String getTitle()		{return tagInfo[1];}
    /** @return the artist of the mp3file*/
    public String getArtist () 		{return tagInfo[2];}
    /** @return the album of the mp3file*/
    public String getAlbum ()  		{return tagInfo[3];}
    /** @return the year of the mp3file*/
    public String getYear ()   		{return tagInfo[4];}
    /** @return the track # of the mp3file*/
    public String getTrackNumber () {return tagInfo[0];}
    /** @return the Genre of the mp3file*/
    public String getGenre ()   	{return tagInfo[5];}
    /** @return the length mins:secs of the mp3file*/
    public String lengthMillis ()		{return tagInfo[6];}//songLengthMillis;}

    
    /** @return TrackNumber - Artist - Title*/
    public String usefulInfo()     		
    {
    	return tagInfo[0]+" - "+tagInfo[2]+" - "+tagInfo[1];
    }

 }

