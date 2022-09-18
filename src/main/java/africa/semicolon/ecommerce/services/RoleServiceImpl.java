package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Role;
import africa.semicolon.ecommerce.data.model.RoleType;
import africa.semicolon.ecommerce.data.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public String createRole(RoleType roleType) {
        Role role = new Role();
        role.setRoleType(roleType);
        roleRepository.save(role);
        return "Role created";
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    //    @Override
//    public Role getRoleByName(String name) throws RoleException {
//        Optional<Role> optionalRole = roleRepository.findByName(name);
//        if (optionalRole.isEmpty()) {
//            throw new RoleException("Role does not exist");
//        }
//        return optionalRole.get();
//    }
    @Override
    public int getRoles() {
        return roleRepository.findAll().size();
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
}
