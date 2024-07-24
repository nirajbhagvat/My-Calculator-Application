import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorOperations {
    public static double calculate(String expression) throws ScriptException {
        // Use Java's built-in JavaScript engine to evaluate the expression
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        // Evaluate the expression and return the result
        return (double) engine.eval(expression);
    }
}
