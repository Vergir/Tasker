package beans;

import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vergir on 30/11/2016.
 */
public class TaskManager {
    @Autowired
    private UserManager userManager;

    private Set<Task> tasks;

    private TaskManager() {}

    @PostConstruct
    private void init() {
        tasks = new HashSet<Task>();
        for (int i = 0; i < 25; i++)
            tasks.add(new Task());
        Task newTask = new Task();
        newTask.setAssignee(userManager.getUsers().iterator().next());
        tasks.add(newTask);
    }

    public Set<Task> getTasks() {
        return tasks;
    }
}
