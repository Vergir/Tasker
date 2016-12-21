package beans;

import entities.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vergir on 15/11/2016.
 */
public class RoleManager {
    private Set<Role> roles;
    public static final Role NO_ROLE = new Role("none", false, false, false);
    public static final Role CHIEF_ADMIN = new Role("Chief Admin", true, true, true);


    public RoleManager() {
        roles = new HashSet<Role>();
        roles.add(new Role("Administrator", true, true, true));
        roles.add(new Role("Manager", false, true, true));
        roles.add(new Role("Employee", false, false, false));
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Role getRole(String name) {
        for (Role r : roles)
            if (r.getDescription().toLowerCase().equals(name.toLowerCase()))
                return r;
        return null;
    }
}
