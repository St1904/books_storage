package books_storage.service;

import books_storage.db.model.Book;
import books_storage.db.model.Rack;
import books_storage.db.repository.BookRepository;
import books_storage.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> findAllBooks() {
        return BookDTO.of(bookRepository.findAll());
    }

    public BookDTO save(BookDTO bookDTO) {
        Book book = bookDTO.toEntity();
        return BookDTO.of(bookRepository.save(book));
    }

    public BookDTO findById(long id) {
        return BookDTO.of(bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found by id: " + id)));
    }

    public List<BookDTO> findByRackAndShelf(Long rackId, Integer shelf) {
        Book bookExample = new Book();
        Rack rack = null;
        if (rackId != null) {
            rack = new Rack();
            rack.setId(rackId);
        }
        bookExample.setRack(rack);
        if (shelf != null) {
            bookExample.setShelf(shelf);
        }
        return BookDTO.of(bookRepository.findAll(Example.of(bookExample, ExampleMatcher.matching().withIgnoreNullValues())));
    }

    public List<BookDTO> findByBookDTO(BookDTO bookDTO) {
        return BookDTO.of(bookRepository.findAll(Example.of(bookDTO.toEntity(), ExampleMatcher.matching().withIgnoreNullValues())));
    }
}
