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

    private QueueOfPassengers line;
    private int position;
    private Random passengerGenerator;
    
    public Station(int inputNumber){
        line = new QueueOfPassengers(20);
        position = inputNumber;
        passengerGenerator = new Random();
    }
    
    public void simulateTimePassed(){
        if (passengerGenerator.nextInt(4) == 0)
        {
            Passenger newGuy = new Passenger(position);
            line.enqueue(newGuy);
        }
    }
    
    public QueueOfPassengers getQueue(){
        return line;
    }
    
    public int getPosition(){
        return position;
    }
}
