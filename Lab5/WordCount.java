
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class WordCount {

  public static void main(String[] args) {
    String worlist1 = "american-english-JL.txt";
    String worlist2 = "hashtable-additional-info.txt";
    String worlist3 = "hashtable.txt";
    String worlist4 = "the-lancashire-cotton-famine.txt";
    String worlist5 = "wit-attendance-policy.txt";
    String[] list = {worlist1, worlist2,worlist3, worlist4, worlist5};

    for (String l : list){
      System.out.println("-----------------------------------------------------");
      System.out.println("File name : " + l );
      System.out.println("-----------------------------------------------------");
      HashTable table = analyzeText(l);
      System.out.println("Final Result : " + l);
      table.printMetrics();
      System.out.println("\n\n");
    }
  }


  /** Reads in a file and product a hashtable from the content
   * @param filePath the location of the file
   *
   */
  public static HashTable analyzeText(String filePath){
    HashTable table = new HashTable();
    try {
      File file = new File(filePath);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        table.addAll(words);
      }
      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return table;
  }
}
