package lexicon.spring.BookLoanWorkshop;

import lexicon.spring.BookLoanWorkshop.dao.AppUserDao;
import lexicon.spring.BookLoanWorkshop.dao.AuthorDao;
import lexicon.spring.BookLoanWorkshop.dao.BookDao;
import lexicon.spring.BookLoanWorkshop.dao.DetailsDao;
import lexicon.spring.BookLoanWorkshop.model.*;
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
    AuthorDao authorDao;
    BookDao bookDao;

    @Autowired
    public MyCommandLine(EntityManager entityManager,AppUserDao appUserDao,DetailsDao detailsDao, AuthorDao authorDao, BookDao bookDao) {
       this.entityManager=entityManager;
        this.appUserDao = appUserDao;
        this.detailsDao=detailsDao;
        this.authorDao=authorDao;
        this.bookDao=bookDao;
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
        Book book1=new Book("swe123","Peppa Pig",14);
        BookLoan appuser1BookLoan=new BookLoan(LocalDate.now(),LocalDate.now().plusDays(20),false);
        BookLoan appuser2BookLoan=new BookLoan(LocalDate.now(),LocalDate.now().plusDays(28),false);

        BookLoan bookLoan1=new BookLoan(LocalDate.now(),LocalDate.now(),false);



        appUser1.addBookLoan(appuser1BookLoan);
        appUser2.addBookLoan(appuser2BookLoan);
        appUser1.addBookLoan(bookLoan1);
        appUser1.removeBookLoan(bookLoan1);

      //  appuser1BookLoan.addAppUser(appUser1);
       // appuser2BookLoan.addAppUser(appUser2);


        Book book2=new Book("swe1234","Pippi Långstrumps",14);
        Author peppa=new Author("Peppa","Pig");
        Author pippi=new Author("Pippi","Långstrump");




        //test
        //
       // Book stestBokk1 = bookDao.create(new Book("swe123","Peppa Pig",14));

        authorDao.create(peppa);
        authorDao.create(pippi);
        bookDao.create(book1);
        bookDao.create(book2);
        book1.addAuthor(peppa);
        book2.addAuthor(pippi);




    }
}
