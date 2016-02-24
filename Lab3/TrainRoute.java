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
import java.util.TimerTask;
import java.util.Timer;
import java.lang.Runtime;
import java.io.IOException;
import java.util.Random;
import java.lang.StringBuilder;



public class TrainRoute extends TimerTask{

	private final String stringRepresentable = "S----S----S----S----S";
	private RouteListener[] listeners;
	private RouteEvent event;

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
	

	public void run() {
      	for (int i = 0; i < listeners.length; i++) {
			RouteListener l = listeners[i];
			l.simulateTimePassed(event);
		}
		 printRepresentable();
  	}


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
	
	private void printRepresentable(){   

 
   		Train redline = (Train)listeners[5];
   		Train greenline = (Train)listeners[6];
   		Train orangeLine = (Train)listeners[7];


		StringBuilder rail = new StringBuilder(stringRepresentable);

		rail.setCharAt(redline.getCurrentPosition(), 'T');
		rail.setCharAt(greenline.getCurrentPosition(), 'T');
		rail.setCharAt(orangeLine.getCurrentPosition(), 'T');

		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); 
		

		System.out.println("RED LINE    : " + redline.getCurrentSize());
   		System.out.println("GREEN LINE  : " + greenline.getCurrentSize());
   		System.out.println("ORANGE LINE : " + orangeLine.getCurrentSize());
		System.out.println(rail);
	}

    public static void main(String[] args){
			TrainRoute trainRoute = new TrainRoute();
			Timer timer = new Timer();
			timer.schedule(trainRoute, 0, 1000);

	}

}