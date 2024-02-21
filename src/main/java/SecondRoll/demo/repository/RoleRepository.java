package SecondRoll.demo.repository;

import SecondRoll.demo.models.ERole;
import SecondRoll.demo.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
