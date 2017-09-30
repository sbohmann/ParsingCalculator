
package parsingCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Expression
{
    final double initialValue;
    final List<Operation> operations;
    
    Expression(double initialValue, List<Operation> operations)
    {
        this.initialValue = initialValue;
        this.operations = Collections.unmodifiableList(new ArrayList<>(operations));
    }
}
