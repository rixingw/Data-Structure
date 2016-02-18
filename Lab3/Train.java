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

/**
 * Group 14: 
 * Terrance Curley 
 * Elvin Xu
 * Rixing Wu  
 * Gregory Lee
 * 
 * Lab 3 Train Problem
 * Due February 16th, 2016
 */
public class Train implements RouteListener 
{
	private static String direction; 
	private int seatsRemaining; 
	private int currentStation; 
	QueueOfPassengers<Passenger> trainQueue = new QueueOfPassengers<>(20); 
	QueueOfPassengers<Passenger> exitQueue = new QueueOfPassengers<>(20); 
	QueueOfPassengers<Passenger> tempQueue = new QueueOfPassengers<>(20); 
	
	public Train(String startingDirection, int startingStation)
	{
		direction = startingDirection; 
		seatsRemaining = 20; 
		currentStation = startingStation; 
	}
	
	/*
	 * returns the current station
	 */
	public int getCurrentStation()
	{
		return currentStation;  
	}
	
	/*
	 * passengers disembark and then new passengers board train
	 */
	public void disembarkAndBoard(QueueOfPassengers<Passenger> queue)
	{
		//adds as many people to train if the train is empty
		if (seatsRemaining == 20)
		{
			for (int i = 0; i < 20; i++) 
			{
				trainQueue.enqueue(queue.getFront()); 
			}
			seatsRemaining = 0; 
		}
		
		//remove people whose destination = current station
		int i; 
		for (i = 0; i > seatsRemaining; i++)
		{
			if (currentStation == trainQueue.getFront().getDestination())
			{
				exitQueue.enqueue(trainQueue.getFront()); 
				trainQueue.dequeue(); 
			} 
			else //moves passengers whose destination != to the current station into a temp queue to allow for an iteration through the queue 
			{
				tempQueue.enqueue(trainQueue.getFront());
				trainQueue.dequeue(); 
			}
			seatsRemaining += i; 

		}	
		while(!tempQueue.isEmpty()) //restores people in tempQueue back into the main queue
		{
			trainQueue.enqueue(tempQueue.getFront());
			tempQueue.dequeue(); 
		}
		exitQueue.clear(); //passengers leave the train 
		
		//adds passengers to the train
		for (int j = 0; j < seatsRemaining; j++)
		{
			trainQueue.enqueue(queue.getFront());
		}
	
	}
	
	/* 
	 * returns the passenger destination  
	 *
	public int getPassengerDestination()
	{
		return trainQueue.getFront().getDestination();  
	}
	
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

	/*
	 * implmenet route listener (in interface method)
	 * - move method
	 * - check boundary
	 * add simulate time past, use things in train route
	 */  
	public void simulateTimePassed(RouteEvent routeEvent) 
	{
		// TODO Auto-generated method stub
		
	}

}