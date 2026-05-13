package questions.librarymanagement.observer;

import questions.librarymanagement.entity.Borrow;
import questions.librarymanagement.entity.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private final String memberId;
    private final String memberName;
    private final List<Borrow> memberBorrowRequests;

    public Member(String memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberBorrowRequests = new ArrayList<>();
    }

    public void update(LibraryItem item) {
        System.out.println("NOTIFICATION for " + memberName + ": The book '" + item.getTitle() + "' you placed a hold on is now available!");
    }

    public void addBorrowRequest(Borrow borrow) {
        memberBorrowRequests.add(borrow);
    }

    public void removeBorrowRequest(Borrow borrow) {
        memberBorrowRequests.remove(borrow);
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public List<Borrow> getMemberBorrowRequests() {
        return memberBorrowRequests;
    }
}
