import java.util.List;

class Parser {
    private List<Token> tokens;
    private int currentTokenIndex;

    Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    private Token currentToken() {
        return tokens.get(currentTokenIndex);
    }

    private void eat(TokenType type) {
        if (currentToken().type == type) {
            currentTokenIndex++;
        } else {
            throw new RuntimeException("Erro de sintaxe: esperado " + type + " mas encontrado " + currentToken().type);
        }
    }

    public void parse() {
        System.out.println("Árvore sintática:");
        E(0);

        if (currentToken().type != TokenType.EOF) {
            throw new RuntimeException("Erro: entrada extra após análise.");
        }
    }

    private void E(int indent) {
        print("E", indent);
        T(indent + 2);
        ELinha(indent + 2);
    }

    private void ELinha(int indent) {
        Token t = currentToken();
        if (t.type == TokenType.PLUS) {
            print("+", indent);
            eat(TokenType.PLUS);
            T(indent + 2);
            ELinha(indent + 2);
        } else if (t.type == TokenType.MINUS) {
            print("-", indent);
            eat(TokenType.MINUS);
            T(indent + 2);
            ELinha(indent + 2);
        } else {
            print("ε", indent);
        }
    }

    private void T(int indent) {
        print("T", indent);
        F(indent + 2);
        TLinha(indent + 2);
    }

    private void TLinha(int indent) {
        Token t = currentToken();
        if (t.type == TokenType.TIMES) {
            print("*", indent);
            eat(TokenType.TIMES);
            F(indent + 2);
            TLinha(indent + 2);
        } else if (t.type == TokenType.DIVIDE) {
            print("/", indent);
            eat(TokenType.DIVIDE);
            F(indent + 2);
            TLinha(indent + 2);
        } else {
            print("ε", indent);
        }
    }

    private void F(int indent) {
        Token t = currentToken();
        if (t.type == TokenType.LPAREN) {
            print("(", indent);
            eat(TokenType.LPAREN);
            E(indent + 2);
            print(")", indent);
            eat(TokenType.RPAREN);
        } else if (t.type == TokenType.ID) {
            print("id", indent);
            eat(TokenType.ID);
        } else {
            throw new RuntimeException("Erro de sintaxe em F: token inesperado " + t.type);
        }
    }

    private void print(String text, int indent) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(text);
    }
}
