import java.awt.*;
import javax.swing.*; 
import java.awt.event.*;

/**
 * fix: parenthesis, backspace, division by 0 
 * @author leeg3
 */ 

public class CalcGui extends JFrame implements ActionListener  
{ 
	private static final long serialVersionUID = 1L; 
	  
	private static final int width = 400;  
	private static final int height = 400;     
	
	private JButton[] button = new JButton[20]; 
	private String[] buttonString = {"C", "<", "Q", "/",
							 		 "7", "8", "9", "*",  
							 		 "4", "5", "6", "-",
							 		 "1", "2", "3", "+",    
							 		 "0", "(", ")", "="}; 
	
	private Dimension displayDimension = new Dimension(300, 15);   
	private Dimension buttonDimension = new Dimension(75, 75);
	
	private int[] temp = {0, 0};
	private boolean[] function = new boolean[4];
	private static boolean aResult;
	
	private JTextArea display = new JTextArea(1, 15);   
	 
	public CalcGui() 
	{  
		super("CalcGui"); 
		setSize(width, height); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLayout(new GridLayout(6,4));
		
		JPanel[] row = new JPanel[6];
		
		//initializes all function elements to false
		for(int i = 0; i < 4; i++)
		{ 
			function[i] = false;
		}
		
		//initializes each row element as a new JPanel
		for (int i = 0; i < 6; i++) 
		{ 
			row[i] = new JPanel(); 
		}
		
		row[0].setLayout(new FlowLayout(FlowLayout.CENTER));
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER,1,1);
		
		//sets the layout for each row to a flow layout 
		for (int i = 1; i < 6; i++)
		{
			row[i].setLayout(layout); 
		} 
		
		//initializes all button elements as a JButton and sets the text to the corresponding element in buttonString
		for(int i = 0; i < 20; i++) 
		{
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].addActionListener(this);
		}
		
		//sets orientation and size of the output display 
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		display.setPreferredSize(displayDimension); //(300,15)
		
		//sets button size to the size buttonDimension (75, 75) 
		for(int i = 0; i < 20; i++)
		{
			button[i].setPreferredSize(buttonDimension);
		}
		
		//adds display to row 0 
		row[0].add(display);   
		add(row[0]); 
		
		
		//adds C, <, Q, / buttons to row 1
		for(int i = 0; i < 4; i++)
		{  
			row[1].add(button[i]); 
		}
		add(row[1]);
		
		//adds 7, 8, 9, * buttons to row 2 
		for(int i = 4; i < 8; i++)
		{
			row[2].add(button[i]);
		}
		add(row[2]);
		
		//adds 4, 5, 6, - buttons to row 3
		for(int i = 8; i < 12; i++)
		{
		    row[3].add(button[i]);
		}
		add(row[3]);
		
		//adds 1, 2, 3, + buttons to row 4 
		for(int i = 12; i < 16; i++)
		{
			row[4].add(button[i]);
		}
		add(row[4]);
		
		//adds 0, (, ), = buttons to row 5
		for (int i = 16; i < 20; i++)
		{ 
			row[5].add(button[i]); 
		}
		add(row[5]); 
	
	}

	public void quit()
	{
		try 
		{
			display.setText(""); 
			for (int i = 0; i < 4; i++) 
			{
				function[i] = false; 
			}
			for (int i = 0; i < 2; i++)
			{
				temp[i] = 0; 
			}
		} 
		catch (NullPointerException e)
		{
			display.setText("Error clearing calculator");
		}
	}
	
	public void backspace()
	{
		try
		{
			
		}
		catch(NullPointerException e)
		{
			
		}
	}
	
	public void getResult()
	{
		int result = 0; 
		temp[1] = Integer.parseInt(display.getText()); 
	
		try 
		{
			if(function[2] == true) 
			{
				result = temp[0] * temp[1];
			}
			else if(function[3] == true) 
			{
				//prevent division by 0 //doesnt work 
				if (temp[1] != 0)
				{
					result = temp[0] / temp[1];
				}
				else 
				{ 
					display.setText("Cannot divide by zero");
				}
			}
			else if(function[0] == true)
			{
				result = temp[0] + temp[1];
			}
			else if(function[1] == true)
			{
				result = temp[0] - temp[1];
			}
			
			display.setText(Integer.toString(result));
			 
			for(int i = 0; i < 4; i++)
			{
				function[i] = false;
			}
			
		}  
		catch(NumberFormatException e) 
		{
			display.setText("Error calculating result");  
		}
		
	}
	
	
	public void actionPerformed(ActionEvent e)   
	{
		//clear
		if(e.getSource() == button[0])
		{
			display.setText("");
		}
		
		//backspace
		if(e.getSource() == button[1])
		{
			backspace(); 
		}
		
		//quit
		if(e.getSource() == button[2])
		{ 
			quit(); 
		}
		 
		///
		if(e.getSource() == button[3])  
		{
			//divide function[3]
			temp[0] = Integer.parseInt(display.getText());
			function[3] = true;
			display.setText(""); 
		}
		
		//7
		if(e.getSource() == button[4]) 
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("7");
				aResult = false; 
			}
			else 
			{
				display.append("7");
			} 
		}
		
		//8
		if(e.getSource() == button[5])
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("8");
				aResult = false; 
			}
			else 
			{
				display.append("8");
			}
		}
		
		//9
		if(e.getSource() == button[6])
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("9");
				aResult = false; 
			}
			else 
			{
				display.append("9");
			}
		}
		
		//*
		if(e.getSource() == button[7]) 
		{
			//multiply function[2]
			temp[0] = Integer.parseInt(display.getText());
			function[2] = true;
			display.setText("");
		}
		
		//4
		if(e.getSource() == button[8])
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("4");
				aResult = false; 
			}
			else 
			{
				display.append("4");
			}
		} 
		
		//5
		if(e.getSource() == button[9])
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("5");
				aResult = false; 
			}
			else 
			{
				display.append("5");
			}
		}
		
		//6
		if(e.getSource() == button[10])
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("6");
				aResult = false; 
			}
			else 
			{
				display.append("6");
			}
		}
		
		//-
		if(e.getSource() == button[11]) 
		{
			//subtract function[1]
			temp[0] = Integer.parseInt(display.getText());
			function[1] = true;
			display.setText("");
		}
		
		//1
		if(e.getSource() == button[12])
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("1");
				aResult = false; 
			}
			else 
			{
				display.append("1");
			}
		}
		
		//2
		if(e.getSource() == button[13]) 
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("2");
				aResult = false; 				
			}
			else 
			{
				display.append("2");
			} 	
		}
		
		//3
		if(e.getSource() == button[14])
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("3");
				aResult = false; 
			}
			else 
			{
				display.append("3");
			}
		}
		
		//+
		if(e.getSource() == button[15])
		{
			//add function[0]
			temp[0] = Integer.parseInt(display.getText());
			function[0] = true;
			display.setText("");
		}
		
		//0
		if(e.getSource() == button[16])
		{
			if (aResult == true)
			{
				display.setText("");
				display.append("0");
				aResult = false; 
			}
			else 
			{
				display.append("0");
			}
		}
		 
		//(
		if(e.getSource() == button[17])
		{
			display.append("(");
		}
		
		//)
		if(e.getSource() == button[18])
		{
			display.append(")");
		}
		
		//=
		if (e.getSource() == button[19])
		{
			getResult(); 
			aResult = true; 
		}  
		
	} 
	
	public static void main(String [] args)
	{
		CalcGui calc = new CalcGui(); 
		calc.setVisible(true);
	}

}