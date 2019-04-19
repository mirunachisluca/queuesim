package ro.tuc.pt.assign2;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame("Simulare cozi");
	JTextField runTime = new JTextField(4);
	JTextField noOfQueues = new JTextField(4);
	JTextField noOfClients = new JTextField(4);
	JTextField minServTime = new JTextField(4);
	JTextField maxServTime = new JTextField(4);
	JTextField minArrTime = new JTextField(4);
	JTextField maxArrTime = new JTextField(4);
	private JScrollPane q1 = new JScrollPane(new JList<String>());
	private JScrollPane q2 = new JScrollPane(new JList<String>());
	private JScrollPane q3 = new JScrollPane(new JList<String>());
	private JScrollPane q4 = new JScrollPane(new JList<String>());
	private JScrollPane q5 = new JScrollPane(new JList<String>());
	private JPanel queuesPanel = new JPanel();
	JButton start = new JButton("Start");
	
	public GUI()
	{
		
		JLabel clientsNo = new JLabel("Nr. clienti", SwingConstants.LEFT);
		JLabel queuesNo = new JLabel("Nr. cozi", SwingConstants.LEFT);
		JLabel servTime = new JLabel("Timp de asteptare:", SwingConstants.CENTER);
		JLabel arrTime = new JLabel("Timp sosire:", SwingConstants.CENTER);
		JLabel min = new JLabel("min", SwingConstants.LEFT);
		JLabel max = new JLabel("max", SwingConstants.LEFT);
		JPanel controlPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1400,600));
		
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Timp de rulare"));
		p1.add(runTime);
		p1.add(start);
		p1.setLayout(new FlowLayout());
		
		JPanel p2 = new JPanel();
		p2.add(queuesNo);
		p2.add(noOfQueues);
		p2.setLayout(new FlowLayout());
		
		JPanel p3 = new JPanel();
		p3.add(clientsNo);
		p3.add(noOfClients);
		
		JPanel p4 = new JPanel();
		p4.add(arrTime);
		p4.add(new JLabel("min"));
		p4.add(minArrTime);
		p4.add(new JLabel("max"));
		p4.add(maxArrTime);
		
		JPanel p5 = new JPanel();
		p5.add(servTime);
		p5.add(min);
		p5.add(minServTime);
		p5.add(max);
		p5.add(maxServTime);
		
		controlPanel.setLayout(new GridLayout(0,1));
		controlPanel.add(p1);
		controlPanel.add(p2);
		controlPanel.add(p3);
		controlPanel.add(p4);
		controlPanel.add(p5);
		controlPanel.setPreferredSize(new Dimension(270,150));
		
		
		q1.setPreferredSize(new Dimension(200,550));
		q2.setPreferredSize(new Dimension(200,550));
		q3.setPreferredSize(new Dimension(200,550));
		q4.setPreferredSize(new Dimension(200,550));
		q5.setPreferredSize(new Dimension(200,550));
		
		queuesPanel.add(q1);
		queuesPanel.add(q2);
		queuesPanel.add(q3);
		queuesPanel.add(q4);
		queuesPanel.add(q5);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,1));
		p.add(queuesPanel);
		
		mainPanel.add(queuesPanel);
		mainPanel.add(controlPanel);
		
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
			
		
	}
	
	
	public void listQueues(ArrayList<Queue> queues)
	{
		queuesPanel.remove(q1);
		queuesPanel.remove(q2);
		queuesPanel.remove(q3);
		queuesPanel.remove(q4);
		queuesPanel.remove(q5);
		
		for (int i=0; i<queues.size(); i++) {
			String[] data = new String[queues.get(i).getNoOfClients()+1];
			data[0] = "Waiting Time: "+queues.get(i).getWaitingTime();
			for (int j=0; j<queues.get(i).getNoOfClients(); j++) {
				data[j+1]=queues.get(i).getClient(j).printClient();
			}
			JList<String> list = new JList<String>(data);
			
			if (i==0) q1 = new JScrollPane(list);
			else
				if (i==1) q2 = new JScrollPane(list);
				else
					if (i==2) q3 = new JScrollPane(list);
					else
						if (i==3) q4 = new JScrollPane(list);
						else q5 = new JScrollPane(list);
		}
		
		q1.setPreferredSize(new Dimension(200,550));
		q2.setPreferredSize(new Dimension(200,550));
		q3.setPreferredSize(new Dimension(200,550));
		q4.setPreferredSize(new Dimension(200,550));
		q5.setPreferredSize(new Dimension(200,550));
		
		queuesPanel.add(q1);
		queuesPanel.add(q2);
		queuesPanel.add(q3);
		queuesPanel.add(q4);
		queuesPanel.add(q5);
		frame.pack();
		queuesPanel.repaint();
	}

}
