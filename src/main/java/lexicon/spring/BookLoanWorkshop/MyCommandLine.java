package lexicon.spring.BookLoanWorkshop;

import lexicon.spring.BookLoanWorkshop.dao.AppUserDao;
import lexicon.spring.BookLoanWorkshop.dao.DetailsDao;
import lexicon.spring.BookLoanWorkshop.model.AppUser;
import lexicon.spring.BookLoanWorkshop.model.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
@Transactional
@Component
public class MyCommandLine implements CommandLineRunner {

    private EntityManager entityManager;
    AppUserDao appUserDao;
    DetailsDao detailsDao;
    @Autowired
    public MyCommandLine(EntityManager entityManager,AppUserDao appUserDao,DetailsDao detailsDao) {
       this.entityManager=entityManager;
        this.appUserDao = appUserDao;
        this.detailsDao=detailsDao;
    }

    @Override
    public void run(String... args) throws Exception {


        Details details1=new Details("anusha@gmail.com","Anusha",LocalDate.now());
        Details details2=new Details("roberto@gmail.com","Roberto",LocalDate.now());
        AppUser appUser1=new AppUser("AnushaYenugu","anusha", LocalDate.now());
        AppUser appUser2=new AppUser("Roberto","roberto",LocalDate.now());
        System.out.println(appUserDao.create(appUser1));
        appUserDao.create(appUser2);
        detailsDao.create(details1);
        detailsDao.create(details2);

        appUser1.setUserDetails(details1);
        appUser2.setUserDetails(details2);




    }
}
