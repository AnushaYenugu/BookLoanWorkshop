package lexicon.spring.BookLoanWorkshop.data;

import lexicon.spring.BookLoanWorkshop.dao.BookLoanDao;
import lexicon.spring.BookLoanWorkshop.model.AppUser;
import lexicon.spring.BookLoanWorkshop.model.Book;
import lexicon.spring.BookLoanWorkshop.model.BookLoan;
import lexicon.spring.BookLoanWorkshop.model.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class BookLoanDAORepositoryTest {
    @Autowired
    private BookLoanDao bookLoanDao;
    private BookLoan bookLoan;
    @Autowired
    private TestEntityManager entityManager;

    public List<AppUser> appUsers(){
        return Arrays.asList(
                new AppUser("simonelbrink","ThisIsASecretPassword",
                        new Details("simon@lexicon.se","Simon Elbrink", LocalDate.parse("1997-03-18"))),
                new AppUser("ulfbengtsson","ThisIsAnOtherPassword",
                        new Details("ulf@lexicon.se","Ulf Bengtsson", LocalDate.parse("1982-08-25")))
        );
    }

    public List<Book> books(){
        return Arrays.asList(
                new Book("34348934fk","Java Core",15),
                new Book("34348934fk","Java Core Advanced",15)
        );
    }



    @BeforeEach
    void setUp() {
        List<AppUser> persistedAppUsers =
                appUsers().stream()
                        .map(entityManager::persist)
                        .collect(Collectors.toList());

        List<Book> persistedBooks =
                books().stream()
                        .map(entityManager::persist)
                        .collect(Collectors.toList());

        List<BookLoan> bookLoans = new ArrayList<>(
                Arrays.asList(
                        new BookLoan(LocalDate.now(),
                                LocalDate.now().plusDays(50),
                                false,
                                persistedAppUsers.get(0),
                                persistedBooks.get(0)),

                        new BookLoan(LocalDate.now(),
                                LocalDate.now().plusDays(30),
                                false,
                                persistedAppUsers.get(0),
                                persistedBooks.get(1))

                )
        );

        List<BookLoan> persistedBookLoans =  bookLoans.stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());

    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        assertEquals(2,bookLoanDao.findAll().size());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}