package beans;

import entities.Role;
import entities.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserManager {
    private Collection<User> users;

	public UserManager() {
        //stub
        users = new ArrayList<User>();
        Role admin = Role.ADMIN;
        users.add(new User("Viktor", "vIKTOR", admin));
        users.add(new User("Zhenya", "zHENYA", admin));
        users.add(new User("Daniil", "dANIIL", admin));
	}

    public Collection<User> getUsers() {
        return users;
    }

    public User getUser(String username, String password) {
        for (User u : users)
            if (u.getUsername().equals(username) && u.getPassword().equals(password))
                return u;
        return null;
    }

    public void addUser(String username, String password, Role role) {
        users.add(new User(username, password, role));
    }
}
