import java.util.ArrayList;
import java.util.List;

// Classe responsável por quebrar a entrada em tokens
class Lexer {
    private String input; // Entrada original (ex: "id + id * (id - id)")
    private int position; // Posição atual no texto da entrada

    // Construtor que inicializa o lexer com a entrada
    Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    // Retorna o caractere atual na posição do cursor
    private char currentChar() {
        // Se ainda há caracteres, retorna o atual; senão, retorna '\0' (fim de string)
        return position < input.length() ? input.charAt(position) : '\0';
    }

    // Avança o cursor para o próximo caractere
    private void advance() {
        position++;
    }

    // Método principal: quebra a string de entrada em tokens
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        // Percorre toda a entrada caractere por caractere
        while (position < input.length()) {
            char c = currentChar();

            if (Character.isWhitespace(c)) {
                // Ignora espaços e tabulações
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
                // Se for letra (início de identificador), continua lendo letras/dígitos
                StringBuilder sb = new StringBuilder();
                while (Character.isLetterOrDigit(currentChar())) {
                    sb.append(currentChar());
                    advance();
                }
                tokens.add(new Token(TokenType.ID, sb.toString()));

            } else {
                // Se encontrar caractere inválido, lança erro
                throw new RuntimeException("Caractere inválido: " + c);
            }
        }

        // Adiciona um token EOF no final da entrada (fim do arquivo)
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}
