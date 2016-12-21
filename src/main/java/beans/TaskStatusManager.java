package beans;

import entities.Priority;
import entities.TaskStatus;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vergir on 21/12/2016.
 */
public class TaskStatusManager {
    private Set<TaskStatus> statuses;

    TaskStatusManager() {
        statuses = new HashSet<TaskStatus>();
        statuses.add(new TaskStatus("Open"));
        statuses.add(new TaskStatus("Closed"));
        statuses.add(new TaskStatus("On Hold"));
        statuses.add(new TaskStatus("Indeterminate"));
    }

    public Set<TaskStatus> getStatuses() {
        return statuses;
    }

    public TaskStatus getTaskStatus(String desc) {
        for (TaskStatus ts : statuses)
            if (ts.getDescription().toLowerCase().equals(desc.toLowerCase()))
                return ts;
        return null;
    }
}
