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
	
        /**
         * If a passenger has the trains current position as their destination,
         * they get off of the train here.
         */
	public void  unloadPassengers(){
		Iterator<Passenger> i = passengers.iterator();
			while (i.hasNext()) {
		 	  Passenger p = i.next();
		   		if (p.getDestination() == position){
				   i.remove();
			}
		}

	}

        /**
         * @return number of seats remaining on the train.
         */
	public int getRemainingSeats(){
		return capacity - passengers.size();
	}

        /**
         * @return number of passengers currently on the train.
         */
	public int getCurrentSize(){
		return passengers.size();
	}
	
        /**
         * Adds a passenger to the train.
         * @param p 
         */
	public void boardPassenger(Passenger p){
		passengers.add(p);
	}

        /**
         * Handles the events that happen every tick.
         * @param routeEvent 
         */
	public void simulateTimePassed(RouteEvent routeEvent){
		if (position % 5 == 0){
			//	
			TrainRoute tr = (TrainRoute)routeEvent.getSource();
			tr.handleStationEvents(position);
		}
			moveTrain();
	}
	/**
         * @return position of the train.
         */
	public int getCurrentPosition(){
		return position;
	}

        /**
         * Changes the train's position based on direction.
         */
	private void moveTrain(){
		if (position == 0)
			directionVector = 1;	
		else if (position == 20)
			directionVector = -1;
	
	position += directionVector;

	}	
}
