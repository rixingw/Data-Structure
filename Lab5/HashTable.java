
import java.math.BigInteger;

class HashTable {
  private int capacity;
  private int size;
  private Node[] table;

// Optional size
  public HashTable(int maxSize){
    capacity = maxSize;
    table = new Node[maxSize];
    size = 0;
  }

// initial size
  public HashTable(){
    this(11);
  }

  public void addAll(String... elements){
    for(String word : elements){
      add(word);
    }
  }


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



 private int nextProbablePrime(){
   BigInteger currentSize = new BigInteger(Integer.toString(capacity));
   BigInteger nxPrime = currentSize.nextProbablePrime();
   return nxPrime.intValue();
 }

  private int generateHashCode(String word){
    int hCode = 0;
    for (char c: word.toCharArray()){
      hCode += (int)c;
    }
    return hCode;
  }


private void resize(){
  int newSize = nextProbablePrime();
  System.out.println(newSize);
  Node[] orig = table.clone();
  table = new Node[newSize];
  System.arraycopy( orig, 0, table, 0, orig.length);
}

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

  public static void main(String[] args) {
    HashTable ht = new HashTable();
    ht.addAll("tac", "dog", "Dog", "cat", "rabbit", "Rait", "egg", "gge","rabbit", "Rait", "egg", "gge", "sdfhbsdfbdskfbdsfk", "sdfsdfhbd");
    ht.printTable();
  }


}
