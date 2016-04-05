
/**
 *  Rxing Wu
 *  Lab5
 */

import java.math.*;
import java.util.ArrayList;
import java.util.Collections;

class HashTable {
  private int capacity;
  private int entries;
  private Node[] table;

  /** Constructor
   * @param max size to use (Better performance)
   */
  public HashTable(int maxSize){
    capacity = maxSize;
    table = new Node[maxSize];
    entries = 0;
  }

  /** Constructor  intitial size = 11
   *  More memeory efficient
   */
  public HashTable(){
    this(11);
  }

  public void addAll(String... elements){
    for(String word : elements){
      add(word);
    }
  }


  /** add a word to the table or add to chain if hashCode already exists
   * @param word the text to add
   */
  public void add(String word){
    if (loadFactor() >= 1.0){
      resize();
    }
    word = word.toLowerCase();
    int hashIndex = getHashIndex(word);
    if (table[hashIndex] == null){
      Node head = new Node(word);
      table[hashIndex] = head;
      entries++;
    }else{
      Node head = table[hashIndex];
      Node temp = head;
      while(temp != null){
        if (temp.getKey().equals(word)){
          temp.addCount();
          return;
        }else{
          if (temp.getNext() == null){
            Node next = new Node(word);
            temp.setNext(next);
            entries++;
            return;
          }else{
            temp = temp.getNext();
            continue;
          }
        }
      }
  }

}


  /** Find the next prime number after (the numeric value) capacity
   *@return the next prime
   */
 private int nextProbablePrime(int num){
   BigInteger currentSize = new BigInteger(Integer.toString(num));
   BigInteger nxPrime = currentSize.nextProbablePrime();
   return nxPrime.intValue();
 }

 /** Generate a integer hash code of a word
  *@return an integer that identify a word based on its characters
  */
  private int generateHashCode(String word){
    int hCode = 0;
    for (char c: word.toCharArray()){
      hCode += (int)c;
    }
    return hCode;
  }


  /** Use to resize the hashtable when needed
   */
  public void resize(){
    int newSize = nextProbablePrime(capacity * 2);
    System.out.println("\nExceeded table load factor threshold...");
    printMetrics();
    System.out.println("Resizing from " + capacity + " to " + newSize + "...");
    capacity = newSize;
    entries = 0;
    Node[] orig = table.clone();
    table = new Node[capacity];
    for(Node n : orig){
      if (n != null){
        Node curr = n;
        while(curr != null){
          add(curr.getKey());
          curr = curr.getNext();
        }
      }
    }
    printMetrics();
    System.out.println();
  }

  private double loadFactor(){
    return entries/(double)capacity;
  }



  /** Prints a textual representation of the hashtable
   */
  public void printMetrics(){
      ArrayList<Integer> metrics = new ArrayList<>();
      for(Node head : table){
        if (head != null){
            Node curr = head;
          int count = 0;
          while(curr != null){
            count++;
            curr = curr.getNext();
          }
          metrics.add(count);
        }
      }

      int buckets = 0;
      int minimum = 0, maximum = 0, mode = 0;
      double  total = 0.0, mean = 0.0, median = 0.0, percFill = 0.0, load = 0.0;

      Collections.sort(metrics);
      minimum = Collections.min(metrics);
      maximum = Collections.max(metrics);

      load = loadFactor();

      // percent fill
      percFill = (double)buckets / (double)capacity;

      buckets = metrics.size();

      for (int i : metrics){
        total+= (double)i;
      }
      //Average -----------------------
      mean = total / buckets;

      //Medium -----------------------
          int mid = metrics.size()/2;
      if (metrics.size() % 2 == 0)
            median = (double)(metrics.get(mid) + metrics.get(mid - 1))/ 2.0;
      else
            median = (double) metrics.get(mid);
      //Mode -----------------------

          int maxValue = 0, maxCount = 0;
            for (int i = 0; i < metrics.size(); ++i) {
                int num = 0;
                for (int j = 0; j < metrics.size(); ++j) {
                    if (metrics.get(j) == metrics.get(i)){++num;}
                }
                if (num > maxCount) {
                    maxCount = num;
                    maxValue = metrics.get(i);
                }
            }
        mode = maxValue;

      System.out.println("Table size   : " + capacity);
      System.out.println("# of entries : " + entries);
      System.out.println("Load Factor  : " + load);
      System.out.println("# of Buckets : " + buckets);
      System.out.println("Minimum      : " + minimum);
      System.out.println("Maximum      : " + maximum);
      System.out.println("Mean         : " + mean);
      System.out.println("Medium       : " + median);
      if (mode != 0){
      System.out.println("Mode         : " + mode);
      }


  }

  private int getHashIndex(String word){
      int hashCode = generateHashCode(word);
      int hashIndex = hashCode % capacity;
      if (hashIndex < 0){
        hashIndex = hashIndex + capacity;
      }
      return hashIndex;
  }


//** test code */
  public static void main(String[] args) {
    HashTable ht = new HashTable();
    ht.addAll("tac", "dog", "Dog", "cat", "rabbit", "Rait", "egg", "gge","rabbit", "Rait", "egg", "gge", "sdfhbsdfbdskfbdsfk", "sdfsdfhbd");

    ht.addAll("apple", "elppa", "leppa");

    ht.printMetrics();
    System.out.println();
  }


}
