package lexicon.spring.BookLoanWorkshop.data;

import lexicon.spring.BookLoanWorkshop.dao.BookLoanDao;
import lexicon.spring.BookLoanWorkshop.model.BookLoan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class BookLoanDAORepository implements BookLoanDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookLoan findById(int id) {
        if(id<=0) throw new IllegalArgumentException("Invalid BookLoan Id");
     BookLoan bookloanById= entityManager.find(BookLoan.class,id);
        return bookloanById;
    }
    @Transactional(readOnly = true)
    @Override
    public Collection<BookLoan> findAll() {
                return entityManager.createQuery("select bl from BookLoan bl").getResultList();
    }
    @Transactional(readOnly = true)
    @Override
    public BookLoan create(BookLoan bookLoan) {
        if(bookLoan==null) throw new IllegalArgumentException("Invalid BookLoan is null");
        entityManager.persist(bookLoan);
        return bookLoan;
    }
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public BookLoan update(BookLoan bookLoan) {
        return entityManager.merge(bookLoan);
    }
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(int id) {
        if(id<=0) throw new IllegalArgumentException("Invalid BookLoan id");
        entityManager.remove(findById(id));

    }
}
