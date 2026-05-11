package structural.composite;

import java.util.ArrayList;
import java.util.List;

class File {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void printStructure(String indent) {
        System.out.println(indent + name);
    }

    public void delete() {
        System.out.println("Deleting file: " + name);
    }
}

class Folder {
    private String name;
    private List<Object> contents = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addContent(Object obj) {
        contents.add(obj);
    }

    public int getSize() {
        int total = 0;
        for (Object o : contents) {
            if (o instanceof File) {
                total += ((File) o).getSize();
            } else {
                total += ((Folder) o).getSize();
            }
        }
        return total;
    }

    public void printStructure(String indent) {
        System.out.println(indent + name + "/");
        for (Object item : contents) {
            if (item instanceof File) {
                ((File) item).printStructure(indent + "  ");
            } else {
                ((Folder) item).printStructure(indent + "  ");
            }
        }
    }

    public void delete() {
        for (Object item : contents) {
            if (item instanceof File) {
                ((File) item).delete();
            } else if (item instanceof Folder) {
                ((Folder) item).delete();
            }
        }
        System.out.println("Deleting folder: " + name);
    }

}

public class WhyComposite {
    static void main() {

    }
}
