/**
 *
 * @author Rixing
 */

public class Driver {
    
    public static void main(String[] args){
        Expression express = new Expression();
        int result = express.evaluate("10 + 2 * 6 * 3 / 2 + 2 + 2 / 3 + 5");
        System.out.print(result);
    }
    
}
