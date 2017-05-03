import java.net.*;
import java.io.*;
public class Server 
{
	
public static void main(String [] args)
{
	try
	{
		ServerSocket server = new ServerSocket(1025);
		System.out.println("waiting...");
		Socket s = server.accept();
		System.out.println("Connected");
		
		//initialize out and in stream
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintStream out = new PrintStream(s.getOutputStream());
		
		Computer cpu = new Computer();
		boolean cont = true;
		 for (String line = in.readLine(); line != null; line = in.readLine()) 
		 {
		     System.out.println(line);
		 }
	
		//send result back to client
		
		System.out.println("Sending Result...");
		//out.println(input);
		
		//close connection
		server.close();
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
}
}
