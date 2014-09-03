import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

public class InfixToPostfix
{
    private String infix;
    private ArrayList<String> postfix;

    public InfixToPostfix(String expression) 
    {
        infix = expression;
        postfix = new ArrayList<String>();
    }

    // Check if the given character is an operator.
    private boolean isOperator(char character) 
    {
        if(character == '/' || character == '*' || character == '+' || character == '-') return true;
        else return false;
    }

    // Return the precedence of the given operator.
    private int precedence(char operator) 
    {
        if(operator == '/' || operator == '*') return 2;
        else return 1;
    }

    // Infix to Postfix conversion using stack.
    public void convertToPostfix() 
    {
        Stack<Character> operators = new Stack<Character>();
        Stack<String> operands = new Stack<String>();

        for(int i = 0; i < infix.length(); ++i) 
        {
            if(isOperator(infix.charAt(i))) 
            {
                if(operators.isEmpty()) operators.push(infix.charAt(i));
                else 
                {
                    if(precedence(infix.charAt(i)) > precedence(operators.peek())) operators.push(infix.charAt(i));
                    else 
                    {
                        while(!operators.isEmpty() && precedence(infix.charAt(i)) <= precedence(operators.peek())) 
                        {
                            postfix.add(operators.pop() + "");
                        }

                        operators.push(infix.charAt(i));
                    }
                }
            }
            else 
            {
                // Extract the operand.
                String currentOperand = infix.charAt(i) + "";
                ++i;
                while(i < infix.length() && !isOperator(infix.charAt(i))) 
                {
                    currentOperand += infix.charAt(i);
                    ++i;
                }

                // Append the operand.
                postfix.add(currentOperand);

                // 1 step back, when an operator is seen.
                if(i < infix.length()) --i;
            }
        }

        while(!operators.isEmpty()) 
        {
            postfix.add(operators.pop() + "");
        }
    }

    // Perform the given binary operation.
    private String performOperation(String operand1, String operand2, Character operator) 
    {
        if(operator == '+') 
        {
            BigDecimal a = new BigDecimal(operand1);
            BigDecimal b = new BigDecimal(operand2);
            BigDecimal c = a.add(b).setScale(2, BigDecimal.ROUND_UP);

            return c.toString();
        }
        else if(operator == '-') 
        {
            BigDecimal a = new BigDecimal(operand1);
            BigDecimal b = new BigDecimal(operand2);
            BigDecimal c = a.subtract(b).setScale(2, BigDecimal.ROUND_UP);

            return c.toString();
        }
        else if(operator == '*') 
        {
            BigDecimal a = new BigDecimal(operand1);
            BigDecimal b = new BigDecimal(operand2);
            BigDecimal c = a.multiply(b).setScale(2, BigDecimal.ROUND_UP);

            return c.toString();
        }
        else 
        {
            BigDecimal a = new BigDecimal(operand1);
            BigDecimal b = new BigDecimal(operand2);
            BigDecimal c = a.divide(b).setScale(2, BigDecimal.ROUND_UP);

            return c.toString();
        }
    }

    // Evaluate the postfix expression.
    public String getInfixResult() 
    {
        Stack<String> partialResults = new Stack<String>();
        String operand1, operand2, result, currentValue;

        for(int i = 0; i < postfix.size(); ++i) 
        {
            currentValue = postfix.get(i);
            if(isOperator(currentValue.charAt(0))) 
            {
                operand2 = partialResults.pop();
                operand1 = partialResults.pop();

                result = performOperation(operand1, operand2, currentValue.charAt(0));
                partialResults.push(result);
            }
            else 
            {
                partialResults.push(currentValue);
            }
        }

        return partialResults.pop();
    }
}
