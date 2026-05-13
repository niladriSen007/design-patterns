package questions.librarymanagement.entity;

import questions.librarymanagement.observer.Member;
import questions.librarymanagement.state.AvailableState;
import questions.librarymanagement.state.ItemState;

public class BookOrMagazineCopy {
    private final String id;
    private final LibraryItem item;
    private ItemState currentState;

    public BookOrMagazineCopy(String id, LibraryItem item) {
        this.id = id;
        this.item = item;
        this.currentState = new AvailableState();
        item.addCopy(this);
    }

    public void changeCurrentState(ItemState itemState) {
        this.currentState = itemState;
    }

    public String getId() {
        return id;
    }

    public LibraryItem getItem() {
        return item;
    }

    public boolean isCopyAvailable(BookOrMagazineCopy copy) {
        return copy.currentState instanceof AvailableState;
    }

    public void returnItem(BookOrMagazineCopy copy) {
        if (isCopyAvailable(copy)) {
            throw new RuntimeException("Cannot return an item that is already available");
        }
        currentState.returnItem(copy);
    }

    public void holdItem(BookOrMagazineCopy copy, Member  member) {
       currentState.holdItem(copy, member);
    }

    public void checkoutItem(BookOrMagazineCopy copy,Member  member) {
        currentState.checkoutItem(copy, member);
    }
}
