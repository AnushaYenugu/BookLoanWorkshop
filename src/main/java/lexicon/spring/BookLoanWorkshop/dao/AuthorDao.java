package lexicon.spring.BookLoanWorkshop.dao;

import lexicon.spring.BookLoanWorkshop.model.Author;

import java.util.Collection;

public interface AuthorDao {

    Author findById(int id);
    Collection<Author> findAll();
    Author create(Author author);
    Author update(Author author);
    void delete(int id);
}
