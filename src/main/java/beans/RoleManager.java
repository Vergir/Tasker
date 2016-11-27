package beans;

import entities.Role;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Vergir on 15/11/2016.
 */
public class RoleManager {
    private Collection<Role> roles;

    public RoleManager() {
        roles = new ArrayList<Role>();
        roles.add(new Role("admin", true, true, true));
    }

    public Collection<Role> getRoles() {
        return roles;
    }
}
