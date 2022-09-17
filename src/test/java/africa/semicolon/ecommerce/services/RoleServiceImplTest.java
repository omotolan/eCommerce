package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Role;
import africa.semicolon.ecommerce.data.model.RoleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;


    @Test
    public void testThatRoleCanBeCreated() {

        String response = roleService.createRole(RoleType.SELLER);
        assertEquals("Role created", response);

    }


    @Test
    public void testToGetAllRoles() {
        List<Role> roleList = roleService.getAllRole();

        assertEquals(1, roleList.size());
    }

}