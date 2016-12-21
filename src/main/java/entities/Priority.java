package entities;

import jpa.DbObject;

/**
 * Created by Vergir on 21/12/2016.
 */
public class Priority {
    private DbObject dbObject;

    private static final long VALUE_ID = 1;

    public Priority() {
        dbObject = new DbObject();
    }
    public Priority(String description, Long value) {
        this();
        setDescription(description);
        setValue(value);
    }
    public Priority(long id) {
        dbObject = DbObject.find(id);
    }

    public Long getId() {
        return dbObject.getObjectId();
    }

    public Long getValue() {
        return dbObject.getNumParam(VALUE_ID);
    }
    public void setValue(Long value) {
        dbObject.setNumParam(VALUE_ID, value);
    }

    public String getDescription() {
        return dbObject.getDescription();
    }
    public void setDescription(String value) {
        dbObject.setDescription(value);
    }
}
