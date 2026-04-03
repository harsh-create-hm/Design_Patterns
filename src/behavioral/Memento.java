package behavioral;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Memento Pattern
 *
 * Intent: Without violating encapsulation, capture and externalize an object's internal state
 * so that the object can be restored to this state later.
 *
 * Use when:
 * - A snapshot of an object's state must be saved so it can be restored later.
 * - A direct interface to obtaining the state would expose implementation details.
 */
public class Memento {

    // Memento – stores the originator's internal state
    static class EditorMemento {
        private final String text;
        private final int cursorPosition;

        EditorMemento(String text, int cursorPosition) {
            this.text = text;
            this.cursorPosition = cursorPosition;
        }

        public String getText()           { return text; }
        public int getCursorPosition()    { return cursorPosition; }
    }

    // Originator – creates and uses mementos
    static class TextEditor {
        private String text = "";
        private int cursorPosition = 0;

        public void type(String words) {
            text = text.substring(0, cursorPosition) + words + text.substring(cursorPosition);
            cursorPosition += words.length();
        }

        public void moveCursor(int position) {
            cursorPosition = Math.max(0, Math.min(position, text.length()));
        }

        public EditorMemento save() {
            return new EditorMemento(text, cursorPosition);
        }

        public void restore(EditorMemento memento) {
            text           = memento.getText();
            cursorPosition = memento.getCursorPosition();
        }

        public String getState() {
            return "Text: \"" + text + "\" | Cursor: " + cursorPosition;
        }
    }

    // Caretaker – holds a history of mementos
    static class History {
        private Deque<EditorMemento> stack = new ArrayDeque<>();

        public void push(EditorMemento memento) {
            stack.push(memento);
        }

        public EditorMemento pop() {
            return stack.isEmpty() ? null : stack.pop();
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Memento pattern demo:");
        System.out.println();

        TextEditor editor = new TextEditor();
        History history = new History();

        editor.type("Hello");
        history.push(editor.save());
        System.out.println("After typing 'Hello':  " + editor.getState());

        editor.type(", World");
        history.push(editor.save());
        System.out.println("After typing ', World': " + editor.getState());

        editor.type("!");
        System.out.println("After typing '!':       " + editor.getState());

        System.out.println("\nUndo:");
        editor.restore(history.pop());
        System.out.println("After undo:             " + editor.getState());

        System.out.println("\nUndo again:");
        editor.restore(history.pop());
        System.out.println("After undo:             " + editor.getState());
    }
}
