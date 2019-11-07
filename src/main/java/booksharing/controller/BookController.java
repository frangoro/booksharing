package booksharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import booksharing.domain.Book;
import booksharing.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
	    List<Book> books = service.listAll();
	    model.addAttribute("books", books);
	     
	    return "search";
	}
	
	@GetMapping("/search")
	public String getBook(Model model, @RequestParam(value="searchQuery",required=false) String searchQuery) {
		System.out.println("aaaaaaaaaaaaaaaaa: " + searchQuery);
		
		List<Book> books = service.find(searchQuery);
	    model.addAttribute("books", books);
	    
		return "search";
	}

}
