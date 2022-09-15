package lexicon.spring.BookLoanWorkshop.data;

import lexicon.spring.BookLoanWorkshop.dao.AuthorDao;
import lexicon.spring.BookLoanWorkshop.model.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AuthorDAORepository implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Author findById(int id) {
        if(id<=0) throw new IllegalArgumentException("Invalid Author Id");
        return entityManager.find(Author.class,id);

    }
    @Transactional(readOnly = true)
    @Override
    public Collection<Author> findAll() {
        return entityManager.createQuery("select a from Author a").getResultList();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Author create(Author author) {
        if(author==null) throw new IllegalArgumentException("Author is null");
        entityManager.persist(author);
        return author;
    }
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Author update(Author author) {
        return entityManager.merge(author);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(int id) {
        if(id<=0) throw new IllegalArgumentException("Invalid Author id");
        entityManager.remove(findById(id));

    }
}
