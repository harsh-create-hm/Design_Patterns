package structural;

/**
 * Decorator Pattern
 *
 * Intent: Attach additional responsibilities to an object dynamically.
 * Decorators provide a flexible alternative to subclassing for extending functionality.
 *
 * Use when:
 * - You want to add responsibilities to individual objects dynamically without affecting others.
 * - Extension by subclassing is impractical due to the large number of independent extensions.
 */
public class Decorator {

    // Component interface
    interface Coffee {
        String getDescription();
        double getCost();
    }

    // Concrete component
    static class SimpleCoffee implements Coffee {
        @Override
        public String getDescription() {
            return "Simple Coffee";
        }

        @Override
        public double getCost() {
            return 1.00;
        }
    }

    // Base decorator
    static abstract class CoffeeDecorator implements Coffee {
        protected Coffee coffee;

        CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }

        @Override
        public String getDescription() {
            return coffee.getDescription();
        }

        @Override
        public double getCost() {
            return coffee.getCost();
        }
    }

    // Concrete decorators
    static class MilkDecorator extends CoffeeDecorator {
        MilkDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return coffee.getDescription() + ", Milk";
        }

        @Override
        public double getCost() {
            return coffee.getCost() + 0.25;
        }
    }

    static class SugarDecorator extends CoffeeDecorator {
        SugarDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return coffee.getDescription() + ", Sugar";
        }

        @Override
        public double getCost() {
            return coffee.getCost() + 0.10;
        }
    }

    static class VanillaDecorator extends CoffeeDecorator {
        VanillaDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return coffee.getDescription() + ", Vanilla";
        }

        @Override
        public double getCost() {
            return coffee.getCost() + 0.50;
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Decorator pattern demo:");

        Coffee coffee = new SimpleCoffee();
        System.out.printf("%-40s $%.2f%n", coffee.getDescription(), coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.printf("%-40s $%.2f%n", coffee.getDescription(), coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.printf("%-40s $%.2f%n", coffee.getDescription(), coffee.getCost());

        coffee = new VanillaDecorator(coffee);
        System.out.printf("%-40s $%.2f%n", coffee.getDescription(), coffee.getCost());

        // Another combination
        Coffee fancy = new VanillaDecorator(new MilkDecorator(new MilkDecorator(new SimpleCoffee())));
        System.out.printf("%-40s $%.2f%n", fancy.getDescription(), fancy.getCost());
    }
}
