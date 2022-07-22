package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Role;
import africa.semicolon.ecommerce.data.repositories.RoleRepository;
import africa.semicolon.ecommerce.exceptions.RoleException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testThatRoleCanBeCreated() {
        Role role = new Role();
        role.setName("USER");
        roleService.createRole(role);
        assertEquals(1, roleRepository.count());

    }

    @Test
    public void testToFindRoleByName() throws RoleException {
        Role role = new Role();
        role.setName("USER");
        Role role1 = roleService.getRoleByName(role.getName());
        assertEquals("USER", role1.getName());
    }

    @Test
    public void testToGetAllRoles() {
        List<Role> roleList = roleService.getAllRole();

        assertEquals(1, roleList.size());
    }

}