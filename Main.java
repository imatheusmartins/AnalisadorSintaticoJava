import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Analisador Sintático ===");
        System.out.println("Digite expressões como: id + id * ( id - id )");
        System.out.println("Digite 'sair' para encerrar.\n");

        while (true) {
            System.out.print("Entrada > ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando...");
                break;
            }

            try {
                Lexer lexer = new Lexer(input);
                Parser parser = new Parser(lexer.tokenize());
                parser.parse();
                System.out.println("✔ Expressão válida.\n");
            } catch (RuntimeException e) {
                System.out.println("✘ Erro: " + e.getMessage() + "\n");
            }
        }

        scanner.close();
    }
}
