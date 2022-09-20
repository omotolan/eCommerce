package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Role;
import africa.semicolon.ecommerce.data.model.RoleType;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    String createRole(RoleType roleType);


    List<Role> getAllRole();
    Optional<Role> getRoleById(Long id);
    int getRoles();

}
