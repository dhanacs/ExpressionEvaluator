// A simple expression evaluator.

import java.util.Stack;

// Expression Evaluator.
public class ExpressionEvaluator {
    private String expression;
    private InfixToPostfix infixObject;

    public ExpressionEvaluator(String expression) {
        this.expression = expression;
    }

    public String getExpressionResult() {
        Stack<Character> result = new Stack<Character>();
        String infix, infixResult;

        for(int i = 0; i < expression.length(); ++i) {
            if (expression.charAt(i) == ')') {
                infix = "";
                Character topCharacter = result.pop();
                while (topCharacter != '(') {
                    infix = topCharacter + infix;
                    topCharacter = result.pop();
                }

                infixObject = new InfixToPostfix(infix);
                infixObject.convertToPostfix();
                infixResult = infixObject.getInfixResult();

                // Push the result into stack.
                for (int j = 0; j < infixResult.length(); ++j) {
                    result.push(infixResult.charAt(j));
                }
            } else {
                result.push(expression.charAt(i));
            }
        }

        // Evaluate the final infix expression.
        infix = "";
        while (!result.isEmpty()) {
            infix = result.pop() + infix;
        }

        infixObject = new InfixToPostfix(infix);
        infixObject.convertToPostfix();
        infixResult = infixObject.getInfixResult();

        return infixResult;
    }
}
