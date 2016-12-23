package entities;

import jpa.DbObject;

/**
 * Created by Vergir on 11/11/2016.
 */
public class Role {
    private static final long CAN_CREATE_USERS_ID = 1;
    private static final long CAN_CREATE_PROJECTS_ID = 2;
    private static final long CAN_PROCESS_TASKS_ID = 3;

    private DbObject dbObject;

    public Role(String description, boolean canCreateUsers, boolean canCreateProjects, boolean canProcessTasks) {
        //TODO: implement creating of roles
        this.dbObject = new DbObject();
        this.dbObject.setDescription(description);
        this.dbObject.setNumParam(CAN_CREATE_USERS_ID, Long.valueOf(canCreateUsers ? 1 : 0));
        this.dbObject.setNumParam(CAN_CREATE_PROJECTS_ID, Long.valueOf(canCreateProjects ? 1 : 0));
        this.dbObject.setNumParam(CAN_PROCESS_TASKS_ID, Long.valueOf(canProcessTasks? 1 : 0));
    }
    public Role(long id) {
        this.dbObject = DbObject.find(id);
    }


    public long getId() {
        return dbObject.getObjectId();
    }
    public String getDescription() {
        return dbObject.getDescription();
    }
    public boolean getCanCreateUsers() {
        return dbObject.getNumParam(CAN_CREATE_USERS_ID) != 0;
    }
    public boolean getCanCreateProjects() {
        return dbObject.getNumParam(CAN_CREATE_PROJECTS_ID) != 0;
    }
    public boolean getCanProcessTasks() {
        return dbObject.getNumParam(CAN_PROCESS_TASKS_ID) != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (this.getCanCreateUsers()      != role.getCanCreateUsers()     ) return false;
        if (this.getCanCreateProjects()   != role.getCanCreateProjects()  ) return false;
        if (this.getCanProcessTasks() != role.getCanProcessTasks()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = dbObject.hashCode();

        hash += 13 * (this.getCanCreateUsers()      ? 13 : 7) + 42;
        hash += 13 * (this.getCanCreateProjects()   ? 13 : 7) + 42;
        hash += 13 * (this.getCanProcessTasks() ? 13 : 7) + 42;

        return hash;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
