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

    public RoleManager() {
        roles = new HashSet<Role>();
        roles.add(new Role("Administrator", true, true, true));
        roles.add(new Role("Manager", false, true, true));
        roles.add(new Role("Employee", false, false, false));
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
