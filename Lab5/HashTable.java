
/**
 *  Rxing Wu
 *  Lab5
 */

import java.math.BigInteger;
class HashTable {
  private int capacity;
  private int size;
  private Node[] table;

  /** Constructor
   * @param max size to use (Better performance)
   */
  public HashTable(int maxSize){
    capacity = maxSize;
    table = new Node[maxSize];
    size = 0;
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
    //Case  insensitive
    word = word.toLowerCase();
    int hCode = generateHashCode(word);

    if (hCode > capacity){
      capacity = hCode + 1;
      resize();
    }

    if (table[hCode] == null){
      Node head = new Node(word);
      table[hCode] = head;
      size++;
    }else{
      Node head = table[hCode];
      Node temp = head;
      while(temp != null){
        if (temp.getKey().equals(word)){
          temp.addCount();
          return;
        }else{
          if (temp.getNext() == null){
            Node next = new Node(word);
            temp.setNext(next);
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
 private int nextProbablePrime(){
   BigInteger currentSize = new BigInteger(Integer.toString(capacity));
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
  private void resize(){
    int newSize = nextProbablePrime();
    Node[] orig = table.clone();
    table = new Node[newSize];
    System.arraycopy( orig, 0, table, 0, orig.length);
  }

  /** Prints a textual representation of the hashtable
   */
  public void printTable(){
    System.out.println(" Hashtable size : " + size + " capacity : " + capacity);
      for(Node n : table){
        if (n != null){
          Node curr = n;
          System.out.print(generateHashCode(n.getKey()) + ": ");
          while(curr != null){
            System.out.print( "  ->  " + curr);
            curr = curr.getNext();
          }
          System.out.println();
        }
      }
  }


  /** Array of words that has the same hashnode
   *
   */
  public Node[] findAnagramOfWord(String text){
    int hCord = generateHashCode(text);
    Node[] anagram = new Node[0];

    if (hCord > capacity || table[hCord] == null){
      return anagram;
    }

    Node head = table[hCord];
    while(head != null){
      Node[] copy = anagram.clone();
      anagram = new Node[anagram.length + 1];
      System.arraycopy( copy, 0, anagram, 0, copy.length);

      anagram[anagram.length -1] = head;
      head = head.getNext();
    }
    return anagram;
  }

//** test code */
  public static void main(String[] args) {
    HashTable ht = new HashTable();
    ht.addAll("tac", "dog", "Dog", "cat", "rabbit", "Rait", "egg", "gge","rabbit", "Rait", "egg", "gge", "sdfhbsdfbdskfbdsfk", "sdfsdfhbd");

    ht.addAll("apple", "elppa", "leppa");

    ht.printTable();
    System.out.println();
    Node[] result = ht.findAnagramOfWord("apple");
    System.out.println("Anagram of apple");
    for (Node n : result){
      System.out.print(n + " \t");
    }
    System.out.println();

  }


}
