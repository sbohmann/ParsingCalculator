
package parsingCalculator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class ExpressionReader
{
    private final Queue<Token> queue;
    private final boolean evaluatingSubExpression;
    
    private final ExpressionBuilder result = new ExpressionBuilder();
    private boolean foundEndOfExpression;
    
    private ExpressionReader(Queue<Token> queue, boolean evaluatingSubExpression)
    {
        this.queue = queue;
        this.evaluatingSubExpression = evaluatingSubExpression;
    }
    
    static Expression evaluate(String line)
    {
        List<Token> tokens = Tokenizer.readTokens(line);
        Queue<Token> queue = new LinkedList<>(tokens);
    
        ExpressionReader reader = new ExpressionReader(queue, false);
        
        return reader.readExpression();
    }
    
    private Expression readExpression()
    {
        while (!foundEndOfExpression)
        {
            Token token = queue.poll();
            
            if (token == null)
            {
                checkLevelForEndOfTokens();
                break;
            }
            
            handleToken(token);
        }
        
        return result.createExpression();
    }
    
    private void handleToken(Token token)
    {
        switch (token.type)
        {
            case Number:
                handleValue(Double.parseDouble(token.text));
                break;
            case OpeningBracket:
                evaluateSubExpression();
                break;
            case ClosingBracket:
                checkLevelForClosingBracket(token.index);
                foundEndOfExpression = true;
                break;
            case Operator:
                handleOperator(Operator.forString(token.text));
                break;
        }
    }
    
    private void handleValue(double value)
    {
        result.addValue(value);
    }
    
    private void evaluateSubExpression()
    {
        ExpressionReader subExpressionEvaluator = new ExpressionReader(queue, true);
        
        Expression subExpression = subExpressionEvaluator.readExpression();
        
        double subExpressionResult = Evaluator.evaluate(subExpression);
        
        handleValue(subExpressionResult);
    }
    
    private void checkLevelForClosingBracket(int index)
    {
        if (!evaluatingSubExpression)
        {
            throw new RuntimeException("Found unexpected closing bracket at index " + index);
        }
    }
    
    private void handleOperator(Operator operator)
    {
        result.addOperator(operator);
    }
    
    private void checkLevelForEndOfTokens()
    {
        if (evaluatingSubExpression)
        {
            throw new RuntimeException("Missing closing bracket at end");
        }
    }
}
