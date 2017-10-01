
package parsingCalculator;

import java.util.ArrayList;
import java.util.List;

class ExpressionBuilder
{
    
    private double initialValue;
    private boolean initialValueWasSet;
    private final List<Operation> operations = new ArrayList<>();
    private Operator currentOperator;
    private boolean negateNextValue;
    
    void addValue(double value)
    {
        if (negateNextValue)
        {
            value = -value;
            negateNextValue = false;
        }
        
        if (!initialValueWasSet)
        {
            initialValue = value;
            initialValueWasSet = true;
        }
        else if (currentOperator == null)
        {
            throw new RuntimeException("Expected operator");
        }
        else
        {
            operations.add(createOperation(value));
            currentOperator = null;
        }
    }
    
    private Operation createOperation(double value)
    {
        return new Operation(currentOperator, value);
    }
    
    void addOperator(Operator operator)
    {
        if (!initialValueWasSet || currentOperator != null)
        {
            if (operator == Operator.Subtraction)
            {
                negateNextValue = !negateNextValue;
            }
            else
            {
                throw new RuntimeException("Expected value");
            }
        }
        else
        {
            currentOperator = operator;
        }
    }
    
    Expression createExpression()
    {
        if (!initialValueWasSet || currentOperator != null)
        {
            throw new IllegalStateException("Missing value token at end of expression");
        }
        
        return new Expression(initialValue, operations);
    }
}
