package ro.tuc.pt.assign2;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
	private int noOfClients;
	private BlockingQueue<Client> clients;
	private AtomicInteger waitingTime;
	private int processingTime;
	
	public Queue()
	{
		clients = new LinkedBlockingQueue<Client>();
		waitingTime = new AtomicInteger();
		processingTime = 0;
	}

	public synchronized void addClient(Client client)
	{
		clients.add(client);
		waitingTime.addAndGet(client.getServTime());
		noOfClients++;
		processingTime += client.getServTime();
		notifyAll();
	}
	
	public int getProcessingTime()
	{
		return this.processingTime;
	}
	
	public int getWaitingTime()
	{
		return waitingTime.get();
	}
	
	public int getNoOfClients()
	{
		return clients.size();
	}
	
	public int getAllClients()
	{
		return this.noOfClients;
	}
	
	public boolean isEmpty()
	{
		if (clients.size() == 0)
			return true;
		else return false;
	}
	
	public Client getClient(int index)
	{
		int i=0;
		for (Client client : clients) {
			if (index == i)
				return client;
			else i++;
		}
		return null;
	}
	
	public synchronized int processClient() throws InterruptedException
	{
		while (clients.size() == 0)
			wait();
		Client client = clients.peek();

		return (client.getServTime());
	}
	
	public void run() {
		
			try {
			while (true) {
				int time = processClient();
				for (int i=0; i<time; i++) {
					Thread.sleep(1000);
					waitingTime.decrementAndGet();
				}
				clients.take();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
