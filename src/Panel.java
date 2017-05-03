import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.imageio.*;
import javax.swing.JButton;
import javax.swing.JPanel;
public class Panel extends JPanel implements ActionListener
{
	JButton fireButton;
	JButton waterButton;
	JButton grassButton;
	JButton quitButton;
	PrintStream out;
	BufferedReader in;
	boolean cont;
	
	public Panel()
	{					
		
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
				
				int option = input.nextInt();
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
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//sets the background color
		setBackground(Color.BLUE);
		//sets the fire button
		fireButton = new JButton("fire");
		fireButton.addActionListener(this);
		add(fireButton, BorderLayout.CENTER);
		
		//sets the water button
		waterButton = new JButton("water");
		waterButton.addActionListener(this);
		add(waterButton, BorderLayout.CENTER);
		
		//sets the grass button
		grassButton = new JButton("grass");
		grassButton.addActionListener(this);
		add(grassButton, BorderLayout.CENTER);
		
		//sets the quit button
		quitButton = new JButton("quit");
		quitButton.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand()=="fire")
		{
			out.print("fire");
		}
		if(e.getActionCommand()=="water")
		{
			out.print("water");
		}
		if(e.getActionCommand()=="grass")
		{
			out.print("grass");
		}
		if(e.getActionCommand()=="quit")
		{
			cont=false;
		}
	}
	
}
