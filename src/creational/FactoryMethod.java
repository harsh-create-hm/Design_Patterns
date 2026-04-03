package creational;

/**
 * Factory Method Pattern
 *
 * Intent: Define an interface for creating an object, but let subclasses decide
 * which class to instantiate. Factory Method lets a class defer instantiation to subclasses.
 *
 * Use when:
 * - A class cannot anticipate the type of objects it must create.
 * - A class wants its subclasses to specify the objects it creates.
 */
public class FactoryMethod {

    // Product interface
    interface Animal {
        String speak();
    }

    // Concrete products
    static class Dog implements Animal {
        @Override
        public String speak() {
            return "Woof!";
        }
    }

    static class Cat implements Animal {
        @Override
        public String speak() {
            return "Meow!";
        }
    }

    // Creator (abstract factory method)
    static abstract class AnimalFactory {
        // Factory method to be implemented by subclasses
        public abstract Animal createAnimal();

        // Template method that uses the factory method
        public String getAnimalSound() {
            Animal animal = createAnimal();
            return animal.speak();
        }
    }

    // Concrete creators
    static class DogFactory extends AnimalFactory {
        @Override
        public Animal createAnimal() {
            return new Dog();
        }
    }

    static class CatFactory extends AnimalFactory {
        @Override
        public Animal createAnimal() {
            return new Cat();
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Factory Method pattern demo:");

        AnimalFactory dogFactory = new DogFactory();
        AnimalFactory catFactory = new CatFactory();

        System.out.println("Dog says: " + dogFactory.getAnimalSound());
        System.out.println("Cat says: " + catFactory.getAnimalSound());
    }
}
