/*Joseph Fabisevich
 *Michael Knower
 */

import javax.swing.JPanel;


/**
 *The Top if the Widget Heirarchy(besides Object)
 *this class is a simple modification the a JPanel because Michael doesnt like setVisible(boolean)
 *
 *@author Michael Knower
 */
public class WidgetPanel extends JPanel
{
	/**
	 *The constructor takes in a width and a height for the window.
	 *@param width	The width of the window.
	 *@param height	The height of the window.
	 */
	public WidgetPanel(int width, int height)
	{
		super(null);
		super.setSize(width, height);
	}
	
	/**
	 *Sets Visible: true
	 */
	public void show()
	{super.setVisible(true);}
	
	/**
	 *Sets Visible: false
	 */
	public void hide()
	{super.setVisible(false);}

	/**
	 *Draws all of the added images/shapes/strings to the screen.
	 */
	public void drawScreen()
	{repaint();}
}