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
			
			//initializes computer
			Computer cpu= new Computer();
			int tieCount=0;
			int lossCount=0;
			int winCount=0;
			
			//initialize out and in stream
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			int userMove=3;
			String userPattern="     ";
			
			while(userMove!=4)
			{
				
				int prediction=cpu.makePrediction(userPattern);
				
				userMove = Integer.parseInt(in.readLine());
				
				switch(prediction)
				{
				case 1:
					System.out.println("Computer predicted fire");
					break;
				case 2:
					System.out.println("Computer predicted water");
					break;
				case 3:
					System.out.println("Computer predicted grass");
					break;
				default:
					break;
				}
				
				System.out.println(userMove);
				if(userMove==4)
				{
					break;
				}
				
				switch(userMove)
				{
				case 1:
					System.out.println("you picked fire");
					if(prediction==1)
					{
						System.out.println("Its a tie");
						tieCount++;
					}
					else if(prediction == 2)
					{
						System.out.println("you lost");
						lossCount++;
					}
					else
					{
						System.out.println("you won");
						winCount++;
					}
					userPattern=userPattern.substring(1, userPattern.length())+"F";
					cpu.storePattern(userPattern);
					break;
				case 2:
					System.out.println("you picked water");
					if(prediction==1)
					{
						System.out.println("you won");
						winCount++;
					}
					else if(prediction==2)
					{
						System.out.println("Its a tie");
						tieCount++;
					}
					else
					{
						System.out.println("you lost");
						lossCount++;
					}
					userPattern=userPattern.substring(1, userPattern.length())+"W";
					cpu.storePattern(userPattern);
					break;
				case 3:
					System.out.println("you picked grass");
					if(prediction==1)
					{
						System.out.println("you lost");
						lossCount++;
					}
					else if(prediction==2)
					{
						System.out.println("you won");
						winCount++;
					}
					else
					{
						System.out.println("Its a tie");
						tieCount++;
					}
					userPattern=userPattern.substring(1, userPattern.length())+"G";
					cpu.storePattern(userPattern);
					break;
				}

			}
			System.out.println("done");;
			//close connection
			server.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
