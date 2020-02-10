package books_storage.db.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Range(min = 1, max = 3, message = "Shelf must be between {min} and {max} inclusive")
    @Column(name = "shelf")
    private Integer shelf;

    @ManyToOne(targetEntity = Rack.class)
    @JoinColumn(name = "rack_id", referencedColumnName = "id", nullable = false)
    private Rack rack;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShelf() {
        return shelf;
    }

    public void setShelf(Integer shelf) {
        this.shelf = shelf;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }
}
