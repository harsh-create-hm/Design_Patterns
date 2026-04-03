package behavioral;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Command Pattern
 *
 * Intent: Encapsulate a request as an object, thereby letting you parameterize clients with
 * different requests, queue or log requests, and support undoable operations.
 *
 * Use when:
 * - You want to parameterize objects with operations.
 * - You want to queue, specify, or execute requests at different times.
 * - You want to support undo/redo functionality.
 */
public class Command {

    // Receiver
    static class TextEditor {
        private StringBuilder text = new StringBuilder();

        public void insertText(String str) {
            text.append(str);
        }

        public void deleteText(int length) {
            if (length > text.length()) length = text.length();
            text.delete(text.length() - length, text.length());
        }

        public String getText() {
            return text.toString();
        }
    }

    // Command interface
    interface EditorCommand {
        void execute();
        void undo();
    }

    // Concrete commands
    static class InsertCommand implements EditorCommand {
        private TextEditor editor;
        private String text;

        InsertCommand(TextEditor editor, String text) {
            this.editor = editor;
            this.text = text;
        }

        @Override
        public void execute() {
            editor.insertText(text);
        }

        @Override
        public void undo() {
            editor.deleteText(text.length());
        }
    }

    static class DeleteCommand implements EditorCommand {
        private TextEditor editor;
        private int length;
        private String deleted;

        DeleteCommand(TextEditor editor, int length) {
            this.editor = editor;
            this.length = length;
        }

        @Override
        public void execute() {
            String current = editor.getText();
            int start = Math.max(0, current.length() - length);
            deleted = current.substring(start);
            editor.deleteText(length);
        }

        @Override
        public void undo() {
            editor.insertText(deleted);
        }
    }

    // Invoker – manages command history for undo/redo
    static class CommandInvoker {
        private Deque<EditorCommand> history = new ArrayDeque<>();

        public void executeCommand(EditorCommand command) {
            command.execute();
            history.push(command);
        }

        public void undo() {
            if (!history.isEmpty()) {
                history.pop().undo();
            } else {
                System.out.println("Nothing to undo.");
            }
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Command pattern demo:");
        System.out.println();

        TextEditor editor = new TextEditor();
        CommandInvoker invoker = new CommandInvoker();

        invoker.executeCommand(new InsertCommand(editor, "Hello"));
        System.out.println("After insert 'Hello': " + editor.getText());

        invoker.executeCommand(new InsertCommand(editor, ", World"));
        System.out.println("After insert ', World': " + editor.getText());

        invoker.executeCommand(new DeleteCommand(editor, 6));
        System.out.println("After delete 6 chars: " + editor.getText());

        invoker.undo();
        System.out.println("After undo: " + editor.getText());

        invoker.undo();
        System.out.println("After undo: " + editor.getText());

        invoker.undo();
        System.out.println("After undo: " + editor.getText());

        invoker.undo();   // nothing to undo
    }
}
