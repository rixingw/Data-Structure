
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

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

public class Train implements RouteListener{

	private List<Passenger> passengers = new ArrayList<>();
	private int position;
	private int directionVector = 1;
	private final int capacity = 20;


	public Train( int initialPosition){
		position = initialPosition;
	}
	
	public void  unloadPassengers(){
		Iterator<Passenger> i = passengers.iterator();
			while (i.hasNext()) {
		 	  Passenger p = i.next();
		   		if (p.getDestination() == position){
				   i.remove();
			}
		}

	}

	public int getRemainingSeats(){
		return capacity - passengers.size();
	}

	public int getCurrentSize(){
		return passengers.size();
	}
	
	public void boardPassenger(Passenger p){
		passengers.add(p);
	}


	public void simulateTimePassed(RouteEvent routeEvent){
		if (position % 5 == 0){
			//	
			TrainRoute tr = (TrainRoute)routeEvent.getSource();
			tr.handleStationEvents(position);
		}
			moveTrain();
	}
	
	public int getCurrentPosition(){
		return position;
	}

	private void moveTrain(){
		if (position == 0)
			directionVector = 1;	
		else if (position == 20)
			directionVector = -1;
	
	position += directionVector;

	}	
}
