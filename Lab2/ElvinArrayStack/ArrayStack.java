/**
 * Group 16:
 * Terrance Curley
 * Elvin Xu
 * Rixing Wu
 * Gregory Lee
 * 
 * Lab 2 Stack ADT
 * Due February 2nd, 2016
 * 
 * The following class is an implementation of the Stack Abstract Data type.
 * 
 */

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack <T> implements StackInterface<T>  {
	//initialize the stack, the top, the default size of the array
	private T[] Stack;
	private int topIndex = -1;
	private final static int DEFAULT_SIZE = 200;
	private static final int MAX_CAPACITY = 10000;
	
	// Constructor creates the array.
	public ArrayStack(){
		T[] Stack = (T[])new Object[DEFAULT_SIZE];
	}

	/**
         * Ensures that the array has capacity, then
         * pushes the newEntry onto the stack, and increments topIndex by 1
         * @param newEntry : item being pushed onto the stack.
         */
	public void push (T newEntry) {
		
		ensureCapacity();
		Stack[topIndex + 1] = newEntry;
		topIndex++;
		}

	

	/**
         * Check if the stack is empty, then
	 * Gets the topIndex, and decrements it.
         * @return The topmost entry, an object of type T.
         */
	public T pop() {
		if(isEmpty())
			throw new EmptyStackException();

                T returnVar = Stack[topIndex];
		topIndex--;
		return returnVar;
	}

        /**
         * Retrieves top piece of data without removing it from the stack.
         * @return array entry at the top index.
         */
	public T peek() {
		if (isEmpty())
	       throw new EmptyStackException();
	    else
	       return Stack[topIndex];
	}

	/**
         * Checks if the Stack is empty.
         * @return True if topIndex is -1 (Stack is empty), false otherwise.
         */
	public boolean isEmpty() {
		if (topIndex == -1){
			return true;
		}
		else
			return false;
		
	}
	
	/**
         * The following method is sourced from Chapter 6 of "Data Structures and
         * Abstractions in Java" by Frank Carrano. Used to avoid stack overflow
         * by resizing the array if the stack has reached capacity.
         */
	private void ensureCapacity()
	 {
	    if (topIndex == Stack.length - 1) // If array is full, double its size
	    {
	       int newLength = 2 * Stack.length;
	       checkCapacity(newLength);
	       Stack = Arrays.copyOf(Stack, newLength);
	    }
	 }
	
	
	/**
         * The following method is sourced from Chapter 6 of "Data Structures and
         * Abstractions in Java" by Frank Carrano. Used for ensureCapacity method.
         * @param capacity 
         */
	private void checkCapacity(int capacity)
	 {
	    if (capacity > MAX_CAPACITY)
	    throw new IllegalStateException("Attempt to create a bag whose " + 
	                                    "capacity exeeds allowed " + 
	                                    "maximum of " + MAX_CAPACITY);
	 }

	/**
         * Clears the stack by setting array to null.
         */
	public void clear() {
		Stack = null;
	}
}
