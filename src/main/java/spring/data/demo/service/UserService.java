package spring.data.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.data.demo.entity.User;
import spring.data.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<User> getUsersByFirstNameOrLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    public List<User> getUsersByUsernameContaining(String keyword) {
        return userRepository.findByUsernameContaining(keyword);
    }

    public List<User> getUsersByEmailEndingWith(String domain) {
        return userRepository.findByEmailEndingWith(domain);
    }

    public List<User> getUsersByFirstNameStartingWith(String prefix) {
        return userRepository.findByFirstNameStartingWith(prefix);
    }

    public List<User> getUsersByLastNameIgnoreCase(String lastName) {
        return userRepository.findByLastNameIgnoreCase(lastName);
    }

    public List<User> getUsersByFirstNameOrderByLastNameAsc(String firstName) {
        return userRepository.findByFirstNameOrderByLastNameAsc(firstName);
    }

    public long countUsersByEmail(String email) {
        return userRepository.countByEmail(email);
    }

    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}