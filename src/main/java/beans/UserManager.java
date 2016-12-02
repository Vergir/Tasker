package beans;

import entities.Role;
import entities.Task;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserManager {
    private Collection<User> users;
    @Autowired
    TaskManager taskManager;

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

    public User getUser(Long id) {
	    for (User u : users)
	        if (u.getId().equals(id))
	            return u;
	    return null;
    }

    public void addUser(String username, String password, Role role) {
        users.add(new User(username, BCrypt.hashpw(password, BCrypt.gensalt()), role));
    }


    public Set<Task> getUserTasks(User u){
	    Set<Task> result = new HashSet<Task>();

	    for (Task t : taskManager.getTasks())
	        if (t.getAssignee().equals(u))
	            result.add(t);

	    return result;
    }
}
