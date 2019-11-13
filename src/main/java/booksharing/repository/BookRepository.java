package booksharing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import booksharing.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query("SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%', ?1,'%')) "
			+ "OR lower(b.author) LIKE lower(concat('%', ?2,'%'))")
    List<Book> findByTitleOrAuthor(String title, String author);
	
	@Query("SELECT b FROM Book b WHERE b.owner = ?1")
	List<Book> findByOwnerId(Long ownerId);

}
