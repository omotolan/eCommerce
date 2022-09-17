package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Role;
import africa.semicolon.ecommerce.data.model.RoleType;
import africa.semicolon.ecommerce.exceptions.RoleException;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    String createRole(RoleType roleType);

//    Role getRoleByName(String name) throws RoleException;

    List<Role> getAllRole();
    Optional<Role> getRoleById(Long id);

}
