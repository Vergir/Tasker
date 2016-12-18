package beans;

import entities.Task;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vergir on 30/11/2016.
 */
public class TaskManager {
    public static final List<String> taskDescs = new ArrayList<String>();
    static {
        for (int i = 0; i < 100; i++) {
            taskDescs.add("Fix bug #" + Double.valueOf(Math.random() * Integer.MAX_VALUE).intValue() % 9999);
            taskDescs.add("Refactor code added in commit #"+Integer.toHexString(Double.valueOf(Math.random()).hashCode()));
        }
        taskDescs.add("Make boss a coffee");
        taskDescs.add("Clean up DB");
    }

    @Autowired
    private UserManager userManager;

    private Set<Task> tasks;

    private TaskManager() {}

    @PostConstruct
    private void init() {
        tasks = new HashSet<Task>();
        List<User> users = userManager.getUsers();
        for (String td : taskDescs)
            tasks.add(new Task(td, users.get(Double.valueOf(Math.random()*users.size()).intValue()).getId()));
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }
}
