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
 * The following class is an implementation of the Queue ADT using a circular array.
 * This implementation was creating using the help of Frank Carrano's textbook
 * Data Structures and Abstractions with Java, 4th ed. Chapter 11.
 */
import java.util.ArrayList;
import java.lang.StringBuilder;

public class QueueOfPassengers<T> implements QueueInterface<T>{

    private T[] queue; 
    
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    
    public QueueOfPassengers(){
        this(DEFAULT_CAPACITY);
    }
    
    public QueueOfPassengers(int initialCapacity){
        checkCapacity(initialCapacity);
        
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
        initialized = true;
    }
    
    /** Adds a new entry to the back of this queue.
      @param newEntry  An object to be added. */
    public void enqueue(T newEntry){
        checkInitialization();
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    }
  
    /** Removes and returns the entry at the front of this queue.
      @return  The object at the front of the queue. 
      @throws  EmptyQueueException if the queue is empty before the operation. */
    public T dequeue(){
        checkInitialization();
        if (isEmpty())
            throw new EmptyQueueException();
        else{
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return front;
        }

    }
  
    /**  Retrieves the entry at the front of this queue.
      @return  The object at the front of the queue.
      @throws  EmptyQueueException if the queue is empty. */
    public T getFront(){
        checkInitialization();
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return queue[frontIndex];
    }
  
     /** Detects whether this queue is empty.
      @return  True if the queue is empty, or false otherwise. */
    public boolean isEmpty(){
        return frontIndex == ((backIndex + 1) % queue.length);
    }
  
    /** Removes all entries from this queue. */
    public void clear(){
    
    }
    
    public void ensureCapacity(){
        if (frontIndex == ((backIndex + 2) % queue.length)){
            T[] oldQueue = queue;
            
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;
            
            for (int i = 0; i < oldSize; i++){
                queue[i] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }
            
            frontIndex = 0;
            backIndex = oldSize - 2;
        }
    }
    
    private void checkInitialization(){
        if (!initialized)
            throw new SecurityException ("Queue object is not initialized.");
    }
    
    private void checkCapacity(int capacity){
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException(MAX_CAPACITY + " Is the Max.");
    }


}