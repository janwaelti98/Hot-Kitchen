package ch.fhnw.webeC.repository;

import ch.fhnw.webeC.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByLastName(String lastName);

    Optional<User> findById(int id);

    Optional<User> findByUserName(String userName);

    User findByEmail(String email);
}
