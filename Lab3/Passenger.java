/**
 * Group 14:
 * Terrance Curley
 * Elvin Xu
 * Rixing Wu
 * Gregory Lee
 * 
 * Lab 3 Train Problem
 * Due February 23rd, 2016
 * 
 */
import java.util.Random;

public class Passenger{

    private int destination;

    public Passenger(int currentStation){
    	int testDestination = getStation();
    	while (testDestination == currentStation){
    		testDestination = getStation(); 
    	}
    	destination = testDestination;
    }

    /**
     * randomly generates the station that the passenger will appear at.
     * @return the initial station of the passenger.
     */
    private int getStation(){
    	Random randomGenerator = new Random();
    	return randomGenerator.nextInt(5);
    }

    /**
     * @return the passenger's destination.
     */
    public int getDestination(){
        return destination * 5;
    }
}