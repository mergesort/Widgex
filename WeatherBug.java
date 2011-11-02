/*Joseph Fabisevich
 *Michael Knower
 */

import ez.util.Utility;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLDocument;


/**
 *WeatherBug Widget.  A widget that enables you to get the weather of any area
 *code in the US.  If you do not know the area code you are in you can use the 
 *GoogleBar widget to look it up.  It will not work unless you have an internet
 *connection.  
 *@author Joseph Fabisevich
 */
public class WeatherBug extends Widget
{
	private JTextField zip, temp;
	private JButton setZip;
	private JLabel display;	
	private static String temperature = "";
		
	/** 
	 *Creates a new WeatherBug
	 */
	public WeatherBug()
	{
		super("WeatherBug", 62);
		super.setBackground(java.awt.Color.BLACK);
		init();
	}

	/**
	 *Takes in the URI to be used and then reads the HTML document that has come
	 *of the URI.  It searches for the 27th line and then substrings that cutting
	 *off the first 2 characters.  Then it converts the String to a URI to a URL
	 *which opens the connection and reads it from the site.
	 *@param uriStr the URI which will be converted into the URL of which the
	 *lines of html will be read from.
	 *@return the String representation of the forecast given.
	 *
	 */
	public String weatherFeed(String uriStr)  
    { 
        final StringBuffer buf = new StringBuffer(1000); 
		if(zip.getText().length() == 5)
		{
	        try  
	        { 
	            // Create an HTML document that appends all text to buf 
	            HTMLDocument doc = new HTMLDocument()  
	            { 
	                public HTMLEditorKit.ParserCallback getReader(int pos)  
	                { 
	                    return  
	                         new HTMLEditorKit.ParserCallback()  
	                         { 
	                         	int i = 0;
	                             public void handleText(char[] data, int pos)  
	                             { 
	    	                             buf.append(data); 
	    	                             i++;
	    	                             if(i == 27)
	    	                             {
	    	                             	temperature = new String(data);
	    	                             	temperature = temperature.substring(2);
	    	                             	temp.setText(temperature);
	    	                             }	
	    	                             buf.append('\n'); 
	                             } 
	                         }; 
	                }
	            }; 
	     
	            // Create a reader on the HTML content 
	            URL url = new URI(uriStr).toURL(); 
	            URLConnection conn = url.openConnection(); 
	            Reader rd = new InputStreamReader(conn.getInputStream()); 
	     
	            // Parse the HTML 
	            EditorKit kit = new HTMLEditorKit(); 
	            kit.read(rd, doc, 0);
	        }  
	        catch (MalformedURLException e){} 
	        catch (URISyntaxException e){} 
	        catch (BadLocationException e){} 
	        catch (IOException e){} 
			
    	}	
    	else
    		JOptionPane.showMessageDialog(this, "Enter a zip code of 5 digits");
	        return temperature; 
    }

	/**
	 *Init 
	 */
	public void init()
	{
		display = new JLabel("WeatherBug",javax.swing.SwingConstants.CENTER);
		display.setSize(154,20);
		display.setLocation(0,0);
		display.setBackground(java.awt.Color.BLACK);
		display.setForeground(java.awt.Color.RED);
		display.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		
		temp = new JTextField();
		temp.setSize(76,21);
		temp.setLocation(77,20);
		temp.setEditable(false);


		zip = new JTextField();
		zip.setSize(76,21);
		zip.setLocation(0,20);
		zip.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					temp.setText(weatherFeed("http://xml.weather.yahoo.com/forecastrss?p="+zip.getText()));
				}
			}	
		});


		setZip = new JButton("Confirm Zip");
		setZip.setSize(154, 20);
		setZip.setLocation(0,42);
		setZip.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				try{
					Integer.parseInt(zip.getText());
					temp.setText(weatherFeed("http://xml.weather.yahoo.com/forecastrss?p="+zip.getText()));
				}catch(NumberFormatException ex){temp.setText("Zip codes have numbers.");}
			}
		});
	
		super.add(zip);
		super.add(setZip);
		super.add(temp);
		super.add(display);
		
		zip.show();
		setZip.show();
		temp.show();
		display.show();
	}
}