package academy.service;

import academy.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
