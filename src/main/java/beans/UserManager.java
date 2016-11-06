package beans;

import java.util.ArrayList;
import java.util.Collection;

public class UserManager {
    private Collection<User> users;

	public UserManager() {
        //stub
        users = new ArrayList<User>();
        users.add(new User("Sasha", "sASHA"));
        users.add(new User("Viktor", "vIKTOR"));
        users.add(new User("Zhenya", "zHENYA"));
        users.add(new User("Daniil", "dANIIL"));
	}

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
