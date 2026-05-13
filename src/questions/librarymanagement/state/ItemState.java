package questions.librarymanagement.state;

import questions.librarymanagement.entity.BookOrMagazineCopy;
import questions.librarymanagement.observer.Member;

public interface ItemState {
    void checkoutItem(BookOrMagazineCopy copy, Member member);

    void returnItem(BookOrMagazineCopy copy);

    void holdItem(BookOrMagazineCopy copy, Member member);
}
