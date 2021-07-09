package ch.fhnw.webeC.service;

import ch.fhnw.webeC.model.MyUserDetails;
import ch.fhnw.webeC.model.User;
import ch.fhnw.webeC.model.web.UserData;
import ch.fhnw.webeC.model.web.exception.UserAlreadyExistException;
import ch.fhnw.webeC.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo) {
        this.userRepository = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();
    }

    public void register(UserData userData) throws UserAlreadyExistException {
        if (checkIfUserExist(userData.getUserName())) {
            throw new UserAlreadyExistException("User already exists for this username");
        }

        User user = new User(userData.getUserName(), userData.getPassword(), "READER");
        BeanUtils.copyProperties(userData, user);

        userRepository.save(user);
    }

    public boolean checkIfUserExist(String name) {
        return userRepository.findByUserName(name).isPresent();
    }
}
