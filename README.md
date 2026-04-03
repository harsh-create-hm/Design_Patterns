# Design Patterns in Java

A collection of all 23 classic **Gang of Four (GoF)** Design Patterns implemented in Java, each in its own separate file with a clear example and a runnable `main` method.

---

## Directory Structure

```
src/
├── creational/          # Patterns for object creation
│   ├── Singleton.java
│   ├── FactoryMethod.java
│   ├── AbstractFactory.java
│   ├── Builder.java
│   └── Prototype.java
├── structural/          # Patterns for composing classes/objects
│   ├── Adapter.java
│   ├── Bridge.java
│   ├── Composite.java
│   ├── Decorator.java
│   ├── Facade.java
│   ├── Flyweight.java
│   └── Proxy.java
└── behavioral/          # Patterns for object interaction/responsibility
    ├── ChainOfResponsibility.java
    ├── Command.java
    ├── Interpreter.java
    ├── Iterator.java
    ├── Mediator.java
    ├── Memento.java
    ├── Observer.java
    ├── State.java
    ├── Strategy.java
    ├── TemplateMethod.java
    └── Visitor.java
```

---

## Creational Patterns

| Pattern | Intent |
|---------|--------|
| **Singleton** | Ensure a class has only one instance and provide a global access point to it. |
| **Factory Method** | Define an interface for creating an object, but let subclasses decide which class to instantiate. |
| **Abstract Factory** | Provide an interface for creating families of related objects without specifying concrete classes. |
| **Builder** | Separate the construction of a complex object from its representation. |
| **Prototype** | Create new objects by copying an existing (prototype) instance. |

---

## Structural Patterns

| Pattern | Intent |
|---------|--------|
| **Adapter** | Convert the interface of a class into another interface clients expect. |
| **Bridge** | Decouple an abstraction from its implementation so that the two can vary independently. |
| **Composite** | Compose objects into tree structures to represent part-whole hierarchies. |
| **Decorator** | Attach additional responsibilities to an object dynamically. |
| **Facade** | Provide a unified interface to a set of interfaces in a subsystem. |
| **Flyweight** | Use sharing to support large numbers of fine-grained objects efficiently. |
| **Proxy** | Provide a surrogate or placeholder for another object to control access to it. |

---

## Behavioral Patterns

| Pattern | Intent |
|---------|--------|
| **Chain of Responsibility** | Pass a request along a chain of handlers until one handles it. |
| **Command** | Encapsulate a request as an object to support undoable operations and queuing. |
| **Interpreter** | Define a grammar and an interpreter for sentences in that language. |
| **Iterator** | Provide a way to access elements of a collection without exposing its representation. |
| **Mediator** | Define an object that encapsulates how a set of objects interact. |
| **Memento** | Capture and restore an object's internal state without violating encapsulation. |
| **Observer** | Define a one-to-many dependency so dependents are notified on state change. |
| **State** | Allow an object to alter its behavior when its internal state changes. |
| **Strategy** | Define a family of algorithms, encapsulate each, and make them interchangeable. |
| **Template Method** | Define the skeleton of an algorithm, deferring some steps to subclasses. |
| **Visitor** | Represent an operation on elements of a structure without changing their classes. |

---

## How to Compile and Run

Each file is self-contained with a `main` method. Compile from the `src/` directory:

```bash
# Compile all patterns
javac creational/*.java structural/*.java behavioral/*.java

# Run a specific pattern
java creational.Singleton
java creational.FactoryMethod
java creational.AbstractFactory
java creational.Builder
java creational.Prototype

java structural.Adapter
java structural.Bridge
java structural.Composite
java structural.Decorator
java structural.Facade
java structural.Flyweight
java structural.Proxy

java behavioral.ChainOfResponsibility
java behavioral.Command
java behavioral.Interpreter
java behavioral.Iterator
java behavioral.Mediator
java behavioral.Memento
java behavioral.Observer
java behavioral.State
java behavioral.Strategy
java behavioral.TemplateMethod
java behavioral.Visitor
```