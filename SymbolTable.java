import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, String> table = new HashMap<>();

    public void declare(String name, String type) {
        if (table.containsKey(name)) {
            throw new RuntimeException("Variável já declarada: " + name);
        }
        table.put(name, type);
    }

    public String getType(String name) {
        if (!table.containsKey(name)) {
            throw new RuntimeException("Variável não declarada: " + name);
        }
        return table.get(name);
    }

    public boolean isDeclared(String name) {
        return table.containsKey(name);
    }
}