package ch.fhnw.webeC.service;

import ch.fhnw.webeC.model.User;
import ch.fhnw.webeC.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("Test")
public class UserServiceTest {
    UserService service;

    UserServiceTest() {
        User mockUser1 = mock(User.class);
        when(mockUser1.getId()).thenReturn(1);
        when(mockUser1.getRoles()).thenReturn("ADMIN");
        when(mockUser1.getUsername()).thenReturn("User1");
        User mockUser2 = mock(User.class);
        when(mockUser2.getId()).thenReturn(2);
        when(mockUser1.getRoles()).thenReturn("ADMIN");
        when(mockUser2.getUsername()).thenReturn("User2");
        User mockUser3 = mock(User.class);
        when(mockUser3.getId()).thenReturn(3);
        when(mockUser1.getRoles()).thenReturn("ADMIN");
        when(mockUser3.getUsername()).thenReturn("User3");

        List<User> allUsers = new ArrayList<>();
        allUsers.add(mockUser1);
        allUsers.add(mockUser2);
        allUsers.add(mockUser3);

        var repo = mock(UserRepository.class);
        when(repo.findAll()).thenReturn(allUsers);
        when(repo.findByUserName("User1")).thenReturn(java.util.Optional.of(mockUser1));
        when(repo.findByUserName("User2")).thenReturn(java.util.Optional.of(mockUser2));
        when(repo.findByUserName("User3")).thenReturn(java.util.Optional.of(mockUser3));
        service = new UserService(repo);
    }

    @Test
    void loadByUsernameTest() {
        var user = service.loadUserByUsername("User1");
        assertEquals("User1", user.getUsername());
    }

    @Test
    void checkIfUserExistsTest() {
        assertTrue(service.checkIfUserExist("User1"));
        assertTrue(service.checkIfUserExist("User2"));
        assertTrue(service.checkIfUserExist("User3"));
        assertFalse(service.checkIfUserExist("Nonexisting user"));
    }
}
