package books_storage.controller;

import books_storage.dto.BookDTO;
import books_storage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookDTO> getAllBooks() {
        return bookService.findAllBooks();
    }

    @RequestMapping(method = RequestMethod.POST)
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }
}
