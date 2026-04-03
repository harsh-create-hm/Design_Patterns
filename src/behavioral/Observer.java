package behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern
 *
 * Intent: Define a one-to-many dependency between objects so that when one object changes state,
 * all its dependents are notified and updated automatically.
 *
 * Use when:
 * - A change to one object requires changing others, and you don't know how many objects need to change.
 * - An object should be able to notify other objects without making assumptions about who those objects are.
 */
public class Observer {

    // Observer interface
    interface StockObserver {
        void update(String stockName, double price);
    }

    // Subject (Observable)
    interface StockMarket {
        void subscribe(StockObserver observer);
        void unsubscribe(StockObserver observer);
        void notifyObservers();
    }

    // Concrete subject
    static class Stock implements StockMarket {
        private String name;
        private double price;
        private List<StockObserver> observers = new ArrayList<>();

        Stock(String name, double initialPrice) {
            this.name = name;
            this.price = initialPrice;
        }

        public void setPrice(double price) {
            this.price = price;
            notifyObservers();
        }

        @Override
        public void subscribe(StockObserver observer) {
            observers.add(observer);
        }

        @Override
        public void unsubscribe(StockObserver observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (StockObserver observer : observers) {
                observer.update(name, price);
            }
        }
    }

    // Concrete observers
    static class MobileApp implements StockObserver {
        private String appName;

        MobileApp(String appName) { this.appName = appName; }

        @Override
        public void update(String stockName, double price) {
            System.out.printf("[%s] Notification: %s is now $%.2f%n", appName, stockName, price);
        }
    }

    static class EmailAlert implements StockObserver {
        private String email;

        EmailAlert(String email) { this.email = email; }

        @Override
        public void update(String stockName, double price) {
            System.out.printf("[Email to %s] %s price changed to $%.2f%n", email, stockName, price);
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Observer pattern demo:");
        System.out.println();

        Stock apple = new Stock("AAPL", 150.00);

        StockObserver mobile1 = new MobileApp("TradingApp");
        StockObserver mobile2 = new MobileApp("WealthManager");
        StockObserver email   = new EmailAlert("investor@example.com");

        apple.subscribe(mobile1);
        apple.subscribe(mobile2);
        apple.subscribe(email);

        System.out.println("Price update to $155.00:");
        apple.setPrice(155.00);

        System.out.println("\nUnsubscribing WealthManager...");
        apple.unsubscribe(mobile2);

        System.out.println("\nPrice update to $160.00:");
        apple.setPrice(160.00);
    }
}
