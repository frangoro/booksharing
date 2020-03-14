package org.frangoro.booksharing.service;

import org.frangoro.booksharing.domain.User;
import org.frangoro.booksharing.dto.UserDto;
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

    @BeforeEach
    void setup() {
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserService(userRepository, passwordEncoder);
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
        String pass = passwordEncoder.encode("frangoro");
        assertEquals("$2a$10$5uGZnc2eBQ7.XowTKwYbyudBR4RYjDVYWFdGNaHHSz/hbIdd//Q2W",pass);
    }
}