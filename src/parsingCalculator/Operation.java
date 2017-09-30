package parsingCalculator;

class Operation
{
    final Operator operator;
    final double value;

    Operation(Operator operator, double value)
    {
        this.operator = operator;
        this.value = value;
    }
}
