/*Joseph Fabisevich
 *Michael Knower
 */

import javax.swing.JFrame;
/**
 * The Main class
 */
public class WidgetStart
{
	//The Widget Bar
	public static WidgetBar bar;

	/** Create A New Widget Bar */
	public WidgetStart()
	{
		bar = new WidgetBar("Widget");
		add();
		bar.show();
	}
	/** Create A New Widget Bar */
	public void add()
	{
		bar.add(new GoogleBar());
		bar.add(new Utility());
		bar.add(new QuickCalc());
		bar.add(new MP3Player());
		bar.add(new PrintWidget());
		bar.add(new WeatherBug());
		bar.add(new Timer());
//		bar.add(new ScratchPad());
		bar.add(new WOTD());
		bar.add(new Command());
//		bar.add(new Organizer());
		bar.add(new SketchWidget());
	}
	
	/** Main */
	public static void main(String [] args)
	{new WidgetStart();}
	
}
