package lexicon.spring.BookLoanWorkshop.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    @Column(nullable = false)
    private String authorFirstName;
    @Column(nullable = false)
    private String authorLastName;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.ALL})
    @JoinTable(joinColumns = @JoinColumn(name = "author_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id"))
    Set<Book> writtenBooks=new HashSet<>();

/*
    public void addBook (Book book){
        if(book == null) throw new IllegalArgumentException("Author is null");
        if(writtenBooks==null) writtenBooks=new TreeSet<>();
        writtenBooks.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook (Book book){
        if(book == null) throw new IllegalArgumentException("Author is null");
        if (writtenBooks!= null){
            if(writtenBooks.contains(book)){
                book.getAuthors().remove(this);
            }
        }
    }
**/
    public Author() {
    }

    public Author(String authorFirstName, String authorLastName) {
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public Author(int authorId, String authorFirstName, String authorLastName, Set<Book> writtenBooks) {
        this.authorId = authorId;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.writtenBooks = writtenBooks;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public Set<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && authorFirstName.equals(author.authorFirstName) && authorLastName.equals(author.authorLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorFirstName, authorLastName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                '}';
    }
}
