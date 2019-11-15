package org.frangoro.booksharing.service;

import java.util.List;
import java.util.Optional;

import org.frangoro.booksharing.domain.Book;
import org.frangoro.booksharing.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;
	
	public List<Book> listAll() {
        return repo.findAll();
    }
	
	public List<Book> find(String searchQuery) {
        return repo.findByTitleOrAuthor(searchQuery, searchQuery);
    }
	
	public List<Book> findByOwnerId(Long userId) {
		return repo.findByOwnerId(userId);
	}
     
    public void save(Book book) {
        repo.save(book);
    }
     
    public Book get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
