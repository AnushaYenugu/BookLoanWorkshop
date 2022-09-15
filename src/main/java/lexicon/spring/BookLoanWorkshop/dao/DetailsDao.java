package lexicon.spring.BookLoanWorkshop.dao;

import lexicon.spring.BookLoanWorkshop.model.Details;

import java.util.Collection;

public interface DetailsDao {

    Details findById(int id);
    Collection<Details> findAll();
    Details create(Details details);
    Details update(Details details);
    void delete(int id);

}
