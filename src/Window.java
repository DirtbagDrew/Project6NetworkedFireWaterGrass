import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * creates the window and the main program
 * @author Andrew
 *
 */
public class Window extends JFrame implements ActionListener
{
	/**
	 * prints out to the server
	 */
	PrintStream out;
	
	/**
	 * reads text information from the serve
	 */
	BufferedReader in;
	
	/**
	 * ends while loop to stop the process
	 */
	Boolean cont;
	
	/**
	 * displays the move that you choose
	 */
	JLabel move;
	
	/**
	 * displays the outcome of the round
	 */
	//JLabel decision;
	
	/**
	 * displays the move the computer decided on
	 */
	JLabel oppDec;
	
	/**
	 * displays the outcome of the round
	 */
	JLabel message;
	
	/**
	 * displays the score
	 */
	JLabel score;
	
	/**
	 * takes in what the computer picked
	 */
	String input1=null;
	
	/**
	 * takes in what the outcome of the round was
	 */
	String input2=null;
	
	/**
	 * takes in the score
	 */
	String input3=null;
	
	/**
	 * socket connection to the server
	 */
	Socket s;
	
	/**
	 * fire symbol button
	 */
	ImageIcon fire;
	
	/**
	 * water symbol button
	 */
	ImageIcon water;
	
	/**
	 * grass symbol button
	 */
	ImageIcon grass;
	
	/**
	 * fire symbol button
	 */
	JButton fireButton;
	
	/**
	 * water symbol button
	 */
	JButton waterButton;
	
	/**
	 * grass symbol button
	 */
	JButton grassButton;
	
	/**
	 * main program for the client, just initializes the window object
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new Window();
	}
	
	/**
	 * creates the window constructor
	 */
	public Window()
	{
		setBounds(100,100,820,620);//x,y,w,h of window
		setTitle("Pick Your Move: Fire, Grass, Water");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		//Panel p = new Panel();
		//setContentPane(p);
		
		//sets the background color
		setBackground(Color.BLUE);
		
		//creates pokemon picture
		ImageIcon poke= new ImageIcon("pokemon.jpg");
		JLabel pokeLabel=new JLabel(poke);
		add(pokeLabel);
		
		//sets the water button
		water = new ImageIcon("water.png");
		waterButton = new JButton(water);
		waterButton.addActionListener(this);
		add(waterButton);
		
		//sets the grass button
		grass = new ImageIcon("grass.png");
		grassButton = new JButton(grass);
		grassButton.addActionListener(this);
		add(grassButton);
		
		//sets the fire button
		fire = new ImageIcon("fire.png");
		fireButton = new JButton(fire);
		fireButton.addActionListener(this);
		add(fireButton);
		
		//sets the quit button
		JButton quitButton = new JButton("quit");
		quitButton.setPreferredSize(new Dimension(180,155));
		quitButton.addActionListener(this);
		add(quitButton);
		
		//initialize move JLabel to say "your move" for default
		move=new JLabel("your move");
		move.setPreferredSize(new Dimension(150,100));
		add(move);
		
		//initialize oppDec JLabel to say "opponent" for default
		oppDec=new JLabel("opponent");
		oppDec.setPreferredSize(new Dimension(200,100));
		add(oppDec);
		
		//initialize message JLabel to say "message" for default
		message = new JLabel("message");
		message.setPreferredSize(new Dimension(150,100));
		add(message);
		
		//initialize score JLabel to say "score:" for default
		score=new JLabel("score:");
		score.setPreferredSize(new Dimension(250,100));
		add(score);

		//sets all the components visible
		setVisible(true);
		
		playSoundtrack("battle.wav");
		
		//try/catch loop to handle the server socket connection
		try
		{	
			s = new Socket("localhost", 1025);
			
			boolean done = false;
			while(!done)
			{
				//endless loop until the quit button is pressed	
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try
		{		
			//creates the bufferedReader and PrintStream to send and recieve data from the server socket
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintStream(s.getOutputStream());
			
			//if the fire button is pressed
			if(e.getSource()==fireButton)
			{
				play("charizard.wav");
				
				//send option 1 to server
				out.println(1);
				
				//read the response from the server
				input1=in.readLine();
				input2=in.readLine();
				input3=in.readLine();
				
				//sets the response to the display
				move.setText("You picked fire");
				oppDec.setText(input1);
				message.setText(input2);
				score.setText(input3);
			}
			
			//if the fire button is pressed
			if(e.getSource()==waterButton)
			{
				play("blastoise.wav");
				
				//send option 2 to server
				out.println(2);
				
				//reads the response from the server
				input1=in.readLine();
				input2=in.readLine();
				input3=in.readLine();
				
				//sets response to display
				move.setText("You picked water");
				oppDec.setText(input1);
				message.setText(input2);
				score.setText(input3);
			}
			
			//if the grass button is pressed
			if(e.getSource()==grassButton)
			{
				play("venusaur.wav");
				
				//send option 3 to the server
				out.println(3);
				
				//reads the response from the server
				input1=in.readLine();
				input2=in.readLine();
				input3=in.readLine();
				
				//sets server response to display
				move.setText("You picked grass");
				oppDec.setText(input1);
				message.setText(input2);
				score.setText(input3);					
			}
			
			//if the quit button is pressed
			if(e.getActionCommand()=="quit")
			{
				//close server connection and exit out of the program
				out.println(4);
				s.close();
				System.exit(0);
			}
				
		}
		catch(IOException f)
		{
			f.printStackTrace();
		}
	}
	/**
	 * plays sounds for the buttons
	 * @param filename the name of the file to be played
	 */
	public static void play(String filename)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();			
		}
		catch(LineUnavailableException e)
		{
			System.out.println("Audio Error");
		}
		catch(IOException e)
		{
			System.out.println("file not found");
		}
		catch(UnsupportedAudioFileException e)
		{
			System.out.println("wrong file type");
		}
	}
	
	public static void playSoundtrack(String filename)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			
			FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(-10);
			
			clip.start();	
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(LineUnavailableException e)
		{
			System.out.println("Audio Error");
		}
		catch(IOException e)
		{
			System.out.println("file not found");
		}
		catch(UnsupportedAudioFileException e)
		{
			System.out.println("wrong file type");
		}
	}
}