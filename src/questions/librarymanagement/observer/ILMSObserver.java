package questions.librarymanagement.observer;

import questions.librarymanagement.entity.LibraryItem;

public interface ILMSObserver {
    void update(LibraryItem item);
}
