
package parsingCalculator;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ExpressionReaderTest
{
    @Test
    public void emptyExpression()
    {
        for (String input : List.of("", " ", "  "))
        {
            try
            {
                ExpressionReader.readExpression(input);
            }
            catch (IllegalStateException exception)
            {
                continue;
            }
            
            Assert.fail();
        }
    }
    
    @Test
    public void positiveNumber()
    {
        for (String input : List.of("3", " 3", "3 ", " 3 "))
        {
            Expression expression = ExpressionReader.readExpression(input);
            Assert.assertTrue(expression.operations.isEmpty());
            Assert.assertEquals(3.0, expression.initialValue, 0.0);
        }
    }
    
    @Test
    public void negativeNumber()
    {
        for (String input : List.of("-3", " - 3", "- 3 ", " -3 ", " - 3 "))
        {
            Expression expression = ExpressionReader.readExpression(input);
            Assert.assertTrue(expression.operations.isEmpty());
            Assert.assertEquals(3.0, expression.initialValue, 0.0);
        }
    }
}
