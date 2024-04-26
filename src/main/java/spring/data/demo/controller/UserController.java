package spring.data.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.data.demo.entity.User;
import spring.data.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public HttpStatus addUser(@RequestBody User user) {
        return userService.addUser(user) != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/firstName/{firstName}/lastName/{lastName}")
    public ResponseEntity<List<User>> getUsersByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        List<User> users = userService.getUsersByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/usernameContaining/{keyword}")
    public ResponseEntity<List<User>> getUsersByUsernameContaining(@PathVariable String keyword) {
        List<User> users = userService.getUsersByUsernameContaining(keyword);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/emailEndingWith/{domain}")
    public ResponseEntity<List<User>> getUsersByEmailEndingWith(@PathVariable String domain) {
        List<User> users = userService.getUsersByEmailEndingWith(domain);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/firstNameStartingWith/{prefix}")
    public ResponseEntity<List<User>> getUsersByFirstNameStartingWith(@PathVariable String prefix) {
        List<User> users = userService.getUsersByFirstNameStartingWith(prefix);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/lastNameIgnoreCase/{lastName}")
    public ResponseEntity<List<User>> getUsersByLastNameIgnoreCase(@PathVariable String lastName) {
        List<User> users = userService.getUsersByLastNameIgnoreCase(lastName);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/firstName/{firstName}/orderByLastNameAsc")
    public ResponseEntity<List<User>> getUsersByFirstNameOrderByLastNameAsc(@PathVariable String firstName) {
        List<User> users = userService.getUsersByFirstNameOrderByLastNameAsc(firstName);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/countByEmail/{email}")
    public ResponseEntity<Long> countUsersByEmail(@PathVariable String email) {
        long count = userService.countUsersByEmail(email);
        return ResponseEntity.ok(count);
    }


    @DeleteMapping("/username/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
}


}