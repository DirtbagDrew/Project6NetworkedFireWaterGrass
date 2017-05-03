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
	boolean cont;
	
	public Panel()
	{					
		
		try
		{	
			repaint();
			Socket s = new Socket("localhost", 1025);
			
			//send message to server
			out = new PrintStream(s.getOutputStream());
			System.out.println("Sending Data...");
			cont = true;
			
			while(cont)
			{
				
			}
			
			//get message from server
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.print("recieving result: ");
			System.out.println("Sum = "+in.readLine());
			
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
