package questions.librarymanagement.state;

import questions.librarymanagement.entity.BookOrMagazineCopy;
import questions.librarymanagement.observer.Member;
import questions.librarymanagement.singleton.TransactionService;

public class AvailableState implements ItemState {
    @Override
    public void checkoutItem(BookOrMagazineCopy copy, Member member) {
        TransactionService.getInstance().createBorrowRequest(copy, member);
        copy.changeCurrentState(new CheckedOutState());
        System.out.println(copy.getId() + " checked out by " + member.getMemberName());
    }

    @Override
    public void returnItem(BookOrMagazineCopy copy) {
        System.out.println("Cannot return item - " + copy.getItem().getTitle() + "with id - " + copy.getId() + "as it is already available.");
    }

    @Override
    public void holdItem(BookOrMagazineCopy copy, Member member) {
        System.out.println("Cannot place hold on item - " + copy.getItem().getTitle() + "with id - " + copy.getId() + "as it is currently available.");
    }
}
