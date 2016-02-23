/**
 * Group 14:
 *	Rixing Wu
 * Terrance Curley
 * Elvin Xu
 * Gregory Lee
 * 
 * Lab 3 Train Problem
 * Due February 23rd, 2016
 * 
 */
import java.util.TimerTask;
import java.util.Timer;
import java.lang.Runtime;
import java.io.IOException;
import java.util.Random;
import java.lang.StringBuilder;



/** 
	TrainRoute is a timertask object provided with run().
	Each char in stringRepresentable reflects the position of each block of the track.
 */
public class TrainRoute extends TimerTask{


	private final String stringRepresentable = "S----S----S----S----S";
	private RouteListener[] listeners;
	private RouteEvent event;

	/** 
		In the constructor: Trains and stations are created as RouteListener Object, 
   		 which store in listeners arraylist
   	*/
	public TrainRoute(){
		event = new RouteEvent(this);

		Station s1 = new Station(0);
		Station s2 = new Station(5);
		Station s3 = new Station(10);
		Station s4 = new Station(15);
		Station s5 = new Station(20);

		Train t1 = new Train(0);
		Train t2 = new Train(8);
		Train t3 = new Train(17);
		listeners =  new RouteListener[]{s1, s2, s3, s4, s5, t1, t2, t3};
	}
	
	/** run() is from the superclass, Invokes every second (see main() )
		-loops each object in listeners array and call their respected simulateTimePassed().
		- (Train and Station implement RouteListener Interface).
		- Prints a string that respresents the route system as a whole*/ 

	public void run() {
      	for (int i = 0; i < listeners.length; i++) {
			RouteListener l = listeners[i];
			l.simulateTimePassed(event);
		}
		 printRepresentable();
  	}


  	/**
  	 This method is provided to Train objects to call when appropriate. Such as when a train is arrived to a station
  		@param intersection : The postion of the station (0,5,10,15, or 20)
  		-Unload passsengers from the respected train
  		-Board passengers from the respected station
  	*/
	public void handleStationEvents(int intersection){
		Train train = null;
		Station station = null;

		for (int i = 0; i < listeners.length; i++) {
				RouteListener lner = listeners[i];
				if (lner instanceof Train){
					Train t = (Train)lner;
					if (t.getCurrentPosition() == intersection)
						train = t;
				}

				if (lner instanceof Station){
					Station s = (Station)lner;
					if (s.getPosition() == intersection)
						station = s;
				}
				
		}

		train.unloadPassengers();
		int remain = train.getRemainingSeats();
		for (int i = 0; i < remain; i++){
			if (station.hasNext()){
				Passenger p = station.sendNextPassenger();
				train.boardPassenger(p);
			}else{
				break;
			}
		}

	}
	
	/** 
	Method prints the Route System
	 dash(-) = empty block
	 S = Station
	 R = Red Train
	 G = Green Train
	 O = Orange Train 
	 */
	private void printRepresentable(){   

   		Train redline = (Train)listeners[5];
   		Train greenline = (Train)listeners[6];
   		Train orangeLine = (Train)listeners[7];


		StringBuilder rail = new StringBuilder(stringRepresentable);

		rail.setCharAt(redline.getCurrentPosition(), 'R');
		rail.setCharAt(greenline.getCurrentPosition(), 'G');
		rail.setCharAt(orangeLine.getCurrentPosition(), 'O');

		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); 
		

		System.out.println("RED LINE    : " + redline.getCurrentSize());
   		System.out.println("GREEN LINE  : " + greenline.getCurrentSize());
   		System.out.println("ORANGE LINE : " + orangeLine.getCurrentSize());
		System.out.println(rail);
	}



	/**
		Main() create trainroute
		- Run every x seconds
	*/
    public static void main(String[] args){
			TrainRoute trainRoute = new TrainRoute();
			Timer timer = new Timer();
			timer.schedule(trainRoute, 0, 1000);

	}

}
