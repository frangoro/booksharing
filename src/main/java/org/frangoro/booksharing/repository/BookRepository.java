package org.frangoro.booksharing.repository;

import java.util.List;

import org.frangoro.booksharing.domain.Book;
import org.frangoro.booksharing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query("SELECT b FROM Book b WHERE b.status <> 'DISABLED' AND (lower(b.title) LIKE lower(concat('%', ?1,'%')) "
			+ "OR lower(b.author) LIKE lower(concat('%', ?2,'%')))")
    List<Book> findByTitleOrAuthor(String title, String author);
	
	@Query("SELECT b FROM Book b WHERE b.owner = ?1")
	List<Book> findByOwnerId(User user);

	@Query("SELECT b FROM Book b WHERE b.status = ?1")
	List<Book> findAllBooksByStatus(String status);

	@Query("SELECT b FROM Book b WHERE b.status <> 'DISABLED' ")
	List<Book> findAllBooksEnabled();

}
