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
			
			//initialize the score
			int tieCount=0;
			int lossCount=0;
			int winCount=0;
			
			//initialize out and in stream
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintStream out = new PrintStream(s.getOutputStream());
			
			//initialize the user input to 0
			int userMove=0;
			
			//initialize the user pattern to be stored
			String userPattern="     ";
			
			//loops until the client chooses the "quit button"
			while(userMove!=4)
			{
				//computer makes prediction based on previous pattern
				int prediction=cpu.makePrediction(userPattern);
				
				//reads the first input from the client, the users move.
				//1=fire; 2=water; 3=grass, 4=quit
				userMove = Integer.parseInt(in.readLine());
				
				//sends over what the computer chose to the client
				switch(prediction)
				{
				case 1://computer chose fire
					out.println("Computer chose fire");
					break;
				case 2://computer chose water
					out.println("Computer chose water");
					break;
				case 3://computer chose grass
					out.println("Computer chose grass");
					break;
				default:
					break;
				}
				
				//user chooses "quit button" ends the while loop to end the game
				if(userMove==4)
				{
					break;
				}
				
				//sends the decicion of the round and the score based off the user move and the computer's move
				switch(userMove)
				{
				case 1://user chose fire
					if(prediction==1)//user chooses fire; computer chooses fire = tie
					{
						out.println("Its a tie");
						
						//updates score with tie
						tieCount++;
						out.println("wins: "+ winCount +" ties: "+tieCount+" losses: "+lossCount);
					}
					else if(prediction == 2)//user chooses fire; computer chooses water = loss
					{
						out.println("you lost");
						
						//updates score with loss
						lossCount++;
						out.println("wins: "+ winCount +" ties: "+tieCount+" losses: "+lossCount);
					}
					else if(prediction ==3)//user chooses fire; computer chooses water = win
					{
						out.println("you won");
						
						//updates score with win
						winCount++;
						out.println("wins: "+ winCount +" ties: "+tieCount+" losses: "+lossCount);
					}
					
					//stores the new pattern to the computer hashmap
					userPattern=userPattern.substring(1, userPattern.length())+"F";
					cpu.storePattern(userPattern);
					break;
				case 2://user chooses water
					if(prediction==1)//user chooses water; computer chooses fire = win
					{
						out.println("you won");
						
						//updates score with win
						winCount++;
						out.println("wins: "+ winCount +" ties: "+tieCount+" losses: "+lossCount);
					}
					else if(prediction==2)//user chooses water; computer chooses water = tie 
					{
						out.println("Its a tie");
						
						//updates score with tie
						tieCount++;
						out.println("wins: "+ winCount +" ties: "+tieCount+" losses: "+lossCount);
						
					}
					else if(prediction==3)//user chooses water; computer chooses grass = loss
					{
						out.println("you lost");
						
						//update score with loss
						lossCount++;
						out.println("wins: "+ winCount +" ties: "+tieCount+" losses: "+lossCount);
					}
					
					//stores the new pattern to the computer hashmap
					userPattern=userPattern.substring(1, userPattern.length())+"W";
					cpu.storePattern(userPattern);
					break;
				case 3://user chooses grass
					if(prediction==1)//user chooses grass; computer chooses fire = loss
					{
						out.println("you lost");
						
						//updates score with loss
						lossCount++;
						out.println("wins: "+ winCount +" ties: "+tieCount+" losses: "+lossCount);
					}
					else if(prediction==2)//user chooses grass; computer chooses water = win
					{
						out.println("you won");
						
						//update score with win
						winCount++;
						out.println("wins: "+ winCount +" ties: "+tieCount+" losses: "+lossCount);
					}
					else if(prediction==3)//user chooses grass; computer chooses grass = tie
					{
						out.println("Its a tie");
						
						//update score with tie
						tieCount++;
						out.println("wins: "+ winCount +" ties: "+tieCount+" losses: "+lossCount);
					}
					
					//stores the new pattern to the computer hashmap
					userPattern=userPattern.substring(1, userPattern.length())+"G";
					cpu.storePattern(userPattern);
					break;
				}

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
