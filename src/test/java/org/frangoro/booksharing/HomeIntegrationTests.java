package org.frangoro.booksharing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.frangoro.booksharing.domain.Book;
import org.frangoro.booksharing.repository.BookRepository;
import org.frangoro.booksharing.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addNewBookAndSearchIt() throws Exception {

        // Create the domain object (the book)
        Book book = new Book();
        book.setAuthor("Author Test1 Test2");
        book.setTitle("TitleTest");

        // Save request
        mockMvc.perform(post("/book/save")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());

        // Search request
        String searchString = "TitleTe";
        mockMvc.perform(get("/search")
                .contentType("text/plain")
                .param("searchQuery", searchString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("search"))
                .andExpect(model().attributeExists("books"));

        // Check the book is returned
        List<Book> serviceBooks = bookService.find(searchString);
        assertEquals(serviceBooks.size(),1);

        List<Book> repositoryBooks = bookRepository.findByTitleOrAuthor(searchString, searchString);
        assertEquals(repositoryBooks.size(),1);

    }

}
