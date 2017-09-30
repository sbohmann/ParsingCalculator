
package parsingCalculator;

import java.util.ArrayList;
import java.util.List;

class Evaluator
{
    static double evaluate(Expression expression)
    {
        List<Operation> additiveOperations = new ArrayList<>();
        
        Operation lastAdditiveOperation = new Operation(Operator.Addition, expression.initialValue);
        
        for (Operation operation : expression.operations)
        {
            if (isMultiplicativeOperation(operation))
            {
                lastAdditiveOperation = applyMultiplicativeToAdditiveOperation(operation, lastAdditiveOperation);
            }
            else
            {
                additiveOperations.add(lastAdditiveOperation);
                lastAdditiveOperation = operation;
            }
        }
        
        additiveOperations.add(lastAdditiveOperation);
        
        return evaluateAdditiveOperationChain(additiveOperations);
    }
    
    private static Operation applyMultiplicativeToAdditiveOperation(Operation multiplicativeOperation, Operation additiveOperation)
    {
        double value = additiveOperation.value;
        
        switch (multiplicativeOperation.operator)
        {
            case Multiplication:
                value *= multiplicativeOperation.value;
                break;
            case Division:
                value /= multiplicativeOperation.value;
                break;
            default:
                throw new IllegalArgumentException();
        }
        
        return new Operation(additiveOperation.operator, value);
    }
    
    private static double evaluateAdditiveOperationChain(List<Operation> additiveOperations)
    {
        double value = 0.0;
        
        for (Operation operation : additiveOperations)
        {
            switch (operation.operator)
            {
                case Addition:
                    value += operation.value;
                    break;
                case Subtraction:
                    value -= operation.value;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        
        return value;
    }
    
    private static boolean isMultiplicativeOperation(Operation operation)
    {
        switch (operation.operator)
        {
            case Addition:
            case Subtraction:
                return false;
            case Multiplication:
            case Division:
                return true;
            default:
                throw new IllegalArgumentException();
        }
    }
}
