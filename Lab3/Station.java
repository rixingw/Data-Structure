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
public class Station implements RouteListener{


    private QueueOfPassengers<Passenger> line;
    private int position;
    private Random passengerGenerator;

    public Station(int inputNumber){
        line = new QueueOfPassengers<Passenger>(20);
	position = inputNumber;
	passengerGenerator = new Random();
    }

    public void simulateTimePassed(RouteEvent routeEvent){
	findPassengers();
    }

    /**
     * retrieves the next passenger.
     * @return next passenger in line by calling dequeue
     */
    public Passenger sendNextPassenger(){
	return line.dequeue();
    }
    
   /**
    * checks if the queue of the station is empty.
    * @return true of line is empty, false otherwise
    */
    public boolean hasNext(){
	return !line.isEmpty();
    }
        
   /**
    * @return position of the station (int value).
    */
    public int getPosition(){
        return position;
    }

   /**
    * method used to randomly generate new passengers.
    * called in simulateTimePassed().
    */
    private void findPassengers(){
        Random r = new Random();
        int num = r.nextInt(2);
        for (int i = 0; i < num; i++){
	Passenger newPassenger = new Passenger(position);
	line.enqueue(newPassenger);
	}
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> e38d12d726920cb3e888eedb919b4487b2beb0df
