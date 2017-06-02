		import java.net.*;
import java.io.*;

/**
 * the server program. Makes the game and sends results to the Window client
 * @author Andrew
 *
 */
public class Server 
{
	
	public static void main(String [] args)
	{
		//try/catch loop to initialize buffered reader and printstream 
		try
		{
			//opens the server socket
			ServerSocket server = new ServerSocket(1025);
			System.out.println("waiting...");
			
			//waits to see if the client is also connected to the server socket
			Socket s = server.accept();
			System.out.println("Connected");
			
			//initializes computer
			Computer cpu= new Computer();
			
			//reads cpu from file, if it exists
			File f = new File("cpu.dat");
			if(f.exists())
			{
				try
				{
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("cpu.dat")));
					cpu=(Computer) in.readObject();
					in.close();
				}
				catch(IOException e)
				{
					System.out.println("error processing file");
				}
				catch(ClassNotFoundException e)
				{
					System.out.println("could not find class");
				}
			}
			
			//initialize out and in stream
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintStream out = new PrintStream(s.getOutputStream());
			
			//initialize the user input to 0
			int userMove=0;
			
			//initialize the user pattern to be stored
			String userPattern="        ";
			
			//loops until the client chooses the "quit button"
			while(userMove!=4)
			{
				userMove = Integer.parseInt(in.readLine());
				
				//computer makes prediction based on previous pattern
				int prediction=cpu.makePrediction(userPattern);
				out.println(prediction);
				
				//user chooses "quit button" ends the while loop to end the game
				if(userMove==4)
				{
					break;
				}
				
				//stores the move to pattern
				
				
				switch(userMove)
				{
				case 1:
					userPattern=userPattern.substring(1, userPattern.length())+"F";
					cpu.storePattern(userPattern);
					
					break;
				case 2:
					userPattern=userPattern.substring(1, userPattern.length())+"W";
					cpu.storePattern(userPattern);
					break;
				case 3:
					userPattern=userPattern.substring(1, userPattern.length())+"G";
					cpu.storePattern(userPattern);
				}
				
				  


		} 
			
			
			//stores cpu object to file cpu.dat
			try
			{
				ObjectOutputStream cpuOut=new ObjectOutputStream(new FileOutputStream(new File("cpu.dat")));
				cpuOut.writeObject(cpu);
				cpuOut.close();
			}
			catch(IOException e)
			{
				System.out.println("Error processing file");
			}
			
			//close connection
			server.close();
			System.out.println("done");
			System.exit(0);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
