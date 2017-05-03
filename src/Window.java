import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
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
	PrintStream out;
	BufferedReader in;
	Boolean cont;
	JLabel move;
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
		setBounds(100,100,700,700);//x,y,w,h of window
		setTitle("Drawing Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//Panel p = new Panel();
		//setContentPane(p);
		
		//sets the background color
				setBackground(Color.BLUE);
				move=new JLabel("your move");
				gbc.fill=GridBagConstraints.HORIZONTAL;
				gbc.gridx=0;
				gbc.gridy=0;
				gbc.weightx=0;
				add(move,gbc);
				
				JLabel decision=new JLabel("decision");
				gbc.fill=GridBagConstraints.HORIZONTAL;
				gbc.gridx=1;
				gbc.gridy=0;
				gbc.weightx=0;
				add(decision,gbc);
				
				JLabel oppDec=new JLabel("opponent");
				gbc.fill=GridBagConstraints.HORIZONTAL;
				gbc.gridx=2;
				gbc.gridy=0;
				gbc.weightx=0;
				add(oppDec,gbc);
				
				//sets the fire button
				JButton fireButton = new JButton("fire");
				fireButton.addActionListener(this);
				gbc.fill=GridBagConstraints.HORIZONTAL;
				gbc.gridx=0;
				gbc.gridy=1;
				gbc.weightx=0;
				gbc.weighty=0;
				add(fireButton,gbc);
				
				//sets the water button
				JButton waterButton = new JButton("water");
				waterButton.addActionListener(this);
				gbc.fill=GridBagConstraints.HORIZONTAL;
				gbc.gridx=1;
				gbc.gridy=1;
				gbc.weightx=0;
				add(waterButton,gbc);
				
				//sets the grass button
				JButton grassButton = new JButton("grass");
				grassButton.addActionListener(this);
				gbc.fill=GridBagConstraints.HORIZONTAL;
				gbc.gridx=2;
				gbc.gridy=1;
				gbc.weightx=0;
				add(grassButton,gbc);
				
				//sets the quit button
				JButton quitButton = new JButton("quit");
				quitButton.getPreferredSize();
				quitButton.addActionListener(this);
				gbc.fill=GridBagConstraints.HORIZONTAL;
				gbc.gridx=3;
				gbc.gridy=1;
				gbc.weightx=0;
				add(quitButton,gbc);
		setVisible(true);
		try
		{	
			Scanner input = new Scanner(System.in);
			Socket s = new Socket("localhost", 1025);
			
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintStream(s.getOutputStream());
			
			boolean done = false;
			while(!done)
			{
				System.out.println("1. Fire");
				System.out.println("2. Water");
				System.out.println("3. Grass");
				System.out.println("4. Quit");
				
				int option = CheckInput.checkIntRange(1, 4);
				switch(option)
				{
				case 1:
					out.println(1);
					break;
				case 2:
					out.println(2);
					break;
				case 3:
					out.println(3);
					break;
				case 4:
					out.println(4);
					done=true;
					break;
				}
			}
			
			//close connection
			s.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand()=="fire")
		{
			out.println(1);
			move.setText("fire");
		}
		if(e.getActionCommand()=="water")
		{
			out.println(2);
		}
		if(e.getActionCommand()=="grass")
		{
			out.println(3);
		}
		if(e.getActionCommand()=="quit")
		{
			out.println(4);
		}
		
	}
}