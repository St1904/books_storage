package books_storage.dto;

import books_storage.db.model.Book;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BookDTO {

    private long id;
    private String name;
    private int shelf;
    private RackDTO rack;

    public BookDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public RackDTO getRack() {
        return rack;
    }

    public void setRack(RackDTO rack) {
        this.rack = rack;
    }

    public Book toEntity() {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setShelf(shelf);
        book.setRack(rack.toEntity());
        return book;
    }

    public static BookDTO of(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setShelf(book.getShelf());
        bookDTO.setRack(RackDTO.of(book.getRack()));
        return bookDTO;
    }

    public static List<BookDTO> of(Collection<Book> books) {
        return books.stream()
                .map(BookDTO::of)
                .collect(Collectors.toList());
    }
}
