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

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable("id") long id) {
        return bookService.findById(id);
    }

    // First variant of "search by rack id and/or shelf".
    // Will work like "find all" without parameters
    @GetMapping
    public List<BookDTO> getBooksBy(@RequestParam(value = "rack_id", required = false) Long rackId,
                                    @RequestParam(value = "shelf", required = false) Integer shelf) {
        return bookService.findByRackAndShelf(rackId, shelf);
    }

    // Second variant of "search by rack id and/or shelf".
    // This one is more flexible: we can use any BookDTO field for exact search without making any changes in endpoint.
    @PostMapping("/by")
    public List<BookDTO> getBooksByFilter(@RequestBody BookDTO bookDTO) {
        return bookService.findBy(bookDTO);
    }

    @PostMapping
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable("id") long id,
                              @RequestBody BookDTO bookDTO) {
        bookDTO.setId(id);
        return bookService.updateBook(bookDTO);
    }
}
