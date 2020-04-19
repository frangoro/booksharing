package org.frangoro.booksharing.controller;

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

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	/*@GetMapping("/")
	public String showBookList(Model model) {
		// TODO id of logged user
		Long userId = 1L;
		List<Book> books = bookService.findByOwnerId(userId);
	    model.addAttribute("books", books);
	    
		return "myBooks";
	}*/

	@GetMapping("/view/{id}")
	public String view(Model model, @PathVariable("id") Long id) {
		Book book = bookService.get(id);
		model.addAttribute("book", book);
		return "viewBook";
	}
	
	@GetMapping("/new")
    public String showBookNewForm(Book book) {
		
        return "newBook";
    }
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Book book = bookService.get(id);
		model.addAttribute("book", book);
		return "editBook";
	}
	
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Book book) {
		book.setId(id);
		book.setOwner(new User(1l));// TODO id of logged user
		bookService.save(book);
		return "redirect:/user/books";
	}
	
	@PostMapping("/save")
	public String save(Book book) {
		book.setOwner(new User(1l));// TODO id of logged user
		bookService.save(book);
		return "redirect:/user/books";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		bookService.delete(id);
		return "redirect:/user/books";
	}
}
