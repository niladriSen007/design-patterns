package questions.librarymanagement.state;

import questions.librarymanagement.entity.BookOrMagazineCopy;
import questions.librarymanagement.observer.Member;
import questions.librarymanagement.singleton.TransactionService;

public class CheckedOutState implements ItemState {
    @Override
    public void checkoutItem(BookOrMagazineCopy copy, Member member) {
        System.out.println("Cannot checkout '" + copy.getItem().getTitle() + "' for " + member.getMemberName() + " because it is already checked out.");
    }

    @Override
    public void returnItem(BookOrMagazineCopy copy) {
        TransactionService.getInstance().endBorrowRequest(copy);
        System.out.println(copy.getId() + " returned.");
        if (copy.getItem().hasObservers()) {
            copy.changeCurrentState(new OnHoldState());
            copy.getItem().notifyObservers();
        } else {
            copy.changeCurrentState(new AvailableState());
        }
    }

    @Override
    public void holdItem(BookOrMagazineCopy copy, Member member) {
        copy.getItem().addObserver(member);
        System.out.println(member.getMemberName() + " placed a hold on '" + copy.getItem().getTitle() + "'");
    }
}
