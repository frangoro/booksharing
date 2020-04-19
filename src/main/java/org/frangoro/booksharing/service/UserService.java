package org.frangoro.booksharing.service;

import org.frangoro.booksharing.domain.Book;
import org.frangoro.booksharing.domain.User;
import org.frangoro.booksharing.dto.UserDto;
import org.frangoro.booksharing.repository.BookRepository;
import org.frangoro.booksharing.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository userRepository;
	private BookRepository bookRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, BookRepository bookRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.bookRepository = bookRepository;
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public User get(Long id) {
		return userRepository.findById(id).get();
	}

	public User get(String username) {
		return userRepository.findByUsername(username);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
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
		return userRepository.save(user);
	}

	public User updateUser(UserDto userDto) {
		User user = userRepository.findByUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		//TODO Check password confirmation. Bear in mind that if this field is empty then we must not change the pass
		return userRepository.save(user);
	}

	public void deleteUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		user.setEnabled(false);
		userRepository.save(user);

		//TODO Also remove the user books from searchs
		bookRepository.findByOwnerId(user).forEach(book -> {
			book.setStatus(Book.Status.DISABLED);
			bookRepository.save(book);
		});
	}
}
