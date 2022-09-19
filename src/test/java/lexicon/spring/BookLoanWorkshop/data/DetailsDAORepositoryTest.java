package lexicon.spring.BookLoanWorkshop.data;

import lexicon.spring.BookLoanWorkshop.dao.DetailsDao;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class DetailsDAORepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    DetailsDao detailsDao;
    private Details testObject;

    public List<Details> details(){
        return Arrays.asList(new Details("Anusha@gmail.com", "Anusha Yenugu",LocalDate.now()),
                new Details("harika@gmail.com","Harika Yenugu",LocalDate.now()));
    }

    @BeforeEach
    void setUp() {
        List<Details> persistedDetails=details().stream()
                .map(entityManager::persist).collect(Collectors.toList());
        testObject=details().get(0);
    }

    @Test
    void findById() {

    }

    @Test
    void findAll() {
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