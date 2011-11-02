/*Joseph Fabisevich
 *Michael Knower
 */

import java.awt.*;

/**
 *A class for static methods and variables to be used throughout the program.
 */
public class Statics
{
	public static final int widgetX = 600;
	public static final int widgetY = 500;
	public static final int widgetWidth = 154;
	public static final int barHeight = 200;
	public static final int standard = 18;
	public static final int textHeight = 20;
	public static final int textWidth = 31;

    public static Dimension getScreenDimensions()
    {return Toolkit.getDefaultToolkit().getScreenSize();}

    public static int getScreenWidth()
    {return new Rectangle(getScreenDimensions()).width;}
    
    public static int getScreenHeight()
    {return new Rectangle(getScreenDimensions()).height;}

}