package behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * Mediator Pattern
 *
 * Intent: Define an object that encapsulates how a set of objects interact.
 * Mediator promotes loose coupling by keeping objects from referring to each other explicitly.
 *
 * Use when:
 * - A set of objects communicate in well-defined but complex ways.
 * - Reusing an object is difficult because it refers to and communicates with many other objects.
 */
public class Mediator {

    // Mediator interface
    interface ChatMediator {
        void sendMessage(String message, User sender);
        void addUser(User user);
    }

    // Colleague
    static abstract class User {
        protected ChatMediator mediator;
        protected String name;

        User(ChatMediator mediator, String name) {
            this.mediator = mediator;
            this.name = name;
        }

        public String getName() { return name; }
        public abstract void send(String message);
        public abstract void receive(String message, String from);
    }

    // Concrete mediator
    static class ChatRoom implements ChatMediator {
        private List<User> users = new ArrayList<>();

        @Override
        public void addUser(User user) {
            users.add(user);
        }

        @Override
        public void sendMessage(String message, User sender) {
            for (User user : users) {
                if (user != sender) {
                    user.receive(message, sender.getName());
                }
            }
        }
    }

    // Concrete colleague
    static class ChatUser extends User {
        ChatUser(ChatMediator mediator, String name) {
            super(mediator, name);
        }

        @Override
        public void send(String message) {
            System.out.println(name + " sends: " + message);
            mediator.sendMessage(message, this);
        }

        @Override
        public void receive(String message, String from) {
            System.out.println(name + " receives from " + from + ": " + message);
        }
    }

    // --- Demo ---
    public static void main(String[] args) {
        System.out.println("Mediator pattern demo:");
        System.out.println();

        ChatMediator chatRoom = new ChatRoom();

        User alice = new ChatUser(chatRoom, "Alice");
        User bob   = new ChatUser(chatRoom, "Bob");
        User carol = new ChatUser(chatRoom, "Carol");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(carol);

        alice.send("Hello everyone!");
        System.out.println();
        bob.send("Hi Alice!");
        System.out.println();
        carol.send("Good morning!");
    }
}
