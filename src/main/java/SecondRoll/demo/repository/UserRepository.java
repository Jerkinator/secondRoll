package SecondRoll.demo.repository;

import SecondRoll.demo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String username);

    Boolean existsByUsername(String username);
}