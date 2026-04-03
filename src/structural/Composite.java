package structural;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern
 *
 * Intent: Compose objects into tree structures to represent part-whole hierarchies.
 * Composite lets clients treat individual objects and compositions of objects uniformly.
 *
 * Use when:
 * - You want to represent part-whole hierarchies of objects.
 * - You want clients to be able to ignore the difference between compositions of objects
 *   and individual objects.
 */
public class Composite {

    // Component interface
    interface FileSystemItem {
        String getName();
        int getSize();
        void print(String indent);
    }

    // Leaf
    static class File implements FileSystemItem {
        private String name;
        private int size;

        File(String name, int size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public void print(String indent) {
            System.out.println(indent + "File: " + name + " (" + size + " KB)");
        }
    }

    // Composite
    static class Directory implements FileSystemItem {
        private String name;
        private List<FileSystemItem> children = new ArrayList<>();

        Directory(String name) {
            this.name = name;
        }

        public void add(FileSystemItem item) {
            children.add(item);
        }

        public void remove(FileSystemItem item) {
            children.remove(item);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getSize() {
            int totalSize = 0;
            for (FileSystemItem item : children) {
                totalSize += item.getSize();
            }
            return totalSize;
        }

        @Override
        public void print(String indent) {
            System.out.println(indent + "Directory: " + name + " (" + getSize() + " KB total)");
            for (FileSystemItem item : children) {
                item.print(indent + "  ");
            }
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Composite pattern demo:");

        // Build a file system tree
        Directory root = new Directory("root");

        Directory src = new Directory("src");
        src.add(new File("Main.java", 5));
        src.add(new File("Utils.java", 3));

        Directory test = new Directory("test");
        test.add(new File("MainTest.java", 4));

        Directory docs = new Directory("docs");
        docs.add(new File("README.md", 2));
        docs.add(new File("API.md", 8));

        root.add(src);
        root.add(test);
        root.add(docs);
        root.add(new File("build.gradle", 1));

        System.out.println();
        root.print("");
        System.out.println("\nTotal root size: " + root.getSize() + " KB");
    }
}
