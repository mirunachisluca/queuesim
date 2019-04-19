package ro.tuc.pt.assign2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation implements Runnable {
	private Manager manager;
	
	public Simulation()
	{
		manager = new Manager();
	}
	
	
	public void run() {
		// TODO Auto-generated method stub
		manager.setup.interf.start.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread(manager);
				thread.start();
			}
		});
		
		
	}
	
	public static void main(String[] args)
	{
		Simulation sim = new Simulation();
		Thread th = new Thread(sim);
		th.start();
		
	}

}
