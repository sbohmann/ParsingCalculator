package parsingCalculator;

enum Operator
{
    Addition,
    Subtraction,
    Multiplication,
    Division;
    
    static Operator forString(String value)
    {
        switch (value)
        {
            case "+":
                return Addition;
            case "-":
                return Subtraction;
            case "*":
                return Multiplication;
            case "/":
                return Division;
            default:
                throw new IllegalArgumentException("Unknown operator representation: [" + value + "]");
        }
    }
}
