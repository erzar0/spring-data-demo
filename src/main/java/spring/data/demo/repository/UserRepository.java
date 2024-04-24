package spring.data.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.data.demo.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByUsernameContaining(String keyword);

    List<User> findByEmailEndingWith(String domain);

    List<User> findByFirstNameStartingWith(String prefix);

    List<User> findByLastNameIgnoreCase(String lastName);

    List<User> findByFirstNameOrderByLastNameAsc(String firstName);

    long countByEmail(String email);

    void deleteByUsername(String username);

    void deleteByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}