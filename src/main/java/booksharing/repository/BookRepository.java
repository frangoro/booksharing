package booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import booksharing.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
