package io.mhan.stompexample.users;

import io.mhan.stompexample.users.entity.User;
import io.mhan.stompexample.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findByIdElseThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public User createAndSave(String username, String password) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
