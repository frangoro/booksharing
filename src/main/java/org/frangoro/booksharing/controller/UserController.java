package org.frangoro.booksharing.controller;

import java.util.List;
import java.util.Optional;

import org.frangoro.booksharing.domain.Book;
import org.frangoro.booksharing.domain.User;
import org.frangoro.booksharing.dto.UserDto;
import org.frangoro.booksharing.service.BookService;
import org.frangoro.booksharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private BookService bookService; //TODO Could be use only userService?
	
	@GetMapping(value= {"/", ""})
	public String view(Model model) {
		// TODO id of logged user
	    User user = service.get(1L);
	    model.addAttribute("user", user);
	     
	    return "myUser";
	}
	
	@PostMapping("/save")
	public String save(Model model, UserDto userDto) {
		service.registerNewUserAccount(userDto);
		return "myUser";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {
		service.delete(id);
		return "search";
	}

	/*@PostMapping("/new}")
	public String new(Model model) {
		service.registerNewUserAccount(id);
		return "search";
	}*/
	
	@GetMapping("/books")
	public String showBookList(Model model) {
		// TODO id of logged user
		Long userId = 1L;
		List<Book> books = bookService.findByOwnerId(userId);
	    model.addAttribute("books", books);
	    
		return "myBooks";
	}
	
	@GetMapping("/book/new")
    public String showBookNewForm(Book book) {
		
        return "newBook";
    }
	
	@GetMapping("/book/edit/{id}")
	public String showBookEditForm(Model model, @PathVariable("id") Long id) {
		Book book = bookService.get(id);
		model.addAttribute("book", book);
		return "editBook";
	}
	
	@PostMapping("/book/update/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book) {
		book.setId(id);
		book.setOwner(new User(1l));// TODO id of logged user
		bookService.save(book);
		return "redirect:/user/books";
	}
	
	@PostMapping("/book/save")
	public String saveBook(Book book) {
		book.setOwner(new User(1l));// TODO id of logged user
		bookService.save(book);
		return "redirect:/user/books";
	}
	
	@GetMapping("/book/delete/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		bookService.delete(id);
		return "redirect:/user/books";
	}
}
