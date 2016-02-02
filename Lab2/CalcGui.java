import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JTextField;


/**
 * fix: parenthesis, backspace, division by 0
 * @author leeg3
 */

public class CalcGui extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    
    private static final int width = 400;
    private static final int height = 400;
    private String currentText = "";
   
    
    private String[][] buttonString = {
                                        {"C", "<", "Q", "/"},
                                        {"7", "8", "9", "*"},
                                        {"4", "5", "6", "-"},
                                        {"1", "2", "3", "+"},
                                        {"0", "(", ")", "="}
                                    };
    
    private Dimension displayDimension = new Dimension(300, 30);
    private Dimension buttonDimension = new Dimension(75, 75);
    
    private JTextField display = new JTextField();
    
    /** Class constructor. This creates and layout in 6 x 4, the top row contains a single textfield, else each row contains 4x buttoms.
     
     */
    public CalcGui()
    {
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,4));
        
        
        //initializes each row element as a new JPanel
        for (int i = 0; i < 6; i++)
        {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            if (i == 0){
                display.setPreferredSize(displayDimension); //(300,15)
                display.setHorizontalAlignment(JTextField.RIGHT);
                panel.add(display);
                
            }else{
                for (int j = 0; j < 4; j++){
                    String bText = buttonString[i-1][j];
                    JButton button = new JButton();
                    button.setText(bText);
                    button.setPreferredSize(buttonDimension);
                    button.addActionListener(this);
                        //Calls actionPerformed
                    panel.add(button);
                }
            }
            
            add(panel);
        }
        
        
    }
    
    /** Method handles all the UI events, such as all the button clicks
     @param e the ActionEvent that triggered the call
     */
    public void actionPerformed(ActionEvent e)
    {
        JButton bClicked = (JButton)e.getSource();
        String bText = bClicked.getText();
        display.setText(currentText);
        
        switch(bText){
                case "C":
                    currentText = ""; break;
                case "<":
                if (currentText.length() > 0)
                    currentText = currentText.substring(0, currentText.length()-1);
                    break;
                case "Q":
                    System.exit(0); break;
                case "=":
                    currentText = evaluateExpression(display.getText()); break;
                default:
                    if ((bText == "*") || (bText == "/") || (bText == "+") ||  (bText == "-")){
                        currentText +=  " " + bText + " ";    // Formatting
                    }else{
                        currentText += bText;
                    }
                
        }
        
        display.setText(currentText);
        
        //Cleared text the next round, if expression has already evaulated
        if (bText == "=")
            currentText = "";
        
    } 
    
    /** Function evaluate the text from the display textfield
     @param infix expression from display
     @throws IllegalArgumentException when trying to divide zero
     @return the result in String format
     */
    private String evaluateExpression(String text){
        Expression evaluater = new Expression();
        
        try{
            int result = evaluater.evaluate(text);
            return Integer.toString(result);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
        
        
    }
    
    
    /**  Main() in project, responsible to present the GUI.
     */
    public static void main(String[] args)
    {
        CalcGui calc = new CalcGui(); 
        calc.setVisible(true);
    }
    
}