package entities;

import jpa.Dao;
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

    public long getId() {
        return dbObject.getObjectId();
    }

    public String getDescription() {
        return dbObject.getDescription();
    }
    public void setDescription(String description) {
        dbObject.setDescription(description);
    }

    public User getAssignee() {
        long userId = dbObject.getNumericParamValue(ASSIGNEE_ID);
        return (User)Dao.getObjectById(userId);
    }
    public void setAssignee(User u) {
        dbObject.setNumericParamValue(ASSIGNEE_ID, u.getId());
    }
}
