import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int currentTokenIndex;
    private boolean[] branches = new boolean[100];

    // Construtor: inicializa a lista de tokens e o índice do token atual
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    // Método principal: inicia o processo de análise sintática chamando E (símbolo
    // inicial)
    public void parse() {
        System.out.println("Árvore sintática:");
        E(0);
    }

    // Corresponde à regra E → T ELinha
    // Imprime "E" na árvore e chama T e ELinha recursivamente
    private void E(int indent) {
        print("E", indent);
        branches[indent] = true;
        T(indent + 2);
        branches[indent] = true;
        ELinha(indent + 2);
        branches[indent] = false;
    }

    // Corresponde a ELinha → + T ELinha | - T ELinha | ε
    // Verifica se há '+' ou '-' e continua recursivamente, ou imprime ε (vazio)
    private void ELinha(int indent) {
        Token token = peek();
        if (token.type == TokenType.PLUS || token.type == TokenType.MINUS) {
            print(token.value, indent);
            advance();
            branches[indent] = true;
            T(indent + 2);
            branches[indent] = true;
            ELinha(indent + 2);
            branches[indent] = false;
        } else {
            print("ε", indent);
        }
    }

    // Corresponde à regra T → F TLinha
    // Imprime "T" e chama F e TLinha
    private void T(int indent) {
        print("T", indent);
        branches[indent] = true;
        F(indent + 2);
        branches[indent] = true;
        TLinha(indent + 2);
        branches[indent] = false;
    }

    // Corresponde a TLinha → * F TLinha | / F TLinha | ε
    // Trata multiplicações e divisões recursivamente, ou ε
    private void TLinha(int indent) {
        Token token = peek();
        if (token.type == TokenType.TIMES || token.type == TokenType.DIVIDE) {
            print(token.value, indent);
            advance();
            branches[indent] = true;
            F(indent + 2);
            branches[indent] = true;
            TLinha(indent + 2);
            branches[indent] = false;
        } else {
            print("ε", indent);
        }
    }

    // Corresponde a F → ( E ) | id
    // Verifica se o fator é uma subexpressão entre parênteses ou um identificador
    private void F(int indent) {
        print("F", indent);
        Token token = peek();
        if (token.type == TokenType.LPAREN) {
            print("(", indent + 2);
            advance();
            branches[indent + 2] = true;
            E(indent + 4);
            branches[indent + 2] = false;
            if (peek().type == TokenType.RPAREN) {
                print(")", indent + 2);
                advance();
            } else {
                throw new RuntimeException("Esperado ')' em F");
            }
        } else if (token.type == TokenType.ID) {
            print("id", indent + 2);
            advance();
        } else {
            throw new RuntimeException("Token inesperado em F: " + token.value);
        }
    }

    // Retorna o token atual sem avançar no índice (lookahead)
    private Token peek() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex);
        }
        return new Token(TokenType.EOF, "");
    }

    // Avança para o próximo token
    private void advance() {
        currentTokenIndex++;
    }

    // Imprime a árvore sintática com indentação e ramificações visuais
    private void print(String text, int indent) {
        if (indent == 0) {
            System.out.println(text);
        } else {
            for (int i = 0; i < indent - 2; i += 2) {
                System.out.print(branches[i] ? "│   " : "    ");
            }
            System.out.print(branches[indent - 2] ? "├── " : "└── ");
            System.out.println(text);
        }
    }
}
