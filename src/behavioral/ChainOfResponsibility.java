package behavioral;

/**
 * Chain of Responsibility Pattern
 *
 * Intent: Avoid coupling the sender of a request to its receiver by giving more than one object
 * a chance to handle the request. Chain the receiving objects and pass the request along the
 * chain until an object handles it.
 *
 * Use when:
 * - More than one object may handle a request, and the handler isn't known a priori.
 * - You want to issue a request to one of several objects without specifying the receiver explicitly.
 */
public class ChainOfResponsibility {

    // Request type
    enum LogLevel { DEBUG, INFO, WARNING, ERROR }

    // Handler interface
    static abstract class Logger {
        protected LogLevel level;
        protected Logger next;

        Logger(LogLevel level) {
            this.level = level;
        }

        public Logger setNext(Logger next) {
            this.next = next;
            return next;
        }

        public void log(LogLevel requestLevel, String message) {
            if (requestLevel.ordinal() >= this.level.ordinal()) {
                write(message);
            }
            if (next != null) {
                next.log(requestLevel, message);
            }
        }

        protected abstract void write(String message);
    }

    // Concrete handlers
    static class ConsoleLogger extends Logger {
        ConsoleLogger() { super(LogLevel.DEBUG); }

        @Override
        protected void write(String message) {
            System.out.println("[Console] " + message);
        }
    }

    static class FileLogger extends Logger {
        FileLogger() { super(LogLevel.WARNING); }

        @Override
        protected void write(String message) {
            System.out.println("[File]    " + message);
        }
    }

    static class EmailLogger extends Logger {
        EmailLogger() { super(LogLevel.ERROR); }

        @Override
        protected void write(String message) {
            System.out.println("[Email]   " + message);
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Chain of Responsibility pattern demo:");
        System.out.println();

        // Build the chain: Console -> File -> Email
        Logger console = new ConsoleLogger();
        Logger file    = new FileLogger();
        Logger email   = new EmailLogger();
        console.setNext(file).setNext(email);

        System.out.println("Sending DEBUG message:");
        console.log(LogLevel.DEBUG, "Variable x = 42");

        System.out.println("\nSending INFO message:");
        console.log(LogLevel.INFO, "Application started");

        System.out.println("\nSending WARNING message:");
        console.log(LogLevel.WARNING, "Low memory");

        System.out.println("\nSending ERROR message:");
        console.log(LogLevel.ERROR, "Database connection failed");
    }
}
