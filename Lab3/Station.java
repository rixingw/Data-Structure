/**
 * Group 14:
 * Terrance Curley
 * Elvin Xu
 * Rixing Wu
 * Gregory Lee
 * 
 * Lab 3 Train Problem
 * Due February 16th, 2016
 * 
 */


public class Station{

    public boolean isTerminal;
    private QueueOfPassengers line;
    private int stationNumber;
    
    public Station(boolean terminality, int inputNumber){
        isTerminal = terminality;
        line = new QueueOfPassengers(20);
        stationNumber = inputNumber;
    }
}
