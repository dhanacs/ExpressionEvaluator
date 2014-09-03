public class Tester 
{
    public static void main(String []args) 
    {
        ExpressionEvaluator object;
        object = new ExpressionEvaluator("(4+(50*6)-(500/250))");
        System.out.println(object.getExpressionResult());

        object = new ExpressionEvaluator("(25)/(5+(10/2)*1)");
        System.out.println(object.getExpressionResult());

        object = new ExpressionEvaluator("25");
        System.out.println(object.getExpressionResult());

        object = new ExpressionEvaluator("((2+3)+((6/2)*100))*(5)");
        System.out.println(object.getExpressionResult());

        object = new ExpressionEvaluator("(10)+(10)");
        System.out.println(object.getExpressionResult());

        object = new ExpressionEvaluator("200+((300))");
        System.out.println(object.getExpressionResult());
    }
}
