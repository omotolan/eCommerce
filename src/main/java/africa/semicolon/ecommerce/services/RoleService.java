package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Role;
import africa.semicolon.ecommerce.exceptions.RoleException;

import java.util.List;

public interface RoleService {
    void createRole(Role role);

    Role getRoleByName(String name) throws RoleException;

    List<Role> getAllRole();
}
