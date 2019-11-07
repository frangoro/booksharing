package booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import booksharing.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
