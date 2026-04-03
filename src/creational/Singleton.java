package creational;

/**
 * Singleton Pattern
 *
 * Intent: Ensure a class has only one instance and provide a global point of access to it.
 *
 * Use when:
 * - There must be exactly one instance of a class, accessible from a well-known access point.
 * - The sole instance should be extensible by subclassing.
 */
public class Singleton {

    // The single instance, created lazily and made volatile for thread safety
    private static volatile Singleton instance;

    private String value;

    // Private constructor prevents direct instantiation
    private Singleton(String value) {
        this.value = value;
    }

    /**
     * Returns the single instance, creating it on first call (double-checked locking).
     */
    public static Singleton getInstance(String value) {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton(value);
                }
            }
        }
        return instance;
    }

    public String getValue() {
        return value;
    }

    // --- Demo ---
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance("First instance");
        Singleton s2 = Singleton.getInstance("Second instance");

        System.out.println("Singleton pattern demo:");
        System.out.println("s1 value: " + s1.getValue());
        System.out.println("s2 value: " + s2.getValue());
        System.out.println("s1 == s2 (same instance)? " + (s1 == s2));
    }
}
