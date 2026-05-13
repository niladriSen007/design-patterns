package questions.librarymanagement.singleton;

import questions.librarymanagement.entity.BookOrMagazineCopy;
import questions.librarymanagement.entity.Borrow;
import questions.librarymanagement.observer.Member;

import java.util.HashMap;
import java.util.Map;

public class TransactionService {
    private static volatile TransactionService instance;
    private final Map<String, Borrow> transactions; // key : LibraryItem id

    private TransactionService() {
        this.transactions = new HashMap<>();
    }

    public static TransactionService getInstance() {
        if (instance == null) {
            synchronized (TransactionService.class) {
                if (instance == null) {
                    instance = new TransactionService();
                }
            }
        }
        return instance;
    }

    public void createBorrowRequest(BookOrMagazineCopy bookOrMagazineCopy, Member member) {
        if (transactions.containsKey(bookOrMagazineCopy.getId())) {
            throw new RuntimeException("Book or Magazine already borrowed by someone else");
        }
        Borrow borrow = new Borrow(bookOrMagazineCopy, member);
        transactions.put(bookOrMagazineCopy.getId(), borrow);
        member.addBorrowRequest(borrow);
    }

    public void endBorrowRequest(BookOrMagazineCopy bookOrMagazineCopy) {
        Borrow borrowReq = transactions.remove(bookOrMagazineCopy.getId());
        if (borrowReq != null) {
            borrowReq.getMember().removeBorrowRequest(borrowReq);
        }
    }

}
