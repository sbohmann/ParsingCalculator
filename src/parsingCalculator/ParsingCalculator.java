
package parsingCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

class ParsingCalculator
{
    private final BufferedReader reader;
    
    private ParsingCalculator()
    {
        this.reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }
    
    public static void main(String[] args)
    {
        try
        {
            new ParsingCalculator().run();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
    
    private void run() throws IOException
    {
        while (true)
        {
            String line = reader.readLine();
            
            if (line == null)
            {
                break;
            }
            
            try
            {
                Expression expression = ExpressionReader.evaluate(line);
    
                double result = Evaluator.evaluate(expression);
    
                System.out.println(result);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
    }
}
