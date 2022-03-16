package com.ITI0302._happy.service;

import com.ITI0302._happy.remake.models.User;
import com.ITI0302._happy.remake.repository.UserRepository;
import com.ITI0302._happy.remake.security.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testFindAll() {
        List<User> findAll = List.of(new User("username", "email", "password"));
        when(userRepository.findAll()).thenReturn(findAll);
        List<User> result = userDetailsService.findAll();
        Assertions.assertEquals(findAll, result);
    }

    @Test
    void testFindById() {
        User findBy = new User("username", "email", "password");
        when(userRepository.findById(anyString())).thenReturn(Optional.of(findBy));
        User result = userDetailsService.findById("id");
        Assertions.assertEquals(findBy, result);
    }

    @Test
    void testAddUser() {
        User addUser = new User("username", "email", "password");
        when(userRepository.save(any(User.class))).thenReturn(addUser);

        User result = userDetailsService.addUser(addUser);

        Assertions.assertEquals(addUser, result);
    }

    @Test
    void testUpdateUser() {
        User user = new User("username", "email", "password");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userDetailsService.updateUser(user);
        Assertions.assertEquals(user, result);
    }



    @Test
    void testSearchByFullName() {
        User user = new User("username", "email", "password");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        List<User> findAll = List.of(user);

        when(userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(anyString(), anyString())).thenReturn(findAll);

        List<User> result = userDetailsService.searchByFullName("firstname", "lastname");
        Assertions.assertEquals(findAll, result);
    }
}
