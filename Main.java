import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite uma expressão (por exemplo: id + id * ( id - id )):");
        String input = scanner.nextLine();

        try {
            Lexer lexer = new Lexer(input);
            Parser parser = new Parser(lexer.tokenize());
            parser.parse();
            System.out.println("Expressão válida.");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        scanner.close();
    }
}
