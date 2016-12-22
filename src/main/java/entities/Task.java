package entities;

import jpa.DbObject;

/**
 * Created by Vergir on 27/11/2016.
 */
public class Task {
    private DbObject dbObject;

    private static final long ASSIGNEE_ID = 1;
    private static final long STATUS_ID = 2;
    private static final long PRIORITY_ID = 3;
    private static final long ASSIGNED_BY_ID = 4;

    public Task() {
        dbObject = new DbObject();
    }
    public Task(String description, User assignee, User assigner, TaskStatus status, Priority priority) {
        this();
        setDescription(description);
        setAssignee(assignee);
        setAssignedBy(assigner);
        setStatus(status);
        setPriority(priority);
    }

    public Long getId() {
        return dbObject.getObjectId();
    }

    public String getDescription() {
        return dbObject.getDescription();
    }
    public void setDescription(String description) {
        dbObject.setDescription(description);
    }

    public User getAssignedBy() {
        return new User(dbObject.getNumParam(ASSIGNED_BY_ID));
    }
    public void setAssignedBy(User assigner) {
        dbObject.setNumParam(ASSIGNED_BY_ID, assigner.getId());
    }


    public User getAssignee() {
        return new User(dbObject.getNumParam(ASSIGNEE_ID));
    }
    public void setAssignee(User assignee) {
        dbObject.setNumParam(ASSIGNEE_ID, assignee.getId());
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
