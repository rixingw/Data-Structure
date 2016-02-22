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
	private static int currentStation; 
	private static QueueOfPassengers<Passenger> trainQueue = new QueueOfPassengers<>(20); 
	private static QueueOfPassengers<Passenger> exitQueue = new QueueOfPassengers<>(20); 
	private static QueueOfPassengers<Passenger> tempQueue = new QueueOfPassengers<>(20); 
	
	public Train(String startingDirection, int startingStation)
	{
		direction = startingDirection; 
		seatsRemaining = 20;  
		currentStation = startingStation * 5; 
	}
	
	public void goToNextStation()
	{
		currentStation += 5; 
	}
	
	//returns current station
	public int getCurrentStation()
	{
		return currentStation;  
	}
	
	//returns current direction
	public String getDirection()
	{
		return direction; 
	}
	
	//adds people to train
	public void addToTrain(QueueOfPassengers<Passenger> queue)
	{
		//adds as many people to train if the train is empty
		if (seatsRemaining == 20)
		{
			while (!queue.isEmpty()) 
			{
				trainQueue.enqueue(queue.getFront()); 
				queue.dequeue(); 
				seatsRemaining--;
			} 
			  
		}	
		else //adds passengers to the train
		{
			for (int j = 0; j < seatsRemaining; j++)
			{
				trainQueue.enqueue(queue.getFront());  
				queue.dequeue();  
			}
		} 
		displayQueue(trainQueue); 
	}
	
	//removes people from the train 
	public void removeFromTrain()
	{
		//System.out.println("Station: " + currentStation);
		//remove people whose destination = current station to exitQueue
		while (!trainQueue.isEmpty()) 
		{ 
			if (trainQueue.getFront().getDestination() == currentStation) 
			{
				exitQueue.enqueue(trainQueue.dequeue());  
				seatsRemaining++; 
			}  
			else //moves passengers whose destination != to the current station into a temp queue 
			{ 
				tempQueue.enqueue(trainQueue.dequeue()); 
			}
		} 
		
		while(!tempQueue.isEmpty()) //restores people in tempQueue back into the main queue
		{
			trainQueue.enqueue(tempQueue.getFront());
			tempQueue.dequeue(); 
		}  
		//passengers leave the train  
		exitQueue.clear(); 
		displayQueue(trainQueue);
		tempQueue.clear(); 
	}
 
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
	
	//displays the given queue  
	public void displayQueue(QueueOfPassengers<Passenger> queue)// int capacity)
	{
		//displays elements in queue then puts queue elements into temp queue
		while(!queue.isEmpty())
		{
			System.out.print(queue.getFront().getDestination() + " ");
			tempQueue.enqueue(queue.getFront());
			queue.dequeue();  
		} 
		System.out.println();
		//puts elements from temp queue back into queue
		while(!tempQueue.isEmpty())
		{
			queue.enqueue(tempQueue.getFront()); 
			tempQueue.dequeue(); 
		}
	}

	/*
	 * implmenet route listener (in interface method)
	 * - move method
	 * - check boundary
	 * add simulate time past, use things in train route
	 */  
	public void simulateTimePassed(RouteEvent routeEvent) 
	{
		
		
	} 
	
	public static void main(String [] args)
	{ 
		Train t1 = new Train("west", 0); 
		
		QueueOfPassengers<Passenger> queue = new QueueOfPassengers<>(20);
		Passenger[] dood = new Passenger[20]; 
		
		for (int i = 0; i < 10; i++)
		{  
			dood[i] = new Passenger(2);  
		}  
		for (int i = 10; i < 20; i++) 
		{
			dood[i] = new Passenger(3); 
		}  
		 
		for (int i = 0; i < 20; i++) 
		{ 
			queue.enqueue(dood[i]); 
		} 
		
		System.out.println("Current direction is " + t1.getDirection()); 
		t1.turnAround();
		System.out.println("New direction is " + t1.getDirection() + "\n");
		
		System.out.println("The current station is " + t1.getCurrentStation());
		System.out.println("Station Queue: ");  
		t1.displayQueue(queue); 
		 
		System.out.println("add people to train"); 
		t1.addToTrain(queue); 
		
		System.out.println("remove people whos destinaton = station"); 
		t1.removeFromTrain();
		
		System.out.println("Train goes to next station\n"); 
		t1.goToNextStation(); 
		
		QueueOfPassengers<Passenger> queue2 = new QueueOfPassengers<>(20);
		Passenger[] person = new Passenger[20]; 
		for (int i = 0; i < 20; i++)  
		{
			person[i] = new Passenger(1); 
		} 
		for (int i = 0; i < 20; i++) 
		{  
			queue2.enqueue(person[i]);  
		}
		
		System.out.println("The current station is " + t1.getCurrentStation());
		System.out.println("Station Queue: "); 
		t1.displayQueue(queue2);
		
		System.out.println("add people to train");
		t1.addToTrain(queue2);
		
		System.out.println("remove people whos destination = station");
		t1.removeFromTrain();
		 
		System.out.println("Train goes to next station\n");
		t1.goToNextStation();
		
		System.out.println("The current station is " + t1.getCurrentStation());
		
		System.out.println("no one to add only remove");
		t1.removeFromTrain(); 
		
		
	}

}