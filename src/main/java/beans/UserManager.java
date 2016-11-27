package beans;

import entities.Role;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Collection;

public class UserManager {
    private Collection<User> users;

	public UserManager() {
        //stub
        users = new ArrayList<User>();
        Role admin = new Role("admin", true, true, true);
        users.add(new User("Viktor", "vIKTOR", admin));
        users.add(new User("Zhenya", "zHENYA", admin));
        users.add(new User("Daniil", "dANIIL", admin));
	}

    public Collection<User> getUsers() {
        return users;
    }

    public User getUser(String username, String password) {
        for (User u : users)
            if (u.getUsername().equals(username) && BCrypt.checkpw(password, u.getPassword()))
                return u;
        return null;
    }

    public void addUser(String username, String password, Role role) {
        users.add(new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), role));
    }
}
