package books_storage.db.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rack")
public class Rack {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "rack")
    private Set<Book> books;

    public Rack() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}