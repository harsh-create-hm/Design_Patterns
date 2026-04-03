package creational;

/**
 * Builder Pattern
 *
 * Intent: Separate the construction of a complex object from its representation,
 * so that the same construction process can create different representations.
 *
 * Use when:
 * - The algorithm for creating a complex object should be independent of the parts
 *   that make up the object and how they are assembled.
 * - The construction process must allow different representations of the object constructed.
 */
public class Builder {

    // Product
    static class Pizza {
        private String size;
        private String crust;
        private String sauce;
        private String cheese;
        private String toppings;

        private Pizza() {}

        @Override
        public String toString() {
            return "Pizza{size='" + size + "', crust='" + crust +
                   "', sauce='" + sauce + "', cheese='" + cheese +
                   "', toppings='" + toppings + "'}";
        }
    }

    // Builder interface
    interface PizzaBuilder {
        PizzaBuilder size(String size);
        PizzaBuilder crust(String crust);
        PizzaBuilder sauce(String sauce);
        PizzaBuilder cheese(String cheese);
        PizzaBuilder toppings(String toppings);
        Pizza build();
    }

    // Concrete builder
    static class ConcretePizzaBuilder implements PizzaBuilder {
        private Pizza pizza = new Pizza();

        @Override
        public PizzaBuilder size(String size) {
            pizza.size = size;
            return this;
        }

        @Override
        public PizzaBuilder crust(String crust) {
            pizza.crust = crust;
            return this;
        }

        @Override
        public PizzaBuilder sauce(String sauce) {
            pizza.sauce = sauce;
            return this;
        }

        @Override
        public PizzaBuilder cheese(String cheese) {
            pizza.cheese = cheese;
            return this;
        }

        @Override
        public PizzaBuilder toppings(String toppings) {
            pizza.toppings = toppings;
            return this;
        }

        @Override
        public Pizza build() {
            return pizza;
        }
    }

    // Director (optional) - defines the construction sequence
    static class PizzaDirector {
        public Pizza buildMargherita(PizzaBuilder builder) {
            return builder.size("Medium").crust("Thin").sauce("Tomato")
                          .cheese("Mozzarella").toppings("Basil").build();
        }

        public Pizza buildPepperoni(PizzaBuilder builder) {
            return builder.size("Large").crust("Thick").sauce("Tomato")
                          .cheese("Cheddar").toppings("Pepperoni").build();
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Builder pattern demo:");

        PizzaDirector director = new PizzaDirector();

        Pizza margherita = director.buildMargherita(new ConcretePizzaBuilder());
        System.out.println("Margherita: " + margherita);

        Pizza pepperoni = director.buildPepperoni(new ConcretePizzaBuilder());
        System.out.println("Pepperoni:  " + pepperoni);

        // Direct builder usage without director
        Pizza custom = new ConcretePizzaBuilder()
                .size("Small").crust("Stuffed").sauce("BBQ")
                .cheese("Gouda").toppings("Mushrooms, Onions").build();
        System.out.println("Custom:     " + custom);
    }
}
