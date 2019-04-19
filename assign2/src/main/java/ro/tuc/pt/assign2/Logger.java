package ro.tuc.pt.assign2;


import java.io.*;

public class Logger {
	
	public static void log(String message) { 
	      PrintWriter out;
			try {
				out = new PrintWriter(new FileWriter("event_logger.txt", true), true);
			    out.println(message);
			    out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
