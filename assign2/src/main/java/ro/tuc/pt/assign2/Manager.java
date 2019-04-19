package ro.tuc.pt.assign2;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager implements Runnable {
	public Settings setup;
	private ArrayList<Queue> queues;
	private AtomicInteger time;
	private int maxNoOfClients = 15;
	
	
	
	public Manager()
	{
		
		setup = new Settings();
		queues = new ArrayList<Queue>(5);
		Thread[] queueTh = new Thread[5];
		for (int i=0; i < 5; i++) {
			queues.add(new Queue());
			queueTh[i] = new Thread(queues.get(i));
		}
		for (int i=0; i<5; i++)
		{
			queueTh[i].start();
		}
		time = new AtomicInteger();
		
	}
	
	public Client generateClient()
	{
		Client client = new Client(time.get(), setup.minArrTime, setup.maxArrTime, setup.minServTime, setup.maxServTime);
		return client;
	}
	
	public void addClientToQueue(Client client)
	{
		int minTimeQueue = 0;
		int minClientsQueue = 0;
		
		for (int i=0; i < setup.noOfQueues; i++){
			if (queues.get(minClientsQueue).getNoOfClients() > queues.get(i).getNoOfClients())
				minClientsQueue = i;
			if (queues.get(minTimeQueue).getWaitingTime() > queues.get(i).getWaitingTime())
				minTimeQueue = i;			
		}
		
		Random rand = new Random();
		if (rand.nextInt() % 2 == 0) {
			queues.get(minTimeQueue).addClient(client);
			client.setQueueNo(minTimeQueue+1);
		}
		else {
			queues.get(minClientsQueue).addClient(client);
			client.setQueueNo(minClientsQueue+1);
		}
	}

	public void run(){
		
		AtomicInteger t = new AtomicInteger();
		int generatedClients=1;
		int peakHour = 0;
		int clientsNo = 0;
		float avgWT = 0;
		int[] empty = {0, 0, 0, 0, 0};
		Client client = generateClient();
		client.setID(generatedClients);
		time.set(client.getArrivalTime());
		boolean added = false;
		
		Logger.log("Starting simulation...");
		
		while (t.get() < setup.runTime) {
			if ((generatedClients != setup.noOfClients) && added == true ) {
				client = generateClient();
				generatedClients++;
				client.setID(generatedClients);
				time.set(client.getArrivalTime());
				added = false;
			}
			
			if (t.get()==time.get()) {
				addClientToQueue(client);
				added = true;
				Logger.log("t="+t.get()+ " Client "+client.getID()+" arrived at queue "+client.getQueueNo()+" and will have to wait " +client.getServTime()+"s.");
			}
			
			setup.interf.listQueues(queues);
			
			System.out.println(t+"s");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			t.incrementAndGet();
			
			boolean newQueue = false;
			int currentNoOfClients=0;
			
			for (int i=0; i<setup.noOfQueues; i++)
			{
				if (queues.get(i).getNoOfClients() == maxNoOfClients)
						newQueue = true;
				if (queues.get(i).isEmpty())
					empty[i]++;
				
				currentNoOfClients += queues.get(i).getNoOfClients();
				
				
			}
			if (currentNoOfClients > clientsNo)
			{
				clientsNo = currentNoOfClients;
				peakHour = t.get();
			}
			
			if (newQueue && setup.noOfQueues < 5) {
				setup.setQueuesNo(setup.noOfQueues + 1);
				newQueue = false;
			}
				
		}
		
		for (int i=0; i<setup.noOfQueues; i++)
		{
			avgWT += queues.get(i).getProcessingTime();
		}
		
		avgWT /= generatedClients;
		
		Logger.log("... Simulation ended"); Logger.log("");
		Logger.log("Simulation ran for "+setup.runTime+" seconds.");
		Logger.log("Generated clients: "+generatedClients+"/"+setup.noOfClients);
		Logger.log("Average waiting time per client: " + avgWT);
		Logger.log("Peak hour was at second " + peakHour + " with a total of " + clientsNo + " clients.");
		int i=1;
		for (Queue q : queues)
		{
			Logger.log("Queue "+i+" served "+q.getAllClients()+" clients with a total of "+q.getProcessingTime()+"s as waiting time and was empty for " + empty[i-1] + "s.");
			i++;
			if (!q.isEmpty())
				Logger.log("Queue "+i+" didn't finish processing the clients. " + q.getNoOfClients()+" client(s) remaining.");
		}
		Logger.log("\n"); 
		

	}
	
	public ArrayList<Queue> getQueues()
	{
		return this.queues;
	}
	
	public int getTime()
	{
		return time.get();
	}
	
	public void setTime(int t)
	{
		time.set(t);
	}
	


}
