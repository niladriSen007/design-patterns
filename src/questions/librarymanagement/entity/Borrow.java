package questions.librarymanagement.entity;

import questions.librarymanagement.observer.Member;

import java.time.LocalDate;

public class Borrow {
    private final BookOrMagazineCopy bookOrMagazineCopy;
    private final Member  member;
    private final LocalDate checkoutDate;

    public Borrow(BookOrMagazineCopy bookOrMagazineCopy, Member member) {
        this.bookOrMagazineCopy = bookOrMagazineCopy;
        this.member = member;
        this.checkoutDate = LocalDate.now();
    }

    public BookOrMagazineCopy getBookOrMagazineCopy() {
        return bookOrMagazineCopy;
    }

    public Member getMember() {
        return member;
    }
}
