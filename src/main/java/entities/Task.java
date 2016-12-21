package entities;

import jpa.DbObject;

import java.util.Properties;

/**
 * Created by Vergir on 27/11/2016.
 */
public class Task {
    private DbObject dbObject;

    private static final long ASSIGNEE_ID = 1;
    private static final long STATUS_ID = 2;
    private static final long PRIORITY_ID = 3;

    public Task() {
        dbObject = new DbObject();
    }
    public Task(String description, long assigneeId, TaskStatus status, Priority priority) {
        this();
        setDescription(description);
        setAssignee(assigneeId);
        setStatus(status);
        setPriority(priority);
    }

    public long getId() {
        return dbObject.getObjectId();
    }

    public String getDescription() {
        return dbObject.getDescription();
    }
    public void setDescription(String description) {
        dbObject.setDescription(description);
    }

    public Long getAssignee() {
        return dbObject.getNumParam(ASSIGNEE_ID);
    }
    public void setAssignee(Long assigneeId) {
        dbObject.setNumParam(ASSIGNEE_ID, assigneeId);
    }

    public TaskStatus getStatus() {
        return new TaskStatus(dbObject.getNumParam(STATUS_ID));
    }
    public void setStatus(TaskStatus status) {
        dbObject.setNumParam(STATUS_ID, status.getId());
    }

    public Priority getPriority() {
        return new Priority(dbObject.getNumParam(PRIORITY_ID));
    }
    public void setPriority(Priority priority) {
        dbObject.setNumParam(PRIORITY_ID, priority.getId());
    }
}
