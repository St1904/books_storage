package books_storage.service;

import books_storage.Application;
import books_storage.db.model.Rack;
import books_storage.db.repository.RackRepository;
import books_storage.dto.BookDTO;
import books_storage.dto.BookDTOBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private RackRepository rackRepository;

    private void initSaveSomeBooks() {
        rackRepository.save(new Rack(1L));
        rackRepository.save(new Rack(2L));
        BookDTO book1 = new BookDTOBuilder().create()
                .withName("Книга 1")
                .withShelf(2)
                .withRack(1L)
                .build();
        BookDTO book2 = new BookDTOBuilder().create()
                .withName("Книга 2")
                .withShelf(2)
                .withRack(2L)
                .build();
        BookDTO book3 = new BookDTOBuilder().create()
                .withName("Книга 3")
                .withShelf(3)
                .withRack(1L)
                .build();
        bookService.save(book1);
        bookService.save(book2);
        bookService.save(book3);
    }

    private void testFindBookByShelfAndRack(BiFunction<Long, Integer, List<BookDTO>> method) {
        List<BookDTO> books = method.apply(2L, 2);
        assertEquals(1, books.size());
        assertEquals("Книга 2", books.get(0).getName());

        books = method.apply(null, 2);
        assertEquals(2, books.size());
        Set<String> names = books.stream().map(BookDTO::getName).collect(Collectors.toSet());
        assertTrue(names.contains("Книга 1"));
        assertTrue(names.contains("Книга 2"));

        books = method.apply(1L, null);
        assertEquals(2, books.size());
        names = books.stream().map(BookDTO::getName).collect(Collectors.toSet());
        assertTrue(names.contains("Книга 1"));
        assertTrue(names.contains("Книга 3"));

        books = method.apply(null, null);
        assertEquals(3, books.size());
    }

    // tests two variants of method on same data
    @Test
    public void testFindBookByShelfAndRack1() {
        initSaveSomeBooks();
        testFindBookByShelfAndRack(bookService::findByRackAndShelf);
        testFindBookByShelfAndRack(this::findBy);
    }

    private List<BookDTO> findBy(Long rackId, Integer shelf) {
        return bookService.findBy(new BookDTOBuilder().create()
                .withShelf(shelf)
                .withRack(rackId)
                .build());
    }
}