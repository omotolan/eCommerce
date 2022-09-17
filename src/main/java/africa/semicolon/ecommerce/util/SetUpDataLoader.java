package africa.semicolon.ecommerce.util;


import africa.semicolon.ecommerce.data.model.Role;
import africa.semicolon.ecommerce.data.model.RoleType;
import africa.semicolon.ecommerce.data.model.User;
import africa.semicolon.ecommerce.data.repositories.UserRepository;
import africa.semicolon.ecommerce.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Component
@Slf4j
@RequiredArgsConstructor
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        roleService.createRole(RoleType.SUPER_ADMIN);
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(1L).get());

        String password = System.getenv("ADMIN_PASSWORD");
        if (userRepository.findUserByEmailIgnoreCase("admin@yahoo.com").isEmpty()) {
            User user = new User();
            user.setFirstName("admin");
            user.setLastName("user");
            user.setEmail("admin@yahoo.com");
            user.setDateJoined(LocalDate.now());
            user.setIsVerified(Boolean.TRUE);
            user.setPhoneNumber("08182769505");
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
