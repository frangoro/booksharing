package org.frangoro.booksharing.service;

import org.frangoro.booksharing.domain.Role;
import org.frangoro.booksharing.domain.User;
import org.frangoro.booksharing.dto.UserDto;
import org.frangoro.booksharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository repo;
	
	public void save(User user) {
		repo.save(user);
	}
	
	public User get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerNewUserAccount(UserDto userDto) /*throws EmailExistsException*/ {

		/*if (emailExist(userDto.getEmail())) {
			throw new EmailExistsException(
					"There is an account with that email adress:" + userDto.getEmail());
		}*/
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		//user.setRole(new Role(Integer.valueOf(1), user));
		return repo.save(user);
	}
}
