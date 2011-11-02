/*Joseph Fabisevich
 *Michael Knower
 */

import javax.swing.JProgressBar;
import com.vladium.utils.SystemInformation;

/**
 * DEPRECATED
 */
public class SysStats extends Widget
{
	private Runtime runtime =  Runtime.getRuntime();
	private JProgressBar memBar, cpuBar;
	
	public SysStats()
	{
		
		super("SysStats", 20);
		System.out.println("DEPRECATED"+ this.getClass().getName());
		init();
		runMonitors();
	}
	
	
	private void init()
	{
		memBar = new JProgressBar(0, (int)runtime.totalMemory());
		memBar.setSize(getWidth(), 20);
		memBar.setLocation(0,0);
        memBar.setValue(0);
        memBar.setStringPainted(true);
        
//        cpuBar = new JProgressBar(0, 100);
//		cpuBar.setSize(getWidth(), 20);
//		cpuBar.setLocation(0,20);
//        cpuBar.setValue(0);
//        cpuBar.setStringPainted(true);
        
        add(memBar);
//        add(cpuBar);
        memBar.setVisible(true);
//        cpuBar.setVisible(true);
    }
	
	private void runMonitors()
	{
		//mem thread
		new Thread()
        {
        	public void run()
        	{
//        		SystemInformation.CPUUsageSnapshot first, last;
				while(true)
        		{
//					first = SystemInformation.makeCPUUsageSnapshot();
        			try{Thread.sleep(750);}
        			catch(InterruptedException iE){iE.printStackTrace();}   
        			memBar.setValue( (int)(runtime.totalMemory() - runtime.freeMemory()) );  
        			memBar.setString("Mem Usage: "+Math.round((memBar.getPercentComplete()*100))+"%"); 
//        			last = SystemInformation.makeCPUUsageSnapshot();
//        			
//        			double val = SystemInformation.getProcessCPUUsage(first,last)*100;
//        			cpuBar.setValue((int)val);
//					cpuBar.setString("CPU Usage: "+Math.round((cpuBar.getPercentComplete()*100))+"%");
//        			System.gc();		
        		}
        	}
        }.start();
	}
	
}
