
/**
 *  Rixing Wu
 *  Lab 2
 * Expression Logic 
 */

public class Expression
{
    
    
    /** Takes in an infix expressing,  and attempts to
     @param infixExpression The string in infix format to evaluate
     @throws IllegalArgumentException when trying to divide by zero
     @return the result of the expression
     */
    public int evaluate(String infixExpression) throws IllegalArgumentException
    { 
        ArrayStack<Integer> operands =  new ArrayStack<>();
        ArrayStack<Character> operators = new ArrayStack<>();
        char[] array = infixExpression.toCharArray();
        
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == ' ')
                continue;
 
            if (array[i] >= '0' && array[i] <= '9')
            {
                StringBuilder sMore = new StringBuilder();
                //add all numeric values to operands
                while (i < array.length && array[i] >= '0' && array[i] <= '9')
                    sMore.append(array[i++]);
                	operands.push(Integer.parseInt(sMore.toString()));
            }
            	// add operators
            else if (array[i] == '(')
                operators.push(array[i]);
 
            else if (array[i] == ')')
            {
                while (operators.peek() != '(')
                {
                	int result = operate(operators.pop(), operands.pop(), operands.pop());
                	operands.push(result);
                }
                operators.pop();
            }
 
            else
            {
                while (!operators.isEmpty() && hasGreaterPrecedence(array[i], operators.peek())  ){
                	int result = operate(operators.pop(), operands.pop(), operands.pop());
                    operands.push(result);
                } 
                operators.push(array[i]);
            }
        }
 
    
        while (!operators.isEmpty()){
        		int result = operate(operators.pop(), operands.pop(), operands.pop());
        		 operands.push(result);
        }
        
       
        return operands.pop();
    }
    
   

    /** This method calculates the result in format of (+ num1 num2)
     @param operator  (+,-,/,*)
     @param num1 The first number
     @param num2 The second number
     @throws IllegalArgumentException if try to divide by zero
     @return true is operator1 has greater precedence, else false
     */
    private int operate(char operator, int num1, int num2)
    {   
       //Handle divide by zero exception
      if (operator == '/' && num1 == 0){ throw new IllegalArgumentException("Cannot divide 0"); }
       // Continue
      if (operator == '+')
            return num2 + num1;
      if (operator == '-')
            return num2 - num1;
      if (operator == '*')
            return num2 * num1;
      if (operator == '/')
            return num2 / num1;
      else
        return 0;
    }
 
    
    /** This function determines which operator has more precedence
     @param  operator1 the first operator (+,-,/,*,etc)
     @param operator2
     @return true is operator1 has greater precedence, else false
     */
    private boolean hasGreaterPrecedence (char operator1, char operator2)
    {   
        if ((operator1 == '(' || operator2 == ')') || ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')))
                return false;
        else
              return true;
    }
 
}