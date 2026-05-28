This is the repository where are creating and practicing the design patterns for the Low level System design.

# Project Structure
- Build: IntelliJ IDEA plain Java module (`design-patterns.iml`) — no Maven/Gradle
- Source root: `src/` (package root, no test source)
- LLD practice questions: `src/questions/{name}/` → packages as `questions.{name}.{layer}`
- Pattern demos: `src/structural/` and `src/behavioural/` (Why*.java + ThisIsWhy*.java pairs)

# Question Layer Conventions
Each `src/questions/{name}/` follows: `entity/`, `strategy/`, `observer/`, `singleton/`, `facade/`, `exceptions/`
Entry point: `Client.java` at the question root

# Completed Questions
- tictactoe, parkinglot, librarymanagement, lrucache (committed)
- elevator (in progress — entity/strategy/observer done, facade + client pending)

# Design Patterns

1. **Singleton Pattern**: Ensures a class has only one instance and provides a global point of access to it.
```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

2. **Factory Pattern**: Defines an interface for creating an object, but lets subclasses decide which class to instantiate.
```java
public interface Shape {
    void draw();
}
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}
public class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) return null;
        if (shapeType.equalsIgnoreCase("CIRCLE")) return new Circle();
        return null;
    }
}
```

3. **Observer Pattern**: Defines a one-to-many dependency so that when one object changes state, all dependents are notified automatically.
```java
import java.util.ArrayList;
import java.util.List;

public interface Observer {
    void update(String event);
}
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o) { observers.add(o); }
    public void notifyObservers(String event) {
        for (Observer o : observers) o.update(event);
    }
}
```
