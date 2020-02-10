package books_storage.controller;

import books_storage.dto.BookDTO;
import books_storage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public BookDTO getBook(@PathVariable("id") long id) {
        return bookService.findById(id);
    }

    // First variant of "search by rack id and/or shelf".
    // Will work like "find all" without parameters
    @RequestMapping(method = RequestMethod.GET)
    public List<BookDTO> getBooksBy(@RequestParam(value = "rack_id", required = false) Long rackId,
                                    @RequestParam(value = "shelf", required = false) Integer shelf) {
        return bookService.findByRackAndShelf(rackId, shelf);
    }

    // Second variant of "search by rack id and/or shelf".
    // This one is more flexible: we can use any BookDTO field for exact search without making any changes in endpoint.
    @RequestMapping(method = RequestMethod.POST, path = "/by")
    public List<BookDTO> getBooksByFilter(@RequestBody BookDTO bookDTO) {
        return bookService.findByBookDTO(bookDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }
}
