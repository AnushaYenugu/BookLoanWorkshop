package lexicon.spring.BookLoanWorkshop.data;

import lexicon.spring.BookLoanWorkshop.dao.AppUserDao;
import lexicon.spring.BookLoanWorkshop.model.AppUser;
import lexicon.spring.BookLoanWorkshop.model.Details;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional

class AppUserDAORepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserDao appUserDao;
    private AppUser testObject;

    public List<AppUser> appUsers(){
        return Arrays.asList(
                new AppUser("simonelbrink","ThisIsASecretPassword",LocalDate.now(),
                        new Details("simon@lexicon.se","Simon Elbrink", LocalDate.parse("1997-03-18"))),
                new AppUser("ulfbengtsson","ThisIsAnOtherPassword",LocalDate.now(),
                        new Details("ulf@lexicon.se","Ulf Bengtsson", LocalDate.parse("1982-08-25")))
        );
    }
    @BeforeEach
    void setUp() {
        appUserDao.findAll().stream().forEach(appUser->appUserDao.delete(appUser.getAppUserId()));

        List<AppUser> persistedAppUsers = appUsers().stream()
                .map(entityManager::persist).collect(Collectors.toList());
        testObject=persistedAppUsers.get(0);

    }

    @Test
    void findById() {
        AppUser foundAppuser=appUserDao.findById(testObject.getAppUserId());
        assertNotNull(foundAppuser);
    }

    @Test
    void findAll() {
        Collection<AppUser> listOfAppUsers=appUserDao.findAll();
        listOfAppUsers.stream().forEach(appUser -> System.out.println(appUser.getAppUserId() + " " + appUser.getUsername()));
        assertEquals(2,listOfAppUsers.size());
    }

    @Test
    void create() {
      AppUser save=  appUserDao.create(new AppUser("Anusha","anusha",LocalDate.now()));

      assertNotNull(save);
      assertEquals("Anusha",save.getUsername());
    }

    @Test
    void update() {
      }

    @Test
    void delete() {

        assertNotNull(entityManager.find(AppUser.class,testObject.getAppUserId()));
        appUserDao.delete(testObject.getAppUserId());
        assertNull(entityManager.find(AppUser.class,testObject.getAppUserId()));
    }
}