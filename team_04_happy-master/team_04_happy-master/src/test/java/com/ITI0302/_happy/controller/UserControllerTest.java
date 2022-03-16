package com.ITI0302._happy.controller;

import com.ITI0302._happy.remake.models.User;
import com.ITI0302._happy.remake.security.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    UserDetailsServiceImpl userDetailsService;
    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAllOwner() {
        List<User> list = List.of(new User("username", "email", "password"));
        when(userDetailsService.findAll()).thenReturn(list);

        ResponseEntity<List<User>> result = userController.searchedUsers("", "");
        ResponseEntity<List<User>> expected = new ResponseEntity<>(list, HttpStatus.OK);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSearchedUser() {
        User user = new User("username", "email", "password");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        List<User> list = List.of(user);

        when(userDetailsService.searchByFullName(anyString(), anyString())).thenReturn(list);

        ResponseEntity<List<User>> result = userController.searchedUsers("firstname", "lastname");
        ResponseEntity<List<User>> expected = new ResponseEntity<>(list, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }


    @Test
    void testFindOwner() {
        User user = new User("username", "email", "password");
        when(userDetailsService.findById(anyString())).thenReturn(user);

        ResponseEntity<User> result = userController.findUser("id");
        ResponseEntity<User> expected = new ResponseEntity<>(user, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSaveUser() {
        User user = new User("username", "email", "password");
        when(userDetailsService.addUser(any())).thenReturn(user);

        ResponseEntity<User> result = userController.saveUser(user);
        ResponseEntity<User> expected = new ResponseEntity<>(HttpStatus.CREATED);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testUpdateUser() {
        User user = new User("username", "email", "password");

        when(userDetailsService.updateUser(any())).thenReturn(user);

        ResponseEntity<User> result = userController.updateUser(user);
        ResponseEntity<User> expected = new ResponseEntity<>(user, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testDeleteUser() {
        ResponseEntity<User> result = userController.deleteUser("id");
        ResponseEntity<User> expected = new ResponseEntity<>(HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }
}
