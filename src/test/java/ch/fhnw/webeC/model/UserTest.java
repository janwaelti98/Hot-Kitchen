package ch.fhnw.webeC.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("Test")
public class UserTest {
    @Test
    public void createUserTest() {

        User newUser = new User("User", "Password", "ADMIN");

        assertEquals("User", newUser.getUsername());
        assertEquals("ADMIN", newUser.getRoles());
        assertNotNull(newUser.getEncryptedPassword());
    }
}


