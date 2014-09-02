public class Tester {
    public static void main(String []args) {
        ExpressionEvaluator object = new ExpressionEvaluator("4+50*6-500/250");
        object.infixToPostfix();
        System.out.println(object.evaluateExpression());
    }
}

// 4 + 300 - 2 = 302.
