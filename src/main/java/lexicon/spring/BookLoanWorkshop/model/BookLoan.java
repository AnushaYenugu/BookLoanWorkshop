package lexicon.spring.BookLoanWorkshop.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    private LocalDate localDate;
    private LocalDate dueDate;
    private boolean returned;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.ALL})
    @JoinColumn(name="borrower")
    AppUser borrower;

    @ManyToOne(cascade={CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name="loaned_book")
    Book book;

    //Convience Methods
/**
    public void addAppUser(AppUser appUser){
        if(appUser == null) throw new IllegalArgumentException("AppUser is null");
        appUser.getLoans().add(this);

    }

    public void removeAppUser(AppUser appUser){
        if(appUser==null) throw new IllegalArgumentException("AppUser is null");
        appUser.getLoans().remove(this);
    }
**/


    public BookLoan() {
    }

    public BookLoan(LocalDate localDate, LocalDate dueDate, boolean returned, AppUser borrower,Book book)
 {
        this.localDate = localDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book=book;
    }

    public BookLoan(LocalDate localDate, LocalDate dueDate, boolean returned) {
        this.localDate = localDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }


    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public AppUser getBorrower() {
        return borrower;
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return loanId == bookLoan.loanId && returned == bookLoan.returned && Objects.equals(localDate, bookLoan.localDate) && Objects.equals(dueDate, bookLoan.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, localDate, dueDate, returned);
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "loanId=" + loanId +
                ", localDate=" + localDate +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                '}';
    }
}
