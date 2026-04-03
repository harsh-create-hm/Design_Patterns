package structural;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Pattern
 *
 * Intent: Use sharing to support large numbers of fine-grained objects efficiently.
 *
 * Use when:
 * - An application uses a large number of objects.
 * - Storage costs are high because of the sheer quantity of objects.
 * - Most object state can be made extrinsic (moved outside the object).
 */
public class Flyweight {

    // Flyweight – stores intrinsic (shared) state
    static class TreeType {
        private String name;
        private String color;
        private String texture;

        TreeType(String name, String color, String texture) {
            this.name = name;
            this.color = color;
            this.texture = texture;
        }

        // extrinsic state (x, y) passed in at draw time
        public void draw(int x, int y) {
            System.out.printf("Drawing %s tree [color=%s, texture=%s] at (%d, %d)%n",
                              name, color, texture, x, y);
        }
    }

    // Flyweight factory – manages the pool of shared flyweights
    static class TreeTypeFactory {
        private Map<String, TreeType> treeTypes = new HashMap<>();

        public TreeType getTreeType(String name, String color, String texture) {
            String key = name + "_" + color + "_" + texture;
            return treeTypes.computeIfAbsent(key, k -> {
                System.out.println("Creating new TreeType: " + name);
                return new TreeType(name, color, texture);
            });
        }

        public int getTypesCount() {
            return treeTypes.size();
        }
    }

    // Context – stores extrinsic state and a reference to the flyweight
    static class Tree {
        private int x;
        private int y;
        private TreeType type;

        Tree(int x, int y, TreeType type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public void draw() {
            type.draw(x, y);
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Flyweight pattern demo:");
        System.out.println();

        TreeTypeFactory factory = new TreeTypeFactory();

        // Plant many trees of just a few types – types are shared
        Tree[] forest = {
            new Tree(1,  2,  factory.getTreeType("Oak",  "Green",  "Rough")),
            new Tree(5,  8,  factory.getTreeType("Pine", "DarkGreen", "Smooth")),
            new Tree(10, 3,  factory.getTreeType("Oak",  "Green",  "Rough")),
            new Tree(15, 7,  factory.getTreeType("Pine", "DarkGreen", "Smooth")),
            new Tree(20, 12, factory.getTreeType("Oak",  "Green",  "Rough")),
            new Tree(25, 5,  factory.getTreeType("Birch","White",  "Peeling")),
        };

        System.out.println();
        System.out.println("Drawing forest (" + forest.length + " trees, " +
                           factory.getTypesCount() + " unique types):");
        for (Tree tree : forest) {
            tree.draw();
        }
    }
}
