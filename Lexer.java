import java.util.ArrayList;
import java.util.List;

class Lexer {
    private String input;
    private int position;

    Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    private char currentChar() {
        return position < input.length() ? input.charAt(position) : '\0';
    }

    private void advance() {
        position++;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (position < input.length()) {
            char c = currentChar();

            if (Character.isWhitespace(c)) {
                advance();
            } else if (c == '+') {
                tokens.add(new Token(TokenType.PLUS, "+"));
                advance();
            } else if (c == '-') {
                tokens.add(new Token(TokenType.MINUS, "-"));
                advance();
            } else if (c == '*') {
                tokens.add(new Token(TokenType.TIMES, "*"));
                advance();
            } else if (c == '/') {
                tokens.add(new Token(TokenType.DIVIDE, "/"));
                advance();
            } else if (c == '(') {
                tokens.add(new Token(TokenType.LPAREN, "("));
                advance();
            } else if (c == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")"));
                advance();
            } else if (Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                while (Character.isLetterOrDigit(currentChar())) {
                    sb.append(currentChar());
                    advance();
                }
                tokens.add(new Token(TokenType.ID, sb.toString()));
            } else {
                throw new RuntimeException("Caractere inválido: " + c);
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}