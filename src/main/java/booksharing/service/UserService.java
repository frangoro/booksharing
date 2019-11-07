package booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booksharing.domain.User;
import booksharing.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public void save(User user) {
		repo.save(user);
	}
	
	public User get(Long id) {
		return repo.findById(id).get();
	}
}
