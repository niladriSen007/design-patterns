package structural.composite;

import java.util.ArrayList;
import java.util.List;

interface FileSystem {
    int getSize();

    void printStructure(String indent);

    void delete();
}

class FileII implements FileSystem {

    private final String name;
    private final int size;

    public FileII(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "- " + name + " (" + size + " KB)");
    }

    @Override
    public void delete() {
        System.out.println("Deleting file: " + name);
    }
}

class FolderII implements FileSystem {

    private final String name;
    private final List<FileSystem> children = new ArrayList<>();

    public FolderII(String name) {
        this.name = name;
    }

    public void addItem(FileSystem item) {
        children.add(item);
    }

    public void removeItem(FileSystem item) {
        children.remove(item);
    }


    @Override
    public int getSize() {
        int total = 0;
        for (FileSystem item : children) {
            total += item.getSize();
        }
        return total;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "+ " + name + "/");
        for (FileSystem item : children) {
            item.printStructure(indent + "  ");
        }
    }

    @Override
    public void delete() {
        for (FileSystem item : children) {
            item.delete();
        }
        System.out.println("Deleting folder: " + name);
    }
}

public class ThisIsWhyComposite {
    static void main() {
        FileSystem file1 = new FileII("readme.txt", 5);
        FileSystem file2 = new FileII("photo.jpg", 1500);
        FileSystem file3 = new FileII("data.csv", 300);

        FolderII documents = new FolderII("Documents");
        documents.addItem(file1);
        documents.addItem(file3);

        FolderII pictures = new FolderII("Pictures");
        pictures.addItem(file2);

        FolderII home = new FolderII("Home");
        home.addItem(documents);
        home.addItem(pictures);

        System.out.println("---- File Structure ----");
        home.printStructure("");

        System.out.println("\nTotal Size: " + home.getSize() + " KB");

        System.out.println("\n---- Deleting All ----");
        home.delete();
    }
}
