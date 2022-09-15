package lexicon.spring.BookLoanWorkshop.dao;


import lexicon.spring.BookLoanWorkshop.model.Book;

import java.util.Collection;

public interface BookDao {
    Book findById(int id);
    Collection<Book> findAll();
    Book create(Book book);
    Book update(Book book);
    void delete(int id);

}
