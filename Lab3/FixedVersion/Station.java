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


	public Passenger sendNextPassenger(){
		return line.dequeue();
	}

	public boolean hasNext(){
		return !line.isEmpty();
	}

	public int getPosition(){
        return position;
    }

    
	 private void findPassengers(){
		Random r = new Random();
		int num = r.nextInt(2);
		for (int i = 0; i < num; i++){
			Passenger newPassenger = new Passenger(position);
			line.enqueue(newPassenger);
		}
	}

}
