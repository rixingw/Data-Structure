

class Node{
  private String key;
  private int count;
  private Node next;

  public Node(String k){
    key = k;
    count = 1;
    next = null;
  }

  public String getKey(){
    return key;
  }

  public int getCount(){
    return count;
  }

  public void addCount(){
    count++;
  }

  public void setNext(Node n){
    this.next = n;
  }

  public Node getNext(){
    return next;
  }

  public String toString(){
      String str = "[" + key + ":" + Integer.toString(count) + "]";
      return str;
  }

}
