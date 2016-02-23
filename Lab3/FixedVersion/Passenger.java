
/**
	class Passenger
*/

import java.util.Random;

public class Passenger{

    private int destination;

    /* Constructor
    	Set destination to (0 - 4) but not currentStaion
    */
    public Passenger(int currentStation){
    	int testDestination = getStation();
    	while (testDestination == currentStation){
    		testDestination = getStation();
    	}
    	destination = testDestination;
    }
    
    /*

    */
    private int getStation(){
    	Random randomGenerator = new Random();
    	return randomGenerator.nextInt(5);
    }
	/*

    */
    public int getDestination(){
        return destination * 5;
    }
}


