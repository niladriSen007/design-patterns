package questions.librarymanagement.facade;

import questions.librarymanagement.entity.BookOrMagazineCopy;
import questions.librarymanagement.entity.ItemType;
import questions.librarymanagement.entity.LibraryItem;
import questions.librarymanagement.factory.ItemFactory;
import questions.librarymanagement.observer.Member;
import questions.librarymanagement.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManagement {
    private static volatile LibraryManagement instance;
    private final Map<String, LibraryItem> catalog = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    private final Map<String, BookOrMagazineCopy> copies = new HashMap<>();

    private LibraryManagement() {
    }

    public static LibraryManagement getInstance() {
        if (instance == null) {
            synchronized (LibraryManagement.class) {
                if (instance == null) {
                    instance = new LibraryManagement();
                }
            }
        }
        return instance;
    }

    // --- Item Management ---
    public List<BookOrMagazineCopy> addItem(ItemType type, String id, String title, String author, int numCopies) {
        List<BookOrMagazineCopy> bookCopies = new ArrayList<>();
        LibraryItem item = ItemFactory.createItem(type, id, title, author);
        catalog.put(id, item);
        for (int i = 0; i < numCopies; i++) {
            String copyId = id + "-c" + (i + 1);
            BookOrMagazineCopy copy = new BookOrMagazineCopy(copyId, item);
            copies.put(copyId, new BookOrMagazineCopy(copyId, item));
            bookCopies.add(copy);
        }
        System.out.println("Added " + numCopies + " copies of '" + title + "'");
        return bookCopies;
    }

    // --- User Management ---
    public Member addMember(String id, String name) {
        Member member = new Member(id, name);
        members.put(id, member);
        return member;
    }

    // --- Core Actions ---
    public void checkout(String memberId, String copyId) {
        Member member = members.get(memberId);
        BookOrMagazineCopy copy = copies.get(copyId);
        if (member != null && copy != null) {
            copy.checkoutItem(copy, member);
        } else {
            System.out.println("Error: Invalid member or copy ID.");
        }
    }

    public void returnItem(String copyId) {
        BookOrMagazineCopy copy = copies.get(copyId);
        if (copy != null) {
            copy.returnItem(copy);
        } else {
            System.out.println("Error: Invalid copy ID.");
        }
    }

    public void placeHold(String memberId, String itemId) {
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);
        if (member != null && item != null) {
            // Place hold on any copy that is checked out
            item.getBookOrMagazineCopies().stream()
                    .filter(c -> !c.isCopyAvailable(c))
                    .findFirst()
                    .ifPresent(copy -> copy.holdItem(copy, member));
        }
    }

    // --- Search (Using Strategy Pattern) ---
    public List<LibraryItem> search(String query, SearchStrategy strategy) {
        return strategy.search(query, new ArrayList<>(catalog.values()));
    }

    public void printCatalog() {
        System.out.println("\n--- Library Catalog ---");
        catalog.values().forEach(item -> System.out.printf("ID: %s, Title: %s, Author/Publisher: %s, Available: %d\n",
                item.getId(), item.getTitle(), item.getAuthorOrPublisher(), item.getAvailableCopyCount()));
        System.out.println("-----------------------\n");
    }

}
