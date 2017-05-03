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
		
		//get input from client
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//something wrong with this
		String input = in.readLine();
		
		System.out.println("Recieving Data: "+ input);
		
		
		//process input
		//String [] values=input.split(",");
		//int sum = 0;
		//for(int i = 0; i <values.length; i++)
		//{
		//	sum+=Integer.parseInt(values[i]);
		//}
	
		//send result back to client
		PrintStream out = new PrintStream(s.getOutputStream());
		System.out.println("Sending Result...");
		out.println(input);
		
		//close connection
		server.close();
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
}
}
