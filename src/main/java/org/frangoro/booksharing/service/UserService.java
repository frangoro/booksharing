package org.frangoro.booksharing.service;

import org.frangoro.booksharing.domain.Role;
import org.frangoro.booksharing.domain.User;
import org.frangoro.booksharing.dto.UserDto;
import org.frangoro.booksharing.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository repo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}

	public void save(User user) {
		repo.save(user);
	}

	public User get(Long id) {
		return repo.findById(id).get();
	}

	public User get(String username) {
		return repo.findByUsername(username);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public User registerNewUserAccount(UserDto userDto) /*throws EmailExistsException*/ {

		/*if (emailExist(userDto.getEmail())) {
			throw new EmailExistsException(
					"There is an account with that email adress:" + userDto.getEmail());
		}*/
		User user = new User();
		BeanUtils.copyProperties(userDto,user);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEnabled(true);
		//user.setRole(new Role(Integer.valueOf(1), user));
		return repo.save(user);
	}

	public User updateUser(UserDto userDto) {
		User user = repo.findByUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		//TODO Check password confirmation. Bear in mind that if this field is empty then we must not change the pass
		return repo.save(user);
	}

	public void deleteUser(UserDto userDto) {
		User user = repo.findByUsername(userDto.getUsername());
		repo.delete(user);
		//TODO Also remove the user books from searchs
	}
}
