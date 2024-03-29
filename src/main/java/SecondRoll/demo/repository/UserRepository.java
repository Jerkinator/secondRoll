package SecondRoll.demo.repository;

import SecondRoll.demo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    User findUserById(String userId);

    User findUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}