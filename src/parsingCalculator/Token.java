
package parsingCalculator;

class Token
{
    final TokenType type;
    final String text;
    final int index;
    
    Token(TokenType type, String text, int index)
    {
        this.type = type;
        this.text = text;
        this.index = index;
    }
}
