package creational;

/**
 * Abstract Factory Pattern
 *
 * Intent: Provide an interface for creating families of related or dependent objects
 * without specifying their concrete classes.
 *
 * Use when:
 * - A system should be independent of how its products are created, composed, and represented.
 * - A system should be configured with one of multiple families of products.
 */
public class AbstractFactory {

    // Abstract product interfaces
    interface Button {
        void render();
    }

    interface Checkbox {
        void render();
    }

    // Windows family
    static class WindowsButton implements Button {
        @Override
        public void render() {
            System.out.println("Rendering Windows Button");
        }
    }

    static class WindowsCheckbox implements Checkbox {
        @Override
        public void render() {
            System.out.println("Rendering Windows Checkbox");
        }
    }

    // MacOS family
    static class MacButton implements Button {
        @Override
        public void render() {
            System.out.println("Rendering Mac Button");
        }
    }

    static class MacCheckbox implements Checkbox {
        @Override
        public void render() {
            System.out.println("Rendering Mac Checkbox");
        }
    }

    // Abstract factory
    interface GUIFactory {
        Button createButton();
        Checkbox createCheckbox();
    }

    // Concrete factories
    static class WindowsFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return new WindowsButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new WindowsCheckbox();
        }
    }

    static class MacFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return new MacButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new MacCheckbox();
        }
    }

    // Client code
    static class Application {
        private Button button;
        private Checkbox checkbox;

        Application(GUIFactory factory) {
            button = factory.createButton();
            checkbox = factory.createCheckbox();
        }

        void renderUI() {
            button.render();
            checkbox.render();
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Abstract Factory pattern demo:");

        System.out.println("\nWindows UI:");
        Application windowsApp = new Application(new WindowsFactory());
        windowsApp.renderUI();

        System.out.println("\nMac UI:");
        Application macApp = new Application(new MacFactory());
        macApp.renderUI();
    }
}
