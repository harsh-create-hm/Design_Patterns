package behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * Interpreter Pattern
 *
 * Intent: Given a language, define a representation for its grammar along with an interpreter
 * that uses the representation to interpret sentences in the language.
 *
 * Use when:
 * - There is a simple grammar to interpret and efficiency is not a critical concern.
 * - You want to represent each grammar rule as a class.
 */
public class Interpreter {

    // Context holds variables used during interpretation
    static class Context {
        private java.util.Map<String, Integer> variables = new java.util.HashMap<>();

        public void setVariable(String name, int value) {
            variables.put(name, value);
        }

        public int getVariable(String name) {
            return variables.getOrDefault(name, 0);
        }
    }

    // Abstract expression
    interface Expression {
        int interpret(Context context);
    }

    // Terminal expressions
    static class NumberExpression implements Expression {
        private int number;
        NumberExpression(int number) { this.number = number; }

        @Override
        public int interpret(Context context) { return number; }
    }

    static class VariableExpression implements Expression {
        private String name;
        VariableExpression(String name) { this.name = name; }

        @Override
        public int interpret(Context context) { return context.getVariable(name); }
    }

    // Non-terminal (composite) expressions
    static class AddExpression implements Expression {
        private Expression left, right;
        AddExpression(Expression left, Expression right) { this.left = left; this.right = right; }

        @Override
        public int interpret(Context context) {
            return left.interpret(context) + right.interpret(context);
        }
    }

    static class SubtractExpression implements Expression {
        private Expression left, right;
        SubtractExpression(Expression left, Expression right) { this.left = left; this.right = right; }

        @Override
        public int interpret(Context context) {
            return left.interpret(context) - right.interpret(context);
        }
    }

    static class MultiplyExpression implements Expression {
        private Expression left, right;
        MultiplyExpression(Expression left, Expression right) { this.left = left; this.right = right; }

        @Override
        public int interpret(Context context) {
            return left.interpret(context) * right.interpret(context);
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Interpreter pattern demo:");
        System.out.println();

        Context context = new Context();
        context.setVariable("x", 10);
        context.setVariable("y", 5);
        context.setVariable("z", 3);

        // Expression: (x + y) * z  =>  (10 + 5) * 3  =  45
        Expression expr1 = new MultiplyExpression(
            new AddExpression(new VariableExpression("x"), new VariableExpression("y")),
            new VariableExpression("z")
        );

        // Expression: x - y + 2  =>  10 - 5 + 2  =  7
        Expression expr2 = new AddExpression(
            new SubtractExpression(new VariableExpression("x"), new VariableExpression("y")),
            new NumberExpression(2)
        );

        System.out.println("x = 10, y = 5, z = 3");
        System.out.println("(x + y) * z = " + expr1.interpret(context));
        System.out.println("x - y + 2   = " + expr2.interpret(context));

        // Update variable and re-evaluate
        context.setVariable("x", 20);
        System.out.println("\nAfter x = 20:");
        System.out.println("(x + y) * z = " + expr1.interpret(context));
        System.out.println("x - y + 2   = " + expr2.interpret(context));
    }
}
