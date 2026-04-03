package structural;

/**
 * Bridge Pattern
 *
 * Intent: Decouple an abstraction from its implementation so that the two can vary independently.
 *
 * Use when:
 * - You want to avoid a permanent binding between an abstraction and its implementation.
 * - Both the abstractions and their implementations should be extensible using subclassing.
 */
public class Bridge {

    // Implementation interface (Implementor)
    interface DrawingAPI {
        void drawCircle(double x, double y, double radius);
        void drawRectangle(double x, double y, double width, double height);
    }

    // Concrete implementations
    static class SVGDrawingAPI implements DrawingAPI {
        @Override
        public void drawCircle(double x, double y, double radius) {
            System.out.printf("SVG: <circle cx=\"%.1f\" cy=\"%.1f\" r=\"%.1f\"/>%n", x, y, radius);
        }

        @Override
        public void drawRectangle(double x, double y, double width, double height) {
            System.out.printf("SVG: <rect x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" height=\"%.1f\"/>%n",
                              x, y, width, height);
        }
    }

    static class CanvasDrawingAPI implements DrawingAPI {
        @Override
        public void drawCircle(double x, double y, double radius) {
            System.out.printf("Canvas: ctx.arc(%.1f, %.1f, %.1f, 0, 2*Math.PI)%n", x, y, radius);
        }

        @Override
        public void drawRectangle(double x, double y, double width, double height) {
            System.out.printf("Canvas: ctx.rect(%.1f, %.1f, %.1f, %.1f)%n", x, y, width, height);
        }
    }

    // Abstraction
    static abstract class Shape {
        protected DrawingAPI drawingAPI;

        Shape(DrawingAPI drawingAPI) {
            this.drawingAPI = drawingAPI;
        }

        public abstract void draw();
        public abstract void resize(double factor);
    }

    // Refined abstractions
    static class CircleShape extends Shape {
        private double x, y, radius;

        CircleShape(double x, double y, double radius, DrawingAPI drawingAPI) {
            super(drawingAPI);
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        @Override
        public void draw() {
            drawingAPI.drawCircle(x, y, radius);
        }

        @Override
        public void resize(double factor) {
            radius *= factor;
        }
    }

    static class RectangleShape extends Shape {
        private double x, y, width, height;

        RectangleShape(double x, double y, double width, double height, DrawingAPI drawingAPI) {
            super(drawingAPI);
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw() {
            drawingAPI.drawRectangle(x, y, width, height);
        }

        @Override
        public void resize(double factor) {
            width *= factor;
            height *= factor;
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Bridge pattern demo:");

        DrawingAPI svg = new SVGDrawingAPI();
        DrawingAPI canvas = new CanvasDrawingAPI();

        Shape circle = new CircleShape(5, 5, 10, svg);
        Shape rectangle = new RectangleShape(0, 0, 20, 15, canvas);

        System.out.println("\nDrawing with original sizes:");
        circle.draw();
        rectangle.draw();

        circle.resize(2.0);
        rectangle.resize(0.5);

        System.out.println("\nDrawing after resize:");
        circle.draw();
        rectangle.draw();

        // Switch implementations without changing abstraction
        Shape circleOnCanvas = new CircleShape(5, 5, 10, canvas);
        System.out.println("\nSame circle on Canvas:");
        circleOnCanvas.draw();
    }
}
