package spring.data.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring.data.demo.entity.User;
import spring.data.demo.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByUsername_ExistingUser() {
        // Mocking data
        User user = new User();
        user.setUsername("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        // Test
        User foundUser = userService.getUserByUsername("testUser");

        // Verify
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    public void testGetUserByUsername_NonExistingUser() {
        // Mocking data
        when(userRepository.findByUsername("nonExistingUser")).thenReturn(null);

        // Test
        User foundUser = userService.getUserByUsername("nonExistingUser");

        // Verify
        assertNull(foundUser);
    }

    @Test
    public void testGetUserByEmail_ExistingUser() {
        // Mocking data
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        // Test
        User foundUser = userService.getUserByEmail("test@example.com");

        // Verify
        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    public void testGetUserByEmail_NonExistingUser() {
        // Mocking data
        when(userRepository.findByEmail("nonexisting@example.com")).thenReturn(null);

        // Test
        User foundUser = userService.getUserByEmail("nonexisting@example.com");

        // Verify
        assertNull(foundUser);
    }

    @Test
    public void testGetUsersByFirstNameAndLastName() {
        // Mocking data
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");

        User user2 = new User();
        user2.setFirstName("John");
        user2.setLastName("Smith");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(users);

        // Test
        List<User> foundUsers = userService.getUsersByFirstNameAndLastName("John", "Doe");

        // Verify
        assertEquals(2, foundUsers.size());
        assertEquals("John", foundUsers.getFirst().getFirstName());
        assertEquals("Doe", foundUsers.getFirst().getLastName());
    }

    @Test
    public void testGetUsersByFirstNameOrLastName() {
        // Mocking data
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");

        User user2 = new User();
        user2.setFirstName("Alice");
        user2.setLastName("Doe");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findByFirstNameOrLastName("John", "Doe")).thenReturn(users);

        // Test
        List<User> foundUsers = userService.getUsersByFirstNameOrLastName("John", "Doe");

        // Verify
        assertEquals(2, foundUsers.size());
        assertEquals("John", foundUsers.get(0).getFirstName());
        assertEquals("Doe", foundUsers.get(0).getLastName());
    }

    @Test
    public void testGetUsersByUsernameContaining() {
        // Mocking data
        User user1 = new User();
        user1.setUsername("testUser");
        User user2 = new User();
        user2.setUsername("userTest");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findByUsernameContaining("test")).thenReturn(users);

        // Test
        List<User> foundUsers = userService.getUsersByUsernameContaining("test");

        // Verify
        assertEquals(2, foundUsers.size());
        assertEquals("testUser", foundUsers.getFirst().getUsername());
    }

    @Test
    public void testGetUsersByEmailEndingWith() {
        // Mocking data
        User user1 = new User();
        user1.setEmail("example@example.com");
        User user2 = new User();
        user2.setEmail("test@test.com");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findByEmailEndingWith("com")).thenReturn(users);

        // Test
        List<User> foundUsers = userService.getUsersByEmailEndingWith("com");

        // Verify
        assertEquals(2, foundUsers.size());
        assertEquals("example@example.com", foundUsers.getFirst().getEmail());
    }

    @Test
    public void testGetUsersByFirstNameStartingWith() {
        // Mocking data
        User user1 = new User();
        user1.setFirstName("John");
        User user2 = new User();
        user2.setFirstName("Jane");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findByFirstNameStartingWith("J")).thenReturn(users);

        // Test
        List<User> foundUsers = userService.getUsersByFirstNameStartingWith("J");

        // Verify
        assertEquals(2, foundUsers.size());
        assertEquals("John", foundUsers.getFirst().getFirstName());
    }

    @Test
    public void testGetUsersByLastNameIgnoreCase() {
        // Mocking data
        User user1 = new User();
        user1.setLastName("Doe");
        User user2 = new User();
        user2.setLastName("DOE");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findByLastNameIgnoreCase("doe")).thenReturn(users);

        // Test
        List<User> foundUsers = userService.getUsersByLastNameIgnoreCase("doe");

        // Verify
        assertEquals(2, foundUsers.size());
        assertEquals("Doe", foundUsers.getFirst().getLastName());
    }

    @Test
    public void testGetUsersByFirstNameOrderByLastNameAsc() {
        // Mocking data
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        User user2 = new User();
        user2.setFirstName("John");
        user2.setLastName("Smith");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findByFirstNameOrderByLastNameAsc("John")).thenReturn(users);

        // Test
        List<User> foundUsers = userService.getUsersByFirstNameOrderByLastNameAsc("John");

        // Verify
        assertEquals(2, foundUsers.size());
        assertEquals("Doe", foundUsers.getFirst().getLastName());
        verify(userRepository, times(1)).findByFirstNameOrderByLastNameAsc("John");
    }

    @Test
    public void testCountUsersByEmail() {
        // Mocking data
        when(userRepository.countByEmail("test@example.com")).thenReturn(3L);

        // Test
        long count = userService.countUsersByEmail("test@example.com");

        // Verify
        assertEquals(3, count);
    }

    @Test
    public void testDeleteUserByUsername() {
        // Test
        userService.deleteUserByUsername("testUser");

        // Verify
        verify(userRepository, times(1)).deleteByUsername("testUser");
    }

    @Test
    public void testDeleteUserByEmail() {
        // Test
        userService.deleteUserByEmail("test@example.com");

        // Verify
        verify(userRepository, times(1)).deleteByEmail("test@example.com");
    }

    @Test
    public void testExistsByUsername() {
        // Mocking data
        when(userRepository.existsByUsername("testUser")).thenReturn(true);

        // Test
        boolean exists = userService.existsByUsername("testUser");

        // Verify
        assertTrue(exists);
    }

    @Test
    public void testExistsByEmail() {
        // Mocking data
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        // Test
        boolean exists = userService.existsByEmail("test@example.com");

        // Verify
        assertTrue(exists);
    }

}
