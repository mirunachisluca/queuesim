package ro.tuc.pt.assign2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {
	GUI interf;
	int runTime;
	int noOfClients;
	int noOfQueues;
	int minServTime;
	int maxServTime;
	int minArrTime;
	int maxArrTime;
	
	public Settings()
	{
		interf = new GUI();
		interf.runTime.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) {
				String s = new String();
				s = interf.runTime.getText();
				runTime = Integer.parseInt(s);
			}
		});
		interf.noOfClients.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				String s = new String();
				s = interf.noOfClients.getText();
				noOfClients = Integer.parseInt(s);
			}
		});
		interf.noOfQueues.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String s = new String();
				s = interf.noOfQueues.getText();
				noOfQueues = Integer.parseInt(s);
			}			
		});
		interf.minArrTime.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				String s = new String();
				s = interf.minArrTime.getText();
				minArrTime = Integer.parseInt(s);
			}
		});
		interf.maxArrTime.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				String s = new String();
				s = interf.maxArrTime.getText();
				maxArrTime = Integer.parseInt(s);
			}
		});
		interf.minServTime.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String s = new String();
				s = interf.minServTime.getText();
				minServTime = Integer.parseInt(s);
			}
		});
		interf.maxServTime.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String s = new String();
				s = interf.maxServTime.getText();
				maxServTime = Integer.parseInt(s);
			}
		});
		
	}
	
	public void setQueuesNo(int nr)
	{
		noOfQueues = nr;
	}
	
}


