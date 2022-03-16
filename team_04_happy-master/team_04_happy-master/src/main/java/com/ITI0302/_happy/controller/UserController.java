package com.ITI0302._happy.controller;

import com.ITI0302._happy.remake.models.User;
import com.ITI0302._happy.remake.security.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/users")
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> searchedUsers(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName)  {
        System.out.println(firstName);
        System.out.println(lastName);
        if (firstName.isBlank() && lastName.isBlank()) {
            List<User> users = userService.findAll();
            System.out.println("Leian kõik ülesse");
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        System.out.println("Leian ainult paar tükki");
        List<User> Users = userService.searchByFullName(firstName, lastName);
        return new ResponseEntity<>(Users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> findUser(@PathVariable String id) {
        User searchedUser = userService.findById(id);
        return new ResponseEntity<>(searchedUser, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> saveUser(@RequestBody User User) {
        this.userService.addUser(User);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User User) {
        User changed = this.userService.updateUser(User);
        return new ResponseEntity<>(changed, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
