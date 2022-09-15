package lexicon.spring.BookLoanWorkshop.data;

import lexicon.spring.BookLoanWorkshop.dao.BookDao;
import lexicon.spring.BookLoanWorkshop.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
@Repository
public class BookDAORepository implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Book findById(int id) {

        return entityManager.find(Book.class,id);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Book> findAll() {
        return entityManager.createQuery("select b from Book b").getResultList();
    }
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(int id) {

            if(id<=0) throw new IllegalArgumentException("Invalid Book Id");
            entityManager.remove(findById(id));
    }
}
