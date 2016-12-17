package entities;

import jpa.DbObject;

/**
 * Created by Vergir on 11/11/2016.
 */
public class Role {
    private static final long CAN_CREATE_USERS_ID = 1;
    private static final long CAN_CREATE_PROJECTS_ID = 2;
    private static final long CAN_ASSIGN_DEVELOPERS_ID = 3;

    private DbObject dbObject;

    public Role(String description, boolean canCreateUsers, boolean canCreateProjects, boolean canAssignDevelopers) {
        //TODO: implement creating of roles
        this.dbObject = new DbObject();
        this.dbObject.setDescription(description);
        this.dbObject.setNumParam(CAN_CREATE_USERS_ID, Long.valueOf(canCreateUsers ? 1 : 0));
        this.dbObject.setNumParam(CAN_CREATE_PROJECTS_ID, Long.valueOf(canCreateProjects ? 1 : 0));
        this.dbObject.setNumParam(CAN_ASSIGN_DEVELOPERS_ID, Long.valueOf(canAssignDevelopers? 1 : 0));
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
    public boolean getCanAssignDevelopers() {
        return dbObject.getNumParam(CAN_ASSIGN_DEVELOPERS_ID) != 0;
    }

    public static Role fromLong(long role) {
        boolean canCreateUsers;
        boolean canCreateProjects;
        boolean canAssignDevelopers;

        canAssignDevelopers = role >= 4;
        role -= canAssignDevelopers ? 4 : 0;
        canCreateProjects = role >= 2;
        role -= canCreateProjects ? 2 : 0;
        canCreateUsers = role >= 1;

        return new Role("created from number", canCreateUsers, canCreateProjects, canAssignDevelopers);
    }

    public long toLong() {
        long result = 0;
        result += getCanCreateUsers() ? 1 : 0;
        result += getCanCreateProjects() ? 2 : 0;
        result += getCanAssignDevelopers() ? 4 : 0;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (this.getCanCreateUsers()      != role.getCanCreateUsers()     ) return false;
        if (this.getCanCreateProjects()   != role.getCanCreateProjects()  ) return false;
        if (this.getCanAssignDevelopers() != role.getCanAssignDevelopers()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = dbObject.hashCode();

        hash += 13 * (this.getCanCreateUsers()      ? 13 : 7) + 42;
        hash += 13 * (this.getCanCreateProjects()   ? 13 : 7) + 42;
        hash += 13 * (this.getCanAssignDevelopers() ? 13 : 7) + 42;

        return hash;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
