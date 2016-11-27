package jpa;

import entities.Role;
import entities.User;

/**
 * Created by Vergir on 27/11/2016.
 */
public class Dao {
    public static Object getObjectById(long id) {
        if (id == 1)
            return new User("daoUser", "daoPasswd", new Role("daoUserRole", true, true, true));
        if (id == 3)
            return new Role("daoUserRole", true, true, true);
        return null;
    }
}
