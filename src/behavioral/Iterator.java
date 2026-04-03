package behavioral;

/**
 * Iterator Pattern
 *
 * Intent: Provide a way to access the elements of an aggregate object sequentially
 * without exposing its underlying representation.
 *
 * Use when:
 * - You want to access an aggregate object's contents without exposing its internal representation.
 * - You want to support multiple simultaneous traversals of aggregate objects.
 */
public class Iterator {

    // Iterator interface
    interface BookIterator {
        boolean hasNext();
        String next();
    }

    // Aggregate interface
    interface BookCollection {
        BookIterator createIterator();
    }

    // Concrete aggregate
    static class BookShelf implements BookCollection {
        private String[] books;
        private int count = 0;

        BookShelf(int maxSize) {
            books = new String[maxSize];
        }

        public void addBook(String title) {
            if (count < books.length) {
                books[count++] = title;
            }
        }

        public int getCount() {
            return count;
        }

        @Override
        public BookIterator createIterator() {
            return new BookShelfIterator(this);
        }

        public String getBookAt(int index) {
            return books[index];
        }
    }

    // Concrete iterator
    static class BookShelfIterator implements BookIterator {
        private BookShelf shelf;
        private int current = 0;

        BookShelfIterator(BookShelf shelf) {
            this.shelf = shelf;
        }

        @Override
        public boolean hasNext() {
            return current < shelf.getCount();
        }

        @Override
        public String next() {
            return shelf.getBookAt(current++);
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Iterator pattern demo:");
        System.out.println();

        BookShelf shelf = new BookShelf(10);
        shelf.addBook("Design Patterns – GoF");
        shelf.addBook("Clean Code");
        shelf.addBook("Refactoring");
        shelf.addBook("The Pragmatic Programmer");
        shelf.addBook("Head First Design Patterns");

        System.out.println("Books on the shelf:");
        BookIterator it = shelf.createIterator();
        int index = 1;
        while (it.hasNext()) {
            System.out.println(index++ + ". " + it.next());
        }
    }
}
