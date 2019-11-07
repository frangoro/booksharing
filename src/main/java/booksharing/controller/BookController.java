package booksharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import booksharing.domain.Book;
import booksharing.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService service;
	
	@RequestMapping("/booksharing")
	public String viewHomePage(Model model) {
	    List<Book> books = service.listAll();
	    model.addAttribute("books", books);
	     
	    return "search";
	}
	
	public void getBook(Long id) {
		service.get(id);
	}

}
