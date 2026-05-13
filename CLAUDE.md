This is the repository where are creating and practicing the design patterns for the Low level System design.

# Design Patterns
1. **Singleton Pattern**: This pattern ensures that a class has only one instance and provides a global point of access to it. It is useful when exactly one object is needed to coordinate actions across the system.
```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Private constructor to prevent instantiation
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```2. **Factory Pattern**: This pattern defines an interface for creating an object, but allows subclasses to alter the type of objects that will be created. It is useful when the exact types of objects to create are not known until runtime.
```javapublic interface Shape {
    void draw();
}
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }
}
public class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();            
        }       
        return null;
    }
}```
3. **Observer Pattern**: This pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. It is useful when an object needs to notify other objects without making assumptions about who those objects are.
```javaimport java.util.ArrayList;
import java.util.List;