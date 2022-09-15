package lexicon.spring.BookLoanWorkshop.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_details1",referencedColumnName = "detailsId")
    private Details userDetails;

    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.ALL})
    List<BookLoan> loans;

    //Convience Methods

    public void addBookLoan(BookLoan  bookLoan){
        if(bookLoan==null) throw new IllegalArgumentException("BookLoan is null");
        if(loans==null) loans=new ArrayList<>();
        loans.add(bookLoan);
        bookLoan.setBorrower(this);

    }

    public void removeBookLoan(BookLoan bookLoan){
        if(bookLoan==null) throw new IllegalArgumentException("Book Loan is null");
        if(bookLoan!=null){
            if(loans.contains(bookLoan)){
                loans.remove(bookLoan);
                 bookLoan.setBorrower(null);
            }
        }
    }



    public AppUser() {
    }

    public AppUser(String username, String password, LocalDate regDate) {
        this.username = username;
        this.password = password;
        this.regDate = regDate;

    }

    public AppUser(String username, String password, LocalDate regDate, Details userDetails) {
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.userDetails = userDetails;
    }


    public List<BookLoan> getLoans() {
        return loans;
    }

    public void setLoans(List<BookLoan> loans) {
        this.loans = loans;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return appUserId == appUser.appUserId && username.equals(appUser.username) && password.equals(appUser.password) && regDate.equals(appUser.regDate) && userDetails.equals(appUser.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, username, password, regDate, userDetails);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", userDetails=" + userDetails +
                '}';
    }
}
