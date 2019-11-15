package org.frangoro.booksharing.controller;

import java.util.List;

import org.frangoro.booksharing.domain.Book;
import org.frangoro.booksharing.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@Autowired
	private BookService service;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied";
	}
	
	@GetMapping(value= {"","/","index","/home"})
	public String viewHomePage(Model model) {
	    List<Book> books = service.listAll();
	    model.addAttribute("books", books);
	     
	    return "search";
	}
	
	@GetMapping("/search")
	public String getBook(Model model, @RequestParam(value="searchQuery",required=false) String searchQuery) {
		
		List<Book> books = service.find(searchQuery);
	    model.addAttribute("books", books);
	    
		return "search";
	}

}
