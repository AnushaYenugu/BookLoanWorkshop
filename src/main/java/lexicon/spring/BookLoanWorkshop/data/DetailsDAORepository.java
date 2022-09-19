package lexicon.spring.BookLoanWorkshop.data;

import lexicon.spring.BookLoanWorkshop.dao.DetailsDao;
import lexicon.spring.BookLoanWorkshop.model.Details;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class DetailsDAORepository implements DetailsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Details findById(int id) {
       if(id<=0) throw new IllegalArgumentException("Details Id is not found");
       Details details= entityManager.find(Details.class,id);
       return details;
    }
    @Transactional(readOnly = true)
    @Override
    public Collection<Details> findAll() {
      Query selectQuery=  entityManager.createQuery("select d from Details d");
        return selectQuery.getResultList();
    }
    @Transactional()
    @Override
    public Details create(Details details) {
      entityManager.persist(details);
        return details;
    }
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(int id) {
        if(id<=0) throw new IllegalArgumentException("Details Id is invalid");
        entityManager.remove(findById(id));
    }
}
