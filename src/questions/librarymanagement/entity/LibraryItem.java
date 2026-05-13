package questions.librarymanagement.entity;

import questions.librarymanagement.observer.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LibraryItem {

    private final String id;
    private final String title;
    private final List<Member> observers;
    private final List<BookOrMagazineCopy> bookOrMagazineCopies;

    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.observers = new CopyOnWriteArrayList<>();
        this.bookOrMagazineCopies = new ArrayList<>();
    }

    public void addObserver(Member observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Member observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        System.out.println("Notifying " + observers.size() + " observers for '" + title + "'...");
        for (Member observer : observers) {
            observer.update(this);
        }
    }

    public void addCopy(BookOrMagazineCopy copy) {
        this.bookOrMagazineCopies.add(copy);
    }

    public BookOrMagazineCopy getAvailableCopy() {
        return bookOrMagazineCopies.stream()
                .filter(copy -> copy.isCopyAvailable(copy))
                .findFirst()
                .orElse(null);
    }

    public long getAvailableCopyCount() {
        return bookOrMagazineCopies.stream()
                .filter(copy -> copy.isCopyAvailable(copy))
                .count();
    }

    public boolean isObserver(Member observer) {
        return observers.contains(observer);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<BookOrMagazineCopy> getBookOrMagazineCopies() {
        return this.bookOrMagazineCopies;
    }

    public boolean hasObservers() {
        return !this.observers.isEmpty();
    }

    public abstract String getAuthorOrPublisher();
}
