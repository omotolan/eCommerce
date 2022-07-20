package africa.semicolon.ecommerce.data.repositories;

import africa.semicolon.ecommerce.data.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
}
