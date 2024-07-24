public class CalculatorOperations {
    public static double calculate(String expression) {
        return evaluate(expression);
    }

    private static double evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        // Stack for numbers
        java.util.Stack<Double> values = new java.util.Stack<>();

        // Stack for operators
        java.util.Stack<Character> ops = new java.util.Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.') {
                StringBuilder StackOfNum = new StringBuilder();
                // There may be more than one digit in number
                while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.'))
                    StackOfNum.append(tokens[i++]);
                values.push(Double.parseDouble(StackOfNum.toString()));
                i--;
            } else if (tokens[i] == '(') {
                ops.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
        }

        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '*' -> {
                return a * b;
            }
            case '/' -> {
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
            }
        }
        return 0;
    }
}