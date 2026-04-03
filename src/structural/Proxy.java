package structural;

/**
 * Proxy Pattern
 *
 * Intent: Provide a surrogate or placeholder for another object to control access to it.
 *
 * Use when:
 * - You need a more versatile or sophisticated reference to an object than a simple pointer.
 * - You want lazy initialization, access control, logging, or caching around an object.
 */
public class Proxy {

    // Subject interface
    interface Image {
        void display();
    }

    // Real subject – expensive to create
    static class RealImage implements Image {
        private String filename;

        RealImage(String filename) {
            this.filename = filename;
            loadFromDisk();
        }

        private void loadFromDisk() {
            System.out.println("Loading image from disk: " + filename);
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    // Proxy – lazy initialization + access control example
    static class ImageProxy implements Image {
        private String filename;
        private RealImage realImage;
        private boolean accessGranted;

        ImageProxy(String filename, boolean accessGranted) {
            this.filename = filename;
            this.accessGranted = accessGranted;
        }

        @Override
        public void display() {
            if (!accessGranted) {
                System.out.println("Access denied for image: " + filename);
                return;
            }
            // Lazy initialization: load only when needed
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Proxy pattern demo:");
        System.out.println();

        // Image is NOT loaded until display() is called (lazy init)
        Image img1 = new ImageProxy("photo1.jpg", true);
        Image img2 = new ImageProxy("photo2.jpg", true);
        Image restricted = new ImageProxy("secret.jpg", false);

        System.out.println("First display of img1 (loads from disk):");
        img1.display();

        System.out.println("\nSecond display of img1 (already loaded, no disk access):");
        img1.display();

        System.out.println("\nDisplaying img2:");
        img2.display();

        System.out.println("\nAttempting to display restricted image:");
        restricted.display();
    }
}
