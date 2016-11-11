package beans;

import entities.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserManager {
    private Collection<User> users;

	public UserManager() {
        //stub
        users = new ArrayList<User>();
        users.add(new User("Viktor", "vIKTOR"));
        users.add(new User("Zhenya", "zHENYA"));
        users.add(new User("Daniil", "dANIIL"));
	}

    public Collection<User> getUsers() {
        return users;
    }

    public User getRandomUser() {
        return (User)users.toArray()[(int)Math.floor(Math.random()*users.size())];
    }

    public boolean userExists(String username, String password) {
        User u = new User(username, password);
        return users.contains(u);
    }
}
