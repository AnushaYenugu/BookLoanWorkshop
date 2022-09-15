package lexicon.spring.BookLoanWorkshop.dao;

import lexicon.spring.BookLoanWorkshop.model.AppUser;

import java.util.Collection;

public interface AppUserDao {

    AppUser findById(int id);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(int id);



}
