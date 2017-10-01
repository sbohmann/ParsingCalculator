
package parsingCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Tokenizer
{
    private static final Pattern Whitespace = Pattern.compile("\\s+");
    private static final Pattern Number = Pattern.compile("\\d+(\\.\\d+)?");
    private static final Pattern OpeningBracket = Pattern.compile("\\(");
    private static final Pattern ClosingBracket = Pattern.compile("\\)");
    private static final Pattern Operator = Pattern.compile("[+\\-*/]");
    
    private static final Map<TokenType, Pattern> patternForType;
    
    private String text;
    private int offset = 0;
    private final List<Token> result = new ArrayList<>();
    
    static
    {
        patternForType = new HashMap<>();
        patternForType.put(TokenType.Number, Number);
        patternForType.put(TokenType.OpeningBracket, OpeningBracket);
        patternForType.put(TokenType.ClosingBracket, ClosingBracket);
        patternForType.put(TokenType.Operator, Operator);
    }
    
    private Tokenizer(String text)
    {
        this.text = text;
    }
    
    static List<Token> readTokens(String text)
    {
        return new Tokenizer(text).readTokens();
    }
    
    private List<Token> readTokens()
    {
        while (true)
        {
            skipWhitespace();
            
            if (offset == text.length())
            {
                return result;
            }
    
            readToken();
        }
    }
    
    private void skipWhitespace()
    {
        Matcher matcher = Whitespace.matcher(text.substring(offset));
        
        if (matcher.lookingAt())
        {
            offset += matcher.end();
        }
    }
    
    private void readToken()
    {
        for (TokenType type : TokenType.values())
        {
            Matcher matcher = patternForType.get(type).matcher(text.substring(offset));
        
            if (matcher.lookingAt())
            {
                int endOfCurrentMatch = offset + matcher.end();
                result.add(new Token(type, text.substring(offset, endOfCurrentMatch), offset));
                offset = endOfCurrentMatch;
                return;
            }
        }
        
        reportParsingError();
    }
    
    private void reportParsingError()
    {
        throw new RuntimeException("Unable to parse token at index " + offset);
    }
}
