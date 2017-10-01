
package parsingCalculator;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ParsingAndEvaluationTest
{
    @Test
    public void singleValue()
    {
        Map<String, Double> resultForInput = Map.of(
            "0", 0.0,
            "0.0", 0.0);
        
        process(resultForInput, 0.0);
    }
    
    @Test
    public void addition()
    {
        Map<String, Double> resultForInput = Map.of(
            "0 + 1", 1.0,
            "0.25 + 1.75", 2.0,
            "0.2 + 1.0", 1.2,
            "0.3 + 1000.0", 1000.3,
            "0.3 + - 1000.0", -999.7,
            "- 0.3 + 1000.0", 999.7);
        
        process(resultForInput, 1e-20);
    }
    
    @Test
    public void brokenDivision()
    {
        Map<String, Double> resultForInput = Map.of(
            "1/-0", Double.NEGATIVE_INFINITY,
            "-1/0", Double.NEGATIVE_INFINITY,
            "1/0",Double.POSITIVE_INFINITY,
            "-1/-0", Double.POSITIVE_INFINITY,
            "0/0",Double.NaN,
            "-0/-0", Double.NaN,
            "-0/0",Double.NaN,
            "0/-0", Double.NaN);
        
        process(resultForInput, 0.0);
    }
    
    private void process(Map<String, Double> resultForInput, double delta)
    {
        for (Map.Entry<String, Double> entry : resultForInput.entrySet())
        {
            Expression expression = ExpressionReader.readExpression(entry.getKey());
            
            double result = Evaluator.evaluate(expression);
    
            Assert.assertEquals(entry.getValue(), result, delta);
        }
    }
}
