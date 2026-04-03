package behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor Pattern
 *
 * Intent: Represent an operation to be performed on the elements of an object structure.
 * Visitor lets you define a new operation without changing the classes of the elements on which it operates.
 *
 * Use when:
 * - An object structure contains many classes of objects with differing interfaces,
 *   and you want to perform operations on these objects that depend on their concrete classes.
 * - Many distinct and unrelated operations need to be performed on objects in a structure,
 *   and you want to avoid "polluting" their classes.
 */
public class Visitor {

    // Visitor interface
    interface ShapeVisitor {
        void visit(Circle circle);
        void visit(Rectangle rectangle);
        void visit(Triangle triangle);
    }

    // Element interface
    interface Shape {
        void accept(ShapeVisitor visitor);
    }

    // Concrete elements
    static class Circle implements Shape {
        double radius;
        Circle(double radius) { this.radius = radius; }

        @Override
        public void accept(ShapeVisitor visitor) { visitor.visit(this); }
    }

    static class Rectangle implements Shape {
        double width, height;
        Rectangle(double width, double height) { this.width = width; this.height = height; }

        @Override
        public void accept(ShapeVisitor visitor) { visitor.visit(this); }
    }

    static class Triangle implements Shape {
        double base, height;
        Triangle(double base, double height) { this.base = base; this.height = height; }

        @Override
        public void accept(ShapeVisitor visitor) { visitor.visit(this); }
    }

    // Concrete visitor 1 – area calculation
    static class AreaCalculator implements ShapeVisitor {
        private double totalArea = 0;

        @Override
        public void visit(Circle circle) {
            double area = Math.PI * circle.radius * circle.radius;
            System.out.printf("Circle area   = %.2f%n", area);
            totalArea += area;
        }

        @Override
        public void visit(Rectangle rectangle) {
            double area = rectangle.width * rectangle.height;
            System.out.printf("Rectangle area = %.2f%n", area);
            totalArea += area;
        }

        @Override
        public void visit(Triangle triangle) {
            double area = 0.5 * triangle.base * triangle.height;
            System.out.printf("Triangle area  = %.2f%n", area);
            totalArea += area;
        }

        public double getTotalArea() { return totalArea; }
    }

    // Concrete visitor 2 – description
    static class ShapeDescriber implements ShapeVisitor {
        @Override
        public void visit(Circle circle) {
            System.out.println("Circle with radius " + circle.radius);
        }

        @Override
        public void visit(Rectangle rectangle) {
            System.out.println("Rectangle " + rectangle.width + " x " + rectangle.height);
        }

        @Override
        public void visit(Triangle triangle) {
            System.out.println("Triangle base=" + triangle.base + ", height=" + triangle.height);
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Visitor pattern demo:");

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(4, 6));
        shapes.add(new Triangle(3, 8));

        System.out.println("\nDescriptions:");
        ShapeDescriber describer = new ShapeDescriber();
        for (Shape shape : shapes) {
            shape.accept(describer);
        }

        System.out.println("\nArea calculation:");
        AreaCalculator calculator = new AreaCalculator();
        for (Shape shape : shapes) {
            shape.accept(calculator);
        }
        System.out.printf("Total area     = %.2f%n", calculator.getTotalArea());
    }
}
