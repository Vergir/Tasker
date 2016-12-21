package entities;

import jpa.DbObject;

/**
 * Created by Vergir on 21/12/2016.
 */
public class TaskStatus {
    private DbObject dbObject;

    public TaskStatus() {
        dbObject = new DbObject();
    }
    public TaskStatus(String description) {
        this();
        setDescription(description);
    }
    public TaskStatus(long id) {
        dbObject = DbObject.find(id);
    }

    public Long getId() {
        return dbObject.getObjectId();
    }

    public String getDescription() {
        return dbObject.getDescription();
    }
    public void setDescription(String value) {
        dbObject.setDescription(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskStatus that = (TaskStatus) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return dbObject != null ? dbObject.hashCode() : 0;
    }
}
