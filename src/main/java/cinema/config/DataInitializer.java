package cinema.config;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
        User userAdmin = new User();
        userAdmin.setEmail("admin@i.ua");
        userAdmin.setPassword("admin123");
        userAdmin.setRoles(Set.of(adminRole));
        userService.add(userAdmin);
        User user = new User();
        user.setEmail("bob@i.ua");
        user.setPassword("bob123");
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}
