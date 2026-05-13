package questions.librarymanagement.state;

import questions.librarymanagement.entity.BookOrMagazineCopy;
import questions.librarymanagement.observer.Member;
import questions.librarymanagement.singleton.TransactionService;

public class OnHoldState implements ItemState {
    @Override
    public void checkoutItem(BookOrMagazineCopy copy, Member member) {
        // Only a member who placed the hold can check it out.
        if (copy.getItem().isObserver(member)) {
            TransactionService.getInstance().createBorrowRequest(copy, member);
            copy.changeCurrentState(new CheckedOutState());
            copy.getItem().removeObserver(member);
            System.out.println("Hold fulfilled. " + copy.getId() + " checked out by " + member.getMemberName());
        } else {
            System.out.println("Item - " + copy.getItem().getTitle() + " with Id - " + copy.getId() + " is on hold for another member, cannot be checked out.");
        }
    }

    @Override
    public void returnItem(BookOrMagazineCopy copy) {
        System.out.println("Item - " + copy.getItem().getTitle() + " with Id - " + copy.getId() + " is on  hold, not checked out.");
    }

    @Override
    public void holdItem(BookOrMagazineCopy copy, Member member) {
        System.out.println("Item - " + copy.getItem().getTitle() + " with Id - " + copy.getId() + " is already on hold.");
    }
}
