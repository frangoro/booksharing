package booksharing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booksharing.domain.Book;
import booksharing.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;
	
	public List<Book> listAll() {
        return repo.findAll();
    }
     
    public void save(Book book) {
        repo.save(book);
    }
     
    public Optional<Book> get(long id) {
        return repo.findById(id);
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
