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
	//Set destination to (0 - 4) but not currentStaion
    //note a single Random object is reused here
    	
    	int testDestination = getStation();
    	while (testDestination == currentStation){
    		getStation();
    	}
    	
    	getDestination();
    	
    	
    }
    
    public int getStation(){
    	Random randomGenerator = new Random();
    	destination = randomGenerator.nextInt(5);
		return destination;
    }


	public int getDestination(){
        return destination * 5;
    }

}

/*
Random randomGenerator = new Random();
for (int i = 0; i <= 100; i++){
  destination = randomGenerator.nextInt(4);
  while (destination != currentStation){
	  System.out.println("Passenger " + i + " 's destination goes to " + destination);
      getDestination();
  } */
  
  /*
  if (destination != currentStation){
	  System.out.println("Passenger " + i + " 's destination goes to " + destination);
      getDestination();
  }
  else
	  destination = randomGenerator.nextInt(4);*/ 	
