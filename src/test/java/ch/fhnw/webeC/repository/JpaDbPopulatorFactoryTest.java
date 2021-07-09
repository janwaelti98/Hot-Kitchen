package ch.fhnw.webeC.repository;

import ch.fhnw.webeC.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class JpaDbPopulatorFactoryTest {
    private final UserRepository repo;

    JpaDbPopulatorFactoryTest(@Autowired UserRepository repo) {
        this.repo = repo;
    }

    @Test
    public void JsonPopulatorStartTest() {
        List<User> users = repo.findAll();
        assertEquals(18, users.size());

        users.forEach(user -> {
            if (1 == user.getId()) {
                assertEquals("Admin", user.getFirstName());
                assertEquals("Admin", user.getUsername());
            } else if (2 == user.getId()) {
                assertEquals("Jan", user.getFirstName());
                assertEquals("Author", user.getUsername());
            }
        });
    }
}