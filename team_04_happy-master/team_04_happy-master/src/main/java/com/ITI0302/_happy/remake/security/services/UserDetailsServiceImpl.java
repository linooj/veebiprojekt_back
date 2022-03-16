package com.ITI0302._happy.remake.security.services;

import com.ITI0302._happy.exception.NotFoundException;
import com.ITI0302._happy.remake.models.User;
import com.ITI0302._happy.remake.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return getById(id);
    }

    public User addUser(User User) {
        return userRepository.save(User);
    }

    public User updateUser(User User) {
        return userRepository.save(User);
    }

    public void deleteUser(String id) {
        userRepository.delete(getById(id));
    }

    public List<User> searchByFullName(String firstName, String lastName) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstName, lastName);
    }

    private User getById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("No such client found. :("));
    }

}
