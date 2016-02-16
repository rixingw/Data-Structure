<<<<<<< HEAD
/* train
 * - currentStation
 * - passengerBoard
 * - pasengerRemove
 * - interval
 * = direction traveled 
 * = capacity 
 * =array of passengers
 * =function that checks passenger destination
 * = check to see if terminal then turn train around 
 * list for array of passengers 
 */

public class Train
{
	private static String direction; 
	private int capacity; 
	Passenger[] dood = new Passenger[20];
	
	public Train()
	{
		direction = "east";
		capacity = 20; 
		for (int i = 0; i < 20; i++)
		{
			dood[i] = new Passenger(0); 
		}
	}
	/*
	 * returns the current station
	 */
	public String currentStation()
	{
		return ""; 
	}
	
	/*
	 * passengers board train
	 */
	public void board()
	{
	
	}
	
	/*
	 * removes passengers from the train 
	 */
	public void disembark()
	{
		
	}
	
	/*
	 * interval between stations 5 seconds
	 */
	public int interval()
	{
		return 0; 
	}
	
	/* 
	 * returns the passenger destination 
	 */
	public String getPassengerDestination()
	{
		return "";   
	}
=======
/**
 * Group 14:
 * Terrance Curley
 * Elvin Xu
 * Rixing Wu
 * Gregory Lee
 * 
 * Lab 3 Train Problem
 * Due February 16th, 2016
 * 
 */

public class Train{
>>>>>>> 1870e94e3d6b44a1ca4cb2d0187b900c148cd435
	
	/*
	 * turns the train around 
	 */
	public String turnAround()
	{
		if (direction.equals("east"))
		{
			direction = "west"; 
		}
		else
		{
			direction = "east"; 
		}
		return direction; 
	}

}