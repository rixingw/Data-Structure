
import java.util.EmptyStackException;

public class ArrayStack <T> implements StackInterface<T>  {
	private T[] array;
	private int topIndex = -1;

    /** Adds a new entry to the top of this stack.
     @param newEntry  An object to be added to the stack. */
    public void push(T newEntry){
        if (array == null){
            array = (T[])new Object[1];
            topIndex++;
            array[topIndex] = newEntry;
        }else{
            T[] copy = (T[])new Object[array.length+1];
            for (int i = 0; i < array.length; i++){
                copy[i] = array[i];
            }
            topIndex++;
            copy[topIndex] = newEntry;
            array = copy;
        }
    }
    
    /** Removes and returns this stack's top entry.
     @return  The object at the top of the stack.
     @throws  EmptyStackException if the stack is empty before the operation. */
    public T pop(){
        if (isEmpty())
            throw new EmptyStackException();
        else{
            T last = array[topIndex];
            array[topIndex] = null;
            topIndex--;
            return last;
        }
    }
    
    /** Retrieves this stack's top entry.
     @return  The object at the top of the stack.
     @throws  EmptyStackException if the stack is empty. */
    public T peek(){
        if (isEmpty())
            throw new EmptyStackException();
        else
            return array[topIndex];
    }
    
    /** Detects whether this stack is empty.
     @return  True if the stack is empty. */
    public boolean isEmpty(){
        if (topIndex == -1){
            return true;
        }else{
            return false;
        }
    }
    
    /** Removes all entries from this stack. */
    public void clear(){
        array = null;
        topIndex = -1;
    }
    
    /**
     Testing
    
    public static void main(String[] args){
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(0);
        System.out.println("0 added ");
        stack.push(1);
        System.out.println("1 added ");
        stack.push(2);
        System.out.println("2 added ");
        stack.push(3);
        System.out.println("3 added ");
        System.out.println("isEmpty " + stack.isEmpty());

        int pop = stack.pop();
        System.out.println("pop " + pop);
        pop = stack.pop();
        System.out.println("pop " + pop);
        pop = stack.pop();
        System.out.println("pop " + pop);
        pop = stack.pop();
        System.out.println("pop " + pop);
        System.out.println("isEmpty " + stack.isEmpty());
     
        stack.push(12);
        System.out.println("peek " + pop);
        int peek = stack.peek();
        System.out.println("isEmpty " + stack.isEmpty());
     
    }
    
     */
    

    
}
