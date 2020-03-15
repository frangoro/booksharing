package org.frangoro.booksharing.repository;

import java.util.List;

import org.frangoro.booksharing.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query("SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%', ?1,'%')) "
			+ "OR lower(b.author) LIKE lower(concat('%', ?2,'%'))")
    List<Book> findByTitleOrAuthor(String title, String author);
	
	@Query("SELECT b FROM Book b WHERE b.owner = ?1") //FIXME How to do these queries with foreign keys
	List<Book> findByOwnerId(Long ownerId);

}
