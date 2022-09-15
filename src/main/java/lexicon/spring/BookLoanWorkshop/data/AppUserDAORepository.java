package lexicon.spring.BookLoanWorkshop.data;

import lexicon.spring.BookLoanWorkshop.dao.AppUserDao;
import lexicon.spring.BookLoanWorkshop.model.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class AppUserDAORepository implements AppUserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public AppUser findById(int id) {
        if(id<=0)  throw new IllegalArgumentException("User Id is invalid");
        AppUser appuser= entityManager.find(AppUser.class,id);
        return appuser;
    }
    @Transactional(readOnly = true)
    @Override
    public Collection<AppUser> findAll() {
        Query selectQuery=entityManager.createQuery("select a from AppUser a");
        return selectQuery.getResultList();
    }
    @Transactional
    @Override
    public AppUser create(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public AppUser update(AppUser appUser) {
        return entityManager.merge(appUser);

    }
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(int id) {
        if(id<=0) throw new IllegalArgumentException("Invalid UserId");
        entityManager.remove(findById(id));
    }
}
