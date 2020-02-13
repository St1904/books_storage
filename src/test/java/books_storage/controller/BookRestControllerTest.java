package books_storage.controller;

import books_storage.dto.BookDTOBuilder;
import books_storage.dto.RackDTO;
import books_storage.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindById() throws Exception {
        given(bookService.findById(anyLong()))
                .willReturn(BookDTOBuilder.create()
                        .withId(1L)
                        .withName("Абракадабра")
                        .withRack(new RackDTO(34L))
                        .withShelf(105)
                        .build());
        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Абракадабра"))
                .andExpect(jsonPath("$.rack").exists())
                .andExpect(jsonPath("$.rack.id").value(34))
                .andExpect(jsonPath("$.shelf").value(105));
    }
}