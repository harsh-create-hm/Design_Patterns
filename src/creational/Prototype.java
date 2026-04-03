package creational;

/**
 * Prototype Pattern
 *
 * Intent: Specify the kinds of objects to create using a prototypical instance,
 * and create new objects by copying this prototype.
 *
 * Use when:
 * - The classes to instantiate are specified at runtime.
 * - Avoiding the inherent cost of creating a new object in the standard way is important.
 * - Instances of a class can have only one of a few different combinations of state.
 */
public class Prototype {

    // Prototype interface
    interface Shape extends Cloneable {
        Shape clone();
        void draw();
    }

    // Concrete prototypes
    static class Circle implements Shape {
        private int radius;
        private String color;

        Circle(int radius, String color) {
            this.radius = radius;
            this.color = color;
        }

        // Copy constructor used by clone
        Circle(Circle source) {
            this.radius = source.radius;
            this.color = source.color;
        }

        @Override
        public Shape clone() {
            return new Circle(this);
        }

        @Override
        public void draw() {
            System.out.println("Drawing Circle [radius=" + radius + ", color=" + color + "]");
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    static class Rectangle implements Shape {
        private int width;
        private int height;
        private String color;

        Rectangle(int width, int height, String color) {
            this.width = width;
            this.height = height;
            this.color = color;
        }

        // Copy constructor used by clone
        Rectangle(Rectangle source) {
            this.width = source.width;
            this.height = source.height;
            this.color = source.color;
        }

        @Override
        public Shape clone() {
            return new Rectangle(this);
        }

        @Override
        public void draw() {
            System.out.println("Drawing Rectangle [width=" + width + ", height=" + height +
                               ", color=" + color + "]");
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Prototype pattern demo:");

        Circle originalCircle = new Circle(10, "Red");
        Circle clonedCircle = (Circle) originalCircle.clone();
        clonedCircle.setColor("Blue");

        System.out.print("Original: ");
        originalCircle.draw();
        System.out.print("Clone:    ");
        clonedCircle.draw();

        System.out.println();

        Rectangle originalRect = new Rectangle(20, 15, "Green");
        Rectangle clonedRect = (Rectangle) originalRect.clone();
        clonedRect.setColor("Yellow");

        System.out.print("Original: ");
        originalRect.draw();
        System.out.print("Clone:    ");
        clonedRect.draw();
    }
}
