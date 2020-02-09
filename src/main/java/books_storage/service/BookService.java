package books_storage.service;

import books_storage.db.model.Book;
import books_storage.db.repository.BookRepository;
import books_storage.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
}
