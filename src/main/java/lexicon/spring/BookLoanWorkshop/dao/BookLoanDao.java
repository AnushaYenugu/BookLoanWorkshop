package lexicon.spring.BookLoanWorkshop.dao;

import lexicon.spring.BookLoanWorkshop.model.BookLoan;

import java.util.Collection;

public interface BookLoanDao {
    BookLoan findById(int id);
    Collection<BookLoan> findAll();
    BookLoan create(BookLoan bookLoan);
    BookLoan update(BookLoan bookLoan);
    void delete(int id);
}
