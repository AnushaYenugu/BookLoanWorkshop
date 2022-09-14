package lexicon.spring.BookLoanWorkshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;
@Entity
public class AppUser {
    @Id
    private int appUserId;
    private String username;
    private String password;
    private LocalDate regDate;
    private Details userDetails;

    public AppUser() {
    }

    public AppUser(String username, String password, LocalDate regDate) {
        this.username = username;
        this.password = password;
        this.regDate = regDate;

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
