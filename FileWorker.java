/*Joseph Fabisevich
 *Michael Knower
 */

/*FileWorker Class
 *Ivan Turner
 *
 *Simplifies reading and writing text files with one class.
 */
 
import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *The FileWorker class uses a simple string, the name of a file, to reference
 *a text file.
 *
 *Once the FileWorker object has been created, the file can be opened for
 *reading or writing.
 *
 *A FileWorker object can not be opened for both reading and writing.  If it
 *has been previously opened for one, and is then opened for the other, it
 *cannot be used for the first unless reopened.
 *
 *@author Ivan Turner
 */
public class FileWorker
{
	//Constants
	/**
	 *The number of lines in the file.
	 */
	public final int length;
	
	//Fields
	private File file;
	private PrintStream out;
	private BufferedReader in;
	
	//Constructor
	/**
	 *Creates a <code>FileWorker</code> object specifying a file.
	 *This filename will be used for all processes carried out with this object.
	 *When the object is opened (either for reading or writing), it will affect
	 *the specified text file.
	 *
	 *@param filename The file to be read or written.
	 */
	public FileWorker(String filename)
	{
		file = new File(filename + ".txt");
		length = length();
	}	

	//openForReading
	//Instantiates the BufferedReader Object so that the File file can be read.
	/**
	 *Opens the FileWorker object so that text characters can be read from
	 *it.
	 *<p>
	 *If the file does not exist or there is some other error, the method
	 *will return false.  Do not attempt to read from the file if it was not
	 *successfully opened.
	 *
	 *@return True if the file was successfully opened.
	 */
	public boolean openForReading()
	{
		try
		{
			out = null;
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		}catch (IOException ioe)
		{
			return false;
		}
		
		return true;
	}
	
	//openForWriting
	//Instantiates the PrintStream object so that the File file can be written.
	/**
	 *Opens the FileWorker object so that text characters can be written to it.
	 *If the file does not exist, the <code>FileWorker</code> will create the
	 *file.
	 *<p>
	 *If the file does exist, the FileWorker will erase all of the data inside
	 *the file.  In order to append to a file, read in all of the data from
	 *the file, add to the data, and rewrite the entire file.
	 *
	 *@return True if the file was successfully opened.
	 */
	public boolean openForWriting()
	{
		try
		{
			in = null;
			out = new PrintStream(new FileOutputStream(file));
		} catch (FileNotFoundException fnfe)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 *Opens the FileWorker object so that text characters can be written to it.
	 *If the file does not exist, the <code>FileWorker</code> will create the
	 *file.
	 *<p>
	 *If the file does exist, the FileWorker will erase all of the data inside
	 *the file unless append is true.  In that case, it will begin writing lines
	 *after the last line of the file.
	 *@param append Tells whether to start writing at the end of the file or
	 *				to replace the whole file.
	 *
	 *@return True if the file was successfully opened.
	 */
	public boolean openForWriting(boolean append)
	{
		try
		{
			in = null;
			out = new PrintStream(new FileOutputStream(file, append));
		} catch (FileNotFoundException fnfe)
		{
			return false;
		}
		
		return true;
	}
	
	//gotoLine
	private void gotoLine(int lineNum) throws IOException
	{
		for (int i = 1; i < lineNum; i++)
			in.readLine();
	}
	
	//length
	private int length()
	{
		openForReading();
		int numLines = 0;
		
		while(true)
		{
			if (readLine() != null)
				numLines++;
			else
				break;
		}
		
		return numLines;
	}
	
	//readLine
	//Reads the next line of text from the file and returns it as a string.
	//Returns null if it has reached the end of the file or the FileWorker is
	//writing.
	/**
	 *Reads the next line of text from the file.
	 *
	 *@return The line of text read or null if the FileWorker has reached the
	 *end of the file.
	 */
	public String readLine()
	{
		try
		{
			if (in != null)
				return in.readLine();
		} catch (IOException ioe){}
		return null;
	}
	
	/**
	 *Reads a specific line of text from the file.  The file is reopened to
	 *reset the line pointer.
	 *
	 *@param lineNum The number of the line you want to read.
	 *
	 *@return The specified line of text read or null if the FileWorker has 
	 *reached the end of the file.
	 */
	public String readLine(int lineNum)
	{
		if (openForReading())
		{
			try
			{
				if (in != null)
				{
					gotoLine(lineNum);
					return in.readLine();
				}
			} catch (IOException ioe){}
		}
		return null;
	}
	
	/**
	 *Reads numLines lines of the text from a file and returns it as an array of Strings.
	 *
	 *@param numLines The number of lines of text you want to read in.
	 *
	 *@return An array containing all of the individual lines as Strings.
	 */
	public String[] readLines(int numLines)
	{
		String lines[] = new String[numLines];
		
		try
		{
			if (in != null)
			{
				for (int i = 0; i < lines.length; i++)
					lines[i] = in.readLine();

				return lines;
			}
		} catch (IOException ioe){}
		return null;
	}
	
	/**
	 *Reads all of the text from the file, starting at the line specified
	 *with startLine.
	 *
	 *@param numLines The number of lines of text you want to read in.
	 *@param startLine The line number where you want to start reading.
	 *
	 *@return An array containing all of the individual lines as Strings.
	 */
	public String[] readLines(int numLines, int startLine)
	{
		String lines[] = new String[numLines];
		
		if (openForReading())
		{
			try
			{
				if (in != null)
				{
					gotoLine(startLine);
					for (int i = 0; i < lines.length; i++)
						lines[i] = in.readLine();
	
					return lines;
				}
			} catch (IOException ioe){}
		}
		
		return null;
	}

	/**
	 *Reads all of the text from a file and returns it as an array of Strings.
	 *
	 *@return An array containing all of the individual lines as Strings.
	 */
	public String[] readLines()
	{
		return readLines(length);
	}
	
	//println
	//Prints an object to the file.
	/**
	 *Prints the String representation of the specified object to the file and
	 *moves to the next line.
	 *
	 *@param o The object to be written.
	 */
	public void println(Object o)
	{
		if (out != null)
			out.println(o);
	}
	
	//Prints a String to the file.
	/**
	 *Prints the specified String to the file and moves to the next line.
	 *
	 *@param str The String to be written.
	 */
	public void println(String str)
	{
		if (out != null)
			out.println(str);
	}

	//Prints an integer to the file.
	/**
	 *Prints the specified int to the file and moves to the next line.
	 *
	 *@param i The integer to be written.
	 */
	public void println(int i)
	{
		if (out != null)
			out.println(i);
	}

	//Prints a double to the file.
	/**
	 *Prints the specified double to the file and moves to the next line.
	 *
	 *@param d The double to be written.
	 */
	public void println(double d)
	{
		if (out != null)
			out.println(d);
	}

	//Prints a boolean to the file.
	/**
	 *Prints the specified boolean to the file and moves to the next line.
	 *
	 *@param b The boolean to be written.
	 */
	public void println(boolean b)
	{
		if (out != null)
			out.println(b);
	}

	//Prints a character to the file.
	/**
	 *Prints the specified char to the file and moves to the next line.
	 *
	 *@param c The char to be written.
	 */
	public void println(char c)
	{
		if (out != null)
			out.println(c);
	}

	//print
	//Prints an object to the file.
	/**
	 *Prints the String representation of the specified object to the file.
	 *
	 *@param o The object to be written.
	 */
	public void print(Object o)
	{
		if (out != null)
			out.print(o);
	}
	
	//Prints a String to the file.
	/**
	 *Prints the specified String to the file.
	 *
	 *@param str The String to be written.
	 */
	public void print(String str)
	{
		if (out != null)
			out.print(str);
	}

	//Prints an integer to the file.
	/**
	 *Prints the specified int to the file.
	 *
	 *@param i The integer to be written.
	 */
	public void print(int i)
	{
		if (out != null)
			out.print(i);
	}

	//Prints a double to the file.
	/**
	 *Prints the specified double to the file.
	 *
	 *@param d The double to be written.
	 */
	public void print(double d)
	{
		if (out != null)
			out.print(d);
	}

	//Prints a boolean to the file.
	/**
	 *Prints the specified boolean to the file.
	 *
	 *@param b The boolean to be written.
	 */
	public void print(boolean b)
	{
		if (out != null)
			out.print(b);
	}

	//Prints a character to the file.
	/**
	 *Prints the specified char to the file.
	 *
	 *@param c The char to be written.
	 */
	public void print(char c)
	{
		if (out != null)
			out.print(c);
	}
	
	//File Methods
	//createNewFile
	/**
	 *Creates a new empty file under the name passed into the FileWorker object.
	 *@return	Whether or not the file was successfully created.
	 */
	 public boolean createNewFile()
	 {
	 	try
	 	{
	 		return file.createNewFile();
	 	} catch (IOException ioe)
	 	{
	 		return false;
	 	}
	 } 
	 
	 //deleteFile
	/**
	 *Deletes the file under the name passed into the FileWorker object.
	 *@return	Whether or not the file was successfully deleted.
	 */
	public boolean deleteFile()
	{
		return file.delete();
	}
	
	//exists
	/**
	 *Tells whether or not the file exists.
	 *@return	True if the file exists.
	 */
	 public boolean exists()
	 {
	 	return file.exists();
	 }
	 
	 public void  printLines(String [] str)
	 {
	 	if (out != null)
	 	for(int i = 0; i < str.length; i++)
			out.println(str[i]);
	 }
}