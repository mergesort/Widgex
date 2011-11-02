/*Joseph Fabisevich
 *Michael Knower
 */

/**
 *The main superclass of all Widgets. This enable the widgets to follow a basic blueprint
 *
 *@author Michael Knower
 */
public abstract class Widget extends WidgetPanel
{
	/**
	 * The Widget's unique ID tag. One Widget may not run more than once
	 * simultaneously
	 */
	private String ID;
	
	/**
	 *Creates a new Widget. The width is determined by the toolbar so only height is modular
	 */
	public Widget(String ID, int widgetHeight)
	{
		super(Statics.widgetWidth, widgetHeight);
		init(ID);
	}
	
	/**initialize*/
	private void init(String ID)
	{this.ID = ID;}
	
	/**
	 * Returns the ID of this Widget
	 * @return the ID of this Widget
	 */
	public String getWidgetID(){return ID;}
}