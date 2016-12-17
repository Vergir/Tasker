package entities;

import jpa.DbObject;

/**
 * Created by Vergir on 27/11/2016.
 */
public class Task {
    private DbObject dbObject;

    private static final long ASSIGNEE_ID = 1;

    public Task() {
        dbObject = new DbObject();
    }
    public Task(String description, long assignee) {
        this();
        setDescription(description);
        setAssignee(assignee);
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
}
