package behavioral;

import java.util.Arrays;
import java.util.List;

/**
 * Strategy Pattern
 *
 * Intent: Define a family of algorithms, encapsulate each one, and make them interchangeable.
 * Strategy lets the algorithm vary independently from clients that use it.
 *
 * Use when:
 * - Many related classes differ only in their behavior.
 * - You need different variants of an algorithm.
 * - An algorithm uses data that clients shouldn't know about.
 */
public class Strategy {

    // Strategy interface
    interface SortStrategy {
        void sort(int[] array);
        String name();
    }

    // Concrete strategies
    static class BubbleSort implements SortStrategy {
        @Override
        public void sort(int[] array) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }

        @Override
        public String name() { return "Bubble Sort"; }
    }

    static class SelectionSort implements SortStrategy {
        @Override
        public void sort(int[] array) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < n; j++) {
                    if (array[j] < array[minIdx]) minIdx = j;
                }
                int temp = array[minIdx];
                array[minIdx] = array[i];
                array[i] = temp;
            }
        }

        @Override
        public String name() { return "Selection Sort"; }
    }

    static class InsertionSort implements SortStrategy {
        @Override
        public void sort(int[] array) {
            int n = array.length;
            for (int i = 1; i < n; i++) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = key;
            }
        }

        @Override
        public String name() { return "Insertion Sort"; }
    }

    // Context
    static class Sorter {
        private SortStrategy strategy;

        Sorter(SortStrategy strategy) {
            this.strategy = strategy;
        }

        public void setStrategy(SortStrategy strategy) {
            this.strategy = strategy;
        }

        public int[] sort(int[] data) {
            int[] copy = Arrays.copyOf(data, data.length);
            strategy.sort(copy);
            return copy;
        }

        public String getStrategyName() {
            return strategy.name();
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Strategy pattern demo:");
        System.out.println();

        int[] data = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + Arrays.toString(data));

        List<SortStrategy> strategies = Arrays.asList(
            new BubbleSort(), new SelectionSort(), new InsertionSort()
        );

        Sorter sorter = new Sorter(strategies.get(0));
        for (SortStrategy strategy : strategies) {
            sorter.setStrategy(strategy);
            int[] sorted = sorter.sort(data);
            System.out.printf("%-20s -> %s%n", strategy.name(), Arrays.toString(sorted));
        }
    }
}
