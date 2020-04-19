package org.frangoro.booksharing.service;

import org.frangoro.booksharing.domain.User;
import org.frangoro.booksharing.dto.UserDto;
import org.frangoro.booksharing.repository.BookRepository;
import org.frangoro.booksharing.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @BeforeEach
    void setup() {
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserService(userRepository, passwordEncoder, bookRepository);
    }

    /*@Test
    void registerNewUserAccount() {
        UserDto userDto = new UserDto();
        userDto.setPassword("frangoro");
        User user = userService.registerNewUserAccount(userDto);
        assertEquals("",user.getPassword());
    }*/

    @Test
    void generatePasswordEncoded() {
        String pass = passwordEncoder.encode("myPass");
        System.out.println(pass);
        assertNotNull(pass);
        assertTrue(passwordEncoder.matches("myPass",pass));
    }
}