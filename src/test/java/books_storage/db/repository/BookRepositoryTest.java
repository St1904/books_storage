package books_storage.db.repository;

import books_storage.db.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void saveSomeBooks() {
        Book book = new Book();
        book.setName("Над пропастью во ржи");
        bookRepository.save(book);
        Book book2 = new Book();
        book2.setName("Как дела у ёжика");
        bookRepository.save(book2);
    }

    @Test
    public void testFindAll() {
        List<Book> books = bookRepository.findAll();
        assertEquals(2, books.size());
    }

    @Test
    public void testFindByName() {
        List<Book> books = bookRepository.findAllByNameContaining("жи");
        assertEquals(2, books.size());
        assertTrue(books.stream().allMatch(book -> book.getName().contains("жи")));

        books = bookRepository.findAllByNameContaining("пропасть");
        assertEquals(1, books.size());
        assertTrue(books.get(0).getName().contains("пропасть"));
    }
}