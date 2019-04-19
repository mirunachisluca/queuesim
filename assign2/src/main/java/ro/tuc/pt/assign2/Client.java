package ro.tuc.pt.assign2;

import java.util.Random;

public class Client {
	private int id;
	private int arrivalTime;
	private int serviceTime;
	private int queueNo;
	
	public Client()
	{
		arrivalTime = 0;
		serviceTime = 0;
		queueNo = 0;
	}
	
	public Client(int currTime, int minArrTime, int maxArrTime, int minServTime, int maxServTime)
	{
		Random rand = new Random();
		IncrementID();
		arrivalTime = currTime + rand.nextInt(maxArrTime - minArrTime) + minArrTime;
		serviceTime = rand.nextInt(maxServTime - minServTime) + minServTime;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setQueueNo(int no)
	{
		this.queueNo = no;
	}
	
	public int getQueueNo()
	{
		return this.queueNo;
	}
	
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	public int getServTime()
	{
		return serviceTime;
	}
	
	public void IncrementID()
	{
		id ++;
	}
	
	public String printClient()
	{
		return ("Client "+id+ "    AT  "+arrivalTime+ "    ST  "+serviceTime);
	}
}
