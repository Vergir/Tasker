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

    public String getDescription() {
        return dbObject.getDescription();
    }

    public void setDescription(String description) {
        dbObject.setDescription(description);
    }

    public User getAssignee() {
        //???
        return null;
    }

    public void setAssignee(User u) {
        //???
    }
}
