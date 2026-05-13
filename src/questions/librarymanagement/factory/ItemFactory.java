package questions.librarymanagement.factory;

import questions.librarymanagement.entity.Book;
import questions.librarymanagement.entity.ItemType;
import questions.librarymanagement.entity.LibraryItem;
import questions.librarymanagement.entity.Magazine;

public class ItemFactory {
    public static LibraryItem createItem(ItemType itemType, String id, String title, String authorOrPublisher) {
        return switch (itemType) {
            case BOOK -> new Book(id, title, authorOrPublisher);
            case MAGAZINE -> new Magazine(id, title, authorOrPublisher);
            default -> throw new IllegalArgumentException("Unknown item type");
        };
    }
}
