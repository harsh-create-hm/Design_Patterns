package behavioral;

/**
 * Template Method Pattern
 *
 * Intent: Define the skeleton of an algorithm in an operation, deferring some steps to subclasses.
 * Template Method lets subclasses redefine certain steps of an algorithm without changing
 * the algorithm's structure.
 *
 * Use when:
 * - You want to implement the invariant parts of an algorithm once and let subclasses implement
 *   the varying behavior.
 * - When common behavior in subclasses should be factored and localized in a common class.
 */
public class TemplateMethod {

    // Abstract class defining the template method
    static abstract class DataProcessor {

        // Template method – defines the skeleton of the algorithm
        public final void process() {
            readData();
            processData();
            writeResult();
        }

        // Steps to be implemented by subclasses
        protected abstract void readData();
        protected abstract void processData();

        // Hook – subclasses may optionally override this
        protected void writeResult() {
            System.out.println("Writing result to default output.");
        }
    }

    // Concrete class 1 – CSV processing
    static class CsvDataProcessor extends DataProcessor {
        @Override
        protected void readData() {
            System.out.println("Reading data from CSV file.");
        }

        @Override
        protected void processData() {
            System.out.println("Parsing and processing CSV data.");
        }

        @Override
        protected void writeResult() {
            System.out.println("Writing processed CSV result to database.");
        }
    }

    // Concrete class 2 – JSON processing
    static class JsonDataProcessor extends DataProcessor {
        @Override
        protected void readData() {
            System.out.println("Reading data from JSON API.");
        }

        @Override
        protected void processData() {
            System.out.println("Deserializing and processing JSON data.");
        }

        // Uses the default writeResult (hook not overridden)
    }

    // Concrete class 3 – XML processing
    static class XmlDataProcessor extends DataProcessor {
        @Override
        protected void readData() {
            System.out.println("Reading data from XML file.");
        }

        @Override
        protected void processData() {
            System.out.println("Parsing XML document and processing nodes.");
        }

        @Override
        protected void writeResult() {
            System.out.println("Writing transformed XML to output file.");
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Template Method pattern demo:");

        DataProcessor[] processors = {
            new CsvDataProcessor(),
            new JsonDataProcessor(),
            new XmlDataProcessor()
        };

        for (DataProcessor processor : processors) {
            System.out.println("\n--- " + processor.getClass().getSimpleName() + " ---");
            processor.process();
        }
    }
}
