package ch.fhnw.webeC.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryIT {
    private final UserRepository repo;

    UserRepositoryIT(@Autowired UserRepository repo) {
        this.repo = repo;
    }

    @Test
    void findAll() {
        var users = repo.findAll();

        assertEquals(5, users.size());
    }

    @Test
    void findById() {
        var user = repo.findById(2);

        assertTrue(user.isPresent());
        assertEquals("Jan", user.get().getUsername());
    }

    @Test
    void findByLastname() {
        var user = repo.findByLastName("Nüssli");

        assertEquals(1, user.size());
        assertEquals("Nüssli", user.get(1).getLastName());
    }
}
