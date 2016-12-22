package beans;

import entities.Priority;
import entities.Task;
import entities.TaskStatus;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import util.Util;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vergir on 30/11/2016.
 */
public class TaskManager {
    private static final List<String> taskDescs = new ArrayList<String>();
    private static final List<String> taskStatuses = new ArrayList<String>();
    static {
        for (int i = 0; i < 200; i++) {
            taskDescs.add("Fix bug #" + Double.valueOf(Math.random() * Integer.MAX_VALUE).intValue() % 9999);
            taskDescs.add("Refactor code added in commit #"+Integer.toHexString(Double.valueOf(Math.random()).hashCode()));
        }
        taskStatuses.add("Open");
        taskStatuses.add("Closed");
        taskStatuses.add("On Hold");
        taskStatuses.add("Indeterminate");
    }

    @Autowired
    private UserManager userManager;
    @Autowired
    private PriorityManager priorityManager;
    @Autowired
    private TaskStatusManager taskStatusManager;

    private Set<Task> tasks;

    private TaskManager() {}

    @PostConstruct
    private void init() {
        tasks = new HashSet<Task>();
        List<User> users = userManager.getUsers();
        Set<Priority> prios = priorityManager.getPriorities();
        Set<TaskStatus> statuses = taskStatusManager.getStatuses();
        for (String desc : taskDescs)
            tasks.add(new Task(desc, Util.randomFrom(users), Util.randomFrom(users), Util.randomFrom(statuses), Util.randomFrom(prios)));
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }
}
